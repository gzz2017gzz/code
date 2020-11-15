package ${pName};

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.gzz.common.config.Result;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * @类说明 【${cName}】控制器
 * @author ${auth}
 * @date ${time}
 **/
//@Slf4j
@RestController
@RequestMapping("${lowUpp}")
@Api(tags = "【${cName}】API")
public class ${upp}Controller {

	@Autowired
	private ${upp}Service ${lowUpp}Service; // 注入【${cName}】业务逻辑层

	/**
	 * @方法说明 新增【${cName}】记录
	 */
	@PostMapping("save")
	@ApiOperation(value = "新增【${cName}】记录")
	public Result<Integer> save(@RequestBody @Validated ${upp} ${lowUpp}) {
		return Result.success(${lowUpp}Service.save(${lowUpp}));
	}

	/**
	 * @方法说明 删除【${cName}】记录
	 */
	@PostMapping("delete")
	@ApiOperation(value = "删除【${cName}】记录")
	public Result<Integer> delete(@RequestBody List<${idType}> ids) {
		return Result.success(${lowUpp}Service.delete(ids));
	}

	/**
	 * @方法说明 修改【${cName}】记录
	 */
	@PostMapping("update")
	@ApiOperation(value = "修改【${cName}】记录")
	public Result<Integer> update(@RequestBody @Validated ${upp} ${lowUpp}) {
		return Result.success(${lowUpp}Service.update(${lowUpp}));
	}
 

	/**
	 * @方法说明 按条件查询【${cName}】列表
	 */
	@PostMapping("list")
	@ApiOperation(value = "按条件查询不分页【${cName}】列表")
	public Result<List<${upp}>> list(@RequestBody ${upp}Cond cond) {
		return Result.success(${lowUpp}Service.list(cond));
	}
	
	/**
	 * @方法说明 按条件查询【${cName}】树
	 */
	@PostMapping("tree")
	@ApiOperation(value = " 按条件查询【${cName}】树")
	public Result<List<${upp}>> tree() {
		return Result.success(${lowUpp}Service.tree());
	}
	
//	/**
//	 * @方法说明 按主键查单个【${cName}】记录
//	 */
//	@PostMapping("findById")
//	@ApiOperation(value = "按主键查单个【${cName}】记录")
//	public Result<${upp}> findById(@RequestParam("id") ${idType} id) {
//		return Result.success(${lowUpp}Service.findById(id));
//	}

	/**
	 * @方法说明 按条件查询【${cName}】记录个数
	 */
	@PostMapping("count")
	@ApiOperation(value = "【${cName}】记录个数")
	public Result<Integer> count(@RequestBody ${upp}Cond cond) {
		return Result.success(${lowUpp}Service.count(cond));
	}
}