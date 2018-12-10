package cn.innovation.platform.upms.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.xiaoleilu.hutool.log.Log;
import com.xiaoleilu.hutool.log.LogFactory;

import cn.innovation.platform.upms.common.dto.AppRegisterInfoDto;
import cn.innovation.platform.upms.common.model.ApplicationRegisterInfo;
import cn.innovation.platform.upms.mapper.ApplicationRegisterInfoMapper;
import cn.innovation.platform.upms.service.IApplicationRegisterInfoService;

/**
 * @ClassName: ApplicationRegisterInfoServiceImpl
 * @Description: 应用注册服务实现
 * @author mqx
 * @date 2017年12月7日 下午1:48:13
 */
@Service("applicationRegisterInfoService")
public class ApplicationRegisterInfoServiceImpl extends
		ServiceImpl<ApplicationRegisterInfoMapper, ApplicationRegisterInfo> implements IApplicationRegisterInfoService {

	private static final Log logger = LogFactory.get();


	/**
	 * 根据Key获取注册信息 验证consumerKey是否正确
	 */
	@Override
	public ApplicationRegisterInfo getApplicationAccount(String consumerKey) {
		logger.info("upms:查询应用注册信息!");
		EntityWrapper<ApplicationRegisterInfo> wrapper = new EntityWrapper<ApplicationRegisterInfo>();
		wrapper.where("consumer_key = {0}", consumerKey);
		List<ApplicationRegisterInfo> list = this.selectList(wrapper);
		if (list!=null && list.size()>0) {
			return list.get(0);
		}
		return null;
	}

	/**
	 * app注册创新平台consumerKey用户
	 */
	@Override
	public boolean registerAppUser(AppRegisterInfoDto registerInfo) {
		boolean isSuccess = false;
		return isSuccess;
	}

}
