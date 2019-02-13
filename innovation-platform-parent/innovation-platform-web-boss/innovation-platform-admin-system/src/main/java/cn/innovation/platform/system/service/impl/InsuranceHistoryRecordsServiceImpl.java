package cn.innovation.platform.system.service.impl;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.xiaoleilu.hutool.log.Log;
import com.xiaoleilu.hutool.log.LogFactory;

import cn.innovation.platform.common.enums.SendStatusEnum;
import cn.innovation.platform.common.support.Convert;
import cn.innovation.platform.common.utils.DateUtils;
import cn.innovation.platform.system.domain.InsuranceHistoryRecords;
import cn.innovation.platform.system.dto.InsuranceStatisDto;
import cn.innovation.platform.system.mapper.InsuranceHistoryRecordsMapper;
import cn.innovation.platform.system.service.IInsuranceHistoryRecordsService;

/**
 * 保险申请记录汇总 服务层实现
 * 
 * @author mqx
 * @date 2018-12-22
 */
@Service
public class InsuranceHistoryRecordsServiceImpl implements IInsuranceHistoryRecordsService {

	private static final Log logger = LogFactory.get();
	
	
	@Autowired
	private InsuranceHistoryRecordsMapper insuranceHistoryRecordsMapper;

	/**
	 * 查询保险申请记录汇总信息
	 * 
	 * @param id
	 *            保险申请记录汇总ID
	 * @return 保险申请记录汇总信息
	 */
	@Override
	public InsuranceHistoryRecords selectInsuranceHistoryRecordsById(Integer id) {
		return insuranceHistoryRecordsMapper.selectInsuranceHistoryRecordsById(id);
	}

	/**
	 * 查询保险申请记录汇总列表
	 * 
	 * @param insuranceHistoryRecords
	 *            保险申请记录汇总信息
	 * @return 保险申请记录汇总集合
	 */
	@Override
	public List<InsuranceHistoryRecords> selectInsuranceHistoryRecordsList(
			InsuranceHistoryRecords insuranceHistoryRecords) {
		return insuranceHistoryRecordsMapper.selectInsuranceHistoryRecordsList(insuranceHistoryRecords);
	}

	/**
	 * 新增保险申请记录汇总
	 * 
	 * @param insuranceHistoryRecords
	 *            保险申请记录汇总信息
	 * @return 结果
	 */
	@Override
	public int insertInsuranceHistoryRecords(InsuranceHistoryRecords insuranceHistoryRecords) {
		return insuranceHistoryRecordsMapper.insertInsuranceHistoryRecords(insuranceHistoryRecords);
	}

	/**
	 * 修改保险申请记录汇总
	 * 
	 * @param insuranceHistoryRecords
	 *            保险申请记录汇总信息
	 * @return 结果
	 */
	@Override
	public int updateInsuranceHistoryRecords(InsuranceHistoryRecords insuranceHistoryRecords) {
		return insuranceHistoryRecordsMapper.updateInsuranceHistoryRecords(insuranceHistoryRecords);
	}

	/**
	 * 删除保险申请记录汇总对象
	 * 
	 * @param ids
	 *            需要删除的数据ID
	 * @return 结果
	 */
	@Override
	public int deleteInsuranceHistoryRecordsByIds(String ids) {
		return insuranceHistoryRecordsMapper.deleteInsuranceHistoryRecordsByIds(Convert.toStrArray(ids));
	}

