package com.atstuding.ems.controller;

import com.atstuding.ems.bean.Employee;
import com.atstuding.ems.bean.Msg;
import com.atstuding.ems.service.EmployeeService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.*;

/**
 * @ClassNmae EmployeeController
 * @Description TODO
 * @Author twj280
 * @Date 2022/7/12 15:56
 * @Version 1.0
 **/
@Controller
public class EmployeeController {
    @Autowired
    public EmployeeService employeeService;
    /**
     * @Author: twj280
     * @Description: 获取页面数据信息
     * @date: 23:49 2022/7/19
     * @Param: [model, pn, keywords]
     * @return java.lang.String
     **/
    @RequestMapping("/index")
    public String index(Model model, @RequestParam(value = "pn", defaultValue = "1") Integer pn, @RequestParam(value = "keywords",required = false) String keywords) {
//        System.out.println("keywords = " + keywords);
        //关键字不为空
        if (keywords != "" && keywords != null ){
            PageHelper.startPage(pn, 8);
            List<Employee> employeeList = employeeService.getEmpByKeywords(keywords);
            PageInfo<Employee> pageInfo = new PageInfo<>(employeeList, 5);
            model.addAttribute("employeeList", employeeList);
            model.addAttribute("pageInfo", pageInfo);
            model.addAttribute("word", keywords);
            return "index";
        }

        //关键字为空
        PageHelper.startPage(pn, 8);
        List<Employee> employeeList = employeeService.getAllEmp();
        PageInfo<Employee> pageInfo = new PageInfo<>(employeeList, 5);
        model.addAttribute("employeeList", employeeList);
        model.addAttribute("pageInfo", pageInfo);
        return "index";
    }

    /**
     * @return com.atstuding.ems.bean.Msg
     * @Author: twj280
     * @Description: 校验名字
     * @date: 17:15 2022/7/16
     * @Param: [employee]
     **/
   /* @RequestMapping(value = "/checkEmp",method = RequestMethod.GET)
    @ResponseBody
    public Msg checkName(Employee employee){
        List<Employee> employeeList = employeeService.checkEmp(employee);
        //employeeList != null && employeeList.size() > 0 说明 名字存在
        if (employeeList != null && employeeList.size() > 0){
            //返回失败，名字不可用
            return Msg.fail();
        }
        //返回成功，名字可用
        return Msg.success();

    }*/
    @RequestMapping(value = "/checkEmp", method = RequestMethod.GET)
    @ResponseBody
    public Msg checkName(String empName) {
        //正则匹配
        String regex = "(^[a-zA-Z0-9_-]{6,16}$)|(^[\\u2E80-\\u9FFF]{2,5})";
        if (!empName.matches(regex)) {
            //匹配失败
            //名字必须为6-16位英文和数字组合或者2-5位中文...。
            return Msg.fail().add("va_msg", "1");
        }


        //匹配成功，进行数据库校验
        Boolean result = employeeService.checkEmp(empName);
        if (!result) {
            //名字不可用！！！
            return Msg.fail().add("va_msg", "2");
        }

        return Msg.success();

    }

    /**
     * @return com.atstuding.ems.bean.Msg
     * @Author: twj280
     * @Description: 保存员工信息
     * @date: 11:44 2022/7/16
     * @Param: [employee]
     **/
    @RequestMapping(value = "/emp", method = RequestMethod.POST)
    @ResponseBody
    public Msg saveEmp(@Valid @RequestBody Employee employee, BindingResult result) {

        if (result.hasErrors()) {
            System.out.println("result.getSuppressedFields() = " + result.getSuppressedFields());
            System.out.println("---------------------------");
            List<FieldError> fieldErrors = result.getFieldErrors();
            Map<String, Object> map = new HashMap<>();
            for (FieldError fieldError : fieldErrors) {
                System.out.println("fieldError.getField() = " + fieldError.getField());
                System.out.println("fieldError.getDefaultMessage() = " + fieldError.getDefaultMessage());
                map.put(fieldError.getField(), fieldError.getDefaultMessage());
            }
            return Msg.fail().add("errorFields", map);
        }
        employeeService.saveEmp(employee);
        return Msg.success();


    }

    /*public Msg saveEmp(@RequestParam("empName")String empName,@RequestParam("gender")String gender,@RequestParam("email")String email,@RequestParam("dId")Integer dId){
        Boolean result = employeeService.save(new Employee(null,empName,gender,email,dId));
        if (!result){
            return Msg.fail();
        }
        return Msg.success();

    }*/

    /**
     * @return
     * @Author: twj280
     * @Description: 员工信息查询
     * @date: 14:54 2022/7/18
     * @Param: []
     **/
    @RequestMapping(value = "/emp/{empId}", method = RequestMethod.GET)
    @ResponseBody
    public Msg getEmp(@PathVariable("empId") Integer empId) {
        Employee emp = employeeService.getEmp(empId);
        return Msg.success().add("emp", emp);
    }

    /**
     * @return com.atstuding.ems.bean.Msg
     * @Author: twj280
     * @Description: 修改员工信息
     * @date: 16:17 2022/7/18
     * @Param: [employee]
     **/
    @RequestMapping(value = "/emp/{empId}", method = RequestMethod.PUT)
    @ResponseBody
    public Msg updateEmp(@RequestBody Employee employee) {
        employeeService.updateEmp(employee);
        return Msg.success();
    }

    /**
     * @return com.atstuding.ems.bean.Msg
     * @Author: twj280
     * @Description: 删除单个和多个员工二合一
     * @date: 21:16 2022/7/18
     * @Param: [employee]
     **/
    @RequestMapping("/emp/{ids}")
    @ResponseBody
    public Msg delEmp(@PathVariable("ids") String ids) {
        if (ids != null && ids != "") {
            System.out.println(ids);
            //ids中包含"-",执行批量删除
            if (ids.contains("-")) {
                List<Integer> del_ids = new ArrayList<>();
                String[] del_strs = ids.split("-");
                for (String del_str : del_strs) {
                    del_ids.add(Integer.parseInt(del_str));
                }
                employeeService.batchDel(del_ids);
                return Msg.success();
            }

            //执行单个删除
            employeeService.delEmp(Integer.parseInt(ids));
            return Msg.success();
        }
//        return Msg.fail();
        throw new RuntimeException("删除异常");
    }
    /*@RequestMapping("/search")
    @ResponseBody
    public Msg search(@){

    }*/

}
