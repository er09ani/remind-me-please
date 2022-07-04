package com.remindermeapp.controller;

import java.text.ParseException;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.remindermeapp.dto.ReminderForm;
import com.remindermeapp.entity.BusinessUser;
import com.remindermeapp.entity.Reminder;
import com.remindermeapp.enums.ReminderStatus;
import com.remindermeapp.repository.BusinessUserRepository;
import com.remindermeapp.repository.ReminderRepository;
import com.remindermeapp.repository.UserRepository;

@Controller
public class ReminderController {
	
	@Autowired
	private BusinessUserRepository businessUserRepo;
	
	@Autowired
	private UserRepository userRepo;
	
	@Autowired
	private ReminderRepository reminderRepo;
	
	@GetMapping("/reminders")
	public String getUserReminders(Model model) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		 String loggedInUserName = "er09ani@gmail.com";
		 
		 List<Reminder> listOfReminders = new LinkedList<>();
		 Optional<BusinessUser> OpBusinessUser = businessUserRepo.findByCustomUser((userRepo.findByEmailId(loggedInUserName)).get(0));
		 BusinessUser businessUser = OpBusinessUser.get();
		 listOfReminders = (List<Reminder>) businessUser.getReminders();
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
	
	@PostMapping("/reminders")
	public String saveReminder(Model model, @ModelAttribute ReminderForm reminderForm) throws ParseException {
		
		 String loggedInUserName = "er09ani@gmail.com";
		 
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
		 
		 return "redirect:/reminders/"+id.toString();
	}
	
	@GetMapping("/reminders/{id}")
	public String viewReminder(Model model, @PathVariable("id") String id, ReminderForm reminderForm ) {
		
		Optional<Reminder> reminderOptional = reminderRepo.findOneByReminderId(UUID.fromString(id));
		if(reminderOptional.isPresent()) {
			reminderForm = new ReminderForm(reminderOptional.get());
			System.out.println(reminderForm);
			model.addAttribute("reminderForm", reminderForm);
			return "reminder";
		}
		else return "error";
		
	}
	
}
