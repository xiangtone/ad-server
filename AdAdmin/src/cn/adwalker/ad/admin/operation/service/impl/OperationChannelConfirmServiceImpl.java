package cn.adwalker.ad.admin.operation.service.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import cn.adwalker.ad.admin.common.vo.SysUserVo;
import cn.adwalker.ad.admin.operation.bean.CahannelConfirmbean;
import cn.adwalker.ad.admin.operation.bean.CampaignConfirmbean;
import cn.adwalker.ad.admin.operation.form.CampaignConfirmEditForm;
import cn.adwalker.ad.admin.operation.form.CampaignConfirmForm;
import cn.adwalker.ad.admin.operation.service.IOperationChannelConfirmService;
import cn.adwalker.ad.admin.operation.vo.CampaignConfirmVo;
import cn.adwalker.ad.admin.operation.vo.CampaignSum;
import cn.adwalker.ad.admin.operation.vo.ChannelCampaignConfirmSumVo;
import cn.adwalker.ad.admin.operation.vo.ChannelConfirmVo;
import cn.adwalker.ad.admin.operation.vo.CostChannelTmpSumVo;
import cn.adwalker.ad.admin.operation.vo.CostChannelTmpVo;
import cn.adwalker.ad.admin.operation.vo.CostPlatFormVo;
import cn.adwalker.core.bean.SimpleBean;
import cn.adwalker.core.page.IPageInfo;
import cn.adwalker.core.util.lang.MathUtil;
import cn.adwalker.core.util.lang.ObjectUtils;
import cn.adwalker.model.ad.dao.IAreaDao;
import cn.adwalker.model.ad.dao.ICampaignDao;
import cn.adwalker.model.ad.domain.Area;
import cn.adwalker.model.finance.dao.IBalanceCampainDao;
import cn.adwalker.model.finance.dao.IBalanceChannelRelDao;
import cn.adwalker.model.finance.dao.IBalanceIncomeRelDao;
import cn.adwalker.model.finance.dao.IBalancePlatformRelDao;
import cn.adwalker.model.finance.dao.ICostChannelDao;
import cn.adwalker.model.finance.dao.ICostPlatFormDao;
import cn.adwalker.model.finance.domain.BalanceChannelRel;
import cn.adwalker.model.finance.domain.BalancePlatformRel;
import cn.adwalker.model.finance.domain.CostChannel;
import cn.adwalker.model.finance.domain.CostPlatForm;
import cn.adwalker.model.operation.dao.ICampaignConfirmLogDao;
import cn.adwalker.model.operation.dao.IChannelConfirmDao;
import cn.adwalker.model.operation.dao.IIosAndroidDayChannelNumDao;
import cn.adwalker.model.operation.domain.CampaignConfirm;
import cn.adwalker.model.operation.domain.CampaignConfirmLog;
import cn.adwalker.model.operation.domain.ChannelCampaignConfirmSum;

/**
 * <p>
 * Title: OperationCampaignConfirmServiceImpl
 * </p>
 * <p>
 * Description:活动确认数service实现类
 * </p>
 * <p>
 * Company: emar
 * </p>
 * 
 * @author lichuang
 * @date 2013-6-8
 */
