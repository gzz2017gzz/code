package com.gzz.createcode.template.app;

import java.util.List;

import com.gzz.createcode.common.Utils;
import com.gzz.createcode.mvc.model.Field;

public class AppCondition {
	public static StringBuilder create(String pName, String clsUpp, List<Field> fList, String auth, String cName) {
		StringBuilder sb = new StringBuilder();
		sb.append("package " + pName + ";");
		sb.append(Utils.dateImport(fList));
		sb.append(Utils.bigImport(fList));
		sb.append("\r\nimport com.dl.keep.common.util.base.BaseCondition;");
		sb.append(Utils.classNote(auth, cName + "--查询条件实体"));
		sb.append("\r\npublic class " + clsUpp + "Cond extends BaseCondition  {");
		StringBuilder field = new StringBuilder();
		StringBuilder setGet = new StringBuilder();
		for (Field fiel : fList) {
			String comments = fiel.getComment();
			String fName = fiel.getName();
			String type = fiel.getType();
			field.append("\r\n	private " + type + " " + fName + ";// " + comments);
			setGet.append("\r\n	public " + type + " get" + Utils.firstUpper(fName) + "() {");
			setGet.append("\r\n		return " + fName + ";");
			setGet.append("\r\n	}");
			setGet.append("\r\n	public void set" + Utils.firstUpper(fName) + "(" + type + " " + fName + ") {");
			setGet.append("\r\n		this." + fName + " = " + fName + ";");
			setGet.append("\r\n	}");
		}
		sb.append("\r\n");
		sb.append("\r\n\t//把不用条件清理掉");
		sb.append(field);
		sb.append("\r\n");
		sb.append(setGet);
		sb.append("\r\n}");
		return sb;
	}
}
