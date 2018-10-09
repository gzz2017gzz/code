package com.gzz.createcode.template.ios;

import java.util.List;

import com.gzz.createcode.mvc.model.Field;

public class QueryListH {
	public static StringBuilder create(String upp, List<Field> fList, String auth, String cName) {
		StringBuilder sb = new StringBuilder();
		StringBuilder field = new StringBuilder();
		for (Field fi : fList) {
			String type = IOSUtil.getType(fi.getType());
			if (type.equals("NSString") || type.equals("NSDate"))
				field.append("\r\n@property (nonatomic,copy)" + type + " * " + fi.getName() + "; //" + fi.getComment());
			else
				field.append("\r\n@property (nonatomic,assign)" + type + " " + fi.getName() + "; //" + fi.getComment());

		}
		sb.append(IOSUtil.getNote(upp, auth));
		sb.append("\r\n#import <Foundation/Foundation.h>");
		sb.append("\r\n@class " + upp + "QueryListData;");
		sb.append("\r\n@interface " + upp + "QueryList : NSObject<YYModel>");
		sb.append("\r\n@property (nonatomic,assign)BOOL success;");
		sb.append("\r\n@property (nonatomic,copy)NSString * errorMsg;");
		sb.append("\r\n@property (nonatomic,copy)NSString * code;");
		sb.append("\r\n@property (nonatomic,strong)NSArray <" + upp + "QueryListData *>* data;");
		sb.append("\r\n@end");
		sb.append("\r\n@interface " + upp + "QueryListData : NSObject<YYModel>");
		sb.append("\r\n@property (nonatomic,assign)NSInteger theID;");
		sb.append(field);
		sb.append("\r\n@end");
		return sb;
	}
}