@Service("channelConfirmService")
public class OperationChannelConfirmServiceImpl implements
	IOperationChannelConfirmService {
	
	@Resource
	private IAreaDao areaDao;
	
	@Resource
	private IChannelConfirmDao channelConfirmDao;

	@Resource
	private IBalanceCampainDao balanceCampainDao;

	@Resource
	private ICampaignConfirmLogDao campaignConfirmLogDao;
	@Resource
	private ICampaignDao campaignDao;
	@Resource
	private ICostPlatFormDao costPlatFormDao;

	@Resource
	private ICostChannelDao costChannelDao;

	@Resource
	private IBalanceIncomeRelDao balanceIncomeRelDao;

	@Resource
	private IBalancePlatformRelDao balancePlatformRelDao;

	@Resource
	private IBalanceChannelRelDao balanceChannelRelDao;
		
	@Resource
	private  IIosAndroidDayChannelNumDao iosAndroidDayChannelNumDao;

	/**
	 * (non-Javadoc)
	 * <p>
	 * Title: findCampaignList
	 * </p>
	 * <p>
	 * Description:TODO
	 * </p>
	 * 
	 * @param bean
	 * @param pageInfo
	 * @return
	 * @throws Exception
	 * @see cn.adwalker.ad.admin.operation.service.emar.escore.platform.operation.service.IOperationCampaignConfirmService#findCampaignList(cn.cn.adwalker.ad.admin.operation.bean.emar.escore.platform.operation.bean.CampaignConfirmbean,
	 *      com.emar.escore.core.page.IPageInfo)
	 */
	@SuppressWarnings("unchecked")
	@Transactional(propagation = Propagation.NEVER)
	@Override
	public List<ChannelConfirmVo> findByPage(CahannelConfirmbean bean,
			IPageInfo pageInfo) throws Exception {
		List<Object> list = new ArrayList<Object>();
		StringBuilder sb = new StringBuilder(
				" T_CHANNEL_CONFIRM a LEFT JOIN  V_CAMPAIGN t  ON a.campaign_id=t.ID LEFT JOIN t_channel s ON a.`channel_id` = s.id WHERE a.STATUS>=1");
		if (bean != null) {
			if (ObjectUtils.isNotEmpty(bean.getMonth_stat_date())) {
				sb.append(" and a.month_stat_date>= '");
				sb.append(bean.getMonth_stat_date());
				sb.append("'");
			}
			if (ObjectUtils.isNotEmpty(bean.getMonth_end_date())) {
				sb.append(" and a.month_end_date<= '");
				sb.append(bean.getMonth_end_date());
				sb.append("'");
			}
			if (ObjectUtils.isNotEmpty(bean.getStatus())) {
				sb.append(" and a.STATUS =");
				sb.append(bean.getStatus());
			}
			if (ObjectUtils.isNotEmpty(bean.getOs())) {
				sb.append(" and t.os ='");
				sb.append(bean.getOs());
				sb.append("'");
			}
			
			if (ObjectUtils.isNotEmpty(bean.getCampaign_name())) {
				sb.append(" and (upper(t.CAMPAIGN_NAME)  like ? or a.campaign_id =? )");
				list.add("%" + bean.getCampaign_name().toUpperCase() + "%");
				list.add(bean.getCampaign_id());
			}
			if (ObjectUtils.isNotEmpty(bean.getChannel())) {
				sb.append(" and (upper(s.NAME)  like ? or a.channel_id =? )");
				list.add("%" + bean.getChannel_name().toUpperCase() + "%");
				list.add(bean.getChannel_id());
			}

		}
		return (List<ChannelConfirmVo>) channelConfirmDao
				.findByPage(
						"a.*,t.campaign_name, t.charge_type,t.os,s.`channe_manager`,s.name channel_name ",
						sb.toString(), list.toArray(),
						" order by a.id desc ",
						ChannelConfirmVo.class, pageInfo);
	}


	@SuppressWarnings("unchecked")
	private CampaignSum findConfirmSum(CampaignConfirmForm confirmFrom)
			throws Exception {
		StringBuffer sb = new StringBuffer();
		sb.append("select sum(a.confirm_num) as sum_forecast_amount,sum(a.in_price*a.confirm_num) as sum_forecast_money from T_PACKAGE_ACTIVATE_DETAIL a left join V_PLACEMENT_PACKAGE t on a.package_id = t.ID "
				+ "where t.CAMPAIGN_ID = ? and a.static_date>= ? and a.static_date<= ? ");
		List<CampaignSum> objects = (List<CampaignSum>) channelConfirmDao
				.findAll(
						sb.toString(),
						new Object[] { confirmFrom.getCampaign_id(),
								confirmFrom.getMonth_stat_date(),
								confirmFrom.getMonth_end_date() },
						CampaignSum.class);
		CampaignSum com = null;
		if (objects != null && objects.size() > 0) {
			com = (CampaignSum) objects.get(0);
			return com;
		}
		return null;
	}

	
	@SuppressWarnings("unchecked")
	private CampaignSum findIosSum(CampaignConfirmForm confirmFrom)
			throws Exception {
		StringBuffer sb = new StringBuffer();
		sb.append("select sum(a.confirm_amount) as sum_forecast_amount,sum(sys_cost) as sum_forecast_money FROM t_ios_activate_num_detail a LEFT JOIN V_ios_activate p ON a.parent_id=p.id"
				+ " where 1=1 AND p.status = 9 AND a.status = 9 and p.CAMPAIGN_ID = ? and a.static_date>= ? and a.static_date<= ? ");
		List<CampaignSum> objects = (List<CampaignSum>) channelConfirmDao
				.findAll(
						sb.toString(),
						new Object[] { confirmFrom.getCampaign_id(),
								confirmFrom.getMonth_stat_date(),
								confirmFrom.getMonth_end_date() },
						CampaignSum.class);
		CampaignSum com = null;
		if (objects != null && objects.size() > 0) {
			com = (CampaignSum) objects.get(0);
			return com;
		}
		return null;
	}
	
	/**
	 * <p>
	 * Title: updateConfirm
	 * </p>
	 * <p>
	 * Description:TODO
	 * </p>
	 * 
	 * @param id
	 * @param number
	 * @throws Exception
	 * @see cn.adwalker.ad.admin.operation.service.emar.escore.platform.operation.service.IOperationCampaignConfirmService#updateConfirm(java.lang.Long,
	 *      java.lang.Integer)
	 */
	@Override
	@Transactional(rollbackFor = { Exception.class })
	public void updateConfirm(CampaignConfirmEditForm form, SysUserVo manageUser)
			throws Exception {
		 CampaignConfirmForm conForm= new CampaignConfirmForm();
		 if(form!=null){
			 conForm.setMonth_stat_date(form.getMonth_stat_date());
			 conForm.setMonth_end_date(form.getMonth_end_date());
			 conForm.setCampaign_id(form.getCampaign_id());
			 conForm.setOs(form.getOs());
		 }
		CampaignConfirm entity = new CampaignConfirm();
		CampaignConfirmLog log = new CampaignConfirmLog();
		entity = channelConfirmDao.getCampaignConfirm(form.getId());
		if (entity != null) {
			CampaignSum cs =new CampaignSum();
			if(ObjectUtils.isNotEmpty(conForm.getOs())){
				// 查找出每天广告主预确认回来的数和钱
				if(conForm.getOs()=="android"){
					cs = this.findConfirmSum(conForm);
				}else {
					cs = this.findIosSum(conForm);
				}
			}
			entity.setForecast_amount(cs.getSum_forecast_amount());
			entity.setForecast_money(cs.getSum_forecast_money());
			entity.setId(form.getId());
			entity.setCampaign_id(form.getCampaign_id());
			entity.setIncome_amount(form.getIncome_amount());
			entity.setMonth_stat_date(form.getMonth_stat_date());
			entity.setMonth_end_date(form.getMonth_end_date());
			entity.setPrice(form.getPrice());
			entity.setOperater_id(manageUser.getId());
			entity.setUpdate_time(new Date());
			if (ObjectUtils.isNotEmpty(form.getPrice())
					&& ObjectUtils.isNotEmpty(form.getIncome_amount())) {
				entity.setIncome_money(MathUtil.multiply(form.getPrice(),
						form.getIncome_amount()));
			}
			entity.setBalance_model(form.getBalance_model());
			channelConfirmDao.update(entity);

			log.setCampaign_id(entity.getCampaign_id());
			log.setIncome_amount(entity.getIncome_amount());
			log.setPrice(entity.getPrice());
			log.setMonth_stat_date(entity.getMonth_stat_date());
			log.setMonth_end_date(entity.getMonth_end_date());
			log.setCampaign_confirm_id(entity.getId());
			log.setOperater_id(entity.getOperater_id());
			log.setUpdate_time(entity.getUpdate_time());
			campaignConfirmLogDao.insert(log);

		}

	}

	/**
	 * 
	 * <p>
	 * Title: public_platform
	 * </p>
	 * <p>
	 * Description:渠道成本
	 * </p>
	 * 
	 * @param id
	 * @throws Exception
	 * @author cuidd
	 * @date 2013-10-31
	 * @return void
	 * @version 1.0
	 */
	@SuppressWarnings("unchecked")
	@Transactional(rollbackFor = { Exception.class })
	private CostChannelTmpSumVo public_platform(CampaignConfirm entity)
			throws Exception {
		CostChannelTmpSumVo sumvo = null;
		// 1、查询录入的收入信息
		// 2、生成响应的平台成本统计
		// 3、维护对账数据与收入的关系
		if (entity != null) {
			// 根据时间区间抓取数据存入中间表
			List<CostPlatFormVo> list = (List<CostPlatFormVo>) channelConfirmDao
					.findAll(
							"SELECT app_id,v.type_id,sum(DEV_COST) cost,sum(effect_num) effect_num  "
									+ "from  T_FINACE_DEV_INCOM_CONFIRM confirm left join V_AD_CAMPAIGN v  "
									+ "on confirm.ad_id=v.id "
									+ "where confirm.status=2 and v.CAMPAIGN_id=? and date_format(confirm.EFFECT_TIME, '%Y-%m-%d')>= ? and date_format(confirm.EFFECT_TIME, '%Y-%m-%d')<=? group by app_id,v.type_id",
							new Object[] { entity.getCampaign_id(),
									entity.getMonth_stat_date(),
									entity.getMonth_end_date() },
							CostPlatFormVo.class);
			if (list != null && list.size() > 0) {
				for (CostPlatFormVo vo : list) {
					CostPlatForm e = new CostPlatForm();
					e.setAmount(vo.getEffect_num());
					e.setApp_id(vo.getApp_id());
					e.setCost(vo.getCost());
					e.setCreate_time(new Date());
					e.setBalance_id(entity.getId());
					e.setType_id(vo.getType_id());
					e.setStatus(0);
					e.setCampaign_id(entity.getCampaign_id());
					costPlatFormDao.insert(e);
				}
			}

			sumvo = getPlatformCost(entity.getId());
			balancePlatformRel(entity);

			// 更新一下总金额
			// PLATFORM_COST_MONEY
			channelConfirmDao
					.update("update T_CAMPAIGN_CONFIRM set  PLATFORM_COST_MONEY=? where id=?",
							new Object[] { sumvo.getCost(), entity.getId() });

		}
		return sumvo;
	}

	@SuppressWarnings("unchecked")
	private void balancePlatformRel(CampaignConfirm entity) throws Exception {
		List<SimpleBean<Long>> list = (List<SimpleBean<Long>>) balancePlatformRelDao
				.findAll(
						"SELECT confirm.id obj "
								+ "from  T_FINACE_DEV_INCOM_CONFIRM confirm left join V_AD_CAMPAIGN v  "
								+ "on confirm.ad_id=v.id "
								+ "where confirm.status=2 and v.CAMPAIGN_id=? and date_format(confirm.EFFECT_TIME, '%Y-%m-%d')>= ? and date_format(confirm.EFFECT_TIME, '%Y-%m-%d')<=? ",
						new Object[] { entity.getCampaign_id(),
								entity.getMonth_stat_date(),
								entity.getMonth_end_date() }, SimpleBean.class);
		if (list != null && list.size() > 0) {
			BalancePlatformRel rel = null;

			List<BalancePlatformRel> entityList = new ArrayList<BalancePlatformRel>();
			int i = 0;
			for (SimpleBean<Long> bean : list) {
				i++;
				System.out.println("total:" + list.size() + "--" + i);
				rel = new BalancePlatformRel();
				rel.setBalance_id(entity.getId());
				rel.setCreate_time(new Date());
				rel.setDetail_id(bean.getObj().longValue());
				entityList.add(rel);

			}
			balancePlatformRelDao.batchInsert(entityList);

		}
	}

	/**
	 * 
	 * <p>
	 * Title: public_channel
	 * </p>
	 * <p>
	 * Description:渠道
	 * </p>
	 * 
	 * @param id
	 * @throws Exception
	 * @author cuidd
	 * @date 2013-10-31
	 * @return void
	 * @version 1.0
	 * @param media_id
	 */
	@SuppressWarnings("unchecked")
	private CostChannelTmpSumVo public_channel(CampaignConfirm entity,
			Long media_id) throws Exception {
		CostChannelTmpSumVo vo = null;
		// 汇总渠道结算数据
		List<CostChannelTmpVo> list = (List<CostChannelTmpVo>) costChannelDao
				.findAll(
						"SELECT channel_id,type_id,sum(ADV_CONFRIM_NUM) ADV_CONFRIM_NUM,sum(AMOUNT) AMOUNT,sum(cost)cost "
								+ "from  T_FINANCE_BALANCE_COST_CHANNEL where static_date>=? and static_date<=? and campaign_id=? and channel_id=?  GROUP  BY  channel_id,type_id",
						new Object[] { entity.getMonth_stat_date(),
								entity.getMonth_end_date(),
								entity.getCampaign_id(), media_id },
						CostChannelTmpVo.class);
		if (list != null && list.size() > 0) {
			for (CostChannelTmpVo tmp : list) {
				CostChannel e = new CostChannel();
				e.setAdv_confrim_num(tmp.getAdv_confrim_num());
				e.setAmount(tmp.getAmount());
				e.setCost(tmp.getCost());
				e.setType_id(tmp.getType_id());
				e.setBalance_id(entity.getId());
				e.setCampaign_id(entity.getCampaign_id());
				e.setChannel_id(tmp.getChannel_id());
				costChannelDao.insert(e);

			}
			balanceChannelRel(entity, media_id);
			vo = getChannelCost(entity.getId(), media_id);
			costChannelDao
					.update("update T_CAMPAIGN_CONFIRM set CHANNEL_AMOUNT=? ,CHANNEL_COST_MONEY=? where id=?",
							new Object[] { vo.getAmount(), vo.getCost(),
									entity.getId() });

		}
		return vo;

		// 维护关联关系

		// 计算总钱

	}

	@SuppressWarnings("unchecked")
	private void balanceChannelRel(CampaignConfirm entity, Long media_id)
			throws Exception {

		List<SimpleBean<Long>> list = (List<SimpleBean<Long>>) balanceChannelRelDao
				.findAll(
						"SELECT id obj "
								+ "from  T_FINANCE_BALANCE_COST_CHANNEL where static_date>=? and static_date<=? and campaign_id=? and channel_id=?",
						new Object[] { entity.getMonth_stat_date(),
								entity.getMonth_end_date(),
								entity.getCampaign_id(), media_id },
						SimpleBean.class);

		if (list != null && list.size() > 0) {
			BalanceChannelRel rel = null;
			for (SimpleBean<Long> bean : list) {
				rel = new BalanceChannelRel();
				rel.setBalance_id(entity.getId());
				rel.setCreate_time(new Date());
				rel.setDetail_id(bean.getObj());
				try {
					balanceChannelRelDao.insert(rel);
				} catch (Exception e) {
					System.out.println(rel.getDetail_id()+"--"+e.toString());
					throw new Exception();
				}
				

			}

		}

	}

	@SuppressWarnings("unchecked")
	private CostChannelTmpSumVo getChannelCost(Long balance_id, Long channel_id)
			throws Exception {
		CostChannelTmpSumVo vo = null;
		List<CostChannelTmpSumVo> list = (List<CostChannelTmpSumVo>) costChannelDao
				.findAll(
						"select sum(cost)cost ,sum(amount)amount from T_FINANCE_COST_CHANNEL where balance_id=? and CHANNEL_ID=?",
						new Object[] { balance_id, channel_id },
						CostChannelTmpSumVo.class);
		if (list != null && list.size() > 0) {
			vo = list.get(0);
		}
		return vo;
	}

	@SuppressWarnings("unchecked")
	private CostChannelTmpSumVo getPlatformCost(Long id) throws Exception {
		CostChannelTmpSumVo vo = null;
		List<CostChannelTmpSumVo> list = (List<CostChannelTmpSumVo>) costChannelDao
				.findAll(
						"select sum(cost) cost,sum(amount) amount from  T_FINANCE_COST_PLATFORM  where balance_id=?",
						new Object[] { id }, CostChannelTmpSumVo.class);
		if (list != null && list.size() > 0) {
			vo = list.get(0);
		}
		return vo;
	}
	/**
	 * (non-Javadoc)
	 * <p>
	 * Title: findSum
	 * </p>
	 * <p>
	 * Description:TODO
	 * </p>
	 * 
	 * @param bean
	 * @return
	 * @see cn.adwalker.ad.admin.operation.service.emar.escore.platform.operation.service.IOperationCampaignConfirmService#findSum(cn.cn.adwalker.ad.admin.operation.bean.emar.escore.platform.operation.bean.CampaignConfirmbean)
	 */
	@Override
	public ChannelCampaignConfirmSumVo findSum(CahannelConfirmbean bean) {
		List<ChannelCampaignConfirmSum> list;
		list = this.getSumList(bean);
		Double sum_forecast_money = 0d;// 统计 总确认数
		Double sum_income_money = 0d;// 确认 总金额
		Integer sum_forecast_amount = 0;// 预确认数
		if (list != null && list.size() > 0) {
			sum_income_money = list.get(0).getSum_income_money();
			sum_forecast_money = list.get(0).getSum_forecast_money();
			sum_forecast_amount = list.get(0).getSum_forecast_amount();
		}
		return new ChannelCampaignConfirmSumVo(sum_income_money, sum_forecast_money,
				sum_forecast_amount);
	}

	/**
	 * <p>
	 * Title: getSumList
	 * </p>
	 * <p>
	 * Description:TODO
	 * </p>
	 * 
	 * @param bean
	 * @return
	 * @author lichuang
	 * @date 2013-10-24
	 * @return List<CampaignConfirmSum>
	 * @version 1.0
	 */
	@SuppressWarnings("unchecked")
	private List<ChannelCampaignConfirmSum> getSumList(CahannelConfirmbean bean) {
		List<ChannelCampaignConfirmSum> list = null;
		List<Object> param = new ArrayList<Object>();
		StringBuffer sb = new StringBuffer(
				"select sum(a.forecast_money) as sum_forecast_money,sum(a.income_money) as sum_income_money,sum(a.forecast_amount) as sum_forecast_amount from  T_CHANNEL_CONFIRM a LEFT JOIN  V_CAMPAIGN t  ON a.campaign_id=t.ID LEFT JOIN t_channel s ON a.`channel_id` = s.id WHERE a.STATUS>=1");
		if (bean != null) {
			if (ObjectUtils.isNotEmpty(bean.getMonth_stat_date())) {
				sb.append(" and a.month_stat_date>= '");
				sb.append(bean.getMonth_stat_date());
				sb.append("'");
			}
			if (ObjectUtils.isNotEmpty(bean.getMonth_end_date())) {
				sb.append(" and a.month_end_date<= '");
				sb.append(bean.getMonth_end_date());
				sb.append("'");
			}
			if (ObjectUtils.isNotEmpty(bean.getStatus())) {
				sb.append(" and a.STATUS =");
				sb.append(bean.getStatus());
			}
			if (ObjectUtils.isNotEmpty(bean.getOs())) {
				sb.append(" and t.os ='");
				sb.append(bean.getOs());
				sb.append("'");
			}
			
			if (ObjectUtils.isNotEmpty(bean.getCampaign_name())) {
				sb.append(" and (upper(t.CAMPAIGN_NAME)  like ? or a.campaign_id =? )");
				param.add("%" + bean.getCampaign_name().toUpperCase() + "%");
				param.add(bean.getCampaign_id());
			}
			if (ObjectUtils.isNotEmpty(bean.getChannel())) {
				sb.append(" and (upper(s.NAME)  like ? or a.channel_id =? )");
				param.add("%" + bean.getChannel_name().toUpperCase() + "%");
				param.add(bean.getChannel_id());
			}

		}
		try {
			list = (List<ChannelCampaignConfirmSum>) this.channelConfirmDao.findAll(
					sb.toString(), param.toArray(), ChannelCampaignConfirmSum.class);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	/**
	 * (non-Javadoc)
	 * <p>
	 * Title: getInfo
	 * </p>
	 * <p>
	 * Description:TODO
	 * </p>
	 * 
	 * @param invoice_id
	 * @param pageInfo
	 * @return
	 * @throws Exception
	 * @see cn.adwalker.ad.admin.operation.service.emar.escore.platform.operation.service.IOperationCampaignConfirmService#getInfo(java.lang.Long,
	 *      com.emar.escore.core.page.IPageInfo)
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<CampaignConfirmVo> getInfo(Long invoice_id, IPageInfo pageInfo)
			throws Exception {

		List<Object> list = new ArrayList<Object>();
		StringBuilder sb = new StringBuilder(
				" T_CAMPAIGN_CONFIRM a left join  V_CAMPAIGN t  on a.campaign_id=t.ID left join t_campaign_salesman s on t.salesman_id=s.id left  join  t_sys_user u  on a.Manager_id=u.id  where 1=1");

		if (ObjectUtils.isNotEmpty(invoice_id)) {
			sb.append(" and a.INVOICE_ID =");
			sb.append(invoice_id);
		}

		return (List<CampaignConfirmVo>) channelConfirmDao
				.findByPage(
						"a.*,t.campaign_name, t.charge_type,t.os,t.adv_id,u.real_name as create_user_name,s.name,s.area_type",
						sb.toString(), list.toArray(),
						" order by a.create_time desc ",
						CampaignConfirmVo.class, pageInfo);
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
	 * @see cn.adwalker.ad.admin.operation.service.emar.escore.platform.operation.service.IOperationCampaignConfirmService#findAll(cn.cn.adwalker.ad.admin.operation.bean.emar.escore.platform.operation.bean.CampaignConfirmbean)
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<CampaignConfirmVo> findAll(CahannelConfirmbean bean)
			throws Exception {
		List<Object> list = new ArrayList<Object>();
		StringBuilder sb = new StringBuilder(
				" T_CAMPAIGN_CONFIRM a left join  V_CAMPAIGN t  on a.campaign_id=t.ID left join t_campaign_salesman s on t.salesman_id=s.id left  join  t_sys_user u  on a.Manager_id=u.id  where 1=1 and a.STATUS>=0");
		if (bean != null) {
			if (ObjectUtils.isNotEmpty(bean.getMonth_stat_date())) {
				sb.append(" and a.month_stat_date>= '");
				sb.append(bean.getMonth_stat_date());
				sb.append("'");
			}
			if (ObjectUtils.isNotEmpty(bean.getMonth_end_date())) {
				sb.append(" and a.month_end_date<= '");
				sb.append(bean.getMonth_end_date());
				sb.append("'");
			}
			if (ObjectUtils.isNotEmpty(bean.getStatus())) {
				sb.append(" and a.STATUS =");
				sb.append(bean.getStatus());
			}
			if (ObjectUtils.isNotEmpty(bean.getOs())) {
				sb.append(" and t.os ='");
				sb.append(bean.getOs());
				sb.append("'");
			}
			if (ObjectUtils.isNotEmpty(bean.getAdv_id())) {
				sb.append(" and t.adv_id like '%");
				sb.append(bean.getAdv_id());
				sb.append("%'");
			}
			if (ObjectUtils.isNotEmpty(bean.getCampaign_name())) {
				sb.append(" and (upper(t.CAMPAIGN_NAME)  like ? or a.campaign_id =? )");
				list.add("%" + bean.getCampaign_name().toUpperCase() + "%");
				list.add(bean.getCampaign_id());
			}

		}
		return (List<CampaignConfirmVo>) channelConfirmDao
				.findAll(
						"a.*,t.campaign_name, t.charge_type,t.os,t.adv_id,u.real_name as create_user_name,s.name,s.area_type",
						sb.toString(), list.toArray(),
						" order by a.create_time desc ",
						CampaignConfirmVo.class);
	}
	/**
	 * 
	 * <p>
	 * Title: channelTotal
	 * </p>
	 * <p>
	 * Description:TODO
	 * </p>
	 * 
	 * @param entity
	 * @throws Exception
	 * @author cuidd
	 * @date 2013-11-5
	 * @return void
	 * @version 1.0
	 */
	private CostChannelTmpSumVo channelTotal(CampaignConfirm entity)
			throws Exception {
		CostChannelTmpSumVo vo = null;
		if (entity != null && entity.getId() != null) {
			vo = getChannelCostAll(entity.getId());
			costChannelDao
					.update("update T_CAMPAIGN_CONFIRM set CHANNEL_AMOUNT=? ,CHANNEL_COST_MONEY=? where id=?",
							new Object[] { vo.getAmount(), vo.getCost(),
									entity.getId() });
			costChannelDao
					.update("update T_FINANCE_COST_CHANNEL set status=? where balance_id=?",
							new Object[] { 1, entity.getId() });

		}
		return vo;

	}

	/**
	 * 
	 * <p>
	 * Title: platformChannelTotal
	 * </p>
	 * <p>
	 * Description:计算总和，并且更新数据状态
	 * </p>
	 * 
	 * @param id
	 * @throws Exception
	 * @author cuidd
	 * @date 2013-11-5
	 * @return void
	 * @version 1.0
	 */
	private void platformTotal(Long id) throws Exception {
		if (id != null) {
			costChannelDao
					.update("update T_FINANCE_COST_PLATFORM set status=? where balance_id=?",
							new Object[] { 1, id });
		}

	}

	@SuppressWarnings("unchecked")
	private CostChannelTmpSumVo getChannelCostAll(Long id) throws Exception {
		CostChannelTmpSumVo vo = null;
		List<CostChannelTmpSumVo> list = (List<CostChannelTmpSumVo>) costChannelDao
				.findAll(
						"select sum(cost)cost ,sum(amount)amount from T_FINANCE_COST_CHANNEL where balance_id=?",
						new Object[] { id }, CostChannelTmpSumVo.class);
		if (list != null && list.size() > 0) {
			vo = list.get(0);
		}
		return vo;
	}

	/**
	 * (non-Javadoc)
	 * <p>
	 * Title: testPlatformCost
	 * </p>
	 * <p>
	 * Description:TODO
	 * </p>
	 * 
	 * @param balance_id
	 * @throws Exception
	 * @see cn.adwalker.ad.admin.operation.service.emar.escore.platform.operation.service.IOperationCampaignConfirmService#testPlatformCost(java.lang.Long)
	 */
	@SuppressWarnings("unchecked")
	@Override
	public void testPlatformCost(Long balance_id) throws Exception {
		List<SimpleBean<BigDecimal>> list = (List<SimpleBean<BigDecimal>>) channelConfirmDao
				.findAll(
						"SELECT  id obj   from   T_CAMPAIGN_CONFIRM where MONTH_STAT_DATE>='2013-10-01' and MONTH_END_DATE<='2013-10-31' and ID not in (SELECT  DISTINCT balance_id from  T_BALANCE_PLATFORM_REL)",
						SimpleBean.class);
		for (SimpleBean<BigDecimal> bean : list) {
			try {
				CampaignConfirm entity = channelConfirmDao.get(bean.getObj()
						.longValue(), CampaignConfirm.class);
				balancePlatformRel(entity);
			} catch (Exception e) {
				e.printStackTrace();
			}

		}

	}

	/**
	 * (non-Javadoc)
	 * <p>
	 * Title: testChannelCost
	 * </p>
	 * <p>
	 * Description:TODO
	 * </p>
	 * 
	 * @throws Exception
	 * 
	 * @see cn.adwalker.ad.admin.operation.service.emar.escore.platform.operation.service.IOperationCampaignConfirmService#testChannelCost()
	 */
	@Override
	public void testChannelCost(Long balance_id, Long media_id)
			throws Exception {
		try {
			CampaignConfirm entity = channelConfirmDao.get(balance_id,
					CampaignConfirm.class);
			balanceChannelRel(entity, media_id);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	@SuppressWarnings("unchecked")
	private CampaignSum findConfirmSum(CampaignConfirm entity) throws Exception {
		StringBuffer sb = new StringBuffer();
		sb.append("select sum(a.confirm_num) as sum_forecast_amount,sum(a.in_price*a.confirm_num) as sum_forecast_money from T_PACKAGE_ACTIVATE_DETAIL a left join V_PLACEMENT_PACKAGE t on a.package_id = t.ID "
				+ "where t.CAMPAIGN_ID = ? and a.static_date>= ? and a.static_date<= ? ");
		List<CampaignSum> objects = (List<CampaignSum>) channelConfirmDao
				.findAll(
						sb.toString(),
						new Object[] { entity.getCampaign_id(),
								entity.getMonth_stat_date(),
								entity.getMonth_end_date() }, CampaignSum.class);
		CampaignSum com = null;
		if (objects != null && objects.size() > 0) {
			com = (CampaignSum) objects.get(0);
			return com;
		}
		return null;
	}
/**
 * (non-Javadoc)
* <p>Title: findByArea</p>
* <p>Description:TODO</p>
* @return
* @throws Exception
* @see cn.adwalker.ad.admin.operation.service.emar.escore.platform.operation.service.IOperationCampaignConfirmService#findByArea()
 */
	@Override
	public List<Area> findByArea() throws Exception {
		return this.areaDao.findByArea();
	}

	/**
	 * (non-Javadoc)
	* <p>Title: updateInCome</p>
	* <p>Description:TODO</p>
	* @param objList
	* @see cn.adwalker.ad.admin.operation.service.IOperationCampaignConfirmService#updateInCome(java.util.List)
	 */
	@Override
	public int[] updateInCome(List<Object[]> objList,String[] idList)throws Exception {
		String ars=null;
		if (idList != null && idList.length > 0) {
			StringBuilder sb = new StringBuilder();
			for (String d : idList) {
				sb.append(d).append(",");
			}
			sb.deleteCharAt(sb.length() - 1);
			ars = sb.toString();
		} else {
			ars = null;
		}
		iosAndroidDayChannelNumDao.updateInCome(ars,1);
		return channelConfirmDao.updateInCome(objList);
	
	}
	
	/**
	 * (non-Javadoc)
	* <p>Title: updateInCome</p>
	* <p>Description:TODO</p>
	* @param objList
	* @see cn.adwalker.ad.admin.operation.service.IOperationCampaignConfirmService#updateInCome(java.util.List)
	 */
	@Override
	public int[] updateInCome(List<Object[]> objList,String[] idList,Object obj)throws Exception {
		String ars=null;
		if (idList != null && idList.length > 0) {
			StringBuilder sb = new StringBuilder();
			for (String d : idList) {
				sb.append(d).append(",");
			}
			sb.deleteCharAt(sb.length() - 1);
			ars = sb.toString();
		} else {
			ars = null;
		}
		iosAndroidDayChannelNumDao.updateInCome(ars,3);
		return channelConfirmDao.updateInCome(objList);
	
	}
}
