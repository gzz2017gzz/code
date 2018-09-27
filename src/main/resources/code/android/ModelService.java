package ${pName};

import java.util.List;
import java.text.SimpleDateFormat;

import com.android.volley.Response;
import org.json.JSONObject;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import com.cn.danceland.myapplication.bean.DLResult;
import com.cn.danceland.myapplication.bean.Page;
import com.cn.danceland.myapplication.utils.ToastUtils;

/**
 * @友情提示 请清理掉用不到的代码包括这段注释
 **/
/**
* @类说明 ${cName}--业务逻辑
* @author ${auth}
* @date  ${time}
**/
public class ${upp}Service {
	private ${upp}Request request = new ${upp}Request();
	private Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd HHmmss").create();
	private SimpleDateFormat sdf= new SimpleDateFormat("yyyy-MM-dd HHmmss");

    /**
    * @方法说明  新增[${cName}]记录
    */
	public void save() {
		${upp} ${lowUpp} = new ${upp}();
		// TODO 准备数据
		request.save(${lowUpp}, new Response.Listener<JSONObject>() {
    			public void onResponse(JSONObject json) {
    				DLResult<Integer> result = gson.fromJson(json.toString(), new TypeToken<DLResult<Integer>>() {
        				}.getType());
        				if (result.isSuccess()) {
        					// TODO 请求据成功后的代码
        				} else {
        					ToastUtils.showToastShort("保存数据失败,请检查手机网络");
        				}
        			}
        		});
        
        	}
        
        /**
        * @方法说明  修改[${cName}]记录
        */
        public void update() {
            ${upp} ${lowUpp} = new ${upp}();
            // TODO 准备数据
            request.save(${lowUpp}, new Response.Listener<JSONObject>() {
                    public void onResponse(JSONObject json) {
                        DLResult<Integer> result = gson.fromJson(json.toString(), new TypeToken<DLResult<Integer>>() {
                            }.getType());
                            if (result.isSuccess()) {
                                // TODO 请求成功后的代码
                            } else {
                                ToastUtils.showToastShort("修改数据失败,请检查手机网络！");
                            }
                        }
                    });
            
                }
                
        /**
        * @方法说明  按主键删除[${cName}]记录
        */
        public void delete() {
            ${idType} id = null;
            // TODO 准备数据
            request.delete(id, new Response.Listener<String>() {
                    public void onResponse(String res) {
                        DLResult<Integer> result = gson.fromJson(res, new TypeToken<DLResult<Integer>>() {
                            }.getType());
                            if (result.isSuccess()) {
                                // TODO 请求成功后的代码
                            } else {
                                ToastUtils.showToastShort("删除数据失败,请检查手机网络！");
                            }
                        }
                    });
                }

            /**
            * @方法说明  按条件查询[${cName}]列表
            */
            public void queryList() {
                ${upp}Cond cond = new ${upp}Cond();
                // TODO 准备查询条件
                request.queryList(cond, new Response.Listener<JSONObject>() {
                        public void onResponse(JSONObject json) {
                            DLResult<List<${upp}>> result = gson.fromJson(json.toString(), new TypeToken<DLResult<List<${upp}>>>() {
                            }.getType());
                            if (result.isSuccess()) {
                                List<${upp}> list = result.getData();
                                System.out.println(list);
                                // TODO 请求成功后的代码
                            } else {
                                ToastUtils.showToastShort("查询分页列表失败,请检查手机网络！");
                            }
                        }
                    });
                }

                /**
                * @方法说明  按条件查询[${cName}]分页列表
                */
            public void queryPage() {
                ${upp}Cond cond = new ${upp}Cond();
                // TODO 准备查询条件
                request.queryPage(cond, new Response.Listener<JSONObject>() {
                        public void onResponse(JSONObject json) {
                            // LogUtil.i(json.toString());
                            DLResult<Page<${upp}>> result = gson.fromJson(json.toString(), new TypeToken<DLResult<Page<${upp}>>>() {
                            }.getType());
                            if (result.isSuccess()) {
                                Page<${upp}> page = result.getData();
                                List<${upp}> list = page.getContent();
                                System.out.println(list);
                                // TODO 查询成功后的代码
                            } else {
                                ToastUtils.showToastShort("查询列表失败,请检查手机网络！\");
                            }

                        }
                    });

                }

	    /**
	     * @方法说明  按主键查询单个[${cName}]
	     */
            public void findById() {
			    ${idType} id = null;
			    // TODO 准备数据
			    request.findById(id, new Response.Listener<String>() {
			            public void onResponse(String res) {
			                DLResult<${upp}> result = gson.fromJson(res, new TypeToken<DLResult<${upp}>>() {
			                }.getType());
			                if (result.isSuccess()) {
			                    ${upp} ${lowUpp} = result.getData();
			                    System.out.println("${lowUpp}");
			                    // TODO 查询成功后的代码
			                } else {
			                    ToastUtils.showToastShort("请检查手机网络！");
			                }
			            }
			        });
            }

            /**
            * @方法说明  按条件查询[${cName}]数据个数
            */
        public void queryCount() {
            ${upp}Cond cond = new ${upp}Cond();
            // TODO 准备查询条件
            request.queryCount(cond, new Response.Listener<JSONObject>() {
                    public void onResponse(JSONObject json) {
                        DLResult<Long> result = gson.fromJson(json.toString(), new TypeToken<DLResult<Long>>() {
                            }.getType());
                            if (result.isSuccess()) {
                                Long count = result.getData();
                                System.out.println(count);
                                // TODO 请求成功后的代码
                            } else {
                                ToastUtils.showToastShort("查询分页列表失败,请检查手机网络！");
                            }
                        }
                    });
                }
            }