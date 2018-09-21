package com.gzz.createcode.test;

import java.security.Principal;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

 
import com.gzz.createcode.common.base.Page;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;


/**
 * @类说明:[部门]app端Swagger控制器类
 * @author 高振中
 * @date:2018-09-21 15:19:21
 **/
@Api(value = "[部门]控制器", description = "[部门]相关操作")
@RestController
@RequestMapping("dept")
public class DeptAction  {
	@SuppressWarnings("unused")
	private final Log logger = LogFactory.getLog(getClass());
	@Autowired
	private DeptService bus; //注入[部门]业务逻辑类

	/**
	 * @方法说明 新增[部门]记录
	 **/
	@ApiOperation(value = "新增[部门]", notes = "返回影响记录行数")
	@ApiImplicitParams({
		@ApiImplicitParam(name = "dept_id", required = true, value = "主键",dataType="Long", paramType = "query"),
		@ApiImplicitParam(name = "branch_id", required = true, value = "门店主键",dataType="Long", paramType = "query"),
		@ApiImplicitParam(name = "parent_id", required = true, value = "上级ID",dataType="Long", paramType = "query"),
		@ApiImplicitParam(name = "name", required = true, value = "部门名称",dataType="String", paramType = "query"),
		@ApiImplicitParam(name = "level", required = true, value = "级别",dataType="Byte", paramType = "query"),
		@ApiImplicitParam(name = "leaf", required = true, value = "是否叶子",dataType="Byte", paramType = "query"),
		@ApiImplicitParam(name = "enable", required = true, value = "可用标志",dataType="Byte", paramType = "query"),
		@ApiImplicitParam(name = "remark", required = true, value = "备注",dataType="String", paramType = "query"),
		@ApiImplicitParam(name = "create_time", required = true, value = "创建时间",dataType="Date", paramType = "query"),
		@ApiImplicitParam(name = "creator", required = true, value = "创建人",dataType="Long", paramType = "query"),
		@ApiImplicitParam(name = "Authorization", value = "Token",required = true, dataType = "string", paramType = "header") })
		@ApiResponses({
			@ApiResponse(code = 0, message = "靠，居然成功了"),
			@ApiResponse(code = 1, message = "说出来你可能不信，我居然会失败"),
			@ApiResponse(code = 2, message = "对方不想理你，并向你抛来一个空指针")
		})
	@PostMapping("save")
	public SwaggerRespImpl<Integer> save(Dept dept, Principal principal) {
		return new SwaggerRespImpl<>(bus.save(dept));
	}


	/**
	 * @方法说明 删除[部门]记录
	 **/
	@ApiOperation(value = "删除[部门]", notes = "返回影响记录行数")
	@ApiImplicitParams({ @ApiImplicitParam(name = "id", value = "[部门]主键", required = true, dataType = "Long", paramType = "query"),
		@ApiImplicitParam(name = "Authorization", value = "Token",required = true, dataType = "string", paramType = "header") })
		@ApiResponses({
			@ApiResponse(code = 0, message = "靠，居然成功了"),
			@ApiResponse(code = 1, message = "说出来你可能不信，我居然会失败"),
			@ApiResponse(code = 2, message = "对方不想理你，并向你抛来一个空指针")
		})
	@PostMapping( "delete" )
	public SwaggerRespImpl<Integer> delete(@RequestParam("id") Long id) {
		return new SwaggerRespImpl<>(bus.delete(new Long[] { id }));
	}


	/**
	 * @方法说明 修改[部门]记录
	 **/
	@ApiOperation(value = "修改[部门]", notes = "返回影响记录行数")
	@ApiImplicitParams({
		@ApiImplicitParam(name = "dept_id", required = true, value = "主键",dataType="Long", paramType = "query"),
		@ApiImplicitParam(name = "branch_id", required = true, value = "门店主键",dataType="Long", paramType = "query"),
		@ApiImplicitParam(name = "parent_id", required = true, value = "上级ID",dataType="Long", paramType = "query"),
		@ApiImplicitParam(name = "name", required = true, value = "部门名称",dataType="String", paramType = "query"),
		@ApiImplicitParam(name = "level", required = true, value = "级别",dataType="Byte", paramType = "query"),
		@ApiImplicitParam(name = "leaf", required = true, value = "是否叶子",dataType="Byte", paramType = "query"),
		@ApiImplicitParam(name = "enable", required = true, value = "可用标志",dataType="Byte", paramType = "query"),
		@ApiImplicitParam(name = "remark", required = true, value = "备注",dataType="String", paramType = "query"),
		@ApiImplicitParam(name = "create_time", required = true, value = "创建时间",dataType="Date", paramType = "query"),
		@ApiImplicitParam(name = "creator", required = true, value = "创建人",dataType="Long", paramType = "query"),
		@ApiImplicitParam(name = "Authorization", value = "Token",required = true, dataType = "string", paramType = "header") })
		@ApiResponses({
			@ApiResponse(code = 0, message = "靠，居然成功了"),
			@ApiResponse(code = 1, message = "说出来你可能不信，我居然会失败"),
			@ApiResponse(code = 2, message = "对方不想理你，并向你抛来一个空指针")
		})
	@PostMapping("update")
	public SwaggerRespImpl<Integer> update(Dept dept, Principal principal) {
		return new SwaggerRespImpl<>(bus.update(dept));
	}


