package server;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

public class SendCommunity extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/json;charset=UTF-8");
		Connection cn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		Gson gson = new Gson();
		String username = request.getParameter("username"); 
		ArrayList commList =new ArrayList();
		int i=0;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			cn = DriverManager.getConnection("jdbc:mysql://8.140.103.153:3306/cug_future?characterEncoding=UTF-8","root","********");
			ps = cn.prepareStatement("select * from STORE where NAME=?");
			ps.setString(1, username);
			rs = ps.executeQuery();
			if(!rs.next())
			{
				ps = cn.prepareStatement("insert into STORE values(?,?)");
				ps.setString(1, username);
				ps.setString(2, "");
				int result =ps.executeUpdate();// 返回值代表收到影响的行数
				if(result > 0) {
					System.out.println("创建用户收藏列表成功！");
				}
				else {
					System.out.println("创建用户收藏列表失败！");
				}
			}
			ps = cn.prepareStatement("select * from RELEASED where NAME= ?");
			ps.setString(1, username);
			rs = ps.executeQuery();
			if(!rs.next())
			{
				ps = cn.prepareStatement("insert into RELEASED values(?,?)");
				ps.setString(1, username);
				ps.setString(2, "");
				int result =ps.executeUpdate();// 返回值代表收到影响的行数
				if(result > 0) {
					System.out.println("创建用户发表列表成功！");
				}
				else {
					System.out.println("创建用户发表列表失败！");
				}
			}
			ps = cn.prepareStatement("select count(*)as totalCount from COMMUNITY");
			rs = ps.executeQuery();
			if(rs.next()) {
				i=rs.getInt("totalCount"); 
			}else {
				System.out.println("输入错误！");
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
		while(i>0&&j<200) {
			try {
				Class.forName("com.mysql.jdbc.Driver");
				cn = DriverManager.getConnection("jdbc:mysql://8.140.103.153:3306/cug_future","root","********");
				ps = cn.prepareStatement("select * from COMMUNITY where NUM="+Integer.toString(i));
				rs = ps.executeQuery();
				if(rs.next()) {
					String name=rs.getString("NAME");
					String head=rs.getString("HEAD");
				    String content=rs.getString("CONTENT");
				    Integer likeVol=rs.getInt("LIKE_VOL");
				    String tcTime= new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(rs.getTimestamp("TC_TIME"));
				    String img=rs.getString("IMG_INDEX");
				    String likelist = rs.getString("LIKELIST");
				    String storelist = rs.getString("STORELIST");
				    String commentlist = rs.getString("COMMENT");
				    
				    				    				    
					TUCAO tucao = new TUCAO();
					tucao.setNum(i);
					tucao.setName(name);
					tucao.setHead(head);
					tucao.setContent(content);
					tucao.setTucaoTime(tcTime);
					tucao.setlikeVol(likeVol);
					String[] imgArray ={};
					if(!img.isEmpty())imgArray = img.split(";");
			        for (int k = 0; k < imgArray.length; k++) {
			        	tucao.setImg(imgArray[k]);
			        }
			        String[] nameArray ={};
			        if(!likelist.isEmpty())nameArray = likelist.split(";");
			        for (int k = 0; k < nameArray.length; k++) {
			        	tucao.setLikeList(nameArray[k]);
			        }
			        String[] commentArray = {};
			        if(!commentlist.isEmpty())   //结束标识
			        {
			        	commentArray= commentlist.split("/#/");
			        }
			        for (int k = 0; k < commentArray.length; k++) {
			        	int index = commentArray[k].indexOf(':');
			        	String name1 =commentArray[k].substring(0, index);
			        	String text = commentArray[k].substring(index+1);
			        	Comment comment = new Comment();
			        	comment.setName(name1);
			        	comment.setText(text);
			        	tucao.setCommentList(comment);
			        }
			        if(Arrays.asList(nameArray).contains(username))
			        {
			        	tucao.setLikeState(1);
			        }
			        String[] storeArray = storelist.split(";");
			        if(Arrays.asList(storeArray).contains(username))
			        {
			        	tucao.setStState(1);
			        }
			        commList.add(tucao);
				}else {
					System.out.println("输入错误！");
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
		String json = gson.toJson(commList);
		response.getWriter().write(json);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doGet(request, response);
	}
}
