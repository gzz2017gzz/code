package com.gzz.createcode.test;
import java.util.List;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.stereotype.Repository;

import com.gzz.createcode.common.base.BaseDao;
import com.gzz.createcode.common.base.Page;
import com.gzz.createcode.common.base.SqlUtil;

/**
 * @类说明:部门数据访问层
 * @author 高振中
 * @date:2018-09-21 14:45:16
 **/
@Repository
public class DeptDao extends BaseDao{
	private StringBuilder select = new StringBuilder();
	private StringBuilder insert = new StringBuilder();

	/**
	 * @方法说明 构造方法,用于拼加SQL及初始化工作
	 **/
	public DeptDao () {
		select.append("SELECT t.dept_id,t.branch_id,t.parent_id,t.name,t.level,t.leaf,t.enable,t.remark,t.create_time,t.creator");
		select.append(" FROM base_dept t WHERE 1=1");

		insert.append("INSERT INTO base_dept (branch_id,parent_id,name,level,leaf,enable,remark,create_time,creator)");
		insert.append(" VALUES (:branch_id,:parent_id,:name,:level,:leaf,:enable,:remark,:create_time,:creator)");
	}

	/**
	 * @方法说明 新增部门记录
	 **/
	public int save(Dept vo) {
		StringBuilder sql = new StringBuilder();
		sql.append("REPLACE INTO base_dept (dept_id,branch_id,parent_id,name,level,leaf,enable,remark,create_time,creator)");
		sql.append(" VALUES (?,?,?,?,?,?,?,?,?,?) ");
		Object[] params ={vo.getDept_id(),vo.getBranch_id(),vo.getParent_id(),vo.getName(),vo.getLevel(),vo.getLeaf(),vo.getEnable(),vo.getRemark(),vo.getCreate_time(),vo.getCreator()};
		//logger.info(SqlUtil.showSql(sql.toString(), params));//显示SQL语句
		return jdbcTemplate.update(sql.toString(), params);
	}

	/**
	 * @方法说明 新增部门记录并返回自增涨主键值
	 **/
	public long saveReturnPK(Dept vo) {
		return saveKey(vo, insert.toString(), "dept_id");
	}

	/**
	 * @方法说明 批量插入部门记录
	 **/
	public int[] insertBatch(List<Dept> list) {
		return batchOperate(list, insert.toString());
	}

	/**
	 * @方法说明 物理删除部门记录(多条)
	 **/
	public int delete(Long ids[]) {
		String sql = "DELETE FROM base_dept WHERE dept_id" + SqlUtil.ArrayToIn(ids);
		return jdbcTemplate.update(sql);
	}

	/**
	 * @方法说明 更新部门记录
	 **/
	public int update(Dept vo) {
		StringBuilder sql = new StringBuilder();
		sql.append("UPDATE base_dept SET branch_id=?,parent_id=?,name=?,level=?,leaf=?,enable=?,remark=?,create_time=?,creator=?");
		sql.append(" WHERE dept_id=? ");
		Object[] params = {vo.getBranch_id(),vo.getParent_id(),vo.getName(),vo.getLevel(),vo.getLeaf(),vo.getEnable(),vo.getRemark(),vo.getCreate_time(),vo.getCreator(),vo.getDept_id()};
		return jdbcTemplate.update(sql.toString(), params);
	}

	/**
	 * @方法说明 按条件查询分页部门列表
	 **/
	public Page<Dept> queryPage(DeptCond cond) {
		StringBuilder sb = new StringBuilder(select);
		sb.append(cond.getCondition());
		//sb.append(cond.getOrderSql());//增加排序子句;
		//logger.info(SqlUtil.showSql(sb.toString(),cond.getArray()));//显示SQL语句
		return queryPage(sb.toString(), cond, Dept.class);
	}

	/**
	 * @方法说明 按条件查询不分页部门列表
	 **/
	public List<Dept> queryList(DeptCond cond) {
		StringBuilder sb = new StringBuilder(select);
		sb.append(cond.getCondition());
		//sb.append(" ORDER BY operate_time DESC");
		return jdbcTemplate.query(sb.toString(), cond.getArray(), new BeanPropertyRowMapper<>(Dept.class));
	}

	/**
	 * @方法说明 按ID查找单个部门实体
	 **/
//	public Dept findById(Long id) {
//		StringBuilder sb = new StringBuilder(select);
//		sb.append(" AND t.dept_id=?");
//		return jdbcTemplate.queryForObject(sb.toString(), new Object[]{id}, new BeanPropertyRowMapper<>(Dept.class));
//	}

	/**
	 * @方法说明 按条件查询部门记录个数
	 **/
//	public long queryCount(DeptCond cond) {
//		String countSql = "SELECT COUNT(1) FROM base_dept t WHERE 1=1" + cond.getCondition();
//		return jdbcTemplate.queryForObject(countSql, cond.getArray(), Long.class);
//	}

	/**
	 * @方法说明 逻辑删除部门记录(多条)
	 **/
//	public int deleteLogic(Long ids[]) {
//		String sql = "UPDATE base_dept SET delete_remark=1 WHERE dept_id" + SqlUtil.ArrayToIn(ids);
//		return jdbcTemplate.update(sql);
//	}
}