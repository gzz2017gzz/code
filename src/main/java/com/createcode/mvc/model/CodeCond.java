package com.createcode.mvc.model;

import java.util.List;

import com.createcode.common.base.BaseCondition;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class CodeCond extends BaseCondition {

	@Override
	public void addCondition() {
		add(t_name, "AND table_name LIKE ?", 3);
		add(t_name_eq, "AND table_name = ?");
		add(db_user, "AND table_schema=?");
	}

	private String t_name;
	private String t_name_eq;
	private String db_user;// 数据库用户名
	private String company;// 公司名
	private String prj;// 项目名
	private String model;// 模块名
	private String auth;// 作者名
	private List<Table> c_list;// 生成代码用的数据
}