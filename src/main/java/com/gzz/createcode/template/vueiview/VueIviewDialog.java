package com.gzz.createcode.template.vueiview;

import java.util.List;

import com.gzz.createcode.common.Utils;
import com.gzz.createcode.mvc.model.Field;

public class VueIviewDialog {
	public static StringBuilder create(List<Field> fList, String lowUpp, String cName, String auth) {
		StringBuilder sb = new StringBuilder();
		StringBuilder field = new StringBuilder();
		StringBuilder validate = new StringBuilder();
		StringBuilder initform = new StringBuilder();
		for (Field fi : fList) {
			String name = fi.getName();
			String comments = fi.getComment();
			field.append("\r\n        	<FormItem label=\"" + comments + "\" prop=\"" + name + "\"><Input placeholder=\"" + comments + "\" v-model=\"form." + name + "\"></Input></FormItem>");
			initform.append("\r\n          " + name + ": null,//" + comments);
			validate.append("\r\n          " + name + ": [");
			validate.append("\r\n            {required: true, message: '请输入" + comments + "', trigger: 'blur'},");
			validate.append("\r\n            {min: 1, max: 10, message: '" + comments + "长度不正确', trigger: 'blur'},");
			validate.append("\r\n          ],");
		}
		sb.append(Utils.pageNote(cName + "新增与修改", auth));
		sb.append("\r\n<template>");
		sb.append("\r\n  <Modal :title=\"title\" v-model=\"show\" :scrollable=\"false\" :mask-closable=\"false\" width=\"600\">");
		sb.append("\r\n    <Form :model=\"form\" ref=\"form\" :rules=\"rules\" :label-width=\"100\">");
		sb.append("\r\n      <Row>");
		sb.append("\r\n        <i-col>");
		sb.append(field);
		sb.append("\r\n        </i-col>");
		sb.append("\r\n      </Row>");
		sb.append("\r\n    </Form>");
		sb.append("\r\n    <div slot=\"footer\" style=\"text-align: right\">");
		sb.append("\r\n      <Button @click=\"show = false\">取消</Button>");
		sb.append("\r\n      <Button type=\"primary\" @click=\"save()\">确定</Button>");
		sb.append("\r\n    </div>");
		sb.append("\r\n  </Modal>");
		sb.append("\r\n</template>");
		sb.append("\r\n<script>");
		sb.append("\r\nimport {Message, Modal} from 'iview';");
		sb.append("\r\n  export default {");
		sb.append("\r\n    props: [\"refresh\"],");
		sb.append("\r\n    data() {");
		sb.append("\r\n      return {");
		sb.append("\r\n        title: '',");
		sb.append("\r\n        form: this.initForm(),");
		sb.append("\r\n        dialogMode: \"save\",");
		sb.append("\r\n        show: false,");
		sb.append("\r\n        rules: {");
		sb.append(validate);// 验证
		sb.append("\r\n        }");
		sb.append("\r\n      }");
		sb.append("\r\n    },");
		sb.append("\r\n    methods: {");
		sb.append("\r\n      save() {//新增及修改记录");
		sb.append("\r\n        const that = this;");
		sb.append("\r\n        this.$refs['form'].validate((valid) => {");
		sb.append("\r\n          if (!valid) {");
		sb.append("\r\n            return;");
		sb.append("\r\n          }");
		sb.append("\r\n          that.$http.post(\"/api/" + lowUpp + "/\" + that.dialogMode, JSON.stringify(that.form)).then(res => {");
		sb.append("\r\n        		this.show = false;");
		sb.append("\r\n            	that.refresh();");
		sb.append("\r\n        		Message.success({");
		sb.append("\r\n          		content: '保存" + cName + "信息成功'");
		sb.append("\r\n       		});");
		sb.append("\r\n          }).catch(res => {");
		sb.append("\r\n        		Message.error({");
		sb.append("\r\n          		content: '保存" + cName + "信息失败' + res");
		sb.append("\r\n       		});");
		sb.append("\r\n          });");
		sb.append("\r\n        });");
		sb.append("\r\n      },");
		sb.append("\r\n      initForm() {//初始数据");
		sb.append("\r\n        return {");
		sb.append(initform);// 字段
		sb.append("\r\n          name: ''");
		sb.append("\r\n        }");
		sb.append("\r\n      },");
		sb.append("\r\n      addDialog() {//新增");
		sb.append("\r\n        this.title = \"新增" + cName + "\";");
		sb.append("\r\n        this.dialogMode = \"save\";");
		sb.append("\r\n        this.form = this.initForm();");
		sb.append("\r\n        this.show = true;");
		sb.append("\r\n      },");
		sb.append("\r\n      editDialog(row) {//修改");
		sb.append("\r\n        this.title = \"修改" + cName + "\";");
		sb.append("\r\n        this.dialogMode = \"update\";");
		sb.append("\r\n        this.form = {...row};");
		sb.append("\r\n        this.show = true;");
		sb.append("\r\n      },");
		sb.append("\r\n    },");
		sb.append("\r\n    components: {}");
		sb.append("\r\n  }");
		sb.append("\r\n</script>");
		sb.append("\r\n<style></style>");
		return sb;
	}
}
