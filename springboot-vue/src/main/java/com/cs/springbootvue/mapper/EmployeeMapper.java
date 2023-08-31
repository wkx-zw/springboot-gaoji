package com.cs.springbootvue.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import com.cs.springbootvue.entity.Employee;
import org.apache.ibatis.annotations.Insert;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * @author yantao
 */
@Repository
public interface EmployeeMapper extends BaseMapper<Employee> {

    List<Employee> queryEmpDept();

   // List<Employee> listEmpDept();
}
