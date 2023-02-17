package org.javaboy.vhr.mapper;

import org.apache.ibatis.annotations.Select;
import org.javaboy.vhr.model.Experience;


import java.util.HashSet;
import java.util.List;

public interface ExperienceMapper {
    @Select({"select * from experience where jobNumber=#{jobNumber}"})
    List<Experience> selectExperience(String jobNumber);

    @Select({"select jobNumber from experience where position like concat('%',#{position},'%')"})
    HashSet<String> selectExpByPos(String position);


}
