package com.securitylast.securityclass;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import com.securitylast.usersec.UserDetailsHere;
@Configuration
@EnableWebSecurity
public class SecureButamwa extends WebSecurityConfigurerAdapter{
	@Autowired
	private UserDetailsHere userdetailshere;
	
	@Bean
	PasswordEncoder passEconder() {
		return new BCryptPasswordEncoder();
	}
	@Bean
    public AuthenticationSuccessHandler myAuthenticationSuccessHandler(){
        return new PathUrlRedirect();
    }
public SecureButamwa(UserDetailsHere userdetailshere) {
		this.userdetailshere = userdetailshere;
	}

@Override
protected void configure(AuthenticationManagerBuilder auth) throws Exception {
	auth.authenticationProvider(daoAuthenticationProvider());
  
}
@Override
	protected void configure(HttpSecurity http) throws Exception {
		http
		.authorizeRequests().antMatchers("/webjars/**").permitAll()
		.antMatchers("/Login/**").permitAll()
		.antMatchers("/publicstyles/**").permitAll()
		.antMatchers("/admin/**").permitAll()
		.antMatchers("/farmer/**").permitAll()
		.antMatchers("/location/**").permitAll()
		.antMatchers("/userregistration/**").permitAll()
		.antMatchers("/visitor/**").permitAll()
		.antMatchers("/admin/**").authenticated()
		.antMatchers("/userview/**").authenticated()
		.antMatchers("/admin/images/**").authenticated()
		.antMatchers("/admin/indexadmin").hasAnyRole("farmer","ADMIN","VISITOR")
		.and()
		.formLogin()
		.loginPage("/butamwa/login").permitAll()
		.successHandler(myAuthenticationSuccessHandler())
		.and()
		.logout()
		.logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
		.logoutSuccessUrl("/butamwa/login").and()
		.rememberMe().userDetailsService(userdetailshere);
	}

@Bean
DaoAuthenticationProvider daoAuthenticationProvider() {
	DaoAuthenticationProvider daoau= new DaoAuthenticationProvider();
	daoau.setUserDetailsService(this.userdetailshere);
	daoau.setForcePrincipalAsString(true);
	daoau.setPasswordEncoder(passEconder());
	return daoau;
}


}
