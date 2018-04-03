package com.gzz.createcode.mvc.action;

import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.gzz.createcode.common.Utils;
import com.gzz.createcode.common.ZipUtils;
import com.gzz.createcode.mvc.dao.CodeDao;
import com.gzz.createcode.mvc.model.CodeCond;
import com.gzz.createcode.mvc.model.Field;
import com.gzz.createcode.mvc.model.Table;
import com.gzz.createcode.mvc.service.CodeService;

/**
 * @功能描述:代码生成器控制器类
 * @author gzz_gzz@163.com
 * @date 2018-02-15
 */
@RestController
@RequestMapping("/code")
public class CodeAction {
	@Autowired
	private CodeService service;// 生成器业务罗辑接口

	/**
	 * @功能描述: 查询数据库中表名列表
	 */
	@RequestMapping(value = "/queryList", method = RequestMethod.POST)
	public List<Table> queryList(@RequestBody CodeCond cond) {
		cond.setDb_user(CodeDao.DBUSER);
		List<Table> queryTableList = service.queryTables(cond);
		return queryTableList;
	}

	/**
	 * @功能描述: 查询数据库中表名列表
	 */
	@RequestMapping(value = "/queryField", method = RequestMethod.POST)
	public List<Field> queryField(@RequestBody CodeCond cond) {
		cond.setDb_user(CodeDao.DBUSER);
		return service.queryFields(cond);
	}

	/**
	 * @功能描述: 生成代码
	 */
	@RequestMapping(value = "/create", method = RequestMethod.POST)
	public void create(@RequestBody CodeCond cond) {
		cond.setDb_user(CodeDao.DBUSER);
		Utils.setTime();
		service.create(cond);
		Utils.chmod();
	}

	@RequestMapping(value = { "/downCode" }, method = { RequestMethod.GET })
	public void downCode(HttpServletResponse response) throws IOException {
		String fileName = "code.zip";
		ZipUtils.createZip(Utils.path() + "com", Utils.path() + fileName);
		Path path = Paths.get(Utils.path() + fileName);
		response.setHeader("Content-Disposition", "attachment; filename=\"" + fileName + "\"");
		response.setHeader("Content-Length", "" + Files.size(path));
		response.setContentType("application/zip");
		OutputStream out = response.getOutputStream();
		out.write(Files.readAllBytes(path));
		out.flush();
		out.close();
	}

}
