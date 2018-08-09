package com.gzz.createcode.template.model;

import java.util.List;

import com.gzz.createcode.common.Utils;
import com.gzz.createcode.mvc.model.Field;

public class Model {
	public static StringBuilder create(String pName, String upp, List<Field> fList, String auth, String cName) {
		StringBuilder sb = new StringBuilder();

		StringBuilder field = new StringBuilder();
		sb.append("package " + pName + ";");
		sb.append(Utils.dateImport(fList));
		sb.append(Utils.bigImport(fList));
		sb.append("\r\nimport lombok.Getter;");
		sb.append("\r\nimport lombok.Setter;");
		sb.append("\r\nimport lombok.AllArgsConstructor;");
		sb.append("\r\nimport lombok.Builder;");
		sb.append("\r\nimport lombok.NoArgsConstructor;");
		sb.append("\r\nimport lombok.experimental.Accessors;");
		sb.append(Utils.classNote(auth, cName + "实体类"));
		sb.append("\r\n@Setter");
		sb.append("\r\n@Getter");
		sb.append("\r\n@Accessors(chain = true)");
		sb.append("\r\n@Builder");
		sb.append("\r\n@AllArgsConstructor");
		sb.append("\r\n@NoArgsConstructor");
		sb.append("\r\npublic class " + upp + " {");
		for (Field fi : fList) {
			String type = fi.getType();
			if (type.equals("Date")) {
				field.append("\r\n\t//@JsonFormat(pattern = \"yyyy-MM-dd HH:mm:ss\", timezone = \"GMT+8\")");
			}
			field.append("\r\n\tprivate " + type + " " + fi.getName() + ";// " + fi.getComment());

		}
		sb.append("\r\n");
		sb.append("\r\n\t//数据库中的字段");
		sb.append(field);
		sb.append("\r\n");
		sb.append("\r\n\t//此处可添加查询显示辅助字段");
		sb.append("\r\n");
		sb.append("\r\n}");
		return sb;
	}
}
