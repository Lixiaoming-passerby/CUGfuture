// pages/lost_detail/lost_detail.js
Page({

  /**
   * 页面的初始数据
   */
  data: {
    id:"",
    res_activity:[],
    state:"",
  },

  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function (options) {
    console.log(options)
    wx.request({
      //url:'https://edu.newsight.cn/wxList.php',
      url:'https://172.20.10.5:443/weixin/Lost',
      data:{},
      success:res=>{
        console.log(res)
        this.setData({
          res_activity:res.data,
          //state:options.currentTarget.id
        })
      }
    })
    this.setData({
      id:options.lost_id
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