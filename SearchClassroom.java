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
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.openqa.selenium.By;
import org.openqa.selenium.By.ByXPath;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.ElementNotInteractableException;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.NoSuchFrameException;
import org.openqa.selenium.NoSuchWindowException;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.springframework.util.CollectionUtils;

import com.google.gson.Gson;

public class SearchClassroom extends HttpServlet{
	private WebDriver driver;
	private Map<String, Object> vars;
	JavascriptExecutor js;
	public String waitForWindow(int timeout) {
		try {
			Thread.sleep(timeout);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		Set<String> whNow = driver.getWindowHandles();
		Set<String> whThen = (Set<String>) vars.get("window_handles");
		if (whNow.size() > whThen.size()) {
			whNow.removeAll(whThen);
		}
		return whNow.iterator().next();
	}
	public void pageTurning(WebDriver driver,ArrayList classroomlist){
        WebElement content = driver.findElement(By.className("ui-jqgrid-view"));
        parseElement(content,classroomlist);
        try {
            Thread.sleep(400);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        //WebElement pageTools = driver.findElement(By.className("page-tools-bottom"));
        //WebElement paginationBox = pageTools.findElement(By.id("pagination_box"));
        //下一页的按钮
        //WebElement next = paginationBox.findElement(By.className("next"));
        WebElement next = driver.findElement(By.xpath("//*[@id=\"next_pager\"]"));
        //按钮存在并且可点击
        if(null != next && next.isEnabled()){
      	  if(next.getAttribute("class").contains("ui-state-disabled")) return;
            //点击翻页
            next.click();
            System.out.println("点击下一页");
            try {
                //等待页面加载
                Thread.sleep(400);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            pageTurning(driver,classroomlist);
        }
 
    }
 
 
    public void parseElement(WebElement content,ArrayList classroomlist){
        List<WebElement> trs = content.findElements(By.tagName("tr"));
        System.out.println(trs.size());
        //*[@id="B5211793463517E3E0530C0114ACD89E"]/td[4]  需要获取5,8,23
        int times = 0;
        for(WebElement tr:trs){
        	if(times == 0){
       		  	times++;
       		  	continue;
       	  	}
            List<WebElement> tds = tr.findElements(By.tagName("td"));
            Classroom myClassroom = new Classroom();
            myClassroom.setType(tds.get(4).getText());
            myClassroom.setSeatnum(tds.get(7).getText());
            myClassroom.setUsed(tds.get(22).getText());
            classroomlist.add(myClassroom);          
        }
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/json;charset=UTF-8");
		Integer type = Integer.parseInt(request.getParameter("type"));
		Integer week = Integer.parseInt(request.getParameter("weekcount")) ;
		Integer day = Integer.parseInt(request.getParameter("week"));
		Integer begin = Integer.parseInt(request.getParameter("from"));
		Integer end = Integer.parseInt(request.getParameter("end"));
		//System.out.println(keyword);
		ArrayList classroomlist =new ArrayList();
		Gson gson = new Gson();
		driver = new ChromeDriver();
		js = (JavascriptExecutor) driver;
		vars = new HashMap<String, Object>();
		try {
			// 1 | open | / |  | 
		    driver.get("https://www.cug.edu.cn/");
		    // 2 | setWindowSize | 1536x806 |  | 
		    driver.manage().window().setSize(new Dimension(1536, 806));
		    // 3 | click | linkText=淇℃伅闂ㄦ埛 |  | 	
		    driver.findElement(By.linkText("信息门户")).click();
		    // 4 | click | linkText=QQ鐧诲綍 |  | 
		    driver.findElement(By.linkText("QQ登录")).click();
		 // 5 | selectFrame | index=0 |  | 
		    driver.switchTo().frame(0);
		    // 6 | click | id=img_out_984268528 |  | 
		    driver.findElement(By.id("img_out_984268528")).click();
		    // 7 | selectFrame | relative=parent |  | 
		    driver.switchTo().defaultContent();
		    try {
				Thread.sleep(5000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				driver.quit();
				e.printStackTrace();
				
			}
		    // 8 | mouseOver | css=.sys-item:nth-child(3) img |  | 
		    {
		      WebElement element = driver.findElement(By.cssSelector(".sys-item:nth-child(3) img"));
		      Actions builder = new Actions(driver);
		      builder.moveToElement(element).perform();
		    }
		    // 9 | click | css=.sys-item:nth-child(3) img |  | 
		    vars.put("window_handles", driver.getWindowHandles());
		    // 10 | mouseOut | css=.sys-item:nth-child(3) img |  | 
		    driver.findElement(By.cssSelector(".sys-item:nth-child(3) img")).click();
		    // 11 | selectWindow | handle=${win6416} |  | 
		    vars.put("win6416", waitForWindow(2000));
		    // 12 | click | linkText=淇℃伅鏌ヨ |  | 
		    {
		      WebElement element = driver.findElement(By.tagName("body"));
		      Actions builder = new Actions(driver);
		      builder.moveToElement(element, 0, 0).perform();
		    }
		    // 13 | click | linkText=鏌ヨ绌洪棽鏁欏 |  | 
		    driver.switchTo().window(vars.get("win6416").toString());
		    // 14 | selectWindow | handle=${win2300} |  | 
		    driver.findElement(By.linkText("信息查询")).click();
		    vars.put("window_handles", driver.getWindowHandles());
		    driver.findElement(By.linkText("查询空闲教室")).click();
		    vars.put("win2300", waitForWindow(2000));
		    driver.switchTo().window(vars.get("win2300").toString());
		    
		    //设置参数
		    
		    //教学楼选择
		    //*[@id="lh_chosen"]/a
		    driver.findElement(By.xpath("//*[@id=\"lh_chosen\"]/a")).click();
		    if(type == 1) driver.findElement(By.xpath("//*[@id=\"lh_chosen\"]/div/ul/li[4]")).click();
		    else driver.findElement(By.xpath("//*[@id=\"lh_chosen\"]/div/ul/li[3]")).click();
		    //周选择
		    String row = String.valueOf((week-1)/10+1);
		    if(week > 10) week = week -10;
		    String col = String.valueOf(week+1);
		    driver.findElement(By.xpath("//*[@id=\"selectTR_ZC\"]/tr["+row+"]/th["+col+"]")).click();
		    //天选择
		    driver.findElement(By.xpath("//*[@id=\"selectTR_XQJ\"]/tr/th["+String.valueOf(day+1)+"]")).click();
		    //节数选择
		    for(int i = begin;i<=end;i++)
		    {
		    	driver.findElement(By.xpath("//*[@id=\"selectTR_JC\"]/th["+String.valueOf(i)+"]")).click();
		    }
		    
		    //查询
		    driver.findElement(By.xpath("//*[@id=\"search_go\"]")).click();
		    try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				driver.quit();
				e.printStackTrace();
				
			}
		    //得到返回结果
		    pageTurning(driver,classroomlist);
		} catch (NoSuchElementException e) {
			// TODO: handle exception
			driver.quit();
		}catch (ElementNotInteractableException e) {
			// TODO: handle exception
			driver.quit();
		}catch (IndexOutOfBoundsException e) {
			// TODO: handle exception
			driver.quit();
		}catch (IllegalStateException e) {
			// TODO: handle exception
			driver.quit();
		}catch (StaleElementReferenceException e) {
			// TODO: handle exception
			driver.quit();
		}catch (NoSuchWindowException e) {
			// TODO: handle exception
			driver.quit();
		}catch (NoSuchFrameException e) {
			// TODO: handle exception
			driver.quit();
		}
	    driver.quit();
		String json = gson.toJson(classroomlist);
		response.getWriter().write(json);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doGet(request, response);
	}
}
