package com.wujunxuan.crud.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.wujunxuan.crud.bean.Department;
import com.wujunxuan.crud.bean.Msg;
import com.wujunxuan.crud.service.DepartmentService;


@Controller
public class DepartmantController {
	@Autowired
	private DepartmentService departmentService;
	
	
	@RequestMapping("/depts")
	@ResponseBody
	public Msg getDepts() {
		
		List<Department> list=departmentService.getDepts();
		return Msg.success().add("depts", list);
	}
}
