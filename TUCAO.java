package server;

import java.util.ArrayList;

public class TUCAO {
	private Integer num;
	private String name;
	private String head;
	private String content;
	private Integer likeVol;
    private String tucaoTime;
	private ArrayList img =new ArrayList();
	private ArrayList likeList =new ArrayList();
	private ArrayList storeList =new ArrayList();
	private ArrayList<Comment> commentList = new ArrayList<Comment>();
	private Integer islike = 0;
	private Integer isstore = 0;
	
	public Integer getNum() {
        return num;
    }

    public void setNum(Integer num) {
        this.num = num;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    
    public String getHead() {
        return head;
    }

    public void setHead(String head) {
        this.head = head;
    }


    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
    public Integer getLikeVol() {
        return likeVol;
    }

    public void setlikeVol(Integer likeVol) {
        this.likeVol = likeVol;
    }
    
    public String getTucaoTime() {
        return tucaoTime;
    }

    public void setTucaoTime(String tucaoTime) {
        this.tucaoTime = tucaoTime;
    }
    public String getImg(int i) {
        return (String) img.get(i);
    }

    @SuppressWarnings("unchecked")
	public void setImg(String img) {
        this.img.add(img);
    }
    
    public String getLikeList(int i) {
        return (String) likeList.get(i);
    }
    
    @SuppressWarnings("unchecked")
   	public void setLikeList(String person) {
           this.likeList.add(person);
    }
    
    public String getStoreList(int i) {
        return (String) storeList.get(i);
    }
    
    @SuppressWarnings("unchecked")
   	public void setStoreList(String person) {
           this.storeList.add(person);
    }
    
    
    public Comment getCommentList(int i) {
        return commentList.get(i);
    }
    @SuppressWarnings("unchecked")
	public void setCommentList(Comment comment) {
        this.commentList.add(comment);
    }
    
    
    public Integer getLikeState() {
        return islike;
    }
    public void setLikeState(Integer likestate) {
        this.islike = likestate;
    }
    
    public Integer getStState() {
        return isstore;
    }
    public void setStState(Integer ststate) {
        this.isstore = ststate;
    }
   
}
