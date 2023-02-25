package org.javaboy.vhr.mapper;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.javaboy.vhr.model.Experience;
import org.javaboy.vhr.model.Interest;

import java.util.HashSet;
import java.util.List;

public interface InterestMapper {
    @Select({"select * from interest where jobNumber=#{jobNumber}"})
    List<Interest> selectInterest(String jobNumber);

    @Select({"select jobNumber from interest where interest like concat('%',#{inte},'%')"})
    HashSet<String> selectByInt(String inte);

    @Delete({"delete from interest where id=#{id}"})
    int delInterest(int id);

    @Insert({"insert into interest (interest,description,jobNumber) values(#{interest},#{description},#{jobNumber})"})
    int addInterest(Interest interest);

    @Update({"update interest set interest=#{interest},description=#{description},jobNumber=#{jobNumber} where id=#{id}"})
    int updateInterest(Interest interest);
}
