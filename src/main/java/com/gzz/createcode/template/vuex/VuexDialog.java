package com.gzz.createcode.template.vuex;

import java.util.List;

import com.gzz.createcode.common.Utils;
import com.gzz.createcode.mvc.model.Field;

public class VuexDialog {
	public static StringBuilder create(List<Field> fList, String cName, String auth, String lowUpp) {
		StringBuilder sb = new StringBuilder();
		StringBuilder filed = new StringBuilder();
		int i = 0;
		for (Field field : fList) {
			i++;
			if (i == 1) {// 第一个字段ID字段不生成
				continue;
			}
			String name = field.getName().toLowerCase();
			String comments = field.getComment();
			filed.append("\r\n        <FormItem label=\"" + comments + "\" prop=\"" + name + "\"><Input placeholder=\"" + comments + "\" v-model=\"form." + name + "\"></Input></FormItem>");
		}
		sb.append(Utils.pageNote(cName + "新增与修改", auth));
		sb.append("\r\n<template>");
		sb.append("\r\n  <Modal :title=\"title\" v-model=\"show\" :scrollable=\"false\" :mask-closable=\"false\" width=\"400\">");
		sb.append("\r\n    <Form :model=\"form\" ref=\"form\" :rules=\"rules\" :label-width=\"100\">");
		sb.append("\r\n      <Row>");
		sb.append("\r\n        <Col>");
		sb.append(filed);
		sb.append("\r\n        </Col>");
		sb.append("\r\n      </Row>");
		sb.append("\r\n    </Form>");
		sb.append("\r\n    <div slot=\"footer\" style=\"text-align: right\">");
		sb.append("\r\n      <Button @click=\"show = false\">取消</Button>");
		sb.append("\r\n      <Button type=\"primary\" @click=\"save()\">确定</Button>");
		sb.append("\r\n    </div>");
		sb.append("\r\n  </Modal>");
		sb.append("\r\n</template>");
		sb.append("\r\n<script>");
		sb.append("\r\n  import  {mapState, mapGetters, mapMutations, mapActions}  from 'vuex';");
		sb.append("\r\n  export default {");
		sb.append("\r\n    data() {");
		sb.append("\r\n      return {}");
		sb.append("\r\n    }, computed: {");
		sb.append("\r\n      ...mapGetters({}),");
		sb.append("\r\n      ...mapState({");
		sb.append("\r\n        form: (state) => state." + lowUpp + ".form,");
		sb.append("\r\n        rules: (state) => state." + lowUpp + ".rules,");
		sb.append("\r\n        title: (state) => state." + lowUpp + ".title,");
		sb.append("\r\n        dialogMode: (state) => state." + lowUpp + ".dialogMode,");
		sb.append("\r\n      }),");
		sb.append("\r\n      show: {");
		sb.append("\r\n        get () {");
		sb.append("\r\n          return this.$store.state." + lowUpp + ".show");
		sb.append("\r\n        },");
		sb.append("\r\n        set (value) {");
		sb.append("\r\n          this.$store.commit('" + lowUpp + "/showDialog', value)");
		sb.append("\r\n        }");
		sb.append("\r\n      }");
		sb.append("\r\n    },");
		sb.append("\r\n    methods: {");
		sb.append("\r\n      ...mapActions({}),");
		sb.append("\r\n      ...mapMutations({}),");
		sb.append("\r\n      save() {//新增及修改记录");
		sb.append("\r\n        const that = this;");
		sb.append("\r\n        this.$refs['form'].validate((valid) => {");
		sb.append("\r\n          if (!valid) {");
		sb.append("\r\n            return;");
		sb.append("\r\n          }");
		sb.append("\r\n          that.$store.dispatch(\"" + lowUpp + "/save\")");
		sb.append("\r\n        });");
		sb.append("\r\n      },");
		sb.append("\r\n    },");
		sb.append("\r\n    components: {}");
		sb.append("\r\n  }");
		sb.append("\r\n</script>");
		sb.append("\r\n<style></style>");
		return sb;
	}
}
