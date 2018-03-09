layui.define(function(exports) { // 提示：模块也可以依赖其它模块，如：layui.define('layer', // callback);
	"use strict";
	
	var $ = layui.$;
	var common = {
		consoleError : function (e, methodName) {
			if (window.console) {
				window.console.error("===========================");
				window.console.error("exception:");
				window.console.error(e);
				window.console.error("methodName:" + methodName);
				window.console.error("Error Name:" + e.name + ", Error Message:" + e.message);
				window.console.error("===========================");
			}
		},
		getFailMsg : function (json) {
			return json.failMsg;
		}
	};
	
	exports('common', common); // 公共JS方法
});