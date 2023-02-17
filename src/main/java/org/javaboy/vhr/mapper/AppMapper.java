package org.javaboy.vhr.mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.javaboy.vhr.model.Application;

import java.util.List;

public interface AppMapper {
    @Select({"select * from application limit #{page},#{size}"})
    List<Application> selectAll(Integer page,Integer size);

    @Select({"SELECT * from application WHERE status='rejected' or status='approved' limit #{page},#{size}"})
    List<Application> selectProcessed(Integer page,Integer size);

    @Select({"select * from application where status='unprocessed' limit #{page},#{size}"})
    List<Application> selectUnprocessed(Integer page,Integer size);

    @Select({"select count(*) from application"})
    Integer countAll();

    @Select({"SELECT count(*) from application WHERE status='rejected' or status='approved'"})
    Integer selectCountPro();

    @Select({"SELECT count(*) from application WHERE status='unprocessed'"})
    Integer selectCountUnpro();

    @Select({"select * from application where empName = #{name} limit #{page},#{size} "})
    List<Application> selectByName(String name,Integer page,Integer size);

    @Select({"SELECT count(*) from application WHERE empName=#{name}"})
    Integer selectCountName(String name);

    @Select({"select * from application where empNum = #{num}"})
    Application selectByNum(String num);

    @Update({"update application set status=#{status} where id=#{id}"})
    int update(Application application);

    @Insert({"insert into application (empName,empNum,appTime,startTime,endTime,content,provider,cost" +
            ",status,postscript) values(#{empName},#{empNum},#{appTime},#{startTime},#{endTime},#{content},#{provider},#{cost}," +
            "#{status},#{postscript}" +
            ")"})
    Integer insertApp(Application application);




}
