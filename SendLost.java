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
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.swing.text.html.HTMLEditorKit.Parser;

import com.google.gson.Gson;

public class SendLost extends HttpServlet{
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
			cn = DriverManager.getConnection("jdbc:mysql://8.140.103.153:3306/cug_future","root","********");
			ps = cn.prepareStatement("select count(*)as totalCount from LOST");
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
		while(i>0&&j<100) {
			try {
				Class.forName("com.mysql.jdbc.Driver");
				cn = DriverManager.getConnection("jdbc:mysql://8.140.103.153:3306/cug_future","root","*******");
				ps = cn.prepareStatement("select * from LOST where NUM="+Integer.toString(i));
				rs = ps.executeQuery();
				if(rs.next()) {
					String title=rs.getString("TITLE");
				    String cont_index=rs.getString("CONT_INDEX");
				    String startTime= new SimpleDateFormat("yyyy-MM-dd").format(rs.getDate("ST_TIME"));
				    String endTime = new SimpleDateFormat("yyyy-MM-dd").format(rs.getDate("ST_TIME"))+45;
				    int state = rs.getInt("STATE");
				    
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
				    
				    
				    Date now = new Date();
					SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
					String d = dateFormat.format(now);
					if(d.compareTo(endTime) == 1)
					{
						i--;
						j++;
						continue;
					}
				    String img=rs.getString("IMG_INDEX");
				    Lost myLost = new Lost();
				    myLost.setTitle(title);
				    myLost.setContent(content);
				    myLost.setNum(i);
				    myLost.setSt_time(startTime);
				    myLost.setState(state);
					String[] imgArray = img.split(";");
			        for (int k = 0; k < imgArray.length; k++) {
			        	myLost.setImg(imgArray[k]);
			        }
			        myActivities.add(myLost);
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
