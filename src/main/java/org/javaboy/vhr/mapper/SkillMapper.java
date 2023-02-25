package org.javaboy.vhr.mapper;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.javaboy.vhr.model.Experience;
import org.javaboy.vhr.model.Skill;

import java.util.HashSet;
import java.util.List;

public interface SkillMapper {

    @Select({"select * from skill where jobNumber=#{jobNumber}"})
    List<Skill> selectSkill(String jobNumber);

    @Select({"select jobNumber from skill where skill like concat('%',#{ski},'%')"})
    HashSet<String> selectBySki(String ski);

    @Delete({"delete from skill where id=#{id}"})
    int delSki(int id);

    @Insert({"insert into skill (skill,description,jobNumber) values(#{skill},#{description},#{jobNumber})"})
    int addSkill(Skill skill);

    @Update({"update skill set skill=#{skill},description=#{description},jobNumber=#{jobNumber} where id=#{id}"})
    int updateSkill(Skill skill);
}
