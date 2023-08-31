package com.cs.springbootvue.service;

import com.cs.springbootvue.entity.Department;
import com.cs.springbootvue.mapper.DepartmentMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author yantao
 */
@Service
public class DepartmentService {

    @Autowired
    private DepartmentMapper departmentMapper;

    public List<Department> listDept() {
        return departmentMapper.selectList(null);
    }
}
