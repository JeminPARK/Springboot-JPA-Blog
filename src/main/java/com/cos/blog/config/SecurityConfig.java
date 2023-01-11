package com.cos.blog.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

//Bean등록 : 스프링컨테이너에서 객체를 관리할수 있게 하는것

@Configuration // 빈등록(IoC로 관리)
@EnableWebSecurity //Security 필터 추가 = 스프링 시큐리티가 이미 활성화가 되어있는데 어떠한 설정을 해당 파일에서 하겠다. = 시큐리티 필터에 등록이됨.
@EnableGlobalMethodSecurity(prePostEnabled = true) //특정주소로 접근을 하면 권한및 인증을 미리 체크하겠다는 뜻.
public class SecurityConfig extends WebSecurityConfigurerAdapter{

	@Bean  //IoC 등록됨
	public BCryptPasswordEncoder encodePWD() {
		
		//String encPassword = new BCryptPasswordEncoder().encode("1234");
		
		return new BCryptPasswordEncoder();   // Bean을 등록하여 반환값을 스프링이 관리함
	}
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http
			.csrf().disable()    //csrf 토큰 비활성화 (테스트시 걸어두는게 좋음)
			.authorizeRequests()
			.antMatchers("/","/auth/**", "/js/**", "/css/**", "/image/**")				// 회원가입후 메인페이지 넘어오는게 / 이므로 "/" 인증권한 추가
			.permitAll()
			.anyRequest()
			.authenticated()
		.and()
			.formLogin()								// /로 인증을 시도하면 자동적으로 아래 loginForm으로 이동하라 명령
			.loginPage("/auth/loginForm");
	}
}
