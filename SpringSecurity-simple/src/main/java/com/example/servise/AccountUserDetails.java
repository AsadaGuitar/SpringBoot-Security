package com.example.servise;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;

import org.springframework.security.core.userdetails.UserDetails;

import com.example.domain.Account;

public class AccountUserDetails implements UserDetails{
	
	private static final long serialVersionUID = 1L;
	private final Account account;
	private final Collection<GrantedAuthority> authorities;
	
	public AccountUserDetails(Account account,
			Collection<GrantedAuthority> authorities) {
		this.account = account;
		this.authorities = authorities;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return authorities;
	}

	@Override
	public String getPassword() {
		return account.getPassword();
	}

	@Override
	public String getUsername() {
		return account.getName();
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}
	
	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return account.isEnabled();
	}
	
	public Account getAccount() {
		return account;
	}
	
	
}