package com.black.zhan.jfinal.common;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import com.black.zhan.common.comm.BlackConfigKey;
import com.black.zhan.jfinal.config.SubJFinalConfig;
import com.black.zhan.jfinal.ext.generator.GeneratorExt;
import com.jfinal.kit.PathKit;
import com.jfinal.kit.PropKit;
import com.jfinal.plugin.druid.DruidPlugin;
import com.jfinal.template.Engine;

/**
 * 本 demo 仅表达最为粗浅的 jfinal 用法，更为有价值的实用的企业级用法
 * 详见 JFinal 俱乐部: http://jfinal.com/club
 * 
 * 在数据库表有任何变动时，运行一下 main 方法，极速响应变化进行代码重构
 */
public class JFinalGenerator {
	
	/** model.base 的包路径 */
	private static final String BASE_MODEL_PACKAGE = "baseModelPackageName";
	/** model.base 要生成JAVA文件的包路径 */
	private static final String BASE_MODEL_OUTPUT = "baseModelOutputDir";
	/** model 的包路径 */
	private static final String MODEL_PACKAGE = "modelPackageName";
	/** model 要生成JAVA文件的包路径 */
	private static final String MODEL_OUTPUT = "modelOutputDir";
	/** 自定义 MappingKit 的类名 */
	private static final String MAPPING_KIT = "mappingKitClassName";
	/** 要生成 model 的表名的前缀 */
	private static final String GENE_TABLE_PREFIX = "geneTablePrefixes";
	
	public static void main(String[] args) {
		List<Map<String, Object>> list = builderGeneModelInfo();
		for (Map<String, Object> map : list) {
			// model 所使用的包名
			String baseModelPackageName = (String) map.get(BASE_MODEL_PACKAGE);
			// model 文件保存路径
			String baseModelOutputDir = (String) map.get(BASE_MODEL_OUTPUT);
			
			// model 所使用的包名 (MappingKit 默认使用的包名)
			String modelPackageName = (String) map.get(MODEL_PACKAGE);
			// model 文件保存路径 (MappingKit 与 DataDictionary 文件默认保存路径)
			String modelOutputDir = (String) map.get(MODEL_OUTPUT);
			
			// 创建生成器
			GeneratorExt generatorExt = new GeneratorExt(getDataSource(), baseModelPackageName, baseModelOutputDir, modelPackageName, modelOutputDir);
			generatorExt.setMappingKitClassName((String) map.get(MAPPING_KIT));
			
			// 设置是否生成链式 setter 方法
			generatorExt.setGenerateChainSetter(false);
			// 添加不需要生成的表名
//			generatorExt.addExcludedTable("pg", "bs");
			// 添加不需要生成的表名前缀
			generatorExt.addGeneTablePrefixes((String[]) map.get(GENE_TABLE_PREFIX));
			// 设置是否在 Model 中生成 dao 对象
			generatorExt.setGenerateDaoInModel(true);
			// 设置是否生成链式 setter 方法
			generatorExt.setGenerateChainSetter(true);
			// 设置是否生成字典文件
			generatorExt.setGenerateDataDictionary(false);
			// 设置需要被移除的表名前缀用于生成modelName。例如表名 "osc_user"，移除前缀 "osc_"后生成的model名为 "User"而非 OscUser
//			generatorExt.setRemovedTableNamePrefixes("t_");
			// 生成
			generatorExt.generate();
			
			Engine.remove("forBaseModel");
			Engine.remove("forModel");
			Engine.remove("forMappingKit");
		}
	}
	
	public static DataSource getDataSource() {
		PropKit.use(BlackConfigKey.CONFIG_FILE);
		DruidPlugin druidPlugin = SubJFinalConfig.createDruidPlugin();
		druidPlugin.start();
		return druidPlugin.getDataSource();
	}

	/**
	 * 构建要生成 model 的信息
	 * @return
	 */
	private static List<Map<String, Object>> builderGeneModelInfo() {
		List<Map<String, Object>> list = new ArrayList<Map<String,Object>>();
		// baseInfo 模块
		Map<String, Object> map = new HashMap<String, Object>();
		map.put(BASE_MODEL_PACKAGE, "com.black.blog.back.baseInfo.model.base");
		map.put(BASE_MODEL_OUTPUT, PathKit.getWebRootPath() + "/src/main/java/com/black/blog/back/baseInfo/model/base");
		map.put(MODEL_PACKAGE, "com.black.blog.back.baseInfo.model");
		map.put(MODEL_OUTPUT, map.get(BASE_MODEL_OUTPUT) + "/..");
		map.put(MAPPING_KIT, "_BaseInfoMappingKit");
		map.put(GENE_TABLE_PREFIX, new String[]{"bi_"});
		list.add(map);
		
		// blogSys 模块
		map = new HashMap<String, Object>();
		map.put(BASE_MODEL_PACKAGE, "com.black.blog.back.blogSys.model.base");
		map.put(BASE_MODEL_OUTPUT, PathKit.getWebRootPath() + "/src/main/java/com/black/blog/back/blogSys/model/base");
		map.put(MODEL_PACKAGE, "com.black.blog.back.blogSys.model");
		map.put(MODEL_OUTPUT, map.get(BASE_MODEL_OUTPUT) + "/..");
		map.put(MAPPING_KIT, "_BlogSysMappingKit");
		map.put(GENE_TABLE_PREFIX, new String[]{"bs_"});
		list.add(map);
		
		return list;
	}
}




