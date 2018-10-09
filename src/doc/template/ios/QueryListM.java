package com.gzz.createcode.template.ios;

import java.util.List;

import com.gzz.createcode.mvc.model.Field;

public class QueryListM {
	public static StringBuilder create(String upp, List<Field> fList, String auth, String cName) {
		StringBuilder sb = new StringBuilder();
		sb.append("\r\n#import \"" + upp + "QueryList.h\"");
		sb.append("\r\n");
		sb.append("\r\n@implementation QueryList");
		sb.append("\r\n+(NSDictionary<NSString *,id> *)modelContainerPropertyGenericClass{");
		sb.append("\r\n    return @{@\"data\":[QueryListDataModel class]};");
		sb.append("\r\n}");
		sb.append("\r\n@end");
		sb.append("\r\n");
		sb.append("\r\n");
		sb.append("\r\n@implementation " + upp + "QueryListData");
		sb.append("\r\n+(NSDictionary<NSString *,id> *)modelCustomPropertyMapper{");
		sb.append("\r\n    return @{@\"theID\":@\"id\"};");
		sb.append("\r\n}");
		sb.append("\r\n");
		sb.append("\r\n@end");
		return sb;
	}
}
