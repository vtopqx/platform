package cn.innovation.platform.upms.service.impl;

import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;

import cn.innovation.platform.upms.common.model.TbApiCompany;
import cn.innovation.platform.upms.mapper.TbApiCompanyMapper;
import cn.innovation.platform.upms.service.IApiCompanyService;

/**
 * <p>
 * 上游渠道公司表 服务实现类
 * </p>
 *
 * @author Administrator
 * @since 2018-12-25
 */
@Service("apiCompanyService")
public class TbApiCompanyServiceImpl extends ServiceImpl<TbApiCompanyMapper, TbApiCompany> implements IApiCompanyService {

	
}
