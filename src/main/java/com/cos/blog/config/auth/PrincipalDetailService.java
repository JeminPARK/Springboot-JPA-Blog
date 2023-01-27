package com.cos.blog.config.auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.cos.blog.model.Users;
import com.cos.blog.repository.UserRepository;

@Service  //Bean등록
public class PrincipalDetailService implements UserDetailsService{
	
	@Autowired
	private UserRepository userRepository;

	//spring이 로그인 요청을 가로챌 때, username, password 변수 2개를 가로채는데
	//password 부분처리는 알아서함..
	//username이 DB에 있는지만 확인해주면됨..
	
	//이함수에서 username 확인
	
	//로그인이 진행될때 이함수가 자동으로 실행되면서 아래 findByUsername에서 username을 자동적으로 찾게되고 만약 없다면 해당사용자를 찾을수 없다고 에러출력 password틀린건 알아서 처리됨..
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		Users principal = userRepository.findByUsername(username)
								.orElseThrow(()->{
											return new UsernameNotFoundException("해당 사용자를 찾을수 없습니다."+username);
								});
		System.out.println(principal.getUsername()+":"+principal.getPassword());
		
		return new PrincipalDetail(principal);  //시큐리티의 세션에 유저정보가 저장이됨.. 아이디 :user 패스워드:콘솔창
	}
}
