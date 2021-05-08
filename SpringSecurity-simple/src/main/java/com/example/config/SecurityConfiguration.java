package com.example.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.example.servise.AccountUserDetailsService;

/**
 * 
 *  SecurityFilterChainインターフェイスの実装。
 *  デフォルトではDefaultSecurityFilterChainクラス
 *  
 * @author JavaUser
 */

@EnableWebSecurity
public class SecurityConfiguration {

	@Configuration
	@Order(1)
	public static class UiWebSecurityConfig extends WebSecurityConfigurerAdapter{
		
		@Override
		protected void configure(HttpSecurity http) throws Exception{
			http.antMatcher("/css/**");
		}
	}
	
	@Configuration
	@Order(2)
	public static class ApiWebSecurityConfig extends WebSecurityConfigurerAdapter{
		
//		@Autowired
//		AccountUserDetailsService accountUserDetailsServise;
		
		//デフォルトでは認証成功時のレスポンスは、一度アクセスを拒否したパス
		@Override
		protected void configure(HttpSecurity http) throws Exception {
			
			http.formLogin() //フォーム認証の適用
				.loginPage("/loginForm").permitAll() //"/loginFrom"へのアクセスを許可
				.loginProcessingUrl("/authenticate") //認証パスを指定
				.usernameParameter("username") // HTML の input nameを取得
				.passwordParameter("password") //資格情報のパラメータを指定
				.defaultSuccessUrl("/Success", true) //成功場合のパス
				.failureForwardUrl("/errorForm"); //失敗時のパス
						
			http.logout()
				.logoutSuccessUrl("/loginForm").permitAll();
				
			http.authorizeRequests() //リクエストを制限
				.anyRequest().authenticated(); //全てのURLリクエストを認証されたユーザーにのみ使用可能にする
		}
		
		/**
		 * DaoAuthenticationProviderを有効化する。
		 * PasswordEncoderとUserDetailsServiceがBean登録されている場合は必要無い。
		 * 
		 * @return
		 */
//		@Autowired
//		void configurerAuthenticationManager(AuthenticationManagerBuilder auth) throws Exception{
//			auth.userDetailsService(accountUserDetailsServise).passwordEncoder(passwordEncoder());
//		}
		
		@Bean
		PasswordEncoder passwordEncoder() {
			return new BCryptPasswordEncoder();
		}
		
	}
	
	
	
	
	
	
}
