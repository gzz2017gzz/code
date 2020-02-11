package ${pName};
//import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
//import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.gzz.common.base.Page;
import com.xsrt.common.Result;
import lombok.extern.slf4j.Slf4j;

/**
 * @类说明 [${cName}]控制器
 * @author ${auth}
 * @date ${time}
 **/
@RestController
@RequestMapping("${lowUpp}")
@Slf4j
public class ${upp}Controller {
 
	@Autowired
	private ${upp}Service service; //注入${cName}业务逻辑层

    /**
     * @方法说明  新增[${cName}]记录
     */
	@PostMapping("save")
	public Result<Integer> save(@RequestBody ${upp} ${lowUpp}) {
		return Result.success(service.save(${lowUpp}));
	}

    /**
     * @方法说明 删除${cName}记录(多条)
     */
	@DeleteMapping("delete")
	public Result<Integer> delete( ${idType} ids[]) {
		return Result.success(service.delete(ids));
	}

    /**
     * @方法说明 修改${cName}记录
     */
	@PostMapping("update")
	public Result<Integer> update(@RequestBody ${upp} ${lowUpp}) {
		return Result.success(service.update(${lowUpp}));
	}

    /**
     * @方法说明 按条件查询分页${cName}列表
     */
	@PostMapping("queryPage")
	public Result<Page<${upp}>> queryPage(@RequestBody ${upp}Cond cond ){
		return Result.success(service.queryPage(cond));
	}

    /**
     * @方法说明 按条件查询不分页${cName}列表
     */
//	@PostMapping("queryList")
//	public Result<List<${upp}>> queryList(@RequestBody ${upp}Cond cond ){
//		return Result.success(service.queryList(cond));
//	}

    /**
     * @方法说明 按主键查单个${cName}记录
     */
//	@PostMapping("findById")
//	public Result<${upp}> findById(@RequestParam("id") ${idType} id) {
//		return Result.success(service.findById(id));
//	}

    /**
     * @方法说明 按条件查询${cName}记录个数
     */
//	@PostMapping("queryCount")
//	public Result<long> queryCount(@RequestBody ${upp}Cond cond ){
//		return Result.success(service.queryCount(cond));
//	}
}