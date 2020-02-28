package ${pName};

import java.util.List;
import org.springframework.stereotype.Repository;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import com.gzz.common.base.BaseDao;
import com.gzz.common.base.Page;
import com.gzz.common.base.SQLUnit;
import lombok.extern.slf4j.Slf4j;
/**
 * @类说明 【${cName}】数据访问层
 * @author ${auth}
 * @date ${time}
 **/
@Slf4j
@Repository
public class ${upp}Dao extends BaseDao{

//	private StringBuilder insert = new StringBuilder();
    private StringBuilder select = new StringBuilder();

    /**
     * @方法说明 构造方法-拼加SQL
     */
    public ${upp}Dao() {
    	select.append("SELECT ${selectFields}");
    	select.append(" FROM ${tName} t ");
    	//insert.append("INSERT INTO ${tName} (${insertFields}) ");
    	//insert.append(" VALUES (${insertValuesFields})");
    }

    /**
     * @方法说明  新增【${cName}】记录
     */
    public int save(${upp} vo) {
        StringBuilder sql = new StringBuilder();
        sql.append("INSERT INTO ${tName} (${replaceFields})");
        sql.append(" VALUES ${replaceValuesFields}");
        Object[] params ={ ${insertParams} };
        //log.info(super.sql(sql.toString(), params));//显示SQL语句
        return jdbcTemplate.update(sql.toString(), params);
    }
    
    /**
     * @方法说明 物理删除【${cName}】记录(多条)
     */
    public int delete(Object ids[]) {
        String sql = "DELETE FROM ${tName} WHERE ${idName} IN" + SQLUnit.toIn(ids);
        return jdbcTemplate.update(sql,ids);
    }
    
    /**
     * @方法说明 更新【${cName}】记录
     */
    public int update(${upp} vo) {
        StringBuilder sql = new StringBuilder();
        sql.append("UPDATE ${tName} SET ${updateFields}");
        sql.append(" WHERE ${idName}=? ");
        Object[] params = {${updateParams}};
        return jdbcTemplate.update(sql.toString(), params);
      }

    /**
     * @方法说明 按条件查询分页【${cName}】列表
     */
    public Page<${upp}> queryPage(${upp}Cond cond) {
        StringBuilder sb = new StringBuilder(select);
        sb.append(cond.where());
        log.info(super.sql(sb.toString(),cond.getArray()));//显示SQL语句
        return queryPage(sb.toString(), cond, ${upp}.class);
    }
    
    /**
     * @方法说明 按条件查询不分页【${cName}】列表
     */
    public List<${upp}> queryList(${upp}Cond cond) {
    	StringBuilder sb = new StringBuilder(select);
    	sb.append(cond.where());
    	//sb.append(" ORDER BY ${idName} DESC");
    	return jdbcTemplate.query(sb.toString(), cond.getArray(), new BeanPropertyRowMapper<>(${upp}.class));
    }
    
    /**
     * @方法说明 按ID查找单个【${cName}】实体
     */
	public ${upp} findById(${idType} id) {
		StringBuilder sb = new StringBuilder(select);
		sb.append(" WHERE t.${idName}=?");
		return jdbcTemplate.queryForObject(sb.toString(), new BeanPropertyRowMapper<>(${upp}.class),id);
	}
    
    /**
     * @方法说明 按条件查询【${cName}】记录个数
     */
	public long queryCount(${upp}Cond cond) {
		String countSql = "SELECT COUNT(1) FROM ${tName} t " + cond.where();
		return jdbcTemplate.queryForObject(countSql, cond.getArray(), Long.class);
	}
    
    /**
     * @方法说明 逻辑删除【${cName}】记录 
     */
//	public int delete(Object ids[]) {
//		String sql = "UPDATE ${tName} SET dr=1 WHERE ${idName} IN " + SQLUnit.toIn(ids);
//		return jdbcTemplate.update(sql,ids);
//	}
    
    /**
     * @方法说明 新增【${cName}】记录并返回自增涨主键值
     */
//	public long saveReturnPK(${upp} vo) {
//		return saveKey(vo, insert.toString(), "${idName}");
//	}
    
    /**
     * @方法说明 批量插入【${cName}】记录
     */
//	public int[] insertBatch(List<${upp}> list) {
//		return batchOperate(list, insert.toString());
//	}    
 
}