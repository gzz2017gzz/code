/*${cName}管理,作者:${auth},日期:${time}*/
<template>
  <div>
    <h3>${cName}</h3>
    <hr />
    <el-form :inline="true">
<#list fList as fi>
<#if (fi_index > 0)>
      <el-form-item label="${fi.comment}">
        <el-input placeholder="请输入${fi.comment}" size="small" v-model="form.${fi.name}"></el-input>
      </el-form-item>
</#if>
</#list>
      <el-form-item>
        <el-button icon="search" @click="loadData()" title="根据输入的条件查询" size="small">查询</el-button>
        <el-button @click="doReset()" title="重置" size="small">重置</el-button>
        <el-button @click="doDelete(selectIds)" title="删除" size="small">删除</el-button>
        <el-button type="primary" icon="plus" @click="doAdd()" title="添加" size="small">添加</el-button>
      </el-form-item>
    </el-form>
    <el-table :data="dataList" v-loading="loading" element-loading-text="正在加载......" style="width: 100%" border @selection-change="selectChange" >
      <el-table-column type="selection" width="55"></el-table-column>
      <el-table-column type="expand">
        <template slot-scope="props">
          <el-form>
            <el-row :gutter="10">
<#list fList as fi>
<#if (fi_index > 0)> 
              <el-col :span="6">
                <el-form-item label="${fi.comment}">{{props.row.${fi.name}}}</el-form-item>
              </el-col>
</#if>
</#list>
            </el-row>
          </el-form>
        </template>
      </el-table-column>
<#list fList as fi>
<#if (fi_index > 0)>
      <el-table-column prop="${fi.name}" label="${fi.comment}"></el-table-column>
</#if>
</#list>
      <el-table-column label="操作" width="150">
        <template slot-scope="props">
          <div>
            <el-button type="text" @click="doEdit(props.row)">编辑</el-button>
            <el-button type="text" @click="doDelete([props.row.id])">删除</el-button>
          </div>
        </template>
      </el-table-column>
    </el-table>
    <div style="text-align: right" v-if="total > 0">
      <el-pagination layout="total, sizes, prev, pager, next, jumper" :current-page="page" :total="total" @current-change="(page) => pageChange(page)"
        :page-sizes="[10, 15, 20, 100]" @size-change="(size) => sizeChange(size)" :page-size="size" small></el-pagination>
    </div>
    <${upp}Dialog ref="dialog" :loadData="loadData"></${upp}Dialog>
  </div>
</template>
<script>
import ${upp}Dialog from './${upp}Dialog.vue';
import { pageMix } from "@/common/page";
export default {
  mixins: [pageMix],
  data: function () {
    return {}
  },
  //computed: {},
  created: function () {
    this.loadData();
  },
  methods: {
    initForm() {
      return {
<#list fList as fi>
<#if (fi_index > 0)> 
        ${fi.name}: null, // ${fi.comment}
</#if>		    
</#list>
      };
    },
    loadData() {
      const that = this;
      that.loading = true;
      const param = { ...that.form, page: that.page, size: that.size };
      that.rq
        .post("/${lowUpp}/page", param)
        .then((res) => {
          that.loading = false;
          that.dataList = res.data.dataList; //数据列表
          that.total = res.data.rowCount; //记录个数
        })
        .catch((res) => {
          that.$message.error("获取${cName}列表失败：" + res);
          that.loading = false;
        });
    },
    doDelete(ids) {
      const that = this;
      if (ids.length == 0) {
        that.$message.error("请选择记录");
        return;
      }
      that
        .$confirm("你确定要删除吗?", "提示", {
          confirmButtonText: "确定",
          cancelButtonText: "取消",
          type: "warning",
        })
        .then(() => {
          that.rq
            .post("/${lowUpp}/delete", ids)
            .then((res) => {
              that.$message.success("删除成功");
              that.loadData();
            })
            .catch((res) => {
              that.$message.error("删除失败：" + res);
            });
        });
    },
  },
  components: { ${upp}Dialog }    
};
</script>
<style></style>
