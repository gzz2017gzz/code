package ${pName};
<#list importList as item>${item}</#list>
import lombok.Getter;
import lombok.Setter;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
/**
 * @类说明 [${cName}]DTO对象
 * @author ${auth}
 * @date ${time}
 **/
@Setter
@Getter
@Accessors(chain = true)
@Builder	
@AllArgsConstructor
@NoArgsConstructor
public class ${upp}DTO{
	<#list fList as fi>
    /**
     * ${fi.comment}
     */
    private ${fi.type} ${fi.name};
    </#list>
}