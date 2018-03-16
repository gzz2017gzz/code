package com.gzz.createcode.template.vuex;

import java.util.List;

import com.gzz.createcode.common.Utils;
import com.gzz.createcode.mvc.model.Field;

public class Expand {
	public static StringBuilder create(List<Field> fList, String cName, String auth) {
		StringBuilder sb = new StringBuilder();
		StringBuilder colum = new StringBuilder();
		fList.forEach(field -> {
			colum.append("\r\n      <i-col span=\"6\">");
			colum.append("\r\n      	<span class=\"expand-key\">" + field.getComment() + "</span>");
			colum.append("\r\n      	<span class=\"expand-value\">{{ row." + field.getName() + "}}</span>");
			colum.append("\r\n      </i-col>");
		});
		sb.append(Utils.pageNote(cName + "折叠扩展", auth));
		sb.append("\r\n<template>");
		sb.append("\r\n  <div>");
		sb.append("\r\n    <Row class=\"expand-row\">");
		sb.append(colum);
		sb.append("\r\n    </Row>");
		sb.append("\r\n  </div>");
		sb.append("\r\n</template>");
		sb.append("\r\n<script>");
		sb.append("\r\n  export default {");
		sb.append("\r\n    props: {row: Object}");
		sb.append("\r\n  };");
		sb.append("\r\n</script>");
		sb.append("\r\n<style scoped>");
		sb.append("\r\n  .expand-row {");
		sb.append("\r\n    margin-bottom: 6px;");
		sb.append("\r\n  }");
		sb.append("\r\n  .expand-key {");
		sb.append("\r\n    font-weight: bold;");
		sb.append("\r\n    line-height: 25px;");
		sb.append("\r\n  }");
		sb.append("\r\n  .expand-value {}");
		sb.append("\r\n</style>");
		return sb;
	}
}
