package com.cos.blog.model;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.hibernate.annotations.CreationTimestamp;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class Reply {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)  //auto_increment
	private int id;
	
	@Column(nullable = false, length = 200)//대용량데이터
	private String content;
	
	@ManyToOne  //하나의 게시글에 여러개의 답글이달린다.
	@JoinColumn(name = "boardId")
	private Board board;
	
	@ManyToOne //하나의 유저가 여러개의 답글을 단다
	@JoinColumn(name="userId")
	private Users user;
	
	@CreationTimestamp
	private Timestamp createDate;
}
