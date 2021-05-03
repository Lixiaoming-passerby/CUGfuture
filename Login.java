package server;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.google.gson.Gson;

public class Login extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/json;charset=UTF-8");
		Connection cn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		String username = request.getParameter("name");
		String password = request.getParameter("password");
		Gson gson = new Gson();
		try {
			Class.forName("com.mysql.jdbc.Driver");
			cn = DriverManager.getConnection("jdbc:mysql://8.140.103.153:3306/cug_future","root","******");
			ps = cn.prepareStatement("select * from USERS where ACCOUNT=? and PASSWD=?");
			ps.setString(1, username);
			ps.setString(2, password);
			rs = ps.executeQuery();
			if(rs.next()) {
				System.out.println("登陆成功！");
				String json = gson.toJson("true");
				response.getWriter().write(json);
			}else {
				System.out.println("账号或密码不正确！");
				String json = gson.toJson("false");
				response.getWriter().write(json);
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
