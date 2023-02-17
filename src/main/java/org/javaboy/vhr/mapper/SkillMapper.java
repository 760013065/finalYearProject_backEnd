package org.javaboy.vhr.mapper;

import org.apache.ibatis.annotations.Select;
import org.javaboy.vhr.model.Experience;
import org.javaboy.vhr.model.Skill;

import java.util.HashSet;
import java.util.List;

public interface SkillMapper {

    @Select({"select * from skill where jobNumber=#{jobNumber}"})
    List<Skill> selectSkill(String jobNumber);

    @Select({"select jobNumber from skill where skill like concat('%',#{ski},'%')"})
    HashSet<String> selectBySki(String ski);
}
