package com.demo.service;


import com.alibaba.fastjson.JSON;
import com.demo.bean.Student;
import com.demo.dao.StudentMapper;
import com.demo.utils.ESUtils;
import com.demo.utils.IOUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
public class StudentServiceImpl implements  StudentService {

    @Autowired
    private ElasticsearchTemplate elasticsearchTemplate;

    @Resource
    StudentMapper studentMapper;



    @Override
    public void importItem(String filePath) {
        String  s = null;
        ArrayList list = new ArrayList();
        //获取文件类型后缀
        String substring = filePath.substring(filePath.lastIndexOf(".") + 1);
        if(substring.equals("txt")){
            try {
                s = IOUtil.readData(filePath,list);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }else if(substring.equals("xlsx")){
            try {
                s = IOUtil.importExcelAction(filePath);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }else if(substring.equals("json")){
            try {
                s = IOUtil.readJson(filePath);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        //打印
        List<Student> students = JSON.parseArray(s, Student.class);

        //添加断言
      //  assert students!= null;

        //添加数据到es中
        ESUtils.add(elasticsearchTemplate,students);
    }




    @Override
    public List getList() {
        return studentMapper.getList();
    }


}
