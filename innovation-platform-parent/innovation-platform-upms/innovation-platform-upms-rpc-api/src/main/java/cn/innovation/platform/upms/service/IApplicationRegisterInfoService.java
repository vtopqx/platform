package cn.innovation.platform.upms.service;

import com.baomidou.mybatisplus.service.IService;

import cn.innovation.platform.upms.common.dto.AppRegisterInfoDto;
import cn.innovation.platform.upms.common.model.ApplicationRegisterInfo;

/**
 * @ClassName: IApplicationRegisterInfoService
 * @Description: 应用注册服务
 * @author mqx
 * @date 2017年12月7日 下午1:47:25
 */
public interface IApplicationRegisterInfoService extends IService<ApplicationRegisterInfo> {

	
	/**
	 * @Description: 应用注册接口
	 * @param AppRegisterInfoDto 注册信息参数对象
	 */
	public boolean registerAppUser(AppRegisterInfoDto infoDto);
	
	/**
	 * @Description: 查询应用注册信息
	 * @param consumerKey key id
	 * @return
	 */
	public ApplicationRegisterInfo getApplicationAccount(String consumerKey);
}
