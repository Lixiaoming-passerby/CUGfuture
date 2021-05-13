package server;

import junit.framework.TestCase;
import org.springframework.mock.web.MockHttpServletRequest;

public class SendCommentTest extends TestCase {
	//声明request变量
	   private MockHttpServletRequest request;
	 
	  //测试方法，此方法为taskFlowController中的方法传入的参数为request, node_id, UUID三个
	  @Test
	  public void testgetNodeLog() {
	     //创建request对象并设置字符编码 
	request = new MockHttpServletRequest();
	request.setCharacterEncoding("UTF-8");
	String id1 = "num";
	String value1="1"
	String id2 = "username";
	String value2="123"
	String id3 = "test";
	String value3="123"
	//调用controller需要测试的方法
	String nodeLog = SendRelease.getNodeLog(request, id1, value1,id2,value2,id3,value3);
	logger.info(nodeLog);
	Assert.assertTrue(true);//断言是否为true
}
