package com.didispace.config;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.oauth2.provider.ClientDetailsService;
import org.springframework.security.oauth2.provider.approval.ApprovalStore;
import org.springframework.security.oauth2.provider.approval.TokenApprovalStore;
import org.springframework.security.oauth2.provider.approval.TokenStoreUserApprovalHandler;
import org.springframework.security.oauth2.provider.request.DefaultOAuth2RequestFactory;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.InMemoryTokenStore;

@Configuration
public class OAuth2SecurityConfiguration extends WebSecurityConfigurerAdapter{
	@Autowired
	private ClientDetailsService clientDetailsService;
	@Autowired
	private DataSource dataSource;
//	@Autowired
//	public void globalUserDetails(AuthenticationManagerBuilder auth) throws Exception {
//		auth.jdbcAuthentication().dataSource(dataSource).usersByUsernameQuery(getUserQuery())
//		.authoritiesByUsernameQuery(getAuthoritiesQuery());
//		
//      auth.inMemoryAuthentication()
//      .withUser("tianmd").password("123").roles("ADMIN").and()
//      .withUser("bob").password("abc123").roles("USER");
//    }
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.jdbcAuthentication().dataSource(dataSource).usersByUsernameQuery(getUserQuery())
		.authoritiesByUsernameQuery(getAuthoritiesQuery());
	}
	@Override
    protected void configure(HttpSecurity http) throws Exception {
		http
		.csrf().disable()
        .authorizeRequests()
        .antMatchers("/").permitAll()
        .anyRequest().authenticated()
        .and()
        .formLogin()
        .loginPage("/login")
        .permitAll()
        .and()
        .logout()
        .permitAll();
		
//		http
//		.csrf().disable()//关闭默认的登录界面
//		.anonymous().disable().authorizeRequests()
//        .antMatchers("/").permitAll()
//        .anyRequest().authenticated()
//        .and()
//    .formLogin()
//        .loginPage("/login")
//        .permitAll()
//        .and()
//    .logout()
//        .permitAll();
    }
	  @Override
	  @Bean
	  public AuthenticationManager authenticationManagerBean() throws Exception {
	        return super.authenticationManagerBean();
	    }
	  
	  @Bean
	  public TokenStore tokenStore() {
			return new InMemoryTokenStore();
		}
	  
	  @Bean
		@Autowired
		public TokenStoreUserApprovalHandler userApprovalHandler(TokenStore tokenStore){
			TokenStoreUserApprovalHandler handler = new TokenStoreUserApprovalHandler();
			handler.setTokenStore(tokenStore);
			handler.setRequestFactory(new DefaultOAuth2RequestFactory(clientDetailsService));
			handler.setClientDetailsService(clientDetailsService);
			return handler;
		}
		
		@Bean
		@Autowired
		public ApprovalStore approvalStore(TokenStore tokenStore) throws Exception {
			TokenApprovalStore store = new TokenApprovalStore();
			store.setTokenStore(tokenStore);
			return store;
		}
		
		private String getUserQuery() {
	        return "SELECT OperatorName as username, OperatorCode as password,'true' as enabled "
	                + "FROM HS_OPRightList "
	                + "WHERE OperatorName = ?";
	    }

	    private String getAuthoritiesQuery() {
	        return "SELECT  OperatorName as username,FundAbbr as role "
	                + "FROM HS_OPRightList "
	                + "WHERE OperatorName=? ";
	
	    }
}
