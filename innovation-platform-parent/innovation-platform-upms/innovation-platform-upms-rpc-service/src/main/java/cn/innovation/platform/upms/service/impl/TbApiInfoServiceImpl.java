package cn.innovation.platform.upms.service.impl;

import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;

import cn.innovation.platform.upms.common.model.TbApiInfo;
import cn.innovation.platform.upms.mapper.TbApiInfoMapper;
import cn.innovation.platform.upms.service.ITbApiInfoService;

/**
 * <p>
 * 上游渠道信息表 服务实现类
 * 
 * @author Administrator
 * @since 2018-12-23
 */
@Service("apiInfoService")
public class TbApiInfoServiceImpl extends ServiceImpl<TbApiInfoMapper, TbApiInfo> implements ITbApiInfoService {


}
