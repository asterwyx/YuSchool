package com.yuschool.utils.interceptor;

import com.yuschool.annotation.CreatedTime;
import com.yuschool.annotation.UpdatedTime;
import org.apache.ibatis.executor.Executor;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.mapping.SqlCommandType;
import org.apache.ibatis.plugin.*;

import java.lang.reflect.Field;
import java.sql.Timestamp;
import java.util.Properties;

@Intercepts({@Signature(type = Executor.class, method = "update", args = {MappedStatement.class, Object.class})})
public class GenerateTimeInterceptor implements Interceptor {

    @Override
    public Object intercept(Invocation invocation) throws Throwable {
        MappedStatement mappedStatement = (MappedStatement) invocation.getArgs()[0];
        SqlCommandType sqlCommandType = mappedStatement.getSqlCommandType();
        Object parameter = invocation.getArgs()[1];
        Field[] declaredFields = parameter.getClass().getDeclaredFields();
        for (Field field : declaredFields) {
            if (field.getAnnotation(CreatedTime.class) != null) {
                // insert语句插入created_time
                if (SqlCommandType.INSERT.equals(sqlCommandType)) {
                    field.setAccessible(true);
                    field.set(parameter, new Timestamp(System.currentTimeMillis()));
                }
            }
            if (field.getAnnotation(UpdatedTime.class) != null) {
                if (SqlCommandType.INSERT.equals(sqlCommandType) || SqlCommandType.UPDATE.equals(sqlCommandType)) {
                    field.setAccessible(true);
                    field.set(parameter, new Timestamp(System.currentTimeMillis()));
                }
            }
        }
        return invocation.proceed();
    }

    @Override
    public Object plugin(Object target) {
        if (target instanceof org.apache.ibatis.executor.Executor) {
            return Plugin.wrap(target, this);
        }
        return target;
    }

    @Override
    public void setProperties(Properties properties) {
    }
}
