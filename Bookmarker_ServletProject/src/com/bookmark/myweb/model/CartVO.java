package com.bookmark.myweb.model;

import java.sql.Date;
import lombok.Data;

@Data
public class CartVO {
	
	private int cartId; //pk
	private int userId; //fk
	private int bookId; //fk
	private Date createAt;

}
