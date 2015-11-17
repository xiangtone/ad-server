package cn.adwalker.ad.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import cn.adwalker.ad.dao.CommonDao;
import cn.adwalker.ad.dao.IMediaUserDao;
import cn.adwalker.ad.dao.domain.MediaUser;
import cn.adwalker.ad.picker.statement.Statement;

@Repository
public class MediaUserDaoImpl extends CommonDao implements IMediaUserDao {
	
	@Override
	public String findMediaUser(String uuid, String appId) {
		Statement stms = stmsFactory.createNativeStatement("select t.string from t_cooperation_config t where t.uuid=? and app_id= ?"); 
		          stms.addParam(uuid,appId);
		          //stms.addOrderBy(" t.uptime desc ");
		          return simpleJdbcTemplate.findForObject(stms, String.class);
		          //List<String> list = simpleJdbcTemplate.queryList(stms, String.class);
		          //System.out.println(list.iterator().hasNext());
		          //return list.iterator().hasNext()?list.iterator().next():null;
	}
	
	@Override
	public void initMediaUser(String uuid, String devUserId, String appId,String os) {		
		Statement s = stmsFactory.createNativeStatement("select t.* from t_cooperation_config t where t.uuid=? and t.string=?");
		          s.addParam(uuid,devUserId);
		if(!simpleJdbcTemplate.queryList(s, Object.class).iterator().hasNext()){
			Statement stms = stmsFactory.createNativeStatement("insert into t_cooperation_config  (uuid,string,uptime,app_id,os) values (?,?,NOW(),?,?) ");
	        stms.addParam(uuid, devUserId, appId,os);
	        simpleJdbcTemplate.update(stms);
		}
	}

	@Override
	public MediaUser  findMediaUser(String media_user_id,Long appid) {
		Statement stms = stmsFactory.createNativeStatement("select t.uuid,t.string,t.app_id from t_cooperation_config t where t.string=? and t.app_id=? limit 0,1 "); 
        stms.addParam(media_user_id,appid);
        //stms.addOrderBy(" t.uptime desc ");
        List<MediaUser> list=simpleJdbcTemplate.queryList(stms, MediaUser.class);
        return (list!=null&&list.size()>0)?list.get(0):null;
	}

	@Override
	public MediaUser findMediaUserByOs(String media_user_id, String os) {
		Statement stms = stmsFactory.createNativeStatement("select t.uuid,t.string,t.app_id from t_cooperation_config t,t_application app where t.app_id=app.id and  t.string=? and os=? limit 0,1 "); 
        stms.addParam(media_user_id,os);
        List<MediaUser> list=simpleJdbcTemplate.queryList(stms, MediaUser.class);
        return (list!=null&&list.size()>0)?list.get(0):null;
	}
	
	
	
	
	
	

}
