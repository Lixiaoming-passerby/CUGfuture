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

public class Store extends HttpServlet {
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
			ps = cn.prepareStatement("select STORELIST from COMMUNITY where NUM=?");
			ps.setInt(1, num);
			rs = ps.executeQuery();
			if(rs.next()) {
				String storeArray = rs.getString(1);
				System.out.println(state);
				System.out.println(username);
				if(state == 0)
				{
					try {
						ps = cn.prepareStatement("select ST_INDEX from STORE where NAME=?");
						System.out.println(ps);
						ps.setString(1, username);
						rs = ps.executeQuery();
						if(rs.next()) {
							String stindex = rs.getString(1);
							stindex += String.valueOf(num)+";";
							ps = cn.prepareStatement("update STORE set ST_INDEX = '"+stindex+"'");
							int res = ps.executeUpdate();
						    System.out.println("result: " + res);
						}
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					storeArray += username+";";
					ps = cn.prepareStatement("update COMMUNITY set STORELIST = '"+storeArray+"' where NUM='" + num + "'");
				}
				else if(state == 1)
				{
					try {
						ps = cn.prepareStatement("select ST_INDEX from STORE where NAME=?");
						ps.setString(1, username);
						rs = ps.executeQuery();
						if(rs.next()) {
							String stindex = rs.getString(1);
							if(stindex.contains(String.valueOf(num)))
							{
								int index = stindex.indexOf(String.valueOf(num));
								stindex = stindex.substring(0,index)+stindex.substring(index+String.valueOf(num).length()+1);
							}
							ps = cn.prepareStatement("update STORE set ST_INDEX = '"+stindex+"' where NAME = '"+username+"'");
							int res = ps.executeUpdate();
						    System.out.println("result: " + res);
						}
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					if(storeArray.contains(username))
					{
						int index = storeArray.indexOf(username);
						storeArray = storeArray.substring(0,index)+storeArray.substring(index+username.length()+1);
					}
					ps = cn.prepareStatement("update COMMUNITY set STORELIST = '"+storeArray+"' where NUM='" + num + "'");
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
