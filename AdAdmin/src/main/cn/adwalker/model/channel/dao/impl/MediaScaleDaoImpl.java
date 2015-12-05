package cn.adwalker.model.channel.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import cn.adwalker.core.repository.impl.BaseDaoImpl;
import cn.adwalker.model.channel.dao.IMediaScaleDao;
import cn.adwalker.model.channel.domain.MediaScale;

/**
 * 
 * <p>
 * Title: MediaScaleDaoImpl
 * </p>
 * <p>
 * Company: adwalker
 * </p>
 * 
 * @author cuidd
 * @date 2013-7-17
 */
@Repository
public class MediaScaleDaoImpl extends BaseDaoImpl<MediaScale> implements IMediaScaleDao {

	private static Map<String, Double> mediaScale = null;

	/**
	 * 
	 * <p>
	 * Title:
	 * </p>
	 */
	public MediaScaleDaoImpl() {
		super();
		this.setTableName("T_CHANNEL");
	}

	@Override
	public void updateChannelRating(Long channelId, Long campaign_id, Long media_id, Double score) throws Exception {
		String sql = "update T_MEDIA_SCALE  set SCALE=? where ID=?";
		jdbcTemplate.update(sql, new Object[] { score, channelId });
		MediaScale m = new MediaScale();
		m.setCampaign_id(campaign_id);
		m.setMedia_id(channelId);
		m.setId(channelId);
		m.setMedia_id(media_id);
		m.setScale(score);
		if (mediaScale == null) {
			mediaScale = loadMediaScale();
		}
		mediaScale.put(media_id + "_" + campaign_id, score);
	}

	/**
	 * (non-Javadoc)
	 * <p>
	 * Title: getMediaScale
	 * </p>
	 * 
	 * @param media_id
	 * @param camplaign_id
	 * @return
	 * @throws Exception
	 * @see cn.adwalker.model.channel.dao.IMediaScaleDao#getMediaScale(java.lang.Long, java.lang.Long)
	 */
	@Override
	public Double getMediaScale(Long media_id, Long camplaign_id) throws Exception {
		Double d = 1d;
		if (media_id != null && camplaign_id != null) {
			if (mediaScale == null) {
				mediaScale = loadMediaScale();
			}
			if (mediaScale.get(media_id + "_" + camplaign_id) != null) {
				d = mediaScale.get(media_id + "_" + camplaign_id);
			}
		}

		return d;
	}

	@SuppressWarnings("unchecked")
	private Map<String, Double> loadMediaScale() throws Exception {
		Map<String, Double> map = new HashMap<String, Double>();
		List<MediaScale> list = (List<MediaScale>) this.findAll("select s.*,rel.CAMPAIGN_ID from T_MEDIA_SCALE s  LEFT JOIN  T_CAMPAIGN_PLACEMENT_REL rel on  s.PLACEMENT_ID=rel.PLACEMENT_id  where SCALE is not null", MediaScale.class);
		if (list != null && list.size() > 0) {
			for (MediaScale s : list) {
				map.put(s.getMedia_id() + "_" + s.getCampaign_id(), s.getScale());
			}
		}
		return map;
	}

}