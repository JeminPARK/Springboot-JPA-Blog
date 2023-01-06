package com.cos.blog.model;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.hibernate.annotations.CreationTimestamp;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

//ORM -> JAVA(다른언어) Object를 테이블로 맵핑해준다.

@Builder //빌더 패턴
@Data
@NoArgsConstructor
@AllArgsConstructor

@Entity //User클래스가 DB에 테이블이 생성이된다.
//@DynamicInsert	//insert시 null인 필드를 제외시켜준다.
public class Users {

	@Id //Primary Key
	@GeneratedValue(strategy = GenerationType.IDENTITY) //프로젝트에서 연결된 DB의 넘버링 전략을 따라간다. 오라클이면 시퀀스 mysql이면 auto_incre를 따라간다.
	
	private int id;   //oracle : sequence, mysql : auto_increment
	
	@Column(nullable = false, length = 30, unique = true) //unique 중복방지 설정후 application에서 ddl-auto create해서 새로만들어야 적용됨.
	private String username; //id
	
	@Column(nullable = false, length = 100) //123456 => 해쉬 (비밀번호 암호화)할것이라 100자리
	private String password;
	
	@Column(nullable = false, length = 50)
	private String email;
	
	//@ColumnDefault("'user'") //홑따옴표를 써서 문자라는것을 알려줘야함.
	//DB는 RoleType이란게 없기때문에 어노테이션 붙여줘야함
	@Enumerated(EnumType.STRING)
	private RoleType role; //Enum을 쓰는게 좋다. 도메인설정 예: 학년 : 1~6 //admin, user, manager (managerrrr** 실수할수있다.)
	
	@CreationTimestamp //시간이 자동입력
	private Timestamp createDate;
	
	
}
