package cn.adwalker.ad.admin.finance.service.impl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import cn.adwalker.ad.admin.common.vo.SysUserVo;
import cn.adwalker.ad.admin.finance.bean.ChannelCostQueryBean;
import cn.adwalker.ad.admin.finance.form.ChannelConfirmFrom;
import cn.adwalker.ad.admin.finance.service.IChannelCostQueryService;
import cn.adwalker.ad.admin.finance.vo.CampaignConfirmVo;
import cn.adwalker.ad.admin.finance.vo.ChannelConfirmVo;
import cn.adwalker.ad.admin.finance.vo.ChannelCostSumVo;
import cn.adwalker.ad.admin.finance.vo.ChannelCostVo;
import cn.adwalker.core.page.IPageInfo;
import cn.adwalker.core.util.lang.ObjectUtils;
import cn.adwalker.model.finance.domain.CostMonthChannel;
import cn.adwalker.model.finance.domain.IncomeMonthCampaign;
import cn.adwalker.model.operation.dao.IChannelConfirmDao;
import cn.adwalker.model.operation.dao.IIosAndroidDayChannelNumDao;
import cn.adwalker.model.operation.dao.IOperationDevIncomeAuditDao;

/**
 * <p>
 * Title: DevIncomeAuditServiceImpl
 * </p>
 * <p>
 * Description:运营确认收入serice实现类
 * </p>
 * <p>
 * Company: adwalker
 * </p>
 * 
 * @author lichuang
 * @date 2013-6-17
 */
@Service
public class ChannelCostQueryServiceImpl implements IChannelCostQueryService {

	@Resource
	private IOperationDevIncomeAuditDao devIncomeAuditDao;
	@Resource
	private IIosAndroidDayChannelNumDao iosAndroidDayChannelNumDao;
	
	@Resource
	private IChannelConfirmDao channelConfirmDao;

	/**
	 * 
	 * (non-Javadoc)
	 * <p>
	 * Title: findByCondition
	 * </p>
	 * <p>
	 * Description:TODO
	 * </p>
	 * 
	 * @param bean
	 * @param pageIndex
	 * @param pageRecord
	 * @return
	 * @throws Exception
	 * @see cn.adwalker.ad.admin.finance.service.IDevIncomeAuditService.finance.service.IDevIncomeEffectConfirmService#findByCondition(cn.adwalker.model.finance.DevIncomeAuditbean.DevIncomeEffectSearchbean,
	 *      int, int)
	 */
	@SuppressWarnings("unchecked")
	@Transactional(propagation = Propagation.NEVER)
	@Override
	public List<ChannelCostVo> findList(ChannelCostQueryBean bean,
			IPageInfo pageInfo) throws Exception {
		List<ChannelCostVo> list = null;
		List<Object> param = new ArrayList<Object>();
		StringBuffer sb = new StringBuffer(
				"t_ios_android_day_channel_num t LEFT JOIN t_channel c ON t.channel_id=c.id LEFT JOIN V_CAMPAIGN n ON t.CAMPAIGN_id=n.id  WHERE 1=1");
		if (bean != null) {
			// 活动
			if (StringUtils.isNotEmpty(bean.getCampaign())) {
				sb.append(" and (t.campaign_id=? or  upper(n.CAMPAIGN_NAME) like ? )");
				param.add(bean.getCampaign_id());
				param.add("%" + bean.getCampaign_name().trim().toUpperCase()
						+ "%");
			}

			// 渠道id
			if (StringUtils.isNotEmpty(bean.getChannel())) {
				sb.append(" and (t.channel_id=? or  upper(c.name) like ? )");
				param.add(bean.getChannel_id());
				param.add("%" + bean.getChannel_name().trim().toUpperCase()
						+ "%");
			}

			// 结算状态
			if (ObjectUtils.isNotEmpty(bean.getStatus())) {
				sb.append(" and t.status ='");
				sb.append(bean.getStatus());
				sb.append("'");
			}
			// 投放方式
			if (ObjectUtils.isNotEmpty(bean.getCharge_type())) {
				sb.append(" and t.charge_type ='");
				sb.append(bean.getCharge_type());
				sb.append("'");
			}
			// ren
			if (ObjectUtils.isNotEmpty(bean.getSales())) {
				sb.append(" and c.channe_manager like'%");
				sb.append(bean.getSales());
				sb.append("%'");
			}
			
			// os
			if (ObjectUtils.isNotEmpty(bean.getOs())) {
				sb.append(" and n.os ='");
				sb.append(bean.getOs());
				sb.append("'");
			}
			// 效果发生时间
			if (ObjectUtils.isNotEmpty(bean.getStatStartTime())) {
				sb.append(" and t.static_date>=? ");
				param.add(bean.getStatStartTime());
			}
			if (ObjectUtils.isNotEmpty(bean.getStatEndTime())) {
				sb.append(" and t.static_date<=? ");
				param.add(bean.getStatEndTime());
			}
			sb.append(" ");
		}
		list = (List<ChannelCostVo>) devIncomeAuditDao
				.findByPage(
						"t.*,c.name channel_name,c.channe_manager,n.CAMPAIGN_NAME,n.os",
						sb.toString(), param.toArray(), " order by t.id desc ",
						ChannelCostVo.class, pageInfo);
		return list;
	}

