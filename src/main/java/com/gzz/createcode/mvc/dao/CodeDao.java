package com.gzz.createcode.mvc.dao;

import java.util.List;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.stereotype.Repository;

import com.gzz.createcode.common.base.BaseDao;
import com.gzz.createcode.common.base.SqlUtil;
import com.gzz.createcode.mvc.model.CodeCond;
import com.gzz.createcode.mvc.model.Field;
import com.gzz.createcode.mvc.model.Table;

import lombok.extern.slf4j.Slf4j;

/**
 * @author gzz_gzz@163.com
 * @功能描述 mysql数据访问类
 * @date 2018-02-15
 */
@Slf4j
@Repository
public class CodeDao extends BaseDao {

	/** @功能描述 查询表名列表 */
	public List<Table> queryTables(CodeCond cond) {
		StringBuilder sb = new StringBuilder();
		sb.append("SELECT table_name t_name,if(table_comment='',table_name,table_comment) comment");
		sb.append(" FROM information_schema.tables WHERE table_schema=(SELECT DATABASE())");
		log.info(sb.toString());
		return jdbcTemplate.query(sb.toString(), new BeanPropertyRowMapper<>(Table.class));
	}

	/** @功能描述 查询字段名列表 */
	public List<Field> queryFields(CodeCond cond) {
		StringBuilder sb = new StringBuilder();
		sb.append("SELECT COLUMN_NAME NAME,");
		sb.append(" CASE WHEN COLUMN_COMMENT = '' THEN COLUMN_NAME ELSE COLUMN_COMMENT END COMMENT,");
		sb.append(" CASE WHEN DATA_TYPE='varchar' OR DATA_TYPE='text' OR DATA_TYPE='char' OR DATA_TYPE='longtext' OR DATA_TYPE='mediumtext' THEN 'String'");
		sb.append(" WHEN DATA_TYPE = 'tinyint' THEN 'Byte'");
		sb.append(" WHEN DATA_TYPE = 'smallint' THEN 'Short'");
		sb.append(" WHEN DATA_TYPE = 'int' OR DATA_TYPE = 'mediumint' THEN 'Integer'");
		sb.append(" WHEN DATA_TYPE = 'datetime' OR DATA_TYPE = 'timestamp' OR DATA_TYPE = 'date' OR DATA_TYPE = 'time' THEN 'Date'");
		sb.append(" WHEN DATA_TYPE = 'bigint' THEN 'Long'");
		sb.append(" WHEN DATA_TYPE = 'float' THEN 'Float'");
		sb.append(" WHEN DATA_TYPE = 'double' THEN 'Double'");
		sb.append(" WHEN DATA_TYPE = 'decimal' THEN 'BigDecimal'");
		sb.append(" WHEN DATA_TYPE = 'boolean' OR DATA_TYPE = 'bit' THEN 'Boolean'");
		sb.append(" ELSE CONCAT ('无效数据类型', DATA_TYPE) END type,CHARACTER_MAXIMUM_LENGTH length");
		sb.append(" FROM INFORMATION_SCHEMA.COLUMNS WHERE table_schema=(SELECT DATABASE())");
		sb.append(cond.and());
		sb.append(" ORDER BY ORDINAL_POSITION");
		log.info(SqlUtil.showSql(sb.toString(), cond.getArray()));
		return jdbcTemplate.query(sb.toString(), cond.getArray(), new BeanPropertyRowMapper<Field>(Field.class));
	}

}
