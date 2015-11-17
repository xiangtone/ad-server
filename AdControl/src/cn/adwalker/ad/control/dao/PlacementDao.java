package cn.adwalker.ad.control.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import cn.adwalker.ad.control.domain.Placement;
import cn.adwalker.ad.control.exception.DatabaseException;
@Repository("placementDao")
public class PlacementDao extends BaseDao<Placement> {
	
	public PlacementDao() {
		setClazz(Placement.class);
	}
	
	public List<Placement> getPlacementList() throws DatabaseException {
		String sql = "SELECT id,name FROM t_placement";	
		return getAll(sql);
	}

	
	
}
