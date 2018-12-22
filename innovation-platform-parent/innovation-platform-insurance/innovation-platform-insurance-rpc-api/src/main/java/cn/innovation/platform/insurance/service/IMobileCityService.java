package cn.innovation.platform.insurance.service;

/**
 * @ClassName: MobileCityService
 * @Description: 号码归属地查询
 * @author mqx
 * @date 2018年12月22日 下午12:22:28
 */
public interface IMobileCityService {

	/**
	 * @Description: 根据号码查询归属地
	 * @param mobile
	 *            手机号码
	 * @return
	 */
	public String getCityByMobile(String mobile);
}
