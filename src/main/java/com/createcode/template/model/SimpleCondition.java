package com.createcode.template.model;

import java.util.List;

import com.createcode.common.CodeUtil;
import com.createcode.mvc.model.Field;

public class SimpleCondition {
	public static StringBuilder genSB(String pName, String clsUpp, List<Field> fList, String auth, String cName) {
		StringBuilder sb = new StringBuilder();
		sb.append("package " + pName + ";");
		sb.append(CodeUtil.dateImport(fList));
		sb.append(CodeUtil.bigImport(fList));
		sb.append("\r\nimport lombok.Getter;");
		sb.append("\r\nimport lombok.Setter;");
		sb.append("\r\nimport com.codegen.common.base.BaseCondition;");
		sb.append(CodeUtil.classComment(auth, cName + "查询条件实体类"));
		sb.append("\r\n@Setter");
		sb.append("\r\n@Getter");
		sb.append("\r\npublic class " + clsUpp + "Cond extends BaseCondition  {");
		StringBuilder fieldstr = new StringBuilder();
		StringBuilder condition = new StringBuilder();
		for (Field field : fList) {
			String	comments = field.getComment();
			String	fName = field.getName().toLowerCase();
			String	type = field.getType();
			fieldstr.append("\r\n	private " + type + " " + fName + ";// " + comments);
			if (type.equals("String")) {
				condition.append("\r\n		//add(" + fName + ", \"AND t." + fName + " LIKE ?\", 3);");
			} else {
				condition.append("\r\n		//add(" + fName + ", \"AND t." + fName + " = ?\");");
			}
		}
		sb.append(CodeUtil.methodComment("拼加自定义条件;可添加新条件、属性字段,删除不用的条件、属性字段"));
		sb.append("\r\n	@Override");
		sb.append("\r\n	public void addCondition() { ");
		sb.append(condition);
		sb.append("\r\n	}");
		sb.append("\r\n");
		sb.append("\r\n\t//页面查询条件的ID名称、查询条可以自行增减、把不用条件清理掉");
		sb.append(fieldstr);
		sb.append("\r\n");
		sb.append("\r\n}");
		return sb;
	}
}
