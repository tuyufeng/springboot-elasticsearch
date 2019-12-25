package com.demo.service;

import java.util.List;

public interface StudentService {


    /**
     * 导入txt json  excle
     * @param filePath
     */
    void importItem(String filePath);


    List getList();
}
