package org.javaboy.vhr;

import org.javaboy.vhr.mapper.*;
import org.javaboy.vhr.model.*;
import org.javaboy.vhr.service.EmployeeService;
import org.javaboy.vhr.service.PositionService;
import org.javaboy.vhr.service.TrainingService;
import org.javaboy.vhr.utils.MD5;
import org.javaboy.vhr.utils.Mail;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;

@SpringBootTest
class AllTests {
    @Autowired
    EmployeeService employeeService;
    @Autowired
    PositionService positionService;
    @Resource
    EmployeeMapper employeeMapper;
    @Resource
    EducationMapper educationMapper;
    @Resource
    ExperienceMapper experienceMapper;
    @Resource
    SkillMapper skillMapper;
    @Resource
    AppMapper appMapper;
    @Autowired
    TrainingService trainingService;
    @Resource
    ProjectMapper projectMapper;
    @Autowired
    Mail mail;


    @Test
    void password() {
        BCryptPasswordEncoder passwordEncoder=new BCryptPasswordEncoder();
        String encodedPassword=passwordEncoder.encode("456");
        System.out.println(encodedPassword);
        boolean match=passwordEncoder.matches("456",encodedPassword);
        System.out.println(match);
    }

    @Test
    void positionTest(){
        List<Position> allPositions = positionService.getAllPositions();
        for(Position position:allPositions){
            System.out.println(position);
        }
    }

    @Test
    void employeeListTest(){
//        List<Employee> list = employeeMapper.getEmployeeByPage(0,5);
//        for(Employee employee:list){
//            System.out.println(employee);
//        }

        Long l= employeeMapper.getTotal1("j");
        System.out.println(l);
    }

    @Test
    void empSearchTest(){
        List<Employee> list = employeeMapper.empSearch1(1,10,"j");
        List<Employee> employees = employeeMapper.empSearch2("10003");
        for(Employee e:list){
            System.out.println(e);
        }
    }

    @Test
    void judgeNumber(){
        System.out.println(employeeService.judgeNumber("10001"));
        System.out.println(employeeService.judgeNumber("123a"));
    }

    @Test
    void addEmp(){
        Employee employee=new Employee();
        employee.setName("Yunyang");
        employee.setAge(22);
        System.out.println(employeeMapper.insertEmp(employee));
    }

    @Test
    void maxJobNumber(){
        Integer integer = employeeMapper.maxJobNumber();
        System.out.println(integer);
    }

    @Test
    void  delEmp(){
        employeeMapper.delEmp(10028);
    }

    @Test
    void updateEmp(){
        Employee employee=new Employee();
        employee.setAge(11);
        employee.setAddress("Beijing");
        employee.setEmail("wy7600@126.com");
        employee.setJobNumber("10025");
        System.out.println(employeeMapper.updateEmp(employee));
    }

    @Test
    void selectEdu(){
        List<Education> educations = educationMapper.selectEducation("10001");
        for(Education e:educations){
            System.out.println(e);
        }
    }

    @Test
    void selectExp(){
        List<Experience> experiences = experienceMapper.selectExperience("10001");
        for(Experience e:experiences){
            System.out.println(e);
        }
    }

    @Test
    void adSearch(){
        String con="java/front end";
        String[] split = con.split("/");
        List<Employee> employees=new ArrayList<>();
//        for(String s:split){
//            System.out.println(s);
//        }
//        HashSet<String> jobNumbers = experienceMapper.selectExpByPos("java");
//        for(String s:jobNumbers){
//            System.out.println(s);
//        }
        HashSet<String> all=new HashSet<>();
        for(int i=0;i<split.length;i++){
            HashSet<String> hashSet = experienceMapper.selectExpByPos(split[i]);
            if(i==0){
                all.addAll(hashSet);
                continue;
            }
            all.retainAll(hashSet);
        }

        for(String s:all){
            Employee employee = employeeMapper.selectByJobNumber(s);
            employees.add(employee);
        }

        for(Employee e:employees){
            System.out.println(e);
        }



    }
    @Test
    void hashSetTest(){
//        HashSet<String> hashSet1=new HashSet<>();
//        HashSet<String> hashSet2=new HashSet<>();
//
//        hashSet1.add("1");
//        hashSet1.add("2");
//        hashSet1.add("3");
//
//        hashSet2.add("2");
//        hashSet2.add("3");
//
//        hashSet1.retainAll(hashSet2);
//        for(String s:hashSet1){
//            System.out.println(s);
//        }
        List<String> list=new ArrayList<>();
        List<String> list1=new ArrayList<>();
        list.add("2");
        list.add("3");
        list1.add("3");
        list.retainAll(list1);
        for(String s:list){
            System.out.println(s);
        }


    }

    @Test
    void skillTest(){
        HashSet<String> hashSet = skillMapper.selectBySki("deep");
        for(String s:hashSet){
            System.out.println(s);
        }
    }

