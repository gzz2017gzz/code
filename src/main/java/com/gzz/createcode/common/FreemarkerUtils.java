package com.gzz.createcode.common;

import java.io.File;
import java.nio.charset.Charset;
import java.util.List;
import java.util.Map;
import javax.annotation.PostConstruct;
import org.springframework.stereotype.Component;
import com.google.common.io.Files;
import freemarker.template.Configuration;
import freemarker.template.Template;

@Component
public class FreemarkerUtils {
	private Configuration cfg = new Configuration(Configuration.VERSION_2_3_28);
	private Template template;
	private List<String> templates;

	@PostConstruct
	public void init() {
		cfg.setClassForTemplateLoading(this.getClass(), "/code/");
		cfg.setDefaultEncoding("UTF-8");
		templates = Utils.scanTemplate("code");
//		'android/Model.java',
//		'android/ModelCond.java',
//		'android/ModelRequest.java',
//		'android/ModelService.java',
//		'appcenter/IModelClient.java',
//		'appcenter/ModelAction.java',
//		'appcenter/ModelBus.java',
//		'common/Model.java',
//		'common/ModelCond.java',
//		'vuex_iview_ui/Model.js',
//		'vuex_iview_ui/ModelDialog.vue',
//		'vuex_iview_ui/ModelList.vue',
//		'vuex_iview_ui/ModelListExpand.vue',
//		'vuex_iview_ui/ModelRouter.js',
//		'vue_element_ui/ModelDialog.vue',
//		'vue_element_ui/ModelList.vue',
//		'vue_element_ui/ModelMock.js',
//		'vue_iview_ui/ModelDialog.vue',
//		'vue_iview_ui/ModelList.vue',
//		'vue_iview_ui/ModelListExpand.vue',
//		'webcenter/IModelClient.java',
//		'webcenter/ModelAction.java',
//		'webcenter/ModelBus.java',
//		'webdata/ModelController.java',
//		'webdata/ModelDao.java',
//		'webdata/ModelService.java',

	}

	public void process(String templateName, Map<String, Object> params) {
		try {
			String[] split = templateName.split("/");
			File file = new File(params.get("path") + split[1].replace("Model", params.get("upp").toString()));
			template = cfg.getTemplate(templateName);
			Files.createParentDirs(file);
			template.process(params, Files.newWriter(file, Charset.forName("utf-8")));
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public List<String> getTemplates() {
		return templates;
	}

}
