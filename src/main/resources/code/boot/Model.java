package ${pName};
<#list importList as item>${item}</#list>
import lombok.Getter;
import lombok.Setter;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
//import lombok.experimental.Accessors;
<#if swagger == 1>
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
</#if>
/**
 * @类说明 【${cName}】实体类
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
@ApiModel(value = "${upp}", description = "${cName}实体")
</#if>
public class ${upp} {
//	@NotNull(message = "客户主建(Id)不能为空(数值型)")
//	@NotEmpty(message = "客户名称(name)不能为空(字符型)")
//	@Length(max = 20, min = 10, message = "客户名称(name)长度在有10~20之间(字符型)")
//	@Max(value = 1000, message = "客户年龄(age)最大值是1000(数值型)")
//	@Min(value = 100, message = "客户年龄(age)最小值是100(数值型)")
//	@Pattern(regexp = "^\\d{10}$", message = "必须为10位数字(字符型[正规判断])")
    // 以下为数据库中 字段
<#list fList as fi>
<#if swagger == 1>
	@ApiModelProperty(value = "${fi.comment}", dataType = "${fi.type}")
</#if>	
	private ${fi.type} ${fi.name}; // ${fi.comment}
</#list>
    // 以下为查询显示辅助属性
}