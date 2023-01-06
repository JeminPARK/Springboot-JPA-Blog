package com.cos.blog.test;

import org.springframework.web.bind.annotation.*;
//import org.springframework.web.bind.annotation.DeleteMapping;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.PutMapping;
//import org.springframework.web.bind.annotation.RestController;

//사용자가 요청 -> 응답(html)
//@Controller

//사용자가 요청 -> 응답(Data)
@RestController                        //String 문자 그자체를 리턴
public class HttpControllerTest {

	private static final String TAG = "HttpControllerTest: ";
	
	@GetMapping("/http/lombok")
	public String lombokTest() {
		
		Member m = new Member(1, "ssar", "1234", "email");
		//builder 어노테이션을 DTO에서 적용시 밑에 정의된대로 생성자를 따로 오버로딩 할 필요없이 원하는 값만 넣을수 있음.
		Member m1 = Member.builder().username("ssar").password("1234").email("ssar@nate.com").build();
		
		System.out.println(TAG+"getter"+m.getId());
		m.setId(5000);
		System.out.println(TAG+"setter"+m.getId());
		
		System.out.println(TAG+"getter"+m1.getUsername());
		m.setUsername("cos");
		System.out.println(TAG+"setter"+m1.getUsername());
		return "lombok test완료";
				
	}
	// 인터넷 브라우저 요청은 무조건 GET요청밖에 할수없다.
	// http://localhost:8000/blog/http/get (select)
	@GetMapping("/http/get")
	public String getTest(Member m) {
		
		return "get요청: "+m.getId()+", "+m.getUsername()+", "+m.getPassword()+", "+m.getEmail();
	
	}
	
	
//	public String getTest(@RequestParam int id, @RequestParam String username) {
//		return "get 요청"+id+", "+username;
//	}
	
	// http://localhost:8080/http/post (insert)
	@PostMapping("/http/post") // raw 종류 : text/plain , application/json
//	public String postTest(@RequestBody String text) {
//		
//		return "post 요청 : "+text;
//	}
	

	// application/json
	public String postTest(@RequestBody Member m) {		 //MessageConverterp;
		
		return "post 요청 : "+m.getId()+", "+m.getUsername()+", "+m.getPassword()+", "+m.getEmail();
	}
	
	//params 방식
//	public String postTest(Member m) {
//		
//		return "post 요청 : "+m.getId()+", "+m.getUsername()+", "+m.getPassword()+", "+m.getEmail();
//	}

	// http://localhost:8080/http/put (update)
	@PutMapping("/http/put")
	public String putTest(@RequestBody Member m) {
		return "put 요청"+m.getId()+", "+m.getUsername()+", "+m.getPassword()+", "+m.getEmail();
	}
	
	// http://localhost:8080/http/delete (delete)
	@DeleteMapping("/http/delete")
	public String deleteTest() {
		return "delete 요청";
	}
}
