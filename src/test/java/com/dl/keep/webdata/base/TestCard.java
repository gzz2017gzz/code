package com.dl.keep.webdata.base;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.gzz.createcode.Application;
import com.gzz.createcode.test.DeptCond;
import com.gzz.createcode.test.DeptDao;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = Application.class)
public class TestCard {

	@Autowired
	private DeptDao dao;

	@Test
	public void query() {

		dao.queryList(new DeptCond());
	}

}
