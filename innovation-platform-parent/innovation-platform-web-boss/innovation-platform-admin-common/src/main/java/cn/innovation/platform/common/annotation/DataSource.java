package cn.innovation.platform.common.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import cn.innovation.platform.common.enums.DataSourceType;

/**
 * 自定义多数据源切换注解
 * 
 * @author mqx
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface DataSource
{
    /**
     * 切换数据源名称
     */
    public DataSourceType value() default DataSourceType.MASTER;
}
