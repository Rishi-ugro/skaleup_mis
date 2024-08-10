package skaleup.losMisReport.ExcelReport;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import skaleup.losMisReport.ExcelReport.MisExcelReport;

import jakarta.mail.MessagingException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;

@Component
public class MisReport {
    @Autowired
    private MisExcelReport es;
    @Value("${ugro.mis.folder}")
    String folderPath;

    @Value("${spring.mail.username}")
    String from;

    private DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
    private String date = dateFormat.format(new Date());
    private Logger LOGGER = LoggerFactory.getLogger(this.getClass());

    public void createExel(String name, String[] sheetname, HashMap<Integer, String[]> caseLoginColumnHeading,
                           HashMap<Integer, ArrayList<Object>> alldata
            , String[] to, String[] cc, int[] sizeOfEachSheet) throws IOException, MessagingException, InterruptedException {
        Workbook workbook = new XSSFWorkbook();
        String fileName = name + date + ".xlsx";
        Font headerFont = workbook.createFont();
        headerFont.setBold(true);
        headerFont.setFontHeightInPoints((short) 12);
        headerFont.setColor(IndexedColors.BLACK.index);
        CellStyle headerStyle = workbook.createCellStyle();
        headerStyle.setFont(headerFont);
        headerStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
        headerStyle.setFillForegroundColor(IndexedColors.GREY_25_PERCENT.index);
        int i = 0;
        for (String eachsheet : sheetname) {
            Sheet sh = workbook.createSheet(eachsheet);
            Row headerRow = sh.createRow(0);
            columncreation(caseLoginColumnHeading.get(i), headerRow, headerStyle);
            ArrayList<Object> data = alldata.get(i);
            Iterator<Object> itr1 = data.iterator();
            int sheetsize = sizeOfEachSheet[i];
            dataIteration(sh, itr1, caseLoginColumnHeading.get(i), sheetsize);
            i = i + 1;

        }
        saveAndMail(workbook, fileName, to, cc);
        alldata.clear();
    }

    static String handleNull(String val) {
        if (val == "Null" || val.trim().equalsIgnoreCase("null") || val.equalsIgnoreCase("NA")) {
            val = "";
        } else if (val.equals("0")) {
            val = "0";

        }
        return val;
    }

    private void columncreation(String[] caseLoginColumnHeading, Row headerRow, CellStyle headerStyle) {
        for (int j = 0; j < caseLoginColumnHeading.length; j++) {
            Cell cell = headerRow.createCell(j);
            cell.setCellValue(caseLoginColumnHeading[j]);
            cell.setCellStyle(headerStyle);
        }

    }

