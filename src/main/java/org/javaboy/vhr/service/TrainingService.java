package org.javaboy.vhr.service;

import org.javaboy.vhr.mapper.AppMapper;
import org.javaboy.vhr.mapper.PlanMapper;
import org.javaboy.vhr.model.Application;
import org.javaboy.vhr.model.Plan;
import org.javaboy.vhr.model.RespPageBean;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Service
public class TrainingService {
    @Resource
    AppMapper appMapper;
    @Resource
    PlanMapper planMapper;

    public RespPageBean getApp(Integer page,Integer size,String keyword,String status){
        if(page!=null && size!=null){
            page=(page-1)*size;
        }
        if(keyword.equals("") && status.equals("")){
            List<Application> applications = appMapper.selectAll(page, size);
            Long total= Long.valueOf(appMapper.countAll());
            RespPageBean bean=new RespPageBean();
            bean.setData(applications);
            bean.setTotal(total);
            return bean;
        }else if(!keyword.equals("") && !status.equals("")){
            size=appMapper.countAll();
            RespPageBean app = getApp(1, size, keyword, "");
            RespPageBean app1 = getApp(1, size, "", status);
            List<Application> data = (List<Application>) app.getData();
            List<Application> data1 = (List<Application>) app1.getData();
            data.retainAll(data1);
//            List<String> con=new ArrayList<>();
//            List<String> con1=new ArrayList<>();
//            for(Application a:data){
//                con.add(a.getEmpNum());
//            }
//            for(Application a:data1){
//                con1.add(a.getEmpNum());
//            }
//            con.retainAll(con1);
//            List<Application> res=new ArrayList<>();
//            for(String s:con){
//                Application application = appMapper.selectByNum(s);
//                res.add(application);
//            }
            RespPageBean bean=new RespPageBean();
            bean.setData(data);
            bean.setTotal((long) data.size());
            return bean;


        }else if(!keyword.equals("") && status.equals("")){
            List<Application> applications = appMapper.selectByName(keyword,page,size);
            Long total = Long.valueOf(appMapper.selectCountName(keyword));
            RespPageBean bean=new RespPageBean();
            bean.setData(applications);
            bean.setTotal(total);
            return bean;
        }else{
            if(status.equals("processed")){
                List<Application> applications = appMapper.selectProcessed(page, size);
                Long total= Long.valueOf(appMapper.selectCountPro());
                RespPageBean bean=new RespPageBean();
                bean.setData(applications);
                bean.setTotal(total);
                return bean;
            }else if(status.equals("unprocessed")){
                List<Application> applications = appMapper.selectUnprocessed(page, size);
                Long total= Long.valueOf(appMapper.selectCountUnpro());
                RespPageBean bean=new RespPageBean();
                bean.setData(applications);
                bean.setTotal(total);
                return bean;
            }else{
                List<Application> applications = appMapper.selectAll(page, size);
                Long total= Long.valueOf(appMapper.countAll());
                RespPageBean bean=new RespPageBean();
                bean.setData(applications);
                bean.setTotal(total);
                return bean;
            }
        }

    }

    public RespPageBean getPlan(Integer page, Integer size, String keyword, String status) {
        if(page!=null && size!=null){
            page=(page-1)*size;
        }
        if(keyword.equals("") && status.equals("")){
            List<Plan> plans = planMapper.selectAll(page, size);
            Long total= Long.valueOf(planMapper.selectCountAll());
            RespPageBean bean=new RespPageBean();
            bean.setData(plans);
            bean.setTotal(total);
            return bean;
        }else if(!keyword.equals("") && !status.equals("")){
            size=planMapper.selectCountAll();
            RespPageBean p1 = getPlan(1, size, keyword, "");
            RespPageBean p2 = getPlan(1, size, "", status);
            List<Plan> data = (List<Plan>) p1.getData();
            List<Plan> data1 = (List<Plan>) p2.getData();
            data.retainAll(data1);
//            List<String> con=new ArrayList<>();
//            List<String> con1=new ArrayList<>();
//
//            for(Plan a:data){
//                con.add(a.getEmpNum());
//            }
//            for(Plan a:data1){
//                con1.add(a.getEmpNum());
//            }
//            con.retainAll(con1);
//            List<Plan> res=new ArrayList<>();
//            for(String s:con){
//                Plan plan = planMapper.selectByNum(s);
//                res.add(plan);
//            }
            RespPageBean bean=new RespPageBean();
            bean.setData(data);
            bean.setTotal((long) data.size());
            return bean;
        }else if(!keyword.equals("") && status.equals("")){
            List<Plan> plans = planMapper.selectByName(page, size, keyword);
            Long total = Long.valueOf(planMapper.selectCountName(keyword));
            RespPageBean bean=new RespPageBean();
            bean.setData(plans);
            bean.setTotal(total);
            return bean;
        }else{
            List<Plan> plans;
            Long total;
            if(!status.equals("all")){
                plans = planMapper.selectByInPro(page, size, status);
                total = Long.valueOf(planMapper.selectCountInPro(status));
            } else {
                plans = planMapper.selectAll(page, size);
                total = Long.valueOf(planMapper.selectCountAll());
            }
            RespPageBean bean=new RespPageBean();
            bean.setData(plans);
            bean.setTotal(total);
            return bean;
        }


    }
}
