package cn.adwalker.ad.admin.common.service;

import java.util.List;

import cn.adwalker.model.finance.domain.DevFinanceAward;
import cn.adwalker.model.finance.domain.DevFinanceAwardVoLog;


public interface IManageFinanceAwardService {
	
	
	public DevFinanceAward findByTime(String currentTime) throws Exception;
	public void insert(DevFinanceAwardVoLog awardVoLog);
	
	public List<DevFinanceAward> findByDate(String startTime,
			String endTime);
	public List<DevFinanceAward> findAll();
}
