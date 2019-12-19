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
 * @author gzz_gzz@163.com
 * @date 2018-02-15
 */
@Scope("prototype")
public class BaseDao {
 
	@Autowired
	protected JdbcTemplate jdbcTemplate;
	@Autowired
	protected NamedParameterJdbcTemplate nameJdbcTemplate;

	protected <T, C extends BaseCondition> Page<T> queryPage(String sql, C cond, Class<T> clazz) {
		String countSQL = "SELECT count(1) FROM (" + sql + ") t";
		int rowCount = jdbcTemplate.queryForObject(countSQL, cond.getArray(), Integer.class);
		int pageSize = cond.getSize();
		int curPage = cond.getPage();
		int pageCount = rowCount % pageSize == 0 ? rowCount / pageSize : rowCount / pageSize + 1;
		String listSql = sql + " LIMIT " + curPage * pageSize + "," + pageSize;
		List<T> dataList = jdbcTemplate.query(listSql.toString(), cond.getArray(), new BeanPropertyRowMapper<T>(clazz));
		return new Page<T>(dataList, curPage, rowCount, pageSize, pageCount);
	}

	protected <T> int[] batchOperate(List<T> list, String sql) {
		return nameJdbcTemplate.batchUpdate(sql, list.stream().map(i -> new BeanPropertySqlParameterSource(i)).collect(Collectors.toList()).toArray(new BeanPropertySqlParameterSource[] {}));
	}

	protected <T> long saveKey(T t, String sql, String id) {
		KeyHolder keyHolder = new GeneratedKeyHolder();
		nameJdbcTemplate.update(sql, new BeanPropertySqlParameterSource(t), keyHolder, new String[] { id });
		return keyHolder.getKey().longValue();
	}
}