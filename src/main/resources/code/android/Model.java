package ${pName};
<#list importList as item>${item}</#list>

/**
 * @类说明 ${cName}--实体类
 * @auth  ${auth}
 * @date  ${time}
 **/
public class ${upp}{
	<#list fList as fi>
    private ${fi.type}  ${fi.name} ;//  ${fi.comment}
    </#list>

    //以下为护展属性
    
    <#list fList as fi>
    public ${fi.type} get${fi.bigName} (){
    	return this.${fi.name};
    }

    public void set${fi.bigName}(${fi.type} ${fi.name}) {
    	this.${fi.name} = ${fi.name};
    }
    </#list>
}