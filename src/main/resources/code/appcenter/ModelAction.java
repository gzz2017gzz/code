package ${pName};

import java.security.Principal;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMethod;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;

import com.dl.appcenter.common.bean.SwaggerRespImpl;
import com.dl.appcenter.common.util.PrincipalAction;
import com.dl.keep.common.domain.app.${upp};
import com.dl.keep.common.domain.app.${upp}Cond;
import com.dl.keep.common.util.MessageInfo;
import com.dl.keep.common.util.Page;
import com.gzz.createcode.mvc.dao.CodeDao;

/**
 * @类说明 [${cName}]app端Swagger控制器类
 * @author ${auth}
 * @date ${time}
 **/
@Api(value = "[${cName}]控制器", description = "[${cName}]相关操作")
@RestController
@RequestMapping("${lowUpp}")
@Slf4j
public class ${upp}Action extends PrincipalAction {
	
    @Autowired
	private ${upp}Bus bus; //注入[${cName}]业务逻辑类

    /**
     * @方法说明  新增[${cName}]记录
     */
    @ApiOperation(value = "新增[${cName}]", notes = "返回影响记录行数")
    @ApiImplicitParams({
    	<#list fList as fi>
            @ApiImplicitParam(name = "${fi.name}", value = "${fi.comment}", paramType = "query", dataType="${fi.type}", required = true),
        </#list>
        @ApiImplicitParam(name = "Authorization", value = "Token",required = false, dataType = "string", paramType = "header")
    })
    @ApiResponses({
        @ApiResponse(code = 0, message = "操作成功")
    })
    @PostMapping("save")
	public SwaggerRespImpl<Integer> save(${upp} ${lowUpp}, Principal principal) {
    	return new SwaggerRespImpl<>(bus.save(${lowUpp}));
	}
    

    /**
     * @方法说明 删除[${cName}]记录
     */
    @ApiOperation(value = "删除[${cName}]", notes = "返回影响记录行数")
    @PostMapping( "delete" )
    public SwaggerRespImpl<Integer> delete(@RequestParam("id") ${idType} id) {
    	return new SwaggerRespImpl<>(bus.delete(new ${idType}[] { id }));
    }

    /**
     * @方法说明 修改[${cName}]记录
     */
    @ApiOperation(value = "修改[${cName}]", notes = "返回影响记录行数")
    @ApiImplicitParams( {
    	<#list fList as fi>
            @ApiImplicitParam(name = "${fi.name}", value = "${fi.comment}", paramType = "query", dataType="${fi.type}", required = false),
	    </#list>
        @ApiImplicitParam(name = "Authorization", value = "Token",required = true, dataType = "string", paramType = "header")
    })
    @PostMapping("update")
    public SwaggerRespImpl<Integer> update(${upp} ${lowUpp}, Principal principal) {
        return new SwaggerRespImpl<>(bus.update(${lowUpp}));
    }

    /**
     * @方法说明 按条件查询分页[${cName}]列表
     */
    @ApiOperation(value = "按条件查询分页[${cName}]列表", notes = "返回分页[${cName}]列表")
    @ApiImplicitParams({
    	<#list fList as fi>
            @ApiImplicitParam(name = "${fi.name}", value = "${fi.comment}", paramType = "query", dataType="${fi.type}", required = false),
        </#list>
        @ApiImplicitParam(name = "Authorization", value = "Token",required = true, dataType = "string", paramType = "header")
    })
    @PostMapping( "queryPage")
    public SwaggerRespImpl<Page<${upp}>> queryPage(${upp}Cond cond, Principal principal) {
        // cond.setBranch_id(getSessionBranchId(principal));
        return new SwaggerRespImpl<>(bus.queryPage(cond));
    }

    /**
     * @方法说明 按条件查询不分页[${cName}]列表
     */
    @ApiOperation(value = "按条件查询不分页[${cName}]列表", notes = "返回不分页[${cName}]列表")
    @ApiImplicitParams({
    	<#list fList as fi>
            @ApiImplicitParam(name = "${fi.name}", value = "${fi.comment}", paramType = "query", dataType="${fi.type}", required = false),
        </#list>
        @ApiImplicitParam(name = "Authorization", value = "Token",required = true, dataType = "string", paramType = "header")
    })
	@PostMapping("queryList")
    public SwaggerRespImpl<List<${upp}>> queryList(${upp}Cond cond, Principal principal) {
        // cond.setBranch_id(getSessionBranchId(principal));
		return new SwaggerRespImpl<>(bus.queryList(cond));
    }
            
    /**
     * @方法说明 按条件查询[${cName}]记录个数
     */
    @ApiOperation(value = "按条件查询[${cName}]记录个数", notes = "返回记录个数")
    @ApiImplicitParams({
    	<#list fList as fi>
            @ApiImplicitParam(name = "${fi.name}", value = "${fi.comment}", paramType = "query", dataType="${fi.type}", required = false),
            </#list>
        @ApiImplicitParam(name = "Authorization", value = "Token",required = true, dataType = "string", paramType = "header")
    })
	@PostMapping( "queryCount")
	public SwaggerRespImpl<Long> queryCount(${upp}Cond cond) {
		return new SwaggerRespImpl<>(bus.queryCount(cond));
	}
                
    /**
     * @方法说明 按主键查询单条[${cName}]记录
     */
    @ApiOperation(value = "按主键查询单个[${cName}]记录", notes = "返回单个[${cName}]对象")
    @ApiImplicitParams({ @ApiImplicitParam(name = "id", value = "[${cName}]的主键", required = true, dataType = "${idType}", paramType = "query"),
    @ApiImplicitParam(name = "Authorization", value = "Token",required = true, dataType = "string", paramType = "header") })
    @PostMapping( "findById")
    public SwaggerRespImpl<${upp}> findById(@RequestParam("id") ${idType} id) {
    	return new SwaggerRespImpl<>(bus.findById(id));
    }

    /**
     * @方法说明 验证方法
     */
    @ApiOperation(value = "按条件验证方法[${cName}]相关相信息", notes = "返回验证结果")
    @ApiImplicitParams( {
    	<#list fList as fi>
            @ApiImplicitParam(name = "${fi.name}", value = "${fi.comment}", paramType = "query", dataType="${fi.type}", required = false),
            </#list>
        @ApiImplicitParam(name = "Authorization", value = "Token",required = true, dataType = "string", paramType = "header")
    })
	@PostMapping( "validate")
    public SwaggerRespImpl<Integer> validate(${upp}Cond cond, Principal principal) {
        // 此处写验证逻辑
        // cond.setfield(...)
        // int count=bus.queryCount(cond)
        // if(count = 0){
        // }
		return new SwaggerRespImpl<>(1);
    }
}

