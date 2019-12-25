package com.demo.utils;
import com.alibaba.fastjson.JSON;
import com.demo.bean.Student;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.util.*;

/**
 * Copyright (C), 2017-2019, Landy
 * Author: Administrator
 * Date: 2019/12/24 13:56
 * FileName: IOUtil
 * Description:
 */
public class IOUtil {
   //从txt中读数据到集合
    public static String readData(String filePath, ArrayList<Student> array) throws IOException {
        //创建输入缓冲流对象
        BufferedReader br = new BufferedReader(new FileReader(filePath));

        String line;
        while((line=br.readLine())!=null) {
            String[] datas = line.split(",");
            Student s = new Student();
            s.setId(datas[0]);
            s.setTitle(datas[1]);
            s.setBrand(datas[2]);
            s.setPrice(datas[3]);
            array.add(s);
        }

        br.close();
        return  JSON.toJSONString(array);
    }

    //把集合中的数据写入txt文件
    public static void writeData(String filePath,ArrayList<Student> array) throws IOException {
        //创建输出缓冲流对象
        BufferedWriter bw = new BufferedWriter(new FileWriter(filePath));

        for (Student s : array) {
            StringBuilder sb = new StringBuilder();
            sb.append(s.getId()).append(",").append(s.getTitle()).append(",").append(s.getPrice()).append(",").append(s.getBrand());

            bw.write(sb.toString());
            bw.newLine();
            bw.flush();
        }

        bw.close();
    }

    //读取json文件
    public static String readJson(String file) throws IOException {
        FileInputStream fis = new FileInputStream(file);
        byte[] bytes = new byte[1024];
        int l = 0;
        StringBuffer sb = new StringBuffer();
        while ((l=fis.read(bytes))!=-1){
            sb.append(new String(bytes,0,l));
        }
        fis.close();
        return sb.toString();
    }



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
                String title = getValue(row.getCell(0));

                String brand = getValue(row.getCell(1));


                String price = getValue( row.getCell(2));
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

    //进行Excel类型转换
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


    public static String file (MultipartFile file) throws IOException {
        String filepath ="H:/es/";
        //获取上传文件的名称
        String name = file. getOriginalFilename();
        //断言
        assert name != null;
        //进行重新命名
        String fileName = UUID. randomUUID() + name. substring(name . indexOf("."));
        //获取绝对路径
        String myPath=filepath + fileName;
        //存储
        file. transferTo(new File(myPath));
        return myPath;
    }

}
