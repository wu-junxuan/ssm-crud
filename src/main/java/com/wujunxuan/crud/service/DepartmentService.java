package com.wujunxuan.crud.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wujunxuan.crud.bean.Department;
import com.wujunxuan.crud.dao.DepartmentMapper;

@Service
public class DepartmentService {
	
	
	@Autowired	
	private DepartmentMapper departmentMapper;
	
	public List<Department> getDepts(){
		 List<Department> list = departmentMapper.selectByExample(null);
		 return list;
	
	}
	
	
	

}
