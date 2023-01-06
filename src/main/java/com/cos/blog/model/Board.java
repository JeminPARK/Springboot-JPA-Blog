package com.cos.blog.model;

import java.sql.Timestamp;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor

@Entity
public class Board {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)  //auto_increment
	private int id;
	
	@Column(nullable = false, length = 100)
	private String title;
	
	@Lob //대용량데이터
	private String content; //섬머노트 라이브러리 <html>태그가 섞여서 디자인됨.
	
	@ColumnDefault("0")  //DB에서 int값으로 잡기때문에 홑따옴표 필요없음.
	private int count; //조회수
	
	@ManyToOne(fetch = FetchType.EAGER) //Many = Board, User = One 한명의 유저는 여러개의 글을 쓸수있다. 
	@JoinColumn(name = "userId")
	private Users user;   //글작성자   DB는 객체를 저장할수 없다. ForeignKey 자바는 객체를 저장할수 있다.
	
	@OneToMany(mappedBy = "board", fetch = FetchType.EAGER) //mappedBy 연관관계의 주인이 아니다(FK가 아님) DB에 컬럼을 만들지말것. board 를 셀렉트할때 join을 통해서 값을 얻기위한것. reply클래스의 board변수를 매개변수에 넣는다.
	// @JoinColumn(name="replyId")      replyId 를 테이블안에 넣어서 구분할경우 replyId여러개가 한컬럼안에 들어가버리는 사태발생. 제1정규화위배되기때문에 joincolumn쓰지않음.
	private List<Reply> reply;			//하나의 게시글에는 한명의작성자와 여러개의 댓글이 달리기때문에 가변적으로 변할 List가 필요하다.
	
	@CreationTimestamp
	private Timestamp createDate;

}
