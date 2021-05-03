package server;

import java.util.ArrayList;

public class Activity {
	private Integer num;
    private String title;
    private String content;
    private Integer viewVol;
    private String startTime;
    private String endTime;
	private ArrayList img =new ArrayList();

    public Integer getNum() {
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
    public Integer getViewVo() {
        return viewVol;
    }

    public void setViewVo(Integer viewVol) {
        this.viewVol = viewVol;
    }
    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }
    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String EndTime) {
        this.endTime = EndTime;
    }
    public String getImg(int i) {
        return (String) img.get(i);
    }

    @SuppressWarnings("unchecked")
	public void setImg(String img) {
        this.img.add(img);
    }
}
