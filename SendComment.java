package server;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

public class SendComment extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/json;charset=UTF-8");
		Connection cn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		int num = Integer.parseInt(request.getParameter("num")); 
		String username = request.getParameter("username");
		String text =request.getParameter("text");
		System.out.println(num);
		System.out.println(username);
		System.out.println(text);
		int result = 0;
		Gson gson = new Gson();
		try {
			Class.forName("com.mysql.jdbc.Driver");
			cn = DriverManager.getConnection("jdbc:mysql://8.140.103.153:3306/cug_future?characterEncoding=UTF-8","root","********");
			ps = cn.prepareStatement("select COMMENT from COMMUNITY where NUM=?");
			ps.setInt(1, num);
			rs = ps.executeQuery();
			if(rs.next()) {
				String commentlist = rs.getString(1);
				commentlist += username+":"+text+"/#/";
				ps = cn.prepareStatement("update COMMUNITY set COMMENT='" + commentlist + "' where NUM = '"+String.valueOf(num)+"'");
				int i = ps.executeUpdate();
			    System.out.println("result: " + i);
			}else {
				System.out.println("找不到相应的记录！");
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
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doGet(request, response);
	}
}
