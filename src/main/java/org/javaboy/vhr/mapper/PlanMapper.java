package org.javaboy.vhr.mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.javaboy.vhr.model.Application;
import org.javaboy.vhr.model.Plan;

import java.util.List;

public interface PlanMapper {
    @Select({"select * from plan limit #{page},#{size}"})
    List<Plan> selectAll(Integer page,Integer size);

    @Select({"select count(*) from plan "})
    Integer selectCountAll();

    @Select({"select * from plan where empName=#{name} limit #{page},#{size}"})
    List<Plan> selectByName(Integer page,Integer size,String name);

    @Select({"select count(*) from plan where empName=#{name}"})
    Integer selectCountName(String name);

    @Select({"select * from plan where status=#{status} limit #{page},#{size}"})
    List<Plan> selectByInPro(Integer page,Integer size,String status);

    @Select({"select count(*) from plan where status=#{status}"})
    Integer selectCountInPro(String status);

    @Select({"select * from plan where empNum = #{num}"})
    Plan selectByNum(String num);

    @Update({"update plan set empName=#{empName},content=#{content},startTime=#{startTime}," +
            "endTime=#{endTime},createTime=#{createTime},provider=#{provider},cost=#{cost}," +
            "evaluation=#{evaluation},status=#{status},postscript=#{postscript} where id=#{id} "})
    int updatePlan(Plan plan);

    @Insert({"INSERT INTO plan (empName,empNum,content,status,postscript,startTime,endTime,provider,cost,createTime,evaluation)" +
            "VALUES(#{empName},#{empNum},#{content},#{status},#{postscript},#{startTime},#{endTime},#{provider},#{cost},#{createTime},#{evaluation}) " })
    int addPlan(Plan plan);

    @Select({"select * from plan where empName=#{empName}"})
    List<Plan> empPlan(String empName);


}
