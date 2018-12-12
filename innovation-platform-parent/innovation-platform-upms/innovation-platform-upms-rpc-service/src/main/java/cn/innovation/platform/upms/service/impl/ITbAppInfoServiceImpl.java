package cn.innovation.platform.upms.service.impl;

import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;

import cn.innovation.platform.upms.common.model.TbAppInfo;
import cn.innovation.platform.upms.mapper.TbAppInfoMapper;
import cn.innovation.platform.upms.service.ITbAppInfoService;

/**
 * @ClassName: ITbAppInfoServiceImpl 
 * @Description: 应用注册信息接口实现
 * @author mqx 
 * @date 2018年12月11日 下午10:51:33
 */
@Service("appInfoService")
public class ITbAppInfoServiceImpl extends ServiceImpl<TbAppInfoMapper, TbAppInfo> implements ITbAppInfoService {

}
