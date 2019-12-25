package com.demo.utils;/**
 * Created by Enzo Cotter on 2019/12/24.
 */

import com.alibaba.fastjson.JSON;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Copyright (C), 2017-2019, Landy
 * Author: Administrator
 * Date: 2019/12/24 14:09
 * FileName: ExcelUtils
 * Description:
 */
public class ExcelUtils {

    //导入Excel数据

    public static String importExcelAction(String filePath) throws Exception {


        XSSFWorkbook wookbook = new XSSFWorkbook(new FileInputStream(filePath));

        XSSFSheet sheet = wookbook.getSheet("Sheet1");

        //获取到Excel文件中的所有行数

        int rows = sheet.getPhysicalNumberOfRows();

        //遍历行

        List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();

        for (int i = 1; i < rows; i++) {

            // 读取左上端单元格

            XSSFRow row = sheet.getRow(i);

// 行不为空

            if (row != null) {

                Map<String, Object> map = new HashMap<String, Object>();
//获取到Excel文件中的所有的列
                int cells = row.getPhysicalNumberOfCells();
                String title = getValue(row.getCell(1));
                String brand = getValue(row.getCell(2));
                String price = getValue( row.getCell(3));
                map.put("id", UUID.randomUUID().toString());
                map.put("title", title);
                map.put("brand", brand);
                map.put("price", price);
                list.add(map);
            }

        }

        //System.out.println("list = " + JSON.toJSONString(list));
        return JSON.toJSONString(list);
    }

    private static String getValue(XSSFCell xSSFCell) {

        if (null == xSSFCell) {

            return "";

        }

        if (xSSFCell.getCellType() == xSSFCell.CELL_TYPE_BOOLEAN) {

// 返回布尔类型的值  

            return String.valueOf(xSSFCell.getBooleanCellValue());

        } else if (xSSFCell.getCellType() == xSSFCell.CELL_TYPE_NUMERIC) {

// 返回数值类型的值  

            return String.valueOf(xSSFCell.getNumericCellValue());

        } else {

// 返回字符串类型的值  
            return String.valueOf(xSSFCell.getStringCellValue());

        }

    }


}