	@Override
	public List<InsuranceStatisDto> selectInsuranceStatis(InsuranceHistoryRecords insuranceHistoryRecords) {
		List<InsuranceStatisDto> dataList = new ArrayList<InsuranceStatisDto>();
		// 创建最后一行总计
		InsuranceStatisDto footRow = new InsuranceStatisDto();
		footRow.setDate("总计");
		Integer allTotal = 0;
		Integer allMTotal = 0;
		// 34个省市
		Integer allBjTotal = 0;
		Integer allTjTotal = 0;
		Integer allShTotal = 0;
		Integer allCqTotal = 0;
		Integer allHbTotal = 0;
		Integer allAhTotal = 0;
		Integer allScTotal = 0;
		Integer allJsTotal = 0;
		Integer allZjTotal = 0;
		Integer allFjTotal = 0;
		Integer allSdTotal = 0;
		Integer allHenTotal = 0;
		Integer allHubTotal = 0;
		Integer allHunTotal = 0;
		Integer allGdTotal = 0;
		Integer allGxTotal = 0;
		Integer allHainTotal = 0;
		Integer allJxTotal = 0;
		Integer allSanxTotal = 0;
		Integer allGzTotal = 0;
		Integer allYnTotal = 0;
		Integer allXzTotal = 0;
		Integer allSxTotal = 0;
		Integer allLnTotal = 0;
		Integer allJlTotal = 0;
		Integer allNmgTotal = 0;
		Integer allGsTotal = 0;
		Integer allQhTotal = 0;
		Integer allNxTotal = 0;
		Integer allXjTotal = 0;
		Integer allTwTotal = 0;
		Integer allXgTotal = 0;
		Integer allAmTotal = 0;
		Integer allHljTotal = 0;

		try {
			String currentMouth = "";
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM");
			if (StringUtils.isEmpty(insuranceHistoryRecords.getCreateTime())) {
				currentMouth = sdf.format(new Date());
			}else{
				currentMouth = sdf.format(insuranceHistoryRecords.getCreateTime());
			}
			// 获取该月所有日期
			Date currentDate = sdf.parse(currentMouth);
			List<Date> allDateList = DateUtils.getAllTheDateOftheMonth(currentDate);

			// 查询数据
			List<InsuranceHistoryRecords> staticList = insuranceHistoryRecordsMapper
					.selectInsuranceStatis(insuranceHistoryRecords);

			// 每日总进件量
			Map<String, Integer> allMap = new HashMap<String, Integer>();
			// 每日各省毛名单
			Map<String, Map<String, Integer>> mPMap = new HashMap<String, Map<String, Integer>>();
			// 每日毛名单累计
			Map<String, Integer> mMap = new HashMap<String, Integer>();
			if (staticList != null && staticList.size() > 0) {
				// 计算[每日总进件量/每日各省毛名单/每日毛名单累计]
				for (InsuranceHistoryRecords item : staticList) {
					// 省份
					String province = item.getClientProvince();
					// 日期
					String date = item.getBirth();
					String status = item.getStatus();
					// 数量
					int dayTotal = Integer.valueOf(item.getSpBillno());
					// 1、计算每天进件量
					if (!allMap.containsKey(date)) {
						allMap.put(date, dayTotal);
					} else {
						int sum = Integer.valueOf(allMap.get(date));
						allMap.put(date, sum + dayTotal);
					}
					// 2、计算各省每天毛名单
					if (status.equals(SendStatusEnum.success.getId())) {
						// 各省毛名单
						Map<String, Integer> pMap = mPMap.get(date);
						if (pMap == null || pMap.size() <= 0) {
							pMap = new HashMap<String, Integer>();
						}
						if (!pMap.containsKey(province)) {
							pMap.put(province, dayTotal);
						} else {
							int sum = Integer.valueOf(pMap.get(province));
							pMap.put(province, sum + dayTotal);
						}
						mPMap.put(date, pMap);
						// 每日毛名单累计
						if (!mMap.containsKey(date)) {
							mMap.put(date, dayTotal);
						} else {
							int sum = Integer.valueOf(mMap.get(date));
							mMap.put(date, sum + dayTotal);
						}
					}
				}
			}

			// 数据合并(循环一个月)
			SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM-dd");
			for (Date mouthDay : allDateList) {
				String day = sdf2.format(mouthDay);
				InsuranceStatisDto item = new InsuranceStatisDto();
				// 日期
				item.setDate(day.substring(5));
				// 进件总数
				item.setAllTotal(allMap.get(day) == null ? "0" : allMap.get(day).toString());
				allTotal += allMap.get(day) == null ? 0 : allMap.get(day);
				// 各省毛名单数
				String bjTotal = "0";
				String tjTotal = "0";
				String shTotal = "0";
				String cqTotal = "0";
				String hbTotal = "0";
				String ahTotal = "0";
				String scTotal = "0";
				String jsTotal = "0";
				String zjTotal = "0";
				String fjTotal = "0";
				String sdTotal = "0";
				String henTotal = "0";
				String hubTotal = "0";
				String hunTotal = "0";
				String gdTotal = "0";
				String gxTotal = "0";
				String hainTotal = "0";
				String jxTotal = "0";
				String sanxTotal = "0";
				String gzTotal = "0";
				String ynTotal = "0";
				String xzTotal = "0";
				String sxTotal = "0";
				String lnTotal = "0";
				String jlTotal = "0";
				String nmgTotal = "0";
				String gsTotal = "0";
				String qhTotal = "0";
				String nxTotal = "0";
				String xjTotal = "0";
				String twTotal = "0";
				String xgTotal = "0";
				String amTotal = "0";
				String hljTotal = "0";

				Map<String, Integer> tmpMap = mPMap.get(day);
				if (tmpMap != null && tmpMap.size() > 0) {
					for (String pName : tmpMap.keySet()) {
						String pTotal = tmpMap.get(pName).toString();
						if (pName.equals("北京")) {
							bjTotal = pTotal;
							allBjTotal += tmpMap.get(pName);
						} else if (pName.equals("天津")) {
							tjTotal = pTotal;
							allTjTotal += tmpMap.get(pName);
						} else if (pName.equals("上海")) {
							shTotal = pTotal;
							allShTotal += tmpMap.get(pName);
						} else if (pName.equals("重庆")) {
							cqTotal = pTotal;
							allCqTotal += tmpMap.get(pName);
						} else if (pName.equals("河北")) {
							hbTotal = pTotal;
							allHbTotal += tmpMap.get(pName);
						} else if (pName.equals("安徽")) {
							ahTotal = pTotal;
							allAhTotal += tmpMap.get(pName);
						} else if (pName.equals("四川")) {
							scTotal = pTotal;
							allScTotal += tmpMap.get(pName);
						} else if (pName.equals("江苏")) {
							jsTotal = pTotal;
							allJsTotal += tmpMap.get(pName);
						} else if (pName.equals("浙江")) {
							zjTotal = pTotal;
							allZjTotal += tmpMap.get(pName);
						} else if (pName.equals("福建")) {
							fjTotal = pTotal;
							allFjTotal += tmpMap.get(pName);
						} else if (pName.equals("山东")) {
							sdTotal = pTotal;
							allSdTotal += tmpMap.get(pName);
						} else if (pName.equals("河南")) {
							henTotal = pTotal;
							allHenTotal += tmpMap.get(pName);
						} else if (pName.equals("湖北")) {
							hubTotal = pTotal;
							allHubTotal += tmpMap.get(pName);
						} else if (pName.equals("湖南")) {
							hunTotal = pTotal;
							allHunTotal += tmpMap.get(pName);
						} else if (pName.equals("广东")) {
							gdTotal = pTotal;
							allGdTotal += tmpMap.get(pName);
						} else if (pName.equals("广西")) {
							gxTotal = pTotal;
							allGxTotal += tmpMap.get(pName);
						} else if (pName.equals("海南")) {
							hainTotal = pTotal;
							allHainTotal += tmpMap.get(pName);
						} else if (pName.equals("江西")) {
							jxTotal = pTotal;
							allJxTotal += tmpMap.get(pName);
						} else if (pName.equals("山西")) {
							sanxTotal = pTotal;
							allSanxTotal += tmpMap.get(pName);
						} else if (pName.equals("贵州")) {
							gzTotal = pTotal;
							allGzTotal += tmpMap.get(pName);
						} else if (pName.equals("云南")) {
							ynTotal = pTotal;
							allYnTotal += tmpMap.get(pName);
						} else if (pName.equals("西藏")) {
							xzTotal = pTotal;
							allXzTotal += tmpMap.get(pName);
						} else if (pName.equals("陕西")) {
							sxTotal = pTotal;
							allSxTotal += tmpMap.get(pName);
						} else if (pName.equals("辽宁")) {
							lnTotal = pTotal;
							allLnTotal += tmpMap.get(pName);
						} else if (pName.equals("吉林")) {
							jlTotal = pTotal;
							allJlTotal += tmpMap.get(pName);
						} else if (pName.equals("内蒙古")) {
							nmgTotal = pTotal;
							allNmgTotal += tmpMap.get(pName);
						} else if (pName.equals("黑龙江")) {
							hljTotal = pTotal;
							allHljTotal += tmpMap.get(pName);
						} else if (pName.equals("甘肃省")) {
							gsTotal = pTotal;
							allGsTotal += tmpMap.get(pName);
						} else if (pName.equals("青海")) {
							qhTotal = pTotal;
							allQhTotal += tmpMap.get(pName);
						} else if (pName.equals("宁夏")) {
							nxTotal = pTotal;
							allNxTotal += tmpMap.get(pName);
						} else if (pName.equals("新疆")) {
							xjTotal = pTotal;
							allXjTotal += tmpMap.get(pName);
						} else if (pName.equals("台湾")) {
							twTotal = pTotal;
							allTwTotal += tmpMap.get(pName);
						} else if (pName.equals("香港")) {
							xgTotal = pTotal;
							allXgTotal += tmpMap.get(pName);
						} else if (pName.equals("澳门")) {
							amTotal = pTotal;
							allAmTotal += tmpMap.get(pName);
						}
					}
				}
				item.setBjTotal(bjTotal);
				item.setTjTotal(tjTotal);
				item.setShTotal(shTotal);
				item.setCqTotal(cqTotal);
				item.setHbTotal(hbTotal);
				item.setAhTotal(ahTotal);
				item.setScTotal(scTotal);
				item.setJsTotal(jsTotal);
				item.setZjTotal(zjTotal);
				item.setFjTotal(fjTotal);
				item.setSdTotal(sdTotal);
				item.setHenTotal(henTotal);
				item.setHubTotal(hubTotal);
				item.setHunTotal(hunTotal);
				item.setGdTotal(gdTotal);
				item.setGxTotal(gxTotal);
				item.setHainTotal(hainTotal);
				item.setJxTotal(jxTotal);
				item.setSanxTotal(sanxTotal);
				item.setGzTotal(gzTotal);
				item.setYnTotal(ynTotal);
				item.setXzTotal(xzTotal);
				item.setSxTotal(sxTotal);
				item.setLnTotal(lnTotal);
				item.setJlTotal(jlTotal);
				item.setNmgTotal(nmgTotal);
				item.setHljTotal(hljTotal);
				item.setGsTotal(gsTotal);
				item.setQhTotal(qhTotal);
				item.setNxTotal(nxTotal);
				item.setXjTotal(xjTotal);
				item.setTwTotal(twTotal);
				item.setXgTotal(xgTotal);
				item.setAmTotal(amTotal);

				// 毛名单累计
				item.setmTotal(mMap.get(day) == null ? "0" : mMap.get(day).toString());
				dataList.add(item);
				allMTotal = allMTotal + (mMap.get(day) == null ? 0 : mMap.get(day));
			}

			// 设置总计
			footRow.setAllTotal(allTotal.toString());
			footRow.setmTotal(allMTotal.toString());

			footRow.setBjTotal(allBjTotal.toString());
			footRow.setTjTotal(allTjTotal.toString());
			footRow.setShTotal(allShTotal.toString());
			footRow.setCqTotal(allCqTotal.toString());
			footRow.setHbTotal(allHbTotal.toString());
			footRow.setAhTotal(allAhTotal.toString());
			footRow.setScTotal(allScTotal.toString());
			footRow.setJsTotal(allJsTotal.toString());
			footRow.setZjTotal(allZjTotal.toString());
			footRow.setFjTotal(allFjTotal.toString());
			footRow.setSdTotal(allSdTotal.toString());
			footRow.setHenTotal(allHenTotal.toString());
			footRow.setHubTotal(allHubTotal.toString());
			footRow.setHunTotal(allHunTotal.toString());
			footRow.setGdTotal(allGdTotal.toString());
			footRow.setGxTotal(allGxTotal.toString());
			footRow.setHainTotal(allHainTotal.toString());
			footRow.setJxTotal(allJxTotal.toString());
			footRow.setSanxTotal(allSanxTotal.toString());
			footRow.setGzTotal(allGzTotal.toString());
			footRow.setYnTotal(allYnTotal.toString());
			footRow.setXzTotal(allXzTotal.toString());
			footRow.setSxTotal(allSxTotal.toString());
			footRow.setLnTotal(allLnTotal.toString());
			footRow.setJlTotal(allJlTotal.toString());
			footRow.setNmgTotal(allNmgTotal.toString());
			footRow.setGsTotal(allGsTotal.toString());
			footRow.setQhTotal(allQhTotal.toString());
			footRow.setNxTotal(allNxTotal.toString());
			footRow.setXjTotal(allXjTotal.toString());
			footRow.setTwTotal(allTwTotal.toString());
			footRow.setXgTotal(allXgTotal.toString());
			footRow.setAmTotal(allAmTotal.toString());
			footRow.setHljTotal(allHljTotal.toString());

			List<InsuranceStatisDto> footList = new ArrayList<InsuranceStatisDto>();
			footList.add(footRow);

			dataList.addAll(0, footList);
		} catch (Exception e) {
			StringWriter sw = new StringWriter();
			PrintWriter pw = new PrintWriter(sw, true);
			e.printStackTrace(pw);
			String stackTraceString = sw.getBuffer().toString();
			logger.error("[赠险],数据统计异常,异常信息:{}", stackTraceString);
		}
		return dataList;
	}
}
