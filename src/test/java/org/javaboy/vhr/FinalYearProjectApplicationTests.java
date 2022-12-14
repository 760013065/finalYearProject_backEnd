package org.javaboy.vhr;

import org.javaboy.vhr.mapper.EmployeeMapper;
import org.javaboy.vhr.model.Employee;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

@SpringBootTest
class FinalYearProjectApplicationTests {

    @Resource
    EmployeeMapper employeeMapper;

    @Test
    void test1() {
        Employee employee = employeeMapper.selectByPrimaryKey(11);
        System.out.println(employee);
    }

}
