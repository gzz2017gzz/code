package com.gzz.createcode.test;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value = "响应结果", description = "响应结果实体")
public class SwaggerRespImpl<T> {

	@ApiModelProperty(value = "是否成功", notes = "true = 成功,false=不成功", readOnly = true)
	private boolean success;
	@ApiModelProperty(value = "失败原因", notes = "错语信息", readOnly = true)
	private String errorMsg;
	@ApiModelProperty(value = "成功响应结果", notes = "错误编码", readOnly = true)
	private Integer code;
	@ApiModelProperty(value = "成功响应结果", notes = "返回数据", readOnly = true)
	private T data;

	public SwaggerRespImpl() {
	}

	public SwaggerRespImpl(T data) {
		this.success = true;
		this.errorMsg = null;
		this.data = data;
	}

	public SwaggerRespImpl(boolean success, String errorMsg, T data) {
		this.success = success;
		this.errorMsg = errorMsg;
		this.data = data;
	}

	public SwaggerRespImpl(Integer code, String errorMsg) {
		this.success = false;
		this.errorMsg = errorMsg;
		this.code = code;
		this.data = null;
	}

	public boolean isSuccess() {
		return success;
	}

	public void setSuccess(boolean success) {
		this.success = success;
	}

	public String getErrorMsg() {
		return errorMsg;
	}

	public void setErrorMsg(String errorMsg) {
		this.errorMsg = errorMsg;
	}

	public T getData() {
		return data;
	}

	public void setData(T data) {
		this.data = data;
	}

	public Integer getCode() {
		return code;
	}

	public void setCode(Integer code) {
		this.code = code;
	}

}
