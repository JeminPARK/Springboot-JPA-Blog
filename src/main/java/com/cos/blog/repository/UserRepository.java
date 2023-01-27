package com.cos.blog.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cos.blog.model.Users;

// DAO 같은 역할
// 자동으로 bean등록이된다.
// @Repository 생략가능
public interface UserRepository extends JpaRepository<Users, Integer> { 
											//해당 JpaRepository는 Users테이블이 관리하는 Repository고 Users테이블의 PK는 Integer이다.

	//SELECT * FROM users WHERE username = 1?;
	Optional<Users> findByUsername(String username);
	
}


//JPA Naming쿼리 전략
	//SELECT * FROM user WHERE username = ?param1 AND password = ?param2; 와 같은의미
	
	// 전통방식 네이밍쿼리
	//Users findByUsernameAndPassword(String username, String password);
	
	//native쿼리방식
//	@Query(value = "SELECT * FROM user WHERE username = ?1 AND password =?2", nativeQuery = true)
//	Users login(String username, String password);
		