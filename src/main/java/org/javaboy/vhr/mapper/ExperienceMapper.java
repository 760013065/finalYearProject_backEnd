package org.javaboy.vhr.mapper;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.javaboy.vhr.model.Experience;


import java.util.HashSet;
import java.util.List;

public interface ExperienceMapper {
    @Select({"select * from experience where jobNumber=#{jobNumber}"})
    List<Experience> selectExperience(String jobNumber);

    @Select({"select jobNumber from experience where position like concat('%',#{position},'%')"})
    HashSet<String> selectExpByPos(String position);

    @Delete({"delete from experience where id=#{id}"})
    int delExp(int id);

    @Insert({"insert into experience (jobNumber,position,startTime,endTime,description) values(" +
            "#{jobNumber},#{position},#{startTime},#{endTime},#{description})"})
    int addExp(Experience exp);

    @Update({"update experience set jobNumber=#{jobNumber},position=#{position},startTime=#{startTime},endTime=#{endTime},description=#{description}" +
            "where id=#{id}"})
    int update(Experience exp);
}
