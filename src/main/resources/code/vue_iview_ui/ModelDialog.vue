/*${cName}新增与修改,作者:${auth},日期:${time}*/
<template>
  <Modal :title="title" v-model="show" :scrollable="false" :mask-closable="false" width="600">
    <Form :common="form" ref="form" :rules="rules" :label-width="100">
      <Row>
        <i-col>
          <#list fList as fi>
          <FormItem label="${fi.comment}" prop="${fi.name}"><Input placeholder="${fi.comment}" v-model="form.${fi.name}"/></FormItem>
          </#list>
        </i-col>
      </Row>
    </Form>
      <div slot="footer" style="text-align: right">
        <Button @click="show = false">取消</Button>
        <Button type="primary" @click="save()">确定</Button>
      </div>
    </Modal>
</template>
<script>
import {Message, Modal} from 'iview';
  export default {
    props: ["refresh"],
    data() {
      return {
        title: '',
        form: this.initForm(),
        dialogMode: "save",
        show: false,
        rules: {
		<#list fList as fi>
          ${fi.name} : [
            {required: true, message: '请输入${fi.comment}', trigger: 'blur'},
            {min: 1, max: 10, message: '${fi.comment}长度不正确}', trigger: 'blur'},
          ],
	    </#list>
        }
      }
    },
    methods: {
      save() {//新增及修改记录
        const that = this;
        this.${dollar}refs['form'].validate((valid) => {
          if (!valid) {
            return;
          }
          that.${dollar}http.post("/api/${lowUpp}/" + that.dialogMode, JSON.stringify(that.form)).then(res => {
          that.show = false;
          that.refresh();
          Message.success({ content: '保存${cName}信息成功'});
          }).catch(res => {
             Message.error({ content: '保存${cName}信息失败' + res });
          });
        });
      },
      initForm() {//初始数据
        return {
          <#list fList as fi>
              ${fi.name} : null,// ${fi.comment}
          </#list>
        }
      },
      addDialog() {//新增
        this.title = "新增${cName}";
        this.dialogMode = "save";
        this.form = this.initForm();
        this.show = true;
      },
      editDialog(row) {//修改
        this.title = "修改${cName}";
        this.dialogMode = "update";
        this.form = {...row};
        this.show = true;
      },
    },
    components: {}
  }
</script>
<style></style>