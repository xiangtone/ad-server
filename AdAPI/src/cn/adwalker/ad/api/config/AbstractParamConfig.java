package cn.adwalker.ad.api.config;

import cn.adwalker.ad.util.ConfigUtil;
public abstract class AbstractParamConfig implements IParamConfig {
	protected final String CALL_BACK_URL=ConfigUtil.getString("check.callback");
	protected static final String  SOURCE="adwalker";
}
