package com.gzz.createcode.mvc.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.common.base.CaseFormat;
import com.gzz.createcode.common.Utils;
import com.gzz.createcode.mvc.dao.CodeDao;
import com.gzz.createcode.mvc.model.CodeCond;
import com.gzz.createcode.mvc.model.Field;
import com.gzz.createcode.mvc.model.Table;
import com.gzz.createcode.template.app.AppAction;
import com.gzz.createcode.template.app.AppCondition;
import com.gzz.createcode.template.app.AppModel;
import com.gzz.createcode.template.app.Request;
import com.gzz.createcode.template.center.Action;
import com.gzz.createcode.template.center.Bus;
import com.gzz.createcode.template.center.Client;
import com.gzz.createcode.template.data.Controller;
import com.gzz.createcode.template.data.Dao;
import com.gzz.createcode.template.data.Serv;
import com.gzz.createcode.template.model.Condition;
import com.gzz.createcode.template.model.Model;
import com.gzz.createcode.template.vue.VueDialog;
import com.gzz.createcode.template.vue.VueList;
import com.gzz.createcode.template.vue.VueMockJs;
import com.gzz.createcode.template.vuex.Dialog;
import com.gzz.createcode.template.vuex.Expand;
import com.gzz.createcode.template.vuex.IndexJs;
import com.gzz.createcode.template.vuex.Js;
import com.gzz.createcode.template.vuex.Page;

/**
 * @功能描述:生成列表类型代码的实现类
 * @author gzz_gzz@163.com
 * @date 2018-02-15
 */
@Service
public class CodeService {
	// private Log logger = LogFactory.getLog(CodeService.class);// 日志类
	@Autowired
	protected CodeDao dao;

	/**
	 * @功能描述:生成代码
	 */
	public void create(CodeCond cond) {
		String auth = cond.getAuth();// 作者
		String pName;
		String path;
		for (Table table : cond.getC_list()) {
			String tName = table.getT_name();// 表名
			cond.setT_name_eq(tName);
			List<Field> fList = dao.queryColumnList(cond);// 字段列表
			String cName = table.getC_name();// 表注释中文名
			String upp = table.getCls_upp();// 类名(首字母大写)
			String low = upp.toLowerCase();// 类名小写
			String lowUpp = Utils.firstLower(upp);
			String idType = Utils.keyType(fList);// 主键数据类型

			pName = cond.pack("common", low);
			path = cond.base("common", low, upp);
			Utils.write(path + ".java", Model.create(pName, upp, fList, auth, cName));
			Utils.write(path + "Cond.java", Condition.create(pName, upp, fList, auth, cName));

			pName = cond.pack("webdata", low);
			path = cond.base("webdata", low, upp);
			Utils.write(path + "Dao.java", Dao.create(pName, upp, auth, cName, fList, tName, idType));
			Utils.write(path + "Service.java", Serv.create(pName, upp, auth, cName, idType, lowUpp));
			Utils.write(path + "Controller.java", Controller.create(pName, upp, auth, cName, idType, lowUpp));

			pName = cond.pack("webcenter", low);
			path = cond.base("webcenter", low, upp);
			Utils.write(path + "Action.java", Action.create(pName, upp, auth, cName, idType, lowUpp));

			Utils.write(path + "Bus.java", Bus.create(pName, upp, auth, cName, idType, lowUpp));
			path = cond.base("webcenter", low, "I" + upp);
			Utils.write(path + "Client.java", Client.create(pName, upp, auth, cName, idType, lowUpp));

			pName = cond.pack("appcenter", low);
			path = cond.base("appcenter", low, upp);
			Utils.write(path + "Action.java", AppAction.create(pName + low, upp, auth, cName, idType, lowUpp));
			Utils.write(path + "Bus.java", Bus.create(pName, upp, auth, cName, idType, lowUpp));
			path = cond.base("appcenter", low, "I" + upp);
			Utils.write(path + "Client.java", Client.create(pName, upp, auth, cName, idType, lowUpp));

			path = cond.base("vue", low, upp);
			Utils.write(path + "List.vue", VueList.create(fList, upp, cName, auth, lowUpp));
			Utils.write(path + "Dialog.vue", VueDialog.create(fList, lowUpp, cName, auth));

			path = cond.base("vuex", low, upp);
			Utils.write(path + "Mock.js", VueMockJs.create(fList, lowUpp, cName, auth));
			Utils.write(path + "List.vue", Page.create(fList, upp, cName, auth, lowUpp));
			Utils.write(path + "Dialog.vue", Dialog.create(fList, cName, auth, lowUpp));
			Utils.write(path + ".js", Js.create(fList, cName, auth, lowUpp));
			Utils.write(path + "Expand.vue", Expand.create(fList, cName, auth));
			Utils.write(path + "index.js", IndexJs.create(upp, low, lowUpp, cond.getModel()));

			pName = cond.pack("android", low);
			path = cond.base("android", low, upp);
			Utils.write(path + "Request.java", Request.create(pName + low, upp, auth, cName, idType, lowUpp));
			Utils.write(path + "Service.java", Serv.create(pName + low, upp, auth, cName, idType, lowUpp));
			Utils.write(path + ".java", AppModel.create(pName + low, upp, fList, auth, cName));
			Utils.write(path + "Cond.java", AppCondition.create(pName + low, upp, fList, auth, cName));
		}
	}

	/**
	 * @功能描述: 查询数据库中表名列表
	 */
	public List<Table> queryTableList(CodeCond para) {
		List<Table> list = dao.queryTableList(para);
		list.forEach(item -> {
			item.setCls_upp(CaseFormat.LOWER_UNDERSCORE.to(CaseFormat.UPPER_CAMEL, Utils.delFirWord(item.getT_name())));
			item.setC_name(item.getComment());
		});
		return list;
	}

	/**
	 * @功能描述: 查询数据库中字段名列表
	 */
	public List<Field> queryColumnList(CodeCond cond) {
		return dao.queryColumnList(cond);
	}
}
