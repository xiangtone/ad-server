package cn.adwalker.ad.util;

public class StatusConstant {

	/** 应用审核状态-草稿 */
	public final static int STATUS_UNAPPLY = 1;

	/** 应用审核状态-待审核 */
	public final static int STATUS_UNCHECK = 2;

	/** 应用审核状态-上线 */
	public final static int STATUS_ONLINE = 3;

	/** 应用审核状态-未通过 */
	public final static int STATUS_NOTPASS = -1;

	/** 应用审核状态-暂停  */
	public final static int STATUS_PAUSE = 0;
	
	/** 应用审核状态-下线 */
	public final static int STATUS_OFFLINE = -2;

	/** 应用审核状态-终止 */
	public final static int STATUS_STOP = -3;
	
	/** 开发者状态-初始化 */
	public final static int DEVELOPER_STATUS_INIT = 0;
	
	/** 开发者状态-正常*/
	public final static int DEVELOPER_STATUS_NORMAL = 1;
	
	/** 开发者状态-冻结*/
	public final static int DEVELOPER_STATUS_FROST = 2;
	
	/** 开发者状态-封号 */
	public final static int DEVELOPER_STATUS_SEALING = 3;
	
}
