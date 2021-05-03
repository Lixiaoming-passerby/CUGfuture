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

public class ViewActivity extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/json;charset=UTF-8");
		Connection cn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		String num1 = request.getParameter("num"); 
		String viewVol1 = request.getParameter("viewVol");
		int num=0;int viewVol=0;
		try {
		    num = Integer.parseInt(num1);
		    viewVol=Integer.parseInt(viewVol1);

		} catch (NumberFormatException e) {
		    e.printStackTrace();
		}
		Gson gson = new Gson();
		try {
			Class.forName("com.mysql.jdbc.Driver");
			cn = DriverManager.getConnection("jdbc:mysql://8.140.103.153:3306/cug_future","root","******");
			ps = cn.prepareStatement("select * from ACTIVITIES where NUM=? and VIEW_VOL=?");
			ps.setInt(1, num);
			ps.setInt(2, viewVol);
			rs = ps.executeQuery();
			if(rs.next()) {
				viewVol++;
				ps = cn.prepareStatement("update ACTIVITIES set VIEW_VOL='" + viewVol + "' where NUM='" + num + "'");
				int i = ps.executeUpdate();
			    System.out.println("resutl: " + i);
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
