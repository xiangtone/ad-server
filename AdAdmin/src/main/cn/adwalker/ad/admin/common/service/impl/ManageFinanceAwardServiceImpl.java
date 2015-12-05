package cn.adwalker.ad.admin.common.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.adwalker.model.finance.dao.IFinanceAwardDao;
import cn.adwalker.model.finance.domain.DevFinanceAward;
import cn.adwalker.model.finance.domain.DevFinanceAwardVoLog;
import cn.adwalker.ad.admin.common.service.IManageFinanceAwardService;
@Service(value="manageFinanceAwardService")
@Transactional
public class ManageFinanceAwardServiceImpl implements  IManageFinanceAwardService {
	@Resource
	private IFinanceAwardDao iFinanceAwardDao;

	public IFinanceAwardDao getiFinanceAwardDao() {
		return iFinanceAwardDao;
	}

	public void setiFinanceAwardDao(IFinanceAwardDao iFinanceAwardDao) {
		this.iFinanceAwardDao = iFinanceAwardDao;
	}

	@Override
	public DevFinanceAward findByTime(String currentTime) throws Exception {
		DevFinanceAward devFinanceAward = new DevFinanceAward();
		devFinanceAward = this.iFinanceAwardDao.findByTime(currentTime);
		return devFinanceAward;
	}

	@Override
	public void insert(DevFinanceAwardVoLog awardVoLog) {
		// TODO Auto-generated method stub
		this.iFinanceAwardDao.insert(awardVoLog);
		
	}

	@Override
	public List<DevFinanceAward> findByDate(String startTime,
			String endTime) {
		return this.iFinanceAwardDao.findByDate(startTime,endTime);
	}
	@Override
	public List<DevFinanceAward> findAll() {
		List<DevFinanceAward> list = new ArrayList<DevFinanceAward>();
		list = this.iFinanceAwardDao.findAll();
		return list;
	}

}
