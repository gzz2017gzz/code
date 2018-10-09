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
