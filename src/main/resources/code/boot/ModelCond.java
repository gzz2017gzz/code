package ${pName};
<#list importList as item>${item}</#list>
import lombok.Getter;
import lombok.Setter;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
//import lombok.experimental.Accessors;
import com.gzz.common.base.BaseCondition;
<#if swagger == 1>
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
</#if>
/**
 * @类说明 [${cName}]查询条件实体
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
		add(${fi.name}, "AND t.${fi.name} LIKE ?", 3);
<#else>
		add(${fi.name}, "AND t.${fi.name} = ?");
</#if>
</#list>
    	// add(ids, "AND t.id IN ");
    }
    // 以下为查询条件
<#list fList as fi>
<#if swagger == 1>
	@ApiModelProperty(hidden = true)
</#if>	
	/**
	 * ${fi.comment}
	 */
	private ${fi.type} ${fi.name};
</#list>
	// private List<Long> ids;// 主键列表
	// 以下为自定义查询条件
}