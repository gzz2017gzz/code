package com.createcode.template.vue;

import java.util.List;

import com.createcode.common.CodeUtil;
import com.createcode.mvc.model.Field;

public class VueMockJs {
	public static StringBuilder genSB(List<Field> fList, String clsLow, String cName, String auth) {
		StringBuilder sb = new StringBuilder();
		StringBuilder columdata = new StringBuilder();
		for (Field field : fList) {
			String type = field.getType();
			String name = field.getName().toLowerCase();
			String comments = field.getComment();
			if (type.equals("String"))
				columdata.append("\r\n        " + name + ": \"@word(5,10)\",//" + comments);
			else
				columdata.append("\r\n        " + name + ": \"@integer(100,200)\",//" + comments);
		}
		sb.append(CodeUtil.vueHead(cName + "模拟数据", auth));
		sb.append("\r\n'use strict';");
		sb.append("\r\nvar Mock = require('mockjs')");
		sb.append("\r\nvar Random = Mock.Random;");
		sb.append("\r\nmodule.exports = {");
		sb.append("\r\n");
		sb.append("\r\n  'POST /api/" + clsLow + "/queryPage': function (req, res, next) {");
		sb.append("\r\n    var data = Mock.mock({");
		sb.append("\r\n      \"content|10\": [{");
		sb.append(columdata);
		sb.append("\r\n      }],");
		sb.append("\r\n      \"number\": '@integer(100,200)',");
		sb.append("\r\n      \"size\": 10,");
		sb.append("\r\n      \"totalElements\": 500,");
		sb.append("\r\n    });");
		sb.append("\r\n    setTimeout(function () {");
		sb.append("\r\n      res.json(data);");
		sb.append("\r\n    }, 500);");
		sb.append("\r\n  },");
		sb.append("\r\n  'POST /api/" + clsLow + "/update': function (req, res, next) {");
		sb.append("\r\n    setTimeout(function () {");
		sb.append("\r\n      res.json({});");
		sb.append("\r\n    }, 500);");
		sb.append("\r\n  },");
		sb.append("\r\n  'POST /api/" + clsLow + "/save': function (req, res, next) {");
		sb.append("\r\n    setTimeout(function () {");
		sb.append("\r\n      res.json({});");
		sb.append("\r\n    }, 500);");
		sb.append("\r\n  },");
		sb.append("\r\n  'POST /api/" + clsLow + "/queryList': function (req, res, next) {");
		sb.append("\r\n    var data = Mock.mock({");
		sb.append("\r\n      \"content|10\": [{");
		sb.append(columdata);
		sb.append("\r\n      }]");
		sb.append("\r\n    });");
		sb.append("\r\n    setTimeout(function () {");
		sb.append("\r\n      res.json(data.content);");
		sb.append("\r\n    }, 500);");
		sb.append("\r\n  },");
		sb.append("\r\n  'DELETE /api/" + clsLow + "/delete': function (req, res, next) {");
		sb.append("\r\n    setTimeout(function () {");
		sb.append("\r\n      res.json({});");
		sb.append("\r\n    }, 500);");
		sb.append("\r\n  },");
		sb.append("\r\n}");

		return sb;
	}
}
