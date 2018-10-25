package ${pName};
import java.util.List;
import java.util.stream.Collectors;
import com.dl.keep.common.util.Page;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
/**
 * @类说明 ${cName}--业务逻辑层
 * @author ${auth}
 * @date  ${time}
 **/
@Service
public class ${upp}Bus {

	@SuppressWarnings("unused")
	private Log logger = LogFactory.getLog(getClass());

	@Autowired
	private I${upp}Client client; //注入${cName}客户端类

	/**
	 * @方法说明  新增[${cName}]记录
	 */
	public int save(${upp} ${lowUpp}) {
		return client.save(${lowUpp});
	}

	/**
	 * @方法说明  删除${cName}记录(多条)
	 */
	public int delete(${idType} ids[]) {
		return client.delete(ids);//物理删除
	}

	/**
	 * @方法说明  更新${cName}记录
	 */
	public int update(${upp} ${lowUpp}) {
		return client.update(${lowUpp});
	}

	/**
	 * @方法说明  按条件查询分页${cName}列表
	 */
	public Page<${upp}> queryPage(${upp}Cond cond) {
		return client.queryPage(cond);
	}

	/**
	 * @方法说明  按条件查询不分页${cName}列表
	 */
	public List<${upp}> queryList(${upp}Cond cond) {
		return client.queryList(cond);
	}

	/**
	 * @方法说明 按条件查询${cName}记录个数
	 */
	public long queryCount(${upp}Cond cond) {
		return client.queryCount(cond);
	}

	/**
	 * @方法说明按主键查找单个${cName}记录
	 */
	public ${upp} findById(${idType} id) {
		return client.findById(id);
	}
}