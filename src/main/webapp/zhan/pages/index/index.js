//index.js
//获取应用实例
const app = getApp()

Page({
  data: {
    motto: 'Hello World',
    userInfo: {},
    hasUserInfo: false,
    canIUse: wx.canIUse('button.open-type.getUserInfo'),
    openid: null,
    openidCheck: '-1'
  },
  //事件处理函数
  bindViewTap: function() {
    wx.navigateTo({
      url: '../logs/logs'
    })
  },
  onLoad: function () {
    if (app.globalData.userInfo) {
      this.setData({
        userInfo: app.globalData.userInfo,
        hasUserInfo: true
      })
    } else if (this.data.canIUse){
      // 由于 getUserInfo 是网络请求，可能会在 Page.onLoad 之后才返回
      // 所以此处加入 callback 以防止这种情况
      app.userInfoReadyCallback = res => {
        this.setData({
          userInfo: res.userInfo,
          hasUserInfo: true
        })
      }
    } else {
      // 在没有 open-type=getUserInfo 版本的兼容处理
      wx.getUserInfo({
        success: res => {
          app.globalData.userInfo = res.userInfo
          this.setData({
            userInfo: res.userInfo,
            hasUserInfo: true
          })
        }
      })
    }
    this.setOpenid();
  },
  getUserInfo: function(e) {
    app.globalData.userInfo = e.detail.userInfo
    this.setData({
      userInfo: e.detail.userInfo,
      hasUserInfo: true
    })
  },
  setOpenid: function () { // 获取并设置 openid
    var thisPage = this;
    if (!app.globalData.openid) {
      wx.request({
        url: 'https://api.weixin.qq.com/sns/jscode2session',
        data: app.globalData.loginData,
        success: res => {
          /*
          console.log('index.js::onLoad::wx.request::res::openid');
          console.log(res.data.openid); // 用户唯一标识
          console.log('index.js::onLoad::wx.request::res::session_key');
          console.log(res.data.session_key); // 会话密钥
          console.log('index.js::onLoad::wx.request::res::unionid');
          console.log(res.data.unionid); // 用户在开放平台的唯一标识符。本字段在满足一定条件的情况下才返回。具体参看UnionID机制说明
          */
          app.globalData.openid = res.data.openid;
          thisPage.checkOpenid(); // 验证
        },
        fail: res => {
          console.log('index.js::onLoad::wx.fail::res');
          console.log(res);
        }
      })
    }
  },
  checkOpenid: function () { // 验证 openid 是否有在后台注册了
    var thisPage = this;
    if (app.globalData.openid) {
      wx.request({
        url: app.globalData.baseUrl + 'wx/index/index/checkOpenid',
        data: {
          openid: app.globalData.openid
        },
        success: res => {
          thisPage.setData({
            openid: app.globalData.openid,
            openidCheck: res.data.success
          })
        },
        fail: res => {
          wx.showToast({
            title: res.errMsg,
            icon: 'success',
            duration: 2000
          })
        }
      })
    }
  },
  test: function() {
    wx.request({
      url: app.globalData.baseUrl + 'wx/index/index',
      success: res => {
        this.setData({
          motto: res.data.aaa
        })
      },
      fail: res =>  {
        wx.showToast({
          title: res.errMsg,
          icon: 'success',
          duration: 2000
        })
      }
    })
  }
})
