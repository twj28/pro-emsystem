package com.atstuding.ems.service;

import com.atstuding.ems.bean.Employee;
import com.atstuding.ems.bean.EmployeeExample;
import com.atstuding.ems.mapper.EmployeeMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @ClassNmae EmployeeService
 * @Description TODO
 * @Author twj280
 * @Date 2022/7/12 16:02
 * @Version 1.0
 **/
@Service
public class EmployeeService {
    @Autowired
    private EmployeeMapper employeeMapper;
    public List<Employee> getAllEmp() {

        List<Employee> employees = employeeMapper.selectByExampleWithDept(null);
        return employees;
    }

    public Boolean checkEmp(String empName) {
        EmployeeExample example = new EmployeeExample();
        example.createCriteria().andEmpNameEqualTo(empName);
        long count = employeeMapper.countByExample(example);
        //返回true表示没查询到。
        return count == 0;
    }

    public void saveEmp(Employee employee) {
       employeeMapper.insertSelective(employee);

    }

    public Employee getEmp(Integer id) {
        Employee employee = employeeMapper.selectByPrimaryKeyWithDept(id);
        return employee;
    }

    public void updateEmp(Employee employee) {
        employeeMapper.updateByPrimaryKeySelective(employee);

    }

    public void delEmp(Integer id) {
        employeeMapper.deleteByPrimaryKey(id);
    }

    public void batchDel(List<Integer> ids) {
        EmployeeExample example = new EmployeeExample();
        example.createCriteria().andEmpIdIn(ids);
        employeeMapper.deleteByExample(example);
    }

    public List<Employee> getEmpByKeywords(String keywords) {
        EmployeeExample example = new EmployeeExample();
        example.createCriteria().andEmpNameLike("%" + keywords +"%");
        List<Employee> employees = employeeMapper.selectByExampleWithDept(example);
        return employees;
    }
}
