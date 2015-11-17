package cn.adwalker.ad.admin.operation.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import cn.adwalker.core.page.IPageInfo;
import cn.adwalker.core.util.CacheUtils;
import cn.adwalker.core.util.lang.ObjectUtils;
import cn.adwalker.model.ad.dao.ICampaignDao;
import cn.adwalker.model.ad.dao.ICampaignPlacementRelDao;
import cn.adwalker.model.ad.dao.IPlacementDao;
import cn.adwalker.model.ad.domain.Campaign;
import cn.adwalker.model.ad.domain.CampaignPlacementRel;
import cn.adwalker.model.config.dao.ICpIosConfigDao;
import cn.adwalker.model.operation.dao.IOperationInterfaceIosDao;
import cn.adwalker.model.operation.domain.CollocationIos;
import cn.adwalker.ad.admin.operation.bean.InterfaceIosBean;
import cn.adwalker.ad.admin.operation.form.CollocationIosForm;
import cn.adwalker.ad.admin.operation.service.IOperationInterfaceIosService;
import cn.adwalker.ad.admin.operation.vo.CollocationIosVo;
import cn.adwalker.ad.admin.operation.vo.InterfaceChannelVo;
import cn.adwalker.ad.admin.operation.vo.InterfaceIosVo;

/**
 * <p>
 * Title: OperationInterfaceIosServiceImpl
 * </p>
 * <p>
 * Description:TODO
 * </p>
 * <p>
 * Company: adwalker
 * </p>
 * 
 * @author lichuang
 * @date 2013-6-5
 */
