/**
 * <p>Title: UserAdRelDaoImpl.java</p>
 * <p>Description:TODO</p>
 * <p>Copyright: Copyright (c) </p>
 * <p>Company: adwalker</p>
 * @author www.adwalker.cn
 * @date 2013-5-16
 * @version 1.0
 */
package cn.adwalker.ad.dao.impl;

import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Repository;

import cn.adwalker.ad.bean.UserAddScore;
import cn.adwalker.ad.cache.element.UserAdRel;
import cn.adwalker.ad.cache.element.UserScore;
import cn.adwalker.ad.dao.CommonDao;
import cn.adwalker.ad.dao.IUserAdRelDao;
import cn.adwalker.ad.picker.statement.Statement;
import cn.adwalker.ad.picker.util.DateUtil;
import cn.adwalker.ad.picker.util.NumberUtil;

/**
 * <p>
 * Title: UserAdRelDaoImpl
 * </p>
 * <p>
 * Description:TODO
 * </p>
 * <p>
 * Company: adwalker
 * </p>
 * 
 * @author www.adwalker.cn
 * @date 2013-5-16
 */
@Repository("userAdRelDao")
public class UserAdRelDaoImpl extends CommonDao implements IUserAdRelDao {
	
	@Override
	public void updateUserAdRel(UserAdRel uar){
		Statement stms = stmsFactory.createNativeStatement("update T_USER_AD_REL t set t.SIGN_NUM = ? ,CREATE_TIME = NOW() where t.UUID = ? and t.AD_ID = ?");
		stms.addParam(uar.getSign_num(),uar.getUuid(),uar.getAd_id());
        simpleJdbcTemplate.update(stms);
	}
	
	@Override
	public void saveUserAdRel(UserAdRel uar,String ip,String ipduan,String imsi){
		Statement stms = stmsFactory.createNativeStatement("insert into T_USER_AD_REL (UUID,APP_ID,AD_ID,SIGN_NUM,CREATE_TIME,IP,IPDUAN,IMSI) values(?,?,?,?,NOW(),?,?,?)");
        stms.addParam(uar.getUuid(),uar.getApp_id(),uar.getAd_id(),uar.getSign_num(),ip,ipduan,imsi);
        simpleJdbcTemplate.update(stms);    
	}


	/**
	 * (non-Javadoc)
	 * <p>
	 * Title: getUserAdRel
	 * </p>
	 * <p>
	 * Description:TODO
	 * </p>
	 * 
	 * @param uuid
	 * @return
	 * @see cn.adwalker.ad.dao.IUserInfoDao#getUserAdRel(java.lang.String)
	 */
	@Override
	public List<UserAdRel> getUserAdRel(String uuid) {
		Statement stms = stmsFactory.createNativeStatement("select ID,UUID,AD_ID,CREATE_TIME,SIGN_NUM from t_user_ad_rel t where t.uuid = ? ");
		          stms.addParam(uuid);
		          return simpleJdbcTemplate.queryList(stms, UserAdRel.class);
		
		/**
		StringBuilder sql = new StringBuilder();
		sql.append(" select ID,UUID,AD_ID,CREATE_TIME,SIGN_NUM from t_user_ad_rel t where t.uuid = ? ");
		List<UserAdRel> list = simpleJdbcTemplate.query(sql.toString(),
				new BeanPropertyRowMapper<UserAdRel>(UserAdRel.class), uuid);
		return list;
	   */
	}
	
	@Override
	public UserAdRel findUserAdRelByUuidAndAdId(String uuid,Long adId){
		Statement stms = stmsFactory.createNativeStatement("SELECT t.ID,t.UUID,t.AD_ID,t.CREATE_TIME,t.SIGN_NUM FROM t_user_ad_rel t WHERE t.uuid=? AND t.ad_id=?");
	              stms.addParam(uuid,adId);
	              return simpleJdbcTemplate.findObject(stms, UserAdRel.class);
	}
	
	@Override
	public Integer findUserAdRelCount(String uuid,Date beginDate,Date endDate){
		Statement stms = stmsFactory.createNativeStatement("SELECT COUNT(*) FROM t_user_ad_rel t WHERE t.uuid=? and t.create_time>=?");
		          stms.addParam(uuid,DateUtil.get00HourDate(new Date()));
		          Integer count = simpleJdbcTemplate.queryCount(stms);
		        return NumberUtil.getInteger(count, 0);
	}
	@Override
	public Integer findUserAdRelCountByImsiAdId(String imsi,Long adId){
		Statement stms = stmsFactory.createNativeStatement("SELECT COUNT(*) FROM t_user_ad_rel t WHERE t.imsi=? and t.ad_id=?");
		          stms.addParam(imsi,adId);
		          Integer count = simpleJdbcTemplate.queryCount(stms);
		          return NumberUtil.getInteger(count, 0);
	}
	
	@Override
	public Integer findUserAdRelCountByIpduanAdId(String ipduan,Long adId){
		Statement stms = stmsFactory.createNativeStatement("SELECT COUNT(*) FROM t_user_ad_rel t WHERE t.ipduan=? and t.ad_id=?");
        stms.addParam(ipduan,adId);
        Integer count = simpleJdbcTemplate.queryCount(stms);
        return NumberUtil.getInteger(count, 0);
	}
	
	//查询当天下载广告list
	@Override
	public List<UserAddScore> queryCurrentUserAddScore(String uuid,Long appId){
		Statement stms = stmsFactory.createNativeStatement("SELECT t.uuid,t.app_id,t.ad_id,t.score,t.stat_date FROM t_user_addscore t WHERE t.uuid=? AND t.app_id=? and t.stat_date>=?");
		          //stms.appendBetween("and", "t.stat_date", DateUtil.get00HourDate(new Date()), DateUtil.get24HourDate(new Date()));
		          stms.addParam(uuid,appId,DateUtil.get00HourDate(new Date()));		         
		return simpleJdbcTemplate.queryList(stms, UserAddScore.class);
	}
	
	@Override
	public UserScore findUserScore(String uuid,Long appId){
		Statement stms = stmsFactory.createNativeStatement("SELECT t.* FROM t_user_score t WHERE t.uuid=? AND t.app_id=?");
		          stms.addParam(uuid,appId);
	    return 	simpleJdbcTemplate.findObject(stms, UserScore.class);
	}
}
