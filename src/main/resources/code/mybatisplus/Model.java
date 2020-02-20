package ${pName};
<#list importList as item>${item}</#list>
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
/**
 * @类说明 [${cName}]实体类
 * @author ${auth}
 * @date ${time}
 **/
@Setter
@Getter
 
@TableName(value = "${tName}")
@Builder
 
public class ${upp} extends Model<${upp}> {
	private static final long serialVersionUID = 1L;
     <#list fList as fi>
    /**
     * ${fi.comment}
     */
   	<#if fi_index == 0>
   	@TableId(value = "${fi.name}", type = IdType.AUTO)
    private ${fi.type} ${fi.name};
   	</#if>
   	<#if fi_index != 0>
	@TableField(value = "${fi.name}", exist = true)
    private ${fi.type} ${fi.name};
   	</#if>

    </#list>

}