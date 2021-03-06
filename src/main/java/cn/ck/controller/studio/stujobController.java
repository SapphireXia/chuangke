package cn.ck.controller.studio;

import cn.ck.controller.AbstractController;
import cn.ck.entity.*;
import cn.ck.entity.bean.JobUsers;
import cn.ck.service.JobsService;
import cn.ck.service.JobuserService;
import cn.ck.service.StudioService;
import cn.ck.service.UsersService;
import cn.ck.utils.ResponseBo;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import org.quartz.Job;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;

/**
 * @author 马圳彬
 * @version 1.0
 * @description 创客
 * @date 2018/9/29 11:36
 **/
@Controller
@RequestMapping("/stujob")
public class stujobController extends AbstractController {
    @Autowired
    private StudioService studioService;
    @Autowired
    private JobsService jobsService;
    @Autowired
    private JobuserService jobuserService;
    @Autowired
    private UsersService usersService;




    @GetMapping("/jobList")
    @ResponseBody
    public ResponseBo mList(){
        /*Studio stu = studioService.selectByzzid(getUser().getAllId());*/
        /*工作室信息*/
        String zzid = getUser().getAllId();
        Studio stu = studioService.selectOne(new EntityWrapper<Studio>().eq("stu_creatid",zzid));

        String stuid = stu.getStuId();
        System.out.println(stuid);
        List<Jobs> joblist = jobsService.selectList(new EntityWrapper<Jobs>().eq("job_studio",stuid));
        ResponseBo map = new ResponseBo();

        map.put("jobs",joblist);
        return map;
    }

    @GetMapping("/jobreviewList")
    @ResponseBody
    public ResponseBo jrList(){
        List<Jobuser> juList = jobuserService.selectList(new EntityWrapper<Jobuser>());
        List<JobUsers> jusList = new ArrayList<>();

        for(Jobuser ju:juList){

            String userId = ju.getJuUsers();
            Users user = usersService.selectOne(new EntityWrapper<Users>().eq("user_id",userId));
            int jobId = ju.getJuJobs();
            Jobs jobs = jobsService.selectOne(new EntityWrapper<Jobs>().eq("job_id",jobId));
            JobUsers jus = new JobUsers();
            jus.setUsers(user);
            jus.setJobs(jobs);
            jus.setJobuser(ju);
            jusList.add(jus);

        }

        return ResponseBo.ok().put("jobusers",jusList);
    }

}