@Service("operationIosService")
public class OperationInterfaceIosServiceImpl implements
		IOperationInterfaceIosService {

	@Resource
	private IOperationInterfaceIosDao interfaceIosDao;
	@Resource
	private IPlacementDao  placementDao;  //add by jief
	
	@Resource
    private ICpIosConfigDao cpIosConfigDao;
	
	@Resource(name = "campaignDao")
	private ICampaignDao campaignDao;

	@Resource(name = "campaignPlacementRelDao")
	private ICampaignPlacementRelDao campaignPlacementRelDao;
	
	/**
	 * (non-Javadoc)
	 * <p>
	 * Title: findList
	 * </p>
	 * <p>
	 * Description:TODO
	 * </p>
	 * 
	 * @param bean
	 * @param pageInfo
	 * @return
	 * @throws Exception
	 * @see cn.adwalker.ad.admin.operation.service.IOperationInterfaceIosService#findList(cn.adwalker.ad.admin.operation.bean.InterfaceIosBean,
	 *      cn.adwalker.core.page.IPageInfo)
	 */
	@SuppressWarnings("unchecked")
	@Transactional(propagation = Propagation.NEVER)
	@Override
	public List<InterfaceIosVo> findList(InterfaceIosBean bean,
			IPageInfo pageInfo) throws Exception {
		List<Object> list = new ArrayList<Object>();
		StringBuffer sb = new StringBuffer("V_CAMPAIGN where 1=1 and OS ='ios'");
		if (ObjectUtils.isNotEmpty(bean.getCampaign_name())) {
			sb.append(" and CAMPAIGN_NAME ");
			sb.append(" like '%");
			sb.append(bean.getCampaign_name());
			sb.append("%'");
		}
		if (ObjectUtils.isNotEmpty(bean.getCampaign_id())) {
			sb.append(" and ID ='");
			sb.append(bean.getCampaign_id());
			sb.append("'");
		}
		return (List<InterfaceIosVo>) interfaceIosDao.findByPage("*",
				sb.toString(), list.toArray(), " order by create_time desc ",
				InterfaceIosVo.class, pageInfo);
	}

	/**
	 * (non-Javadoc)
	 * <p>
	 * Title: updatecollocationIos
	 * </p>
	 * <p>
	 * Description:TODO
	 * </p>
	 * 
	 * @param form
	 * @throws Exception
	 * @see cn.adwalker.ad.admin.operation.service.IOperationInterfaceIosService#updatecollocationIos(cn.adwalker.ad.admin.operation.form.CollocationIosForm)
	 */
	@Transactional(propagation=Propagation.SUPPORTS,readOnly=true)
	@Override
	public void updatecollocationIos(CollocationIosForm form) throws Exception {
		CollocationIos collocation = new CollocationIos();
		if (form.getId()!= null && ObjectUtils.isNotEmpty(form.getId())) {
//			collocation.setAd_key(form.getAd_key());
//			collocation.setAdid_str(form.getAdid_str());
//			collocation.setClient_ip(form.getClient_ip());
//			collocation.setDeviceid_para(form.getDeviceid_para());
//			collocation.setEventtime_para(form.getEventtime_para());
			collocation.setId(form.getId());
//			collocation.setSend_type(form.getSend_type());
//			collocation.setSource(form.getSource());
//			collocation.setSourse_str(form.getSourse_str());
//			collocation.setUrl(form.getUrl());
//			collocation.setAd_name(form.getAd_name());
			collocation.setPlacement_id(form.getPlacement_id());
			interfaceIosDao.update(collocation);
			if(collocation.getPlacement_id()!=null&&collocation.getId()!=null){
				interfaceIosDao.updatePlacementIos(collocation);
			}
			
			//接入单价的修改
			if(ObjectUtils.isNotEmpty(form.getPlacement_id())){
				CampaignPlacementRel rel=campaignPlacementRelDao.getByPlacementId(form.getPlacement_id());
				Campaign campaign = (Campaign) campaignDao.getById(rel.getCampaign_id().toString());
				if( ObjectUtils.isNotEmpty(form.getId())){
					cpIosConfigDao.updatePrice(form.getId(), campaign.getPrice());
					CacheUtils.updateConfigAdvPrice(form.getId());
					}
			}
		} 
//		else {
//			collocation.setAd_key(form.getAd_key());
//			collocation.setAdid_str(form.getAdid_str());
//			collocation.setClient_ip(form.getClient_ip());
//			collocation.setDeviceid_para(form.getDeviceid_para());
//			collocation.setEventtime_para(form.getEventtime_para());
//			collocation.setId(form.getPlacement_id()+"yjf");
//			collocation.setSend_type(form.getSend_type());
//			collocation.setSource(form.getSource());
//			collocation.setSourse_str(form.getSourse_str());
//			collocation.setUrl(form.getUrl());
//			collocation.setPlacement_id(form.getPlacement_id());
//			collocation.setAd_name(form.getAd_name());
//			collocation.setCreate_time(new Date());
//			interfaceIosDao.insertIos(collocation);
//			if(collocation.getPlacement_id()!=null&&collocation.getId()!=null){
//				interfaceIosDao.updatePlacementIos(collocation);
//			}
//		}

	}

	/**
	 * (non-Javadoc)
	 * <p>
	 * Title: getIos
	 * </p>
	 * <p>
	 * Description:TODO
	 * </p>
	 * 
	 * @param campaign_id
	 * @return
	 * @throws Exception
	 * @see cn.adwalker.ad.admin.operation.service.IOperationInterfaceIosService#getIos(java.lang.Long)
	 */
	@Override
	public CollocationIosVo getIos(Long placement_id) throws Exception {
		CollocationIos collocation=interfaceIosDao.getIos(placement_id);
		CollocationIosVo iosVo=null;
		if(collocation!=null){
			iosVo=new CollocationIosVo(collocation); 
		}
		return iosVo;
	}

	/**  (non-Javadoc)
	* <p>Title: findChannelList</p>
	* <p>Description:TODO</p>
	* @param pageInfo
	* @return
	* @see cn.adwalker.ad.admin.operation.service.IOperationInterfaceIosService#findChannelList(cn.adwalker.core.page.IPageInfo)
	*/
	@SuppressWarnings("unchecked")
	@Override
	public List<InterfaceChannelVo> findChannelList(IPageInfo pageInfo) throws Exception {
		List<Object> list = new ArrayList<Object>();
		StringBuffer sb = new StringBuffer(" T_CHANNEL_CONFIG where 1=1 ");
		
		List<InterfaceChannelVo> channellist= (List<InterfaceChannelVo>) interfaceIosDao.findByPage("*",
				sb.toString(), list.toArray(), " order by CHANNEL_NAME desc ",
				InterfaceChannelVo.class, pageInfo);
		
		
		return channellist;
	}

	/**  (non-Javadoc)
	* <p>Title: getChannel</p>
	* <p>Description:TODO</p>
	* @param channel
	* @return
	* @see cn.adwalker.ad.admin.operation.service.IOperationInterfaceIosService#getChannel(java.lang.String)
	*/
	@Override
	public InterfaceChannelVo getChannel(String channel) {
		InterfaceChannelVo vo=interfaceIosDao.getChannel(channel);
		if(vo==null){
			vo=new InterfaceChannelVo(); 
		}
		return vo;
	}
	@Override
	public String getConfigByPlacmentId(Long placment_id) {
		return placementDao.getConfigId(placment_id);
	}
}
