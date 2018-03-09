package com.black.zhan.jfinal.config;

import com.black.zhan.common.comm.BlackConfigKey;
import com.black.zhan.jfinal.common.MappingKit;
import com.black.zhan.jfinal.handler.SessionIdHandler;
import com.black.zhan.jfinal.interceptor.GlobalAuthInterceptor;
import com.black.zhan.jfinal.routes.BackRoutes;
import com.black.zhan.jfinal.routes.WxRoutes;
import com.jfinal.config.Constants;
import com.jfinal.config.Handlers;
import com.jfinal.config.Interceptors;
import com.jfinal.config.JFinalConfig;
import com.jfinal.config.Plugins;
import com.jfinal.config.Routes;
import com.jfinal.core.JFinal;
import com.jfinal.kit.PropKit;
import com.jfinal.plugin.activerecord.ActiveRecordPlugin;
import com.jfinal.plugin.druid.DruidPlugin;
import com.jfinal.render.ViewType;
import com.jfinal.template.Engine;

/**
 * 本 demo 仅表达最为粗浅的 jfinal 用法，更为有价值的实用的企业级用法 详见 JFinal 俱乐部:
 * http://jfinal.com/club
 * 
 * API引导式配置
 */
public class SubJFinalConfig extends JFinalConfig {

	/**
	 * 运行此 main 方法可以启动项目，此main方法可以放置在任意的Class类定义中，不一定要放于此
	 * 
	 * 使用本方法启动过第一次以后，会在开发工具的 debug、run config 中自动生成
	 * 一条启动配置，可对该自动生成的配置再添加额外的配置项，例如 VM argument 可配置为： -XX:PermSize=64M
	 * -XX:MaxPermSize=256M
	 */
	public static void main(String[] args) {
		/**
		 * 特别注意：Eclipse 之下建议的启动方式
		 */
		JFinal.start("src/main/webapp", 80, "/", 5);

		/**
		 * 特别注意：IDEA 之下建议的启动方式，仅比 eclipse 之下少了最后一个参数
		 */
		// JFinal.start("src/main/webapp", 80, "/");
	}

	/**
	 * 配置常量
	 */
	public void configConstant(Constants me) {
		me.setError404View("/jsp/exception/404.jsp"); // 404异常页面
		me.setError500View("/jsp/exception/500.jsp"); // 500异常页面

		me.setViewType(ViewType.JSP); // 编译方式为 JSP

		PropKit.use(BlackConfigKey.CONFIG_FILE); // 加载少量必要配置，随后可用PropKit.get(...)获取值
		me.setDevMode(PropKit.getBoolean(BlackConfigKey.DEV_MODE, false));	// 根据配置设置开发模式
	}

	/**
	 * 配置路由
	 */
	public void configRoute(Routes me) {
		/*
		 * CONTROLLER自动注册参考：http://www.jfinal.com/share/140?p=1#reply_start
		 * 但 JFINAL 不建议使用，因此采用手工注册，
		 * 手工注册还有个好处，就是能针对不同的路由添加需要的拦截器
		 */
		me.add(new WxRoutes());	// 微信端路由
		me.add(new BackRoutes());	// 后端路由
		
//		me.add("/", IndexController.class, "/index");	// 第三个参数为该Controller的视图存放路径
//		me.add("/blog", BlogController.class);	// 第三个参数省略时默认与第一个参数值相同，在此即为"/blog"
	}

	public void configEngine(Engine me) {
//		me.addSharedFunction("/common/_layout.html");
//		me.addSharedFunction("/common/_paginate.html");
	}

	/**
	 * 配置插件
	 */
	public void configPlugin(Plugins me) {
//		DruidPlugin druidPlugin = createDruidPlugin(); // 配置 druid 数据库连接池插件
//		me.add(druidPlugin);
//
//		ActiveRecordPlugin arp = new ActiveRecordPlugin(druidPlugin); // 配置ActiveRecord插件
//		MappingKit.mapping(arp); // 所有映射在 MappingKit 中自动化搞定
//		me.add(arp);
	}

	public static DruidPlugin createDruidPlugin() {
		return new DruidPlugin(PropKit.get(BlackConfigKey.JDBC_URL), PropKit.get(BlackConfigKey.USRE), PropKit.get(BlackConfigKey.PASSWORD).trim());
	}

	/**
	 * 配置全局拦截器
	 */
	public void configInterceptor(Interceptors me) {
		me.addGlobalActionInterceptor(new GlobalAuthInterceptor()); // 添加全局拦截器
	}

	/**
	 * 配置处理器
	 */
	public void configHandler(Handlers me) {
		me.add(new SessionIdHandler());
	}
}
