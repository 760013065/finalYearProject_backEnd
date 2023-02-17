package org.javaboy.vhr.controller;


import org.javaboy.vhr.mapper.*;
import org.javaboy.vhr.model.*;
import org.javaboy.vhr.service.EmployeeService;
import org.javaboy.vhr.utils.Mail;
import org.javaboy.vhr.utils.POIUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/employee/basic")
public class EmpBasicController {
    @Autowired
    private EmployeeService employeeService;

    @Resource
    EmployeeMapper employeeMapper;

    @Resource
    ExperienceMapper experienceMapper;

    @Resource
    SkillMapper skillMapper;

    @Resource
    InterestMapper interestMapper;

    @Resource
    EducationMapper educationMapper;

    @GetMapping("/")
    public RespPageBean getEmployeeByPage(@RequestParam(defaultValue = "1") Integer page,
                                          @RequestParam(defaultValue = "10") Integer size,
                                          String keyword) {
        return employeeService.getEmployeeByPage(page, size,keyword);
    }

    @PostMapping("/")
    public RespBean addEmp(@RequestBody Employee employee){
        if(employeeService.addEmp(employee)==1){
            Mail.sendMail(employee.getEmail(),"Dear "+employee.getName()+", welcome to our team! Thank you for choosing to join us and let us work hard together to create more value." +
                    "Please keep in mind that your job number is <h1 style=\"color: red\">"+employee.getJobNumber()+"</h1>","welcome!");
            return RespBean.ok("add successfully");
        }
        return RespBean.error("add failure");
    }

    @GetMapping("/maxJobNumber")
    public RespBean MaxJobNumber(){
        RespBean bean=RespBean.build().setStatus(200).setObj(employeeMapper.maxJobNumber()+1);
        return bean;
    }

    @DeleteMapping("/{jobNumber}")
    public RespBean delEmp(@PathVariable Integer jobNumber){
        Employee employee = employeeMapper.selectByJobNumber(String.valueOf(jobNumber));
        if(employeeService.delEmp(jobNumber)==1){
            Mail.sendMail(employee.getEmail(),"Dear "+employee.getName()+", thank you for your contribution to the development of the company and wish you all the best in the future!","GoodBye!");
            return  RespBean.ok("delete successfully");
        }
        return  RespBean.error("delete failure");
    }

    @PutMapping("/")
    public RespBean updateEmp(@RequestBody Employee employee){
        if(employeeMapper.updateEmp(employee)==1){
            return RespBean.ok("update successfully");
        }
        return  RespBean.error("update failure");
    }

    @PostMapping("/test")
    public RespBean test(@RequestBody Content content){
        System.out.println(content.getInterest());
        return RespBean.ok("yes");
    }

    @PostMapping("/adSearch")
    public RespPageBean adSearch(@RequestBody Content content){
        List<Employee> data = employeeService.advancedSearch(content);
        Long total= Long.valueOf(data.size());
        RespPageBean bean=new RespPageBean();
        bean.setData(data);
        bean.setTotal(total);
        return bean;
    }

    @GetMapping("/viewAd/{jobNumber}")
    public AdvancedSearchBean viewAd(@PathVariable String jobNumber){
        AdvancedSearchBean bean=new AdvancedSearchBean();
        List<Interest> inte = interestMapper.selectInterest(jobNumber);
        List<Skill> skills = skillMapper.selectSkill(jobNumber);
        List<Experience> experiences = experienceMapper.selectExperience(jobNumber);
        List<Education> educations = educationMapper.selectEducation(jobNumber);
        bean.setEdu(educations);
        bean.setExp(experiences);
        bean.setInte(inte);
        bean.setSki(skills);
        return bean;
    }

    @GetMapping("/export")
    public ResponseEntity<byte[]> exportData(){
        Long total = employeeMapper.getTotal();
        Integer size= Math.toIntExact(total);
        List<Employee> list = employeeMapper.getEmployeeByPage(0, size);
        return POIUtils.employee2Excel(list);
    }

    @PostMapping("/import")
    public RespBean importData(MultipartFile file) throws IOException {
//        file.transferTo(new File("/Users/liuwenshuai/Desktop/finalYearProject/src/main/resources/files","upload.xls"));
        List<Employee> list = POIUtils.excel2Employee(file);

        for(Employee e:list){
            if(e.getName()!=null){
                employeeMapper.insertEmp(e);
            }
        }
        return  RespBean.ok("import successfully");
    }
}
