package server;
import java.util.ArrayList;

public class Lost {
	private Integer num;
    private String title;
    private String content;
    private String st_time;
    private Integer state;
	private ArrayList img =new ArrayList();
	
	public Integer getNum()
	{
		return num;
	}
	public void setNum(Integer num) {
		this.num = num;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getSt_time() {
		return st_time;
	}
	public void setSt_time(String st_time) {
		this.st_time = st_time;
	}
	
	public Integer getState() {
		return state;
	}
	public void setState(Integer state) {
		this.state = state;
	}
	public String getImg(int i) {
        return (String) img.get(i);
    }

    @SuppressWarnings("unchecked")
	public void setImg(String img) {
        this.img.add(img);
    }
}
