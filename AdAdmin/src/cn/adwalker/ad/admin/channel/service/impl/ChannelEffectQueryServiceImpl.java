package cn.adwalker.ad.admin.channel.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import cn.adwalker.core.page.IPageInfo;
import cn.adwalker.core.util.lang.ObjectUtils;
import cn.adwalker.model.operation.dao.IPackageActivateDetailDao;
import cn.adwalker.model.operation.domain.AdvNumberSum;
import cn.adwalker.ad.admin.channel.bean.ChannelEffectQueryBean;
import cn.adwalker.ad.admin.channel.service.IChannelEffectQueryService;
import cn.adwalker.ad.admin.channel.vo.ChannelEffectSumVo;
import cn.adwalker.ad.admin.channel.vo.ChannelEffectVo;

/**
 * <p>
 * Title: ChannelEffectQueryServiceImpl
 * </p>
 * <p>
 * Description:android渠道效果数查询
 * </p>
 * <p>
 * Company: adwalker
 * </p>
 * 
 * @author lichuang
 * @date 2013-5-23
 */
@Service
public class ChannelEffectQueryServiceImpl implements
		IChannelEffectQueryService {

	@Resource
	private IPackageActivateDetailDao packageActivateDetailDao;

	/**
	 * (non-Javadoc)
	 * <p>
	 * Title: findDetailByPList
	 * </p>
	 * <p>
	 * Description:TODO
	 * </p>
	 * 
	 * @param bean
	 * @param pageInfo
	 * @return
	 * @throws Exception
	 * @see cn.adwalker.ad.admin.operation.service.IConfirmationNumberService#findDetailByPList(cn.adwalker.admin.operation.bean.ConfirmNumberbean,
	 *      cn.adwalker.core.page.IPageInfo)
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<ChannelEffectVo> findByPage(ChannelEffectQueryBean bean,
			IPageInfo pageInfo) throws Exception {
		List<ChannelEffectVo> list = null;
		List<Object> paramsList = new ArrayList<Object>();
		StringBuilder sb = new StringBuilder(
				" T_PACKAGE_ACTIVATE_DETAIL a left join V_PLACEMENT_PACKAGE t on a.package_id=t.ID LEFT  JOIN  t_channel  m ON  a.media_id=m.id  left join t_type sys_type on a.type_id=sys_type.id,T_PACKAGE_ACTIVATE p where 1=1 and a.parent_id=p.id and p.status=1 and a.status=? and a.media_type=?");
		paramsList.add(1);// 状态为已发布
		paramsList.add(1);// 媒体类型为渠道
		if (bean != null) {
			if (bean.getRole_id() == 2009 || bean.getRole_id() == 4
					|| bean.getRole_id() == 2015) {
				if (!StringUtils.isEmpty(bean.getReal_name())) {
					sb.append(" and m.channe_manager like '%");
					sb.append(bean.getReal_name().trim());
					sb.append("%'");
				}
			}
			if (ObjectUtils.isNotEmpty(bean.getStatic_start_date())) {
				sb.append(" and a.static_date >=?");
				paramsList.add(bean.getStatic_start_date());
			}

			if (ObjectUtils.isNotEmpty(bean.getStatic_end_date())) {
				sb.append(" and a.static_date<=?");
				paramsList.add(bean.getStatic_end_date());
			}
			if (!StringUtils.isEmpty(bean.getPackage_id())) {
				sb.append(" and a.PACKAGE_ID like '%");
				sb.append(bean.getPackage_id().trim());
				sb.append("%'");
			}
			if (!StringUtils.isEmpty(bean.getCampaign_name())) {
				sb.append(" and upper(t.CAMPAIGN_NAME) like '%");
				sb.append(bean.getCampaign_name().trim().toUpperCase());
				sb.append("%'");
			}
			if (!StringUtils.isEmpty(bean.getMedia_name())) {
				sb.append(" and m.name like '%");
				sb.append(bean.getMedia_name());
				sb.append("%'");
			}
		}
		list = (List<ChannelEffectVo>) packageActivateDetailDao
				.findByPage(
						" a.*,t.CAMPAIGN_NAME,t.code,t.file_name,t.remarks,m.name media_name,sys_type.name type_name",
						sb.toString(), paramsList.toArray(),
						" order by a.id desc ", ChannelEffectVo.class, pageInfo);
		return list;
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
	 * @see cn.adwalker.ad.admin.operation.service.IConfirmationNumberService#findAll(cn.adwalker.admin.finance.bean.FinanceConsumebean)
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<ChannelEffectVo> findAll(ChannelEffectQueryBean bean)
			throws Exception {
		List<ChannelEffectVo> resultList = null;
		List<Object> list = new ArrayList<Object>();
		StringBuilder sb = new StringBuilder(
				" T_PACKAGE_ACTIVATE_DETAIL a left join V_PLACEMENT_PACKAGE t on a.package_id=t.ID LEFT  JOIN  t_channel  m ON  a.media_id=m.id  left join   t_type sys_type on a.type_id=sys_type.id,T_PACKAGE_ACTIVATE p where 1=1 and a.parent_id=p.id and p.status=1 and a.status=?  and a.media_type=? ");
		list.add(1);// 状态为已发布
		list.add(1);// 媒体类型为渠道
		if (bean != null) {
			if (bean.getRole_id() == 2009 || bean.getRole_id() == 4
					|| bean.getRole_id() == 2015) {
				if (!StringUtils.isEmpty(bean.getReal_name())) {
					sb.append(" and m.channe_manager like '%");
					sb.append(bean.getReal_name().trim());
					sb.append("%'");
				}

			}
			if (ObjectUtils.isNotEmpty(bean.getStatic_start_date())) {
				sb.append(" and a.static_date >=?");
				list.add(bean.getStatic_start_date());
			}

			if (ObjectUtils.isNotEmpty(bean.getStatic_end_date())) {
				sb.append(" and a.static_date<=?");
				list.add(bean.getStatic_end_date());
			}
			if (!StringUtils.isEmpty(bean.getPackage_id())) {
				sb.append(" and a.PACKAGE_ID like '%");
				sb.append(bean.getPackage_id().trim());
				sb.append("%'");
			}
			if (!StringUtils.isEmpty(bean.getCampaign_name())) {
				sb.append(" and upper(t.CAMPAIGN_NAME) like '%");
				sb.append(bean.getCampaign_name().trim().toUpperCase());
				sb.append("%'");
			}
			if (!StringUtils.isEmpty(bean.getMedia_name())) {
				sb.append(" and upper(m.name) like '%");
				sb.append(bean.getMedia_name().trim().toLowerCase());
				sb.append("%'");
			}
		}

		resultList = (List<ChannelEffectVo>) packageActivateDetailDao
				.findAll(
						" a.*,t.CAMPAIGN_NAME,t.code,t.file_name,t.remarks,m.name media_name,sys_type.name type_name",
						sb.toString(), list.toArray(),
						" order by a.create_time desc ", ChannelEffectVo.class);
		return resultList;
	}

	/**
	 * (non-Javadoc)
	 * <p>
	 * Title: getNumberSum
	 * </p>
	 * <p>
	 * Description:TODO
	 * </p>
	 * 
	 * @param bean
	 * @return
	 * @throws Exception
	 * @see cn.adwalker.ad.admin.operation.service.IConfirmationNumberService#getNumberSum(cn.adwalker.admin.operation.bean.ConfirmNumDetailbean)
	 */
	@Override
	public ChannelEffectSumVo getNumberSum(ChannelEffectQueryBean bean)
			throws Exception {
		List<AdvNumberSum> list;
		list = this.getAdvNumberSumList(bean);
		int sum_platform_amount = 0;// 统计 总确认数
		int sum_amount = 0;// 总确认数

		if (list != null && list.size() > 0) {
			sum_platform_amount = list.get(0).getSum_platform_amount();
			sum_amount = list.get(0).getSum_amount();

		}
		return new ChannelEffectSumVo(sum_amount, sum_platform_amount);
	}

	/**
	 * 
	 * <p>
	 * Title: getAdvNumberSumList
	 * </p>
	 * <p>
	 * Description:根据条件查询总数
	 * </p>
	 * 
	 * @param bean
	 * @return
	 * @author lichuang
	 * @date 2014-2-11
	 * @return List<AdvNumberSum>
	 * @version 1.0
	 * @throws Exception 
	 */
	@SuppressWarnings("unchecked")
	private List<AdvNumberSum> getAdvNumberSumList(ChannelEffectQueryBean bean) throws Exception {
		List<AdvNumberSum> listsum = null;
		List<Object> list = new ArrayList<Object>();
		StringBuilder sb = new StringBuilder(
				"select  IFNULL(sum(a.confirm_num),0) as sum_amount,IFNULL(sum(a.confirm_amount),0) as sum_platform_amount from T_PACKAGE_ACTIVATE_DETAIL a left join V_PLACEMENT_PACKAGE t on a.package_id=t.ID LEFT  JOIN  t_channel m ON  a.media_id=m.id  left join   t_type sys_type on a.type_id=sys_type.id,T_PACKAGE_ACTIVATE p where 1=1 and a.parent_id=p.id and p.status=1 and a.status=? and a.media_type=? ");

		list.add(1);// 状态为已发布
		list.add(1);// 媒体类型为渠道
		if (bean != null) {
			if (ObjectUtils.isNotEmpty(bean.getStatic_start_date())) {
				sb.append(" and a.static_date >=?");
				list.add(bean.getStatic_start_date());
			}

			if (ObjectUtils.isNotEmpty(bean.getStatic_end_date())) {
				sb.append(" and a.static_date<=?");
				list.add(bean.getStatic_end_date());
			}
			if (!StringUtils.isEmpty(bean.getPackage_id())) {
				sb.append(" and a.PACKAGE_ID like '%");
				sb.append(bean.getPackage_id().trim());
				sb.append("%'");
			}
			if (!StringUtils.isEmpty(bean.getCampaign_name())) {
				sb.append(" and upper(t.CAMPAIGN_NAME) like '%");
				sb.append(bean.getCampaign_name().trim().toUpperCase());
				sb.append("%'");
			}
			if (!StringUtils.isEmpty(bean.getMedia_name())) {
				sb.append(" and upper(m.name) like '%");
				sb.append(bean.getMedia_name().trim().toUpperCase());
				sb.append("%'");
			}
		}

		listsum = (List<AdvNumberSum>) this.packageActivateDetailDao.findAll(
				sb.toString(), list.toArray(), AdvNumberSum.class);

		return listsum;
	}
}
