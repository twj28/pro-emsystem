package com.atstuding.ems.test;

import com.atstuding.ems.bean.Department;
import com.atstuding.ems.bean.Employee;
import com.atstuding.ems.mapper.DepartmentMapper;
import com.atstuding.ems.mapper.EmployeeMapper;
import org.apache.ibatis.session.SqlSession;


import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Random;
import java.util.UUID;

/**
 * @ClassNmae InsertDataTest
 * @Description TODO
 * @Author twj280
 * @Date 2022/7/12 1:25
 * @Version 1.0
 **/
@RunWith(SpringJUnit4ClassRunner.class)//使用Spring的test 来测试
@ContextConfiguration(locations = {"classpath:applicationContext.xml"})//指定spring配置文件的位置
public class InsertDataTest {
    @Autowired
    private SqlSession sqlSession;
    @Autowired
    private DepartmentMapper departmentMapper;
    
    @Test
    public void InsertDataTest(){
        //批量插入
        EmployeeMapper mapper = sqlSession.getMapper(EmployeeMapper.class);
        for (int i = 0; i < 500; i++) {
            int result = mapper.insertSelective(new Employee(null, getName(), getGender(), getName() + "@126.com", getDid()));

        }
        System.out.println("员工数据插入完成！！！");


    }
    //随机生成性别
    public static String getGender(){
        Random random = new Random();
        int sexInt = random.nextInt(10);
        if (sexInt < 5){
            return "M";
        }
        return "F";

    }
    //随机生成名字
    public static String getName(){
        return UUID.randomUUID().toString().substring(0,6);
    }
    //随机生成名字
    public static int getDid(){
        Random random = new Random();
        return random.nextInt(1, 5);
    }
    @Test
    public void insertDeptDataTest(){
        /*
        int result = departmentMapper.insert(new Department(null, "人事部"));
        System.out.println("result = " + result);
        */



    }
}
