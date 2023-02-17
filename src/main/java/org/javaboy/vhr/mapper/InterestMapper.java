package org.javaboy.vhr.mapper;

import org.apache.ibatis.annotations.Select;
import org.javaboy.vhr.model.Experience;
import org.javaboy.vhr.model.Interest;

import java.util.HashSet;
import java.util.List;

public interface InterestMapper {
    @Select({"select * from interest where jobNumber=#{jobNumber}"})
    List<Interest> selectInterest(String jobNumber);

    @Select({"select jobNumber from interest where interest like concat('%',#{inte},'%')"})
    HashSet<String> selectByInt(String inte);
}
