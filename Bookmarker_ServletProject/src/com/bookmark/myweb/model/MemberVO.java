package com.bookmark.myweb.model;

import java.sql.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MemberVO {
	
	private int userId; //pk
	private String pw; 
	private String role; //admin, student, librarian
	private String name; //name
	private String phoneNumber; //phone
	private String address; //address
	private int majorId; //fk
	private Date createAt;
}