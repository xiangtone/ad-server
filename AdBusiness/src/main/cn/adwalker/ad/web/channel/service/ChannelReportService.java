package cn.adwalker.ad.web.channel.service;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import cn.adwalker.ad.core.page.IPageInfo;
import cn.adwalker.ad.model.channel.dao.ChannelReportDao;
import cn.adwalker.ad.model.channel.domain.ChannelReportSum;
import cn.adwalker.ad.model.channel.domain.UserChannels;
import cn.adwalker.ad.util.DateUtil;
import cn.adwalker.ad.web.channel.bean.ChannelReportbean;
import cn.adwalker.ad.web.channel.vo.ChannelReportSumVo;
import cn.adwalker.ad.web.channel.vo.ChannelReportVo;
import cn.adwalker.ad.web.channel.vo.ChannelSdkReportVo;

/**
 * <p>
 * Description:前台渠道代理统计
 * </p>
 */
@Service("channelReportService")
public class ChannelReportService {

	@Resource
	private ChannelReportDao channelReportDao;

	@SuppressWarnings("unchecked")
	public List<ChannelReportVo> findProxyList(Long id, ChannelReportbean bean, IPageInfo pageInfo) throws Exception {
		List<ChannelReportVo> result = null;
		List<Object> list = new ArrayList<Object>();
		StringBuilder sb = new StringBuilder(" T_PACKAGE_ACTIVATE_DETAIL a left join V_PLACEMENT_PACKAGE t  on a.package_id = t.ID left join t_placement p on t.PLACEMENT_ID=p.id where a.static_date>='2014-06-01' and a.status=1 and a.media_id= ");
		if (id != null) {
			sb.append(id);
		}
		if (bean != null) {
			if (!StringUtils.isEmpty(bean.getStart_date())) {
				sb.append(" and a.STATIC_DATE>='");
				sb.append(bean.getStart_date());
				sb.append("'");
			}
			if (!StringUtils.isEmpty(bean.getEnd_date())) {
				sb.append(" and a.STATIC_DATE<='");
				sb.append(bean.getEnd_date());
				sb.append("'");
			}
			if (!StringUtils.isEmpty(bean.getCampaign_name())) {
				sb.append(" and p.NAME like '%");
				sb.append(bean.getCampaign_name());
				sb.append("%'");
			}
		}
		result = (List<ChannelReportVo>) channelReportDao.findByPage(" a.id,a.static_date,a.out_price as price,a.confirm_amount,a.media_id,p.name as campaign_name,t.file_name", sb.toString(), list.toArray(), " order by a.static_date desc ", ChannelReportVo.class, pageInfo);
		return result;

	}

	public UserChannels exists(String email) throws Exception {
		return this.channelReportDao.exists(email);
	}
	
	public UserChannels getById(Long id) throws Exception {
		UserChannels userChannel = new UserChannels();
		userChannel = channelReportDao.getById(id);
		return userChannel;
	}

	public void updateChannelPass(String password, Long id) throws Exception {
		channelReportDao.updateUserPassword(password, id);
	}

	public List<ChannelSdkReportVo> findSdkList(Long id, ChannelReportbean bean, IPageInfo pageInfo) throws Exception {
		List<ChannelSdkReportVo> result = null;
		List<Object> list = new ArrayList<Object>();
		StringBuilder sb = new StringBuilder(" t_static_channel_byday t left join t_application a  on t.app_id=a.id left join  T_CHANNEL c on t.channel=c.marking where t.static_date>='2014-06-01'  and type_id='0' and c.id= ");
		if (id != null) {
			sb.append(id);
		}
		if (bean != null) {
			if (!StringUtils.isEmpty(bean.getStart_date())) {
				sb.append(" and t.STATIC_DATE>='");
				sb.append(bean.getStart_date());
				sb.append("'");
			}
			if (!StringUtils.isEmpty(bean.getEnd_date())) {
				sb.append(" and t.STATIC_DATE<='");
				sb.append(bean.getEnd_date());
				sb.append("'");
			}
			if (!StringUtils.isEmpty(bean.getApp_name())) {
				sb.append(" and a.name like '%");
				sb.append(bean.getApp_name());
				sb.append("%'");
			}
			sb.append(" group by t.channel,t.static_date,a.name,c.id");
		}
		result = (List<ChannelSdkReportVo>) channelReportDao.findByPage(" t.channel,t.static_date,a.name as app_name,c.id,sum(t.activate) as confirm_amount,sum(t.cost) as confirm_money", sb.toString(), list.toArray(), ChannelSdkReportVo.class, pageInfo);
		return result;
	}

