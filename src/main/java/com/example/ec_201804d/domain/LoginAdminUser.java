package com.example.ec_201804d.domain;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;

/**
 * 管理者のログイン情報を格納するドメイン.
 * 
 * @author hibiki.ono
 *
 */
public class LoginAdminUser extends org.springframework.security.core.userdetails.User{
	
	private static final long serialVersionUID = 1L;
	private final AdminUser adminUser;
	
	public LoginAdminUser(AdminUser adminUser, Collection<GrantedAuthority> authorityList) {
		super(adminUser.getEmail(), adminUser.getPassword(), authorityList);
		this.adminUser = adminUser;
	}
	
	@Override
	public String toString() {
		return "LoginAdminUser [adminUser=" + adminUser + "]";
	}

	public AdminUser getAdminUser() {
		return adminUser;
	}
}
