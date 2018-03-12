//app.js
App({
  onLaunch: function () {
    // 展示本地存储能力
    var logs = wx.getStorageSync('logs') || []
    logs.unshift(Date.now())
    wx.setStorageSync('logs', logs)

    // 登录
    wx.login({
      success: res => {
        // 发送 res.code 到后台换取 openId, sessionKey, unionId
        this.globalData.loginData.js_code = res.code;
      }
    })
    // 获取用户信息
    wx.getSetting({
      success: res => {
        if (res.authSetting['scope.userInfo']) { // 判断用户是否已经授权
          // 已经授权，可以直接调用 getUserInfo 获取头像昵称，不会弹框
          wx.getUserInfo({
            success: res => {
              // 可以将 res 发送给后台解码出 unionId
              this.globalData.userInfo = res.userInfo

              // 由于 getUserInfo 是网络请求，可能会在 Page.onLoad 之后才返回
              // 所以此处加入 callback 以防止这种情况
              if (this.userInfoReadyCallback) {
                this.userInfoReadyCallback(res)
              }
            }
          })
        }
      }
    })
  },
  globalData: {
    userInfo: null,
    openid: null,
    //baseUrl: 'http://dog-dog-123.com/', // 后台数据交互根URL
    baseUrl: 'http://localhost:8080/zhan/',
    loginData: {
      appid: 'wx8d26f86f263d2d41', // AppID(小程序ID)
      secret: '3b3664681856d54e40d0aa7220d5013a', // AppSecret(小程序密钥)
      js_code: null,
      grant_type: 'authorization_code'
    },
  },
  CONS: {
    SUCCESS_1: '1'
  }
})