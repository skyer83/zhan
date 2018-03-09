package com.black.zhan.jfinal.ext.generator;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import javax.sql.DataSource;

import com.jfinal.plugin.activerecord.generator.MetaBuilder;
import com.jfinal.plugin.activerecord.generator.TableMeta;

public class MetaBuilderExt extends MetaBuilder {
	
	protected Set<String> geneTablesPrefixes = new TreeSet<String>(String.CASE_INSENSITIVE_ORDER);

	public MetaBuilderExt(DataSource dataSource) {
		super(dataSource);
	}
	
	public void addGeneTablePrefixes(String... geneTablesPrefixes) {
		if (geneTablesPrefixes != null) {
			for (String tablePrefixes : geneTablesPrefixes) {
				this.geneTablesPrefixes.add(tablePrefixes);
			}
		}
	}
	
	@Override
	protected void buildTableNames(List<TableMeta> ret) throws SQLException {
		ResultSet rs = getTablesResultSet();
		while (rs.next()) {
			String tableName = rs.getString("TABLE_NAME");
			
			if (excludedTables.contains(tableName)) {
				System.out.println("Skip table :" + tableName);
				continue ;
			}
			
			if (isSkipTable(tableName)) {
				System.out.println("Skip table :" + tableName);
				continue ;
			}
			
			// junhua_shen_s
			// 有指定生成 model 的前缀的表才生成，否则不生成
			if (null != geneTablesPrefixes && 0 < geneTablesPrefixes.size()) {
				if (this.containsGeneTablesPrefixes(tableName)) {
					TableMeta tableMeta = new TableMeta();
					tableMeta.name = tableName;
					tableMeta.remarks = rs.getString("REMARKS");
					
					tableMeta.modelName = buildModelName(tableName);
					tableMeta.baseModelName = buildBaseModelName(tableMeta.modelName);
					ret.add(tableMeta);
				} else {
					System.out.println("Skip table :" + tableName);
				}
				continue ;
			} else {
				TableMeta tableMeta = new TableMeta();
				tableMeta.name = tableName;
				tableMeta.remarks = rs.getString("REMARKS");
				
				tableMeta.modelName = buildModelName(tableName);
				tableMeta.baseModelName = buildBaseModelName(tableMeta.modelName);
				ret.add(tableMeta);
			}
			// junhua_shen_e
		}
		rs.close();
	}
	
	/**
	 * 是否包含需要生成model的表格的前缀
	 * @param tableName
	 * @return
	 */
	private boolean containsGeneTablesPrefixes(String tableName) {
		String tableNameLow = tableName.toLowerCase();
		for (String tablesPrefixes : geneTablesPrefixes) {
			String tablesPrefixesLow = tablesPrefixes.toLowerCase();
			if (tableNameLow.startsWith(tablesPrefixesLow)) return true;
		}
		return false;
	}
}
