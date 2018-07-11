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
		

		//全体的なセキュリティの設定を行うメソッド
		@Override
		public void configure(WebSecurity web) {
			web.ignoring().antMatchers("/css/**", "/img/**", "/js/**", "/fonts/**");
		}

		//User側のログイン・ログアウトに関する設定や、権限の設定を行うことが出来るメソッド
		@Override
		protected void configure(HttpSecurity http) throws Exception{
			http
			    .antMatcher("/user**/**") //これから権限の設定をするパスを指定
			    .authorizeRequests() //権限に関する設定
			    .antMatchers("/user/**","/user**/**").permitAll() //「/user/**」に設定されているパスは権限を持っていなくてもアクセス可能
			    .antMatchers("/userPayment/**", "/userorderhistory/**").hasRole("USER") //ここで指定しているパスはユーザ権限がないとアクセス不可
			    .anyRequest()
			    .authenticated(); //上記以外のパスは認証が必要
			
//			http.authorizeRequests()
//				.antMatchers("/usercredit-card/cardpayment","/user/**","/user**/**","/").permitAll();
			
			
			
			http.formLogin() //ログインに関する設定
			    .loginPage("/user/login") //ログイン画面に遷移させるパス. ログイン認証されていない場合もこのパスに遷移する
			    .loginProcessingUrl("/user/login") //ログインボタンを押した際に遷移させるパス(自動的にログインしてくれる)
			    .failureUrl("/user/login?error=true")  //ログインに失敗した際に遷移させるパス
			    .defaultSuccessUrl("/user/viewItemList", false) //ログインに成功した際に遷移させるパス(商品一覧画面)
			    .usernameParameter("email") //ログイン時に使用するメールアドレスのリクエストパラメータ名
			    .passwordParameter("password");  //ログイン時に使用するパスワードのリクエストパラメータ名
			
			http.logout() //ログアウトに関する設定
			    .logoutRequestMatcher(new AntPathRequestMatcher("/user/logout")) //ログアウトボタンを押した際に遷移させるパス
			    .logoutSuccessUrl("/user/login") //ログアウト後に遷移させるパス
			    .deleteCookies("JSESSIONID") //ログアウト後、Cookieに保存されているセッションIDを削除
			    .invalidateHttpSession(true); //true:ログアウト後、セッションを無効にする false:セッションを無効にしない　ログアウトした際に、ブラウザの切断をしているようなもの
			
//			http.csrf()
//			    .csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse());
		}
		
		//認証に関する設定
		//認証ユーザを取得する「UserDetailsService」の設定や
		//パスワード照合時に使う「PasswordEncoder」の設定
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
			    .antMatchers("/adminuserlogin/index").permitAll()
			    .antMatchers("/admin**/**").hasRole("ADMIN")
			    .anyRequest()
			    .authenticated();
			
			http.formLogin()
			    .loginPage("/adminuserlogin/index")
			    .loginProcessingUrl("/adminLogin")
			    .failureUrl("/adminuserlogin/index?error=true")
			    .defaultSuccessUrl("/adminuserlogin/fromLogintoMenu", false)
			    .usernameParameter("email")
			    .passwordParameter("password");
			
			http.logout()
			    .logoutRequestMatcher(new AntPathRequestMatcher("/adminlogout/**"))
			    .logoutSuccessUrl("/adminuserlogin/index")
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
