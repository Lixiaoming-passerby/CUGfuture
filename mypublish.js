// pages/mypublish/mypublish.js
var app=getApp()
Page({

  /**
   * 页面的初始数据
   */
  data: {
    mycontent:[],
    mycomment:'',
     hiddenmodalput:true,
     comment_id:0,
  },
  modalinput:function(e){
		this.setData({
      comment_id:e.target.id,
		   hiddenmodalput: !this.data.hiddenmodalput
		})
	},
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
  wx.showLoading({
    title: '请稍后',
  })
  wx.request({
    url:'https://172.20.10.5:443/weixin/SendComment?num=' + this.data.mycontent[this.data.comment_id].num + "&" + "username=" + app.globalData.userInfo.nickName
    + "&" + "text=" + this.data.mycomment,
    data:{},
    success:res=>{
      this.onLoad()
      wx.hideLoading()
    }
  })
},
getcomment:function(e){
  this.setData({
    mycomment:e.detail.value
  })
  
  },
  //点赞，更新数据，向数据库传数据
  zan:function(e){
    console.log(e.target.id)
    wx.showLoading({
      title: '请稍后',
    })
    wx.request({
      url:'https://172.20.10.5:443/weixin/Like?num=' + this.data.mycontent[e.target.id].num + "&" + "username=" + app.globalData.userInfo.nickName
      + "&" + "state=" + this.data.mycontent[e.target.id].islike,
      data:{},
      success:res=>{
        this.onLoad()
        wx.hideLoading()
      }
    })
   
  
  },
  //取消点赞，更新数据，向数据库传数据
  del_zan:function(e){
    wx.showLoading({
      title: '请稍后',
    })
    wx.request({
      url:'https://172.20.10.5:443/weixin/Like?num=' + this.data.mycontent[e.target.id].num + "&" + "username=" + app.globalData.userInfo.nickName
      + "&" + "state=" + this.data.mycontent[e.target.id].islike,
      data:{},
      success:res=>{
        this.onLoad()
        wx.hideLoading()
      }
    })
    
  },
  //收藏，更新数据，向数据库传数据
  collect:function(e){
    wx.showLoading({
      title: '请稍后',
    })
    wx.request({
      url:'https://172.20.10.5:443/weixin/Store?num=' + this.data.mycontent[e.target.id].num + "&" + "username=" + app.globalData.userInfo.nickName
      + "&" + "state=" + this.data.mycontent[e.target.id].isstore,
      data:{},
      success:res=>{
        this.onLoad()
        wx.hideLoading()
        wx.showToast({
          title: "收藏成功",
          duration: 1500
        })
      }
    })
   
    
  },
  //取消收藏，，更新数据，向数据库传数据
  del_collect:function(e){
    wx.showLoading({
      title: '请稍后',
    })
    wx.request({
      url:'https://172.20.10.5:443/weixin/Store?num=' + this.data.mycontent[e.target.id].num + "&" + "username=" + app.globalData.userInfo.nickName
      + "&" + "state=" + this.data.mycontent[e.target.id].isstore,
      data:{},
      success:res=>{
        this.onLoad()
        wx.hideLoading()
        wx.showToast({
          title: "取消收藏成功",
          duration: 1500
        })
      }
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
    wx.request({
      url:'https://172.20.10.5:443/weixin/SendRelease?username=' + app.globalData.userInfo.nickName,
      data:{},
      success:res=>{
        this.setData({
          mycontent:res.data
        })
      }
    })

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