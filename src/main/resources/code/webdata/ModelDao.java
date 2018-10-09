package ${pName};
import java.util.List;
import org.springframework.stereotype.Repository;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import com.gzz.createcode.common.base.BaseDao;
import com.gzz.createcode.common.base.Page;
import com.gzz.createcode.common.base.SqlUtil;
/**
 * @类说明 [${cName}]数据访问层
 * @author ${auth}
 * @date ${time}
 **/
@Repository
public class ${upp}Dao extends BaseDao{

    private StringBuilder insert = new StringBuilder();
    private StringBuilder select = new StringBuilder();

    /**
     * @方法说明 构造方法-拼加SQL 
     */
    public ${upp}Dao () {
    	select.append("SELECT ${selectFields}");
    	select.append(" FROM ${tName} t WHERE 1=1");
		
        insert.append("INSERT INTO ${tName} (${insertFields}) ");
        insert.append(" VALUES (${insertValuesFields})");
    }

    /**
     * @方法说明  新增${cName}记录
     */
    public int save(${upp} vo) {
        StringBuilder sql = new StringBuilder();
        sql.append("REPLACE INTO ${tName} (${replaceFields})");
        sql.append(" VALUES ${replaceValuesFields}");
        Object[] params ={ ${paramsFields} };
        //logger.info(SqlUtil.showSql(sql.toString(), params));//显示SQL语句
        return jdbcTemplate.update(sql.toString(), params);
    }
    
    /**
     * @方法说明 新增${cName}记录并返回自增涨主键值
     */
    public long saveReturnPK(${upp} vo) {
         return saveKey(vo, insert.toString(), "${idName}");
    }
    
    /**
     * @方法说明 批量插入${cName}记录
     */
    public int[] insertBatch(List<${upp}> list) {
       return batchOperate(list, insert.toString());
    }
    
    /**
     * @方法说明 物理删除${cName}记录(多条)
     */
    public int delete(${idType} ids[]) {
        String sql = "DELETE FROM ${tName} WHERE ${idName}" + SqlUtil.ArrayToIn(ids);
        return jdbcTemplate.update(sql);
    }
    
    /**
     * @方法说明 更新${cName}记录
     */
    public int update(${upp} vo) {
        StringBuilder sql = new StringBuilder();
        sql.append("UPDATE ${tName} SET ${updateFields} ");
        sql.append(" WHERE ${idName}=? ");
        Object[] params = {${updateParams}};
        return jdbcTemplate.update(sql.toString(), params);
      }

    /**
     * @方法说明 按条件查询分页${cName}列表
     */
    public Page<${upp}> queryPage(${upp}Cond cond) {
        StringBuilder sb = new StringBuilder(select);
        sb.append(cond.getCondition());
        //sb.append(cond.getOrderSql());//增加排序子句;
        //logger.info(SqlUtil.showSql(sb.toString(),cond.getArray()));//显示SQL语句
        return queryPage(sb.toString(), cond, ${upp}.class);
    }
    
    /**
     * @方法说明 按条件查询不分页${cName}列表
     */
    public List<${upp}> queryList(${upp}Cond cond) {
    	StringBuilder sb = new StringBuilder(select);
    	sb.append(cond.getCondition());
    	//sb.append(" ORDER BY operate_time DESC");
    	return jdbcTemplate.query(sb.toString(), cond.getArray(), new BeanPropertyRowMapper<>(${upp}.class));
    }
    
    /**
     * @方法说明 按ID查找单个${cName}实体
     */
    public ${upp} findById(${idType} id) {
    	StringBuilder sb = new StringBuilder(select);
    	sb.append(" AND t.${idName}=?");
    	return jdbcTemplate.queryForObject(sb.toString(), new Object[]{id}, new BeanPropertyRowMapper<>(${upp}.class));
    }
    
    /**
     * @方法说明 按条件查询${cName}记录个数
     */
    public long queryCount(${upp}Cond cond) {
    	String countSql = "SELECT COUNT(1) FROM ${tName} t WHERE 1=1" + cond.getCondition();
    	return jdbcTemplate.queryForObject(countSql, cond.getArray(), Long.class);
    }
    
    /**
     * @方法说明 按条件查询${cName}记录个数
     */
    public int deleteLogic(${idType} ids[]) {
    	String sql = "UPDATE ${tName} SET delete_remark=1 WHERE ${idName}" + SqlUtil.ArrayToIn(ids);
    	return jdbcTemplate.update(sql);
    }
 
}