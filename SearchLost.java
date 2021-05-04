package server;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

public class SearchLost extends HttpServlet{
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/json;charset=UTF-8");
		Connection cn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		String keyword = request.getParameter("keyword");
		String state = request.getParameter("state");
		ArrayList mylost =new ArrayList();
		Gson gson = new Gson();
		try {
			Class.forName("com.mysql.jdbc.Driver");
			cn = DriverManager.getConnection("jdbc:mysql://8.140.103.153:3306/cug_future?characterEncoding=UTF-8","root","********");
			ps = cn.prepareStatement("select * from LOST where TITLE like ? and STATE = ?");
			ps.setString(1, "%"+keyword+"%");
			ps.setInt(2, Integer.parseInt(state));
			rs = ps.executeQuery();
			while(rs.next()) {
				Integer num=rs.getInt("NUM");
				String title=rs.getString("TITLE");
			    String cont_index=rs.getString("CONT_INDEX");
			    String st_Time= new SimpleDateFormat("yyyy-MM-dd").format(rs.getTimestamp("ST_TIME"));
			    String endTime = new SimpleDateFormat("yyyy-MM-dd").format(rs.getDate("ST_TIME"))+45;
			    String State = rs.getString("STATE");
			    Date now = new Date();
				SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
				String content = "";
				File file = new File(cont_index);
				InputStreamReader isr = new InputStreamReader(new FileInputStream(file), "UTF-8");
			    BufferedReader reader = null;
			    String tempString = null;			    
			    try {
			    	reader = new BufferedReader(isr);
			        while ((tempString = reader.readLine()) != null) {
			            content+=tempString;
			            content+='\n';
			        }
			        reader.close();
			    } catch (FileNotFoundException e) {
			        // TODO Auto-generated catch block
			        e.printStackTrace();
			    } catch (IOException e) {
			        // TODO Auto-generated catch block
			        e.printStackTrace();
			    }finally{
			        if(reader != null){
			            try {
			                reader.close();
			            } catch (IOException e) {
			                // TODO Auto-generated catch block
			                e.printStackTrace();
			            }
			        }
			    }
				
				
				String d = dateFormat.format(now);
				if(d.compareTo(endTime) == 1)
				{
					continue;
				}
			    String img=rs.getString("IMG_INDEX");
			    Lost lost = new Lost();
			    lost.setTitle(title);
			    lost.setContent(content);
			    lost.setNum(num);
			    lost.setState(Integer.parseInt(State));
			    lost.setSt_time(st_Time);
				String[] imgArray = img.split(";");
		        for (int k = 0; k < imgArray.length; k++) {
		        	lost.setImg(imgArray[k]);
		        }
				lost.setImg(img);
				mylost.add(lost);
			}
			Collections.reverse(mylost);
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
		String json = gson.toJson(mylost);
		response.getWriter().write(json);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doGet(request, response);
	}
}
