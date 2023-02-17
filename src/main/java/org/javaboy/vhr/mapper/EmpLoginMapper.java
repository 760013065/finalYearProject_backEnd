package org.javaboy.vhr.mapper;

import org.apache.ibatis.annotations.Select;
import org.javaboy.vhr.model.EmpLogin;

public interface EmpLoginMapper {
    @Select("select * from empLogin where username=#{username}")
    EmpLogin getEmpLogin(String username);
}
