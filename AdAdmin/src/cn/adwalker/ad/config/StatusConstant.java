package cn.adwalker.ad.config;

public class StatusConstant {

	/**
	 * 广告类型平台
	 */
	public final static int AD_TYPE_PLATFORM = 0;

	/**
	 * 广告类型渠道
	 */
	public final static int AD_TYPE_CHANNEL = 1;

	// -40：草稿、-30：超量下线;-20：投放结束;-10、暂停；-1：人工下线;0:初始化；1：上线
	/**
	 * 上线
	 */
	public final static int AD_STATUS_ONLINE = 1;

	/**
	 * 初始化
	 */
	public final static int AD_STATUS_INIT = 0;

	/**
	 * 下线
	 */
	public final static int AD_STATUS_OFFLINE = -1;

	/**
	 * 活动结束
	 */
	public final static int AD_STATUS_OVER = -5;

	/**
	 * 暂停
	 */
	public final static int AD_STATUS_PAUSE = -10;

	/**
	 * 定时下线
	 */
	public final static int AD_STATUS_OVER_TIME = -20;

	/**
	 * 超量
	 */
	public final static int AD_STATUS_OVER_AMOUNT = -30;
	
	/**
	 * 草稿
	 */
	public final static int AD_STATUS_FOR_ADUIT = -35;

	/**
	 * 草稿
	 */
	public final static int AD_STATUS_OVER_DRAFT = -40;

	/**
	 * 活动草稿
	 */
	public final static int CAMPAIGN_STATUS_OVER_DRAFT = -50;

	/**
	 * 投放审核
	 */
	public final static int CAMPAIGN_STATUS_ADUIT = -40;

	/**
	 * 待投放
	 */
	public final static int CAMPAIGN_STATUS_TO_PLACEMENT = -30;

	/**
	 * 投放草稿
	 */
	public final static int CAMPAIGN_STATUS_PLACEMENT_CAOGAO = -20;

	/**
	 * 投放草稿2
	 */
	public final static int CAMPAIGN_STATUS_PLACEMENT_CAOGAO_STEP_TOW = -19;

	public final static int CAMPAIGN_STATUS_PLACEMENT_CAOGAO_STEP_THREE = -18;

	/**
	 * 投放待审核
	 */
	public final static int CAMPAIGN_STATUS_PLACEMENT_AUDIT = -10;

	/**
	 * 上线
	 */
	public final static int CAMPAIGN_STATUS_PLACEMENT_ON_LINE = 1;

	/**
	 * 下线
	 */
	public final static int CAMPAIGN_STATUS_PLACEMENT_OFF_LINE = -1;

	public static String getCampaignStatusName(Integer status) {
		String s = null;
		if (status != null) {
			if (status == CAMPAIGN_STATUS_OVER_DRAFT) {
				s = "活动草稿";
			} else if (status == CAMPAIGN_STATUS_ADUIT) {
				s = "活动待审核";
			} else if (status == CAMPAIGN_STATUS_TO_PLACEMENT) {
				s = "待投放";
			} else if ((status == CAMPAIGN_STATUS_PLACEMENT_CAOGAO)
					|| (status == CAMPAIGN_STATUS_PLACEMENT_CAOGAO_STEP_TOW)
					|| (status == CAMPAIGN_STATUS_PLACEMENT_CAOGAO_STEP_THREE)) {
				s = "投放草稿";
			} else if (status == CAMPAIGN_STATUS_PLACEMENT_AUDIT) {
				s = "投放待审核";
			} else if (status == CAMPAIGN_STATUS_PLACEMENT_ON_LINE) {
				s = "已发布";
			} else if (status == CAMPAIGN_STATUS_PLACEMENT_OFF_LINE) {
				s = "活动结束";
			}
		}
		return s;

	}

	/** 应用审核状态-待提交 */
	public final static int APP_STATUS_INIT = 1;

	/** 应用审核状态-daishenhe */
	public final static int APP_STATUS_FOR_AUDTI = 2;

	/** 应用审核状态-未通过 */
	public final static int APP_STATUS_NOTPASS = -1;

	/** 应用审核状态-上线 */
	public final static int APP_STATUS_ONLINE = 3;

	/** 应用审核状态-下线 */
	public final static int APP_STATUS_OFFLINE = -2;

	
	
	
	public static String getAppStatusName(Integer status) {
		String s = null;
		if (status != null) {
			if (status == APP_STATUS_INIT) {
				s = "待提交";
			} else if (status == APP_STATUS_FOR_AUDTI) {
				s = "待审核";
			} else if (status == APP_STATUS_NOTPASS) {
				s = "未通过 ";
			} else if (status == APP_STATUS_ONLINE) {
				s = "上线";
			} else if (status == APP_STATUS_OFFLINE) {
				s = "下线";
			}
		}
		return s;

	}

	/** 应用状态-是否加入黑名单 */
	public final static int SHIT_LIST = 1;
	/** 活动状态（-1:全部、0:草稿、1:待审核、2:已审核、3:下线） */
	public final static Integer ACTIVITY_STATUS_Z = 0;
	/** 活动状态（-1:全部、0:草稿、1:待审核、2:已审核、3:下线） */
	public final static Integer ACTIVITY_STATUS = 1;
	/** 活动状态（-1:全部、0:草稿、1:待审核、2:已审核、3:下线） */
	public final static Integer ACTIVITY_STATUS_T = 2;
	/** 活动状态（-1:全部、0:草稿、1:待审核、2:已审核、3:下线） */
	public final static Integer ACTIVITY_STATUS_BELOW = 3;

	/** 渠道状态(1：激活、2：封号、) */
	public final static Integer CHANNEL_STATUS_F = 1;
	/** 渠道状态(1：激活、2：封号、) */
	public final static Integer CHANNEL_STATUS_T = -10;

	/** 渠道状态(1：激活、2：封号、) */
	public final static Integer CHANNEL_STATUS_DEL = -1;

	/** 渠道状态(3：财务类型、) */
	public final static Integer CHANNEL_B = 3;
	/** 确认数据导入已发布状态 */
	public final static Integer ADV_STATUS_PUBLISH = 1;
	/** 确认收入为发布的状态 */
	public final static Integer INCOME_STATUS_F = 1;
	/** 确认收入为发布的状态 */
	public final static Integer INCOME_STATUS_T = 2;
	/** 广告主数据为已分数 */
	public final static Integer FRACTION_STATUS_F = 2;
}
