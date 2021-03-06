package com.uz.laboratory.statistical.util;


import com.uz.laboratory.statistical.dict.Constants;
import com.uz.laboratory.statistical.dto.tableView.StatisticsRemarkTableDto;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

public class ReportUtil {
    private static StringBuilder stringBuilder = new StringBuilder();

    public static void createRemarkReport(List<StatisticsRemarkTableDto> remarkTableDtoList, String savePath, String fileName) throws IOException {
        XSSFWorkbook workbook = new XSSFWorkbook(ReportUtil.class.getResourceAsStream("/excel/remarkTableGost.xlsx"));
        XSSFSheet sheet = workbook.getSheet(Constants.REMARK_REPORT_SHEET_NAME);
        for (int rowNumber = 4, remarkIndex = 0;
             rowNumber < (remarkTableDtoList.size() + 5) && remarkIndex < remarkTableDtoList.size();
             rowNumber++, remarkIndex++) {
            XSSFRow row = sheet.createRow(rowNumber);
            List<String> remarkValuesList = DtoUtil.remarkDtoAsList(remarkTableDtoList.get(remarkIndex));
            for (int cellNumber = 0; cellNumber < 8; cellNumber++) {
                row.createCell(cellNumber).setCellValue(remarkValuesList.get(cellNumber));
            }
        }

        FileOutputStream fileOut = new FileOutputStream(stringBuilder.append(savePath)
                .append("/")
                .append(fileName)
                .append(".xlsx")
                .toString());
        stringBuilder.setLength(0);
        workbook.write(fileOut);
        fileOut.flush();
        fileOut.close();
    }
}

