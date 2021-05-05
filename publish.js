// pages/publish/publish.js
var app=getApp()
Page({

  /**
   * 页面的初始数据
   */
  data: {
    img_url: [],
    content:'',
    hideAdd:0,
   
    myimg_url:""

  },
  input:function(e){
    this.setData({
      content:e.detail.value
    })
  },
  chooseimage:function(){
    var that = this;
    wx.chooseImage({
      count: 9, // 默认9  
      sizeType: ['original', 'compressed'], // 可以指定是原图还是压缩图，默认二者都有  
      sourceType: ['album', 'camera'], // 可以指定来源是相册还是相机，默认二者都有  
      success: function (res) {
        if (res.tempFilePaths.length>0){
          //图如果满了9张，不显示加图
          if (res.tempFilePaths.length == 9){
            that.setData({
              hideAdd:1
            })
          }else{
            that.setData({
              hideAdd: 0
            })
          }
          //把每次选择的图push进数组
          let img_url = that.data.img_url;
          for (let i = 0; i < res.tempFilePaths.length; i++) {
            img_url.push(res.tempFilePaths[i])
          }
          that.setData({
            img_url: img_url
          })
          
        }
        
      }
    })  
  },

  send:function(){
   that.get_img()
    for (let i = 0; i < this.data.img_url.length; i++){
      this.data.myimg_url+=this.data.img_url[i]
      this.data.myimg_url+=";"
    }
  },
  //发布按钮事件
  /*send:function(){
    var that = this;
    var user_id = wx.getStorageSync('userid')
    
    wx.showLoading({
      title: '上传中',
    })
    that.get_img2()
    for (let i = 0; i < this.data.img_url.length; i++){
      this.data.myimg_url+=this.data.img_url[i]
      this.data.myimg_url+=";"
    }
    wx.request({
      url: "https://172.20.10.5:443/weixin/Release?username=" + app.globalData.userInfo.nickName + "&" + "head=" + app.globalData.userInfo.avatarUrl
      + "&" + "text=" + this.data.content+ "&" + "pic=" + this.data.myimg_url,
      //url地址为后端代码的位置需修改
      data: '',
      success: this.getResult.bind(this)
    })
  },*/
  getResult: function (res){
    console.log(res.data);
    if(res.data == 1){
    wx.showToast({
      title: "发布成功",
      duration: 2000
    })
    wx.switchTab({
      url: '/pages/community/community',
    })
  }
  if(res.data == 0){
    wx.showToast({
      title: "发布失败",
      icon: 'none',
      duration: 3000
    })
  }
  },
  get_img:function(){
    for (let i = 0; i < this.data.img_url.length; i++) {
    wx.downloadFile({
      url: this.data.img_url[i],
      filePath:wx.env.USER_DATA_PATH + '/pic'+i+'.png',
      success:function (res) {
        console.log(res);
      },
      fail:function (res) {
        console.log(res);
      },
    })
  }
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

  },
 

})