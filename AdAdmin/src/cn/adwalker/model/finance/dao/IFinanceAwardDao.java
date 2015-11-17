package cn.adwalker.model.finance.dao;

import java.util.List;

import cn.adwalker.model.finance.domain.DevFinanceAward;
import cn.adwalker.model.finance.domain.DevFinanceAwardVoLog;

public interface IFinanceAwardDao {

	public DevFinanceAward findByTime(String currentTime);

	public void insert(DevFinanceAwardVoLog awardVoLog);

	public DevFinanceAwardVoLog findByDevId(Long developerId);

	public List<DevFinanceAward> findByDate(String startTime,
			String endTime);

	public void insert(DevFinanceAward awardVo);

	public List<DevFinanceAward> findAll();

}
