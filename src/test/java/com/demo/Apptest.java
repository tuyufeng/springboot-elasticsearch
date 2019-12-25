package com.demo;


import com.demo.bean.Student;
import com.demo.service.StudentService;
import com.demo.utils.ESUtils;
import com.demo.utils.IOUtil;
import io.searchbox.client.JestClient;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@SpringBootTest
@RunWith(SpringJUnit4ClassRunner.class)
public class Apptest {
    @Autowired
    ElasticsearchTemplate elasticsearchTemplate;
    @Autowired
    JestClient jestClient;
    @Autowired
    StudentService studentService;


    /**
     * 导入txt
     */
    @Test
    public void testInTxt(){
        String filePath = "F:/teacher/ideaWorkspace/shixun1/cloud/ES/src/main/java/com/demo/utils/files/a.txt";
        studentService.importItem(filePath);
    }



    /*
    导入json
     */
    @Test
    public void testInJson(){
        String filePath = "F:/teacher/ideaWorkspace/shixun1/cloud/ES/src/main/java/com/demo/utils/files/aa.json";
        studentService.importItem(filePath);
    }


    /*
  导入excle
   */
    @Test
    public void testInExcle(){
        String filePath = "F:/teacher/ideaWorkspace/shixun1/cloud/ES/src/main/java/com/demo/utils/files/es.xlsx";
        studentService.importItem(filePath);
    }


    //读取并存入txt
    @Test
    public  void  testTxt() throws Exception {
        String fileName = "F:/teacher/ideaWorkspace/shixun1/cloud/ES/src/main/java/com/demo/utils/files/a.txt";
        Student item = new Student();

        item.setId(UUID.randomUUID().toString());
        item.setTitle("华为手机pl");
        item.setPrice("2313.00");
        item.setBrand("华为");
        ArrayList<Student> objects = new ArrayList<>();

        IOUtil.writeData(fileName, objects);
    }



    @Test
    public void testMysql(){
        List list = studentService.getList();
        ESUtils.add(elasticsearchTemplate,list);
    }
}
