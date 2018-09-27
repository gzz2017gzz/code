import http from '../../../utils/http';
import {Message, Modal} from 'iview';
//初始数据
const initForm = {
  <#list fList as fi>
  ${fi.name}:null, //${fi.comment}
  </#list>
};
//模型
export default {
  state: {
	//分页列表
	total: 0,
	page: 1,
	dataList: [],
	loading: false,
	searchForm: {
    		<#list fList as fi>
			${fi.name}:null, //${fi.comment}
			</#list>
	},
	//新增与修改
	form: {...initForm},

	title: '',
	dialogMode: "save",
	show: false,
  },
  getters: {},
  mutations: {
	['${lowUpp}/refresh'](state){
	  state.loading = true;
	  const requestData = {...state.searchForm, page: state.page - 1};
	  http.post("/api/${lowUpp}/queryPage", JSON.stringify(requestData)).then(res => {
		state.loading = false;
		state.dataList = res.data.content;
		state.total = res.data.totalElements;
	  }).catch(res => {
		Message.error({
		  content: "获取${cName}列表失败：" + res
		});
		state.loading = false;
	  });
	},
	["${lowUpp}/addDialog"](state){
	  state.title = "新增${cName}";
	  state.dialogMode = "save";
	  state.form = {...initForm};
	  state.show = true;
	},
	["${lowUpp}/editDialog"](state, row){
	  state.title = "修改${cName}";
	  state.dialogMode = "update";
	  state.form = {...row};
	  state.show = true;
	},
	["${lowUpp}/showDialog"](state, show){
	  state.show = show;
	},
	["${lowUpp}/setPage"](state, page){
	  state.page = page;
	},
  },
  actions: {
	["${lowUpp}/deleteAction"]({state, dispatch, commit}, row){
	  Modal.confirm({
		title: '提示',
		content: '您确定要删除吗?',
		closable:true,
		onOk: () => {
		  http.delete("/api/${lowUpp}/delete", {
			params: {ids: [row.${idName}]}
		  }).then(res => {
			Message.success({
			  content: "删除成功"
			});
			commit("${lowUpp}/refresh");
		  }).catch(res => {
			Message.error({
			  content: "删除失败：" + res
			});
		  });
		},
		onCancel: () => {

		}
	  });
	},
	['${lowUpp}/save']({state, dispatch, commit}){
	  http.post("/api/${lowUpp}/" + state.dialogMode, JSON.stringify(state.form)).then(res => {
		commit("${lowUpp}/refresh");
		commit("${lowUpp}/showDialog", false);
	  }).catch(res => {
		Message.error({
		  content: '保存${cName}信息失败' + res
		});
	  })
	},
  }
};
 
