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
	public int save(@RequestBody ${upp} ${lowUpp}) {
		return service.save(${lowUpp});
	}

    /**
     * @方法说明 删除${cName}记录(多条)
     */
	@DeleteMapping("delete")
	public int delete( ${idType} ids[]) {
		return service.delete(ids);
	}

    /**
     * @方法说明 修改${cName}记录
     */
	@PostMapping("update")
	public int update(@RequestBody ${upp} ${lowUpp}) {
		return service.update(${lowUpp});
	}

    /**
     * @方法说明 按条件查询分页${cName}列表
     */
	@PostMapping("queryPage")
	public Page<${upp}> queryPage(@RequestBody ${upp}Cond cond ){
		return service.queryPage(cond);
	}

    /**
     * @方法说明 按条件查询不分页${cName}列表
     */
//	@PostMapping("queryList")
//	public List<${upp}> queryList(@RequestBody ${upp}Cond cond ){
//		return service.queryList(cond);
//	}

    /**
     * @方法说明 按主键查单个${cName}记录
     */
//	@PostMapping("findById")
//	public ${upp} findById(@RequestParam("id") ${idType} id) {
//		return service.findById(id);
//	}

    /**
     * @方法说明 按条件查询${cName}记录个数
     */
//	@PostMapping("queryCount")
//	public long queryCount(@RequestBody ${upp}Cond cond ){
//		return service.queryCount(cond);
//	}
}