package server;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
public class Regist extends HttpServlet {
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/json;charset=UTF-8");
		Connection cn = null;
		PreparedStatement ps = null;
		Gson gson = new Gson();
		String username = request.getParameter("username");
		try {
		if(!cha(username)) {
		String password1 = request.getParameter("password1");
		String password2 = request.getParameter("password2");
		if(password1.equals(password2)) {
		if((!username.equals("")) && (!password1.equals(""))) {
			Class.forName("com.mysql.jdbc.Driver");
			cn = DriverManager.getConnection("jdbc:mysql://8.140.103.153:3306/cug_future","root","*****");
			ps = cn.prepareStatement("insert into USERS values (?,?)");
		}else {
			System.out.println("注册信息不能为空！");
			response.getWriter().write(gson.toJson(1));
		}
			ps.setString(1, username);
			ps.setString(2, password1);
			int num = ps.executeUpdate();
			if(num > 0) {
				System.out.println("注册成功！");
				String json = gson.toJson("true");
				response.getWriter().write(json);
			}else {
				System.out.println("注册失败！");
				String json = gson.toJson("false");
				response.getWriter().write(json);
			}
		}else {
				System.out.println("两次密码不一致，请重新输入！");
				response.getWriter().write(gson.toJson(0));
			}
				}else {
					System.out.println("用户名重复，请重新输入！");
				response.getWriter().write(gson.toJson(-1));
				}
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			try {
				if(ps!=null)
				ps.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
			try {
				if(cn!=null)
				cn.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
	}
	public static Connection method() throws Exception {
		Class.forName("com.mysql.cj.jdbc.Driver");
		Connection cn = DriverManager.getConnection("jdbc:mysql://8.140.103.153:3306/cug_future","root","12345678");
		return cn;
	}
	
	public static boolean cha(String username) throws Exception {
		boolean flag;
		Connection cn = method();
		PreparedStatement ps = cn.prepareStatement("select * from USERS where ACCOUNT = ?");
		ps.setString(1, username);
		ResultSet rs = ps.executeQuery();
		if(rs.next()) {
			flag = true;
		}else {
			flag = false;
		}
		rs.close();
		ps.close();
		return flag;
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doGet(request, response);
	}
}
