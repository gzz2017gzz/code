<title>代码生成器-ELEMENT版gzz_gzz@163.com</title>
<link rel="stylesheet" type="text/css" href="element.css">
<script type="text/javascript" src="vue.min.js"></script>
<script type="text/javascript" src="element.min.js"></script>
<script type="text/javascript" src="axios.min.js"></script>
<div id="app">
   <div style="color: blue;font-size: 14px;" >
	注意:
	【请调整作者名(可以为中文)】
	【请调整[模块名]】
	【确保表名注释与字段名注释为中文(点击[查看])】
	【<span style="color: red;" >已去掉</span>类名中表名前缀部分(BasePerson改为Person)】<br>
<!--  	目录说明: -->
<!-- 	【model实体类→common】 -->
<!-- 	【data数据访问微服务→web-data,app-data】 -->
<!-- 	【center业务逻辑微服务→web-center,app-center】 -->
<!-- 	【vue前端vue+element】 -->
<!-- 	【vuex前端vuex+iview】 -->
<!-- 	【app安卓端】<br> -->
	代码路径: 
	 \\192.168.1.97\public\code\com\dl
    </div>
    <div class="search">
        表名
        <el-input v-model="form.t_name" placeholder="请输入表名" style="width:150px" size="small"></el-input>
        <el-button @click="query" type="primary" size="small">查询</el-button>
        作者名
        <el-input v-model="form.auth" style="width:150px" size="small"></el-input>
        公司名
        <el-input v-model="form.company" style="width:150px" size="small"></el-input>
        项目名:[common,webcenter,webdata,appcenter,vue,vuex,android]
         
        模块名
        <el-input v-model="form.model" style="width:150px" size="small"></el-input>
        <el-button @click="createCode" type="primary" size="small">生成代码</el-button>
    </div>
    <el-table :data="filterTableList" class="tabClass" @selection-change="onSelectChange" border size="small">
        <el-table-column type="selection" width="40"></el-table-column>
        <el-table-column prop="t_name" label="表名"></el-table-column>
        <el-table-column prop="comment" label="注释"></el-table-column>
        <el-table-column label="建议类名">
            <template slot-scope="props">
                <el-input v-model="props.row.cls_upp" size="small"></el-input>
            </template>
        </el-table-column>
        <el-table-column label="类中文名">
            <template slot-scope="props">
                <el-input v-model="props.row.c_name" size="small"></el-input>
            </template>
        </el-table-column>
        <el-table-column label="字段信息" width="80" >
            <template slot-scope="props">
                <span style="" @click="queryfieldList(props.row.t_name)">查看</span>
            </template>
        </el-table-column>
    </el-table>
    <br>
    <el-dialog :visible.sync="show">
        <el-table :data="f_list" class="tabClass" border size="small">
            <el-table-column prop="name" label="字段名"></el-table-column>
            <el-table-column prop="comment" label="字段注释"></el-table-column>
            <el-table-column prop="type" label="数据类型"></el-table-column>
        </el-table>
    </el-dialog>
</div>
<script>
    axios.defaults.headers.post['Content-Type'] = 'application/json';
    new Vue({
        el: '#app',
        data: function () {
            return {
                form: {
                    t_name: '',
                    c_list: [],
                    auth: "gzz_gzz@163.com",
                    company: "dl",
                    model: "card",
                },
                t_list: [],
                f_list: [],
                show: false,
            }
        },
        created: function () {
            this.query();
        },
        computed: {
        	filterTableList() {
                const that = this;
                return that.t_list.filter(item => item.t_name.indexOf(that.form.t_name) != -1);
            }
        },
        methods: {
            queryfieldList(t_name) {
                this.show = true;
                axios.post("/code/queryField", JSON.stringify({"t_name_eq": t_name})).then(res => {
                    this.f_list = res.data;
                }).catch(res => {
                    this.$notify.error({title: '失败', message: '加载表信息败!'});
                });
            },
            onSelectChange(val) {
                this.form.c_list = val;
            },
            query() {
                axios.post("/code/queryList", JSON.stringify({"t_name": this.form.t_name})).then(res => {
                    this.t_list = res.data;
                }).catch(res => {
                    this.$notify.error({title: '失败', message: '加载表信息败!'});
                });
            },
            createCode() {
                if (this.form.c_list.length == 0) {
                    this.$notify({title: '警告', message: '请至少选择一条记录.!', type: 'warning'});
                    return;
                }
                axios.post("/code/create", JSON.stringify(this.form)).then(res => {
                    this.$notify.info({title: '消息', message: '生成代码成功'});
                }).catch(res => {
                    this.$notify.error({title: '失败', message: '生成代码失败,请检查代码是否已经存在!'});
                });
            }
        },
    });
</script>
<style>
    .search {
        padding-bottom: 10px;
    }

    .tabClass .el-input--small .el-input__inner {
        height: 20px;
    }

    .tabClass .cell {
        font-size: 13px;
        line-height: 16px;
    }

    .el-table--small td, .el-table--small th {
        padding: 0px 0;
    }
</style>