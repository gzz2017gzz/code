package com.gzz.createcode.template.model;

import java.util.List;

import com.gzz.createcode.common.CodeUtil;
import com.gzz.createcode.mvc.model.Field;

public class Model {
	public static StringBuilder genSB(String pName, String clsUpp, List<Field> fList, String auth, String cName) {
		StringBuilder sb = new StringBuilder();

		StringBuilder fieldstr = new StringBuilder();
		sb.append("package " + pName + ";");
		sb.append(CodeUtil.dateImport(fList));
		sb.append(CodeUtil.bigImport(fList));
		sb.append("\r\nimport lombok.Getter;");
		sb.append("\r\nimport lombok.Setter;");
		sb.append(CodeUtil.classComment(auth, cName + "实体类"));
		sb.append("\r\n@Setter");
		sb.append("\r\n@Getter");
		sb.append("\r\npublic class " + clsUpp + " {");
		for (Field field : fList) {
			String name = field.getName().toLowerCase();
			String type = field.getType();
			String comments = field.getComment();
			if (type.equals("Date")) {
				fieldstr.append("\r\n\t//@JsonFormat(pattern = \"yyyy-MM-dd HH:mm:ss\", timezone = \"GMT+8\")");
			}
			fieldstr.append("\r\n\tprivate " + type + " " + name + ";// " + comments);

		}
		sb.append("\r\n");
		sb.append("\r\n\t//数据库中的字段");
		sb.append(fieldstr);
		sb.append("\r\n");
		sb.append("\r\n\t//此处可添加查询显示辅助字段");
		sb.append("\r\n");
		sb.append("\r\n}");
		return sb;
	}
}
