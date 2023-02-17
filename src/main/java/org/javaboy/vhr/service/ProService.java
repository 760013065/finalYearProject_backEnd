package org.javaboy.vhr.service;

import org.javaboy.vhr.mapper.ProjectMapper;
import org.javaboy.vhr.model.Application;
import org.javaboy.vhr.model.Project;
import org.javaboy.vhr.model.RespPageBean;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class ProService {
    @Resource
    ProjectMapper projectMapper;

    public RespPageBean getPro(Integer page, Integer size, String status) {
        if(page!=null && size!=null){
            page=(page-1)*size;
        }
        List<Project> all;
        Long total;
        if(status.equals("") || status==null ||status.equals("all")){
            all = projectMapper.getAll(page, size);
            total = Long.valueOf(projectMapper.getCount());
            RespPageBean bean=new RespPageBean();
            bean.setData(all);
            bean.setTotal(total);
            return bean;
        }else{

                all = projectMapper.getStatus(page, size, status);
                total = Long.valueOf(projectMapper.getStatusCount(status));
                RespPageBean bean=new RespPageBean();
                bean.setData(all);
                bean.setTotal(total);
                return bean;

        }

    }
}
