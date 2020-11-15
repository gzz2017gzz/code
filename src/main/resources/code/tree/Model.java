package ${pName};
${importList}
import java.util.List;
import lombok.Getter;
import lombok.Setter;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
<#if swagger == 1>
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
</#if>
/**
 * @类说明 【${cName}】实体
 * @author ${auth}
 * @date ${time}
 **/
@Setter
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
<#if swagger == 1>
@ApiModel(description = "【${cName}】实体")
</#if>
public class ${upp} {
	// 以下为数据库中 字段
<#list fList as fi>
<#if swagger == 1>
	@ApiModelProperty("${fi.comment}")
</#if>	
	private ${fi.type} ${fi.name}; // ${fi.comment}
</#list>
	// 以下为查询显示辅助属性
	private List<${upp}> children ;
}