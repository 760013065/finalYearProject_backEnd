package org.javaboy.vhr.service;


import org.javaboy.vhr.mapper.EmployeeMapper;
import org.javaboy.vhr.mapper.ExperienceMapper;
import org.javaboy.vhr.mapper.InterestMapper;
import org.javaboy.vhr.mapper.SkillMapper;
import org.javaboy.vhr.model.Content;
import org.javaboy.vhr.model.Employee;
import org.javaboy.vhr.model.RespPageBean;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;


@Service
public class EmployeeService {
    @Resource
    EmployeeMapper employeeMapper;
    @Resource
    ExperienceMapper experienceMapper;
    @Resource
    SkillMapper skillMapper;
    @Resource
    InterestMapper interestMapper;

    public RespPageBean getEmployeeByPage(Integer page, Integer size, String keyword){
        if(page!=null && size!=null){
            page=(page-1)*size;
        }
        if(keyword==null || keyword.equals("")){
            List<Employee> data=employeeMapper.getEmployeeByPage(page,size);
            Long total=employeeMapper.getTotal();
            RespPageBean bean=new RespPageBean();
            bean.setData(data);
            bean.setTotal(total);
            return bean;
        }else{
            if (judgeNumber(keyword)) {
                List<Employee> data=employeeMapper.empSearch2(keyword);
                Long total=1l;
                RespPageBean bean=new RespPageBean();
                bean.setData(data);
                bean.setTotal(total);
                return bean;
            }else {
                List<Employee> data=employeeMapper.empSearch1(page,size,keyword);
                Long total=employeeMapper.getTotal1(keyword);
                RespPageBean bean=new RespPageBean();
                bean.setTotal(total);
                bean.setData(data);
                return bean;
            }
        }

    }

    public boolean judgeNumber(String keyword){
        for(int i=0;i<keyword.length();i++){
            if(keyword.charAt(i)-'0'>9 || keyword.charAt(i)-'0'<0){
                return false;
            }
        }
        return true;
    }


    public Integer addEmp(Employee employee) {
        return employeeMapper.insertEmp(employee);
    }

    public int delEmp(Integer jobNumber) {
        return employeeMapper.delEmp(jobNumber);
    }

    public List<Employee> advancedSearch(Content content){
        String experience = content.getExperience();
        String interest = content.getInterest();
        String skill = content.getSkill();

        String[] splitExp ;
        String[] splitInt ;
        String[] splitSki ;
        List<Employee> employees=new ArrayList<>();

        splitExp = experience.split("/");
        splitInt = interest.split("/");
        splitSki = skill.split("/");

        HashSet<String> employeesExp = processExp(splitExp);
        HashSet<String> employeesSki = processSki(splitSki);
        HashSet<String> employeesInt = processInt(splitInt);

        //又没查到的直接过滤掉了
        int m=1;
        if(!experience.equals("")){
            m=m*employeesExp.size();
        }
        if(!interest.equals("")){
            m=m*employeesInt.size();
        }
        if(!skill.equals("")){
            m=m*employeesSki.size();
        }
        if(m==0){
            return new ArrayList<>();
        }

        if(experience.equals("") && skill.equals("") && interest.equals("")){
            return new ArrayList<>();
        } else if (!experience.equals("") && skill.equals("") && interest.equals("")) {
            for(String s:employeesExp){
                Employee employee = employeeMapper.selectByJobNumber(s);
                employees.add(employee);
            }
            return employees;
        } else if (experience.equals("") && !skill.equals("") && interest.equals("")) {
            for(String s:employeesSki){
                Employee employee = employeeMapper.selectByJobNumber(s);
                employees.add(employee);
            }
            return employees;
        }else if(experience.equals("") && skill.equals("") && !interest.equals("")){
            for(String s:employeesInt){
                Employee employee = employeeMapper.selectByJobNumber(s);
                employees.add(employee);
            }
            return employees;
        }else if(!experience.equals("") && !skill.equals("") && interest.equals("")){
            employeesExp.retainAll(employeesSki);
            for(String s:employeesExp){
                Employee employee = employeeMapper.selectByJobNumber(s);
                employees.add(employee);
            }
            return employees;
        }else if(!experience.equals("") && skill.equals("") && !interest.equals("")){
            employeesExp.retainAll(employeesInt);
            for(String s:employeesExp){
                Employee employee = employeeMapper.selectByJobNumber(s);
                employees.add(employee);
            }
            return employees;
        }else if(experience.equals("") && !skill.equals("") && !interest.equals("")){
            employeesSki.retainAll(employeesInt);
            for(String s:employeesSki){
                Employee employee = employeeMapper.selectByJobNumber(s);
                employees.add(employee);
            }
            return employees;
        }else {//全非空
            employeesExp.retainAll(employeesInt);
            employeesExp.retainAll(employeesSki);
            for(String s:employeesExp){
                Employee employee = employeeMapper.selectByJobNumber(s);
                employees.add(employee);
            }
            return employees;
        }



    }

    public HashSet<String> processExp(String[] split){
        if(split[0].equals("")){
            return new HashSet<>();
        }
        HashSet<String> all=new HashSet<>();
        for(int i=0;i<split.length;i++){
            HashSet<String> hashSet = experienceMapper.selectExpByPos(split[i]);
            if(i==0){
                all.addAll(hashSet);
                continue;
            }
            all.retainAll(hashSet);
        }



        return all;
    }
    public HashSet<String> processSki(String[] split){
        if(split[0].equals("")){
            return new HashSet<>();
        }

        HashSet<String> all=new HashSet<>();
        for(int i=0;i<split.length;i++){
            HashSet<String> hashSet = skillMapper.selectBySki(split[i]);
            if(i==0){
                all.addAll(hashSet);
                continue;
            }
            all.retainAll(hashSet);
        }

        return all;
    }

    public HashSet<String> processInt(String[] split){

        if(split[0].equals("")){
            return new HashSet<>();
        }

        HashSet<String> all=new HashSet<>();
        for(int i=0;i<split.length;i++){
            HashSet<String> hashSet = interestMapper.selectByInt(split[i]);
            if(i==0){
                all.addAll(hashSet);
                continue;
            }
            all.retainAll(hashSet);
        }



        return all;
    }
}
