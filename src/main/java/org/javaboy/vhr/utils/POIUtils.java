package org.javaboy.vhr.utils;

import org.apache.poi.hpsf.DocumentSummaryInformation;
import org.apache.poi.hpsf.SummaryInformation;
import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.javaboy.vhr.model.Employee;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


public class POIUtils {

    public static ResponseEntity<byte[]> employee2Excel(List<Employee> list) {
        //1. 创建一个 Excel 文档
        HSSFWorkbook workbook = new HSSFWorkbook();
        //2. 创建文档摘要
        workbook.createInformationProperties();
        //3. 获取并配置文档信息
        DocumentSummaryInformation docInfo = workbook.getDocumentSummaryInformation();
        //文档类别
        docInfo.setCategory("employee information");
        //文档管理员
        docInfo.setManager("Liu Wenshuai");
        //设置公司信息
        docInfo.setCompany("www.javaboy.org");
        //4. 获取文档摘要信息
        SummaryInformation summInfo = workbook.getSummaryInformation();
        //文档标题
        summInfo.setTitle("employee information");
        //文档作者
        summInfo.setAuthor("Liu Wenshuai");
        // 文档备注
        summInfo.setComments("Liu Wenshuai provide");

        HSSFCellStyle headerStyle = workbook.createCellStyle();
        headerStyle.setFillForegroundColor(IndexedColors.YELLOW.index);
        headerStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);

        HSSFCellStyle dateCellStyle = workbook.createCellStyle();
        dateCellStyle.setDataFormat(HSSFDataFormat.getBuiltinFormat("m/d/yy"));

        HSSFSheet sheet = workbook.createSheet("employee information");

        sheet.setColumnWidth(0, 5 * 256);
        sheet.setColumnWidth(1, 12 * 256);
        sheet.setColumnWidth(2, 10 * 256);
        sheet.setColumnWidth(3, 10 * 256);
        sheet.setColumnWidth(4, 12 * 256);
        sheet.setColumnWidth(5, 20 * 256);
        sheet.setColumnWidth(6, 25 * 256);
        sheet.setColumnWidth(7, 10 * 256);
        sheet.setColumnWidth(8, 16 * 256);
        sheet.setColumnWidth(9, 20 * 256);
        sheet.setColumnWidth(10, 15 * 256);
        sheet.setColumnWidth(11, 20 * 256);
        sheet.setColumnWidth(12, 16 * 256);

        HSSFRow r0 = sheet.createRow(0);

        HSSFCell c0 = r0.createCell(0);
        c0.setCellValue("id");
        c0.setCellStyle(headerStyle);

        HSSFCell c1 = r0.createCell(1);
        c1.setCellStyle(headerStyle);
        c1.setCellValue("name");

        HSSFCell c2 = r0.createCell(2);
        c2.setCellStyle(headerStyle);
        c2.setCellValue("gender");

        HSSFCell c3 = r0.createCell(3);
        c3.setCellStyle(headerStyle);
        c3.setCellValue("job number");

        HSSFCell c4 = r0.createCell(4);
        c4.setCellStyle(headerStyle);
        c4.setCellValue("age");

        HSSFCell c5 = r0.createCell(5);
        c5.setCellStyle(headerStyle);
        c5.setCellValue("birthday");

        HSSFCell c6 = r0.createCell(6);
        c6.setCellStyle(headerStyle);
        c6.setCellValue("idCard number");

        HSSFCell c7 = r0.createCell(7);
        c7.setCellStyle(headerStyle);
        c7.setCellValue("address");

        HSSFCell c8 = r0.createCell(8);
        c8.setCellStyle(headerStyle);
        c8.setCellValue("nation");

        HSSFCell c9 = r0.createCell(9);
        c9.setCellStyle(headerStyle);
        c9.setCellValue("email");

        HSSFCell c10 = r0.createCell(10);
        c10.setCellStyle(headerStyle);
        c10.setCellValue("phone number");

        HSSFCell c11 = r0.createCell(11);
        c11.setCellStyle(headerStyle);
        c11.setCellValue("department");

        HSSFCell c12 = r0.createCell(12);
        c12.setCellStyle(headerStyle);
        c12.setCellValue("position");

