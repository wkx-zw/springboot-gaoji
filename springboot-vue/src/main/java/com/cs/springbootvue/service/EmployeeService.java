package com.cs.springbootvue.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

import com.cs.springbootvue.entity.Employee;
import com.cs.springbootvue.mapper.EmployeeMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

/**
 * @author yantao
 */
@Service
public class EmployeeService {

    @Autowired
    private EmployeeMapper employeeMapper;

    public IPage<Employee> pageEmp(String searchName, int pageNum, int pageSize) {
        Page<Employee> empPage = new Page<>(pageNum, pageSize);
        QueryWrapper<Employee> queryWrapper = new QueryWrapper<Employee>();
        if (searchName != null) {
            queryWrapper.like("emp_name", searchName);
        }

        return employeeMapper.selectPage(empPage, queryWrapper);
    }

    public List<Employee> listEmployee(String searchName){
        QueryWrapper<Employee> queryWrapper = new QueryWrapper<Employee>();
        if (searchName != null) {
            queryWrapper.like("emp_name", searchName);
        }
        queryWrapper.orderByDesc("emp_id");
        return employeeMapper.selectList(queryWrapper);
    }

    public void addEmployee(Employee employee) {
        System.out.println("12321");
        employeeMapper.insert(employee);
    }


    public void deleteEmployee(int empId) {
        employeeMapper.deleteById(empId);
    }

    public Employee getEmployee(int empId) {
        return employeeMapper.selectById(empId);
    }

    public void updateEmployee(Employee employee) {
        employeeMapper.updateById(employee);
    }

    public List<Employee> employeeList(){
        return employeeMapper.selectList(null);
    }




}
