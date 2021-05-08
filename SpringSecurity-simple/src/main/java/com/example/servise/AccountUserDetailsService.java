package com.example.servise;

import java.util.Collection;

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

/**
 * フォームから名前を受け取りUserDetailsを返すサービスクラス
 * 見つからなかった際のレスポンス定義などはここで行う。
 * 
 * @author JavaUser
 *
 */
@Service
public class AccountUserDetailsService implements UserDetailsService{
	
	//DBアクセスクラスを依存注入
	@Autowired
	DaoFactory daoFactory;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

		AccountDao dao = daoFactory.getAccountDao();
		Account account = dao.findOne(username);
		
		if(account == null) 
			throw new UsernameNotFoundException(username + "is not found.");
		return new AccountUserDetails(account, getAuthorities(account));
	}
	
	//ロール情報をリクエストに応じて作成
	private Collection<GrantedAuthority> getAuthorities(Account account){
		
		if(account.isAdmin())
			return AuthorityUtils.createAuthorityList("ROLE_ADMIN","ROLE_USER");
		else
			return AuthorityUtils.createAuthorityList("ROLE_USER");
	}

}
