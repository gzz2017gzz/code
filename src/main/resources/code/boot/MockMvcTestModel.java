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
 * @类说明 [${cName}]测试工具，将本类移到maven测试目录中或测试完成之后删除
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
 		//.${fi.name}("gaozz") // 设置【${fi.comment}】的值
 		</#list>
 		.build();
		logger.info(doRequest("/${lowUpp}/save", ${lowUpp}));
	}
	/**
	 * @方法说明 测试 查询[${cName}]列表,条件可以为空,可直接运行
	 */
	@Test
	public void queryList() throws Exception {
		${upp}Cond cond = ${upp}Cond.builder()
 		<#list fList as fi>
 		//.${fi.name}("gaozz")  // 设置查询条件【${fi.comment}】的值
 		</#list>	
		.build();
		logger.info(doRequest("/${lowUpp}/queryList", cond));
	}
	/**
	 * @方法说明 测试 查询[${cName}]分页列表,条件可以为空,可直接运行
	 */
	@Test
	public void queryPage() throws Exception {
		${upp}Cond cond = ${upp}Cond.builder()
 		<#list fList as fi>
 		//.${fi.name}("gaozz")  // 设置查询条件【${fi.comment}】的值
 		</#list>	
		.build();
		// cond.setPage(0); //当前页
		// cond.setSize(10); //页大小
		logger.info(doRequest("/${lowUpp}/queryPage", cond));
	}
	
	private <T> String doRequest(String url, T t) throws Exception {// restController专用测试方法
		return mvc.perform(post(url).contentType(MediaType.APPLICATION_JSON).content(new ObjectMapper().writeValueAsString(t))).andExpect(status().isOk())
				.andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8)).andReturn().getResponse().getContentAsString();
	}
}