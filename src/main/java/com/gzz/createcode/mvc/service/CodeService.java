package com.gzz.createcode.mvc.service;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.common.base.CaseFormat;
import com.gzz.createcode.common.CodeUtil;
import com.gzz.createcode.mvc.dao.CodeDao;
import com.gzz.createcode.mvc.model.CodeCond;
import com.gzz.createcode.mvc.model.Field;
import com.gzz.createcode.mvc.model.Table;
import com.gzz.createcode.template.app.AppAction;
import com.gzz.createcode.template.app.AppCondition;
import com.gzz.createcode.template.app.AppModel;
import com.gzz.createcode.template.app.AppRequest;
import com.gzz.createcode.template.app.AppService;
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
import com.gzz.createcode.template.vuex.VuexDialog;
import com.gzz.createcode.template.vuex.VuexExpand;
import com.gzz.createcode.template.vuex.VuexIndexJs;
import com.gzz.createcode.template.vuex.VuexJs;
import com.gzz.createcode.template.vuex.VuexList;

/**
 * @功能描述:生成列表类型代码的实现类
 * @author gzz_gzz@163.com
 * @date 2018-02-15
 */
@Service
public class CodeService {
	Log logger = LogFactory.getLog(CodeService.class);// 日志类
	@Autowired
	protected CodeDao dao;

	/**
	 * @功能描述:生成代码
	 */
	public void create(CodeCond cond) {
		String auth = cond.getAuth();// 作者
		String model = cond.getModel();// 模块名
		String pName = "com." + cond.getCompany() + "." + cond.getPrj() + "." + cond.getModel() + ".";
		String basePath = CodeUtil.getBasePath() + "com/" + cond.getCompany() + "/" + cond.getPrj() + "/" + model + "/";
		for (Table table : cond.getC_list()) {
			String tName = table.getT_name();// 表名
			cond.setT_name_eq(tName);
			List<Field> fList = dao.queryColumnList(cond);// 字段列表
			String cName = table.getC_name();// 表注释中文名
			String clsUpp = table.getCls_upp();// 类名(首字母大写)
 			String clsLow = clsUpp.toLowerCase();// 类名小写
			String lowUpp = CodeUtil.firstLower(clsUpp);
			logger.info(lowUpp);
			String idType = CodeUtil.keyType(fList);// 主键数据类型

			String path = basePath + "model/" + clsLow + "/" + clsUpp;// 数据模型
			CodeUtil.writeFile(path + ".java", Model.genSB(pName + clsLow, clsUpp, fList, auth, cName));
			CodeUtil.writeFile(path + "Cond.java", Condition.genSB(pName + clsLow, clsUpp, fList, auth, cName));

			path = basePath + "data/" + clsLow + "/" + clsUpp;// 数据访问
			CodeUtil.writeFile(path + "Dao.java", Dao.genSB(pName + clsLow, clsUpp, auth, cName, fList, tName, idType));
			CodeUtil.writeFile(path + "Service.java", Serv.genSB(pName + clsLow, clsUpp, auth, cName, idType, lowUpp));
			CodeUtil.writeFile(path + "Controller.java", Controller.genSB(pName + clsLow, clsUpp, auth, cName, idType, lowUpp));

			path = basePath + "center/" + clsLow + "/";// 层务逻辑
			CodeUtil.writeFile(path + "I" + clsUpp + "Client.java", Client.genSB(pName + clsLow, clsUpp, auth, cName, idType, lowUpp));
			CodeUtil.writeFile(path + clsUpp + "Bus.java", Bus.genSB(pName + clsLow, clsUpp, auth, cName, idType, lowUpp));
			CodeUtil.writeFile(path + clsUpp + "Action.java", Action.genSB(pName + clsLow, clsUpp, auth, cName, idType,  lowUpp));

			path = basePath + "vue/" + clsLow + "/" + clsUpp;// vue前端
			CodeUtil.writeFile(path + "List.vue", VueList.genSB(fList, clsUpp, cName, auth, lowUpp));
			CodeUtil.writeFile(path + "Dialog.vue", VueDialog.genSB(fList, lowUpp, cName, auth));

			path = basePath + "vuex/" + clsLow + "/" + clsUpp;// vuex前端
			CodeUtil.writeFile(path + "Mock.js", VueMockJs.genSB(fList, lowUpp, cName, auth));
			CodeUtil.writeFile(path + "List.vue", VuexList.genSB(fList, clsUpp, cName, auth,  lowUpp));
			CodeUtil.writeFile(path + "Dialog.vue", VuexDialog.genSB(fList,   cName, auth, lowUpp));
			CodeUtil.writeFile(path + ".js", VuexJs.genSB(fList,  cName, auth, lowUpp));
			CodeUtil.writeFile(path + "Expand.vue", VuexExpand.genSB(fList, cName, auth));
			CodeUtil.writeFile(path + "index.js", VuexIndexJs.genSB(fList, clsUpp, cName, auth));

			path = basePath + "app/" + clsLow + "/" + clsUpp;// 安卓前端
			CodeUtil.writeFile(path + "Request.java", AppRequest.genSB(pName + clsLow, clsUpp, auth, cName, idType, lowUpp));
			CodeUtil.writeFile(path + "Service.java", AppService.genSB(pName + clsLow, clsUpp, auth, cName, idType, lowUpp));
			CodeUtil.writeFile(path + ".java", AppModel.genSB(pName + clsLow, clsUpp, fList, auth, cName));
			CodeUtil.writeFile(path + "Cond.java", AppCondition.genSB(pName + clsLow, clsUpp, fList, auth, cName));
			CodeUtil.writeFile(path + "Action.java", AppAction.genSB(pName + clsLow, clsUpp, auth, cName, idType, lowUpp));
		}
		CodeUtil.setPermissions();
	}

	/**
	 * @功能描述: 查询数据库中表名列表
	 */
	public List<Table> queryTableList(CodeCond para) {
		List<Table> list = dao.queryTableList(para);
		list.forEach(item -> {
			item.setCls_upp(CaseFormat.LOWER_UNDERSCORE.to(CaseFormat.UPPER_CAMEL, item.getT_name()));
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
