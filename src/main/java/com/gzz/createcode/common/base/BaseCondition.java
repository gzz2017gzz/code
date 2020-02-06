package com.gzz.createcode.common.base;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import lombok.Data;

/**
 * @功能说明 拼加页面查询条件的基础类
 * @author https://www.jianshu.com/u/3bd57d5f1074
 * @date 2019-12-24 10:50:00
 */
@Data
public abstract class BaseCondition {

	private static final List<Object> paramList = new ArrayList<>();// 参数值
	private static final StringBuffer condition = new StringBuffer();// 条件语句
	private int size = 10;//	页大小(每页记录条)
	private int page = 0;//		当前页码

	/**
	 * @功能说明: 拼加条件使用等于大于小于....运算符(String类型)
	 */
	protected void add(String value, String sql) {
		if (!StringUtils.isEmpty(sql) && !StringUtils.isEmpty(value)) {
			condition.append(" " + sql);
			paramList.add(value);
		}
	}

	/**
	 * @功能说明: 拼加条件使用等于大于小于....运算符(Short类型)
	 */
	protected void add(Short value, String sql) {
		if (value != null && !StringUtils.isEmpty(sql)) {
			condition.append(" " + sql);
			paramList.add(value);
		}
	}

	protected void add(Byte value, String sql) {
		if (value != null && !StringUtils.isEmpty(sql)) {
			condition.append(" " + sql);
			paramList.add(value);
		}
	}

	/**
	 * @功能说明 拼加条件使用等于大于小于....运算符(String类型)
	 */
	protected void add(Float value, String sql) {
		if (value != null && !StringUtils.isEmpty(sql)) {
			condition.append(" " + sql);
			paramList.add(value);
		}
	}

	/**
	 * @功能说明: 拼加条件使用等于大于小于....运算符(Long类型)
	 */

	protected void add(Long value, String sql) {
		if (value != null && !StringUtils.isEmpty(sql)) {
			condition.append(" " + sql);
			paramList.add(value);
		}
	}

	/**
	 * @功能说明: 拼加条件使用等于大于小于....运算符(Boolean类型)
	 */
	protected void add(Boolean value, String sql) {
		if (value != null && !StringUtils.isEmpty(sql)) {
			condition.append(" " + sql);
			paramList.add(value);
		}
	}

	/**
	 * @功能说明 拼加条件使用等于大于小于....运算符(BigDecimal类型)
	 */
	protected void add(BigDecimal value, String sql) {
		if (value != null && !StringUtils.isEmpty(sql)) {
			condition.append(" " + sql);
			paramList.add(value);
		}
	}

	/**
	 * @功能说明 拼加条件使用等于大于小于....运算符(Integer类型)
	 */
	protected void add(Integer value, String sql) {
		if (value != null && !StringUtils.isEmpty(sql)) {
			condition.append(" " + sql);
			paramList.add(value);
		}
	}

	/**
	 * @功能说明: 拼加条件使用等于大于小于....运算符(Date类型)
	 */
	protected void add(Date value, String sql) {
		if (value != null && !StringUtils.isEmpty(sql)) {
			condition.append(" " + sql);
			paramList.add(value);
		}
	}

	/**
	 * @功能说明 拼加条件
	 */
	protected void add(String sql) {
		if (null != sql && !"".equals(sql)) {
			condition.append(" " + sql);
		}
	}

	/**
	 * @功能 拼加条件in子句
	 */
	protected void add(List<Object> ids, String sql) {
		if (CollectionUtils.isEmpty(ids) && !StringUtils.isEmpty(sql)) {
			condition.append(" " + sql);
			condition.append(SqlUtil.ArrayToIn(ids.toArray()));
			paramList.addAll(ids);
		}
	}

	/**
	 * @功能说明 拼加条件使用like关键字模糊查询时
	 */
	protected void add(String value, String sql, int pos) {
		if (!StringUtils.isEmpty(sql) && !StringUtils.isEmpty(value)) {
			condition.append(" " + sql);
			if (pos == 1) {
				paramList.add("%" + value);
			} else if (pos == 2) {
				paramList.add(value + "%");
			} else if (pos == 3) {
				paramList.add("%" + value + "%");
			}
		}
	}

	/**
	 * @功能说明 将List转为数组
	 */
	public Object[] getArray() {
		return paramList.toArray();
	}

	/**
	 * @功能说明 取条件字符串
	 */

	public String getCondition() {
		condition.setLength(0); // 	清除查询条件
		paramList.clear();
		addCondition();
		return condition.toString();
	}

	/**
	 * @功能说明 拼加条件方法
	 */
	public abstract void addCondition();

}
