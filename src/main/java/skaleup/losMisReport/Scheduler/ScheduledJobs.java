package skaleup.losMisReport.Scheduler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import skaleup.losMisReport.model.MisNames;
import skaleup.losMisReport.model.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import skaleup.losMisReport.service.MisService;

import java.util.ArrayList;

@Component
public class ScheduledJobs {

    @Autowired
    MisService misService;
    @Autowired
    MisNames misNames;
    private Logger LOGGER = LoggerFactory.getLogger(this.getClass());

    @Scheduled(cron = "0 0 */2 * * ?")
    public ArrayList<Response> runMIs() {
        ArrayList<Response> resp = new ArrayList<>();
        Response resMIS = new Response();
        try {
            resMIS.reportName = misNames.getSkaleupMIS();
            LOGGER.info("Skaleup report has started");
            misService.misfunc();
            LOGGER.info("Skaleup report has been completed");
            resMIS.success = true;
            resp.add(resMIS);
        } catch (Exception e) {
            LOGGER.error("Error in Skaleup mis ", e);
            resMIS.error = e.getMessage();
            resp.add(resMIS);
        }
        return resp;
    }

}
