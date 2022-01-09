package com.javaex.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.javaex.vo.Guestbookvo;


public class GuestbookDao {

	//필
	//생
	//메
	//메
	public List<Guestbookvo> getList(){
	
		List<Guestbookvo> guestbookList = new ArrayList<Guestbookvo>();
		// 0. import java.sql.*;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
		    // 1. JDBC 드라이버 (Oracle) 로딩
			Class.forName("oracle.jdbc.driver.OracleDriver");

		    // 2. Connection 얻어오기
			String url = "jdbc:oracle:thin:@localhost:1521:xe";
			conn = DriverManager.getConnection(url, "webdb", "webdb");
			System.out.println("로딩완료");
		    // 3. SQL문 준비 / 바인딩 / 실행
		    
			String query ="";
			query += " select  no, ";
			query += "         name, ";
			query += "         password, ";
			query += "         content, ";
			query += "         to_char(reg_date, 'yyyy-mm-dd hh:mi:ss') reg_date ";
			query += " from guestbook ";
			query += " order by reg_date desc ";
			
			//쿼리
			pstmt = conn.prepareStatement(query);
		    
			//바인딩 물음표 없어서 생략
			
			//실행 셀렉트만 익스쿼트 쿼리 나머지는 업데이트
			rs = pstmt.executeQuery();
			// 4.결과처리
			while(rs.next()) {
				int no = rs.getInt("no");
				String name = rs.getString("name");
				String password = rs. getString("password");
				String content = rs.getString("content");
				String regDate = rs.getString("reg_date");
				Guestbookvo guestbookvo = new Guestbookvo(no, name, password, content, regDate);
				guestbookList.add(guestbookvo);
				System.out.println("이건 셀렉트문");
			}
		} catch (ClassNotFoundException e) {
		    System.out.println("error: 드라이버 로딩 실패 - " + e);
		} catch (SQLException e) {
		    System.out.println("error:" + e);
		} finally {
		   
		    // 5. 자원정리
		    try {
		        if (rs != null) {
		            rs.close();
		        }                
		        if (pstmt != null) {
		            pstmt.close();
		        }
		        if (conn != null) {
		            conn.close();
		        }
		    } catch (SQLException e) {
		        System.out.println("error:" + e);
		    }
		
		}

	
		return guestbookList;
		
	}
////////////////////////////////////////////////////////////////////////////////////////////	
	
	public Guestbookvo Insert(Guestbookvo guestvo) {
		
		Guestbookvo guestbookvo = new Guestbookvo();
		// 0. import java.sql.*;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
		    // 1. JDBC 드라이버 (Oracle) 로딩
			Class.forName("oracle.jdbc.driver.OracleDriver");

		    // 2. Connection 얻어오기
			String url = "jdbc:oracle:thin:@localhost:1521:xe";
			conn = DriverManager.getConnection(url, "webdb", "webdb");
			System.out.println("로딩완료");
		    // 3. SQL문 준비 / 바인딩 / 실행
			String query = ""; // 쿼리문 문자열만들기, ? 주의
	         query += " insert into guestbook ";
	         query += " values (seq_guestbook_no.nextval, ?,?,?, sysdate) ";
	         // System.out.println(query);

	         pstmt = conn.prepareStatement(query); // 쿼리로 만들기

	         pstmt.setString(1, guestvo.getName()); // ?(물음표) 중 1번째, 순서중요
	         pstmt.setString(2, guestvo.getPassword() ); // ?(물음표) 중 2번째, 순서중요
	         pstmt.setString(3, guestvo.getContent()); // ?(물음표) 중 3번째, 순서중요
	     

	         int count = pstmt.executeUpdate(); 
			
			//실행 셀렉트만 익스쿼트 쿼리 나머지는 업데이트

			// 4.결과처리

				System.out.println("이건 인서트문");
//				int no = rs.getInt("no");
//				String name = rs.getString("name");
//				String password = rs. getString("password");
//				String content = rs.getString("content");
//				String regDate = rs.getString("reg_date");
//				guestbookvo = new Guestbookvo(no, name, password, content, regDate);
//				
		} catch (ClassNotFoundException e) {
		    System.out.println("error: 드라이버 로딩 실패 - " + e);
		} catch (SQLException e) {
		    System.out.println("error:" + e);
		} finally {
		   
		    // 5. 자원정리
		    try {
		        if (rs != null) {
		            rs.close();
		        }                
		        if (pstmt != null) {
		            pstmt.close();
		        }
		        if (conn != null) {
		            conn.close();
		        }
		    } catch (SQLException e) {
		        System.out.println("error:" + e);
		    }
		
		}

	
		return guestbookvo;
	
	
	
	
	
	
	}
///////////////////////////////////////////////////////////////////
	public int Delete(Guestbookvo vo) {
		
		int count = 0;
		// 0. import java.sql.*;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
		    // 1. JDBC 드라이버 (Oracle) 로딩
			Class.forName("oracle.jdbc.driver.OracleDriver");

		    // 2. Connection 얻어오기
			String url = "jdbc:oracle:thin:@localhost:1521:xe";
			conn = DriverManager.getConnection(url, "webdb", "webdb");
		    // 3. SQL문 준비 / 바인딩 / 실행
			String query = ""; // 쿼리문 문자열만들기, ? 주의
	         query += " delete guestbook ";
	         query += " where no = ? ";
	         query += " and password= ? ";
	         // System.out.println(query);

	         pstmt = conn.prepareStatement(query); // 쿼리로 만들기

	         pstmt.setInt(1, vo.getNo()); // ?(물음표) 중 1번째, 순서중요
	         pstmt.setString(2, vo.getPassword());

	         count = pstmt.executeUpdate(); 
			
			//실행 셀렉트만 익스쿼트 쿼리 나머지는 업데이트

			// 4.결과처리

				
//				int no = rs.getInt("no");
//				String name = rs.getString("name");
//				String password = rs. getString("password");
//				String content = rs.getString("content");
//				String regDate = rs.getString("reg_date");
//				guestbookvo = new Guestbookvo(no, name, password, content, regDate);
//				
		} catch (ClassNotFoundException e) {
		    System.out.println("error: 드라이버 로딩 실패 - " + e);
		} catch (SQLException e) {
		    System.out.println("error:" + e);
		} finally {
		   
		    // 5. 자원정리
		    try {
		        if (rs != null) {
		            rs.close();
		        }                
		        if (pstmt != null) {
		            pstmt.close();
		        }
		        if (conn != null) {
		            conn.close();
		        }
		    } catch (SQLException e) {
		        System.out.println("error:" + e);
		    }
		
		}

	
		return count;
	
	
	}
	/////////////////////////////////////////////////////////////////////////////////////
		
	
}
