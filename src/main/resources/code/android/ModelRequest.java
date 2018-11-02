package ${pName};

import java.util.HashMap;
import java.util.Map;

import org.json.JSONException;
import org.json.JSONObject;

import com.android.volley.AuthFailureError;
import com.android.volley.Response;
import com.android.volley.Response.Listener;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.google.gson.Gson;

import com.cn.danceland.myapplication.MyApplication;
import com.cn.danceland.myapplication.utils.Constants;
import com.cn.danceland.myapplication.utils.SPUtils;
import com.cn.danceland.myapplication.utils.ToastUtils;

/**
 * @友情提示 请清理掉用不到的代码包括这段注释
 **/
/**
 * @类说明 ${cName}--网络请求层
 * @author ${auth}
 * @date ${time}
 **/
public class ${upp}Request {


	/**
	 * @方法说明   新增[${cName}]记录
	 */
	public void save(${upp} ${lowUpp}, Listener<JSONObject> listener) {
		JsonObjectRequest request = new JsonObjectRequest(1, Constants.HOST + "${lowUpp}/save", new Gson().toJson(${lowUpp}), listener, new Response.ErrorListener() {
			public void onErrorResponse(VolleyError error) {
				ToastUtils.showToastShort("请检查手机网络！");
			}
		}) {
			public Map<String, String> getHeaders() throws AuthFailureError {
				HashMap<String, String> map = new HashMap<String, String>();
				map.put("Authorization", SPUtils.getString(Constants.MY_TOKEN, ""));
				return map;
			}
		};
		MyApplication.getHttpQueues().add(request);
	}
    
    	
	/**
	 * @方法说明   修改[${cName}]记录
	 */
	public void update(${upp} ${lowUpp}, Listener<JSONObject> listener) {
		JsonObjectRequest request = new JsonObjectRequest(1, Constants.HOST + "${lowUpp}/update", new Gson().toJson(${lowUpp}), listener, new Response.ErrorListener() {
			public void onErrorResponse(VolleyError error) {
				ToastUtils.showToastShort("请检查手机网络！");
			}
		}) {
			public Map<String, String> getHeaders() throws AuthFailureError {
				HashMap<String, String> map = new HashMap<String, String>();
				map.put("Authorization", SPUtils.getString(Constants.MY_TOKEN, ""));
				return map;
			}
		};
		MyApplication.getHttpQueues().add(request);
	}
        
    /**
     * @方法说明   按主键删除[${cName}]记录
     */
	public void delete(${idType} id, Listener<String> listener) {
		StringRequest request = new StringRequest(1, Constants.HOST + "${lowUpp}/delete?id=" + id, listener, new Response.ErrorListener() {
			@Override
			public void onErrorResponse(VolleyError volleyError) {
				ToastUtils.showToastShort("请检查手机网络！");
			}
		}) {
			@Override
			public Map<String, String> getHeaders() throws AuthFailureError {
				HashMap<String, String> map = new HashMap<String, String>();
				map.put("Authorization", SPUtils.getString(Constants.MY_TOKEN, ""));
				return map;
			}
		};
		MyApplication.getHttpQueues().add(request);
	}
            
    /**
     * @方法说明   按条件查询[${cName}]记录
     */
	public void queryList(${upp}Cond cond, Listener<JSONObject> listener) {
		JsonObjectRequest request = new JsonObjectRequest(1, Constants.HOST + "${lowUpp}/queryList", new Gson().toJson(cond), listener, new Response.ErrorListener() {
			public void onErrorResponse(VolleyError error) {
				ToastUtils.showToastShort("请检查手机网络！");
			}
		}) {
			public Map<String, String> getHeaders() throws AuthFailureError {
				HashMap<String, String> map = new HashMap<String, String>();
				map.put("Authorization", SPUtils.getString(Constants.MY_TOKEN, ""));
				return map;
			}
		};
		MyApplication.getHttpQueues().add(request);
	}

    /**
     * @方法说明   按条件查询[${cName}]分页列表
     */
	public void queryPage(${upp}Cond cond, Listener<JSONObject> listener) {
		JsonObjectRequest request = new JsonObjectRequest(1, Constants.HOST + "${lowUpp}/queryPage", new Gson().toJson(cond), listener, new Response.ErrorListener() {
			public void onErrorResponse(VolleyError error) {
				ToastUtils.showToastShort("请检查手机网络！");
			}
		}) {
			public Map<String, String> getHeaders() throws AuthFailureError {
				HashMap<String, String> map = new HashMap<String, String>();
				map.put("Authorization", SPUtils.getString(Constants.MY_TOKEN, ""));
				return map;
			}
		};
		MyApplication.getHttpQueues().add(request);
	}

	/**
	 * @方法说明   按主键查询[${cName}]单个数据
	 */
	public void findById(${idType} id, Listener<String> listener) {
		StringRequest request = new StringRequest(1, Constants.HOST + "${lowUpp}/findById?id=" + id, listener, new Response.ErrorListener() {
			@Override
			public void onErrorResponse(VolleyError volleyError) {
				ToastUtils.showToastShort("请检查手机网络！");
			}
		}) {
			@Override
			public Map<String, String> getHeaders() throws AuthFailureError {
				HashMap<String, String> map = new HashMap<String, String>();
				map.put("Authorization", SPUtils.getString(Constants.MY_TOKEN, ""));
				return map;
			}
		};
		MyApplication.getHttpQueues().add(request);
	}

    /**
     * @方法说明   按条件查询[${cName}]数据个数
     */
	public void queryCount(${upp}Cond cond, Listener<JSONObject> listener) {
		JsonObjectRequest request = new JsonObjectRequest(1, Constants.HOST + "${lowUpp}/queryCount", new Gson().toJson(cond), listener, new Response.ErrorListener() {
			public void onErrorResponse(VolleyError error) {
				ToastUtils.showToastShort("请检查手机网络！");
			}
		}) {
			public Map<String, String> getHeaders() throws AuthFailureError {
				HashMap<String, String> map = new HashMap<String, String>();
				map.put("Authorization", SPUtils.getString(Constants.MY_TOKEN, ""));
				return map;
			}
		};
		MyApplication.getHttpQueues().add(request);
	}
}