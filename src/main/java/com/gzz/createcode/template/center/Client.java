package com.gzz.createcode.template.center;

import com.gzz.createcode.common.Utils;

public class Client {
	public static StringBuilder create(String pName, String clsUpp, String auth, String cName, String idType, String lowUpp) {
		StringBuilder sb = new StringBuilder();
		sb.append("package " + pName + ";");
		sb.append("\r\nimport java.util.List;");
		sb.append("\r\nimport org.springframework.cloud.netflix.feign.FeignClient;");
		sb.append("\r\nimport org.springframework.web.bind.annotation.RequestBody;");
		sb.append("\r\nimport org.springframework.web.bind.annotation.RequestMapping;");
		sb.append("\r\nimport org.springframework.web.bind.annotation.RequestParam;");
		sb.append("\r\nimport com.dl.keep.common.util.Page;");
		sb.append(Utils.classNote(auth, cName + "客户端类"));
		sb.append("\r\n@FeignClient(\"dl-keep-web-data/" + lowUpp + "\")");
		sb.append("\r\npublic interface I" + clsUpp + "Client {");
		sb.append(Utils.methodNote("新增" + cName + "记录"));
		sb.append("\r\n	@RequestMapping(\"save\")");
		sb.append("\r\n	int save(@RequestBody " + clsUpp + " " + lowUpp + ");");
		sb.append(Utils.methodNote("删除" + cName + "记录(多条)"));
		sb.append("\r\n	@RequestMapping(\"delete\")");
		sb.append("\r\n	int delete(@RequestParam(\"ids[]\") " + idType + " ids[]);");
		sb.append(Utils.methodNote("修改" + cName + "记录"));
		sb.append("\r\n	@RequestMapping(\"update\")");
		sb.append("\r\n	int update(@RequestBody " + clsUpp + " " + lowUpp + ");");
		sb.append(Utils.methodNote("按条件查询分页" + cName + "列表"));
		sb.append("\r\n	@RequestMapping(\"queryPage\")");
		sb.append("\r\n	Page<" + clsUpp + "> queryPage(@RequestBody " + clsUpp + "Cond cond );");
		sb.append(Utils.methodNote("按条件查询不分页" + cName + "列表"));
		sb.append("\r\n	@RequestMapping(\"queryList\")");
		sb.append("\r\n	List<" + clsUpp + "> queryList(@RequestBody " + clsUpp + "Cond cond );");
		sb.append(Utils.methodNote("按条件查询" + cName + "记录个数"));
		sb.append("\r\n	@RequestMapping(\"queryCount\")");
		sb.append("\r\n	long queryCount(@RequestBody " + clsUpp + "Cond cond );");
		sb.append(Utils.methodNote("按主键查单个" + cName + "记录"));
		sb.append("\r\n	@RequestMapping(\"findById\")");
		sb.append("\r\n	" + clsUpp + " findById(@RequestParam(\"id\") " + idType + " id);");
		sb.append("\r\n}");
		return sb;
	}
}
