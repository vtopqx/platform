package cn.innovation.platform.system.service;

import cn.innovation.platform.system.domain.InsuranceVinsunRecords;
import java.util.List;

/**
 * 畅思保险申请记录 服务层
 * 
 * @author mqx
 * @date 2018-12-16
 */
public interface IInsuranceVinsunRecordsService 
{
	/**
     * 查询畅思保险申请记录信息
     * 
     * @param id 畅思保险申请记录ID
     * @return 畅思保险申请记录信息
     */
	public InsuranceVinsunRecords selectInsuranceVinsunRecordsById(Integer id);
	
	/**
     * 查询畅思保险申请记录列表
     * 
     * @param insuranceVinsunRecords 畅思保险申请记录信息
     * @return 畅思保险申请记录集合
     */
	public List<InsuranceVinsunRecords> selectInsuranceVinsunRecordsList(InsuranceVinsunRecords insuranceVinsunRecords);
	
	/**
     * 新增畅思保险申请记录
     * 
     * @param insuranceVinsunRecords 畅思保险申请记录信息
     * @return 结果
     */
	public int insertInsuranceVinsunRecords(InsuranceVinsunRecords insuranceVinsunRecords);
	
	/**
     * 修改畅思保险申请记录
     * 
     * @param insuranceVinsunRecords 畅思保险申请记录信息
     * @return 结果
     */
	public int updateInsuranceVinsunRecords(InsuranceVinsunRecords insuranceVinsunRecords);
		
	/**
     * 删除畅思保险申请记录信息
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	public int deleteInsuranceVinsunRecordsByIds(String ids);
	
}
