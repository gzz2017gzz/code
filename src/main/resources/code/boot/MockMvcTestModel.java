package ${pName};

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import com.fasterxml.jackson.databind.ObjectMapper;
/**
 * @类说明 [请求日志]测试工具，将本类移到maven测试目录中或测试完成之后删除
 * @author 高振中
 * @date 2019-01-12 22:40:08
 **/
@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class MockMvcTest${upp} {
	private Log logger = LogFactory.getLog(getClass());
	@Autowired
	private MockMvc mvc;
	/**
	 * @方法说明 测试 新增[${cName}]记录,根据数据类型修改每个字段的值
	 */
 	//@Test
	public void save() throws Exception {
 		${upp} ${lowUpp} = ${upp}.builder()
 		<#list fList as fi>
 		//.${fi.name}("http://www.gaozz.club") // 设置【${fi.comment}】的值
 		</#list>
 		.build();
 
		MvcResult result = mvc.perform(post("/${lowUpp}/save")
				.contentType(MediaType.APPLICATION_JSON)
				.content(new ObjectMapper().writeValueAsString(sysLog))).andExpect(status().isOk()) 
 				.andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8)) 
				.andReturn(); 
		logger.info(result.getResponse().getContentAsString());
	}
	/**
	 * @方法说明 测试 查询[${cName}]列表,条件可以为空,可直接运行
	 */
	@Test
	public void queryList() throws Exception {
		${upp}Cond cond = ${upp}Cond.builder()
 		<#list fList as fi>
 		//.${fi.name}("http://www.gaozz.club")  // 设置查询条件【${fi.comment}】的值
 		</#list>	
		.build();
		MvcResult result = mvc.perform(post("/${lowUpp}/queryList")
				.contentType(MediaType.APPLICATION_JSON)
				.content(new ObjectMapper().writeValueAsString(cond))).andExpect(status().isOk()) 
				.andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8)) 
				.andReturn();// 返回执行请求的结果
		logger.info(result.getResponse().getContentAsString());
	}
	/**
	 * @方法说明 测试 查询[${cName}]分页列表,条件可以为空,可直接运行
	 */
	@Test
	public void queryPage() throws Exception {
		${upp}Cond cond = ${upp}Cond.builder()
 		<#list fList as fi>
 		//.${fi.name}("http://www.gaozz.club")  // 设置查询条件【${fi.comment}】的值
 		</#list>	
		.build();
		MvcResult result = mvc.perform(post("/${lowUpp}/queryPage")
				.contentType(MediaType.APPLICATION_JSON)
				.content(new ObjectMapper().writeValueAsString(cond))).andExpect(status().isOk()) 
				.andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8)) 
				.andReturn();// 返回执行请求的结果
		logger.info(result.getResponse().getContentAsString());
	}
}