    private void dataIteration(Sheet sh, Iterator<Object> itr, String[] columns, int sheetsize) {
        int index1 = 0;
        while (itr.hasNext()) {
            Object[] tmp = (Object[]) itr.next();
            int size = 0;
            index1++;
            Row row = sh.createRow(index1);
            String crnNO = handleNull(String.valueOf(tmp[0]));
            row.createCell(0).setCellValue(crnNO);
            ++size;
            String crnNO1 = handleNull(String.valueOf(tmp[1]));
            row.createCell(1).setCellValue(crnNO1);
            ++size;
            String crnNO2 = handleNull(String.valueOf(tmp[2]));
            row.createCell(2).setCellValue(crnNO2);
            ++size;
            String crnNO3 = handleNull(String.valueOf(tmp[3]));
            row.createCell(3).setCellValue(crnNO3);
            ++size;
            if (sheetsize == (size)) {
                continue;
            }
            String crnNO4 = handleNull(String.valueOf(tmp[4]));
            row.createCell(4).setCellValue(crnNO4);
            ++size;
            if (sheetsize == (size)) {
                continue;
            }
            String crnNO5 = handleNull(String.valueOf(tmp[5]));
            row.createCell(5).setCellValue(crnNO5);
            ++size;
            if (sheetsize == size) {
                continue;
            }
            String crnNO6 = handleNull(String.valueOf(tmp[6]));
            row.createCell(6).setCellValue(crnNO6);
            ++size;
            if (sheetsize == size) {
                continue;
            }
            String crnNO7 = handleNull(String.valueOf(tmp[7]));
            row.createCell(7).setCellValue(crnNO7);
            ++size;
            String crnNO8 = handleNull(String.valueOf(tmp[8]));
            row.createCell(8).setCellValue(crnNO8);
            ++size;
            String crnNO9 = handleNull(String.valueOf(tmp[9]));
            row.createCell(9).setCellValue(crnNO9);
            ++size;
            String crnNo10 = handleNull(String.valueOf(tmp[10]));
            row.createCell(10).setCellValue(crnNo10);
            ++size;
            String crnNO11 = handleNull(String.valueOf(tmp[11]));
            row.createCell(11).setCellValue(crnNO11);
            ++size;
            String crnNO12 = handleNull(String.valueOf(tmp[12]));
            row.createCell(12).setCellValue(crnNO12);
            ++size;
            String crnNO13 = handleNull(String.valueOf(tmp[13]));
            row.createCell(13).setCellValue(crnNO13);
            ++size;
            if (sheetsize == size) {
                continue;
            }
            String crnNO14 = handleNull(String.valueOf(tmp[14]));
            row.createCell(14).setCellValue(crnNO14);
            ++size;
            String crnNO15 = handleNull(String.valueOf(tmp[15]));
            row.createCell(15).setCellValue(crnNO15);
            ++size;
            if (sheetsize == size) {
                continue;
            }
            String crnNO16 = handleNull(String.valueOf(tmp[16]));
            row.createCell(16).setCellValue(crnNO16);
            ++size;
            String crnNO17 = handleNull(String.valueOf(tmp[17]));
            row.createCell(17).setCellValue(crnNO17);
            ++size;
            if (sheetsize == size) {
                continue;

            }
            String crnNO18 = handleNull(String.valueOf(tmp[18]));
            row.createCell(18).setCellValue(crnNO18);
            ++size;
            if (sheetsize == size) {
                continue;
            }
            String crnNO19 = handleNull(String.valueOf(tmp[19]));
            row.createCell(19).setCellValue(crnNO19);
            ++size;
            String crnNO20 = handleNull(String.valueOf(tmp[20]));
            row.createCell(20).setCellValue(crnNO20);
            ++size;
            String crnNO21 = handleNull(String.valueOf(tmp[21]));
            row.createCell(21).setCellValue(crnNO21);
            ++size;
            if (sheetsize == (size)) {
                continue;
            }
            String crnNO22 = handleNull(String.valueOf(tmp[22]));
            row.createCell(22).setCellValue(crnNO22);
            ++size;
            if (sheetsize == size) {
                continue;
            }
            String crnNO23 = handleNull(String.valueOf(tmp[23]));
            row.createCell(23).setCellValue(crnNO23);
            ++size;
            if (sheetsize == size) {
                continue;
            }
            String crnNO24 = handleNull(String.valueOf(tmp[24]));
            row.createCell(24).setCellValue(crnNO24);
            ++size;

            String crnNO25 = handleNull(String.valueOf(tmp[25]));
            row.createCell(25).setCellValue(crnNO25);
            ++size;
            String crnNO26 = handleNull(String.valueOf(tmp[26]));
            row.createCell(26).setCellValue(crnNO26);
            ++size;
            String crnNO27 = handleNull(String.valueOf(tmp[27]));
            row.createCell(27).setCellValue(crnNO27);
            ++size;
            if (sheetsize == size) {
                continue;
            }
            String crnNO28 = handleNull(String.valueOf(tmp[28]));
            row.createCell(28).setCellValue(crnNO28);
            ++size;
            String crnNO29 = handleNull(String.valueOf(tmp[29]));
            row.createCell(29).setCellValue(crnNO29);
            ++size;
            if (sheetsize == size) {
                continue;
            }
            String crnNO30 = handleNull(String.valueOf(tmp[30]));
            row.createCell(30).setCellValue(crnNO30);
            ++size;
            if (sheetsize == size) {
                continue;
            }
            String crnNO31 = handleNull(String.valueOf(tmp[31]));
            row.createCell(31).setCellValue(crnNO31);
            ++size;
            if (sheetsize == size) {
                continue;
            }

            String crnNO32 = handleNull(String.valueOf(tmp[32]));
            row.createCell(32).setCellValue(crnNO32);
            ++size;
            if (sheetsize == size) {
                continue;
            }
            String crnNO33 = handleNull(String.valueOf(tmp[33]));
            row.createCell(33).setCellValue(crnNO33);
            ++size;
            String crnNO34 = handleNull(String.valueOf(tmp[34]));
            row.createCell(34).setCellValue(crnNO34);
            ++size;
            String crnNO35 = handleNull(String.valueOf(tmp[35]));
            row.createCell(35).setCellValue(crnNO35);
            ++size;
            String crnNO36 = handleNull(String.valueOf(tmp[36]));
            row.createCell(36).setCellValue(crnNO36);
            ++size;
            String crnNO37 = handleNull(String.valueOf(tmp[37]));
            row.createCell(37).setCellValue(crnNO37);
            ++size;
            String crnNO38 = handleNull(String.valueOf(tmp[38]));
            row.createCell(38).setCellValue(crnNO38);
            ++size;
            if (sheetsize == size) {
                continue;
            }
            String crnNO39 = handleNull(String.valueOf(tmp[39]));
            row.createCell(39).setCellValue(crnNO39);
            ++size;
            if (sheetsize == size) {
                continue;
            }
            String crnNO40 = handleNull(String.valueOf(tmp[40]));
            row.createCell(40).setCellValue(crnNO40);
            ++size;
            String crnNO41 = handleNull(String.valueOf(tmp[41]));
            row.createCell(41).setCellValue(crnNO41);
            ++size;
            String crnNO42 = handleNull(String.valueOf(tmp[42]));
            row.createCell(42).setCellValue(crnNO42);
            ++size;
            String crnNO43 = handleNull(String.valueOf(tmp[43]));
            row.createCell(43).setCellValue(crnNO43);
            ++size;
            String crnNO44 = handleNull(String.valueOf(tmp[44]));
            row.createCell(44).setCellValue(crnNO44);
            ++size;
            String crnNO45 = handleNull(String.valueOf(tmp[45]));
            row.createCell(45).setCellValue(crnNO45);
            ++size;
            String crnNO46 = handleNull(String.valueOf(tmp[46]));
            row.createCell(46).setCellValue(crnNO46);
            ++size;
            String crnNO47 = handleNull(String.valueOf(tmp[47]));
            row.createCell(47).setCellValue(crnNO47);
            ++size;
            String crnNO48 = handleNull(String.valueOf(tmp[48]));
            row.createCell(48).setCellValue(crnNO48);
            ++size;
            String crnNO49 = handleNull(String.valueOf(tmp[49]));
            row.createCell(49).setCellValue(crnNO49);
            ++size;
            String crnNO50 = handleNull(String.valueOf(tmp[50]));
            row.createCell(50).setCellValue(crnNO50);
            ++size;
            String crnNO51 = handleNull(String.valueOf(tmp[51]));
            row.createCell(51).setCellValue(crnNO51);
            ++size;
            if (sheetsize == size) {
                continue;
            }
            String crnNO52 = handleNull(String.valueOf(tmp[52]));
            row.createCell(52).setCellValue(crnNO52);
            String crnNO53 = handleNull(String.valueOf(tmp[53]));
            row.createCell(53).setCellValue(crnNO53);
            String crnNO54 = handleNull(String.valueOf(tmp[54]));
            row.createCell(54).setCellValue(crnNO54);
            String crnNO55 = handleNull(String.valueOf(tmp[55]));
            row.createCell(55).setCellValue(crnNO55);
            String crnNO56 = handleNull(String.valueOf(tmp[56]));
            row.createCell(56).setCellValue(crnNO56);
            String crnNO57 = handleNull(String.valueOf(tmp[57]));
            row.createCell(57).setCellValue(crnNO57);
            String crnNO58 = handleNull(String.valueOf(tmp[58]));
            row.createCell(58).setCellValue(crnNO58);
            String crnNO59 = handleNull(String.valueOf(tmp[59]));
            row.createCell(59).setCellValue(crnNO59);
            String crnNO60 = handleNull(String.valueOf(tmp[60]));
            row.createCell(60).setCellValue(crnNO60);
            String crnNO61 = handleNull(String.valueOf(tmp[61]));
            row.createCell(61).setCellValue(crnNO61);
            String crnNO62 = handleNull(String.valueOf(tmp[62]));
            row.createCell(62).setCellValue(crnNO62);
            String crnNO63 = handleNull(String.valueOf(tmp[63]));
            row.createCell(63).setCellValue(crnNO63);
            String crnNO64 = handleNull(String.valueOf(tmp[64]));
            row.createCell(64).setCellValue(crnNO64);
            String crnNO65 = handleNull(String.valueOf(tmp[65]));
            row.createCell(65).setCellValue(crnNO65);
            String crnNO66 = handleNull(String.valueOf(tmp[66]));
            row.createCell(66).setCellValue(crnNO66);
            String crnNO67 = handleNull(String.valueOf(tmp[67]));
            row.createCell(67).setCellValue(crnNO67);
            String crnNO68 = handleNull(String.valueOf(tmp[68]));
            row.createCell(68).setCellValue(crnNO68);
            String crnNO69 = handleNull(String.valueOf(tmp[69]));
            row.createCell(69).setCellValue(crnNO69);
            String crnNO70 = handleNull(String.valueOf(tmp[70]));
            row.createCell(70).setCellValue(crnNO70);
            String crnNO71 = handleNull(String.valueOf(tmp[71]));
            row.createCell(71).setCellValue(crnNO71);
            String crnNO72 = handleNull(String.valueOf(tmp[72]));
            row.createCell(72).setCellValue(crnNO72);
            String crnNO73 = handleNull(String.valueOf(tmp[73]));
            row.createCell(73).setCellValue(crnNO73);
            String crnNO74 = handleNull(String.valueOf(tmp[74]));
            row.createCell(74).setCellValue(crnNO74);
            String crnNO75 = handleNull(String.valueOf(tmp[75]));
            row.createCell(75).setCellValue(crnNO75);
            String crnNO76 = handleNull(String.valueOf(tmp[76]));
            row.createCell(76).setCellValue(crnNO76);
            String crnNO77 = handleNull(String.valueOf(tmp[77]));
            row.createCell(77).setCellValue(crnNO77);
            String crnNO78 = handleNull(String.valueOf(tmp[78]));
            row.createCell(78).setCellValue(crnNO78);
        }
        for (int z = 0; z < columns.length; z++) {
            sh.autoSizeColumn(z);
        }
    }

    private void saveAndMail(Workbook workbook, String fileName, String[] to, String[] cc) throws IOException, MessagingException, InterruptedException {
        Files.createDirectories(Paths.get(folderPath));
        System.out.println(Paths.get(folderPath));
        FileOutputStream fileOut = new FileOutputStream(
                folderPath + fileName);
        workbook.write(fileOut);
        fileOut.close();
        workbook.close();
        LOGGER.info("Generating the Email for " + fileName.substring(0, fileName.length() - 15));
        es.sendMisEmail(fileName, from, to, cc);

    }
}
