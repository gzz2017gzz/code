package ${pName};
<#list importList as item>${item}</#list>
import java.util.List;

import com.gzz.common.base.BaseCondition;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
<#if swagger == 1>
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
</#if>

/**
 * @类说明 【${cName}】查询条件实体
 * @author ${auth}
 * @date ${time}
 **/
@Setter
@Getter
//@Accessors(chain = true)
@Builder
@AllArgsConstructor
@NoArgsConstructor
<#if swagger == 1>
@ApiModel(value = "${upp}Cond", description = "${cName}查询条件实体")
</#if>
public class ${upp}Cond extends BaseCondition {

	/**
	 * @方法说明: 拼加自定义条件
	 **/
	@Override
	public void addCondition() {
<#list fList as fi>
<#if fi.type == "String">
		add("AND t.${fi.name} LIKE ?", ${fi.name}, 3);
<#else>
		add("AND t.${fi.name} = ?", ${fi.name});
</#if>
</#list>
		add("AND t.id IN", ids);
	}

	// 以下为查询条件
<#list fList as fi>
<#if swagger == 1>
	@ApiModelProperty(hidden = true)
</#if>
	private ${fi.type} ${fi.name}; // ${fi.comment}
</#list>
	private List<Object> ids;// 主键列表
	// 以下为自定义查询条件
}