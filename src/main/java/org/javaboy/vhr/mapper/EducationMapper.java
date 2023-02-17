package org.javaboy.vhr.mapper;

import org.apache.ibatis.annotations.Select;
import org.javaboy.vhr.model.Education;

import java.util.List;

public interface EducationMapper {
    @Select({"select * from education where jobNumber=#{jobNumber}"})
    List<Education> selectEducation(String jobNumber);



}
