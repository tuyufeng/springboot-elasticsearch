package com.demo.dao;


import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public  interface StudentMapper {
     List getList();
}