	public ChannelReportSumVo getReportSum(Long id, ChannelReportbean bean) {
		List<ChannelReportSum> list;
		list = this.getReportSumAmount(id, bean);
		Integer sum_amount = 0;// 统计 总确认数
		Double sum_money = 0d;// 确认 总金额
		Double sum_money_sdk = 0d;// 确认 总金额
		if (list != null && list.size() > 0) {
			sum_amount = list.get(0).getSum_amount();
			sum_money = list.get(0).getSum_money();
		}
		return new ChannelReportSumVo(sum_amount, sum_money, sum_money_sdk);
	}

	private List<ChannelReportSum> getReportSumAmount(Long id, ChannelReportbean bean) {
		List<ChannelReportSum> list = null;
		StringBuffer sb = new StringBuffer("select sum(a.confirm_amount) as sum_amount ,sum(a.confirm_amount*a.out_price) as sum_money  from T_PACKAGE_ACTIVATE_DETAIL a left join V_PLACEMENT_PACKAGE t  on a.package_id = t.ID left join t_placement p on t.PLACEMENT_ID=p.id where a.static_date>='2014-06-01' and a.status=1 and a.media_id= ");
		if (id != null) {
			sb.append(id);
		}
		if (bean != null) {
			if (!StringUtils.isEmpty(bean.getStart_date())) {
				sb.append(" and a.STATIC_DATE>='");
				sb.append(bean.getStart_date());
				sb.append("'");
			}
			if (!StringUtils.isEmpty(bean.getEnd_date())) {
				sb.append(" and a.STATIC_DATE<='");
				sb.append(bean.getEnd_date());
				sb.append("'");
			}
			if (!StringUtils.isEmpty(bean.getCampaign_name())) {
				sb.append(" and p.NAME like '%");
				sb.append(bean.getCampaign_name());
				sb.append("%'");
			}
		}
		try {
			list = (List<ChannelReportSum>) this.channelReportDao.findAll(sb.toString(), ChannelReportSum.class);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	public ChannelReportSumVo getReportSumSdk(Long id, ChannelReportbean bean) {
		List<ChannelReportSum> list;
		list = this.getReportSumSdkCh(id, bean);
		Integer sum_amount = 0;// 统计 总确认数
		Double sum_money = 0d;// 确认 总金额
		Double sum_money_sdk = 0d;// 确认 总金额
		if (list != null && list.size() > 0) {
			sum_amount = list.get(0).getSum_amount();
			sum_money_sdk = list.get(0).getSum_money_sdk();
		}
		return new ChannelReportSumVo(sum_amount, sum_money, sum_money_sdk);
	}

	private List<ChannelReportSum> getReportSumSdkCh(Long id, ChannelReportbean bean) {
		List<ChannelReportSum> list = null;
		StringBuffer sb = new StringBuffer("select sum(t.activate) as sum_amount ,sum(t.cost) as sum_money_sdk  from t_static_channel_byday t left join t_application a  on t.app_id=a.id left join  T_CHANNEL c on t.channel=c.marking where t.static_date>='2014-06-01'  and type_id='0' and c.id= ");
		if (id != null) {
			sb.append(id);
		}
		if (bean != null) {
			if (!StringUtils.isEmpty(bean.getStart_date())) {
				sb.append(" and t.STATIC_DATE>='");
				sb.append(bean.getStart_date());
				sb.append("'");
			}
			if (!StringUtils.isEmpty(bean.getEnd_date())) {
				sb.append(" and t.STATIC_DATE<='");
				sb.append(bean.getEnd_date());
				sb.append("'");
			}
			if (!StringUtils.isEmpty(bean.getApp_name())) {
				sb.append(" and a.NAME like '%");
				sb.append(bean.getApp_name());
				sb.append("%'");
			}
		}
		try {
			list = (List<ChannelReportSum>) this.channelReportDao.findAll(sb.toString(), ChannelReportSum.class);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}
}