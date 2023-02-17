package org.javaboy.vhr.mapper;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.javaboy.vhr.model.Employee;

import java.util.List;

public interface EmployeeMapper {
    @Select({"SELECT * from employee limit #{page} , #{size}"})
    List<Employee> getEmployeeByPage(Integer page,Integer size);

    @Select({"SELECT count(*) from employee"})
    Long getTotal();

    @Select({"SELECT * from employee where name like concat('%',#{name},'%') limit #{page} , #{size}"})
    List<Employee> empSearch1(Integer page,Integer size,String name);

    @Select({"select count(name) from employee where name like concat('%',#{name},'%')"})
    Long getTotal1(String name);

    @Select({"SELECT * from employee where jobNumber = #{jobNumber}"})
    List<Employee> empSearch2(String jobNumber);

    @Insert({
            "INSERT INTO employee (name,gender,jobNumber,age,birthday,idCard,address,nation,email," +
                    "phone,department,position) " +
                    "VALUES(#{name},#{gender},#{jobNumber},#{age},#{birthday},#{idCard},#{address},#{nation}," +
                    "#{email},#{phone},#{department},#{position})\n"
    })
    Integer insertEmp(Employee employee);

    @Select({"SELECT max(jobNumber) FROM employee"})
    Integer maxJobNumber();

    @Delete({"delete from employee where jobNumber= #{jobNumber}"})
    int delEmp(Integer jobNumber);

    @Update({"update employee set name= #{name},gender=#{gender},birthday=#{birthday}," +
            "age=#{age},idCard=#{idCard},address=#{address},nation=#{nation},email=#{email}," +
            "phone=#{phone},department=#{department},position=#{position} where jobNumber=#{jobNumber}"})
    int updateEmp(Employee employee);

    @Select({"select * from employee where jobNumber=#{jobNumber}"})
    Employee selectByJobNumber(String jobNumber);

    @Select({"select jobNumber from employee where name=#{name}"})
    String getEmpByName(String name);
}
