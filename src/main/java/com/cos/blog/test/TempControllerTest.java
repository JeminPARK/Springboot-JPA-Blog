package com.cos.blog.test;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller				// 해당경로 이하에 있는 파일을 리턴 String 문자열리턴안함.
public class TempControllerTest {

	// http://localhost:8000/blog/temp/home
	@GetMapping("/temp/home")
	public String tempHome() {
		
		System.out.println("tempHome()");
		
		//파일리턴 기본경로 :src/main/resources/static
		//리턴명 : /home.html
		//종합 경로: src/main/resources/static/home.html
		
		return "/home.html";
	}
	
	//jsp 파일을 static폴더에 넣을시 jsp파일은 동적인파일이라 브라우저가 읽지를 못함.
	@GetMapping("/temp/jsp")
	public String tempJsp() {
		
		//prefix: /WEB-INF/views/
		//suffix: .jsp
		//풀네임: /WEB-INF/views/test.jsp
		
		return "test";
	}
}