    @Test
    void adSearchTest(){
        Content content=new Content();
        content.setExperience("java");
        content.setInterest("");
        content.setSkill("");
        List<Employee> employees = employeeService.advancedSearch(content);
        for(Employee e:employees){
            System.out.println(e);
        }
//
//        String exp="java/c";
//        String[] split = exp.split("/");
//        HashSet<String> hashSet = employeeService.processExp(split);
//        for(String s:hashSet){
//            System.out.println(s);
//        }
//
//
//        String ski="";
//        String[] split1 = ski.split("/");
//        HashSet<String> hashSet = employeeService.processSki(split1);
//        for(String s:hashSet){
//            System.out.println(s);
//        }
//
//
//        String inte="";
//        String[] split2 = inte.split("/");
//        HashSet<String> hashSet = employeeService.processInt(split2);
//        for(String s:hashSet){
//            System.out.println(s);
//        }
//
//        employees.retainAll(employees1);
//        employees.retainAll(employees2);
//        for(Employee e:employees){
//            System.out.println(e);
//        }
//        String inte="writing";
//        String[] split = inte.split("/");
//        HashSet<String> hashSet = employeeService.processInt(split);
//        for(String s:hashSet){
//            System.out.println(s);
//        }


    }

    @Test
    void easyTest(){
        String[] s="".split("/");

        System.out.println(s[0].equals(""));
    }

    @Test
    void appTest(){
//        List<Application> applications = appMapper.selectAUnprocessed(0, 5);
//        for(Application application:applications){
//            System.out.println(application);
//        }
//        Integer integer = appMapper.selectCountPro();
//        Integer integer1 = appMapper.selectCountUnpro();
//        System.out.println(integer);
//        System.out.println(integer1);

//        List<Application> mike = appMapper.selectByName("mike");
//        for(Application a:mike){
//            System.out.println(a);
//        }
    }
    @Resource
    PlanMapper planMapper;
    @Test
    void planTest(){
        List<Plan> plans = planMapper.selectAll(0,10);
        Integer integer = planMapper.selectCountAll();
        for(Plan p:plans){
            System.out.println(p);
        }
        System.out.println(integer);
    }

    @Test
    void addPlan(){
//        Plan plan=new Plan();
//        plan.setEvaluation("");
//        plan.setCreateTime(new Date());
//        planMapper.addPlan(plan);
//        RespPageBean plan = trainingService.getPlan(1, 6, "Liu", "in progress");
//        List<Plan> data = (List<Plan>) plan.getData();
//        for(Plan p:data){
//            System.out.println(p);
//        }
        Plan plan=new Plan();
        plan.setCreateTime(new Date());
        plan.setEmpNum("10001");
        System.out.println(planMapper.updatePlan(plan));


    }

    @Test
    void str(){
        String s="10051.0";
        System.out.println(s.substring(0,5));
    }

    @Test
    void proTest(){
//        for (Project project : projectMapper.getStatus(0,6,"finished")) {
//            System.out.println(project);
//        }

//        for (Task task : projectMapper.getTasks(10)) {
//            System.out.println(task);
//        }

//        Task task=new Task();
//        task.setEmpName("mike");
//        task.setContent("part1");
//        projectMapper.insertTask(task);
//        Integer max = projectMapper.getMax();
//        System.out.println(max);
//        Project p=new Project();
//        p.setId(11);
//        p.setName("pro");
//        projectMapper.insertPro(p);
//        Project p=new Project();
//        p.setId(14);
//        p.setManager("lws");
//        projectMapper.updatePro(p);
//        projectMapper.delTask(28);
//        Task t=new Task();
//        t.setId(28);
//        projectMapper.updateTask(t);
        List<Task> avg = projectMapper.avg();
        for(Task t:avg){
            t.setEmpName(employeeMapper.selectByJobNumber(t.getEmpNum()).getName());
            System.out.println(t);
        }

    }
    @Test
    void mailTest() throws Exception {
        Mail.sendMail("760013065@qq.com","Dear xxx, welcome to our team! Thank you for choosing to join us and let us work hard together to create more value.Please keep in mind that your job number is <h1 style=\"color: red\">10001</h1>","welcome!");
    }

@Resource
EmpLoginMapper empLoginMapper;
    @Test
    void md5(){
//        System.out.println(MD5.generateUUID().substring(0,5));
//        String s = MD5.md5("123" + "903ed");
//        System.out.println(s);
        EmpLogin mike = empLoginMapper.getEmpLogin("mike");
        System.out.println(mike);
        String s=MD5.md5("123"+mike.getSalt());
        System.out.println(s.equals(mike.getPassword()));
    }

    @Test
    void app(){
//        Date date=new Date(System.currentTimeMillis());
//        Application application=new Application();
//        application.setAppTime(date);
//        appMapper.insertApp(application);
        List<Plan> mike = planMapper.empPlan("mike");
        for(Plan p:mike){
            System.out.println(p);
        }
    }

    @Test
    void empTask(){
//        for (Task task : projectMapper.getTaskByNum("10003")) {
//            System.out.println(task);
//        }
//        String empByName = employeeMapper.getEmpByName("Jordan");
//        System.out.println(empByName);
        Project proById = projectMapper.getProById(10);
        System.out.println(proById);

    }







}
