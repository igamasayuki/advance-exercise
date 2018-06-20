package com.example.ec_201804d;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;


/**
 * ログイン認証用設定.
 * 
 * @author hibiki.ono
 *
 */
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	
	@Configuration
	@Order(1)
	public static class UserSecurityConfig extends WebSecurityConfigurerAdapter{
		
		@Autowired
		private UserDetailsService userDetailsService;
		
		/* (non-Javadoc)
		 * @see org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter#configure(org.springframework.security.config.annotation.web.builders.WebSecurity)
		 */
		@Override
		public void configure(WebSecurity web) {
			web.ignoring().antMatchers("/css/**", "/img/**", "/js/**", "/fonts/**");
		}
		/* (non-Javadoc)
		 * @see org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter#configure(org.springframework.security.config.annotation.web.builders.HttpSecurity)
		 */
		@Override
		protected void configure(HttpSecurity http) throws Exception{
			http.authorizeRequests().antMatchers("/userlogin/**","/register/**", "/itemList/**", "item_detail/**", "/registerAdmin/**").permitAll()
			.anyRequest().authenticated();
			
			http.formLogin()
			.loginPage("/userlogin/toUserLogin")
			.loginProcessingUrl("/login")
			.failureUrl("/userlogin/toUserLogin?error=true")
			.defaultSuccessUrl("/viewItemList", false)
			.usernameParameter("email")
			.passwordParameter("password");
			
			http.logout()
			.logoutRequestMatcher(new AntPathRequestMatcher("/logout**"))
			.logoutSuccessUrl("/userlogin/toUserLogin")
			.deleteCookies("JSESSIONID")
			.invalidateHttpSession(true);
		}
		/* (non-Javadoc)
		 * @see org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter#configure(org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder)
		 */
		@Override
		public void configure(AuthenticationManagerBuilder auth) throws Exception{
			auth.userDetailsService(userDetailsService)
			.passwordEncoder(new BCryptPasswordEncoder());
		}
	}
	
	@Configuration
	@Order(2)
	public static class AdminUserSecurityConfig extends WebSecurityConfigurerAdapter{
		
		@Autowired
		private UserDetailsService adminUserDetailsService;
		
		/* (non-Javadoc)
		 * @see org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter#configure(org.springframework.security.config.annotation.web.builders.WebSecurity)
		 */
		@Override
		public void configure(WebSecurity web) {
			web.ignoring().antMatchers("/css/**", "/img/**", "/js/**", "/fonts/**");
		}
		/* (non-Javadoc)
		 * @see org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter#configure(org.springframework.security.config.annotation.web.builders.HttpSecurity)
		 */
		@Override
		protected void configure(HttpSecurity http) throws Exception{
			http.authorizeRequests().antMatchers("/adminLogin/**", "registerAdmin/**").permitAll()
			.anyRequest().authenticated();
			
			http.formLogin()
			.loginPage("/adminLogin/viewAdminLogin")
			.loginProcessingUrl("/adminlogin")
			.failureUrl("/adminLogin/viewAdminLogin?error=true")
			.defaultSuccessUrl("/fromLogintoMenu", false)
			.usernameParameter("email")
			.passwordParameter("password");
			
			http.logout()
			.logoutRequestMatcher(new AntPathRequestMatcher("/adminlogout**"))
			.logoutSuccessUrl("/adminLogin/viewAdminLogin")
			.deleteCookies("JSESSIONID")
			.invalidateHttpSession(true);
		}
		/* (non-Javadoc)
		 * @see org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter#configure(org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder)
		 */
		@Override
		public void configure(AuthenticationManagerBuilder auth) throws Exception{
			auth.userDetailsService(adminUserDetailsService)
			.passwordEncoder(new BCryptPasswordEncoder());
		}		
	}
	
	
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
}
