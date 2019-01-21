/*${cName}新增与修改},作者:${auth},日期:${time}*/
<template>
  <Modal :title="title" v-model="show" :scrollable="false" :mask-closable="false" width="600" draggable>
    <Form :model="form" ref="form" :rules="rules" :label-width="100">
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
  import  {mapState, mapGetters, mapMutations, mapActions}  from 'vuex';
  export default {
    data() {
      return {
        rules: {
          <#list fList as fi>
          ${fi.name} :[
            {required: true, message: '请输入${fi.comment}', trigger: 'blur'},
            {min: 1, max: 10, message: '${fi.comment}长度不正确}', trigger: 'blur'},
          ],
    </#list>
    		},
       }
    }, computed: {
      ...mapGetters({}),
      ...mapState({
        form: (state) => state.${lowUpp}.form,
        title: (state) => state.${lowUpp}.title,
        dialogMode: (state) => state.${lowUpp}.dialogMode,
      }),
      show: {
        get () {
          return this.${dollar}store.state.${lowUpp}.show
        },
        set (value) {
          this.${dollar}store.commit('${lowUpp}/showDialog', value)
        }
      }
    },
    methods: {
      ...mapActions({}),
      ...mapMutations({}),
      save() {//新增及修改记录
        const that = this;
        this.${dollar}refs['form'].validate((valid) => {
          if (!valid) {
            return;
          }
          that.${dollar}store.dispatch("${lowUpp}/save")
        });
      },
    },
    components: {}
  }
</script>
<style></style>