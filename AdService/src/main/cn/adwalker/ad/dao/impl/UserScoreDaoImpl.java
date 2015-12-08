/**
* <p>Title: UserScoreDaoImpl.java</p>
* <p>Description:TODO</p>
* <p>Copyright: Copyright (c) </p>
* <p>Company: adwalker</p>
* @author www.adwalker.cn
* @date 2013-4-23
* @version 1.0
*/
package cn.adwalker.ad.dao.impl;

import org.springframework.stereotype.Repository;

import cn.adwalker.ad.bean.UserAddScore;
import cn.adwalker.ad.cache.element.UserScore;
import cn.adwalker.ad.dao.CommonDao;
import cn.adwalker.ad.dao.IUserScoreDao;
import cn.adwalker.ad.picker.statement.Statement;

/**
 * <p>Title: UserScoreDaoImpl</p>
 * <p>Description:TODO</p>
 * <p>Company: adwalker</p> 
 * @author    www.adwalker.cn
 * @date       2013-4-23
 */
@Repository("userScoreDao")
public class UserScoreDaoImpl extends CommonDao implements IUserScoreDao {

	
	/**  
	* <p>Title: getUuidAppScore</p>
	* <p>Description:TODO</p>
	* @param uuid
	* @param appId
	* @return
	* @see cn.adwalker.ad.dao.IUserScoreDao#getUuidAppScore(java.lang.String, long)
	*/
	@Override
	public UserScore getUuidAppScore(String uuid, long appId) {
		Statement stms = stmsFactory.createNativeStatement("select * from T_USER_SCORE t where t.UUID = ? and t.APP_ID = ?");
		          stms.addParam(uuid,appId);
		return simpleJdbcTemplate.findObject(stms, UserScore.class);
		/**
		StringBuffer sql = new StringBuffer();
		sql.append(" select * from T_USER_SCORE t where t.UUID = ? and t.APP_ID = ?");		
		
		List<UserScore> list = simpleJdbcTemplate.query(sql.toString(), new BeanPropertyRowMapper<UserScore>(UserScore.class),uuid,appId);
		if (list != null && list.size() != 0) {
			return list.get(0);
		}else{
			return null;
		}*/
	}

	
	
	/**
	* <p>Title: insertUserScore</p>
	* <p>Description:TODO</p>
	* @param userScore
	* @see cn.adwalker.ad.dao.IUserScoreDao#insertUserScore(cn.adwalker.ad.cache.element.UserScore)
	*/
	@Override
	public void insertUserScore(UserScore userScore) {
		Statement stms =  stmsFactory.createNativeStatement("insert into T_USER_SCORE (UUID,create_time,APP_ID,SCORE) values (?,NOW(),?,?)");
		          stms.addParam(userScore.getUuid(),userScore.getApp_id(),userScore.getScore());
		simpleJdbcTemplate.update(stms);
	}
	
	public void insertUserAddScore(UserAddScore uas){
		Statement stms =  stmsFactory.createNativeStatement("insert  into `t_user_addscore`(`uuid`,`ad_id`,`app_id`,`score`,`stat_date`) values (?,?,?,?,NOW());");
        stms.addParam(uas.getUuid(),uas.getAd_id(),uas.getApp_id(),uas.getScore());
        simpleJdbcTemplate.update(stms);
	}

	
	
	/** 
	* <p>Title: updateUserScore</p>
	* <p>Description:TODO</p>
	* @param userScore
	* @see cn.adwalker.ad.dao.IUserScoreDao#updateUserScore(cn.adwalker.ad.cache.element.UserScore)
	*/
	@Override
	public void updateUserScore(UserScore userScore) {
		Statement stms = stmsFactory.createNativeStatement("update T_USER_SCORE t set t.SCORE = ? where UUID = ? and APP_ID = ?");
		          stms.addParam(userScore.getScore(),userScore.getUuid(),userScore.getApp_id());
		          simpleJdbcTemplate.update(stms);
		/**
		StringBuffer sql = new StringBuffer();
		sql.append(" update T_USER_SCORE t set t.SCORE = ? where UUID = ? and APP_ID = ? ");
		simpleJdbcTemplate.update(sql.toString(),userScore.getScore(),userScore.getUuid(),userScore.getApp_id());
	    */
	}

}
