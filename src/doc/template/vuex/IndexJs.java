package com.gzz.createcode.template.vuex;

public class IndexJs {
	public static StringBuilder create(String upp, String low, String lowUpp, String model) {
		StringBuilder sb = new StringBuilder();
		sb.append("import Vue from 'vue'");
		sb.append("\r\nimport Vuex from 'vuex'");
		sb.append("\r\nimport app from './app';");
		sb.append("\r\nimport " + upp + " from '../pages/" + model + "/" + low + "/" + upp + "';");
		sb.append("\r\nVue.use(Vuex)");
		sb.append("\r\nexport default new Vuex.Store({");
		sb.append("\r\n  modules: {");
		sb.append("\r\n    app:app,");
		sb.append("\r\n    " + lowUpp + ":" + upp + ",");
		sb.append("\r\n  }");
		sb.append("\r\n})");
		return sb;
	}
}
