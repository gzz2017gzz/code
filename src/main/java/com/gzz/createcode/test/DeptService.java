package com.gzz.createcode.test;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.gzz.createcode.common.base.Page;

/**
 * @类说明:部门数据逻辑层
 * @author 高振中
 * @date:2018-09-21 14:45:16
 **/
@Service
public class DeptService {
	@SuppressWarnings("unused")
	private Log logger = LogFactory.getLog(getClass());
	@Autowired
	private DeptDao dao; //注入部门数据访问层

	/**
	 * @方法说明 新增部门记录
	 **/
	@Transactional
	public int save(Dept dept) {
		return dao.save(dept);
	}

	/**
	 * @方法说明 删除部门记录(多条)
	 **/
	public int delete(Long ids[]) {
		//return dao.deleteLogic(ids);//逻辑删除
		return dao.delete(ids);//物理删除
	}

	/**
	 * @方法说明 更新部门记录
	 **/
	@Transactional
	public int update(Dept dept) {
		return dao.update(dept);
	}

	/**
	 * @方法说明 按条件查询分页部门列表
	 **/
	public Page<Dept> queryPage(DeptCond cond) {
		return dao.queryPage(cond);
	}

	/**
	 * @方法说明 按条件查询不分页部门列表(使用范型)
	 **/
	public List<Dept> queryList(DeptCond cond) {
		return dao.queryList(cond);
	}

	/**
	 * @方法说明 按ID查找单个部门记录
	 **/
//	public Dept findById(Long id) {
//		return dao.findById(id);
//	}

	/**
	 * @方法说明 按条件查询部门记录个数
	 **/
//	public long queryCount(DeptCond cond) {
//		return dao.queryCount(cond);
//	}
}