	@SuppressWarnings("unchecked")
	private List<ChannelCostSumVo> findSummary(ChannelCostQueryBean bean)
			throws Exception {

		StringBuffer sb = new StringBuffer();
		List<Object> param = new ArrayList<Object>();
		sb.append("select sum(t.confirm_amount* t.price) as sum_cost,sum(IFNULL(t.confirm_amount,0)) as sum_amount from t_ios_android_day_channel_num t LEFT JOIN t_channel c ON t.channel_id=c.id LEFT JOIN V_CAMPAIGN n ON t.CAMPAIGN_id=n.id  WHERE 1=1");
		if (bean != null) {
			// 活动
			if (StringUtils.isNotEmpty(bean.getCampaign())) {
				sb.append(" and (t.campaign_id=? or  upper(n.CAMPAIGN_NAME) like ? )");
				param.add(bean.getCampaign_id());
				param.add("%" + bean.getCampaign_name().trim().toUpperCase()
						+ "%");
			}

			// 渠道id
			if (StringUtils.isNotEmpty(bean.getChannel())) {
				sb.append(" and (t.channel_id=? or  upper(c.name) like ? )");
				param.add(bean.getChannel_id());
				param.add("%" + bean.getChannel_name().trim().toUpperCase()
						+ "%");
			}

			// 结算状态
			if (ObjectUtils.isNotEmpty(bean.getStatus())) {
				sb.append(" and t.status ='");
				sb.append(bean.getStatus());
				sb.append("'");
			}
			// 投放方式
			if (ObjectUtils.isNotEmpty(bean.getCharge_type())) {
				sb.append(" and t.charge_type ='");
				sb.append(bean.getCharge_type());
				sb.append("'");
			}
			// ren
			if (ObjectUtils.isNotEmpty(bean.getSales())) {
				sb.append(" and c.channe_manager like'%");
				sb.append(bean.getSales());
				sb.append("%'");
			}
			
			// os
			if (ObjectUtils.isNotEmpty(bean.getOs())) {
				sb.append(" and n.os ='");
				sb.append(bean.getOs());
				sb.append("'");
			}
			// 效果发生时间
			if (ObjectUtils.isNotEmpty(bean.getStatStartTime())) {
				sb.append(" and t.static_date>=? ");
				param.add(bean.getStatStartTime());
			}
			if (ObjectUtils.isNotEmpty(bean.getStatEndTime())) {
				sb.append(" and t.static_date<=? ");
				param.add(bean.getStatEndTime());
			}
			sb.append(" ");
		}
		return (List<ChannelCostSumVo>) this.devIncomeAuditDao.findAll(
				sb.toString(), param.toArray(), ChannelCostSumVo.class);
	}

	/**
	 * (non-Javadoc)
	 * <p>
	 * Title: findSummaryByCondition
	 * </p>
	 * <p>
	 * Description:求和
	 * </p>
	 * 
	 * @param bean
	 * @return
	 * @throws Exception
	 * @see cn.adwalker.ad.admin.operation.service.IOperationDevIncomeAuditService#findSummaryByCondition(cn.adwalker.admin.operation.bean.DevIncomeAuditbean)
	 */
	@Transactional(propagation = Propagation.NEVER)
	@Override
	public ChannelCostSumVo findSumByCondition(ChannelCostQueryBean bean)
			throws Exception {
		ChannelCostSumVo vo = null;
		List<ChannelCostSumVo> dDB = this.findSummary(bean);
		if (dDB != null && dDB.size() > 0) {
			vo = dDB.get(0);
		}
		return vo;
	}

