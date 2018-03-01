package com.gzz.createcode.template.app;

import java.util.List;

import com.gzz.createcode.common.CodeUtil;
import com.gzz.createcode.mvc.model.Field;

public class AppModel {
	public static StringBuilder genSB(String pName, String clsUpp, List<Field> fList, String auth, String cName) {
		StringBuilder sb = new StringBuilder();
		StringBuilder field = new StringBuilder();
		StringBuilder setGet = new StringBuilder();
		sb.append("package " + pName + ";");
		sb.append(CodeUtil.dateImport(fList));
		sb.append(CodeUtil.bigImport(fList));
		sb.append(CodeUtil.classComment(auth, cName + "--实体类"));
		sb.append("\r\npublic class " + clsUpp + " {");
		for (Field map : fList) {
			String name = map.getName().toLowerCase();
			String type = map.getType();
			String comments = map.getComment();
			field.append("\r\n\tprivate " + type + " " + name + ";// " + comments);
			setGet.append("\r\n\tpublic " + type + " get" + CodeUtil.firstUpper(name) + "() {");
			setGet.append("\r\n\t\treturn " + name + ";");
			setGet.append("\r\n\t}");
			setGet.append("\r\n\tpublic void set" + CodeUtil.firstUpper(name) + "(" + type + " " + name + ") {");
			setGet.append("\r\n\t\tthis." + name + " = " + name + ";");
			setGet.append("\r\n\t}");
		}
		sb.append("\r\n");
		sb.append("\r\n\t//原始属性");
		sb.append(field);
		sb.append("\r\n");
		sb.append("\r\n\t//新增属性");
		sb.append("\r\n");
		sb.append(setGet);
		sb.append("\r\n}");
		return sb;
	}
}
