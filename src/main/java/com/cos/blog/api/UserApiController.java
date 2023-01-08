package com.cos.blog.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.cos.blog.dto.ResponseDTO;
import com.cos.blog.model.RoleType;
import com.cos.blog.model.Users;
import com.cos.blog.service.UserService;

@RestController
public class UserApiController {
	
	@Autowired
	private UserService userService;

	@PostMapping("/api/user")
	public ResponseDTO<Integer> save(@RequestBody Users user) { //파라미터 username, password, email
		System.out.println("UseApiController : save 호출됨");
		//실제로 DB에 insert를하고 아래에서 return을 처리함.
		
		user.setRole(RoleType.USER);				// user의 역할타입만 set으로 넣어준다. 
		int result =userService.회원가입(user);
		return new ResponseDTO<Integer>(HttpStatus.OK, result);	//자바 오브젝트를 JSON으로 변환해서 리턴 (Jackson)사용
	}
}
