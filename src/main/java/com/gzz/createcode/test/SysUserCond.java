package com.gzz.createcode.test;

import java.util.Date;
import java.util.List;

import com.gzz.createcode.common.base.BaseCondition;

import lombok.Getter;
import lombok.Setter;

/**
 * @类说明:测试用户查询条件实体类
 * @author:高振中
 * @date:2018-03-21 09:09:51
 **/
@Setter
@Getter
public class SysUserCond extends BaseCondition {

	/**
	 * @方法说明:拼加自定义条件
	 **/
	@Override
	public void addCondition() {
		add(id, "AND t.id = ?");
		add(birthday, "AND t.birthday = ?");
		add(giving, "AND t.giving = ?");
		add(enable, "AND t.enable = ?");
		add(name, "AND t.name LIKE ?", 3);
		add(ids, "AND t.id IN ");
	}

	// 查询条件,把不用的条件清理掉
	private Long id;// 主键
	private Date birthday;// 生日
	private Float giving;// 赠送金额
	private Byte enable;// 状态
	private String name;// 储值卡名称
	private List<Long> ids;// 主键列表

}