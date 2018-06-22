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
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import com.example.ec_201804d.service.AdminUserDetailsServiceImpl;
import com.example.ec_201804d.service.UserDetailsServiceImpl;


/**
 * ログイン認証用設定.
 * 
 * @author hibiki.ono
 *
 */
@EnableWebSecurity
public class SecurityConfig {
	
	@Configuration
	@Order(2)
	public static class UserSecurityConfig extends WebSecurityConfigurerAdapter{
		
		@Autowired
		private UserDetailsServiceImpl userDetailsService;
		
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
			http
			    .antMatcher("/user/**")
			    .authorizeRequests()
			    .antMatchers("/user/**").permitAll()
			    .antMatchers("/payment/**").hasRole("USER")
			    .anyRequest()
			    .authenticated();
			
			http.formLogin()
			    .loginPage("/user/login")
			    .loginProcessingUrl("/user/login")
			    .failureUrl("/user/login?error=true")
			    .defaultSuccessUrl("/user/viewItemList", false)
			    .usernameParameter("email")
			    .passwordParameter("password");
			
			http.logout()
			    .logoutRequestMatcher(new AntPathRequestMatcher("/user/logout"))
			    .logoutSuccessUrl("/user/login")
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
	@Order(1)
	public static class AdminUserSecurityConfig extends WebSecurityConfigurerAdapter{
		
		@Autowired
		private AdminUserDetailsServiceImpl adminUserDetailsService;
		
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
			http
			    .antMatcher("/admin**/**")
			    .authorizeRequests()
			    .antMatchers("/admin/login").permitAll()
			    .antMatchers("/admin**/**").hasRole("ADMIN")
			    .anyRequest()
			    .authenticated();
			
			http.formLogin()
			    .loginPage("/admin/login")
			    .loginProcessingUrl("/adminLogin")
			    .failureUrl("/admin/login?error=true")
			    .defaultSuccessUrl("/admin/fromLogintoMenu", false)
			    .usernameParameter("email")
			    .passwordParameter("password");
			
			http.logout()
			    .logoutRequestMatcher(new AntPathRequestMatcher("/adminlogout/**"))
			    .logoutSuccessUrl("/admin/login")
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
