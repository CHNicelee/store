package com.ice.util;

import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.InputStream;

/**
 * Created by asd on 9/20/2017.
 */
public class MybatisUtil {
    public static SqlSessionFactory getFactory(){
        String resource="conf.xml";

        //加载mybatis 的配置文件（它也加载关联的映射文件）
        InputStream is=MybatisUtil.class.getClassLoader().getResourceAsStream(resource);

        //构建sqlSession 的工厂
        SqlSessionFactory factory=new SqlSessionFactoryBuilder().build(is);
        return factory;
    }
}