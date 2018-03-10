package com.gzz.createcode.template.vue;

import java.util.List;

import com.gzz.createcode.common.Utils;
import com.gzz.createcode.mvc.model.Field;

public class VueDialog {
	public static StringBuilder create(List<Field> fList, String lowUpp, String cName, String auth) {
		StringBuilder sb = new StringBuilder();
		StringBuilder filed = new StringBuilder();
		StringBuilder validate = new StringBuilder();
		StringBuilder initform = new StringBuilder();
		int i = 0;
		for (Field field : fList) {
			i++;
			if (i == 1) {// 第一个字段ID字段不生成
				continue;
			}
			String name = field.getName();
			String comments = field.getComment();
			filed.append("\r\n          <el-form-item label=\"" + comments + "\" prop=\"" + name + "\">");
			filed.append("\r\n            <el-input placeholder=\"请输入" + comments + "\" v-model=\"form." + name + "\"></el-input>");
			filed.append("\r\n          </el-form-item>");
			initform.append("\r\n          " + name + ": null,");
			validate.append("\r\n          " + name + ": [");
			validate.append("\r\n            {required: true, message: '请输入" + comments + "', trigger: 'blur'},");
			validate.append("\r\n            {min: 1, max: 10, message: '" + comments + "长度不正确', trigger: 'blur'},");
			validate.append("\r\n          ],");
		}
		sb.append(Utils.pageNote(cName + "新增与修改", auth));
		sb.append("\r\n<template>");
		sb.append("\r\n  <el-dialog :title=\"title\" :visible.sync=\"show\" :close-on-click-modal=\"false\" :close-on-press-escape=\"false\">");
		sb.append("\r\n    <el-form :model=\"form\" ref=\"form\" :rules=\"rules\" label-width=\"100px\">");
		sb.append("\r\n      <el-row>");
		sb.append("\r\n        <el-col>");
		sb.append(filed);// 字段
		sb.append("\r\n        </el-col>");
		sb.append("\r\n      </el-row>");
		sb.append("\r\n    </el-form>");
		sb.append("\r\n    <div slot=\"footer\" style=\"text-align: right\">");
		sb.append("\r\n      <el-button @click=\"show = false\">取消</el-button>");
		sb.append("\r\n      <el-button type=\"primary\" @click=\"save()\">确定</el-button>");
		sb.append("\r\n    </div>");
		sb.append("\r\n  </el-dialog>");
		sb.append("\r\n</template>");
		sb.append("\r\n<script>");
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
		sb.append("\r\n            that.show = false;");
		sb.append("\r\n            that.$message.success(that.title + \"成功!\");");
		sb.append("\r\n            that.refresh();");
		sb.append("\r\n          }).catch(res => {");
		sb.append("\r\n            that.$message.error(that.title + \"出错!\" + res);");
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
