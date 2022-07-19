package com.atstuding.ems.test;

import com.atstuding.ems.bean.Employee;
import com.atstuding.ems.mapper.EmployeeMapper;
import org.apache.ibatis.session.SqlSession;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

/**
 * @ClassNmae SelectDataTest
 * @Description TODO
 * @Author twj280
 * @Date 2022/7/12 16:57
 * @Version 1.0
 **/
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:applicationContext.xml"})
public class SelectDataTest {
    @Autowired
    private EmployeeMapper employeeMapper;
    @Autowired
    private SqlSession sqlSession;

    @Test
    public void testBatchSelect(){
        EmployeeMapper mapper = sqlSession.getMapper(EmployeeMapper.class);
        List<Employee> employees = mapper.selectByExampleWithDept(null);
        employees.forEach(System.out::println);
    }
    @Test
    public void testSelect(){
        List<Employee> employees = employeeMapper.selectByExampleWithDept(null);
        employees.forEach(System.out::println);

    }
    @Test
    public void testSelectById(){
        Employee employee = employeeMapper.selectByPrimaryKeyWithDept(5);
        System.out.println("employee = " + employee);

    }
}
