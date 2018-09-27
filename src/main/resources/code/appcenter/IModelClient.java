package ${pName};
import java.util.List;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import com.dl.keep.common.util.Page;

/**
 * @类说明 ${cName}--客户端类
 * @author ${auth}
 * @date  ${time}
 **/
@FeignClient("dl-keep-web-data/${lowUpp}")
public interface I${upp}Client {

    /**
     * @方法说明  新增[${cName}]记录
     */
	@PostMapping("save")
	int save(@RequestBody ${upp} ${lowUpp});

    /**
     * @方法说明  删除${cName}记录(多条)
     */
	@PostMapping("delete")
	int delete(@RequestParam("ids[]") ${idType} ids[]);

    /**
     * @方法说明  修改${cName}记录
     */
	@PostMapping("update")
	int update(@RequestBody ${upp} ${lowUpp});

    /**
     * @方法说明  按条件查询分页${cName}列表
     */
	@PostMapping("queryPage")
	Page<${upp}> queryPage(@RequestBody ${upp}Cond cond );

    /**
     * @方法说明  按条件查询不分页${cName}列表
     */
	@PostMapping("queryList")
	List<${upp}> queryList(@RequestBody ${upp}Cond cond );

    /**
     * @方法说明  按主键查单个${cName}记录
     */
	@PostMapping("findById")
	${upp} findById(@RequestParam("id") ${idType} id);

    /**
     * @方法说明  按条件查询${cName}记录个数
     */
	@PostMapping("queryCount")
	long queryCount(@RequestBody ${upp}Cond cond );

    /**
     * @方法说明  按条件查询分页${cName}列表
     */
    @PostMapping("queryPage")
    String queryPageString(@RequestBody ${upp}Cond cond );

    /**
     * @方法说明  按条件查询不分页${cName}列表
     */
    @PostMapping("queryList")
    String queryListString(@RequestBody ${upp}Cond cond );

    /**
     * @方法说明  按主键查单个${cName}记录
     */
    @PostMapping("findById")
    String findByIdString(@RequestParam("id") ${idType} id);

}