package com.gzz.createcode.common.base;

import java.util.ArrayList;
import java.util.List;

import io.swagger.annotations.ApiModelProperty;

/**
 * @功能描述 分页数据
 * @author gzz_gzz@163.com
 * @date 2018-02-15
 */
public class Page<T> {
	@ApiModelProperty(value = "数据列表", dataType = "Array")
	private List<T> dataList = new ArrayList<>();
	@ApiModelProperty(value = "页大小", dataType = "Long")
	private int pageSize = 10;// 页大小(每页记录条)
	@ApiModelProperty(value = "记录数", dataType = "Long")
	private long rowCount;// 记录总数
	@ApiModelProperty(value = "当前页", dataType = "Long")
	private int curpage = 0;// 当前页码
	@ApiModelProperty(value = "总页数", dataType = "Long")
	private int pageCount;// 当前页码

	public Page(List<T> dataList, int curpage, long rowCount, int pagesize, int pageCount) {
		this.dataList.addAll(dataList);
		this.pageSize = pagesize;
		this.rowCount = rowCount;
		this.curpage = curpage;
		this.pageCount = pageCount;
	}

	public List<T> getDataList() {
		return dataList;
	}

	public int getPageSize() {
		return pageSize;
	}

	public long getRowCount() {
		return rowCount;
	}

	public long getPageCount() {
		return pageCount;
	}

	public int getCurpage() {
		return curpage;
	}

	public void setDataList(List<T> dataList) {
		this.dataList = dataList;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public void setRowCount(long rowCount) {
		this.rowCount = rowCount;
	}

	public void setCurpage(int curpage) {
		this.curpage = curpage;
	}

	public void setPageCount(int pageCount) {
		this.pageCount = pageCount;
	}

}
