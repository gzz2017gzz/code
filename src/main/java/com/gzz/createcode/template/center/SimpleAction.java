package com.gzz.createcode.template.center;

import com.gzz.createcode.common.CodeUtil;

public class SimpleAction {
	public static StringBuilder genSB(String pName, String clsUpp, String auth, String cName, String idType, String clsLow) {
		StringBuilder sb = new StringBuilder();

		sb.append("package " + pName + ";");
		sb.append("\r\nimport java.util.List;");
		sb.append("\r\nimport org.apache.commons.logging.Log;");
		sb.append("\r\nimport org.apache.commons.logging.LogFactory;");
		sb.append("\r\nimport org.springframework.beans.factory.annotation.Autowired;");
		sb.append("\r\nimport org.springframework.web.bind.annotation.RequestBody;");
		sb.append("\r\nimport org.springframework.web.bind.annotation.RequestMapping;");
		sb.append("\r\nimport org.springframework.web.bind.annotation.RequestParam;");
		sb.append("\r\nimport org.springframework.web.bind.annotation.RestController;");
		sb.append("\r\nimport java.security.Principal;");
		sb.append("\r\nimport com.dl.webcenter.common.controller.PrincipalAction;");
		sb.append("\r\nimport com.dl.keep.common.util.MessageInfo;");
		sb.append("\r\nimport com.dl.keep.common.util.Page;");

		sb.append(CodeUtil.classComment(auth, cName + "前端控制器层"));
		sb.append("\r\n@RestController");
		sb.append("\r\n@RequestMapping(\"api/" + clsLow + "\")");
		sb.append("\r\npublic class " + clsUpp + "Action extends PrincipalAction {");
		sb.append("\r\n	@SuppressWarnings(\"unused\")");
		sb.append("\r\n	private final Log logger = LogFactory.getLog(getClass());");
		sb.append("\r\n	@Autowired");
		sb.append("\r\n	private " + clsUpp + "Bus bus; //" + cName + "Business层");

		sb.append(CodeUtil.methodComment("新增" + cName + "记录"));
		sb.append("\r\n	@RequestMapping(\"save\")");
		sb.append("\r\n	public int save(@RequestBody " + clsUpp + " " + clsLow + ", Principal principal) {");
		sb.append("\r\n		return bus.save(" + clsLow + ");");
		sb.append("\r\n	}");
		sb.append(CodeUtil.methodComment("验证方法"));
		sb.append("\r\n\t@RequestMapping(\"validate\")");

		sb.append("\r\n\tpublic MessageInfo validate(@RequestBody " + clsUpp + "Cond cond, Principal principal) {");
		sb.append("\r\n\t\tMessageInfo mi = new MessageInfo();");
		sb.append("\r\n\t\t//此处写验证逻辑");
		sb.append("\r\n\t\t//cond.setfield(...)");
		sb.append("\r\n\t\t//int count=bus.queryCount(cond)");
		sb.append("\r\n\t\t//if(count = 0){");
		sb.append("\r\n\t\t\t//mi.setMessage(1, \"记录个数不能为0!\");");
		sb.append("\r\n\t\t//}");
		sb.append("\r\n\t\treturn mi;");
		sb.append("\r\n\t}");

		sb.append(CodeUtil.methodComment("删除" + cName + "记录(多条)"));
		sb.append("\r\n	@RequestMapping(\"delete\")");
		sb.append("\r\n	public int delete(@RequestParam(\"ids[]\") " + idType + " ids[]) {");
		sb.append("\r\n		return bus.delete(ids);");
		sb.append("\r\n	}");

		sb.append(CodeUtil.methodComment("修改" + cName + "记录"));
		sb.append("\r\n	@RequestMapping(\"update\")");
		sb.append("\r\n	public int update(@RequestBody " + clsUpp + " " + clsLow + ", Principal principal) {");
		sb.append("\r\n		return bus.update(" + clsLow + ");");
		sb.append("\r\n	}");
		sb.append(CodeUtil.methodComment("按条件查询分页" + cName + "列表"));
		sb.append("\r\n	@RequestMapping(\"queryPage\")");
		sb.append("\r\n	public Page<" + clsUpp + "> queryPage(@RequestBody " + clsUpp + "Cond cond, Principal principal){");
		sb.append("\r\n		//cond.setBranch_id(getDefaultBranchId(principal));");
		sb.append("\r\n		return bus.queryPage(cond);");
		sb.append("\r\n	}");
		sb.append(CodeUtil.methodComment("按条件查询不分页" + cName + "列表"));
		sb.append("\r\n	@RequestMapping(\"queryList\")");
		sb.append("\r\n	public List<" + clsUpp + "> queryList(@RequestBody " + clsUpp + "Cond cond, Principal principal){");
		sb.append("\r\n		//cond.setBranch_id(getDefaultBranchId(principal));");
		sb.append("\r\n		return bus.queryList(cond);");
		sb.append("\r\n	}");
		sb.append(CodeUtil.methodComment("按条件查询" + cName + "记录个数"));
		sb.append("\r\n	@RequestMapping(\"queryCount\")");
		sb.append("\r\n	public long queryCount(@RequestBody " + clsUpp + "Cond cond){");
		sb.append("\r\n		return bus.queryCount(cond);");
		sb.append("\r\n	}");
		sb.append(CodeUtil.methodComment("按主键查找单个" + cName + "记录"));
		sb.append("\r\n	@RequestMapping(\"findById\")");
		sb.append("\r\n	public " + clsUpp + " findById(@RequestParam(\"id\") " + idType + " id) {");
		sb.append("\r\n		return bus.findById(id);");
		sb.append("\r\n	}");
		sb.append("\r\n}");
		return sb;
	}
}
