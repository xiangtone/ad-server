package cn.adwalker.ad.admin.operation.service;

import java.util.List;

import cn.adwalker.ad.admin.operation.bean.DspInfobean;
import cn.adwalker.ad.admin.operation.form.DspConfigInfoForm;
import cn.adwalker.ad.admin.operation.form.DspInfoForm;
import cn.adwalker.ad.admin.operation.vo.DspInfoVo;
import cn.adwalker.core.page.IPageInfo;
import cn.adwalker.model.operation.domain.DspInfo;


/**
* <p>Title: IDspInfoImpService</p>
* <p>Description:DSP业务service端</p>
* <p>Company: adwalker</p> 
* @author   lichuang
* @date       2013-12-2
 */
public interface IDspInfoImpService {
	/**
	* <p>Title: getDspInfo</p>
	* <p>Description:查出dsp_name和dsp_id</p>
	* @return
	* @throws Exception
	* @author lichuang
	* @date 2013-12-2
	* @return List<DspInfo>
	* @version 1.0
	 */
	public List<DspInfo> getDspInfo()throws Exception;
	/**
	* <p>Title: findList</p>
	* <p>Description:列表</p>
	* @param bean
	* @param pageInfo
	* @return
	* @throws Exception
	* @author lichuang
	* @date 2013-12-3
	* @return List<DspInfoVo>
	* @version 1.0
	 */
	public List<DspInfoVo> findList(DspInfobean bean, IPageInfo pageInfo)throws Exception;
	/**
	* <p>Title: save</p>
	* <p>Description:保存dsp业务数据</p>
	* @param form
	* @throws Exception
	* @author lichuang
	* @date 2013-12-3
	* @return void
	* @version 1.0
	 */
	public void save(DspInfoForm form)throws Exception;
	/**
	* <p>Title: getDspDetail</p>
	* <p>Description:修改dsp业务数据</p>
	* @param id
	* @return
	* @throws Exception
	* @author lichuang
	* @date 2013-12-3
	* @return DspInfoVo
	* @version 1.0
	 */
	public DspInfoVo getDspDetail(Long id)throws Exception;
	/**
	* <p>Title: updateDspService</p>
	* <p>Description:修改Dsp业务</p>
	* @param form
	* @throws Exception
	* @author lichuang
	* @date 2013-12-3
	* @return void
	* @version 1.0
	 */
	public void updateDspService(DspInfoForm form)throws Exception;
	/**
	* <p>Title: saveConfig</p>
	* <p>Description:保存dsp业务参数</p>
	* @param form
	* @throws Exception
	* @author lichuang
	* @date 2013-12-3
	* @return void
	* @version 1.0
	 */
	public void saveConfig(DspConfigInfoForm form)throws Exception;
	/**
	* <p>Title: getConfigList</p>
	* <p>Description:修改参数数据</p>
	* @param dsp_id
	* @return
	* @throws Exception
	* @author lichuang
	* @date 2013-12-3
	* @return List<DspConfigInfoForm>
	* @version 1.0
	 */
	public List<DspConfigInfoForm> getConfigList(Long dsp_id)throws Exception;
	/**
	* <p>Title: updateDspConfig</p>
	* <p>Description:修改参数</p>
	* @param id
	* @param param_type
	* @param param_name
	* @throws Exception
	* @author lichuang
	* @date 2013-12-3
	* @return void
	* @version 1.0
	 */
	public void updateDspConfig(Long[] id,String[] param_type,
			String[] param_name)throws Exception;
	
}
