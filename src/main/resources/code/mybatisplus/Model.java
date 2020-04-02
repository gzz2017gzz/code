package ${pName};
<#list importList as item>${item}</#list>
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
/**
 * @类说明 【${cName}】实体类
 * @author ${auth}
 * @date ${time}
 **/
@Setter
@Getter
@Builder
@TableName(value = "${tName}")
@AllArgsConstructor
@NoArgsConstructor
public class ${upp} extends Model<${upp}> {
	private static final long serialVersionUID = 1L;
//	@NotNull(message = "客户主建(Id)不能为空(数值型)")
//	@NotEmpty(message = "客户名称(name)不能为空(字符型)")
//	@Length(max = 20, min = 10, message = "客户名称(name)长度在有10~20之间(字符型)")
//	@Max(value = 1000, message = "客户年龄(age)最大值是1000(数值型)")
//	@Min(value = 100, message = "客户年龄(age)最小值是100(数值型)")
//	@Pattern(regexp = "^\\d{10}$", message = "必须为10位数字(字符型)")
	<#list fList as fi>
	<#if fi_index == 0>
	@TableId(value = "${fi.name}", type = IdType.AUTO)
	private ${fi.type} ${fi.name}; /* -${fi.comment} */
	</#if>
	<#if fi_index != 0>
	private ${fi.type} ${fi.name}; /* -${fi.comment} */
	</#if>
	</#list>
}