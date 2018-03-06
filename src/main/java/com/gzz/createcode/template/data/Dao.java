package com.gzz.createcode.template.data;

import java.util.List;

import com.gzz.createcode.common.CodeUtil;
import com.gzz.createcode.mvc.model.Field;

public class Dao {
	public static StringBuilder genSB(String pName, String clsUpp, String auth, String cName, List<Field> fList, String tName, String idType) {
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

		sb.append(CodeUtil.classComment(auth, cName + "数据访问层"));
		sb.append("\r\n@Repository");
		sb.append("\r\npublic class " + clsUpp + "Dao extends BaseDao{");
		sb.append("\r\n\tprivate StringBuilder select = new StringBuilder();");
		sb.append("\r\n\tprivate StringBuilder insert = new StringBuilder();");
		sb.append(CodeUtil.methodComment("构造方法,用于拼加SQL及初始化工作"));
		sb.append("\r\n\tpublic " + clsUpp + "Dao () {");
		sb.append("\r\n\t\tselect.append(\"SELECT " + CodeUtil.addLower(fList, "t.", ",").toString() + " FROM " + tName + " t WHERE 1=1\");");
		sb.append("\r\n");
		sb.append("\r\n\t\tinsert.append(\"INSERT INTO " + tName + " (" + CodeUtil.add(fList, "", ",", 2).toString() + ")\");");
		sb.append("\r\n\t\tinsert.append(\" VALUES (" + CodeUtil.add(fList, ":", ",", 2) + ")\");");
		sb.append("\r\n\t}");
		sb.append(CodeUtil.methodComment("新增" + cName + "记录"));
		sb.append("\r\n\tpublic int save(" + clsUpp + " vo) {");
		sb.append("\r\n\t\tString sql = \"REPLACE INTO " + tName + " (" + CodeUtil.addLower(fList, "", ",").toString() + ") VALUES "
				+ CodeUtil.add(fList.size()) + " \";");
		sb.append("\r\n\t\tObject[] params ={");

		sb.append(CodeUtil.add(fList, "vo.get", "(),") + "};");
		sb.append("\r\n\t\t//logger.info(SqlUtil.showSql(sql, params));//显示SQL语句");
		sb.append("\r\n\t\treturn jdbcTemplate.update(sql, params);");
		sb.append("\r\n\t}");

		sb.append(CodeUtil.methodComment("新增" + cName + "记录返回自增涨主键值"));
		sb.append("\r\n\tpublic long saveReturnPK(" + clsUpp + " vo) {");
		sb.append("\r\n\t\treturn saveKey(vo, insert.toString(), \"" + idName + "\");");
		sb.append("\r\n\t}");

		sb.append(CodeUtil.methodComment("批量插入" + cName + "记录"));
		sb.append("\r\n\tpublic int[] insertBatch(List<" + clsUpp + "> list) {");
		sb.append("\r\n\t\treturn batchOperate(list, insert.toString());");
		sb.append("\r\n\t}");

		sb.append(CodeUtil.methodComment("物理删除" + cName + "记录(多条)"));
		sb.append("\r\n	public int delete(" + idType + " ids[]) {");
		sb.append("\r\n		String sql = \"DELETE FROM " + tName + " WHERE " + idName + "\" + SqlUtil.ArrayToIn(ids);//数值型ID使用ArrayToInNum");
		sb.append("\r\n		return jdbcTemplate.update(sql);");
		sb.append("\r\n	}");
		sb.append(CodeUtil.methodComment("按ID查找单个" + cName + "实体"));
		sb.append("\r\n	public " + clsUpp + " findById(" + idType + " id) {");
		sb.append("\r\n		StringBuilder sb = new StringBuilder(select);");
		sb.append("\r\n		sb.append(\" AND t." + idName + "=?\");");
		sb.append("\r\n		return jdbcTemplate.queryForObject(sb.toString(), new Object[]{id}, new BeanPropertyRowMapper<>(" + clsUpp + ".class));");
		sb.append("\r\n	}");
		sb.append(CodeUtil.methodComment("更新" + cName + "记录"));
		sb.append("\r\n	public int update(" + clsUpp + " vo) {");
		sb.append("\r\n\t\tString sql = \"UPDATE " + tName + " SET " + CodeUtil.add(fList, "", "=?,", 2).toString() + " WHERE " + idName + "=? \";");
		sb.append("\r\n		Object[] params = {");
		sb.append(CodeUtil.add(fList, "vo.get", "(),", 1) + ",vo.get" + CodeUtil.firstUpper(idName) + "()};");
		sb.append("\r\n		return jdbcTemplate.update(sql, params);");
		sb.append("\r\n	}");
		sb.append(CodeUtil.methodComment("按条件查询分页" + cName + "列表-根据需要替换成自己的SQL"));
		sb.append("\r\n	public Page<" + clsUpp + "> queryPage(" + clsUpp + "Cond cond) {");
		sb.append("\r\n		StringBuilder sb = new StringBuilder(select);");
		sb.append("\r\n		sb.append(cond.getCondition());");
		sb.append("\r\n		//sb.append(cond.getOrderSql());//增加排序子句;");
		sb.append("\r\n\t\t//logger.info(SqlUtil.showSql(sb.toString(),cond.getArray()));//显示SQL语句");
		sb.append("\r\n		return queryPage(sb.toString(), cond, " + clsUpp + ".class);");
		sb.append("\r\n	}");

		sb.append(CodeUtil.methodComment("按条件查询不分页" + cName + "列表(使用范型)-根据需要替换成自己的SQL"));
		sb.append("\r\n	public List<" + clsUpp + "> queryList(" + clsUpp + "Cond cond) {");
		sb.append("\r\n		StringBuilder sb = new StringBuilder(select);");
		sb.append("\r\n		sb.append(cond.getCondition());");
		sb.append("\r\n		return jdbcTemplate.query(sb.toString(), cond.getArray(), new BeanPropertyRowMapper<>(" + clsUpp + ".class));");
		sb.append("\r\n	}");

		sb.append(CodeUtil.methodComment("按条件查询" + cName + "记录个数"));
		sb.append("\r\n	public long queryCount(" + clsUpp + "Cond cond) {");
		sb.append("\r\n		String countSql = \"SELECT COUNT(1) FROM " + tName + " t WHERE 1=1\" + cond.getCondition();");
		sb.append("\r\n		return jdbcTemplate.queryForObject(countSql, cond.getArray(), Long.class);");
		sb.append("\r\n	}");

		sb.append(CodeUtil.methodComment("逻辑删除" + cName + "记录(多条)"));
		sb.append("\r\n	public int deleteLogic(" + idType + " ids[]) {");
		sb.append("\r\n		String sql = \"UPDATE " + tName + " SET delete_remark=1 WHERE " + idName + "\" + SqlUtil.ArrayToIn(ids);");
		sb.append("\r\n		return jdbcTemplate.update(sql);");
		sb.append("\r\n	}");
		sb.append("\r\n}");
		return sb;
	}
}