        for (int i = 0; i <list.size(); i++) {
            Employee employee = list.get(i);
            HSSFRow row = sheet.createRow(i + 1);
            row.createCell(0).setCellValue(employee.getId());
            row.createCell(1).setCellValue(employee.getName());
            row.createCell(2).setCellValue(employee.getGender());
            row.createCell(3).setCellValue(employee.getJobNumber());
            row.createCell(4).setCellValue(employee.getAge());
            HSSFCell cell = row.createCell(5);
            cell.setCellStyle(dateCellStyle);
            cell.setCellValue(employee.getBirthday());

            row.createCell(6).setCellValue(employee.getIdCard());
            row.createCell(7).setCellValue(employee.getAddress());
            row.createCell(8).setCellValue(employee.getNation());
            row.createCell(9).setCellValue(employee.getEmail());
            row.createCell(10).setCellValue(employee.getPhone());
            row.createCell(11).setCellValue(employee.getDepartment());
            row.createCell(12).setCellValue(employee.getPosition());

        }
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        HttpHeaders headers = new HttpHeaders();
        try {
            headers.setContentDispositionFormData("attachment", new String("employee info.xls".getBytes("UTF-8"), "ISO-8859-1"));
            headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
            workbook.write(byteArrayOutputStream);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return new ResponseEntity<byte[]>(byteArrayOutputStream.toByteArray(),headers,HttpStatus.CREATED);




    }


    public static List<Employee> excel2Employee(MultipartFile file) {
        List<Employee> list=new ArrayList<>();
        Employee employee;
        try {
            HSSFWorkbook workbook = new HSSFWorkbook(file.getInputStream());
            int numberOfSheets = workbook.getNumberOfSheets();
            for (int i = 0; i < numberOfSheets; i++) {
                HSSFSheet sheet = workbook.getSheetAt(i);
                int physicalNumberOfRows = sheet.getPhysicalNumberOfRows();
                for (int j = 0; j < physicalNumberOfRows; j++) {
                    if (j == 0) {
                        continue;//跳过标题行
                    }
                    HSSFRow row = sheet.getRow(j);
                    if (row == null) {
                        continue;//防止数据中间有空行
                    }
                    int physicalNumberOfCells = row.getPhysicalNumberOfCells();
                    employee=new Employee();
                    for (int k = 1; k < physicalNumberOfCells; k++) {
                        HSSFCell cell = row.getCell(k);
                        switch (cell.getCellType()) {
                            case STRING:
                                String cellValue = cell.getStringCellValue();
                                switch (k) {
                                    case 1:
                                        employee.setName(cellValue);
                                        break;
                                    case 2:
                                        employee.setGender(cellValue);
                                        break;
                                    case 3:
                                        employee.setJobNumber(cellValue);
                                        break;
//                                    case 4:
//                                        employee.setAge(Integer.valueOf(cellValue));
//                                        break;


                                    case 6:
                                        employee.setIdCard(cellValue);
                                        break;
                                    case 7:
                                        employee.setAddress(cellValue);
                                        break;
                                    case 8:
                                        employee.setNation(cellValue);
                                        break;
                                    case 9:
                                        employee.setEmail(cellValue);
                                        break;
                                    case 10:
                                        employee.setPhone(cellValue);
                                        break;
                                    case 11:
                                        employee.setDepartment(cellValue);
                                        break;
                                    case 12:
                                        employee.setPosition(cellValue);
                                        break;


                                }
//                            case NUMERIC:
//                                double integer = cell.getNumericCellValue();
//                                switch (k){
//                                    case 4:
//                                        employee.setAge((int) integer);
//                                        break;
//                                }
                            default:
                                switch (k){

                                    case 3:
                                        String s = String.valueOf(cell.getNumericCellValue());
                                        employee.setJobNumber(s.substring(0,5));
                                        break;
                                    case 4:
                                        employee.setAge(Integer.valueOf((int) cell.getNumericCellValue()));
                                        break;

                                    case 5:
                                        employee.setBirthday(cell.getDateCellValue());
                                        break;


                                }
                        }
                    }
                    list.add(employee);
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return list;
    }
}
