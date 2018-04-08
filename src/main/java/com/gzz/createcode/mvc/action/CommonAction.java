package com.gzz.createcode.mvc.action;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

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
	@RequestMapping("/")
	public String index() {
		return "index";
	}
}
