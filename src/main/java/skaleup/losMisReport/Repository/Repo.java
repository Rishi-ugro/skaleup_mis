package skaleup.losMisReport.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import skaleup.losMisReport.model.MisApi;

import java.util.ArrayList;

@Repository
public interface Repo extends JpaRepository<MisApi,Long> {

    public static final String getMisData = "select\n" +
            "\tt.Department,\n" +
            "\tt.Application_Created_BY,\n" +
            "\tSourcing_Branch_Name,\n" +
            "\tt.designation,\n" +
            "\tt.applicationid,\n" +
            "\tt.Business_name,\n" +
            "\tt.Constitution,\n" +
            "\tt.statuscode as Application_status,\n" +
            "\tt.application_created_date,\n" +
            "\tt.BDM_Name,\n" +
            "\tt.File_Login_Date,\n" +
            "\tcase\n" +
            "\t\twhen t.Bureau_Completed is not null then 'Y'\n" +
            "\t\telse 'N'\n" +
            "\tend Bureau_Completed,CMR_Score,Cibil_Score,\n" +
            "\tcase\n" +
            "\t\twhen t.IMD_Completed is not null then 'Y'\n" +
            "\t\telse 'N'\n" +
            "\tend IMD_Completed,\n" +
            "\tcase\n" +
            "\t\twhen t.Bank_Completed is not null then 'Y'\n" +
            "\t\telse 'N'\n" +
            "\tend Bank_Completed,\n" +
            "\tcase\n" +
            "\t\twhen t.FE_Completed is not null then 'Y'\n" +
            "\t\telse 'N'\n" +
            "\tend FE_Completed,\n" +
            "\tcase\n" +
            "\t\twhen t.DDE_Completed is not null then 'Y'\n" +
            "\t\telse 'N'\n" +
            "\tend DDE_Completed,\n" +
            "\tcase\n" +
            "\t\twhen t.File_Login_Date is not null then 'Y'\n" +
            "\t\telse 'N'\n" +
            "\tend File_Login,\n" +
            "\tcase\n" +
            "\t\twhen t.Crdit_Sub_Stage <> 'CREDEC' then 'Y'\n" +
            "\t\telse 'N'\n" +
            "\tend Credit_Assessement_Intiated,\n" +
            "\tt.credit_approval_status,\n" +
            "\tcase\n" +
            "\t\twhen split_part(credit_approval_status::text,\n" +
            "\t\t',' ,\n" +
            "\t\t1)= '{APPR' then 'Y'\n" +
            "\t\twhen split_part(credit_approval_status::text,\n" +
            "\t\t',' ,\n" +
            "\t\t1)= '{APPR}' then 'Y'\n" +
            "\t\twhen split_part(credit_approval_status::text,\n" +
            "\t\t',' ,\n" +
            "\t\t2)= 'APPR}' then 'Y'\n" +
            "\t\telse 'N'\n" +
            "\tend case_approval,\n" +
            "\tt.credit_approval_role,\n" +
            "\tcase\n" +
            "\t\twhen t.Commerical_Intiated_Date is not null then 'Y'\n" +
            "\t\telse 'N'\n" +
            "\tend Commerical_Intiated,\n" +
            "\tt.Commerical_Intiated_Date,\n" +
            "\tcase\n" +
            "\t\twhen t.Commerical_Completed_Date is not null then 'Y'\n" +
            "\t\telse 'N'\n" +
            "\tend Commerical_Completed,\n" +
            "\tt.Commerical_Completed_Date,\n" +
            "\tcase\n" +
            "\t\twhen t.Disbursement_Initiated_Date is not null then 'Y'\n" +
            "\t\telse 'N'\n" +
            "\tend Disbursement_Intiated,\n" +
            "\tcase\n" +
            "\t\tt.statuscode\n" +
            "when 'SENT TO LMS' then 'Y'\n" +
            "\t\telse 'N'\n" +
            "\tend Pushed_To_LMS\n" +
            "from\n" +
            "\t(\n" +
            "\tselect\n" +
            "\t\tu.userkey,\n" +
            "\t\tur.functioncode as Department,\n" +
            "\t\temailid as Application_Created_BY,\n" +
            "\t\tdesignation,\n" +
            "\t\ta.applicationid,\n" +
            "\t\t(select registeredbusinessname as Business_name\n" +
            "\t\tfrom dmcredit.application_applicant d,\n" +
            "\t\tdmcredit.application_applicant_occupation aao\n" +
            "\t\twhere d.applicationkey=a.applicationkey \n" +
            "\t\tand applicanttype='APPLICANT' and applicantcategory='ENTITY' and d.appapplicantkey=aao.appapplicantkey ),\n" +
            "\t\t(select array_agg(aao.constitutiontype) as Constitution\n" +
            "\t\tfrom dmcredit.application_applicant d,\n" +
            "\t\tdmcredit.application_applicant_occupation aao\n" +
            "\t\twhere d.applicationkey=a.applicationkey \n" +
            "\t\tand applicanttype='APPLICANT' and applicantcategory='ENTITY' and d.appapplicantkey=aao.appapplicantkey ),\n" +
            "\t\t(select array_agg(distinct cs.score) as CMR_Score\n" +
            "\t\tfrom dmcredit.application_applicant d,\n" +
            "\t\tdmfiverification.cibil_details cd,\n" +
            "\t\tdmfiverification.cibil_score cs\n" +
            "\t\twhere d.applicationkey=a.applicationkey \n" +
            "\t\tand applicanttype='APPLICANT' and d.applicantid=cd.customerid \n" +
            "\t\tand cd.cibiltype='Commercial Cibil' and cd.state ='SUCCESS' and cd.cibildetailskey=cs.cibildetailskey \n" +
            "\t\tand cs.isactive='true'),\n" +
            "\t\t(select array_agg(cs.score) as Cibil_Score\n" +
            "\t\tfrom dmcredit.application_applicant d,\n" +
            "\t\tdmfiverification.cibil_details cd,\n" +
            "\t\tdmfiverification.cibil_score cs\n" +
            "\t\twhere d.applicationkey=a.applicationkey \n" +
            "\t\tand applicanttype='COAPPLICANT' and d.applicantid=cd.customerid \n" +
            "\t\tand cd.cibiltype='Consumer Cibil' and cd.state ='SUCCESS' and cd.cibildetailskey=cs.cibildetailskey \n" +
            "\t\tand cs.isactive='true' ),\n" +
            "\t\ta.statuscode,\n" +
            "\t\tto_char(a.createddt,\n" +
            "\t\t'dd-mm-yyyy') as application_created_date,\n" +
            "\t\tas2.bdm as BDM_Name,\n" +
            "\t\tb.branchname as Sourcing_Branch_Name,\n" +
            "\t\t(\n" +
            "\t\tselect\n" +
            "\t\t\tto_char(min(substageoutdate),\n" +
            "\t\t\t'dd-mm-yyyy') as Bureau_Completed\n" +
            "\t\tfrom\n" +
            "\t\t\tdmcredit.application_stage as3\n" +
            "\t\twhere\n" +
            "\t\t\tas3.applicationkey = a.applicationkey\n" +
            "\t\t\tand as3.stagecode = 'FileLogin'\n" +
            "\t\t\tand substagecode = 'BUREAUSUMMARY'\n" +
            "\t\t\tand as3.status = 'Completed'),\n" +
            "\t\t(\n" +
            "\t\tselect\n" +
            "\t\t\tto_char(min(substageoutdate),\n" +
            "\t\t\t'dd-mm-yyyy') as IMD_Completed\n" +
            "\t\tfrom\n" +
            "\t\t\tdmcredit.application_stage as3\n" +
            "\t\twhere\n" +
            "\t\t\tas3.applicationkey = a.applicationkey\n" +
            "\t\t\tand as3.stagecode = 'FileLogin'\n" +
            "\t\t\tand substagecode = 'IMD'\n" +
            "\t\t\tand as3.status = 'Completed'),\n" +
            "\t\t(\n" +
            "\t\tselect\n" +
            "\t\t\tto_char(min(substageoutdate),\n" +
            "\t\t\t'dd-mm-yyyy') as Bank_Completed\n" +
            "\t\tfrom\n" +
            "\t\t\tdmcredit.application_stage as3\n" +
            "\t\twhere\n" +
            "\t\t\tas3.applicationkey = a.applicationkey\n" +
            "\t\t\tand as3.stagecode = 'FileLogin'\n" +
            "\t\t\tand substagecode = 'IVBANKING'\n" +
            "\t\t\tand as3.status = 'Completed'),\n" +
            "\t\t(\n" +
            "\t\tselect\n" +
            "\t\t\tto_char(min(substageoutdate),\n" +
            "\t\t\t'dd-mm-yyyy') as GST_Completed\n" +
            "\t\tfrom\n" +
            "\t\t\tdmcredit.application_stage as3\n" +
            "\t\twhere\n" +
            "\t\t\tas3.applicationkey = a.applicationkey\n" +
            "\t\t\tand as3.stagecode = 'FileLogin'\n" +
            "\t\t\tand substagecode = 'IVGSTIN'\n" +
            "\t\t\tand as3.status = 'Completed'),\n" +
            "\t\t(\n" +
            "\t\tselect\n" +
            "\t\t\tto_char(min(substageoutdate),\n" +
            "\t\t\t'dd-mm-yyyy') as FE_Completed\n" +
            "\t\tfrom\n" +
            "\t\t\tdmcredit.application_stage as3\n" +
            "\t\twhere\n" +
            "\t\t\tas3.applicationkey = a.applicationkey\n" +
            "\t\t\tand as3.stagecode = 'FileLogin'\n" +
            "\t\t\tand substagecode = 'FE'\n" +
            "\t\t\tand as3.status = 'Completed'),\n" +
            "\t\t(\n" +
            "\t\tselect\n" +
            "\t\t\tto_char(min(substageoutdate),\n" +
            "\t\t\t'dd-mm-yyyy') as DDE_Completed\n" +
            "\t\tfrom\n" +
            "\t\t\tdmcredit.application_stage as3\n" +
            "\t\twhere\n" +
            "\t\t\tas3.applicationkey = a.applicationkey\n" +
            "\t\t\tand as3.stagecode = 'FileLogin'\n" +
            "\t\t\tand substagecode = 'ADDITIONALDETAILS'\n" +
            "\t\t\tand as3.status = 'Completed'),\n" +
            "\t\t(\n" +
            "\t\tselect\n" +
            "\t\t\tto_char(min(stageindate),\n" +
            "\t\t\t'dd-mm-yyyy') as File_Login_Date\n" +
            "\t\tfrom\n" +
            "\t\t\tdmcredit.application_stage as3\n" +
            "\t\twhere\n" +
            "\t\t\tas3.applicationkey = a.applicationkey\n" +
            "\t\t\tand as3.stagecode = 'CredDesc'),\n" +
            "\t\t(\n" +
            "\t\tselect\n" +
            "\t\t\tmax(substagecode) as Crdit_Sub_Stage\n" +
            "\t\tfrom\n" +
            "\t\t\tdmcredit.application_stage as3\n" +
            "\t\twhere\n" +
            "\t\t\tas3.applicationkey = a.applicationkey\n" +
            "\t\t\tand as3.stagecode = 'CredDesc'),\n" +
            "\t\t(\n" +
            "\t\tselect\n" +
            "\t\t\tarray_agg(status) as credit_approval_status\n" +
            "\t\tfrom\n" +
            "\t\t\tdmcredit.application_case_approving_details acad\n" +
            "\t\twhere\n" +
            "\t\t\tacad.applicationkey = a.applicationkey\n" +
            "\t\t\tand acad.isactive = 'True'),\n" +
            "\t\t(\n" +
            "\t\tselect\n" +
            "\t\t\tarray_agg(rolecode) as credit_approval_role\n" +
            "\t\tfrom\n" +
            "\t\t\tdmcredit.application_case_approving_details acad\n" +
            "\t\twhere\n" +
            "\t\t\tacad.applicationkey = a.applicationkey\n" +
            "\t\t\tand acad.isactive = 'True'),\n" +
            "\t\t(\n" +
            "\t\tselect\n" +
            "\t\t\tto_char(min(stageindate),\n" +
            "\t\t\t'dd-mm-yyyy') as Commerical_Intiated_Date\n" +
            "\t\tfrom\n" +
            "\t\t\tdmcredit.application_stage as3\n" +
            "\t\twhere\n" +
            "\t\t\tas3.applicationkey = a.applicationkey\n" +
            "\t\t\tand as3.stagecode = 'Fulfillment'),\n" +
            "\t\t(\n" +
            "\t\tselect\n" +
            "\t\t\tto_char(max(stageoutdate),\n" +
            "\t\t\t'dd-mm-yyyy') as Commerical_Completed_Date\n" +
            "\t\tfrom\n" +
            "\t\t\tdmcredit.application_stage as3\n" +
            "\t\twhere\n" +
            "\t\t\tas3.applicationkey = a.applicationkey\n" +
            "\t\t\tand as3.stagecode = 'Fulfillment'),\n" +
            "\t\t(\n" +
            "\t\tselect\n" +
            "\t\t\tto_char(min(stageindate),\n" +
            "\t\t\t'dd-mm-yyyy') as Disbursement_Initiated_Date\n" +
            "\t\tfrom\n" +
            "\t\t\tdmcredit.application_stage as3\n" +
            "\t\twhere\n" +
            "\t\t\tas3.applicationkey = a.applicationkey\n" +
            "\t\t\tand as3.stagecode = 'Disbursement')\n" +
            "\t\t--(select min(aad.assignmentkey) as first_credit_owner_id from dmcredit.application_assignment_details aad where aad.applicationkey=a.applicationkey \n" +
            "\t\t--and aad.functioncode='CREDIT')\n" +
            "\tfrom\n" +
            "\t\tdmiam.users u,\n" +
            "\t\tdmiam.user_roles ur,\n" +
            "\t\tdmcredit.application a,\n" +
            "\t\tdmcredit.application_sourcing as2,\n" +
            "\t\tdmmaster.branch b\n" +
            "\twhere\n" +
            "\t\tu.userkey = ur.userkey\n" +
            "\t\t--and ur.rolecode ='BDM'\n" +
            "\t\tand ur.functioncode = 'SALES'\n" +
            "\t\tand u.emailid = a.createdby\n" +
            "\t\tand a.applicationkey = as2.applicationkey\n" +
            "\t\tand as2.bdmbranch = b.branchcode\n" +
            "\t\t--and a.applicationid  in ('UGRO000779237755')\n" +
            "\t\tand u.emailid not in ('varun.dudani@ugrocapital.com')\n" +
            ")t\n" +
            "/*dmfiverification.cibil_details cd \n" +
            "where\n" +
            "t.applicantid=cd.customerid and \n" +
            "cibiltype='Consumer Cibil' and state ='SUCCESS'*/\n" +
            ";";

    @Query(value = getMisData, nativeQuery = true)
    public ArrayList getMisData();
}
