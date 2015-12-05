package cn.adwalker.ad.config;

import java.util.ArrayList;
import java.util.List;

import cn.adwalker.core.util.lang.StringUtil;

public class Constant {

	/**
	 * 广告形式
	 */
	public final static int TYPE_WALL_SCORE = 0;

	public final static int TYPE_WALL_LIST = 1;

	public final static int TYPE_BANNER = 4;

	public final static int TYPE_CHARTBOOST = 5;

	public final static int APP_STATUS_TMP = 1;

	public final static int APP_STATUS_FOR_AUDIT = 2;

	public final static int APP_STATUS_NOT_PASS = -1;

	public final static int APP_STATUS_ON_LINE = 3;

	public final static int APP_STATUS_OFF_LINE = -2;

	public final static int APP_STATUS_ON_SUSPEND = 0;

	public final static int APP_STATUS_STOP = -3;

	/** 开发者帐号状态-初始化 */
	public final static int BLOCK_NOTUSED = 0;

	/** 开发者帐号状态-正常(激活) */
	public final static int BLOCK_USE = 1;

	/** 开发者帐号状态-封停 */
	public final static int BLOCK_CLOSED = 3;

	/** 开发者帐号状态-冻结 */
	public final static int BLOCK_FREEZE = 2;

	public static final String ROLE_CODE_SALES_MANAGER = "XIAOSHOUJINGLI";

	public static final String ROLE_CODE_SALES_MAN = "XIAOSHOUZHUANYUAN";

	private static List<String> ROLE_XIAOSHOU_LIST = new ArrayList<String>();

	static {
		ROLE_XIAOSHOU_LIST.add(ROLE_CODE_SALES_MANAGER);
		ROLE_XIAOSHOU_LIST.add(ROLE_CODE_SALES_MAN);

	}
	
	
	
	public static boolean isSeal(String roleCode){
		boolean b=false;
		if (!StringUtil.isEmpty(roleCode)&&ROLE_XIAOSHOU_LIST.contains(roleCode)) {
			b=true;
		}
		return b;
		
	}

}
