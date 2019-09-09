package com.wujunxuan.crud.service;



import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wujunxuan.crud.bean.Employee;
import com.wujunxuan.crud.bean.EmployeeExample;
import com.wujunxuan.crud.bean.EmployeeExample.Criteria;
import com.wujunxuan.crud.dao.EmployeeMapper;

@Service
public class EmployeeService {


	@Autowired
	EmployeeMapper employeeMapper;

	/**
	 * 查询所有员工
	 * @return
	 */
	public List<Employee> getAll() {
		// TODO Auto-generated method stub
		return employeeMapper.selectByExampleWithDept(null);
	}

	public void saveEmp(Employee employee) {
		
		 employeeMapper.insertSelective(employee);
	}
	

	/***
	 * check user usable
	 * @param empName
	 * @return
	 */
	public boolean checkUser(String empName) {
		EmployeeExample example=new EmployeeExample();
		Criteria criteria =example.createCriteria();
		criteria.andEmpNameEqualTo(empName);
		
		long count= employeeMapper.countByExample(example);
		System.out.println(count);
		return count==0;
	}

	public Employee getEmp(Integer id) {
		Employee employee = employeeMapper.selectByPrimaryKey(id);
		return employee;
	}





}
