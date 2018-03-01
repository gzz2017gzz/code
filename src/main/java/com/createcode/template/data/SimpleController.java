package com.createcode.template.data;

import com.createcode.common.CodeUtil;

public class SimpleController {
	public static StringBuilder genSB(String pName, String clsUpp, String auth, String cName,String idType,String clsLow) {
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
		sb.append("\r\nimport com.dl.keep.common.util.Page;");

		sb.append(CodeUtil.classComment(auth, cName + "数据控制器层"));
		sb.append("\r\n@RestController");
		sb.append("\r\n@RequestMapping(\"" + clsLow + "\")");
		sb.append("\r\npublic class " + clsUpp + "Controller {");
		sb.append("\r\n	@SuppressWarnings(\"unused\")");
		sb.append("\r\n	private final Log logger = LogFactory.getLog(getClass());");
		sb.append("\r\n	@Autowired");
		sb.append("\r\n	private " + clsUpp + "Service service; //注入" + cName + "数据逻辑层");

		sb.append(CodeUtil.methodComment("新增" + cName + "记录"));
		sb.append("\r\n	@RequestMapping(\"save\")");
		sb.append("\r\n	public int save(@RequestBody " + clsUpp + " " + clsLow + ") {");
		sb.append("\r\n		return service.save(" + clsLow + ");");
		sb.append("\r\n	}");

		sb.append(CodeUtil.methodComment("删除" + cName + "记录(多条)"));
		sb.append("\r\n	@RequestMapping(\"delete\")");
		sb.append("\r\n	public int delete(@RequestParam(\"ids[]\") " + idType + " ids[]) {");
		sb.append("\r\n		return service.delete(ids);");
		sb.append("\r\n	}");

		sb.append(CodeUtil.methodComment("修改" + cName + "记录"));
		sb.append("\r\n	@RequestMapping(\"update\")");
		sb.append("\r\n	public int update(@RequestBody " + clsUpp + " " + clsLow + ") {");
		sb.append("\r\n		return service.update(" + clsLow + ");");
		sb.append("\r\n	}");
		sb.append(CodeUtil.methodComment("按条件查询分页" + cName + "列表"));
		sb.append("\r\n	@RequestMapping(\"queryPage\")");
		sb.append("\r\n	public Page<" + clsUpp + "> queryPage(@RequestBody " + clsUpp + "Cond cond ){");
		sb.append("\r\n		return service.queryPage(cond);");
		sb.append("\r\n	}");
		sb.append(CodeUtil.methodComment("按条件查询不分页" + cName + "列表"));
		sb.append("\r\n	@RequestMapping(\"queryList\")");
		sb.append("\r\n	public List<" + clsUpp + "> queryList(@RequestBody " + clsUpp + "Cond cond ){");
		sb.append("\r\n		return service.queryList(cond);");
		sb.append("\r\n	}");
		sb.append(CodeUtil.methodComment("按主键查单个" + cName + "记录"));
		sb.append("\r\n	@RequestMapping(\"findById\")");
		sb.append("\r\n	public " + clsUpp + " findById(@RequestParam(\"id\") " + idType + " id) {");
		sb.append("\r\n		return service.findById(id);");
		sb.append("\r\n	}");
		sb.append(CodeUtil.methodComment("按条件查询" + cName + "记录个数"));
		sb.append("\r\n	@RequestMapping(\"queryCount\")");
		sb.append("\r\n	public long queryCount(@RequestBody " + clsUpp + "Cond cond ){");
		sb.append("\r\n		return service.queryCount(cond);");
		sb.append("\r\n	}");
		sb.append("\r\n}");
		return sb;
	}
}
