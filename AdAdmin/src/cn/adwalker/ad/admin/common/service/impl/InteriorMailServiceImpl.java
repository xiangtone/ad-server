package cn.adwalker.ad.admin.common.service.impl;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import cn.adwalker.ad.admin.app.vo.DeveloperVo;
import cn.adwalker.ad.admin.common.form.InteriorMailForm;
import cn.adwalker.ad.admin.common.service.IInteriorMailService;
import cn.adwalker.ad.admin.common.vo.InteriorMailVo;
import cn.adwalker.ad.admin.common.vo.SysUserVo;
import cn.adwalker.core.page.IPageInfo;
import cn.adwalker.core.util.lang.ObjectUtils;
import cn.adwalker.model.app.dao.IDeveloperDao;
import cn.adwalker.model.news.dao.IInteriorMailDevDao;
import cn.adwalker.model.news.dao.IInteriorMainDao;
import cn.adwalker.model.news.domain.InteriorMail;
import cn.adwalker.model.news.domain.InteriorMailDev;
import cn.adwalker.model.news.domain.NewsNoticedomain;

/**
* <p>Title: InteriorMailServiceImpl</p>
* <p>Description:站内信</p>
* <p>Company: adwalker</p> 
* @author   lichuang
* @date       2014-1-26
 */
@Service(value = "interiorMailService")
@Transactional
public class InteriorMailServiceImpl implements IInteriorMailService {

	@Resource
	private IInteriorMainDao  interiorMainDao;
	
	@Resource
	private IInteriorMailDevDao interiorMailDevDao;
	
	@Resource
	private IDeveloperDao developerDao;
	
	

