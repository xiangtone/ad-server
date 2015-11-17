package cn.adwalker.ad.admin.app.service.impl;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import cn.adwalker.core.page.IPageInfo;
import cn.adwalker.model.app.dao.IMarketActivityDao;
import cn.adwalker.model.app.domain.MarketActivity;
import cn.adwalker.ad.admin.app.bean.MarketAvtivityBean;
import cn.adwalker.ad.admin.app.form.MarketAvtivityForm;
import cn.adwalker.ad.admin.app.service.IMarketActivityService;

/**
 * <p>
 * Title: MarketActivityServiceImpl
 * </p>
 * <p>
 * Description:市场活动service实现类
 * </p>
 * <p>
 * Company: adwalker
 * </p>
 * 
 * @author lichuang
 * @date 2013-6-18
 */
@Service("marketActivitySettingService")
public class MarketActivityServiceImpl implements IMarketActivityService {
	@Resource
	private IMarketActivityDao marketActivityDao;

	/**
	 * (non-Javadoc)
	 * <p>
	 * Title: findByPage
	 * </p>
	 * <p>
	 * Description:TODO
	 * </p>
	 * 
	 * @param bean
	 * @param pageInfor
	 * @return
	 * @throws Exception
	 * @see cn.adwalker.ad.admin.app.service.IMarketActivityService#findByPage(cn.adwalker.ad.admin.app.bean.MarketAvtivityBean,
	 *      cn.adwalker.core.page.IPageInfo)
	 */
	@SuppressWarnings("unchecked")
	@Transactional(propagation = Propagation.NEVER)
	@Override
	public List<MarketActivity> findByPage(MarketAvtivityBean bean,
			IPageInfo pageInfor) throws Exception {
		List<MarketActivity> list = null;
		StringBuffer sb = new StringBuffer();
		sb.append("T_MEDIA_ACTIVITY where 1=1");
		if (bean != null) {
			if (!StringUtils.isEmpty(bean.getName())) {
				sb.append(" and upper(name) like '%"
						+ bean.getName().trim().toUpperCase() + "%'");
			}

			if (bean.getBudget() != null) {
				sb.append(" and budget=" + bean.getBudget());
			}
			if (bean.getAcitvity_time() != null) {
				sb.append(" and upper(acitvity_time) like'%");
				sb.append(bean.getAcitvity_time().toUpperCase());
				sb.append("%'");
			}
		}
		list = (List<MarketActivity>) marketActivityDao.findByPage(
				" id,name,budget,content,create_time,acitvity_time ",
				sb.toString(), "order by create_time desc ",
				MarketActivity.class, pageInfor);
		return list;
	}

	/**
	 * (non-Javadoc)
	 * <p>
	 * Title: findById
	 * </p>
	 * <p>
	 * Description:TODO
	 * </p>
	 * 
	 * @param id
	 * @return
	 * @see cn.adwalker.ad.admin.app.service.IMarketActivityService#findById(java.lang.Long)
	 */
	@Transactional(propagation = Propagation.NEVER)
	@Override
	public MarketActivity findById(Long id) {
		return marketActivityDao.findMarketActivityById(id);
	}

	/**
	 * (non-Javadoc)
	 * <p>
	 * Title: updatadwalkerketActivity
	 * </p>
	 * <p>
	 * Description:TODO
	 * </p>
	 * 
	 * @param form
	 * @see cn.adwalker.ad.admin.app.service.IMarketActivityService#updatadwalkerketActivity(cn.adwalker.ad.admin.app.form.MarketAvtivityForm)
	 */
	@Override
	public void updatadwalkerketActivity(MarketAvtivityForm form) {
		marketActivityDao.updatadwalkerketActivity(form);
	}

	/**
	 * (non-Javadoc)
	 * <p>
	 * Title: addMarketActivity
	 * </p>
	 * <p>
	 * Description:TODO
	 * </p>
	 * 
	 * @param form
	 * @see cn.adwalker.ad.admin.app.service.IMarketActivityService#addMarketActivity(cn.adwalker.ad.admin.app.form.MarketAvtivityForm)
	 */
	@Override
	public void addMarketActivity(MarketAvtivityForm form) {
		form.setCreate_time(new Date());
		marketActivityDao.addMarketActivity(form);
	}

}
