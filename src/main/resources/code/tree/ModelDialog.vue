/*${cName}新增与修改,作者:${auth},日期:${time}*/
<template>
  <el-dialog :title="title" :visible.sync="show" :close-on-click-modal="false" :close-on-press-escape="false">
    <el-form :model="form" ref="form" :rules="rules" label-width="100px">
      <el-row>
        <el-col>
          <#list fList as fi>
          <#if (fi_index > 0)>
          <el-form-item label='${fi.comment}' prop='${fi.name}'>
            <el-input placeholder='请输入${fi.comment}' v-model='form.${fi.name}' size="small"  />
          </el-form-item>
          </#if>
          </#list>
        </el-col>
        </el-row>
    </el-form>
    <div slot="footer" style="text-align: right">
      <el-button @click="show = false">取消</el-button>
      <el-button type="primary" @click="save()">确定</el-button>
    </div>
  </el-dialog>
</template>
<script>
  export default {
    props: ["queryTree"],
    data() {
      return {
        title: '',
        form: this.initForm(),
        dialogMode: "save",
		show: false,
		rules: {
      <#list fList as fi>
      <#if (fi_index > 0)>
      ${fi.name} :[
        {required: true, message: '请输入${fi.comment}', trigger: 'blur'},
        {min: 1, max: 10, message: '${fi.comment}长度不正确', trigger: 'blur'}],
      </#if>
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
          that.rq.post("/${lowUpp}/" + that.dialogMode, that.form).then(res => {
            that.show = false;
            that.${dollar}message.success(that.title + "成功!");
            that.queryTree();
          }) 
        });
      },
      initForm() {//初始数据
        return {
          <#list fList as fi>
          ${fi.name} : null, // ${fi.comment}
          </#list>
        }
      },
      addDialog(parentId) {//新增${cName}
        this.title = "新增${cName}";
        this.dialogMode = "save";
        this.form = { ...this.initForm(), parentId: parentId };
        this.show = true;
      },
      editDialog(row) {//修改${cName}
        this.title = "修改${cName}";
        this.dialogMode = "update";
        this.form = {...row};
        this.show = true;
      },
    } 
  }
</script>
<style></style>
