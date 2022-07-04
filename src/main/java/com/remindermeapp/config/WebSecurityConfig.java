package com.remindermeapp.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.authority.mapping.GrantedAuthoritiesMapper;
import org.springframework.security.core.authority.mapping.SimpleAuthorityMapper;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@SuppressWarnings("deprecation")
@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter{
	
	@Autowired
	private UserDetailsService userDetailsService;
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http
			.authorizeRequests()
			.antMatchers("/h2-console/**").permitAll()
			.antMatchers("/", "/home","/register").permitAll()
			.anyRequest().permitAll()
			.and()
			.formLogin()
            .loginPage("/login")
            .failureUrl("/login?error")
            .permitAll()
            .and()
            .logout()
            .invalidateHttpSession(true)
            .clearAuthentication(true)
            .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
            .logoutSuccessUrl("/login?logout")
       .permitAll();
        ;
        http.csrf().disable();
        http.headers().frameOptions().disable();
	}
	
	
//	@Bean
//	@Override
//	public UserDetailsService userDetailsService() {
//		UserDetails user = 
//				User.withDefaultPasswordEncoder()
//				.username("user")
//				.password("password")
//				.build();
//		
//		return new InMemoryUserDetailsManager(user);
//		
//	}
//	
//	@Bean
//	public UserDetailsService users(DataSource datasource) {
//		return new MyJdbcUserDetailsManager(datasource);
//		
//	}

    @Bean
    public GrantedAuthoritiesMapper authoritiesMapper() {
        SimpleAuthorityMapper authorityMapper = new SimpleAuthorityMapper();
        authorityMapper.setConvertToUpperCase(true);
        authorityMapper.setPrefix("ROLE_");
        return authorityMapper;
    }
	
	@Override
	   protected void configure(AuthenticationManagerBuilder auth) 
	          throws Exception {
	      
	      auth.authenticationProvider(authenticationProvider());
	   }
	
	@Bean
	   public DaoAuthenticationProvider authenticationProvider() {
	      
	      DaoAuthenticationProvider auth = 
	            new DaoAuthenticationProvider();
	      auth.setUserDetailsService(userDetailsService);
	      auth.setPasswordEncoder(passwordEncoder());
	      auth.setAuthoritiesMapper(authoritiesMapper());
	      return auth;
	   }
	
	@Bean
	public static PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
}
