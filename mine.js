// pages/mine/mine.js
var app = getApp()
Page({

  /**
   * 页面的初始数据
   */
  data: {
    motto: '欢迎',
    userInfo: {}
  },
gotocollect:function(){
  wx.navigateTo({
    url:"/pages/collect/collect"
  })
},
gotopublish:function(){
  wx.navigateTo({
    url:"/pages/mypublish/mypublish"
  })
},
gotomanager:function(){
  wx.navigateTo({
    url:"/pages/manager/manager"
  })
},
gotoset:function(){
  wx.navigateTo({
    url:"/pages/set/set"
  })
},
gotoservice:function(){
  wx.navigateTo({
    url:"/pages/service/service"
  })
},
  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function (options) {
    
    console.log(wx.getSystemInfoSync())
  
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

  },
})