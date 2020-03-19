package ${pName};

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zhxd.common.web.Response;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * @类说明 【${cName}】控制器
 * @author ${auth}
 * @date ${time}
 **/
@Api(tags = "${cName}接口")
@RestController
@RequestMapping("${low}")
public class ${upp}Controller {

	@Autowired
	private I${upp}Service service;//注入【${cName}】业务逻辑接口
	
	/**
	 * @方法说明  新增【${cName}】记录
	 */
	@PostMapping("add")
	@ApiOperation(value = "新增【${cName}】记录")
	 public Response add(@RequestBody @Valid ${upp} ${lowUpp}) {
 		return Response.success(service.save(${lowUpp}));
	}
	
	/**
	 * @方法说明 按主键删除【${cName}】记录
	 */
	@PostMapping("delete")
	@ApiOperation(value = "按主键删除【${cName}】记录")
	public Response delete(Integer id) {
		return Response.success(service.removeById(id));
	}
	
	/**
	 * @方法说明 修改【${cName}】记录
	 */
	@PostMapping("edit")
	@ApiOperation(value = "修改【${cName}】记录")
	public Response edit(@RequestBody @Valid ${upp} ${lowUpp}) {
 		return Response.success(service.updateById(${lowUpp}));
	}
	
	/**
	 * @方法说明 按条件查询分页【${cName}】列表
	 */
	@PostMapping("page")
	@ApiOperation(value = "按条件查询分页【${cName}】列表")
	public Response page(@RequestBody ${upp} ${lowUpp}, long current, long size) {
		return Response.success(service.page(new Page<${upp}>(current,size), new QueryWrapper<${upp}>(${lowUpp})));
	}
	
	/**
	 * @方法说明 按主键查单个【${cName}】记录
	 */
	@PostMapping("findById")
	@ApiOperation(value = "按主键查单个【${cName}】记录")
	public Response get(Integer id) {
		return Response.success(service.getById(id));
	}
}
