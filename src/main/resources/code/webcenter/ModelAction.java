package ${pName};
import java.util.List;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import java.security.Principal;
import com.dl.webcenter.common.controller.PrincipalAction;
import com.dl.keep.common.util.MessageInfo;
import com.dl.keep.common.util.Page;
/**
 * @类说明 ${cName}--前端控制器层
 * @author ${auth}
 * @date  ${time}
 **/
@RestController
@RequestMapping("api/${lowUpp}")
public class ${upp}Action extends PrincipalAction {

	@SuppressWarnings("unused")
	private final Log logger = LogFactory.getLog(getClass());

	@Autowired
	private ${upp}Bus bus; //${cName}Business层

    /**
     * @方法说明  新增[${cName}]记录
     */
	@PostMapping("save")
	public int save(@RequestBody ${upp} ${lowUpp}, Principal principal) {
		return bus.save(${lowUpp});
	}
    
    /**
     * @方法说明  新增[${cName}]记录
     */
    //@PostMapping("validate")
    //public MessageInfo validate(@RequestBody ${upp}Cond cond, Principal principal) {
    //MessageInfo mi = new MessageInfo();
    ////此处写验证逻辑
    ////cond.setfield(...)
    ////int count=bus.queryCount(cond)
    ////if(count = 0){
    ////mi.setMessage(1, "记录个数不能为0!\
    ////}
    //return mi;
    //}

    /**
     * @方法说明 删除${cName}记录(多条)
     */
	@DeleteMapping("delete")
	public int delete(@RequestParam("ids[]") ${idType} ids[]) {
		return bus.delete(ids);
	}

    /**
     * @方法说明 修改${cName}记录
     */
	@PostMapping("update")
	public int update(@RequestBody ${upp} ${lowUpp}, Principal principal) {
		return bus.update(${lowUpp}); 
	}

    /**
     * @方法说明 按条件查询分页${cName}列表
     */
	@PostMapping("queryPage")
	public Page<${upp}> queryPage(@RequestBody ${upp}Cond cond, Principal principal){
		//cond.setBranch_id(getDefaultBranchId(principal));
		return bus.queryPage(cond);
	}

    /**
     * @方法说明 按条件查询不分页${cName}列表
     */
    @PostMapping("queryList")
    public List<${upp}> queryList(@RequestBody ${upp}Cond cond, Principal principal){
        //cond.setBranch_id(getDefaultBranchId(principal));
        return bus.queryList(cond);
    }

    /**
     * @方法说明 按主键查找单个${cName}记录
     */
	@GetMapping("findById")
	public ${upp} findById(@RequestParam("id") ${idType} id) {
		return bus.findById(id);
	}

    /**
     * @方法说明 按条件查询${cName}记录个数
     */
	@PostMapping("queryCount")
	public long queryCount(@RequestBody ${upp}Cond cond){
		return bus.queryCount(cond);
	}
}