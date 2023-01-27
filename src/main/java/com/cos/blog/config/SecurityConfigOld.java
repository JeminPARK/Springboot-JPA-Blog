//package com.cos.blog.config;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.autoconfigure.condition.ConditionalOnWebApplication;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.security.web.SecurityFilterChain;
//
//import com.cos.blog.config.auth.PrincipalDetailService;
//
//import lombok.extern.slf4j.Slf4j;
//
//@Configuration //빈등록(Ioc관리)
//@EnableWebSecurity// security필터등록
//@EnableGlobalMethodSecurity(prePostEnabled = true) //특정주소를 접근을 하면 권한 및 인증을 미리체크함.
//
//public class SecurityConfigOld {
//
//	@Autowired
//	private PrincipalDetailService principalDetailService;
//	
//	@Bean //IOC로 관리됨.
//	public BCryptPasswordEncoder encodePWD() {
//			return new BCryptPasswordEncoder();
//	}
//	
//	@Bean
//	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
//		http
//		.csrf().disable()
//			.authorizeRequests()
//				.antMatchers("/","/auth/**","/js/**","/css/**","/image/**","/dummy/**")
//				.permitAll()
//				.anyRequest()
//				.authenticated()
//			.and()
//				.formLogin()
//				.loginPage("/auth/loginForm")
//				.loginProcessingUrl("/auth/loginProc")
//				.defaultSuccessUrl("/");
//		
//		return http.build();
//				
//	}
//}
