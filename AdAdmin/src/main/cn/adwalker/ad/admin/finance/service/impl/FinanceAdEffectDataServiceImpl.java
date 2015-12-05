/**
 * 
 */
package cn.adwalker.ad.admin.finance.service.impl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import javax.annotation.Resource;

import jxl.Sheet;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.adwalker.model.operation.dao.IAdEffectDataDao;
import cn.adwalker.model.operation.domain.FinanceAdEffectData;
import cn.adwalker.ad.admin.finance.service.IFinanceAdEffectDataService;

/**
 * @author wjp
 * 
 */
@Service(value = "financeAdEffectDataService")
@Transactional
public class FinanceAdEffectDataServiceImpl implements
		IFinanceAdEffectDataService {

	/** 日志记录器 */
	Logger logger = Logger.getLogger(FinanceAdEffectDataServiceImpl.class);

	/** 广告主广告效果录入 */
	@Resource
	private IAdEffectDataDao adEffectDataDao;


	@Override
	public int insert(FinanceAdEffectData financeAdEffectData) throws Exception {
		return this.adEffectDataDao.insert(financeAdEffectData);
	}

	@Override
	public int update(FinanceAdEffectData financeAdEffectData) throws Exception {
		return this.adEffectDataDao.update(financeAdEffectData);
	}

	
	@Override
	public HashMap<String, Object> checkDataWapActivate(Sheet sheet)
			throws Exception {

		HashMap<String, Object> resMap = new HashMap<String, Object>();

		// 封装返回值
		List<FinanceAdEffectData> list = new ArrayList<FinanceAdEffectData>();
		String msg = "";
		for (int j = 1; j < sheet.getRows(); j++) {
			try {
				String adId = sheet.getCell(0, j).getContents().trim();// 第一行
				String validAmount = sheet.getCell(1, j).getContents().trim();// 第二行
				String statDate = sheet.getCell(2, j).getContents().trim();// 第三行
				if ("".equals(adId) && "".equals(validAmount)
						&& "".equals(statDate)) {
					continue;
				}

				try {
					Integer.parseInt(validAmount);
				} catch (Exception e) {
					msg = "激活数格式不对！";
					break;
				}

				if ("".equals(adId)) {// 广告id为空
					msg = "广告ID不能为空！";
					break;
				}
				if ("".equals(validAmount)) {// 激活数不能空
					msg = "激活数不能为空！";
					break;
				}
				if ("".equals(statDate) || statDate.length() != 10) {// 日期不能空
					msg = "日期格式不正确！";
					break;
				}
				SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
				Date today = format.parse(format.format(new Date()));// 当前日期
				Date date;
				try {
					date = format.parse(statDate);
				} catch (Exception e) {
					msg = "日期格式不正确！";
					break;
				}
				if (date.after(today)) {
					msg = "日期不能是未来日期！";
					break;
				}
				if (date.equals(today)) {
					msg = "日期不能是今天！";
					break;
				}
				FinanceAdEffectData bean = new FinanceAdEffectData();
				bean.setAdId(Long.valueOf(sheet.getCell(0, j).getContents()
						.trim()));// 广告id
				bean.setValidAmount(Integer.parseInt(sheet.getCell(1, j)
						.getContents().trim()));// 有效激活数
				bean.setStatDate(sheet.getCell(2, j).getContents().trim());// 日期
				list.add(bean);

			} catch (Exception e) {
				e.printStackTrace();
				msg = "数据异常！";
				break;
			}
		}

		if (!msg.equals("")) {
			resMap.put("error", msg);
		} else {
			resMap.put("list", list);
		}

		return resMap;
	}
}
