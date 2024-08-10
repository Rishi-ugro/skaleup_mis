package skaleup.losMisReport.model;

import lombok.Data;
import org.springframework.stereotype.Component;

@Data
public class Response {

    public boolean success = false;
    public String reportName = null;

    public String error = null;

    @Data
    @Component
    public static class MisNames {
        String SkaleupMIS = "DOPS MIS";

    }
}
