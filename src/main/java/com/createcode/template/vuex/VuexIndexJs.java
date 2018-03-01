package com.createcode.template.vuex;

import java.util.List;

import com.createcode.common.CodeUtil;
import com.createcode.mvc.model.Field;

public class VuexIndexJs {
	public static StringBuilder genSB(List<Field> list, String className, String CNName, String author) {
		String clsName = className.toLowerCase();
		StringBuilder sb = new StringBuilder();
		sb.append(CodeUtil.vueHead(CNName, author));
		sb.append("\r\nimport Vue from 'vue'");
		sb.append("\r\nimport Vuex from 'vuex'");
		sb.append("\r\nimport app from './app';");
		sb.append("\r\nimport " + clsName + " from '../pages/" + clsName + "/" + className + "';");
		sb.append("\r\nVue.use(Vuex)");
		sb.append("\r\n");
		sb.append("\r\nexport default new Vuex.Store({");
		sb.append("\r\n  modules: {");
		sb.append("\r\n    app:app,");
		sb.append("\r\n    " + clsName + ":" + clsName + "");
		sb.append("\r\n  }");
		sb.append("\r\n})");
		return sb;
	}
}
