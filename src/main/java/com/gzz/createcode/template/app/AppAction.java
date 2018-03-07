package com.gzz.createcode.template.app;

import com.gzz.createcode.common.Utils;

public class AppAction {
	public static StringBuilder create(String pName, String clsUpp, String auth, String cName, String idType, String lowUpp) {
		StringBuilder sb = new StringBuilder();
		sb.append("package " + pName + ";");
		sb.append("\r\n");
		sb.append("\r\nimport java.security.Principal;");
		sb.append("\r\nimport java.util.List;");
		sb.append("\r\n");
		sb.append("\r\nimport org.apache.commons.logging.Log;");
		sb.append("\r\nimport org.apache.commons.logging.LogFactory;");
		sb.append("\r\nimport org.springframework.beans.factory.annotation.Autowired;");
		sb.append("\r\nimport org.springframework.web.bind.annotation.RequestBody;");
		sb.append("\r\nimport org.springframework.web.bind.annotation.RequestMapping;");
		sb.append("\r\nimport org.springframework.web.bind.annotation.RequestParam;");
		sb.append("\r\nimport org.springframework.web.bind.annotation.RestController;");
		sb.append("\r\nimport org.springframework.web.bind.annotation.RequestMethod;");
		sb.append("\r\n");
		sb.append("\r\nimport io.swagger.annotations.Api;");
		sb.append("\r\nimport io.swagger.annotations.ApiImplicitParam;");
		sb.append("\r\nimport io.swagger.annotations.ApiImplicitParams;");
		sb.append("\r\nimport io.swagger.annotations.ApiOperation;");
		sb.append("\r\n");
		sb.append("\r\nimport com.dl.appcenter.common.bean.SwaggerRespImpl;");
		sb.append("\r\nimport com.dl.appcenter.common.util.PrincipalAction;");
		sb.append("\r\nimport com.dl.keep.common.domain.app." + clsUpp + ";");
		sb.append("\r\nimport com.dl.keep.common.domain.app." + clsUpp + "Cond;");
		sb.append("\r\nimport com.dl.keep.common.util.MessageInfo;");
		sb.append("\r\nimport com.dl.keep.common.util.Page;");
		sb.append("\r\n");
		sb.append(Utils.classNote(auth, "[" + cName + "]app端Swagger控制器类"));
		sb.append("\r\n\t@Api(value = \"[" + cName + "]控制器\", description = \"[" + cName + "]相关操作\")");
		sb.append("\r\n\t@RestController");
		sb.append("\r\n\t@RequestMapping(\"" + lowUpp + "\")");
		sb.append("\r\n\tpublic class " + clsUpp + "Action extends PrincipalAction {");
		sb.append("\r\n\t@SuppressWarnings(\"unused\")");
		sb.append("\r\n	private final Log logger = LogFactory.getLog(getClass());");
		sb.append("\r\n	@Autowired");
		sb.append("\r\n	private " + clsUpp + "Bus bus; //注入[" + cName + "]业务逻辑类");
		sb.append(Utils.methodNote("新增[" + cName + "]记录"));
		sb.append("\r\n\t@ApiOperation(value = \"新增[" + cName + "]\", notes = \"返回影响记录行数\")");
		sb.append("\r\n\t@ApiImplicitParams({ @ApiImplicitParam(name = \"" + lowUpp + "\", value = \"[" + cName + "]对象\", required = true, dataType = \"" + clsUpp + "\"),");
		sb.append("\r\n\t\t@ApiImplicitParam(name = \"Authorization\", value = \"Token\",required = true, dataType = \"string\", paramType = \"header\") })");
		sb.append("\r\n\t@RequestMapping( value = \"save\", method = RequestMethod.POST)");
		sb.append("\r\n	public SwaggerRespImpl<Integer> save(@RequestBody " + clsUpp + " " + lowUpp + ", Principal principal) {");
		sb.append("\r\n		return new SwaggerRespImpl<>(bus.save(" + lowUpp + "));");
		sb.append("\r\n	}");
		sb.append("\r\n");
		sb.append(Utils.methodNote("删除[" + cName + "]记录"));
		sb.append("\r\n\t@ApiOperation(value = \"删除[" + cName + "]\", notes = \"返回影响记录行数\")");
		sb.append("\r\n\t@ApiImplicitParams({ @ApiImplicitParam(name = \"id\", value = \"[" + cName + "]主键\", required = true, dataType = \"" + idType + "\", paramType = \"query\"),");
		sb.append("\r\n\t\t@ApiImplicitParam(name = \"Authorization\", value = \"Token\",required = true, dataType = \"string\", paramType = \"header\") })");
		sb.append("\r\n\t@RequestMapping( value = \"delete\", method = RequestMethod.POST)");
		sb.append("\r\n	public SwaggerRespImpl<Integer> delete(@RequestParam(\"id\") " + idType + " id) {");
		sb.append("\r\n		return new SwaggerRespImpl<>(bus.delete(new " + idType + "[] { id }));");
		sb.append("\r\n	}");
		sb.append("\r\n");
		sb.append(Utils.methodNote("修改[" + cName + "]记录"));
		sb.append("\r\n\t@ApiOperation(value = \"修改[" + cName + "]\", notes = \"返回影响记录行数\")");
		sb.append("\r\n\t@ApiImplicitParams({ @ApiImplicitParam(name = \"" + lowUpp + "\", value = \"[" + cName + "]对象\", required = true, dataType = \"" + clsUpp + "\"),");
		sb.append("\r\n\t\t@ApiImplicitParam(name = \"Authorization\", value = \"Token\",required = true, dataType = \"string\", paramType = \"header\") })");
		sb.append("\r\n\t@RequestMapping( value = \"update\", method = RequestMethod.POST)");
		sb.append("\r\n	public SwaggerRespImpl<Integer> update(@RequestBody " + clsUpp + " " + lowUpp + ", Principal principal) {");
		sb.append("\r\n		return new SwaggerRespImpl<>(bus.update(" + lowUpp + "));");
		sb.append("\r\n	}");
		sb.append("\r\n");
		sb.append(Utils.methodNote("按条件查询分页[" + cName + "]列表"));
		sb.append("\r\n\t@ApiOperation(value = \"按条件查询分页[" + cName + "]列表\", notes = \"返回分页[" + cName + "]列表\")");
		sb.append("\r\n\t@ApiImplicitParams({ @ApiImplicitParam(name = \"cond\", value = \"[" + cName + "]查询条件对象\", required = true, dataType = \"" + clsUpp + "Cond\"),");
		sb.append("\r\n\t\t@ApiImplicitParam(name = \"Authorization\", value = \"Token\",required = true, dataType = \"string\", paramType = \"header\") })");
		sb.append("\r\n	@RequestMapping( value=\"queryPage\", method = RequestMethod.POST)");
		sb.append("\r\n	public SwaggerRespImpl<Page<" + clsUpp + ">> queryPage(@RequestBody " + clsUpp + "Cond cond, Principal principal) {");
		sb.append("\r\n		// cond.setBranch_id(getDefaultBranchId(principal));");
		sb.append("\r\n		return new SwaggerRespImpl<>(bus.queryPage(cond));");
		sb.append("\r\n	}");
		sb.append("\r\n");
		sb.append(Utils.methodNote("按条件查询不分页[" + cName + "]列表"));
		sb.append("\r\n\t@ApiOperation(value = \"按条件查询不分页[" + cName + "]列表\", notes = \"返回不分页[" + cName + "]列表\")");
		sb.append("\r\n\t@ApiImplicitParams({ @ApiImplicitParam(name = \"cond\", value = \"[" + cName + "]查询条件对象\", required = true, dataType = \"" + clsUpp + "Cond\"),");
		sb.append("\r\n\t\t@ApiImplicitParam(name = \"Authorization\", value = \"Token\",required = true, dataType = \"string\", paramType = \"header\") })");

		sb.append("\r\n	@RequestMapping( value=\"queryList\", method = RequestMethod.POST)");
		sb.append("\r\n	public SwaggerRespImpl<List<" + clsUpp + ">> queryList(@RequestBody " + clsUpp + "Cond cond, Principal principal) {");
		sb.append("\r\n		// cond.setBranch_id(getDefaultBranchId(principal));");
		sb.append("\r\n		return new SwaggerRespImpl<>(bus.queryList(cond));");
		sb.append("\r\n	}");
		sb.append("\r\n");
		sb.append(Utils.methodNote("按条件查询[" + cName + "]记录个数"));

		sb.append("\r\n\t@ApiOperation(value = \"按条件查询[" + cName + "]记录个数\", notes = \"返回记录个数\")");
		sb.append("\r\n\t@ApiImplicitParams({ @ApiImplicitParam(name = \"cond\", value = \"[" + cName + "]查询条件对象\", required = true, dataType = \"" + clsUpp + "Cond\"),");
		sb.append("\r\n\t\t@ApiImplicitParam(name = \"Authorization\", value = \"Token\",required = true, dataType = \"string\", paramType = \"header\") })");

		sb.append("\r\n	@RequestMapping( value=\"queryCount\", method = RequestMethod.POST)");
		sb.append("\r\n	public SwaggerRespImpl<Long> queryCount(@RequestBody " + clsUpp + "Cond cond) {");
		sb.append("\r\n		return new SwaggerRespImpl<>(bus.queryCount(cond));");
		sb.append("\r\n	}");
		sb.append("\r\n");
		sb.append(Utils.methodNote("按主键查询单条[" + cName + "]记录"));

		sb.append("\r\n\t@ApiOperation(value = \"按主键查询单个[" + cName + "]记录\", notes = \"返回单个[" + cName + "]对象\")");
		sb.append("\r\n\t@ApiImplicitParams({ @ApiImplicitParam(name = \"id\", value = \"[" + cName + "]的主键\", required = true, dataType = \"" + idType + "\", paramType = \"query\"),");
		sb.append("\r\n\t\t@ApiImplicitParam(name = \"Authorization\", value = \"Token\",required = true, dataType = \"string\", paramType = \"header\") })");

		sb.append("\r\n	@RequestMapping( value=\"findById\", method = RequestMethod.POST)");
		sb.append("\r\n	public SwaggerRespImpl<" + clsUpp + "> findById(@RequestParam(\"id\") " + idType + " id) {");
		sb.append("\r\n		return new SwaggerRespImpl<>(bus.findById(id));");
		sb.append("\r\n	}");

		sb.append(Utils.methodNote("验证方法"));
		sb.append("\r\n	@RequestMapping(\"validate\")");
		sb.append("\r\n	public MessageInfo validate(@RequestBody " + clsUpp + "Cond cond, Principal principal) {");
		sb.append("\r\n		MessageInfo mi = new MessageInfo();");
		sb.append("\r\n		// 此处写验证逻辑");
		sb.append("\r\n		// cond.setfield(...)");
		sb.append("\r\n		// int count=bus.queryCount(cond)");
		sb.append("\r\n		// if(count = 0){");
		sb.append("\r\n		// mi.setMessage(1, \"记录个数不能为0!\");");
		sb.append("\r\n		// }");
		sb.append("\r\n		return mi;");
		sb.append("\r\n	}");
		sb.append("\r\n");
		sb.append("\r\n}");
		return sb;
	}
}
