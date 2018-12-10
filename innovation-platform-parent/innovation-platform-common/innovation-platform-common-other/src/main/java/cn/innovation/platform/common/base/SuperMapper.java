package cn.innovation.platform.common.base;

/**
 * <p>
 * 自定义 mapper 父类
 * </p>
 * @author 刘利民
 */
public interface SuperMapper<T> extends com.baomidou.mybatisplus.mapper.BaseMapper<T> {

    // 这里可以写自己的公共方法、注意不要让 mp 扫描到误以为是实体 Base 的操作
}
