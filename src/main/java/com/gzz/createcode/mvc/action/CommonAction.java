package com.gzz.createcode.mvc.action;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * @功能描述:首页的跳转类
 * @author gzz_gzz@163.com
 * @date 2018-02-15
 */
@Controller
public class CommonAction {
	/**
	 * @功能描述: 进入主页面的跳转
	 */
	@RequestMapping(path = "/", method = { RequestMethod.GET, RequestMethod.POST })
	public String index() {
		return "index";
	}
}
