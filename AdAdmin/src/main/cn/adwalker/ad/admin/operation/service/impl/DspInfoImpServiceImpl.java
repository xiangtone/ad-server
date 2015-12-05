package cn.adwalker.ad.admin.operation.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import cn.adwalker.core.page.IPageInfo;
import cn.adwalker.core.util.DateUtil;
import cn.adwalker.core.util.lang.ObjectUtils;
import cn.adwalker.model.operation.dao.IDspConfigInfoDao;
import cn.adwalker.model.operation.dao.IDspInfoDao;
import cn.adwalker.model.operation.domain.DspConfigInfo;
import cn.adwalker.model.operation.domain.DspInfo;
import cn.adwalker.ad.admin.operation.bean.DspInfobean;
import cn.adwalker.ad.admin.operation.form.DspConfigInfoForm;
import cn.adwalker.ad.admin.operation.form.DspInfoForm;
import cn.adwalker.ad.admin.operation.service.IDspInfoImpService;
import cn.adwalker.ad.admin.operation.vo.DspInfoVo;

/**
 * <p>
 * Title: DspInfoImpServiceImpl
 * </p>
 * <p>
 * Description:DSP业务service端
 * </p>
 * <p>
 * Company: adwalker
 * </p>
 * 
 * @author lichuang
 * @date 2013-12-2
 */
@Service(value = "dspInfoImpService")
public class DspInfoImpServiceImpl implements IDspInfoImpService {
	@Resource
	private IDspInfoDao dspInfoDao;
	@Resource
	private IDspConfigInfoDao dspConfigInfoDao;

	/**
	 * (non-Javadoc)
	 * <p>
	 * Title: getDspInfo
	 * </p>
	 * <p>
	 * Description:TODO
	 * </p>
	 * 
	 * @return
	 * @throws Exception
	 * @see cn.adwalker.ad.admin.operation.service.IDspInfoImpService#getDspInfo()
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<DspInfo> getDspInfo() throws Exception {
		List<DspInfo> list = dspInfoDao.findAll("SELECT * FROM T_DSP_INFO  ",
				new Object[] {}, DspInfo.class);
		return list;
	}

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
	 * @see cn.adwalker.ad.admin.operation.service.IDspInfoImpService#findList(cn.adwalker.ad.admin.operation.bean.DspInfobean,
	 *      cn.adwalker.core.page.IPageInfo)
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<DspInfoVo> findList(DspInfobean bean, IPageInfo pageInfo)
			throws Exception {

		List<DspInfoVo> list = null;
		List<Object> paramList = new ArrayList<Object>();
		StringBuilder sb = new StringBuilder(" t_dsp_info t where 1=1");
		if (bean != null) {

			if (ObjectUtils.isNotEmpty(bean.getDsp_name())) {
				sb.append(" and upper(t.dsp_name) like '%");
				sb.append(bean.getDsp_name().trim().toUpperCase());
				sb.append("%'");
			}
			if (ObjectUtils.isNotEmpty(bean.getDsp_id())) {
				sb.append(" and t.dsp_id=? ");
				paramList.add(bean.getDsp_id());
			}
			// 效果发生时间
			if (ObjectUtils.isNotEmpty(bean.getStart_time())) {
				sb.append(" and t.create_date >='" + bean.getStart_time() + "'");
			}
			if (ObjectUtils.isNotEmpty(bean.getEnd_time())) {
				sb.append(" and t.create_date <='" + bean.getEnd_time() + "'");
			}
		}
		list = (List<DspInfoVo>) dspInfoDao.findByPage("*", sb.toString(),
				paramList.toArray(), DspInfoVo.class, pageInfo);

		return list;

	}

	/**
	 * (non-Javadoc)
	 * <p>
	 * Title: save
	 * </p>
	 * <p>
	 * Description:TODO
	 * </p>
	 * 
	 * @param form
	 * @throws Exception
	 * @see cn.adwalker.ad.admin.operation.service.IDspInfoImpService#save(cn.adwalker.ad.admin.operation.form.DspInfoForm)
	 */
	@Override
	public void save(DspInfoForm form) throws Exception {
		if (form != null) {
			DspInfo dI = new DspInfo();
			dI.setBean(form.getBean());
			dI.setCreate_date(DateUtil.formatDate(new Date(), "yyyy-MM-dd"));
			dI.setDsp_desc(form.getDsp_desc());
			dI.setDsp_key(form.getDsp_key());
			dI.setDsp_name(form.getDsp_name());
			dI.setDsp_url(form.getDsp_url());
			dI.setService(form.getService());
			dI.setSource_str(form.getSource_str());
			dspInfoDao.insert(dI);
		}

	}

