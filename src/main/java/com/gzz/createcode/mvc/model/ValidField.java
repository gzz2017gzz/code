package com.gzz.createcode.mvc.model;

import java.util.HashMap;
import java.util.Map;

import org.springframework.util.StringUtils;

public class ValidField {
	private static final Map<String, String> map = new HashMap<>();

	ValidField() {
		map.put("String", "\t//@Length(max = #) //字段长度");
		map.put("Long", "\t//@Max(9223372036854775807L) // bigint");
		map.put("Integer", "\t//@Max(2147483647) // int");
		map.put("Byte", "\t//@Max(127) // tinyint");
		map.put("Short", "\t//@Max(32767) // smallint");
	}

	public static String getValid(Field field) {
		String name = field.getName();
		String value = map.get(name);
		if (!StringUtils.isEmpty(value))
			return name.equals("String") ? value.replace("#", field.getLength().toString()) : value;
		return "";
	}
}
