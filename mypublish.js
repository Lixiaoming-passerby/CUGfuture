// pages/mypublish/mypublish.js
Page({

  /**
   * 页面的初始数据
   */
  data: {
    content:[
      {id:0,name:"美咲",pic_url:["/pic/lulu.jpg"],text:"呵呵",head:"/pic/naying.jpg",time:"2021-5-1",up:[],comment:[],is_up:0,is_clo:1},
      {id:1,name:"美咲",pic_url:["/pic/jmq1.jpg","/pic/jmq2.jpg"],text:"男朋友新砖",head:"/pic/jmq3.jpg",time:"2021-5-1",up:["高泽宇"],comment:[],is_up:1,is_clo:1},
      {id:2,name:"美咲",pic_url:[],text:"我好好看",head:"/pic/3.jpg",time:"2021-5-1",up:["童鑫康","冯泽","高泽宇"],comment:[{name:"高泽宇",text:"谢谢哥哥的赞美"},{name:"童鑫康",text:"你好恶心"}],is_up:1,is_clo:1},
    ],
    hiddenmodalput:true,

  },
  modalinput:function(){
		this.setData({
		   hiddenmodalput: !this.data.hiddenmodalput
		})
	},
	//取消按钮
	cancel: function(){
        this.setData({
            hiddenmodalput: true
        });
    },
    //确认
    confirm: function(){
        this.setData({
	        hiddenmodalput: true
	    })
    },
    zan:function(e){
      this.data.content[e.target.id].up.push("高泽宇")
      var temp_up='content['+e.target.id+'].is_up'
      this.setData({
        [temp_up]:1
      })
    
    },
    //取消点赞，更新数据，向数据库传数据
    del_zan:function(e){
      var temp_up='content['+e.target.id+'].is_up'
      var myindex=0
      for (var i = 0; i < this.data.content[e.target.id].up.length; i++) { 
        if (this.data.content[e.target.id].up[i] == e.target.id) {
          myindex==i;
           break; 
          }
        } 
      this.data.content[e.target.id].up.splice(myindex, 1)
      this.setData({
        [temp_up]:0
      })
    },
    //收藏，更新数据，向数据库传数据
    collect:function(e){
      var temp_clo='content['+e.target.id+'].is_clo'
      this.setData({
        [temp_clo]:1
      })
      wx.showToast({
        title: "收藏成功",
        duration: 1500
      })
    },
    //取消收藏，，更新数据，向数据库传数据
    del_collect:function(e){
      var temp_clo='content['+e.target.id+'].is_clo'
      this.setData({
        [temp_clo]:0
      })
      wx.showToast({
        title: "取消收藏成功",
        duration: 1500
      })
    },
    delete_cont:function(e){
      var that=this
      wx.showModal({
        title: '提示',
        content: '确定要删除此动态吗？',
        success: function(res) {
          if (res.confirm) {
          that.data.content.splice(e.target.id, 1)
          wx.showToast({
            title: "删除成功",
            duration: 1500
          })
          } else if (res.cancel) {
          console.log('用户点击取消')
          }
        }
      })

    },
  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function (options) {

  },

  /**
   * 生命周期函数--监听页面初次渲染完成
   */
  onReady: function () {

  },

  /**
   * 生命周期函数--监听页面显示
   */
  onShow: function () {

  },

  /**
   * 生命周期函数--监听页面隐藏
   */
  onHide: function () {

  },

  /**
   * 生命周期函数--监听页面卸载
   */
  onUnload: function () {

  },

  /**
   * 页面相关事件处理函数--监听用户下拉动作
   */
  onPullDownRefresh: function () {

  },

  /**
   * 页面上拉触底事件的处理函数
   */
  onReachBottom: function () {

  },

  /**
   * 用户点击右上角分享
   */
  onShareAppMessage: function () {

  }
})