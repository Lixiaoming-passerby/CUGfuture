// pages/classroom/classroom.js
Page({

  /**
   * 页面的初始数据
   */
  data: {
    years: ["公教一","公教二"],
    year:"公教二",
    months: [10,11,12,13,14,15,16,17,18],
    month: "11",
    days: ["周一","周二","周三","周四","周五","周六","周日",],
    day: "1",
    begins:[1,3,5,7,9,11],
    begin:1,
    ends:[2,4,6,8,10,12],
    end:2,
    value: [9999, 1, 1],
    out:false,
    mydata:[],
    emptyroom:[],


  },
  bindChange: function(e) {
    const val = e.detail.value
    this.setData({
      year: this.data.years[val[0]],
      month: this.data.months[val[1]],
      day: this.data.days[val[2]],
      begin:this.data.begins[val[3]],
      end:this.data.ends[val[4]],
    })
  },
  choose_class:function(){
   this.setData({
     out:true,
   })

  },
  choose_class2:function(){
    this.setData({
      out:false,
    })
 
   },
   submit:function(){
     this.mydata=[]
    this.data.emptyroom = []
     if(this.data.begin>this.data.end)
     {
      wx.showToast({
        title: "输入错误",
        icon: 'error',
        duration: 1500
      })
     }
     else{
       if(this.data.year=="公教一"){
         this.data.mydata.push(1)
       }
       else{
        this.data.mydata.push(2)
       }
       this.data.mydata.push(this.data.month)
       if(this.data.day=="周一")  this.data.mydata.push(1)
       else if(this.data.day=="周二") this.data.mydata.push(2)
       else if(this.data.day=="周三") this.data.mydata.push(3)
       else if(this.data.day=="周四") this.data.mydata.push(4)
       else if(this.data.day=="周五") this.data.mydata.push(5)
       else if(this.data.day=="周六") this.data.mydata.push(6)
       else this.data.mydata.push(7)
       this.data.mydata.push(this.data.begin)
       this.data.mydata.push(this.data.end)
       wx.request({
        url: "https://172.20.10.5:443/weixin/Classroom?type=" + this.data.mydata[0] + "&" + "weekcount=" + this.data.mydata[1]+ "&" + "week=" + this.data.mydata[2]+ "&" + "from=" + this.data.mydata[3]+ "&" + "end=" + this.data.mydata[4],
        //url地址为后端代码的位置需修改
        data: this.data.mydata,
        success:res=>{
          this.setData({
            emptyroom:res.data
          })
        }
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

  }
})