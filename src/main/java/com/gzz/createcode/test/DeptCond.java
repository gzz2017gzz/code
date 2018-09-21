package com.gzz.createcode.test;
import java.util.Date;

import com.gzz.createcode.common.base.BaseCondition;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * @类说明:部门查询条件实体类
 * @author 高振中
 * @date:2018-09-21 14:45:16
 **/
@Setter
@Getter
//@Accessors(chain = true)
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(value = "Dept", description = "部门查询条件实体")
public class DeptCond extends BaseCondition {

	/**
	 * @方法说明 拼加自定义条件 
	 **/
	@Override
	public void addCondition() { 
		//add(dept_id, "AND t.dept_id = ?");
		//add(branch_id, "AND t.branch_id = ?");
		//add(parent_id, "AND t.parent_id = ?");
		//add(name, "AND t.name LIKE ?", 3);
		//add(level, "AND t.level = ?");
		//add(leaf, "AND t.leaf = ?");
		//add(enable, "AND t.enable = ?");
		//add(remark, "AND t.remark LIKE ?", 3);
		//add(create_time, "AND t.create_time = ?");
		//add(creator, "AND t.creator = ?");
		//add(ids, "AND t.id IN ");
	}

	//查询条件,把不用的条件清理掉
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
	@ApiModelProperty(value = "创建时间", dataType = "Date")
	private Date create_time;
	@ApiModelProperty(value = "创建人", dataType = "Long")
	private Long creator;
	//private List<Long> ids;// 主键列表

}