/*${cName}管理,作者:${auth},日期:${time}*/
<template>
  <div>
    <el-row>
      <el-col :span="8">
        <el-button type="text" size="mini" @click="() => handleNodeClick({ ${idName}: 0, children: 'A' })" title="根结点">根结点</el-button>
        <el-button type="text" size="mini" @click="() => doAdd(0)" title="新增">新增</el-button>
        <el-tree default-expand-all :data="menus" :props="defaultProps" @node-click="handleNodeClick">
          <span class="custom-tree-node" slot-scope="{ node, data }">
            <span>{{ node.label }}</span>
            <span>
              <el-button type="text" size="mini" @click="() => doAdd(data.${idName})" title="新增下级">新增下级</el-button>
              <el-button type="text" size="mini" @click="() => doEdit(data)" title="编辑">编辑</el-button>
              <el-button type="text" size="mini" @click="() => doDelete(data)" title="删除">删除</el-button>
            </span>
          </span>
        </el-tree>
      </el-col>
      <el-col :span="15">
		<el-table :data="dataList" stripe v-loading="loading" element-loading-text="正在加载......" style="width: 100%" border size="mini">
      <#list fList as fi>
      <#if (fi_index > 0)>
      		<el-table-column prop="${fi.name}" label="${fi.comment}"></el-table-column>
      </#if>
      </#list>
      </el-table>
      </el-col>
    </el-row>
    <${upp}Dialog ref="dialog" :queryTree="queryTree"></${upp}Dialog>
  </div>
</template>
<script>
import ${upp}Dialog from "./${upp}Dialog.vue";
export default {
  components: { ${upp}Dialog },
  data() {
    return {
      menus: [],
      dataList: [],
      loading: false,
      defaultProps: { children: "children", label: "name" },
      form: {
        parentId: null, // 所属上级
      },
    };
  },
  name: "${upp}",
  created() {
    this.queryTree();
  },
  methods: {
    handleNodeClick(data) {
      this.dataList = [];
      if (data.children) {
        this.form.parentId = data.${idName};
        this.queryList();
      } 
    },
    queryTree() {
      const that = this;
      this.rq.post("/${lowUpp}/tree").then((res) => {
        if (res.code == 200) {
          that.menus = res.data;
        }
      });
    },
    doAdd(parentId) {
      this.$refs["dialog"].addDialog(parentId);
    },
    doEdit(row) {
      this.$refs["dialog"].editDialog(row);
    },
    queryList() {
      const that = this;
      that.loading = true;
      that.rq.post("/${lowUpp}/list", this.form).then((res) => {
        that.dataList = res.data; //数据列表
      });
      that.loading = false;
    },
    doDelete(row) {
      const that = this;
      this.$confirm("你确定要删除吗?", "提示", {
        confirmButtonText: "确定",
        cancelButtonText: "取消",
        type: "warning",
      }).then(() => {
        that.rq.post("/${lowUpp}/delete", [row.${idName}]).then((res) => {
          that.$message.success("删除成功");
          that.queryTree();
        });
      });
    },
  },
};
</script>
<style scoped lang="scss">
.custom-tree-node {
  flex: 1;
  display: flex;
  align-items: center;
  justify-content: space-between;
  font-size: 14px;
  padding-right: 8px;
}
</style>