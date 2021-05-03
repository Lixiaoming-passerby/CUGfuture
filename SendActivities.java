package server;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

public class SendActivities extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/json;charset=UTF-8");
		Connection cn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		Gson gson = new Gson();
		ArrayList myActivities =new ArrayList();
		int i=0;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			cn = DriverManager.getConnection("jdbc:mysql://8.140.103.153:3306/cug_future","root","******");
			ps = cn.prepareStatement("select count(*)as totalCount from ACTIVITIES");
			rs = ps.executeQuery();
			if(rs.next()) {
				i=rs.getInt("totalCount"); 
				System.out.println(i);
			}else {
				System.out.println("ÊäÈë´íÎó£¡");
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
		int j=0;
		while(i>0&&j<500) {
			try {
				Class.forName("com.mysql.jdbc.Driver");
				cn = DriverManager.getConnection("jdbc:mysql://8.140.103.153:3306/cug_future","root","12345678");
				ps = cn.prepareStatement("select * from ACTIVITIES where NUM="+Integer.toString(i));
				rs = ps.executeQuery();
				if(rs.next()) {
					String title=rs.getString("TITLE");
				    String content=rs.getString("CONT_INDEX");
				    Integer viewVol=rs.getInt("VIEW_VOL");
				    String startTime= new SimpleDateFormat("yyyy-MM-dd").format(rs.getDate("ST_TIME"));
				    String endTime= new SimpleDateFormat("yyyy-MM-dd").format(rs.getDate("END_TIME"));
				    String img=rs.getString("IMG_INDEX");
					Activity myActivity =new Activity();
					myActivity.setTitle(title);
					myActivity.setContent(content);
					myActivity.setNum(i);
					myActivity.setStartTime(startTime);
					myActivity.setEndTime(endTime);
					myActivity.setViewVo(viewVol);
					String[] imgArray = img.split(";");
			        for (int k = 0; k < imgArray.length; k++) {
			        	myActivity.setImg(imgArray[k]);
			        }
			        myActivities.add(myActivity);
				}else {
					System.out.println("ÊäÈë´íÎó£¡");
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
			i--;
			j++;
		}
		String json = gson.toJson(myActivities);
		response.getWriter().write(json);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doGet(request, response);
	}
}


