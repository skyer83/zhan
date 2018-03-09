layui.config({ // config的设置是全局的
	base : BASE_PATH + '/resources/thirdLib/layuiExt/' // 假设这是你存放拓展模块的根目录
}).extend({ // 设定模块别名
	common : 'common' // 如果 mymod.js 是在根目录，也可以不用设定别名
});