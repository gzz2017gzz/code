package ${pName};

import java.util.List;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.stereotype.Repository;

import com.gzz.common.base.BaseDao;

/**
 * @类说明 【${cName}】数据访问层
 * @author ${auth}
 * @date ${time}
 **/
//@Slf4j
@Repository
public class ${upp}Dao extends BaseDao {

	/**
	 * @方法说明 新增【${cName}】记录
	 */
	public int save(${upp} vo) {
		StringBuilder sql = new StringBuilder();
		sql.append("INSERT INTO ${tName} (${insertFields})");
		sql.append(" VALUES (${insertValues})");
		Object[] params = {${insertParams} };
		// log.info(super.sql(sql.toString(), params));// 显示SQL语句
		return jdbcTemplate.update(sql.toString(), params);
	}

	/**
	 * @方法说明 删除【${cName}】记录
	 */
	public int delete(List<${idType}> ids) {
		String sql = "DELETE FROM ${tName} WHERE ${idName} IN" + toIn(ids);
		// log.info(super.sql(sql, ids));// 显示SQL语句
		return jdbcTemplate.update(sql, ids.toArray());
	}

	/**
	 * @方法说明 更新【${cName}】记录
	 */
	public int update(${upp} vo) {
		StringBuilder sql = new StringBuilder();
		sql.append("UPDATE ${tName} SET ${updateFields}");
		sql.append(" WHERE ${idName}=? ");
		Object[] params = {${updateValues} };
		// log.info(super.sql(sql.toString(), params));// 显示SQL语句
		return jdbcTemplate.update(sql.toString(), params);
	}

	/**
	 * @方法说明 按条件查询【${cName}】列表
	 */
	public List<${upp}> list(${upp}Cond cond) {
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT ${selectFields}");
		sql.append(" FROM ${tName} t");
		sql.append(cond.where());
		sql.append(" ORDER BY ${idName}");
		// log.info(super.sql(sql.toString(),cond.array()));//显示SQL语句
		return jdbcTemplate.query(sql.toString(), cond.array(), new BeanPropertyRowMapper<>(${upp}.class));
	}

	/**
	 * @方法说明 按条件查询【${cName}】记录个数
	 */
	public int count(${upp}Cond cond) {
		String sql = "SELECT COUNT(1) FROM ${tName} t " + cond.where();
		// log.info(super.sql(sql,cond.array()));//显示SQL语句
		return jdbcTemplate.queryForObject(sql, cond.array(), Integer.class);
	}
}