package cn.adwalker.ad.admin.operation.service;

import java.util.List;

import cn.adwalker.ad.admin.operation.bean.InterfaceIosBean;
import cn.adwalker.ad.admin.operation.form.CollocationIosForm;
import cn.adwalker.ad.admin.operation.vo.CollocationIosVo;
import cn.adwalker.ad.admin.operation.vo.InterfaceChannelVo;
import cn.adwalker.ad.admin.operation.vo.InterfaceIosVo;
import cn.adwalker.core.page.IPageInfo;

/**
 * <p>
 * Title: IOperationInterfaceIosService
 * </p>
 * <p>
 * Description:iosService
 * </p>
 * <p>
 * Company: adwalker
 * </p>
 * 
 * @author lichuang
 * @date 2013-5-23
 */
public interface IOperationInterfaceIosService {

	/**
	 * 
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
	 * @author cuidd
	 * @date 2013-9-3
	 * @return List<InterfaceIosVo>
	 * @version 1.0
	 */
	public List<InterfaceIosVo> findList(InterfaceIosBean bean,
			IPageInfo pageInfo) throws Exception;

	/**
	 * <p>
	 * Title: updatecollocationIos
	 * </p>
	 * <p>
	 * Description:插入或更新
	 * </p>
	 * 
	 * @param id
	 * @throws Exception
	 * @author lichuang
	 * @date 2013-6-6
	 * @return void
	 * @version 1.0
	 */
	public void updatecollocationIos(CollocationIosForm form) throws Exception;

	/**
	 * <p>
	 * Title: getIos
	 * </p>
	 * <p>
	 * Description:获取ios配置数据
	 * </p>
	 * 
	 * @param campaign_id
	 * @return
	 * @throws Exception
	 * @author lichuang
	 * @date 2013-6-6
	 * @return CollocationIosVo
	 * @version 1.0
	 */
	public CollocationIosVo getIos(Long placement_id) throws Exception;

	/**
	 * <p>
	 * Title: findChannelList
	 * </p>
	 * <p>
	 * Description:TODO
	 * </p>
	 * 
	 * @param pageInfo
	 * @return
	 * @return List<InterfaceIosVo>
	 * @throws
	 */
	public List<InterfaceChannelVo> findChannelList(IPageInfo pageInfo)
			throws Exception;

	/**
	 * <p>
	 * Title: getChannel
	 * </p>
	 * <p>
	 * Description:TODO
	 * </p>
	 * 
	 * @param channel
	 * @return
	 * @return InterfaceChannelVo
	 * @throws
	 */
	public InterfaceChannelVo getChannel(String channel);
	/**
	 * <p>根据投放id获取配置id</p>
	 * @param placment_id
	 * @return
	 */
	public String getConfigByPlacmentId(Long placment_id);
}
