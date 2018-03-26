package com.gzz.createcode.test;

import java.security.Principal;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.gzz.createcode.common.base.Page;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

/**
 * @类说明:[测试用户]app端Swagger控制器类
 * @author:高振中
 * @date:2018-03-21 09:09:51
 **/
@Api(value = "[测试用户]控制器", description = "[测试用户]相关操作")
@RestController
@RequestMapping("sysUser")
public class SysUserAction {
	@SuppressWarnings("unused")
	private final Log logger = LogFactory.getLog(getClass());
	@Autowired
	private SysUserService service; // 注入[测试用户]业务逻辑类

	/**
	 * @方法说明:新增[测试用户]记录
	 **/
	@ApiOperation(value = "新增[测试用户]", notes = "返回影响记录行数")
	@ApiImplicitParams({ @ApiImplicitParam(name = "sysUser", value = "[测试用户]对象", required = true, dataType = "SysUser"),
			@ApiImplicitParam(name = "Authorization", value = "Token", required = true, dataType = "string", paramType = "header") })
	@RequestMapping(value = "save", method = RequestMethod.POST)
	public SwaggerRespImpl<Integer> save(@RequestBody SysUser sysUser, Principal principal) {
		return new SwaggerRespImpl<>(service.save(sysUser));
	}

	/**
	 * @方法说明:删除[测试用户]记录
	 **/
	@ApiOperation(value = "删除[测试用户]", notes = "返回影响记录行数")
	@ApiImplicitParams({ @ApiImplicitParam(name = "id", value = "[测试用户]主键", required = true, dataType = "Long", paramType = "query"),
			@ApiImplicitParam(name = "Authorization", value = "Token", required = true, dataType = "string", paramType = "header") })
	@RequestMapping(value = "delete", method = RequestMethod.POST)
	public SwaggerRespImpl<Integer> delete(@RequestParam("id") Long id) {
		return new SwaggerRespImpl<>(service.delete(new Long[] { id }));
	}

	/**
	 * @方法说明:修改[测试用户]记录
	 **/
	@ApiOperation(value = "修改[测试用户]", notes = "返回影响记录行数")
	@ApiImplicitParams({ @ApiImplicitParam(name = "sysUser", value = "[测试用户]对象", required = true, dataType = "SysUser"),
			@ApiImplicitParam(name = "Authorization", value = "Token", required = true, dataType = "string", paramType = "header") })
	@RequestMapping(value = "update", method = RequestMethod.POST)
	public SwaggerRespImpl<Integer> update(@RequestBody SysUser sysUser, Principal principal) {
		return new SwaggerRespImpl<>(service.update(sysUser));
	}

	/**
	 * @方法说明:按条件查询分页[测试用户]列表
	 **/
	@ApiOperation(value = "按条件查询分页[测试用户]列表", notes = "返回分页[测试用户]列表")
	@ApiImplicitParams({ @ApiImplicitParam(name = "cond", value = "[测试用户]查询条件对象", required = true, dataType = "SysUserCond"),
			@ApiImplicitParam(name = "Authorization", value = "Token", required = true, dataType = "string", paramType = "header") })
	@RequestMapping(value = "queryPage", method = RequestMethod.POST)
	public SwaggerRespImpl<Page<SysUser>> queryPage(@RequestBody SysUserCond cond, Principal principal) {
		// cond.setBranch_id(getSessionBranchId(principal));
		return new SwaggerRespImpl<>(service.queryPage(cond));
	}

	/**
	 * @方法说明:按条件查询不分页[测试用户]列表
	 **/
	@ApiOperation(value = "按条件查询不分页[测试用户]列表", notes = "返回不分页[测试用户]列表")
	@ApiImplicitParams({ @ApiImplicitParam(name = "cond", value = "[测试用户]查询条件对象", required = true, dataType = "SysUserCond"),
			@ApiImplicitParam(name = "Authorization", value = "Token", required = true, dataType = "string", paramType = "header") })
	@RequestMapping(value = "queryList", method = RequestMethod.POST)
	public SwaggerRespImpl<List<SysUser>> queryList(@RequestBody SysUserCond cond, Principal principal) {
		// cond.setBranch_id(getSessionBranchId(principal));
		return new SwaggerRespImpl<>(service.queryList(cond));
	}

	/**
	 * @方法说明:按条件查询[测试用户]记录个数
	 **/
	@ApiOperation(value = "按条件查询[测试用户]记录个数", notes = "返回记录个数")
	@ApiImplicitParams({ @ApiImplicitParam(name = "cond", value = "[测试用户]查询条件对象", required = true, dataType = "SysUserCond"),
			@ApiImplicitParam(name = "Authorization", value = "Token", required = true, dataType = "string", paramType = "header") })
	@RequestMapping(value = "queryCount", method = RequestMethod.POST)
	public SwaggerRespImpl<Long> queryCount(@RequestBody SysUserCond cond) {
		return new SwaggerRespImpl<>(service.queryCount(cond));
	}

	/**
	 * @方法说明:按主键查询单条[测试用户]记录
	 **/
	@ApiOperation(value = "按主键查询单个[测试用户]记录", notes = "返回单个[测试用户]对象")
	@ApiImplicitParams({ @ApiImplicitParam(name = "id", value = "[测试用户]的主键", required = true, dataType = "Long", paramType = "query"),
			@ApiImplicitParam(name = "Authorization", value = "Token", required = true, dataType = "string", paramType = "header") })
	@RequestMapping(value = "findById", method = RequestMethod.POST)
	public SwaggerRespImpl<SysUser> findById(@RequestParam("id") Long id) {
		return new SwaggerRespImpl<>(service.findById(id));
	}

}