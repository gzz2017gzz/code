package com.gzz.createcode.test;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

/**
 * @类说明:测试用户实体类
 * @author:高振中
 * @date:2018-03-21 09:09:51
 **/
@Setter
@Getter
@ApiModel(value = "SysUser", description = "用户实体")
public class SysUser {

	// 数据库中的字段

	@ApiModelProperty(value = "用户主键", hidden = true)
	private Long id;// 主键
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	@ApiModelProperty(value = "生日", dataType = "Date")
	private Date birthday;// 生日
	@ApiModelProperty(value = "赠送金额", dataType = "Float")
	private Float giving;// 赠送金额
	@ApiModelProperty(value = "状态", dataType = "Byte")
	private Byte enable;// 状态
	@ApiModelProperty(value = "储值卡名称", dataType = "String")
	private String name;// 储值卡名称

	// 此处可添加查询显示辅助字段

}