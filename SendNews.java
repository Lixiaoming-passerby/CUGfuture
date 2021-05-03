package server;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.google.gson.Gson;
public class SendNews extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/json;charset=UTF-8");
		Connection cn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		Gson gson = new Gson();
		ArrayList myNews =new ArrayList();
		int i=0;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			cn = DriverManager.getConnection("jdbc:mysql://8.140.103.153:3306/cug_future","root","*******");
			ps = cn.prepareStatement("select count(*)as totalCount from NEWS");
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
				cn = DriverManager.getConnection("jdbc:mysql://8.140.103.153:3306/cug_future","root","******");
				ps = cn.prepareStatement("select * from NEWS where NUM="+Integer.toString(i));
				rs = ps.executeQuery();
				if(rs.next()) {
					String title=rs.getString("TITLE");
				    String content=rs.getString("CONT_INDEX");
				    Integer viewVol=rs.getInt("VIEW_VOL");
				    String newsTime= new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(rs.getTimestamp("NEWS_TIME"));
				    String img=rs.getString("IMG_INDEX");
					News news=new News();
					news.setTitle(title);
					news.setContent(content);
					news.setNum(i);
					news.setNewsTime(newsTime);
					news.setViewVo(viewVol);
					String[] imgArray = img.split(";");
			        for (int k = 0; k < imgArray.length; k++) {
			        	news.setImg(imgArray[k]);
			        }
					news.setImg(img);
					myNews.add(news);
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
		String json = gson.toJson(myNews);
		response.getWriter().write(json);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doGet(request, response);
	}
}

