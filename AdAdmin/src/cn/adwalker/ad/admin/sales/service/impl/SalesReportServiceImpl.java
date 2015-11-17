package cn.adwalker.ad.admin.sales.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import cn.adwalker.ad.admin.sales.bean.DetailAndroidQueryBean;
import cn.adwalker.ad.admin.sales.bean.DetailIosQueryBean;
import cn.adwalker.ad.admin.sales.bean.SalesReportBean;
import cn.adwalker.ad.admin.sales.service.ISalesReportService;
import cn.adwalker.ad.admin.sales.vo.SalesReportDetailAndroidVo;
import cn.adwalker.ad.admin.sales.vo.SalesReportDetailIosVo;
import cn.adwalker.ad.admin.sales.vo.SalesReportVo;
import cn.adwalker.ad.admin.sales.vo.TmpCampainBalance;
import cn.adwalker.core.page.IPageInfo;
import cn.adwalker.core.util.lang.StringUtil;
import cn.adwalker.model.ad.dao.ICampaignDao;
import cn.adwalker.model.ad.dao.ICampaignSalesmanDao;
import cn.adwalker.model.ad.domain.CampaignSalesman;
import cn.adwalker.model.operation.dao.IPackageActivateDao;

/**
 * 
 * <p>
 * Title: SalesReportServiceImpl
 * </p>
 * <p>
 * Description:TODO
 * </p>
 * <p>
 * Company: adwalker
 * </p>
 * 
 * @author cuidd
 * @date 2014-8-12
 */
@Service
public class SalesReportServiceImpl implements ISalesReportService {

	@Resource
	private ICampaignDao campaignDao;

	@Resource
	private IPackageActivateDao packageActivateDao;

	@Resource
	private ICampaignSalesmanDao campaignSalesmanDao;

	@SuppressWarnings("unchecked")
	@Override
	public List<SalesReportVo> findByPage(SalesReportBean bean,
			IPageInfo pageInfor) throws Exception {
		List<SalesReportVo> resultList = null;
		StringBuilder sb = new StringBuilder("where 1=1");
		List<Object> paramList = new ArrayList<Object>();
		if (bean != null) {
			if (StringUtil.isNotEmpty(bean.getCampaign_name())) {
				sb.append(" and (upper(v.campaign_name)  like ? or v.ID = ? )");
				paramList
						.add("%" + bean.getCampaign_name().toUpperCase() + "%");
				paramList.add(bean.getCampaign_id());
			}
			if (StringUtil.isNotEmpty(bean.getAdv_name())) {
				sb.append(" and v.company_name  like ?");
				paramList.add("%" + bean.getAdv_name() + "%");
			}

			if (StringUtil.isNotEmpty(bean.getOs())) {
				sb.append(" and v.os=?");
				paramList.add(bean.getOs());
			}

			if (bean.getSales_id() != null) {
				sb.append(" and sales.id=?");
				paramList.add(bean.getSales_id());
			}

		}

		/*
		 * 1、过滤活动。 2、根据活动信息收集数据
		 */
		List<SalesReportVo> list = (List<SalesReportVo>) campaignDao
				.findByPage(
						"v.id as campaign_id,v.campaign_name,v.adv_id,v.company_name as adv_name,v.os,sales.name as sales_name",
						"v_campaign v left join t_campaign_salesman sales on sales.id=v.salesman_id "
								+ sb.toString(), paramList.toArray()," order by  v.create_time desc",
						SalesReportVo.class, pageInfor);
		Map<Long, TmpCampainBalance> map = null;
		map = getData_android(map, bean.getStart_date(), bean.getEnd_date());
		map = getData_ios(map, bean.getStart_date(), bean.getEnd_date());

		if (list != null) {
			resultList = new ArrayList<SalesReportVo>();
		}
		for (SalesReportVo vo : list) {
			TmpCampainBalance tmp = map.get(vo.getCampaign_id());
			if (tmp != null) {
				vo.setConfirm_amount(tmp.getConfirm_amount());
				vo.setCharage_type(tmp.getCharge_type());
				vo.setConfirm_money(tmp.getConfirm_money());
			}
			resultList.add(vo);
		}
		return resultList;
	}

	/**
	 * 
	 * <p>
	 * Title: getData_ios
	 * </p>
	 * <p>
	 * Description:查询IOS活动对应数据
	 * </p>
	 * 
	 * @param map
	 * @param start_date
	 * @param end_date
	 * @return
	 * @throws Exception
	 * @author cuidd
	 * @date 2014-8-14
	 * @return Map<Long,TmpCampainBalance>
	 * @version 1.0
	 */
	@SuppressWarnings("unchecked")
	private Map<Long, TmpCampainBalance> getData_ios(
			Map<Long, TmpCampainBalance> map, String start_date, String end_date)
			throws Exception {
		StringBuilder sb = new StringBuilder();
		List<Object> prarmList = new ArrayList<Object>();
		sb.append("SELECT  campaign_id,count(1),sum(activate) confirm_amount,sum(activate*price)confirm_money,max(balance_model) charge_type  from ");
		sb.append(" t_ios_activate_num where status=?  group by campaign_id ");
		prarmList.add(9);
		if (!StringUtil.isEmpty(start_date)) {
			sb.append(" and static_date >='" + start_date + "'");
		}
		if (!StringUtil.isEmpty(end_date)) {
			sb.append("and static_date <='" + end_date + "'");
		}
		List<TmpCampainBalance> list = (List<TmpCampainBalance>) packageActivateDao
				.findAll(sb.toString(), prarmList.toArray(),
						TmpCampainBalance.class);
		if (list != null && list.size() > 0) {
			if (map == null) {
				map = new HashMap<Long, TmpCampainBalance>();
			}
			for (TmpCampainBalance balance : list) {
				map.put(balance.getCampaign_id(), balance);
			}
		}

		return map;
	}

