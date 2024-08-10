package skaleup.losMisReport.service;

import skaleup.losMisReport.ExcelReport.MisExcelReport;
import skaleup.losMisReport.ExcelReport.MisReport;
import skaleup.losMisReport.Repository.Repo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.mail.MessagingException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

@Component
public class MisService {

    @Autowired
    MisReport mr;
    @Autowired
    private Repo repo;
    public void misfunc() throws MessagingException, IOException, InterruptedException, jakarta.mail.MessagingException {
        ArrayList data = repo.getMisData();
        HashMap<Integer, ArrayList<Object>> allData = new HashMap<>();
        allData.put(0, data);
        String name = "Skaleup login MIS-";
        String[] columnHeading = {
                "department",
                "application_created_by",
                "sourcing_branch_name",
                "designation",
                "applicationid",
                "applicant_name",
                "application_status",
                "application_created_date",
                "bdm_name",
                "file_login_date",
                "bureau_completed",
                "cmr_score",
                "cibil_score",
                "imd_completed",
                "bank_completed",
                "fe_completed",
                "dde_completed",
                "file_login",
                "credit_assessement_intiated",
                "credit_approval_status",
                "case_approval",
                "credit_approval_role",
                "commerical_intiated",
                "commerical_intiated_date",
                "commerical_completed",
                "commerical_completed_date",
                "disbursement_intiated",
                "pushed_to_lms"
        };
        HashMap<Integer, String[]> sb = new HashMap<>();
        sb.put(0, columnHeading);
        String[] to = {
                "peeyush.mittal@ugrocapital.com","apurva.jhaa@ugrocapital.com", "varun.dudani@ugrocapital.com", "shailesh.verma@ugrocapital.com",
                "ravish.kumar1@ugrocapital.com", "shailesh.verma@ugrocapital.com",
                "chandan.vishwasrao-cs@ugrocapital.com"
        };
        String[] cc = {"rishi.khandelwal@ugrocapital.com"};
        String[] sheetname = {"Skaleup Case Level Report"};
        boolean k = false;
        int[] sizeOfEachSheet = {28};
        mr.createExel(name, sheetname, sb, allData, to, cc, sizeOfEachSheet);
        allData.clear();
        data.clear();

    }

}
