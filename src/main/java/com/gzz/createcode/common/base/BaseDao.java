package com.gzz.createcode.common.base;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

/**
 * @功能描述 dao类公共类
 * @author https://www.jianshu.com/u/3bd57d5f1074
 * @date 2020-02-02 02:20:20
 */
@Scope("prototype")
public class BaseDao {

	@Autowired
	protected JdbcTemplate jdbcTemplate;
	@Autowired
	protected NamedParameterJdbcTemplate nameJdbcTemplate;

	/**
	 * @功能描述 分类查询
	 */
	protected final <T, C extends BaseCondition> Page<T> queryPage(final String sql, C cond, final Class<T> clazz) {
//		String countSQL = "SELECT count(1) FROM (" + sql + ") t";//支持要分页的句中有嵌套select .... from,效率低
		String countSQL = sql.replaceAll("(?i)(SELECT)(.*)(?i)(FROM)", "$1 count(1) $3");//不支持要分页的句中有嵌套select .... from,效率高
		int rowCount = jdbcTemplate.queryForObject(countSQL, cond.getArray(), Integer.class);
		int pageSize = cond.getSize();
		int curPage = cond.getPage();
		int pageCount = rowCount % pageSize == 0 ? rowCount / pageSize : rowCount / pageSize + 1;
		String listSql = sql + " LIMIT " + curPage * pageSize + "," + pageSize;
		List<T> dataList = jdbcTemplate.query(listSql.toString(), cond.getArray(), new BeanPropertyRowMapper<T>(clazz));
		return new Page<T>(dataList, pageSize, rowCount, curPage, pageCount);
	}

	/**
	 * @功能描述 批操作
	 */
	protected final <T> int[] batchOperate(final List<T> list, final String sql) {
		return nameJdbcTemplate.batchUpdate(sql, list.stream().map(i -> new BeanPropertySqlParameterSource(i)).collect(Collectors.toList()).toArray(new BeanPropertySqlParameterSource[] {}));
	}

	/**
	 * @功能描述 保存数据返回主键
	 */
	protected final <T> long saveKey(final T t, String sql, final String id) {
		KeyHolder keyHolder = new GeneratedKeyHolder();
		nameJdbcTemplate.update(sql, new BeanPropertySqlParameterSource(t), keyHolder, new String[] { id });
		return keyHolder.getKey().longValue();
	}
}