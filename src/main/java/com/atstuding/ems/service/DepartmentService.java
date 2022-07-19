package com.atstuding.ems.service;

import com.atstuding.ems.bean.Department;
import com.atstuding.ems.mapper.DepartmentMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @ClassNmae DepartmentService
 * @Description TODO
 * @Author twj280
 * @Date 2022/7/14 0:04
 * @Version 1.0
 **/
@Service
public class DepartmentService {
    @Autowired
    DepartmentMapper departmentMapper;

    public List<Department> getDepts() {
        List<Department> departments = departmentMapper.selectByExample(null);
        return departments;
    }
}
