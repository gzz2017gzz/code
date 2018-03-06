package com.gzz.createcode.template.center;

import com.gzz.createcode.common.Utils;

public class Bus {
	public static StringBuilder create(String pName, String clsUpp, String auth, String cName, String idType,String lowUpp) {
		StringBuilder sb = new StringBuilder();
		sb.append("package " + pName + ";");
		sb.append("\r\nimport java.util.List;");
		sb.append("\r\nimport com.dl.keep.common.util.Page;");
		sb.append("\r\n");
		sb.append("\r\nimport org.apache.commons.logging.Log;");
		sb.append("\r\nimport org.apache.commons.logging.LogFactory;");
		sb.append("\r\nimport org.springframework.beans.factory.annotation.Autowired;");
		sb.append("\r\nimport org.springframework.stereotype.Service;");
		sb.append(Utils.classNote(auth, cName + "业务逻辑层"));
		sb.append("\r\n@Service");
		sb.append("\r\npublic class " + clsUpp + "Bus {");
		sb.append("\r\n	@SuppressWarnings(\"unused\")");
		sb.append("\r\n	private Log logger = LogFactory.getLog(getClass());");
		sb.append("\r\n	@Autowired");
		sb.append("\r\n	private I" + clsUpp + "Client client; //注入" + cName + "客户端类");
		sb.append(Utils.methodNote("新增" + cName + "记录"));
		sb.append("\r\n	public int save(" + clsUpp + " " + lowUpp + ") {");
		sb.append("\r\n		return client.save(" + lowUpp + ");");
		sb.append("\r\n	}");
		sb.append(Utils.methodNote("删除" + cName + "记录(多条)"));
		sb.append("\r\n	public int delete(" + idType + " ids[]) {");
		sb.append("\r\n		return client.delete(ids);//物理删除");
		sb.append("\r\n	}");
		sb.append(Utils.methodNote("按主键查找单个" + cName + "记录"));
		sb.append("\r\n	public " + clsUpp + " findById(" + idType + " id) {");
		sb.append("\r\n		return client.findById(id);");
		sb.append("\r\n	}");
		sb.append(Utils.methodNote("更新" + cName + "记录"));
		sb.append("\r\n	public int update(" + clsUpp + " " + lowUpp + ") {");
		sb.append("\r\n		return client.update(" + lowUpp + ");");
		sb.append("\r\n	}");
		sb.append(Utils.methodNote("按条件查询分页" + cName + "列表"));
		sb.append("\r\n	public Page<" + clsUpp + "> queryPage(" + clsUpp + "Cond cond) {");
		sb.append("\r\n		return client.queryPage(cond);");
		sb.append("\r\n	}");
		sb.append(Utils.methodNote("按条件查询不分页" + cName + "列表"));
		sb.append("\r\n	public List<" + clsUpp + "> queryList(" + clsUpp + "Cond cond) {");
		sb.append("\r\n		return client.queryList(cond);");
		sb.append("\r\n	}");
		sb.append(Utils.methodNote("按条件查询" + cName + "记录个数"));
		sb.append("\r\n	public long queryCount(" + clsUpp + "Cond cond) {");
		sb.append("\r\n		return client.queryCount(cond);");
		sb.append("\r\n	}");
		sb.append("\r\n}");
		return sb;
	}
}
