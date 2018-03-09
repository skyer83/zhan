package com.black.zhan.jfinal.ext.generator;

import java.util.List;

import javax.sql.DataSource;

import com.jfinal.plugin.activerecord.generator.BaseModelGenerator;
import com.jfinal.plugin.activerecord.generator.Generator;
import com.jfinal.plugin.activerecord.generator.ModelGenerator;
import com.jfinal.plugin.activerecord.generator.TableMeta;
import com.jfinal.plugin.activerecord.generator.TypeMapping;

/**
 * 对 Generator 的扩展
 * 1、增加可自定义不生成model的表的前缀
 * @author jhshen
 *
 */
public class GeneratorExt extends Generator {
	
	protected MetaBuilderExt metaBuilder;
	
	/**
	 * 构造 Generator，生成 BaseModel、Model、MappingKit 三类文件，其中 MappingKit 输出目录与包名与 Model相同
	 * @param dataSource 数据源
	 * @param baseModelPackageName base model 包名
	 * @param baseModelOutputDir base mode 输出目录
	 * @param modelPackageName model 包名
	 * @param modelOutputDir model 输出目录
	 */
	public GeneratorExt(DataSource dataSource, String baseModelPackageName, String baseModelOutputDir, String modelPackageName, String modelOutputDir) {
		this(dataSource, new BaseModelGenerator(baseModelPackageName, baseModelOutputDir), new ModelGenerator(modelPackageName, baseModelPackageName, modelOutputDir));
	}
	
	/**
	 * 构造 Generator，只生成 baseModel
	 * @param dataSource 数据源
	 * @param baseModelPackageName base model 包名
	 * @param baseModelOutputDir base mode 输出目录
	 */
	public GeneratorExt(DataSource dataSource, String baseModelPackageName, String baseModelOutputDir) {
		this(dataSource, new BaseModelGenerator(baseModelPackageName, baseModelOutputDir));
	}
	
	public GeneratorExt(DataSource dataSource, BaseModelGenerator baseModelGenerator) {
		super(dataSource, baseModelGenerator);
		
		this.metaBuilder = new MetaBuilderExt(dataSource);
	}
	
	/**
	 * 使用指定 BaseModelGenerator、ModelGenerator 构造 Generator 
	 * 生成 BaseModel、Model、MappingKit 三类文件，其中 MappingKit 输出目录与包名与 Model相同
	 */
	public GeneratorExt(DataSource dataSource, BaseModelGenerator baseModelGenerator, ModelGenerator modelGenerator) {
		super(dataSource, baseModelGenerator, modelGenerator);
		
		this.metaBuilder = new MetaBuilderExt(dataSource);
	}
	
	/**
	 * 设置 MetaBuilder，便于扩展自定义 MetaBuilder
	 */
	public void setMetaBuilder(MetaBuilderExt metaBuilder) {
		if (metaBuilder != null) {
			this.metaBuilder = metaBuilder;
		}
	}

	@Override
	public void setTypeMapping(TypeMapping typeMapping) {
		this.metaBuilder.setTypeMapping(typeMapping);
	}
	
	/**
	 * 设置需要被移除的表名前缀，仅用于生成 modelName 与  baseModelName
	 * 例如表名  "osc_account"，移除前缀 "osc_" 后变为 "account"
	 */
	@Override
	public void setRemovedTableNamePrefixes(String... removedTableNamePrefixes) {
		metaBuilder.setRemovedTableNamePrefixes(removedTableNamePrefixes);
	}
	
	/**
	 * 添加不需要处理的数据表
	 */
	@Override
	public void addExcludedTable(String... excludedTables) {
		metaBuilder.addExcludedTable(excludedTables);
	}
	
	/**
	 * 添加不需要处理的数据表
	 */
	public void addGeneTablePrefixes(String... excludedTablesPrefixes) {
		metaBuilder.addGeneTablePrefixes(excludedTablesPrefixes);
	}
	
	@Override
	public void generate() {
		if (dialect != null) {
			metaBuilder.setDialect(dialect);
		}
		
		long start = System.currentTimeMillis();
		List<TableMeta> tableMetas = metaBuilder.build();
		if (tableMetas.size() == 0) {
			System.out.println("TableMeta 数量为 0，不生成任何文件");
			return ;
		}
		
		baseModelGenerator.generate(tableMetas);
		
		if (modelGenerator != null) {
			modelGenerator.generate(tableMetas);
		}
		
		if (mappingKitGenerator != null) {
			mappingKitGenerator.generate(tableMetas);
		}
		
		if (dataDictionaryGenerator != null && generateDataDictionary) {
			dataDictionaryGenerator.generate(tableMetas);
		}
		
		long usedTime = (System.currentTimeMillis() - start) / 1000;
		System.out.println("Generate complete in " + usedTime + " seconds.");
	}
}
