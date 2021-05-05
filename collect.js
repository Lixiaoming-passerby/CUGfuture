// pages/collect/collect.js
var app=getApp()
Page({

  /**
   * 页面的初始数据
   */
  data: {
   
    mycontent:[],
    mycomment:'',
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
    confirm: function(e){
      console.log(e)
        this.setData({
	        hiddenmodalput: true
      })
      wx.showLoading({
        title: '请稍后',
      })
      wx.request({
        url:'https://172.20.10.5:443/weixin/SendComment?num=' + this.data.mycontent[e.target.id].num + "&" + "username=" + app.globalData.userInfo.nickName
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
  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function (options) {
    wx.request({
      url:'https://172.20.10.5:443/weixin/SendStore?username=' + app.globalData.userInfo.nickName,
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