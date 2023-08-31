package com.cs.springbootvue.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;

import com.cs.springbootvue.annotation.PassToken;
import com.cs.springbootvue.entity.Department;
import com.cs.springbootvue.entity.Employee;
import com.cs.springbootvue.mapper.EmployeeMapper;
import com.cs.springbootvue.service.DepartmentService;
import com.cs.springbootvue.service.EmployeeService;
import com.cs.springbootvue.utils.BaseResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author yantao
 */
@Slf4j
@RestController
@RequestMapping("emp")
@CrossOrigin
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    @Autowired
    private DepartmentService departmentService;

    @Autowired
    EmployeeMapper employeeMapper;

    @PassToken
    @GetMapping("listempl")
    public List<Employee> createEmployee(){
        List<Employee> employees1 = employeeMapper.queryEmpDept();
        for (Employee employee : employees1) {
            System.out.println(employee);
        }
        return employees1;
    }


    @GetMapping("/emps")
    public BaseResult emPage2(@RequestParam(value = "currentPage", required = false, defaultValue = "") Integer currentPage,
                              @RequestParam(value = "pageSize", required = false, defaultValue = "") Integer pageSize,
                              @RequestParam(value = "searchName", required = false, defaultValue = "") String searchName) {

        IPage<Employee> ep = employeeService.pageEmp(searchName, currentPage, pageSize);
        System.out.println("123123123");
        return BaseResult.getBaseResult(0, "success", ep);
    }

  /*  @PassToken
    @GetMapping("/emps/{currentPage}")
    public BaseResult emPage(@PathVariable("currentPage") Integer currentPage,
                              @RequestParam("pageSize") int pageSize,
                              @RequestParam(value = "searchName", required = false, defaultValue = "") String searchName) {
        IPage<Employee> ep = employeeService.pageEmp(searchName, currentPage, pageSize);
        System.out.println("123123123");
        return BaseResult.getBaseResult(0,"success",ep);
    }*/

   /* @GetMapping("/emp/toadd")
    public String toAdd(Map<String, Object> map) {
        List<Department> depts = departmentService.listDept();
        map.put("depts", depts);
        return "emp/add";
    }*/
   @GetMapping("/depts")
   public BaseResult listDept(){

       List<Department> depts = departmentService.listDept();
       System.out.println(depts);
       return BaseResult.getBaseResult(0, "SUCCESS", depts);
   }


    @PostMapping("/add")
    public BaseResult addEmp(@RequestBody Employee employee) {
        System.out.println("22222222222" + employee);
        employeeService.addEmployee(employee);

        return BaseResult.getBaseResult(0, "success",null);

    }

  /*  @GetMapping("/emp/toupdate/{empId}/{page}")
    public String toUpdate(@PathVariable("empId") Integer empId,
                           @PathVariable("page") Integer page, Map<String, Object> map) {
        Employee employee = employeeService.getEmployee(empId);
        List<Department> depts = departmentService.listDept();
        map.put("emp", employee);
        map.put("depts", depts);
        map.put("page", page);
        return "emp/update";
    }*/


    @PutMapping("/emp/update")
    public BaseResult updateEmp(Employee employee) {
        System.out.println("========" + employee);

        employeeService.updateEmployee(employee);
        return BaseResult.getBaseResult(0, "success", null);
    }
/*
    @GetMapping("/emp/delete/{empId}/{page}")
    public String deleteEmp(@PathVariable("empId") Integer empId,
                            @PathVariable("page") Integer page) {
        employeeService.deleteEmployee(empId);

        return "redirect:/emps/" + page;
    }*/

    @DeleteMapping("/delete/{empId}")
    public BaseResult deleteEmp2(@PathVariable("empId") Integer empId) {
        try {
            System.out.println();
        }catch (Exception e){
            System.out.println();
        }
        log.info("进来了删除方法   :    " + empId);
        employeeService.deleteEmployee(empId);
        return BaseResult.getBaseResult(0, "success", null);
    }

}
