package com.plutus.dao;

import org.apache.commons.io.IOUtils;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.springframework.util.ObjectUtils;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

/**
 * 根据mybatis.xml中配置的不同的environment创建对应的SqlSessionFactory
 */
public final class DataSourceSqlSessionFactory {
    private static final String CONFIGURATION_PATH = "mybatis-config.xml";

    private static final Map<DataSourceEnvironment, SqlSessionFactory> SQLSESSIONFACTORYS
            = new HashMap<DataSourceEnvironment, SqlSessionFactory>();

    /**
     * 根据指定的DataSourceEnvironment获取对应的SqlSessionFactory
     *
     * @param environment 数据源environment
     * @return SqlSessionFactory
     */
    public static SqlSessionFactory getSqlSessionFactory(DataSourceEnvironment environment) {
        SqlSessionFactory sqlSessionFactory = SQLSESSIONFACTORYS.get(environment);
        if (null != sqlSessionFactory)
            return sqlSessionFactory;
        else {
            InputStream inputStream = null;
            try {
                inputStream = Resources.getResourceAsStream(CONFIGURATION_PATH);
                sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream, environment.name());

//                logger.info("Get {} SqlSessionFactory successfully.", environment.name());
            } catch (IOException e) {
//                logger.warn("Get {} SqlSessionFactory error.", environment.name());
//                logger.error(e.getMessage(), e);
            } finally {
                IOUtils.closeQuietly(inputStream);
            }

            SQLSESSIONFACTORYS.put(environment, sqlSessionFactory);
            return sqlSessionFactory;
        }
    }

    /**
     * 配置到Configuration.xml文件中的数据源的environment的枚举描述
     *
     * @author boyce
     * @version 2014-3-27
     */
    public static enum DataSourceEnvironment {
        DEVELOPMENT, WORK;
    }
}
