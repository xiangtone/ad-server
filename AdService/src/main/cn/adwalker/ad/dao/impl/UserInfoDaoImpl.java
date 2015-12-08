package cn.adwalker.ad.dao.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.stereotype.Repository;

import cn.adwalker.ad.cache.element.UserInfo;
import cn.adwalker.ad.dao.CommonDao;
import cn.adwalker.ad.dao.IUserInfoDao;
import cn.adwalker.ad.picker.statement.Statement;
import cn.adwalker.ad.picker.util.PickerUtil;

@Repository("userInfoDao")
public class UserInfoDaoImpl extends CommonDao implements IUserInfoDao {

	private static final Logger logger = LoggerFactory
			.getLogger(UserInfoDaoImpl.class);


	@Override
	public int insert(UserInfo userInfo) {
		StringBuffer sql = new StringBuffer();
		//sql.append(" insert into T_USER ");
		//sql.append(" (ID,UUID,IMEI,TEL_NUM,TEL_MODEL,NET_ENV,AREA_CODE,OPERATOR,SCREEN_WIDTH,SCREEN_HEIGH,SCORE,CREATE_TIME,os,brand,IMSI,APP_ID,PDA_TYPE,OPENUDID,IDFA,IDFV,JAILBROKEN) ");
		//sql.append(" values (SEQ_USER.NEXTVAL,:uuid,:imei,:telNum,:telModel,:netEnv,:areaCode,:operator,:screenWidth,:screenHeigh,:score,:createTime,:os,:brand,:imsi,:appId,:pdaType,:openUDID,:idfa,:idfv,:jailbroken) ");
		sql.append(" insert into  "+PickerUtil.tableName(userInfo.getUuid()));
		sql.append(" (UUID,IMEI,TEL_NUM,TEL_MODEL,NET_ENV,AREA_CODE,OPERATOR,SCREEN_WIDTH,SCREEN_HEIGH,SCORE,CREATE_TIME,os,brand,IMSI,APP_ID,PDA_TYPE,OPENUDID,IDFA,IDFV,JAILBROKEN) ");
		sql.append(" values (:uuid,:imei,:telNum,:telModel,:netEnv,:areaCode,:operator,:screenWidth,:screenHeigh,:score,:createTime,:os,:brand,:imsi,:appId,:pdaType,:openUDID,:idfa,:idfv,:jailbroken) ");
		logger.info(sql.toString());
		return simpleJdbcTemplate.update(sql.toString(),new BeanPropertySqlParameterSource(userInfo));
	}

	/**
	 * <p>
	 * Title: getUserInfo
	 * </p>
	 * <p>
	 * Description:TODO
	 * </p>
	 * 
	 * @param uuid
	 * @return
	 * @see cn.adwalker.ad.dao.IUserInfoDao#getUserInfo(java.lang.String)
	 */
	@Override
	public UserInfo getUserInfo(String uuid) {
		Statement stms = stmsFactory.createNativeStatement("select id,uuid,imei,tel_num,tel_model,net_env,area_code,operator,os,brand,screen_width,screen_heigh,pda_type,score,create_time,imsi,OPENUDID,IDFA,JAILBROKEN,IDFV from "+PickerUtil.tableName(uuid)+" t where t.uuid = ?");
		          stms.addParam(uuid);
		          List<UserInfo> list = simpleJdbcTemplate.queryList(stms, UserInfo.class);
		          return list.iterator().hasNext()?list.iterator().next():null;
	}

	/**
	 * (non-Javadoc)
	 * <p>
	 * Title: updateUserArea_Code
	 * </p>
	 * <p>
	 * Description:更新用户区域信息
	 * </p>
	 * 
	 * @param uuid
	 * @param string
	 * @see cn.adwalker.ad.dao.IUserInfoDao#updateUserArea_Code(java.lang.String,
	 *      java.lang.String)
	 */
	@Override
	public void updateUserArea_Code(String uuid, String city, String province) {
		// cuidd修改 AREA_CODE返回的数据部分不太合法产生异常，防止脚本注入，此处用预处理的方式 2013-03-20
		Statement stms = stmsFactory.createNativeStatement(" update "+PickerUtil.tableName(uuid)+" set AREA_CODE = ?,AREA_PROVINCE = ? where uuid=?");
		          stms.addParam(city,province,uuid);
		          simpleJdbcTemplate.update(stms);
		 /**
		StringBuffer sql = new StringBuffer();
		sql.append(" update T_USER set AREA_CODE = ?,AREA_PROVINCE = ?");
		sql.append(" where uuid = ?");
		simpleJdbcTemplate.update(sql.toString(), new Object[] { city,
				province, uuid });
         */
	}


	/**
	 * <p>
	 * Title: updateUserOs
	 * </p>
	 * <p>
	 * Description:更新用户的操作系统版本号 add by jief 2013-09-04
	 * </p>
	 * 
	 * @param uuid
	 * @param os
	 * @see cn.adwalker.ad.dao.IUserInfoDao#updateUserOs(java.lang.String,
	 *      String)
	 */
	@Override
	public void updateUserOs(String uuid, String os_version, String openUDID,String idfa, String idfv) {
		Statement stms = stmsFactory.createNativeStatement(" update "+PickerUtil.tableName(uuid)+" set os = ?,OPENUDID=? ,IDFA=?,IDFV=? where uuid = ?");
		          stms.addParam(os_version,openUDID,idfa,idfv,uuid);
		          simpleJdbcTemplate.update(stms);
		/**
		StringBuffer sql = new StringBuffer();
		sql.append(" update "+UserInfo.tableName(uuid)+" set os = ?,OPENUDID=? ,IDFA=?,IDFV=?");
		sql.append(" where uuid = ?");
		simpleJdbcTemplate.update(sql.toString(), new Object[] { os, openUDID,
				idfa, idfv, uuid });
        */
	}
}
