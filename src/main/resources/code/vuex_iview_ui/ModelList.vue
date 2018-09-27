/*${cName}管理,作者:${auth},日期:${time}*/
<template>
  <div>
    <h3>${cName}"管理</h3>
    <hr/>
    <Form inline :label-width="70">
      <#list fList as fi>
      <FormItem label="${fi.comment}"><Input placeholder="请输入${fi.comment}}" v-model="form.${fi.name}"/></FormItem>
      </#list>
      <FormItem>
        <Button icon="search" @click="refresh" title="根据输入的条件查询" type="primary">查询</Button>
          <Button type="primary" icon="plus" @click="addDialog()" title="添加" >添加</Button>
      </FormItem>
    </Form>
      <Table :loading="loading" :columns="tableHeader" :data="dataList" style="width: 100%"></Table>
      <br/>
      <div style="text-align: right" v-if="total > 0">
        <Page size="small" :current="page" :total="total" show-total
                   @on-change="(curr) => {this.page = curr ; this.refresh();}"></Page>
      </div>
      <${upp}Dialog></${upp}Dialog>
 </div>
</template>
<script>
 import ${upp}Dialog from './${upp}Dialog.vue';
 import ${upp}Expand from './${upp}Expand.vue';
 import {mapState, mapGetters, mapMutations, mapActions} from 'vuex';

 export default {
   components: {${upp}Dialog,${upp}Expand},
   data: function () {
     const that = this;
     return {
       tableHeader: [
         {
           type: 'expand',
           width: 50,
           render: (h, params) => {
             return h(${upp}Expand, {
               props: {row: params.row}
             })
           }
         },
         <#list fList as fi>
           {title: '${fi.comment}', key: '${fi.name}'},
         </#list>
         { title: '操作',
           width: 150,
           render: (h, params) => {
             return h('div', [
               h('Button', {
                 props: {type:'primary',size:'small'},
                 style: {marginRight: '5px'},
                 on: {
                   click: () => {
                     that.editDialog(params.row)
                   }
                 }
               }, '编辑'),
               h('Button', {
                 props: {type: 'primary',size: 'small'},
                 on: {
                   click: () => {
                     that.${dollar}store.dispatch('${lowUpp}/deleteAction', params.row)
                   }
                 }
               }, '删除')
             ]);
           }
         }
       ]
     }
   },
   computed: {
     ...mapGetters({
     }),
     ...mapState({
       form: (state) => state.${lowUpp}.searchForm,
       loading: (state) => state.${lowUpp}.loading,
       total: (state) => state.${lowUpp}.total,
       dataList: (state) => state.${lowUpp}.dataList
     }),
     page: {
       get() {
         return this.${dollar}store.state.${lowUpp}.page
       },
       set(value) {
         this.${dollar}store.commit('${lowUpp}/setPage', value)
       }
     }
   },
   created: function () {
     this.refresh();
   },
   methods: {
     ...mapActions({}),
     ...mapMutations(
       {
         refresh: '${lowUpp}/refresh',
         addDialog: '${lowUpp}/addDialog',
         editDialog: '${lowUpp}/editDialog',
       }
     ),
   }
 }
</script>
<style></style>