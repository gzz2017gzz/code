/*${cName}列表管理,作者:${auth},日期:${time}*/
<template>
  <div>
    <h3>${cName}管理</h3>
    <hr/>
    <Form inline :label-width="70">
      <#list fList as fi>
      <FormItem label="${fi.comment}"><Input placeholder="请输入${fi.comment}" v-model="form.${fi.name}"/></FormItem>
      </#list>
        <FormItem>
          <Button icon="search" @click="refresh" title="根据输入的条件查询" type="primary">查询</Button>
          <Button type="primary" icon="plus" @click="doAdd()" title="添加" >添加</Button>
        </FormItem>
      </Form>
      <Table :loading="loading" :columns="tableHeader" :data="dataList" style="width: 100%"></Table>
      <br/>
      <div style="text-align: right" v-if="total > 0">
        <Page size="small" :current="page" :total="total" show-total  @on-change="(curr) => {this.page = curr ; this.refresh();}"
              show-sizer @on-page-size-change="(pageSize) => { this.pageSize = pageSize ; this.refresh();}" :page-size="pageSize"></Page>
      </div>
      <${upp}Dialog ref="dialog" :refresh="refresh"></${upp}Dialog>
  </div>
</template>
<script>
import {Message, Modal} from 'iview';
import ${upp}Dialog from './${upp}Dialog.vue';
import ${upp}ListExpand from './${upp}ListExpand.vue';
  export default {
    data: function () {
      const that = this;
      return {
        tableHeader: [
          {
            type: 'expand',
            width: 50,
            render: (h, params) => {
              return h(${upp}ListExpand, {
                props: {row: params.row}
              })
            }
          },
		  <#list fList as fi>
            {title: '${fi.comment}', key: '${fi.name}'},
	      </#list>
          {
            title: '操作',
            width: 150,
            render: (h, params) => {
              return h('div', [
                h('Button', {
                  props: {type:'primary',size:'small'},
                  style: {marginRight: '5px'},
                  on: {
                    click: () => {
                      that.doEdit(params.row)
                    }
                  }
                }, '编辑'),
                h('Button', {
                  props: {type: 'primary',size: 'small'},
                  on: {
                    click: () => {
                      that.doDelete(params.row)
                    }
                  }
                }, '删除')
              ]);
            }
          }
        ],
        total: 0,
        page: 1,
        pageSize: 20,
        dataList: [],
        form: {
		    <#list fList as fi>
          ${fi.name} : null,// ${fi.comment}
		    </#list>
        },
        loading: false
      }
    },
    computed: {},
    created: function () {
      this.refresh();
    },
    methods: {
      refresh() {
        const that = this;
        that.loading = true;
        const requestData = {...that.form, page: that.page - 1,size:that.pageSize};
        that.${dollar}http.post("/api/${lowUpp}/queryPage", JSON.stringify(requestData)).then(res => {
		          that.loading = false;
		          that.dataList = res.data.content;
		          that.total = res.data.totalElements;
		        }).catch(res => {
		        Message.error({ content: "获取${cName}列表失败：" + res });
		        });
      },
      doAdd() {
        this.${dollar}refs["dialog"].addDialog();
      },
      doEdit(row) {
        this.${dollar}refs["dialog"].editDialog(row);
      },
      doDelete(row) {
        const that = this;
      Modal.confirm({
        title: '提示',
        content: '您确定要删除吗?',
        closable:true,
        onOk: () => {
          this.${dollar}http.delete("/api/${lowUpp}/delete", {
		            params: {ids: [row.${idName}]}
		          }).then(res => {
		            Message.success({ content: "删除成功" });
		            that.refresh();
		          }).catch(res => {
		            Message.error({ content: "删除失败：" + res });
		          });
        },
        onCancel: () => {
        }
      });
      }
    },
    components: {${upp}Dialog,${upp}ListExpand},
  }
</script>
<style></style>
