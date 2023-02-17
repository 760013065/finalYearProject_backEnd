package org.javaboy.vhr.controller;

import org.javaboy.vhr.mapper.AppMapper;
import org.javaboy.vhr.mapper.PlanMapper;
import org.javaboy.vhr.model.Application;
import org.javaboy.vhr.model.Plan;
import org.javaboy.vhr.model.RespBean;
import org.javaboy.vhr.model.RespPageBean;
import org.javaboy.vhr.service.TrainingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/training/basic")
public class TrainingController {

    @Autowired
    TrainingService trainingService;
    @Resource
    PlanMapper planMapper;
    @Resource
    AppMapper appMapper;

    @GetMapping("/empPlan")
    RespPageBean empPlan(String username){
        List<Plan> plans = planMapper.empPlan(username);
        RespPageBean bean=new RespPageBean();
        bean.setData(plans);
        return bean;
    }

    @PostMapping("/empApp")
    RespBean empApp(@RequestBody Application application){
        Date date=new Date(System.currentTimeMillis());
        application.setAppTime(date);
        application.setStatus("unprocessed");
        if (appMapper.insertApp(application)==1) {
            return RespBean.ok("submit successfully");
        }else{
            return RespBean.error("submit failure");
        }
    }

    @GetMapping("/app")
    RespPageBean getApp(@RequestParam(defaultValue = "1") Integer page,
                        @RequestParam(defaultValue = "6") Integer size,
                        String keyword,String status){
        RespPageBean app = trainingService.getApp(page, size, keyword, status);
        return app;
    }

    @GetMapping("/plan")
    RespPageBean getPlan(@RequestParam(defaultValue = "1") Integer page,
                        @RequestParam(defaultValue = "6") Integer size,
                        String keyword,String status){
        RespPageBean app = trainingService.getPlan(page, size, keyword, status);
        return app;
    }

    @PutMapping("/plan")
    RespBean editPlan(@RequestBody Plan plan){
        if(planMapper.updatePlan(plan)==1){
            return RespBean.ok("update successfully");
        }else{
            return  RespBean.error("update failure");
        }
    }

    @PutMapping("/app")
    RespBean rejectOrApproved(@RequestBody Application application){
        if(appMapper.update(application)==1){
            if(application.getStatus().equals("rejected")){
                return RespBean.ok("update successfully");
            }else {
                Plan plan=new Plan();
                plan.setEmpName(application.getEmpName());
                plan.setEmpNum(application.getEmpNum());
                plan.setContent(application.getContent());
                plan.setStartTime(application.getStartTime());
                plan.setEndTime(application.getEndTime());
                plan.setCreateTime(new Date());
                plan.setProvider(application.getProvider());
                plan.setCost(application.getCost());
                plan.setStatus("in progress");
                plan.setPostscript(application.getPostscript());
                planMapper.addPlan(plan);
                return RespBean.ok("update successfully,the system automatically creates the corresponding training plan");
            }
        }else{
            return  RespBean.error("update failure");
        }
    }

    @PostMapping("/plan")
    RespBean addPlan(@RequestBody Plan plan){
        if(planMapper.addPlan(plan)==1){
            return RespBean.ok("add successfully");
        }else{
            return RespBean.ok("add failure");
        }
    }
}
