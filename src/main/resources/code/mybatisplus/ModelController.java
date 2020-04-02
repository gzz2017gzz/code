package ${pName};

import javax.validation.Valid;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.gzz.common.util.Result;

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
	private I${upp}Service ${lowUpp}Service;//注入【${cName}】业务逻辑接口
	
	/**
	 * @方法说明  新增【${cName}】记录
	 */
	@PostMapping("add")
	@ApiOperation(value = "新增【${cName}】记录")
	 public Result<Boolean> add(@RequestBody @Valid ${upp} ${lowUpp}) {
 		return Result.success(${lowUpp}Service.save(${lowUpp}));
	}
	
	/**
	 * @方法说明 按主键删除【${cName}】记录
	 */
	@PostMapping("delete")
	@ApiOperation(value = "按主键删除【${cName}】记录")
	public Result<Boolean> delete(Integer id) {
		return Result.success(${lowUpp}Service.removeById(id));
	}
	
	/**
	 * @方法说明 修改【${cName}】记录
	 */
	@PostMapping("edit")
	@ApiOperation(value = "修改【${cName}】记录")
	public Result<Boolean> edit(@RequestBody @Valid ${upp} ${lowUpp}) {
 		return Result.success(${lowUpp}Service.updateById(${lowUpp}));
	}
	
	/**
	 * @方法说明 按条件查询分页【${cName}】列表
	 */
	@PostMapping("page")
	@ApiOperation(value = "按条件查询分页【${cName}】列表")
	public Result<IPage<${upp}>> page(@RequestBody ${upp}Cond cond) {
		${upp} ${lowUpp}= new ${upp}();
		BeanUtils.copyProperties(cond, ${lowUpp});
		return Result.success(${lowUpp}Service.page(new Page<${upp}>(cond.getPage(),cond.getSize()), new QueryWrapper<${upp}>(${lowUpp})));
	}
	
	/**
	 * @方法说明 按主键查单个【${cName}】记录
	 */
	@PostMapping("findById")
	@ApiOperation(value = "按主键查单个【${cName}】记录")
	public Result<${upp}> get(Integer id) {
		return Result.success(${lowUpp}Service.getById(id));
	}
}
