package com.example.servise;

import java.util.Collection;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.dao.AccountDao;
import com.example.dao.DaoFactory;
import com.example.domain.Account;

@Service
public class AccountUserDetailsService implements UserDetailsService{
	
	@Autowired
	DaoFactory daoFactory;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

		AccountDao dao = daoFactory.getAccountDao();
		Account account = Optional.ofNullable(dao.findOne(username))
				.orElseThrow(() -> new UsernameNotFoundException("user not found."));
		return new AccountUserDetails(account, getAuthorities(account));
	}
	
	private Collection<GrantedAuthority> getAuthorities(Account account){
		
		if(account.isAdmin())
			return AuthorityUtils.createAuthorityList("ROLE_ADMIN","ROLE_USER");
		else
			return AuthorityUtils.createAuthorityList("ROLE_USER");
	}

}
