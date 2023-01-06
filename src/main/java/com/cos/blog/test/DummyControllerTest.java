package com.cos.blog.test;

import java.util.List;
import java.util.function.Supplier;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.cos.blog.model.RoleType;
import com.cos.blog.model.Users;
import com.cos.blog.repository.UserRepository;


//html파일이아니라 data를 리턴해주는 컨트롤러 = RestController
@RestController
public class DummyControllerTest {
	
	@Autowired			//의존성주입
	private UserRepository userRepository; //null상태 autowird => userRepository 타입으로 스프링이 관리하는 객체가있다면 주입시켜줌.

	// http://localhost:8000/blog/dummy/join(요청)
	// http의 body에 username, password, email 데이터를 가지고 요청
	@PostMapping("/dummy/join")
	public String join( Users user ) { //key=value, key=value (약속된규칙)
		
		System.out.println("id: " +user.getId());
		System.out.println("username: "+user.getUsername());
		System.out.println("password: "+user.getPassword());
		System.out.println("email: "+user.getEmail());
		System.out.println("role: "+user.getRole());
		System.out.println("createDate: "+user.getCreateDate());
		
		user.setRole(RoleType.USER);
		userRepository.save(user);			//save함수 insert 용도로 씀.
		
		return "회원가입이 완료되었습니다.";
		
	}
	
//	public String join(String username, String password, String email) { //key=value, key=value (약속된규칙)
//		
//		System.out.println("username: "+username);
//		System.out.println("password: "+password);
//		System.out.println("email"+email);
//		return "회원가입이 완료되었습니다.";
//		
//	}
	
	//{id}주소로 파라미터를 전달받을수 있음.
	// http://localhost:8000/blog/dummy/user/3
	@GetMapping("/dummy/user/{id}")
	public Users detail(@PathVariable int id) {
		
		
		//user/4 을 찾을때 DB에서 못찾으면 user가 null이되는데 반환타입이 null이 되버리면 에러가 생기니 optional로 user객체를 감싸서 가저올것이니 null유무 판단할것.
		//Users user = userRepository.findById(id).get();
		
//     supplier 인터페이스를 이용하여 빈객체로 만드는방법.		
//		Users user = userRepository.findById(id).orElseGet(new Supplier<Users>() {
//			// interface를 new생성하기위해 익명객체를 만듬.
//			
//			@Override
//			public Users get() {
//				// TODO Auto-generated method stub
//				return new Users();
//			}
//		
//		});
		
		//id 조회시 해당 id 없을경우 권장방법 오류메시지출력됨.
		Users user = userRepository.findById(id).orElseThrow(new Supplier<IllegalArgumentException>() {
			
			@Override
			public IllegalArgumentException get() {
				// TODO Auto-generated method stub
				return new IllegalArgumentException("해당 유저는 없습니다. id: "+id);
			}
		});
		
		//람다식
//		Users user = userRepository.findById(id).orElseThrow(()-> {
//			return new IllegalArgumentException("해당사용자는 없습니다.");
//		});
		
		// 요청: 웹브라우저 - java객체를 이해못함.
		// user객체 = java객체
		// 변환 (웹브라우저가 이해하는 데이터) -> json(Gson 라이브러리)
		// 스프링부트 -> messageConverter 자동 작동
		// 자바객체 반환 -> messageConverter 의 jackson 라이브러리 작동 -> json 타입으로 반환
		
		
		return user;
	}
	
	//save()함수 
	// id를 전달하지않으면 insert수행
	// id를 전달했을때 해당 id에 대한 데이터가 존재하면 update수행
	// id를 전달했을때 해당 id 에 대한 데이터 미존재하면 insert수행
	
	@Transactional		//Transactional 어노테이션을 쓰면 save함수를 쓰지않아도 update수행됨. 함수종료시 자동 commit
	@PutMapping("/dummy/user/{id}")
	public Users updateUser(@PathVariable int id, @RequestBody Users requestUser) {// json 데이터를 spring이 java object(message Converter의 Jackson 라이브러리)로 변환해서 받아줌.
		System.out.println("id : "+id);
		System.out.println("password : "+requestUser.getPassword());
		System.out.println("email : "+requestUser.getEmail());
		
		
		Users user = userRepository.findById(id).orElseThrow(()->{
			return new IllegalArgumentException("수정에 실패하였습니다.");
		});
		user.setPassword(requestUser.getPassword());
		user.setEmail(requestUser.getEmail());
		
//		requestUser.setId(id);
//		requestUser.setUsername("ssar"); 하드코딩하여 username을 강제로 바꾸었음.
		
//		userRepository.save(user);
		
		//더티 체킹 :변경을 감지해 쌓아두었다가 한번에 쓰기작업
		
		return user;
	}
	
	// http://localhost:8000/blog/dummy/user
	@GetMapping("/dummy/users")
	public List<Users> list() {
		
		return userRepository.findAll();
	}
	
	//한페이지당 2건의 데이터를 리턴받아 볼 예정
	@GetMapping("/dummy/user")
	public List<Users> pageList(@PageableDefault(size = 2, sort = "id", direction = Sort.Direction.DESC) Pageable pageable){
		Page<Users> pagingUser = userRepository.findAll(pageable);
		
		List<Users> users = pagingUser.getContent();
		
		return users;
		
	}
	
	@DeleteMapping("/dummy/user/{id}")
	public String delete(@PathVariable int id) {
		
		try {
			userRepository.deleteById(id);
		}
		catch(EmptyResultDataAccessException e) {
			return "삭제 에 실패 하였습니다 해당 id는 DB에 없습니다.";
		}
		
		
		return "삭제 되었습니다 id"+id;
		
	}
	
	
}
