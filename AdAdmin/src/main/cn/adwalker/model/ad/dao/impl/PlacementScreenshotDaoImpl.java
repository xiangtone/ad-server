/**
 * 
 */
package cn.adwalker.model.ad.dao.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang.ArrayUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.stereotype.Repository;

import cn.adwalker.ad.config.AppConstant;
import cn.adwalker.core.repository.impl.BaseDaoImpl;
import cn.adwalker.model.ad.dao.IPlacementScreenshotDao;
import cn.adwalker.model.ad.domain.PlacementScreenshot;

/**
 * @author fangguanhong
 * 
 */
@Repository("placementScreenshotDao")
public class PlacementScreenshotDaoImpl extends BaseDaoImpl<PlacementScreenshot> implements IPlacementScreenshotDao {

	public PlacementScreenshotDaoImpl() {
		setTableName("T_PLACEMENT_SCREENSHOT");
	}

	/**
	 * 
	 * <p>
	 * Title: findByPlacement
	 * </p>
	 * <p>
	 * Description:获取投放正常的应用截图
	 * </p>
	 * 
	 * @param id
	 * @return
	 * @throws Exception
	 * @author cuidd
	 * @date 2013-5-8
	 * @return List<AdScreenshot>
	 * @version 1.0
	 */
	private List<PlacementScreenshot> findByPlacement(Long id) throws Exception {
		StringBuffer sql = new StringBuffer();
		sql.append(" select * from T_PLACEMENT_SCREENSHOT ");
		sql.append(" where PLACEMENT_ID='");
		sql.append(id);
		sql.append("' and del=" + AppConstant.DEL_NO);
		return this.jdbcTemplate.query(sql.toString(), new BeanPropertyRowMapper<PlacementScreenshot>(PlacementScreenshot.class));
	}

	/**
	 * (non-Javadoc)
	 * <p>
	 * Title: saveOrUpdate
	 * </p>
	 * <p>
	 * Description:保存或更新应用截图
	 * </p>
	 * 
	 * @param placement_id
	 * @param imageUrl
	 * @return
	 * @throws Exception
	 * @see cn.adwalker.model.ad.dao.IPlacementScreenshotDao#saveOrUpdate(java.lang.Long, java.lang.String[])
	 */
	@Override
	public Integer saveOrUpdate(Long placement_id, String imageUrl[]) throws Exception {
		if (imageUrl != null && imageUrl.length > 0) {

			List<PlacementScreenshot> list = findByPlacement(placement_id);
			List<PlacementScreenshot> delList = new ArrayList<PlacementScreenshot>();
			List<PlacementScreenshot> addList = new ArrayList<PlacementScreenshot>();
			// 判断是插入还是更新
			if (list != null && list.size() > 0) {
				for (PlacementScreenshot screenshot : list) {
					boolean del = true;
					for (int i = 0; i < imageUrl.length; i++) {
						if (!StringUtils.isEmpty(screenshot.getImg_url()) && !StringUtils.isEmpty(imageUrl[i]) && screenshot.getImg_url().equals(imageUrl[i])) {
							del = false;
							imageUrl = (String[]) ArrayUtils.remove(imageUrl, i);
							i = 0;
							break;
						}
					}
					if (del) {
						delList.add(screenshot);
					}
				}
				if (imageUrl.length > 0) {
					for (int i = 0; i < imageUrl.length; i++) {
						addList.add(getScreenshotByUrl(placement_id, imageUrl[i], i));
					}
				}
			} else {
				if (imageUrl.length > 0) {
					for (int i = 0; i < imageUrl.length; i++) {
						addList.add(getScreenshotByUrl(placement_id, imageUrl[i], i));
					}
				}
			}
			if (delList != null && delList.size() > 0) {
				StringBuilder sql = new StringBuilder();
				List<Object[]> parameters = new ArrayList<Object[]>();
				sql.append("update T_PLACEMENT_SCREENSHOT set DEL=? where id=?");
				for (PlacementScreenshot screenshot : delList) {
					parameters.add(new Object[] { AppConstant.DEL_YES, screenshot.getId() });
				}
				jdbcTemplate.batchUpdate(sql.toString(), parameters);
			}
			if (addList.size() > 0) {
				StringBuilder sql = new StringBuilder();
				List<Object[]> parameters = new ArrayList<Object[]>();
				sql.append("insert into T_PLACEMENT_SCREENSHOT(");
				sql.append("PLACEMENT_ID,");
				sql.append("IMG_URL,");
				sql.append("SORT,");
				sql.append("DEL,");
				sql.append("CREATE_TIME)");
				sql.append(" values(?,?,?,?,?)");
				for (PlacementScreenshot screenshot : addList) {
					parameters.add(new Object[] { screenshot.getPlacement_id(), screenshot.getImg_url(), screenshot.getSort(), screenshot.getDel(), screenshot.getCreate_time() });
				}
				jdbcTemplate.batchUpdate(sql.toString(), parameters);

			}
		}

		return null;
	}

	/**
	 * 
	 * <p>
	 * Title: getScreenshotByUrl
	 * </p>
	 * <p>
	 * Description:图片路径转换为对象
	 * </p>
	 * 
	 * @param placement_id
	 * @param url
	 * @return
	 * @author cuidd
	 * @date 2013-5-8
	 * @return AdScreenshot
	 * @version 1.0
	 */
	private PlacementScreenshot getScreenshotByUrl(Long placement_id, String url, Integer sort) {
		PlacementScreenshot adScreenshot = new PlacementScreenshot();
		adScreenshot.setDel(AppConstant.DEL_NO);
		adScreenshot.setCreate_time(new Date());
		adScreenshot.setImg_url(url);
		adScreenshot.setPlacement_id(placement_id);
		adScreenshot.setSort(sort);
		return adScreenshot;
	}

	/**
	 * (non-Javadoc)
	 * <p>
	 * Title: getByPlacement
	 * </p>
	 * 
	 * @param placement_id
	 * @return
	 * @throws Exception
	 * @see cn.adwalker.model.ad.dao.IPlacementScreenshotDao#getByPlacement(java.lang.Long)
	 */
	@Override
	public List<PlacementScreenshot> getByPlacement(Long placement_id) throws Exception {
		return findByPlacement(placement_id);
	}
}
