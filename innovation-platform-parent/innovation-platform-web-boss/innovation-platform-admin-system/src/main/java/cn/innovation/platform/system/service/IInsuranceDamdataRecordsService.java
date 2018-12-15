package cn.innovation.platform.system.service;

import cn.innovation.platform.system.domain.InsuranceDamdataRecords;
import java.util.List;

/**
 * 大坝保险申请记录 服务层
 * 
 * @author mqx
 * @date 2018-12-13
 */
public interface IInsuranceDamdataRecordsService 
{
	/**
     * 查询大坝保险申请记录信息
     * 
     * @param id 大坝保险申请记录ID
     * @return 大坝保险申请记录信息
     */
	public InsuranceDamdataRecords selectInsuranceDamdataRecordsById(Integer id);
	
	/**
     * 查询大坝保险申请记录列表
     * 
     * @param insuranceDamdataRecords 大坝保险申请记录信息
     * @return 大坝保险申请记录集合
     */
	public List<InsuranceDamdataRecords> selectInsuranceDamdataRecordsList(InsuranceDamdataRecords insuranceDamdataRecords);
	
	/**
     * 新增大坝保险申请记录
     * 
     * @param insuranceDamdataRecords 大坝保险申请记录信息
     * @return 结果
     */
	public int insertInsuranceDamdataRecords(InsuranceDamdataRecords insuranceDamdataRecords);
	
	/**
     * 修改大坝保险申请记录
     * 
     * @param insuranceDamdataRecords 大坝保险申请记录信息
     * @return 结果
     */
	public int updateInsuranceDamdataRecords(InsuranceDamdataRecords insuranceDamdataRecords);
		
	/**
     * 删除大坝保险申请记录信息
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	public int deleteInsuranceDamdataRecordsByIds(String ids);
	
}
