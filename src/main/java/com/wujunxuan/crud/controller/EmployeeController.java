package com.wujunxuan.crud.controller;



import static org.hamcrest.CoreMatchers.nullValue;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.wujunxuan.crud.bean.Employee;
import com.wujunxuan.crud.bean.Msg;
import com.wujunxuan.crud.service.EmployeeService;

/**
 * @author wujunxuan
 *
 */
@Controller
public class EmployeeController {
	
	@Autowired
	EmployeeService employeeService;
	
	/**
	 * 单个批量二合一
	 * 批量删除：1-2-3
	 * 单个删除：1
	 * 
	 * @param id
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/emp/{ids}",method=RequestMethod.DELETE)
	public Msg deleteEmp(@PathVariable("ids")String ids){
		//批量删除
		if(ids.contains("-")){
			List<Integer> del_ids = new ArrayList<>();
			String[] str_ids = ids.split("-");
			//组装id的集合
			for (String string : str_ids) {
				del_ids.add(Integer.parseInt(string));
			}
			employeeService.deleteBatch(del_ids);
		}else{
			Integer id = Integer.parseInt(ids);
			employeeService.deleteEmp(id);
		}
		return Msg.success();
	}
	
	
	
	@ResponseBody
	@RequestMapping(value="/emp/{empId}",method=RequestMethod.PUT)
	public Msg saveEmp(Employee employee,HttpServletRequest request){
		System.out.println("请求体中的值："+request.getParameter("gender"));
		System.out.println("将要更新的员工数据："+employee);
		employeeService.updateEmp(employee);
		return Msg.success()	;
	}

	
	@RequestMapping(value="/emp/{id}",method=RequestMethod.GET)
	@ResponseBody
	public Msg getEmp(@PathVariable("id")Integer id){
		
		Employee employee = employeeService.getEmp(id);
		return Msg.success().add("emp", employee);
	}
	/***
	 * check user 
	 * @param empName
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/checkuser")
	public Msg checkuser(@RequestParam("empName")String empName) {
		String regx="(^[a-zA-Z0-9_-]{6,16}$)|(^[\\u2E80-\\u9FFF]{2,5})";
		if (!empName.matches(regx)) {
			return Msg.fail().add("va_msg", "后端：用户名可以是2-5位中文或者6-16位英文和数字的组合");
			
		}
		//数据库检验
		boolean b=employeeService.checkUser(empName);
		if (b) {
			return Msg.success();
			
		}else {
			
			return Msg.fail().add("va_msg", "后端：用户名不可用");
		}
	}
	
	/***
	 * save employee
	 * 导入hibernate validator
	 * @param employee
	 * @return
	 */
	@RequestMapping(value = "/emp",method = RequestMethod.POST)
	@ResponseBody
	public Msg saveEmp(@Valid Employee employee,BindingResult result) {
		if (result.hasErrors()) {
			Map<String , Object> map=new HashMap<>();
			List<FieldError> fieldErrors = result.getFieldErrors();
			for (FieldError fieldError : fieldErrors) {
				System.out.println("错误字段"+fieldError.getField());
				System.out.println("错误信息"+fieldError.getDefaultMessage());
				map.put(fieldError.getField(),fieldError.getDefaultMessage());
			}
			return Msg.fail().add("errorFields", map);
		} else {

			employeeService.saveEmp(employee);
			return Msg.success();
		}
		
	}
	
	@RequestMapping("/emps")
	@ResponseBody
	public Msg getEmpWithJson(@RequestParam(value = "pn", defaultValue = "1") Integer pn) {
		PageHelper.startPage(pn, 5);
		// startPage后面紧跟的这个查询就是一个分页查询
		List<Employee> emps = employeeService.getAll();
		// 使用pageInfo包装查询后的结果，只需要将pageInfo交给页面就行了。
		// 封装了详细的分页信息,包括有我们查询出来的数据，传入连续显示的页数
		PageInfo page = new PageInfo(emps, 5);
		return Msg.success().add("pageInfo",page);
	}
	
	
//	@RequestMapping("/emps")
//	public String getEmps(
//			@RequestParam(value = "pn", defaultValue = "1") Integer pn,
//			Model model) {
//		// 这不是一个分页查询；
//		// 引入PageHelper分页插件
//		// 在查询之前只需要调用，传入页码，以及每页的大小
//		PageHelper.startPage(pn, 5);
//		// startPage后面紧跟的这个查询就是一个分页查询
//		List<Employee> emps = employeeService.getAll();
//		// 使用pageInfo包装查询后的结果，只需要将pageInfo交给页面就行了。
//		// 封装了详细的分页信息,包括有我们查询出来的数据，传入连续显示的页数
//		PageInfo page = new PageInfo(emps, 5);
//		model.addAttribute("pageInfo", page);
//
//		return "list";
//	}

}
