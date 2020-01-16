package com.gzz.createcode.common;

import java.io.File;
import java.nio.charset.Charset;
import java.util.List;
import java.util.Map;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.google.common.io.Files;

import freemarker.template.Configuration;
import freemarker.template.Template;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class FreemarkerUtils implements CommandLineRunner {
	private Configuration cfg = new Configuration(Configuration.VERSION_2_3_29);
	private Template template;
	private List<String> templates;

	@Override
	public void run(String... args) throws Exception {
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
			log.error("生成代码时出显异常!", e);
		}

	}

	public List<String> getTemplates() {
		return templates;
	}

}
