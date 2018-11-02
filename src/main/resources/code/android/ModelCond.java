package ${pName};

<#list importList as item>${item}</#list>
import com.gzz.createcode.common.base.BaseCondition;

/**
 * @类说明 ${cName}--查询条件实体
 * @aut ${auth}
 * @date ${time}
 **/
public class ${upp}Cond extends BaseCondition  {
<#list fList as fi>
    private ${fi.type} ${fi.name} ;// ${fi.comment}
</#list>

<#list fList as fi>
    public ${fi.type} get${fi.bigName} (){
        return this.${fi.name};
    }

    public void set${fi.bigName}(${fi.type} ${fi.name}) {
        this.${fi.name} = ${fi.name};
    }
</#list>
}