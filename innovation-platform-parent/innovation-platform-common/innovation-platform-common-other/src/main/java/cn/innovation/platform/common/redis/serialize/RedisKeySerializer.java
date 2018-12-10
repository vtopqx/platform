package cn.innovation.platform.common.redis.serialize;

import java.nio.charset.Charset;

import org.springframework.core.convert.ConversionService;
import org.springframework.core.convert.support.DefaultConversionService;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.SerializationException;

import com.xiaoleilu.hutool.lang.Assert;

import cn.innovation.platform.common.utils.Charsets;

/**
 * Redis key序列化，支持更多的基本类型
 * @author 刘利民
 *
 */
public class RedisKeySerializer implements RedisSerializer<Object> {

    private final Charset charset;

    private final ConversionService conversionService;

    public RedisKeySerializer() {
        this(Charsets.UTF_8);
    }

    public RedisKeySerializer(Charset charset) {
        Assert.notNull(charset, "Charset must not be null");
        this.charset = charset;
        conversionService = DefaultConversionService.getSharedInstance();
    }

    @Override
    public byte[] serialize(Object o) throws SerializationException {
        if (o == null) {
            return null;
        }
        String string = conversionService.convert(o, String.class);
        return string.getBytes(charset);
    }

    @Override
    public Object deserialize(byte[] bytes) throws SerializationException {
        throw new RuntimeException("Used only for serializing key, not for deserialization.");
    }
}
