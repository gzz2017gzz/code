package com.gzz.createcode.template.data;

import com.gzz.createcode.common.Utils;

public class Controller {
	public static StringBuilder create(String pName, String upp, String auth, String cName,String idType, String lowUpp) {
		StringBuilder sb = new StringBuilder();
		sb.append("package " + pName + ";");
		sb.append("\r\nimport java.util.List;");
		sb.append("\r\nimport org.apache.commons.logging.Log;");
		sb.append("\r\nimport org.apache.commons.logging.LogFactory;");
		sb.append("\r\nimport org.springframework.beans.factory.annotation.Autowired;");
		sb.append("\r\nimport org.springframework.web.bind.annotation.RequestBody;");
		sb.append("\r\nimport org.springframework.web.bind.annotation.PostMapping;");
		sb.append("\r\nimport org.springframework.web.bind.annotation.RequestParam;");
		sb.append("\r\nimport org.springframework.web.bind.annotation.RestController;");
		sb.append("\r\nimport com.dl.keep.common.util.Page;");

		sb.append(Utils.classNote(auth, cName + "数据控制器层"));
		sb.append("\r\n@RestController");
		sb.append("\r\n@RequestMapping(\"" + lowUpp + "\")");
		sb.append("\r\npublic class " + upp + "Controller {");
		sb.append("\r\n	@SuppressWarnings(\"unused\")");
		sb.append("\r\n	private final Log logger = LogFactory.getLog(getClass());");
		sb.append("\r\n	@Autowired");
		sb.append("\r\n	private " + upp + "Service service; //注入" + cName + "数据逻辑层");

		sb.append(Utils.methodNote("新增" + cName + "记录"));
		sb.append("\r\n	@PostMapping(\"save\")");
		sb.append("\r\n	public int save(@RequestBody " + upp + " " + lowUpp + ") {");
		sb.append("\r\n		return service.save(" + lowUpp + ");");
		sb.append("\r\n	}");

		sb.append(Utils.methodNote("删除" + cName + "记录(多条)"));
		sb.append("\r\n	@PostMapping(\"delete\")");
		sb.append("\r\n	public int delete(@RequestParam(\"ids[]\") " + idType + " ids[]) {");
		sb.append("\r\n		return service.delete(ids);");
		sb.append("\r\n	}");

		sb.append(Utils.methodNote("修改" + cName + "记录"));
		sb.append("\r\n	@PostMapping(\"update\")");
		sb.append("\r\n	public int update(@RequestBody " + upp + " " + lowUpp + ") {");
		sb.append("\r\n		return service.update(" + lowUpp + ");");
		sb.append("\r\n	}");
		sb.append(Utils.methodNote("按条件查询分页" + cName + "列表"));
		sb.append("\r\n	@PostMapping(\"queryPage\")");
		sb.append("\r\n	public Page<" + upp + "> queryPage(@RequestBody " + upp + "Cond cond ){");
		sb.append("\r\n		return service.queryPage(cond);");
		sb.append("\r\n	}");
		sb.append(Utils.methodNote("按条件查询不分页" + cName + "列表"));
		sb.append("\r\n	@PostMapping(\"queryList\")");
		sb.append("\r\n	public List<" + upp + "> queryList(@RequestBody " + upp + "Cond cond ){");
		sb.append("\r\n		return service.queryList(cond);");
		sb.append("\r\n	}");
		sb.append(Utils.methodNote("按主键查单个" + cName + "记录"));
		sb.append("\r\n	@PostMapping(\"findById\")");
		sb.append("\r\n	public " + upp + " findById(@RequestParam(\"id\") " + idType + " id) {");
		sb.append("\r\n		return service.findById(id);");
		sb.append("\r\n	}");
		sb.append(Utils.methodNote("按条件查询" + cName + "记录个数"));
		sb.append("\r\n	@PostMapping(\"queryCount\")");
		sb.append("\r\n	public long queryCount(@RequestBody " + upp + "Cond cond ){");
		sb.append("\r\n		return service.queryCount(cond);");
		sb.append("\r\n	}");
		sb.append("\r\n}");
		return sb;
	}
}
