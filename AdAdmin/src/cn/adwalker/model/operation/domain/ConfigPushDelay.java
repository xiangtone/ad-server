/**
 * 
 */
package cn.adwalker.model.operation.domain;

/**
 * @author wjp 行云广告设置表实体
 */
public class ConfigPushDelay {

	/** 主键编号 */
	private Long id;

	/** 设置类型  ‘AUTOPUSHDELAY’自动推送间隔  ‘MANUALPUSHDELAY’手动推送间隔  ‘FINANCETAX’财务扣税 */
	private String configType;
	
	/**配置项值*/
	private String configValue;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	public ConfigPushDelay(){};
	public ConfigPushDelay(String configType,String configValue){
//		this.id=id;
		this.configType=configType;
		this.configValue=configValue;
	}
	
	public String getConfigType() {
		return configType;
	}

	public void setConfigType(String configType) {
		this.configType = configType;
	}

	public String getConfigValue() {
		return configValue;
	}

	public void setConfigValue(String configValue) {
		this.configValue = configValue;
	}
}
