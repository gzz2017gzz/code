package com.gzz.createcode.test;
import java.util.Date;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * @类说明:部门实体类
 * @author 高振中
 * @date:2018-09-21 14:45:16
 **/
@Setter
@Getter
//@Accessors(chain = true)
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(value = "Dept", description = "部门实体")
public class Dept {

	//数据库中的字段
	@ApiModelProperty(value = "主键", dataType = "Long")
	private Long dept_id;
	@ApiModelProperty(value = "门店主键", dataType = "Long")
	private Long branch_id;
	@ApiModelProperty(value = "上级ID", dataType = "Long")
	private Long parent_id;
	@ApiModelProperty(value = "部门名称", dataType = "String")
	private String name;
	@ApiModelProperty(value = "级别", dataType = "Byte")
	private Byte level;
	@ApiModelProperty(value = "是否叶子", dataType = "Byte")
	private Byte leaf;
	@ApiModelProperty(value = "可用标志", dataType = "Byte")
	private Byte enable;
	@ApiModelProperty(value = "备注", dataType = "String")
	private String remark;
	//@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	@ApiModelProperty(value = "创建时间", dataType = "Date")
	private Date create_time;
	@ApiModelProperty(value = "创建人", dataType = "Long")
	private Long creator;

	//此处可添加查询显示辅助字段

}