	/**
	 * @方法说明 按条件查询分页[部门]列表
	 **/
	@ApiOperation(value = "按条件查询分页[部门]列表", notes = "返回分页[部门]列表")
	@ApiImplicitParams({
		@ApiImplicitParam(name = "dept_id", required = true, value = "主键",dataType="Long", paramType = "query"),
		@ApiImplicitParam(name = "branch_id", required = true, value = "门店主键",dataType="Long", paramType = "query"),
		@ApiImplicitParam(name = "parent_id", required = true, value = "上级ID",dataType="Long", paramType = "query"),
		@ApiImplicitParam(name = "name", required = true, value = "部门名称",dataType="String", paramType = "query"),
		@ApiImplicitParam(name = "level", required = true, value = "级别",dataType="Byte", paramType = "query"),
		@ApiImplicitParam(name = "leaf", required = true, value = "是否叶子",dataType="Byte", paramType = "query"),
		@ApiImplicitParam(name = "enable", required = true, value = "可用标志",dataType="Byte", paramType = "query"),
		@ApiImplicitParam(name = "remark", required = true, value = "备注",dataType="String", paramType = "query"),
		@ApiImplicitParam(name = "create_time", required = true, value = "创建时间",dataType="Date", paramType = "query"),
		@ApiImplicitParam(name = "creator", required = true, value = "创建人",dataType="Long", paramType = "query"),
		@ApiImplicitParam(name = "Authorization", value = "Token",required = true, dataType = "string", paramType = "header") })
		@ApiResponses({
			@ApiResponse(code = 0, message = "靠，居然成功了"),
			@ApiResponse(code = 1, message = "说出来你可能不信，我居然会失败"),
			@ApiResponse(code = 2, message = "对方不想理你，并向你抛来一个空指针")
		})
	@PostMapping( "queryPage")
	public SwaggerRespImpl<Page<Dept>> queryPage(DeptCond cond, Principal principal) {
		// cond.setBranch_id(getSessionBranchId(principal));
		return new SwaggerRespImpl<>(bus.queryPage(cond));
	}


	/**
	 * @方法说明 按条件查询不分页[部门]列表
	 **/
	@ApiOperation(value = "按条件查询不分页[部门]列表", notes = "返回不分页[部门]列表")
	@ApiImplicitParams({
		@ApiImplicitParam(name = "dept_id", required = true, value = "主键",dataType="Long", paramType = "query"),
		@ApiImplicitParam(name = "branch_id", required = true, value = "门店主键",dataType="Long", paramType = "query"),
		@ApiImplicitParam(name = "parent_id", required = true, value = "上级ID",dataType="Long", paramType = "query"),
		@ApiImplicitParam(name = "name", required = true, value = "部门名称",dataType="String", paramType = "query"),
		@ApiImplicitParam(name = "level", required = true, value = "级别",dataType="Byte", paramType = "query"),
		@ApiImplicitParam(name = "leaf", required = true, value = "是否叶子",dataType="Byte", paramType = "query"),
		@ApiImplicitParam(name = "enable", required = true, value = "可用标志",dataType="Byte", paramType = "query"),
		@ApiImplicitParam(name = "remark", required = true, value = "备注",dataType="String", paramType = "query"),
		@ApiImplicitParam(name = "create_time", required = true, value = "创建时间",dataType="Date", paramType = "query"),
		@ApiImplicitParam(name = "creator", required = true, value = "创建人",dataType="Long", paramType = "query"),
		@ApiImplicitParam(name = "Authorization", value = "Token",required = true, dataType = "string", paramType = "header") })
		@ApiResponses({
			@ApiResponse(code = 0, message = "靠，居然成功了"),
			@ApiResponse(code = 1, message = "说出来你可能不信，我居然会失败"),
			@ApiResponse(code = 2, message = "对方不想理你，并向你抛来一个空指针")
		})
	@PostMapping("queryList")
	public SwaggerRespImpl<List<Dept>> queryList(DeptCond cond, Principal principal) {
		// cond.setBranch_id(getSessionBranchId(principal));
		return new SwaggerRespImpl<>(bus.queryList(cond));
	}


	/**
	 * @方法说明 按条件查询[部门]记录个数
	 **/
//	@ApiOperation(value = "按条件查询[部门]记录个数", notes = "返回记录个数")
//	@PostMapping( "queryCount")
//	public SwaggerRespImpl<Long> queryCount(@RequestBody DeptCond cond) {
//		return new SwaggerRespImpl<>(bus.queryCount(cond));
//	}


	/**
	 * @方法说明 按主键查询单条[部门]记录
	 **/
//	@ApiOperation(value = "按主键查询单个[部门]记录", notes = "返回单个[部门]对象")
//	@ApiImplicitParams({ @ApiImplicitParam(name = "id", value = "[部门]的主键", required = true, dataType = "Long", paramType = "query"),
//		@ApiImplicitParam(name = "Authorization", value = "Token",required = true, dataType = "string", paramType = "header") })
//	@PostMapping( "findById")
//	public SwaggerRespImpl<Dept> findById(@RequestParam("id") Long id) {
//		return new SwaggerRespImpl<>(bus.findById(id));
//	}

	/**
	 * @方法说明 验证方法
	 **/
//	@ApiOperation(value = "按条件验证方法[部门]相关相信息", notes = "返回验证结果")
//	@ApiImplicitParams({ @ApiImplicitParam(name = "cond", value = "[部门]查询条件对象", required = true, dataType = "DeptCond"),
//		@ApiImplicitParam(name = "Authorization", value = "Token",required = true, dataType = "string", paramType = "header") })
//	@PostMapping( "validate")
//	public SwaggerRespImpl<Integer> validate(@RequestBody DeptCond cond, Principal principal) {
		// 此处写验证逻辑
		// cond.setfield(...)
		// int count=bus.queryCount(cond)
		// if(count = 0){
		// }
//		return new SwaggerRespImpl<>(1);
//	}

}