	/**
	 * (non-Javadoc)
	 * <p>
	 * Title: getDspDetail
	 * </p>
	 * <p>
	 * Description:TODO
	 * </p>
	 * 
	 * @param id
	 * @return
	 * @throws Exception
	 * @see cn.adwalker.ad.admin.operation.service.IDspInfoImpService#getDspDetail(java.lang.Long)
	 */
	@Override
	public DspInfoVo getDspDetail(Long id) throws Exception {
		DspInfoVo vo = new DspInfoVo();
		// dsp业务
		DspInfo dIv = dspInfoDao.findAdvInfo(id);
		vo.setBean(dIv.getBean());
		vo.setCreate_date(dIv.getCreate_date());
		vo.setDsp_desc(dIv.getDsp_desc());
		vo.setDsp_key(dIv.getDsp_key());
		vo.setDsp_name(dIv.getDsp_name());
		vo.setDsp_url(dIv.getDsp_url());
		vo.setService(dIv.getService());
		vo.setSource_str(dIv.getSource_str());
		vo.setDsp_id(dIv.getDsp_id());
		return vo;
	}

	/**
	 * (non-Javadoc)
	 * <p>
	 * Title: updateDspService
	 * </p>
	 * <p>
	 * Description:TODO
	 * </p>
	 * 
	 * @param form
	 * @throws Exception
	 * @see cn.adwalker.ad.admin.operation.service.IDspInfoImpService#updateDspService(cn.adwalker.ad.admin.operation.form.DspInfoForm)
	 */
	@Override
	public void updateDspService(DspInfoForm form) throws Exception {
		if (form != null) {
			DspInfo dI = new DspInfo();
			dI.setBean(form.getBean());
			dI.setDsp_desc(form.getDsp_desc());
			dI.setDsp_key(form.getDsp_key());
			dI.setDsp_name(form.getDsp_name());
			dI.setDsp_url(form.getDsp_url());
			dI.setService(form.getService());
			dI.setSource_str(form.getSource_str());
			dI.setDsp_id(form.getDsp_id());
			dspInfoDao.updateDspService(dI);
		}
	}

	/**
	 * (non-Javadoc)
	 * <p>
	 * Title: saveConfig
	 * </p>
	 * <p>
	 * Description:TODO
	 * </p>
	 * 
	 * @param form
	 * @throws Exception
	 * @see cn.adwalker.ad.admin.operation.service.IDspInfoImpService#saveConfig(cn.adwalker.ad.admin.operation.form.DspConfigInfoForm)
	 */
	@Override
	public void saveConfig(DspConfigInfoForm form) throws Exception {
		if (form != null) {
			DspConfigInfo dcI = new DspConfigInfo();
			dcI.setDsp_id(form.getDsp_id());
			dcI.setParam_type(form.getParam_type());
			dcI.setParam_name(form.getParam_name());
			dspConfigInfoDao.insert(dcI);
		}
	}

	/**
	 * (non-Javadoc)
	 * <p>
	 * Title: getConfigList
	 * </p>
	 * <p>
	 * Description:TODO
	 * </p>
	 * 
	 * @param dsp_id
	 * @return
	 * @throws Exception
	 * @see cn.adwalker.ad.admin.operation.service.IDspInfoImpService#getConfigList(java.lang.Long)
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<DspConfigInfoForm> getConfigList(Long dsp_id) throws Exception {

		List<DspConfigInfoForm> list = (List<DspConfigInfoForm>) dspConfigInfoDao
				.findAll("select * from t_dsp_config where dsp_id=?",
						new Object[] { dsp_id }, DspConfigInfoForm.class);
		return list;
	}

	/**
	 * (non-Javadoc)
	 * <p>
	 * Title: updateDspConfig
	 * </p>
	 * <p>
	 * Description:TODO
	 * </p>
	 * 
	 * @param id
	 * @param param_type
	 * @param param_name
	 * @throws Exception
	 * @see cn.adwalker.ad.admin.operation.service.IDspInfoImpService#updateDspConfig(java.lang.Long[],
	 *      java.lang.String[], java.lang.String)
	 */
	@SuppressWarnings("unchecked")
	@Override
	public void updateDspConfig(Long[] id, String[] param_type,
			String[] param_name) throws Exception {
		List<Object[]> param = new ArrayList<Object[]>();
		if (id != null && param_type != null && param_name != null) {
			for (int i = 0; i < id.length; i++) {
				param.add(new Object[] { param_type[i], param_name[i], id[i] });

			}
			dspConfigInfoDao
					.batchUpdate(
							"update t_dsp_config set param_type=?,param_name=? where id=?",
							param);
		}
	}

}
