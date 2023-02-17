package org.javaboy.vhr.controller;

import org.javaboy.vhr.mapper.EmployeeMapper;
import org.javaboy.vhr.mapper.ProjectMapper;
import org.javaboy.vhr.model.*;
import org.javaboy.vhr.service.ProService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/pro/basic")
public class ProjectController {
    @Autowired
    ProService proService;
    @Resource
    ProjectMapper projectMapper;
    @Resource
    EmployeeMapper employeeMapper;

    @GetMapping("/empTask")
    RespPageBean getEmpTask(String username){
        String num = employeeMapper.getEmpByName(username);
        List<Task> taskByNum = projectMapper.getTaskByNum(num);
        List<TaskEmp> ans=new ArrayList<>();
        for(Task t:taskByNum){
            TaskEmp taskEmp=new TaskEmp();
            taskEmp.setContent(t.getContent());
            taskEmp.setEvaluation(t.getEvaluation());
            taskEmp.setStatus(t.getStatus());
            taskEmp.setStart(projectMapper.getProById(t.getProId()).getStartTime());
            taskEmp.setEnd(projectMapper.getProById(t.getProId()).getEndTime());
            ans.add(taskEmp);
        }
        RespPageBean bean=new RespPageBean();
        bean.setData(ans);
        return bean;

    }

    @GetMapping("/")
    RespPageBean getPro(@RequestParam(defaultValue = "1") Integer page,
                        @RequestParam(defaultValue = "6") Integer size,
                        String status){
        RespPageBean pro = proService.getPro(page, size, status);
        return pro;
    }

    @GetMapping("/tasks")
    RespPageBean getTasks(Integer proId){
        List<Task> tasks = projectMapper.getTasks(proId);
        for(Task t:tasks){
            Employee employee = employeeMapper.selectByJobNumber(t.getEmpNum());
            t.setEmpName(employee.getName());
        }
        RespPageBean bean=new RespPageBean();
        bean.setData(tasks);
        return bean;
    }

    @PostMapping("/add")
    void addTask(@RequestBody Task task,Integer proId){
        task.setProId(proId);
        task.setStatus("incomplete");
        projectMapper.insertTask(task);
    }
    @PostMapping("/addPro")
    RespBean addPro(@RequestBody Project project){
        if(projectMapper.insertPro(project)==1){
            return RespBean.ok("create successfully");
        }else{
            return RespBean.ok("create failure");
        }
    }

    @GetMapping("/who")
    String getWho(String empNum){
        Employee employee = employeeMapper.selectByJobNumber(empNum);
        return employee.getName();
    }

    @PostMapping("/test")
    RespBean test(){
        return RespBean.ok("yes");
    }

    @GetMapping("/maxId")
    Integer getMaxId(){
        return projectMapper.getMax()+1;
    }

    @PutMapping("/editPro")
    RespBean editPro(@RequestBody Project project){
        if(projectMapper.updatePro(project)==1){
            return RespBean.ok("update successfully");
        }else{
            return RespBean.error("update failure");
        }
    }

    @DeleteMapping("/delTask")
    RespBean del(Integer id){
        if(projectMapper.delTask(id)==1){
            return RespBean.ok("delete successfully");
        }else{
            return RespBean.error("delete failure");
        }
    }

    @PostMapping("/editTask")
    void edit(@RequestBody Task task,Integer proId){
        if(task.getEmpNum().equals("") || task.getEmpNum()==null || task.getContent().equals("") || task.getContent()==null){
            return;
        }
        task.setProId(proId);
        if(task.getId()==0){
//            System.out.println(task);
//            System.out.println("charu");
            projectMapper.insertTask(task);
        }else{
//            System.out.println(task);
//            System.out.println("gengxin");
            projectMapper.updateTask(task);
        }
    }
    @GetMapping("/rank")
    RespPageBean rank(){
        List<Task> avg = projectMapper.avg();
        for(Task t:avg){
            t.setEmpName(employeeMapper.selectByJobNumber(t.getEmpNum()).getName());
        }
        RespPageBean bean=new RespPageBean();
        bean.setData(avg);
        return bean;
    }
}
