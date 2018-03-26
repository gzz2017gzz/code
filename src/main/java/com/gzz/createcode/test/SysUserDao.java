package com.gzz.createcode.test;
import java.util.List;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.stereotype.Repository;

import com.gzz.createcode.common.base.BaseDao;
import com.gzz.createcode.common.base.Page;
import com.gzz.createcode.common.base.SqlUtil;

/**
 * @类说明:测试用户数据访问层
 * @author:高振中
 * @date:2018-03-21 09:09:51
 **/
@Repository
public class SysUserDao extends BaseDao{
	private StringBuilder select = new StringBuilder();
	private StringBuilder insert = new StringBuilder();

	/**
	 * @方法说明:构造方法,用于拼加SQL及初始化工作
	 **/
	public SysUserDao () {
		select.append("SELECT t.id,t.birthday,t.giving,t.enable,t.name");
		select.append(" FROM center_sys_user t WHERE 1=1");

		insert.append("INSERT INTO center_sys_user (birthday,giving,enable,name)");
		insert.append(" VALUES (:birthday,:giving,:enable,:name)");
	}

	/**
	 * @方法说明:新增测试用户记录
	 **/
	public int save(SysUser vo) {
		StringBuilder sql = new StringBuilder();
		sql.append("REPLACE INTO center_sys_user (id,birthday,giving,enable,name)");
		sql.append(" VALUES (?,?,?,?,?) ");
		Object[] params ={vo.getId(),vo.getBirthday(),vo.getGiving(),vo.getEnable(),vo.getName()};
		logger.info(SqlUtil.showSql(sql.toString(), params));//显示SQL语句
		return jdbcTemplate.update(sql.toString(), params);
	}

	/**
	 * @方法说明:新增测试用户记录并返回自增涨主键值
	 **/
	public long saveReturnPK(SysUser vo) {
		return saveKey(vo, insert.toString(), "id");
	}

	/**
	 * @方法说明:批量插入测试用户记录
	 **/
	public int[] insertBatch(List<SysUser> list) {
		return batchOperate(list, insert.toString());
	}

	/**
	 * @方法说明:物理删除测试用户记录(多条)
	 **/
	public int delete(Long ids[]) {
		String sql = "DELETE FROM center_sys_user WHERE id" + SqlUtil.ArrayToIn(ids);
		return jdbcTemplate.update(sql);
	}

	/**
	 * @方法说明:按ID查找单个测试用户实体
	 **/
	public SysUser findById(Long id) {
		StringBuilder sb = new StringBuilder(select);
		sb.append(" AND t.id=?");
		return jdbcTemplate.queryForObject(sb.toString(), new Object[]{id}, new BeanPropertyRowMapper<>(SysUser.class));
	}

	/**
	 * @方法说明:更新测试用户记录
	 **/
	public int update(SysUser vo) {
		StringBuilder sql = new StringBuilder();
		sql.append("UPDATE center_sys_user SET birthday=?,giving=?,enable=?,name=?");
		sql.append(" WHERE id=? ");
		Object[] params = {vo.getBirthday(),vo.getGiving(),vo.getEnable(),vo.getName(),vo.getId()};
		return jdbcTemplate.update(sql.toString(), params);
	}

	/**
	 * @方法说明:按条件查询分页测试用户列表
	 **/
	public Page<SysUser> queryPage(SysUserCond cond) {
		StringBuilder sb = new StringBuilder(select);
		sb.append(cond.getCondition());
		//sb.append(cond.getOrderSql());//增加排序子句;
		//logger.info(SqlUtil.showSql(sb.toString(),cond.getArray()));//显示SQL语句
		return queryPage(sb.toString(), cond, SysUser.class);
	}

	/**
	 * @方法说明:按条件查询不分页测试用户列表
	 **/
	public List<SysUser> queryList(SysUserCond cond) {
		StringBuilder sb = new StringBuilder(select);
		sb.append(cond.getCondition());
		//sb.append(" ORDER BY operate_time DESC");
		return jdbcTemplate.query(sb.toString(), cond.getArray(), new BeanPropertyRowMapper<>(SysUser.class));
	}

	/**
	 * @方法说明:按条件查询测试用户记录个数
	 **/
	public long queryCount(SysUserCond cond) {
		String countSql = "SELECT COUNT(1) FROM center_sys_user t WHERE 1=1" + cond.getCondition();
		return jdbcTemplate.queryForObject(countSql, cond.getArray(), Long.class);
	}

	/**
	 * @方法说明:逻辑删除测试用户记录(多条)
	 **/
	public int deleteLogic(Long ids[]) {
		String sql = "UPDATE center_sys_user SET delete_remark=1 WHERE id" + SqlUtil.ArrayToIn(ids);
		return jdbcTemplate.update(sql);
	}
}