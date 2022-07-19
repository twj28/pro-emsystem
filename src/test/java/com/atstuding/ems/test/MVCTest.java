package com.atstuding.ems.test;

import com.atstuding.ems.bean.Employee;
import com.github.pagehelper.PageInfo;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.context.WebApplicationContext;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * @ClassNmae MVCTest
 * @Description TODO
 * @Author twj280
 * @Date 2022/7/19 23:58
 * @Version 1.0
 **/
@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(locations = {"classpath:applicationContext.xml","file:src/main/webapp/WEB-INF/dispatcherServlet-servlet.xml"})
public class MVCTest {
    public MockMvc mockMvc;
    @Autowired
    public WebApplicationContext context;

    @Before
    public void initMockMVC() {
        //初始化获取MockMVC对象
        mockMvc = MockMvcBuilders.webAppContextSetup(context).build();
    }
    @Test
    public void test() throws Exception {
//        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.get("/index").param("pn", "6")).andReturn();
        //方式一：
//        MultiValueMap multiValueMap = new LinkedMultiValueMap();
//        multiValueMap.add("pn","6");
//        multiValueMap.add("keywords","5");
//
//        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.get("/index").params(multiValueMap)).andReturn();
        //方式二：
        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.get("/index").param("pn", "6").param("keywords", "5")).andReturn();

        PageInfo<Employee> pageInfo = (PageInfo<Employee>) result.getRequest().getAttribute("pageInfo");
        System.out.println("pageInfo.getPageNum() = " + pageInfo.getPageNum());
        System.out.println("pageInfo.getPages() = " + pageInfo.getPages());

        int[] navigatepageNums = pageInfo.getNavigatepageNums();
        System.out.println("navigatepageNums = " + Arrays.toString(navigatepageNums));
    }

}
