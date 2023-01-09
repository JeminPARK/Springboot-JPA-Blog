package com.cos.blog.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cos.blog.model.Users;
import com.cos.blog.repository.UserRepository;

// 스프링이 컴포넌트 스캔을 통해서 Bean에 등록을 해줌. IoC를 해준다.
@Service
public class UserService {

	@Autowired
	private UserRepository userRepository;
	
	@Transactional
	public void 회원가입(Users user) {
		
		userRepository.save(user);
		
	}
//	public int 회원가입(Users user) {
//		
//		try {
//			userRepository.save(user);
//			return 1;
//		}
//		catch(Exception e) {
//			e.printStackTrace();
//			System.out.println("UserService : 회원가입() : "+e.getMessage());
//		}
//		return -1;
//	}
	
	@Transactional(readOnly = true)  //Select 할때 트랜잭션 시작, 서비스 종료시에 트랜잭션 종료 (정합성 유지)
	public Users 로그인(Users user) {
		
		return userRepository.findByUsernameAndPassword(user.getUsername(), user.getPassword()); //데이터를 여러번 조회하더라도 같은데이터가 찾아진다. 
		
	}
	
	
}
