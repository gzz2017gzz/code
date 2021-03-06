package ${pName};
${importList}
import com.gzz.common.util.BaseCondition;
import lombok.Getter;
import lombok.Setter;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

/**
 * @类说明 【${cName}】DTO对象
 * @author ${auth}
 * @date ${time}
 **/
@Setter
@Getter
@Accessors(chain = true)
@Builder	
@AllArgsConstructor
@NoArgsConstructor
public class ${upp}Cond extends BaseCondition  {
	<#list fList as fi>
	private ${fi.type} ${fi.name};/* -${fi.comment} */
	</#list>
}