	/**
	 * (non-Javadoc)
	 * <p>
	 * Title: findTitleList
	 * </p>
	 * <p>
	 * Description:TODO
	 * </p>
	 * 
	 * @param pageInfo
	 * @return
	 * @throws Exception
	 * @see cn.adwalker.ad.admin.common.service.emar.escore.platform.common.service.INewsNoticeService#findTitleList(com.emar.escore.core.page.IPageInfo)
	 */
	@SuppressWarnings("unchecked")
	@Transactional(propagation = Propagation.NEVER)
	@Override
	public List<InteriorMailVo> findTitleList(IPageInfo pageInfo)
			throws Exception {
		List<Object> list = new ArrayList<Object>();
		StringBuffer sb = new StringBuffer(" t_platform_interior_mail t where 1=1 ");
		return (List<InteriorMailVo>) interiorMainDao.findByPage("*",
				sb.toString(), list.toArray(), " order by t.UPDATE_TIME desc ",
				InteriorMailVo.class, pageInfo);
	}
	/**
	 * 
	 * <p>
	 * Title: convertToVo
	 * </p>
	 * <p>
	 * Description:实体转换成vo
	 * </p>
	 * 
	 * @param entity
	 * @return
	 * @author cuidd
	 * @date 2013-1-17
	 * @return NewsNoticeVo
	 * @version 1.0
	 * @throws
	 */
	private InteriorMailVo convertToVo(InteriorMail entity) {
		InteriorMailVo vo = null;
		if (entity != null) {
			vo = new InteriorMailVo();
			vo.setContent(entity.getContent());
			vo.setId(entity.getId());
			vo.setManagerId(entity.getManager_id());
			vo.setTitle(entity.getTitle());
			vo.setUpdate_time(entity.getUpdate_time());
			vo.setDev_id_str(entity.getDev_id_str());
		}

		return vo;

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
	 * @see cn.adwalker.ad.admin.common.service.emar.escore.platform.common.service.INewsNoticeService#findById(java.lang.Long)
	 */
	@Override
	public InteriorMailVo findById(Long id) {
		return convertToVo(interiorMainDao.findById(id));
	}

	/**
	 * (non-Javadoc)
	 * <p>
	 * Title: insert
	 * </p>
	 * <p>
	 * Description:TODO
	 * </p>
	 * 
	 * @param from
	 * @param manageUser
	 * @throws Exception
	 * @see cn.adwalker.ad.admin.common.service.emar.escore.platform.common.service.INewsNoticeService#insert(com.emar.escore.platform.common.form.NewsNotice,
	 *      cn.adwalker.ad.admin.common.vo.emar.escore.platform.common.vo.SysUserVo)
	 */
	@Override
	public void InteriorMailInsert(InteriorMailForm form, SysUserVo manageUser) throws Exception {
		InteriorMail im=new InteriorMail();
		if(ObjectUtils.isNotEmpty(form)){
			im.setTitle(form.getTitle());
			im.setContent(form.getContent());
			im.setStatus(form.getStatus());
			im.setDev_id_str(form.getDev_id_str());
			im.setUpdate_time(new Date());
			im.setCreate_time(new Date());
			im.setManager_id(manageUser.getId());
		}
		if(ObjectUtils.isNotEmpty(im)){
			InteriorMailDev imd=new InteriorMailDev();
			Long id = this.interiorMainDao.insert(im);
			imd.setInterior_id(id);
			imd.setStatus(0);
			
			if(im.getStatus()==2){
				if(im.getDev_id_str().equals("all")){
					//发送全部
					List<DeveloperVo> list=this.developerDao.getAppCount();
					for (int i = 0; i < list.size(); i++) {
						Long  dev_id=list.get(i).getDev_id();
						imd.setDev_id(dev_id);
						interiorMailDevDao.insert(imd);
					}
					
				}else{
					//发送部分
					String[] s = im.getDev_id_str().split(",");
					for (int i = 0; i < s.length-1; i++) {
						System.out.println("s="+Long.valueOf(s[i]));
						imd.setDev_id(Long.valueOf(s[i]));
						interiorMailDevDao.insert(imd);
					}
				}
			}
			
		}
	}

	/**
	 * (non-Javadoc)
	* <p>Title: update</p>
	* <p>Description:TODO</p>
	* @param from
	* @throws Exception
	* @see cn.adwalker.ad.admin.common.service.emar.escore.platform.common.service.IInteriorMailService#update(com.emar.escore.platform.common.form.NewsNotice)
	 */
	@Override
	public void updateInteriorMail(InteriorMailForm form,SysUserVo manageUser) throws Exception {
		InteriorMail im=new InteriorMail();
		if(ObjectUtils.isNotEmpty(form)){
			im.setId(form.getId());
			im.setTitle(form.getTitle());
			im.setContent(form.getContent());
			im.setStatus(form.getStatus());
			im.setDev_id_str(form.getDev_id_str());
			im.setUpdate_time(new Date());
			im.setManager_id(manageUser.getId());
		}
		if(ObjectUtils.isNotEmpty(im)){
			InteriorMailDev imd=new InteriorMailDev();
			this.interiorMainDao.update(im);
			imd.setInterior_id(im.getId());
			imd.setStatus(0);
			
			if(im.getStatus()==2){
				if(im.getDev_id_str().equals("all")){
					//发送全部
					List<DeveloperVo> list=this.developerDao.getAppCount();
					for (int i = 0; i < list.size(); i++) {
						Long  dev_id=list.get(i).getDev_id();
						imd.setDev_id(dev_id);
						interiorMailDevDao.insert(imd);
					}
					
				}else{
					//发送部分
					String[] s = im.getDev_id_str().split(",");
					for (int i = 0; i < s.length-1; i++) {
						imd.setDev_id(Long.valueOf(s[i]));
						interiorMailDevDao.insert(imd);
					}
				}
			}
			
		}
	}

	/**
	 * (non-Javadoc)
	 * <p>
	 * Title: delete
	 * </p>
	 * <p>
	 * Description:TODO
	 * </p>
	 * 
	 * @param id
	 * @throws ParseException
	 * @see cn.adwalker.ad.admin.common.service.emar.escore.platform.common.service.INewsNoticeService#delete(java.lang.Long)
	 */
	@Override
	public void delete(Long id) throws ParseException {
		NewsNoticedomain newsNotice = new NewsNoticedomain();
		newsNotice.setId(id);
		newsNotice.setDel(1);
		this.interiorMainDao.updateDel(newsNotice);
	}

	
}
