package com.remindermeapp.controller;

import java.text.ParseException;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;

import com.remindermeapp.dto.ReminderForm;
import com.remindermeapp.entity.BusinessUser;
import com.remindermeapp.entity.Reminder;
import com.remindermeapp.enums.ReminderStatus;
import com.remindermeapp.repository.BusinessUserRepository;
import com.remindermeapp.repository.ReminderRepository;
import com.remindermeapp.repository.UserRepository;
import com.remindermeapp.utils.DateUtil;

@Controller
@PreAuthorize("hasRole('USER')")
public class ReminderController {
	
	@Autowired
	private BusinessUserRepository businessUserRepo;
	
	@Autowired
	private UserRepository userRepo;
	
	@Autowired
	private ReminderRepository reminderRepo;
	
//	Read
	@GetMapping("/reminders")
	public String getUserReminders(Model model) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		 String loggedInUserName = auth.getName();
		 System.out.println(loggedInUserName);
		 List<Reminder> listOfReminders = new LinkedList<>();
		 Optional<BusinessUser> OpBusinessUser = businessUserRepo.findByCustomUser((userRepo.findByEmailId(loggedInUserName)).get(0));
		 BusinessUser businessUser = OpBusinessUser.get();
		 System.out.println(businessUser);
		 listOfReminders = (List<Reminder>) reminderRepo.findAllByBusinessUser(businessUser);
		 System.out.println(listOfReminders);
		 
		 List<ReminderForm> remindersForm = new LinkedList<ReminderForm>();
		 for(Reminder reminder :  listOfReminders) {
			 remindersForm.add(new ReminderForm(reminder));
		 }
		 
		 model.addAttribute("RemindersForm", remindersForm);
		 
		return "reminders";
	}
	
	
	@GetMapping("/reminders/new")
	public String createReminder(Model model) {
		
		//get logged In user if email id is verified put emailid to reminderForm and same for phone number
		ReminderForm reminderForm = new ReminderForm();
		
		reminderForm.setEmailId("er09ani@gmail.com");
		reminderForm.setPhoneNumber("+91 9730049154");
		model.addAttribute("reminderForm", reminderForm);
		//reminderForm.setEmailIdChecked(true);
		return "reminder_create";
	}
	
//	Create
	@PostMapping("/reminders")
	public String saveReminder(Model model, @ModelAttribute ReminderForm reminderForm) throws ParseException {
		
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String loggedInUserName = auth.getName();
		 
		 List<Reminder> listOfReminders = new LinkedList<>();
		 
		 Optional<BusinessUser> OpBusinessUser = businessUserRepo.findByCustomUser((userRepo.findByEmailId(loggedInUserName)).get(0));
		 BusinessUser businessUser = OpBusinessUser.get();
		 listOfReminders = (List<Reminder>) businessUser.getReminders();
		 
//		 if(reminderForm.isEmailIdChecked)
//		 System.out.println("emailChecked");
//		 if(reminderForm.isPhoneNumberChecked)
//		 System.out.println("phoneNuberChecked");
		 
		 Reminder reminder = new Reminder(reminderForm);
		 reminder.setBusinessUser(businessUser);
		 reminder.setCreatedOn(new Date());
		 reminder.setRemiderStatus(ReminderStatus.PENDING);
		 UUID id = reminderRepo.save(reminder).getReminderId();
		 
		 return "redirect:/reminders";
	}
	
//	Read
	@GetMapping("/reminders/{id}")
	public String viewReminder(Model model, @PathVariable("id") String id, ReminderForm reminderForm ) {
		
		Optional<Reminder> reminderOptional = reminderRepo.findOneByReminderId(UUID.fromString(id));
		if(reminderOptional.isPresent()) {
			reminderForm = new ReminderForm(reminderOptional.get());
			System.out.println(reminderForm);
			model.addAttribute("reminderForm", reminderForm);
			model.addAttribute("reminderId", id);
			return "reminder";
		}
		else return "error";
		
	}
	
//	Update
	@PutMapping("/reminders/{id}")
	public String updateReminder(Model model, @PathVariable("id") String id, @ModelAttribute ReminderForm reminderForm) {
		
		Optional<Reminder> reminderOptional = reminderRepo.findOneByReminderId(UUID.fromString(id));
		if(reminderOptional.isPresent()) {
			Reminder reminderToUpdate = reminderOptional.get();
			reminderToUpdate.setTitle(reminderForm.getTitle());
			reminderToUpdate.setMessage(reminderForm.getMessage());
			
			reminderToUpdate.setSendTime(DateUtil.getDateTime(reminderForm.getDate(), reminderForm.getTime()));
			reminderToUpdate.setModifiedOn(new Date());
			reminderToUpdate.setEmailIdChecked(reminderForm.isEmailIdChecked());
			reminderToUpdate.setPhoneNumberChecked(reminderForm.isPhoneNumberChecked());
			
			reminderRepo.save(reminderToUpdate);
			
			return "redirect:/reminders/" + id;
		}
		else return "error";
		
	}
	
//	Delete
	@DeleteMapping("/reminders/{id}")
	public String deleteReminder(Model model, @PathVariable("id") String id, HttpServletResponse response) {
		Optional<Reminder> reminderOptional = reminderRepo.findOneByReminderId(UUID.fromString(id));
		if(reminderOptional.isPresent()) {
			reminderRepo.delete(reminderOptional.get());
			
			return "reminders";
		}
		
		else {
			response.setStatus(HttpServletResponse.SC_NOT_FOUND);
			return "/reminders";
		}
	}
	
//	@GetMapping("reminder/{id}/edit")
//	public String editReminder(Model model, @PathVariable("id") String id) {
//		ReminderForm form = new ReminderForm();
//		model.addAttribute("RemindersForm", form);
//		return "reminder_edit";
//	}
	
}
