package com.carvendy.base.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Mysql {
	
	public static final String URL="jdbc:mysql://127.0.0.1:3306/test";
	public static final String DRIVER="com.mysql.jdbc.Driver";
	public static final String USER="root";
	public static final String PWD="";
	
	
	Connection getConn() throws Exception{
		Connection con=null;
		
		try {
			Class.forName(DRIVER);
			con= DriverManager.getConnection(URL, USER, PWD);
		} catch (ClassNotFoundException e) {
			//e.printStackTrace();
			throw new Exception(e);
		} catch (SQLException e) {
			//	e.printStackTrace();
			throw new Exception(e);
		}
		
		return con;
	}
	
	
	public void insert() throws Exception {
		Connection con = null;
		Statement st=null;
		PreparedStatement pst=null;
		String name="xu";
		int age=123;
		
		con=getConn();
		String sql="insert student (name,age) value('"+name+"',"+age+") ";
		//con.prepareStatement(sql);
		st=con.createStatement();
		/*int num = st.executeUpdate(sql);
		System.out.println("num="+num);*/
		
	}
	
	
	public void insertBatch() throws Exception{
		Connection con = null;
		Statement st=null;
		PreparedStatement pst=null;
		String name="abc";
		int age=123;
		
		con=getConn();
		String sql=null;
		
		/*
		 *  Statement 批量插入
		 */
		st=con.createStatement();
		for(int i=0;i<5;i++){
			sql="insert student (name,age) value('"+(name+i)+"',"+(age+i)+") ";
			st.addBatch(sql);
		}
		
		//st.executeBatch();
		
		/*
		 * prepareStatement-批量插入
		 */
		sql="insert student (name,age) value(?,?) ";
		pst=con.prepareStatement(sql);
		for(int i=100;i-100<5;i++){
			pst.setObject(1, name+i);
			pst.setObject(2, age+i);
			pst.addBatch();
		}
		pst.executeBatch();
		
		close(con, pst);
	}
	
	
	public void select() throws Exception{
		Connection con = null;
		Statement st=null;
		PreparedStatement pst=null;
		ResultSet rs=null;
		
		con=getConn();
		
		st = con.createStatement();
		String sql="select * from student where name like '%abc1%' ";
		rs = st.executeQuery(sql);
		
		while(rs.next()){
			String name = rs.getString("name");
			int age=rs.getInt("age");
			System.out.println(name+"-"+age);
		}
		
		close(con, pst, rs);
	}
	
	private void close(Connection con,Statement st){
		close(con, st, null);
	}
	
	private void close(Connection con,Statement st,ResultSet rs){
		if(rs!=null){
			try {
				rs.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}finally{
				rs=null;
			}
		}
		
		if(st!=null){
			try {
				st.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}finally{
				st=null;
			}
		}
		
		if(con!=null){
			try {
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}finally{
				con=null;
			}
		}
	}
	
	public static void main(String[] args) throws Exception {
		Mysql mysql = new Mysql();
		//mysql.insertBatch();
		
		//mysql.select();
	}
	
}
