package com.cos.blog.config.auth;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.cos.blog.model.Users;

import lombok.Data;

@Data
public class PrincipalDetail implements UserDetails{

	/**
	 * 
	 */
	private static final long serialVersionUID = -8398143920484947634L;
	private Users user;   //콤포지션 = 객체를 품고 있는것. 
	
	
	public PrincipalDetail(Users user) {
		
		this.user = user;
		//System.out.println(user.getId()+""+user.getPassword());
	}
	
	// 스프링 시큐리티가 로그인 요청을 가로채서 로그인을 진행하고 완료가 되면 UserDetails 타입의 오브젝트를 
	// 스프링 시큐리티의 고유한 세션저장소에 저장을 해준다..
	@Override
	public String getPassword() {
		// TODO Auto-generated method stub
		return user.getPassword();
	}

	@Override
	public String getUsername() {
		
		return user.getUsername();
	}

	//계정이 만료되지 않았는 지 리턴한다.. (true: 만료안됨)
	@Override
	public boolean isAccountNonExpired() {		
		// TODO Auto-generated method stub
		return true;
	}

	//계정이 잠겨있지 않았는지 리턴한다.. (true: 잠기지 않음)
	@Override
	public boolean isAccountNonLocked() {
		// TODO Auto-generated method stub
		return true;
	}

	// 비밀번호가 만료되지 않았는지 리턴한다.. (true : 만료안됨)
	@Override
	public boolean isCredentialsNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	//계정이 활성화(사용가능)인지 리턴한다..( true: 활성화)
	@Override
	public boolean isEnabled() {
		// TODO Auto-generated method stub
		return true;
	}
	
	
	
	//계정이 갖고있는 권한목록을 리턴한다..( 권한이 여러개 있을수 있어서 루프를 돌아야 하는데 우리는 한개만)
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
	
		Collection<GrantedAuthority>  collectors = new ArrayList<>();
		
		//전통적 GrantedAutority 호출방법
//		collectors.add(new GrantedAuthority() {		// 자바에서는 파라미터에 함수를 넣을수 없기때문에 객체생성후 중괄호열어서 오버라이딩한다.
//			
//			@Override
//			public String getAuthority() {
//				
//				return "ROLE_"+user.getRole();		// ROLE_USER 스프링은 앞에 prefix로  ROLE_이렇게 해야 알아듣는다.
//			}
//			
//		});
		
		collectors.add(()-> {return "ROLE_"+user.getRole();});		//GrandtedAuthority 객체안에는 getAuthority()함수 밖에없으니 람다식으로 표현하여 리턴값에 ROLE지정
	
		return collectors;
	}
	
	
}
