package server;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

public class Release extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/json;charset=UTF-8");
		Connection cn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		Gson gson = new Gson();
		int result = -1;
		String username = request.getParameter("username"); 
		String head = request.getParameter("head"); 
		String text = request.getParameter("text");
		String pic_index = request.getParameter("pic");
		System.out.println(username);
		System.out.println(head);
		System.out.println(text);
		System.out.println(pic_index);
		int i=0;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			cn = DriverManager.getConnection("jdbc:mysql://8.140.103.153:3306/cug_future","root","********");
			ps = cn.prepareStatement("select count(*)as totalCount from COMMUNITY");
			rs = ps.executeQuery();
			if(rs.next()) {
				i=rs.getInt("totalCount"); 
				System.out.println(i);
			}else {
				System.out.println("输入错误！");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			try {
				if(rs!=null)
				rs.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			try {
				if(ps!=null)
				ps.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			try {
				if(cn!=null)
				cn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		Date now = new Date();
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		String d = dateFormat.format(now);
		System.out.println(d);
		try {
			Class.forName("com.mysql.jdbc.Driver");
			cn = DriverManager.getConnection("jdbc:mysql://8.140.103.153:3306/cug_future?characterEncoding=UTF-8","root","********");
			ps = cn.prepareStatement("insert into COMMUNITY values(?,?,?,?,?,?,?,?,?,?)");
			ps.setInt(1, i+1);
			ps.setString(2, username);
			ps.setString(3, head);
			ps.setString(4, text);
			ps.setInt(5, 0);
			ps.setString(6, d);
			ps.setString(7, pic_index);
			ps.setString(8, "");
			ps.setString(9, "");
			ps.setString(10, "");
			result =ps.executeUpdate();// 返回值代表收到影响的行数
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		
		if(result > 0) System.out.println("插入成功");
		try {
			ps = cn.prepareStatement("select RELEINDEX from RELEASED where NAME=?");
			ps.setString(1, username);
			rs = ps.executeQuery();
			if(rs.next()) {
				String releindex = rs.getString(1);
				releindex += String.valueOf(i+1);
				ps = cn.prepareStatement("update RELEASED set RELEINDEX = '"+releindex+";"+"' where NAME ='"+username+"'");
				int res = ps.executeUpdate();
			    System.out.println("result: " + res);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		String json = gson.toJson(result);
		response.getWriter().write(json);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doGet(request, response);
	}
}