	/**
	 * (non-Javadoc)
	 * <p>
	 * Title: findAll
	 * </p>
	 * <p>
	 * Description:TODO
	 * </p>
	 * 
	 * @param bean
	 * @return
	 * @throws Exception
	 * @see cn.adwalker.ad.admin.finance.service.IDevIncomeAuditService.finance.service.IDevIncomeEffectConfirmService#findAll(cn.adwalker.model.finance.DevIncomeAuditbean.DevIncomeEffectSearchbean)
	 */
	@SuppressWarnings("unchecked")
	@Transactional(propagation = Propagation.NEVER)
	@Override
	public List<ChannelCostVo> findAll(ChannelCostQueryBean bean)
			throws Exception {
		List<ChannelCostVo> list = null;
		List<Object> param = new ArrayList<Object>();
		StringBuffer sb = new StringBuffer(
				"V_FINACE_DEV_INCOM_CONFIRM t where t.dev_status =? and status!=0");
		param.add(1);// 开发者账号状态
		if (bean != null) {
			// 活动
			if (StringUtils.isNotEmpty(bean.getCampaign())) {
				sb.append(" and (t.campaign_id=? or  upper(t.campaign_name) like ? )");
				param.add(bean.getCampaign_id());
				param.add("%" + bean.getCampaign_name().trim().toUpperCase()
						+ "%");
			}
			// 开发者id
			if (StringUtils.isNotEmpty(bean.getChannel())) {
				sb.append(" and (t.channel_id=? or  upper(channel.name) like ? )");
				param.add(bean.getChannel_id());
				param.add("%" + bean.getChannel_name().trim().toUpperCase()
						+ "%");
			}

			// 结算状态
			if (ObjectUtils.isNotEmpty(bean.getStatus())) {
				sb.append(" and t.status ='");
				sb.append(bean.getStatus());
				sb.append("'");
			}
			// 开发者佣金
			if (ObjectUtils.isNotEmpty(bean.getDev_confirmMoney())) {
				sb.append(" and t.confirmMoney <='");
				sb.append(bean.getDev_confirmMoney());
				sb.append("'");
			}
			// 效果发生时间
			if (ObjectUtils.isNotEmpty(bean.getStatStartTime())) {
				sb.append(" and date_format(t.EFFECT_TIME,'%Y-%m-%d') >='"
						+ bean.getStatStartTime() + "'");
			}
			if (ObjectUtils.isNotEmpty(bean.getStatEndTime())) {
				sb.append(" and date_format(t.EFFECT_TIME,'%Y-%m-%d') <='"
						+ bean.getStatEndTime() + "'");
			}
			sb.append(" ");
		}
		list = (List<ChannelCostVo>) devIncomeAuditDao.findAll("*",
				sb.toString(), param.toArray(),
				" order by t.CREATE_TIME desc ", ChannelCostVo.class);
		return list;
	}
	/**
	 * (non-Javadoc)
	* <p>Title: submitIncomeNum</p>
	* <p>Description:TODO</p>
	* @param ids
	* @param manageUser
	* @return
	 * @throws Exception 
	* @see cn.adwalker.ad.admin.finance.service.IChannelCostQueryService#submitIncomeNum(java.lang.String, cn.adwalker.ad.admin.common.vo.SysUserVo)
	 */
	@Override
	public Long submitIncomeNum(String ids, SysUserVo manageUser)
			throws Exception {

		Long income_id=0l;
		String arr[]=ids.split(",");
		String ars=null;
		if (arr != null && arr.length > 0) {
			StringBuilder sb = new StringBuilder();
			for (String d : arr) {
				sb.append(d).append(",");
			}
			sb.deleteCharAt(sb.length() - 1);
			ars = sb.toString();
		} else {
			ars = null;
		}
		CostMonthChannel plist =new CostMonthChannel();
				plist=iosAndroidDayChannelNumDao.getById(ars);
		if(plist!=null){
			plist.setCreate_time(new Date());
			plist.setIncome_money(plist.getForecast_money());
			plist.setStatus(1);
			plist.setInvoice_status(0);
			plist.setPayment_status(0);
			plist.setManager_id(manageUser.getId());
			
				income_id=channelConfirmDao.insert(plist);
		}
		iosAndroidDayChannelNumDao.updateById(ars,income_id);
		
		return income_id;
	}
	/**
	 * (non-Javadoc)
	* <p>Title: getById</p>
	* <p>Description:TODO</p>
	* @param income_id
	* @return
	* @throws Exception
	* @see cn.adwalker.ad.admin.finance.service.IChannelCostQueryService#getById(java.lang.Long)
	 */
	@Override
	public ChannelConfirmVo getById(Long income_id) throws Exception {
		ChannelConfirmVo plist =new ChannelConfirmVo();
		plist=channelConfirmDao.getgetById(income_id);
		return plist;
	}
	/**
	 * (non-Javadoc)
	* <p>Title: updateStatus</p>
	* <p>Description:TODO</p>
	* @param from
	* @throws Exception
	* @see cn.adwalker.ad.admin.finance.service.IChannelCostQueryService#updateStatus(cn.adwalker.ad.admin.finance.form.ChannelConfirmFrom)
	 */
	@Override
	public void updateStatus(ChannelConfirmFrom from) throws Exception {
		CostMonthChannel plist =new CostMonthChannel();
		if(from!=null){
			plist.setId(from.getId());
			plist.setIncome_money(from.getIncome_money());
			plist.setIncome_remark(from.getIncome_remark());
			channelConfirmDao.updateStatus(plist);
			
		}		
	}
	/**
	 * (non-Javadoc)
	* <p>Title: deleteStatus</p>
	* <p>Description:TODO</p>
	* @param id
	* @throws Exception
	* @see cn.adwalker.ad.admin.finance.service.IChannelCostQueryService#deleteStatus(java.lang.Long)
	 */
	@Override
	public void deleteStatus(Long id) throws Exception {
		channelConfirmDao.deleteStatus(id);
		iosAndroidDayChannelNumDao.updateStatus(id, 1);
	}
}
