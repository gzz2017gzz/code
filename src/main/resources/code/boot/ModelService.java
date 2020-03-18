package ${pName};
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gzz.common.base.Page;

/**
 * @类说明 【${cName}】业务逻辑层
 * @author ${auth}
 * @date ${time}
 **/
//@Slf4j
@Service
public class ${upp}Service {

	@Autowired
	private ${upp}Dao ${lowUpp}Dao; // 注入【${cName}】数据访问层

	/**
	 * @方法说明 新增【${cName}】记录
	 */
	// @Transactional
	public int save(${upp} ${lowUpp}) {
		return ${lowUpp}Dao.save(${lowUpp});
	}

	/**
	 * @方法说明 删除【${cName}】记录
	 */
	public int delete(${idType}[] ids) {
		return ${lowUpp}Dao.delete(ids);
	}

	/**
	 * @方法说明 更新【${cName}】记录
	 */
	public int update(${upp} ${lowUpp}) {
		return ${lowUpp}Dao.update(${lowUpp});
	}

	/**
	 * @方法说明 按条件查询分页【${cName}】列表
	 */
	public Page<${upp}> page(${upp}Cond cond) {
		return ${lowUpp}Dao.page(cond);
	}

	/**
	 * @方法说明 按条件查询【${cName}】列表
	 */
	public List<${upp}> list(${upp}Cond cond) {
		return ${lowUpp}Dao.list(cond);
	}

	/**
	 * @方法说明 按主键查找单个【${cName}】记录
	 */
	public ${upp} findById(${idType} id) {
		return ${lowUpp}Dao.findById(id);
	}

	/**
	 * @方法说明 按条件查询【${cName}】记录个数
	 */
	public int count(${upp}Cond cond) {
		return ${lowUpp}Dao.count(cond);
	}
}