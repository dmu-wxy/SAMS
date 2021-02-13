package com.wxy.sams.util;

import com.wxy.sams.model.Animal;
import org.apache.poi.hpsf.DocumentSummaryInformation;
import org.apache.poi.hpsf.SummaryInformation;
import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.IndexedColors;
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
    public static final String[] ANIMAL_PARAM = {"编号","名字","品种","位置","性别","出生日期"};
    public static final Integer[] PARAM_LENGTH = {5,8,8,15,3,11};

    public static ResponseEntity<byte[]> animal2Excel(List<Animal> animalList) {
        //1. 创建一个 Excel 文档
        HSSFWorkbook workbook = new HSSFWorkbook();
        //2. 创建文档摘要
        workbook.createInformationProperties();
        //3. 获取并配置文档信息
        DocumentSummaryInformation docInfo = workbook.getDocumentSummaryInformation();
        //文档类别
        docInfo.setCategory("流浪动物信息");
        //文档管理员
        docInfo.setManager("meteor");
        //设置公司信息
        docInfo.setCompany("www.smartdog.top");
        //4. 获取文档摘要信息
        SummaryInformation summInfo = workbook.getSummaryInformation();
        //文档标题
        summInfo.setTitle("流浪动物信息表");
        //文档作者
        summInfo.setAuthor("meteor");
        // 文档备注
        summInfo.setComments("本文档由 meteor 提供");
        //5. 创建样式
        //创建标题行的样式
        HSSFCellStyle headerStyle = workbook.createCellStyle();
        headerStyle.setFillForegroundColor(IndexedColors.YELLOW.index);
        headerStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
        HSSFCellStyle dateCellStyle = workbook.createCellStyle();
        dateCellStyle.setDataFormat(HSSFDataFormat.getBuiltinFormat("m/d/yy"));
        HSSFSheet sheet = workbook.createSheet("流浪动物信息表");
        //设置列的宽度
        sheet.setColumnWidth(0, 5 * 256);
        sheet.setColumnWidth(1, 10 * 256);
        sheet.setColumnWidth(2, 10 * 256);
        sheet.setColumnWidth(3, 20 * 256);
        sheet.setColumnWidth(4, 3 * 256);
        sheet.setColumnWidth(5, 12 * 256);
        //6. 创建标题行
        HSSFRow r0 = sheet.createRow(0);
        HSSFCell c0 = r0.createCell(0);
        c0.setCellValue("编号");
        c0.setCellStyle(headerStyle);
        HSSFCell c1 = r0.createCell(1);
        c1.setCellStyle(headerStyle);
        c1.setCellValue("姓名");
        HSSFCell c2 = r0.createCell(2);
        c2.setCellStyle(headerStyle);
        c2.setCellValue("品种");
        HSSFCell c3 = r0.createCell(3);
        c3.setCellStyle(headerStyle);
        c3.setCellValue("位置");
        HSSFCell c4 = r0.createCell(4);
        c4.setCellStyle(headerStyle);
        c4.setCellValue("性别");
        HSSFCell c5 = r0.createCell(5);
        c5.setCellStyle(headerStyle);
        c5.setCellValue("出生日期");

        for (int i = 0; i < animalList.size(); i++) {
            Animal animal = animalList.get(i);
            HSSFRow row = sheet.createRow(i + 1);
            row.createCell(0).setCellValue(animal.getAid());
            row.createCell(1).setCellValue(animal.getAname());
            row.createCell(2).setCellValue(animal.getBreed());
            row.createCell(3).setCellValue(animal.getP_addr());
            row.createCell(4).setCellValue(animal.getGender());
            HSSFCell cell4 = row.createCell(5);
            cell4.setCellStyle(dateCellStyle);
            cell4.setCellValue(animal.getBirth());
        }

        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        HttpHeaders headers = new HttpHeaders();
        try {
            headers.setContentDispositionFormData("attachment", new String("流浪动物信息表.xls".getBytes("UTF-8"), "ISO-8859-1"));
            headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
            workbook.write(baos);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new ResponseEntity<byte[]>(baos.toByteArray(), headers, HttpStatus.CREATED);
    }

    /**
     * excel解析成动物数据集合
     * @param file
     * @return
     */
    public static List<Animal> excel2Animal(MultipartFile file) {
        List<Animal> animals = new ArrayList<>();
        Animal animal = null;
        try {
            //1.创建一个workbook对象
            HSSFWorkbook workbook = new HSSFWorkbook(file.getInputStream());
            //2.获取workbook中的表单数量
            int number = workbook.getNumberOfSheets();
            for(int i = 0;i < number;i++){
                //3.获取表单
                HSSFSheet sheet = workbook.getSheetAt(i);
                //4.获取表单的行数
                int rows = sheet.getPhysicalNumberOfRows();
                for(int j = 0;j < rows;j++){
                    if(j == 0){
                        continue;//跳过标题行
                    }
                    //5.获取行
                    HSSFRow row = sheet.getRow(j);
                    if(row == null){
                        continue; //跳过空行
                    }
                    //6.获取列数
                    int cells = row.getPhysicalNumberOfCells();
                    animal = new Animal();
                    for(int k = 0;k < cells;k++){
                        HSSFCell cell = row.getCell(k);
                        switch (cell.getCellType()){
                            case STRING:
                                String cellValue = cell.getStringCellValue();
                                switch (k){
                                    case 1:
                                        animal.setAname(cellValue);
                                        break;
                                    case 2:
                                        animal.setBreed(cellValue);
                                        break;
                                    case 3:
                                        animal.setP_addr(cellValue);
                                        break;
                                    case 4:
                                        animal.setGender(cellValue);
                                }
                                break;
                            default:{
                                //处理日期数据
                                animal.setBirth(cell.getDateCellValue());
                            }
                            break;
                        }
                    }
                    animals.add(animal);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return animals;
    }
}
