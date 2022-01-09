package com.javaex.dao;

import java.util.List;

import com.javaex.vo.Guestbookvo;

public class Test {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		
		GuestbookDao guestbookDao = new GuestbookDao();
		List<Guestbookvo> gbList = guestbookDao.getList();
		
		System.out.println(gbList.toString());
	
	}

}
