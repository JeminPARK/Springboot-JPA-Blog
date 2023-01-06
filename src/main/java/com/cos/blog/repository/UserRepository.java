package com.cos.blog.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cos.blog.model.Users;

// DAO 같은 역할
// 자동으로 bean등록이된다.
// @Repository 생략가능
public interface UserRepository extends JpaRepository<Users, Integer> { 
											//해당 JpaRepository는 Users테이블이 관리하는 Repository고 Users테이블의 PK는 Integer이다.

	
}
