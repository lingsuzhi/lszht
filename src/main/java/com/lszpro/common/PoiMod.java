package com.lszpro.common;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.hssf.util.HSSFColor;

import java.io.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * Poi写Excel
 *
 * @author jianggujin
 */
public class PoiMod {

    public static void doXls(OutputStream outputStream, List<Object> sList, Map<String, String> title) {
        //title 传的是  表头 和 表头注解

        HSSFWorkbook workbook = null;
        workbook = new HSSFWorkbook();
        HSSFSheet sheet = workbook.createSheet("sheet");
        //创建第一栏
        HSSFRow headRow = sheet.createRow(0);
        String[] titleArray = new String[title.values().size()];
        title.values().toArray(titleArray);

        for (int m = 0; m < titleArray.length; m++) {
            HSSFCell cell = headRow.createCell(m);
            cell.setCellType(HSSFCell.CELL_TYPE_STRING);
            sheet.setColumnWidth(m, 6000);
            HSSFCellStyle style = workbook.createCellStyle();
            HSSFFont font = workbook.createFont();
            font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
            short color = HSSFColor.RED.index;
            font.setColor(color);
            style.setFont(font);
            //填写数据
            cell.setCellStyle(style);
            cell.setCellValue(titleArray[m]);

        }


        //写入数据
        for (int i = 0; i < sList.size(); i++) {
            Object obj = sList.get(i);

            Map<String, String> bMap= LszUtil.jsonObjToMap(obj);


            HSSFRow row = sheet.createRow(i + 1);
            int n = 0;
            for (String key : title.keySet()) {
                Object mObj = bMap.get(key);
                row.createCell(n);
                if(mObj != null){
                    row.getCell(n).setCellValue(mObj.toString());
                }

                n++;
            }
        }
        try {
            workbook.write(outputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}