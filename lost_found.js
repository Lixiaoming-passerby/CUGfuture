// pages/lost_found/lost_found.js
Page({

  /**
   * 页面的初始数据
   */
  data: {
    flag:0,
    res_activity:[],

  },

  goto_detail:function(e){
    console.log(e)
    wx.navigateTo({
      url:'/pages/lost_detail/lost_detail?lost_id='+e.currentTarget.id
    })

  },

  get_tar:function(e){
    console.log(e)
    this.setData({
      flag:e.target.id
    })
  },
  formSubmit(e) {
    console.log(e)
  wx.request({
    url: "https://172.20.10.5:443/weixin/SearchLost?keyword=" + "%"+e.detail.value["keyword"]+"%"+ "&" + "state=" +this.data.flag,
    //url地址为后端代码的位置需修改
    data: e.detail.value,
    success: this.getResult.bind(this)
  })
},
getResult: function (res){
  this.setData({
    res_activity:res.data
  })
  if(res.data == ""){
    wx.showToast({
      title: "暂无相关信息",
      icon:"error",
      duration: 2000
    })
    }
    this.onShow()
},

  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function (options) {
    wx.request({
    //url:'https://edu.newsight.cn/wxList.php',
      url:'https://172.20.10.5:443/weixin/Lost',
      data:{},
      success:res=>{
        this.setData({
          res_activity:res.data
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