package com.gzz.createcode.mvc.action;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gzz.createcode.common.Utils;
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
	@RequestMapping("/queryList")
	public List<Table> queryList(@RequestBody CodeCond cond) {
		cond.setDb_user(CodeDao.DBUSER);
		List<Table> queryTableList = service.queryTableList(cond);
		return queryTableList;
	}

	/**
	 * @功能描述: 查询数据库中表名列表
	 */
	@RequestMapping("/queryField")
	public List<Field> queryField(@RequestBody CodeCond cond) {
		cond.setDb_user(CodeDao.DBUSER);
		return service.queryColumnList(cond);
	}

	/**
	 * @功能描述: 生成代码
	 */
	@RequestMapping("/create")
	public void create(@RequestBody CodeCond cond) {
		cond.setDb_user(CodeDao.DBUSER);
		Utils.setTime();
		service.create(cond);
		Utils.chmod();
	}
}
