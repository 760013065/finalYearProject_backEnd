package org.javaboy.vhr.mapper;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.javaboy.vhr.model.Project;
import org.javaboy.vhr.model.Task;

import java.util.List;

public interface ProjectMapper {
    @Select({"select * from project limit #{page},#{size}"})
    List<Project> getAll(Integer page,Integer size);

    @Select({"select count(*) from project"})
    Integer getCount();

    @Select({"select * from project where status=#{status} limit #{page},#{size}"})
    List<Project> getStatus(Integer page,Integer size,String status);

    @Select({"select count(*) from project where status=#{status}"})
    Integer getStatusCount(String status);

    @Select({"select * from tasks where proId=#{proId}"})
    List<Task> getTasks(Integer proId);

    @Insert({"insert into tasks (proId,empName,empNum,content,status,evaluation) values(#{proId},#{empName},#{empNum},#{content},#{status},#{evaluation})"})
    Integer insertTask(Task task);

    @Select({"select max(id) from project"})
    Integer getMax();

    @Insert({"insert into project (id,name,aim,description,manager,startTime,endTime,createTime,status) values(#{id},#{name},#{aim},#{description},#{manager},#{startTime},#{endTime},#{createTime},#{status})"})
    Integer insertPro(Project project);

    @Update({"update project set name=#{name},aim=#{aim},description=#{description},manager=#{manager},status=#{status},startTime=#{startTime},endTime=#{endTime},createTime=#{createTime} where id=#{id}"})
    int updatePro(Project project);

    @Delete({"delete from tasks where id=#{id}"})
    int delTask(Integer id);

    @Update({"update tasks set proId=#{proId},empName=#{empName},empNum=#{empNum},content=#{content},status=#{status},evaluation=#{evaluation} where id=#{id}"})
    int updateTask(Task task);

    @Select({"SELECT empNum,AVG(evaluation) as evaluation from tasks WHERE LENGTH(evaluation) >=1 group by empNum ORDER BY evaluation desc"})
    List<Task> avg();

    @Select({"select * from tasks where empNum=#{empNum}"})
    List<Task> getTaskByNum(String empNum);

    @Select({"select * from project where id=#{proId}"})
    Project getProById(int proId);

}
