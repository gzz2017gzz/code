package com.gzz.createcode.mvc.model;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class Field {
	private String name;// 字段名
	private String comment;// 注释
	private String type;// 数据类型
 	private String bigName; // 首字母大写
	private Long length; // 字符型的长度
	private String camel; // 首字母大写
	
}
