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

public class Like extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/json;charset=UTF-8");
		Connection cn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		int num = Integer.parseInt(request.getParameter("num")); 
		String username = request.getParameter("username");
		int state =Integer.parseInt(request.getParameter("state"));
		int result = 0;
		Gson gson = new Gson();
		try {
			Class.forName("com.mysql.jdbc.Driver");
			cn = DriverManager.getConnection("jdbc:mysql://8.140.103.153:3306/cug_future?characterEncoding=UTF-8","root","********");
			ps = cn.prepareStatement("select LIKE_VOL,LIKELIST from COMMUNITY where NUM=?");
			ps.setInt(1, num);
			rs = ps.executeQuery();
			if(rs.next()) {
				int likeVol = rs.getInt(1);
				String likeArray = rs.getString(2);
				if(state == 0)
				{
					likeVol++;
					likeArray += username+";";
					ps = cn.prepareStatement("update COMMUNITY set LIKE_VOL='" + likeVol + "',LIKELIST = '"+likeArray+"' where NUM='" + num + "'");
				}
				else if(state == 1)
				{
					likeVol--;
					if(likeArray.contains(username))
					{
						int index = likeArray.indexOf(username);
						likeArray = likeArray.substring(0,index)+likeArray.substring(index+username.length()+1);
					}
					ps = cn.prepareStatement("update COMMUNITY set LIKE_VOL='" + likeVol + "',LIKELIST = '"+likeArray+"' where NUM='" + num + "'");
				}
				else {
					System.out.println("state值出错");
				}
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