	@SuppressWarnings("unchecked")
	private Map<Long, TmpCampainBalance> getData_android(
			Map<Long, TmpCampainBalance> map, String start_date, String end_date)
			throws Exception {
		StringBuilder sb = new StringBuilder();
		List<Object> prarmList = new ArrayList<Object>();
		sb.append("select t.* from ");
		sb.append("(select campaign_id, sum(confirm_amount) as confirm_amount,sum(confirm_amount*in_price) confirm_money,max(charge_type) charge_type  from  t_package_activate where status=? ");
		prarmList.add(1);
		if (!StringUtil.isEmpty(start_date)) {
			sb.append(" and static_date >='" + start_date + "'");
		}
		if (!StringUtil.isEmpty(end_date)) {
			sb.append("and static_date <='" + end_date + "'");
		}
		sb.append(" group by campaign_id ) t ");
		List<TmpCampainBalance> list = (List<TmpCampainBalance>) packageActivateDao
				.findAll(sb.toString(), prarmList.toArray(),
						TmpCampainBalance.class);
		if (list != null && list.size() > 0) {
			if (map == null) {
				map = new HashMap<Long, TmpCampainBalance>();
			}
			for (TmpCampainBalance balance : list) {
				map.put(balance.getCampaign_id(), balance);
			}
		}

		return map;
	}

	@Override
	public List<CampaignSalesman> getSalesmanList() throws Exception {
		return campaignSalesmanDao.all();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<SalesReportDetailAndroidVo> detail_android(
			DetailAndroidQueryBean bean, IPageInfo pageInfo) throws Exception {
		List<SalesReportDetailAndroidVo> list = null;
		if (bean != null && bean.getCampaign_id() != null) {
			StringBuilder sb = new StringBuilder();
			List<Object> paramList = new ArrayList<Object>();
			sb.append("(select static_date,package_id,sys_activate,confirm_amount,in_price,charge_type,campaign_id from  t_package_activate where status=? ");
			paramList.add(1);
			sb.append("and campaign_id=? ");
			paramList.add(bean.getCampaign_id());
			if (!StringUtil.isEmpty(bean.getStart_date())) {
				sb.append(" and static_date >=?");
				paramList.add(bean.getStart_date());
			}
			if (!StringUtil.isEmpty(bean.getEnd_date())) {
				sb.append("and static_date <=?");
				paramList.add(bean.getEnd_date());
			}
			sb.append(" order by static_date,package_id ) t ");
			sb.append("left join  t_placement_package p on t.package_id=p.id ");

			if (!StringUtil.isEmpty(bean.getPackageInfo())) {
				sb.append(" and (upper(p.file_name)  like ? or t.package_id = ? )");
				paramList.add("%" + bean.getPackage_name() + "%");
				paramList.add(bean.getPackage_id());
			}

			list = (List<SalesReportDetailAndroidVo>) packageActivateDao
					.findByPage(" t.*,p.file_name", sb.toString(),
							paramList.toArray(),
							SalesReportDetailAndroidVo.class, pageInfo);
		}

		return list;

	}

	@SuppressWarnings("unchecked")
	@Override
	public List<SalesReportDetailIosVo> detail_ios(DetailIosQueryBean bean,
			IPageInfo pageInfo) throws Exception {
		List<SalesReportDetailIosVo> list = null;
		if (bean != null && bean.getCampaign_id() != null) {
			StringBuilder sb = new StringBuilder();
			List<Object> paramList = new ArrayList<Object>();
			sb.append("(select static_date,activate,confirm_amount,price in_price,balance_model charge_type,campaign_id,click from  t_ios_activate_num where status=? ");
			sb.append("and campaign_id=? ");
			paramList.add(9);
			paramList.add(bean.getCampaign_id());
			if (!StringUtil.isEmpty(bean.getStart_date())) {
				sb.append(" and static_date >=?");
				paramList.add(bean.getStart_date());
			}
			if (!StringUtil.isEmpty(bean.getEnd_date())) {
				sb.append("and static_date <=?");
				paramList.add(bean.getEnd_date());
			}
			sb.append(" order by id ) t ");
			list = (List<SalesReportDetailIosVo>) packageActivateDao
					.findByPage(" t.*", sb.toString(), paramList.toArray(),
							SalesReportDetailIosVo.class, pageInfo);
		}

		return list;
	}

	@Override
	public Long getSalesmanIdBySysUser(Long id) throws Exception {
		Long l = null;
		if (id != null) {
			l = campaignSalesmanDao.getSalesmanIdBySysUser(id);
		}
		return l;
	}
}
