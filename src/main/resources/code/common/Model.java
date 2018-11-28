package ${pName};
<#list importList as item>${item}</#list>
import lombok.Getter;
import lombok.Setter;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
//import lombok.experimental.Accessors;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * @类说明 [${cName}]实体类
 * @author ${auth}
 * @date ${time}
 **/
@Setter
@Getter
//@Accessors(chain = true)
@Builder	
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(value = "${upp}", description = "${cName}实体")
public class ${upp} {
    // 数据库中的字段
<#list fList as fi>
	@ApiModelProperty(value = "${fi.comment}", dataType = "${fi.type}")
	private ${fi.type} ${fi.name}; // ${fi.comment}
</#list>
    // 此处可添加查询显示辅助字段
}