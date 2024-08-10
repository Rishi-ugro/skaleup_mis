package skaleup.losMisReport.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import skaleup.losMisReport.Scheduler.ScheduledJobs;
import skaleup.losMisReport.model.Response;
import skaleup.losMisReport.service.MisService;

import java.util.ArrayList;

@RestController
@RequestMapping("/los")
public class MisController {

    @Autowired
    ScheduledJobs scheduledJobs;

    @GetMapping("/CaseLevelReport")
    public ArrayList<Response> caseLevelMis()
    {
        return scheduledJobs.runMIs();
    }

}
