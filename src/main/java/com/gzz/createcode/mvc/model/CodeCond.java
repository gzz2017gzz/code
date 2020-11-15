package com.gzz.createcode.mvc.model;

import java.util.List;

import com.gzz.createcode.common.base.BaseCondition;
import com.gzz.createcode.common.utils.Utils;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class CodeCond extends BaseCondition {
	@Override
	public void addCondition() {
		add("AND table_name LIKE ?", t_name, 3);
		add("AND table_name = ?", t_name_eq);
	}

	private String t_name;// 表名模糊
	private String t_name_eq;// 表名等于
	private String company;// 公司名
	private String item_name;// 项目名
	private int swagger;// 使用
	private String model;// 模块名
	private String auth;// 作者名
	private List<Table> c_list;// 生成代码用的数据
	private String sql;

	public String pack(String app, String low) {
		app = app.equals("tree") ? item_name : app;
		return "com." + company + "." + app + "." + model + "." + low;
	}

	public String path(String app, String low) {
		app = app.equals("tree") ? item_name : app;
		return Utils.path() + "com/" + company + "/" + app + "/" + model + "/" + low + "/";
	}
}