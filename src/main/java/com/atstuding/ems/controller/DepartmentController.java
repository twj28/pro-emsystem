package com.atstuding.ems.controller;

import com.atstuding.ems.bean.Department;
import com.atstuding.ems.bean.Msg;
import com.atstuding.ems.service.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;


import java.util.List;

/**
 * @ClassNmae DepartmentController
 * @Description TODO
 * @Author twj280
 * @Date 2022/7/14 0:00
 * @Version 1.0
 **/
@Controller
public class DepartmentController {
    @Autowired
    public DepartmentService departmentService;

    /*@RequestMapping(value = "/depts",method = RequestMethod.GET)
    @ResponseBody
    public List<Department> getDepts(){
        List<Department> departments = departmentService.getDepts();
        return departments;
    }*/
    @RequestMapping(value = "/depts", method = RequestMethod.GET)
    @ResponseBody
    public Msg getDepts() {
        List<Department> departments = departmentService.getDepts();
        return Msg.success().add("depts", departments);
    }
}
