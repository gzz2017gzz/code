package ${pName};
 
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.common.collect.Lists;


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
	public int delete(List<${idType}> ids) {
		return ${lowUpp}Dao.delete(ids);
	}

	/**
	 * @方法说明 更新【${cName}】记录
	 */
	public int update(${upp} ${lowUpp}) {
		return ${lowUpp}Dao.update(${lowUpp});
	}

	/**
	 * @方法说明 按条件查询【${cName}】列表
	 */
	public List<${upp}> list(${upp}Cond cond) {
		return ${lowUpp}Dao.list(cond);
	}

	/**
	 * @方法说明 按条件查询【${cName}】记录个数
	 */
	public int count(${upp}Cond cond) {
		return ${lowUpp}Dao.count(cond);
	}
	/**
	 * @方法说明 查询【功能】树
	 */
	public List<${upp}> tree() {
		List<${upp}> ${lowUpp}s = ${lowUpp}Dao.list(${upp}Cond.builder().build());
		Map<Integer, ${upp}> treeMap = new HashMap<>();
		List<${upp}> treeList = Lists.newArrayList();
		for (${upp} ${lowUpp} : ${lowUpp}s) {
			treeMap.put(${lowUpp}.getId(), ${lowUpp});
			if (${lowUpp}.getParentId() == 0) {
				treeList.add(${lowUpp});
			} else {
				if (treeMap.get(${lowUpp}.getParentId()).getChildren() == null)
					treeMap.get(${lowUpp}.getParentId()).setChildren(Lists.newArrayList());
				treeMap.get(${lowUpp}.getParentId()).getChildren().add(${lowUpp});// children
			}
		}
		treeMap.clear();
		return treeList;
	}
}