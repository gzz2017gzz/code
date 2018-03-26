package com.gzz.createcode.test;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Getter;
import lombok.Setter;

/**
 * @类说明:测试用户实体类
 * @author:高振中
 * @date:2018-03-21 09:09:51
 **/
@Setter
@Getter
public class SysUser {

	//数据库中的字段
	private Long id;// 主键
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	private Date birthday;// 生日
	private Float giving;// 赠送金额
	private Byte enable;// 状态
	private String name;// 储值卡名称

	//此处可添加查询显示辅助字段

}