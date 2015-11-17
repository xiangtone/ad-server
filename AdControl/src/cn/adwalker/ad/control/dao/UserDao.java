package cn.adwalker.ad.control.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import cn.adwalker.ad.control.domain.User;
import cn.adwalker.ad.control.exception.DatabaseException;

@Repository("userDao")
public class UserDao extends BaseDao<User> {
	
	public UserDao() {
		setClazz(User.class);
	}
	
	public List<User> getUserList(String bdate,String sdate) throws DatabaseException{
		String sql = "SELECT UUID,tel_model,operator,os,app_id,openudid,idfv,imei,net_env,area_code,brand,imsi,area_province,create_time FROM  t_user_0 WHERE  create_time>='"+bdate+"' AND create_time<='"+sdate+"'"
				+" UNION ALL SELECT uuid,tel_model,operator,os,app_id,openudid,idfv,imei,net_env,area_code,brand,imsi,area_province,create_time FROM  t_user_1 WHERE  create_time>='"+bdate+"' AND create_time<='"+sdate+"'"
				+" UNION ALL SELECT uuid,tel_model,operator,os,app_id,openudid,idfv,imei,net_env,area_code,brand,imsi,area_province,create_time FROM  t_user_2 WHERE  create_time>='"+bdate+"' AND create_time<='"+sdate+"'"
				+" UNION ALL SELECT uuid,tel_model,operator,os,app_id,openudid,idfv,imei,net_env,area_code,brand,imsi,area_province,create_time FROM  t_user_3 WHERE  create_time>='"+bdate+"' AND create_time<='"+sdate+"'"
				+" UNION ALL SELECT uuid,tel_model,operator,os,app_id,openudid,idfv,imei,net_env,area_code,brand,imsi,area_province,create_time FROM  t_user_4 WHERE  create_time>='"+bdate+"' AND create_time<='"+sdate+"'"
				+" UNION ALL SELECT uuid,tel_model,operator,os,app_id,openudid,idfv,imei,net_env,area_code,brand,imsi,area_province,create_time FROM  t_user_5 WHERE  create_time>='"+bdate+"' AND create_time<='"+sdate+"'"
				+" UNION ALL SELECT uuid,tel_model,operator,os,app_id,openudid,idfv,imei,net_env,area_code,brand,imsi,area_province,create_time FROM  t_user_6 WHERE  create_time>='"+bdate+"' AND create_time<='"+sdate+"'"
				+" UNION ALL SELECT uuid,tel_model,operator,os,app_id,openudid,idfv,imei,net_env,area_code,brand,imsi,area_province,create_time FROM  t_user_7 WHERE  create_time>='"+bdate+"' AND create_time<='"+sdate+"'"
				+" UNION ALL SELECT uuid,tel_model,operator,os,app_id,openudid,idfv,imei,net_env,area_code,brand,imsi,area_province,create_time FROM  t_user_8 WHERE  create_time>='"+bdate+"' AND create_time<='"+sdate+"'"
				+" UNION ALL SELECT uuid,tel_model,operator,os,app_id,openudid,idfv,imei,net_env,area_code,brand,imsi,area_province,create_time FROM  t_user_9 WHERE  create_time>='"+bdate+"' AND create_time<='"+sdate+"'"
				+" UNION ALL SELECT uuid,tel_model,operator,os,app_id,openudid,idfv,imei,net_env,area_code,brand,imsi,area_province,create_time FROM  t_user_a WHERE  create_time>='"+bdate+"' AND create_time<='"+sdate+"'"
				+" UNION ALL SELECT uuid,tel_model,operator,os,app_id,openudid,idfv,imei,net_env,area_code,brand,imsi,area_province,create_time FROM  t_user_b WHERE  create_time>='"+bdate+"' AND create_time<='"+sdate+"'"
				+" UNION ALL SELECT uuid,tel_model,operator,os,app_id,openudid,idfv,imei,net_env,area_code,brand,imsi,area_province,create_time FROM  t_user_c WHERE  create_time>='"+bdate+"' AND create_time<='"+sdate+"'"
				+" UNION ALL SELECT uuid,tel_model,operator,os,app_id,openudid,idfv,imei,net_env,area_code,brand,imsi,area_province,create_time FROM  t_user_d WHERE  create_time>='"+bdate+"' AND create_time<='"+sdate+"'"
				+" UNION ALL SELECT uuid,tel_model,operator,os,app_id,openudid,idfv,imei,net_env,area_code,brand,imsi,area_province,create_time FROM  t_user_e WHERE  create_time>='"+bdate+"' AND create_time<='"+sdate+"'"
				+" UNION ALL SELECT uuid,tel_model,operator,os,app_id,openudid,idfv,imei,net_env,area_code,brand,imsi,area_province,create_time FROM  t_user_f WHERE  create_time>='"+bdate+"' AND create_time<='"+sdate+"'";
		return getAll(sql);
	}

}
