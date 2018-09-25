package com.gzz.createcode.mvc.service;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;

import org.springframework.stereotype.Component;

import com.google.common.collect.Lists;
import com.google.common.io.Files;

import freemarker.core.ParseException;
import freemarker.template.Configuration;
import freemarker.template.MalformedTemplateNameException;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import freemarker.template.TemplateNotFoundException;

@Component
public class FreemarkerUtils {
	private Configuration cfg = new Configuration(Configuration.VERSION_2_3_28);
	private Template template;

	@PostConstruct
	public void init() {
		cfg.setClassForTemplateLoading(this.getClass(), "/code/");
		cfg.setDefaultEncoding("UTF-8");

	}

	public void process(String templateName, Map<String, Object> params) {
		try {
			String[] split = templateName.split("/");
			File file = new File(params.get("path") + split[1].replace("Model", params.get("upp").toString()));
			template = cfg.getTemplate(templateName);
			Files.createParentDirs(file);
			template.process(params, Files.newWriter(file, Charset.forName("utf-8")));
		} catch (TemplateNotFoundException e) {
			e.printStackTrace();
		} catch (MalformedTemplateNameException e) {
			e.printStackTrace();
		} catch (ParseException e) {
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (TemplateException e) {
			e.printStackTrace();
		}

	}

	public List<String> getTemplates() {
		List<String> list = Lists.newArrayList();
		list.add("common/Model.java");
		list.add("common/ModelCond.java");

		list.add("webdata/ModelDao.java");
		list.add("webdata/ModelService.java");
		list.add("webdata/ModelController.java");
		return list;
	}

}
