package com.wujunxuan.crud.test;

import java.util.UUID;

import org.apache.ibatis.session.SqlSession;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.wujunxuan.crud.bean.Department;
import com.wujunxuan.crud.bean.Employee;
import com.wujunxuan.crud.dao.DepartmentMapper;
import com.wujunxuan.crud.dao.EmployeeMapper;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:applicationContext.xml"})
public class MapperTest {
	
	@Autowired
	DepartmentMapper departmentMapper;
	
	@Autowired
	EmployeeMapper employeemapper;
	
	@Autowired
	SqlSession sqlSession;
	
	@Test
	public void testDEPTinsert() {
		System.out.println(departmentMapper);
		
		
		Department record1=new Department(null,"test1");
		Department record2=new Department(null,"test2");
		departmentMapper.insertSelective(record1);
		departmentMapper.insertSelective(record2);
		
		
	}
	@Test
	public void testEMPinsert() {
		System.out.println(employeemapper);
		
		
		Employee record1=new Employee(null,"A","M","A@test.com",1);
		Employee record2=new Employee(null,"B","F","B@test.com",2);
		employeemapper.insertSelective(record1);
		employeemapper.insertSelective(record2);
		
		
	}
	@Test
	public void testEMPinsertMany() {
		System.out.println(employeemapper);
		
		//批量插入
		EmployeeMapper mEmployeeMapper= sqlSession.getMapper(EmployeeMapper.class);
		for (int i = 0; i < 1000; i++) {
			String uidString=UUID.randomUUID().toString().substring(2, 7)+i;
			mEmployeeMapper.insertSelective(new Employee(null,uidString,"M",uidString+"@test.com",1));
		}
		System.out.println("ok");
		
		
	}


}
