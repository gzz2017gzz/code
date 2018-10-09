package ${pName};
import java.util.List;
import com.gzz.createcode.common.base.Page;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @类说明 [${cName}]业务逻辑层
 * @author ${auth}
 * @date ${time}
 **/
@Service
public class ${upp}Service {

	@SuppressWarnings("unused")
	private Log logger = LogFactory.getLog(getClass());
	@Autowired
	private ${upp}Dao dao; //注入${cName}数据访问层

	/**
	 * @方法说明 新增[${cName}]记录
	 */
	@Transactional
	public int save(${upp} ${lowUpp}) {
			return dao.save(${lowUpp});
	}

	/**
	 * @方法说明 删除${cName}记录(多条)
	 */
	public int delete(${idType} ids[]) {
		//return dao.deleteLogic(ids);//逻辑删除
		return dao.delete(ids);//物理删除
	}

	/**
	 * @方法说明 更新${cName}记录
	 */
	@Transactional
	public int update(${upp} ${lowUpp}) {
		return dao.update(${lowUpp}); 
	}

	/**
	 * @方法说明 按条件查询分页${cName}列表
	 */
	public Page<${upp}> queryPage(${upp}Cond cond) {
		return dao.queryPage(cond);
	}

	/**
	 * @方法说明  按条件查询不分页${cName}列表 
	 */
	public List<${upp}> queryList(${upp}Cond cond) {
		return dao.queryList(cond);
	}

	/**
	 * @方法说明 按主键查找单个${cName}记录
	 */
	public ${upp} findById(${idType} id) {
		return dao.findById(id);
	}

	/**
	 * @方法说明 按条件查询${cName}记录个数
	 */
	public long queryCount(${upp}Cond cond) {
		return dao.queryCount(cond);
	}
}