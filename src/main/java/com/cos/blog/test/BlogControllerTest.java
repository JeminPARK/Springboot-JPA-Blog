package com.cos.blog.test;

import org.springframework.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RestController;

// 스프링이 com.cos.blog 패키지 이하를 스캔해서 모든파일을 메모리에 new하는것은 아니다.
// 특정 어노테이션이 붙어 있는 클래스 파일들을 new해서(IOC) 스프링 컨테이너에서 관리함.

@RestController
public class BlogControllerTest {

	@GetMapping("/test/hello")
	public String hello() {
		
		return "<h1>hello spring boot</h1>";
		
	}
}
