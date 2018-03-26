package com.gzz.createcode.template.data;

import java.util.List;

import com.gzz.createcode.common.Utils;
import com.gzz.createcode.mvc.model.Field;

public class Dao {
	public static StringBuilder create(String pName, String upp, String auth, String cName, List<Field> fList, String tName, String idType) {
		StringBuilder sb = new StringBuilder();
		String idName = fList.get(0).getName().toLowerCase();
		sb.append("package " + pName + ";");
		sb.append("\r\nimport java.util.List;");
		sb.append("\r\n");
		sb.append("\r\nimport org.springframework.stereotype.Repository;");
		sb.append("\r\nimport org.springframework.jdbc.core.BeanPropertyRowMapper;");
		sb.append("\r\n");
		sb.append("\r\nimport com.dl.keep.common.util.SqlUtil;");
		sb.append("\r\nimport com.dl.webdata.common.jdbc.BaseDao;");
		sb.append("\r\nimport com.dl.keep.common.util.Page;");

		sb.append(Utils.classNote(auth, cName + "数据访问层"));
		sb.append("\r\n@Repository");
		sb.append("\r\npublic class " + upp + "Dao extends BaseDao{");
		sb.append("\r\n\tprivate StringBuilder select = new StringBuilder();");
		sb.append("\r\n\tprivate StringBuilder insert = new StringBuilder();");
		sb.append(Utils.methodNote("构造方法,用于拼加SQL及初始化工作"));
		sb.append("\r\n\tpublic " + upp + "Dao () {");
		sb.append("\r\n\t\tselect.append(\"SELECT " + Utils.add(fList, "t.", ",", false, "select") + "\");");
		sb.append("\r\n\t\tselect.append(\" FROM " + tName + " t WHERE 1=1\");");
		sb.append("\r\n");
		sb.append("\r\n\t\tinsert.append(\"INSERT INTO " + tName + " (" + Utils.add(fList, "", ",", true, "insert") + ")\");");
		sb.append("\r\n\t\tinsert.append(\" VALUES (" + Utils.add(fList, ":", ",", true, "insert") + ")\");");
		sb.append("\r\n\t}");
		sb.append(Utils.methodNote("新增" + cName + "记录"));
		sb.append("\r\n\tpublic int save(" + upp + " vo) {");
		sb.append("\r\n\t\tStringBuilder sql = new StringBuilder();");
		sb.append("\r\n\t\tsql.append(\"REPLACE INTO " + tName + " (" + Utils.add(fList, "", ",", false, "sql") + "\"));");
		sb.append("\r\n\t\tsql.append(\" VALUES " + Utils.add(fList) + " \");");
		sb.append("\r\n\t\tObject[] params ={" + Utils.add(fList, "vo.get", "(),", false) + "};");
		sb.append("\r\n\t\t//logger.info(SqlUtil.showSql(sql.toString(), params));//显示SQL语句");
		sb.append("\r\n\t\treturn jdbcTemplate.update(sql.toString(), params);");
		sb.append("\r\n\t}");

		sb.append(Utils.methodNote("新增" + cName + "记录并返回自增涨主键值"));
		sb.append("\r\n\tpublic long saveReturnPK(" + upp + " vo) {");
		sb.append("\r\n\t\treturn saveKey(vo, insert.toString(), \"" + idName + "\");");
		sb.append("\r\n\t}");

		sb.append(Utils.methodNote("批量插入" + cName + "记录"));
		sb.append("\r\n\tpublic int[] insertBatch(List<" + upp + "> list) {");
		sb.append("\r\n\t\treturn batchOperate(list, insert.toString());");
		sb.append("\r\n\t}");

		sb.append(Utils.methodNote("物理删除" + cName + "记录(多条)"));
		sb.append("\r\n	public int delete(" + idType + " ids[]) {");
		sb.append("\r\n		String sql = \"DELETE FROM " + tName + " WHERE " + idName + "\" + SqlUtil.ArrayToIn(ids);");
		sb.append("\r\n		return jdbcTemplate.update(sql);");
		sb.append("\r\n	}");
		sb.append(Utils.methodNote("按ID查找单个" + cName + "实体"));
		sb.append("\r\n	public " + upp + " findById(" + idType + " id) {");
		sb.append("\r\n		StringBuilder sb = new StringBuilder(select);");
		sb.append("\r\n		sb.append(\" AND t." + idName + "=?\");");
		sb.append("\r\n		return jdbcTemplate.queryForObject(sb.toString(), new Object[]{id}, new BeanPropertyRowMapper<>(" + upp + ".class));");
		sb.append("\r\n	}");
		sb.append(Utils.methodNote("更新" + cName + "记录"));
		sb.append("\r\n	public int update(" + upp + " vo) {");
		sb.append("\r\n\t\tStringBuilder sql = new StringBuilder();");
		sb.append("\r\n\t\tsql.append(\"UPDATE " + tName + " SET " + Utils.add(fList, "", "=?,", true, "sql") + "\");");
		sb.append("\r\n\t\tsql.append(\" WHERE " + idName + "=? \");");
		sb.append("\r\n		Object[] params = {" + Utils.add(fList, "vo.get", "(),", true) + ",vo.get" + Utils.firstUpper(idName) + "()};");
		sb.append("\r\n		return jdbcTemplate.update(sql.toString(), params);");
		sb.append("\r\n	}");
		sb.append(Utils.methodNote("按条件查询分页" + cName + "列表"));
		sb.append("\r\n	public Page<" + upp + "> queryPage(" + upp + "Cond cond) {");
		sb.append("\r\n		StringBuilder sb = new StringBuilder(select);");
		sb.append("\r\n		sb.append(cond.getCondition());");
		sb.append("\r\n		//sb.append(cond.getOrderSql());//增加排序子句;");
		sb.append("\r\n\t\t//logger.info(SqlUtil.showSql(sb.toString(),cond.getArray()));//显示SQL语句");
		sb.append("\r\n		return queryPage(sb.toString(), cond, " + upp + ".class);");
		sb.append("\r\n	}");

		sb.append(Utils.methodNote("按条件查询不分页" + cName + "列表"));
		sb.append("\r\n	public List<" + upp + "> queryList(" + upp + "Cond cond) {");
		sb.append("\r\n		StringBuilder sb = new StringBuilder(select);");
		sb.append("\r\n		sb.append(cond.getCondition());");
		sb.append("\r\n		//sb.append(\" ORDER BY operate_time DESC\");");
		
		sb.append("\r\n		return jdbcTemplate.query(sb.toString(), cond.getArray(), new BeanPropertyRowMapper<>(" + upp + ".class));");
		sb.append("\r\n	}");

		sb.append(Utils.methodNote("按条件查询" + cName + "记录个数"));
		sb.append("\r\n	public long queryCount(" + upp + "Cond cond) {");
		sb.append("\r\n		String countSql = \"SELECT COUNT(1) FROM " + tName + " t WHERE 1=1\" + cond.getCondition();");
		sb.append("\r\n		return jdbcTemplate.queryForObject(countSql, cond.getArray(), Long.class);");
		sb.append("\r\n	}");

		sb.append(Utils.methodNote("逻辑删除" + cName + "记录(多条)"));
		sb.append("\r\n	public int deleteLogic(" + idType + " ids[]) {");
		sb.append("\r\n		String sql = \"UPDATE " + tName + " SET delete_remark=1 WHERE " + idName + "\" + SqlUtil.ArrayToIn(ids);");
		sb.append("\r\n		return jdbcTemplate.update(sql);");
		sb.append("\r\n	}");
		sb.append("\r\n}");
		return sb;
	}
}
