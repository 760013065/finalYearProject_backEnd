package org.javaboy.vhr.mapper;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.javaboy.vhr.model.Education;

import java.util.List;

public interface EducationMapper {
    @Select({"select * from education where jobNumber=#{jobNumber}"})
    List<Education> selectEducation(String jobNumber);

    @Delete({"delete from education where id=#{id}"})
    int delEdu(int id);

    @Insert({"insert into education (degree,school,major,grades,startTime,endTime,jobNumber) " +
            "values(#{degree},#{school},#{major},#{grades},#{startTime},#{endTime},#{jobNumber}) "})
    int add(Education education);

    @Update({"update education set degree=#{degree},school=#{school},major=#{major},grades=#{grades},startTime=#{startTime},endTime=#{endTime},jobNumber=#{jobNumber} where id=#{id}"})
    int update(Education education);
}
