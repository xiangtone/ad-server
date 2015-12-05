package cn.adwalker.core.pool;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Service;

import cn.adwalker.ad.config.AppConstant;
import cn.adwalker.model.common.dao.ISysSortDao;
import cn.adwalker.model.common.domain.SysCategory;
import cn.adwalker.model.common.domain.Sort;
import cn.adwalker.model.config.dao.IConfigAlarmDao;
import cn.adwalker.model.config.dao.IConfigEScoreDao;
import cn.adwalker.model.config.domain.ConfigEScore;
import cn.adwalker.model.config.domain.ConfigFinance;
import cn.adwalker.model.config.domain.ConfigSDK;
import cn.adwalker.model.sys.dao.ISysResourceDao;
import cn.adwalker.model.sys.domain.SysResource;
import cn.adwalker.model.sys.domain.SysPurview;

/**
 * spring初始化pool
 * 
 * @author gary
 * 
 */
@Service("springDatePool")
public class SpringDatePool implements InitializingBean {

	private static final Logger log = LoggerFactory
			.getLogger(SpringDatePool.class);

	@Resource
	private IConfigEScoreDao configEScoreDao;

	@Resource
	private IConfigAlarmDao configAlarmDao;

	@Resource
	private ISysSortDao sysSortDao;

	@Resource
	private ISysResourceDao sysResourceDao;

	/** 权限子菜单 */
	private Set<String> purviewUrlSet;
	private ConfigEScore platformConfig;// 平台设置
	private ConfigFinance financeConfig; // 财务设置

	private ConfigSDK sdkConfig;

	private List<SysCategory> ecList;

	private List<Sort> bigSortList;

	private Map<String, SysResource> resMap;

	@Override
	public void afterPropertiesSet() throws Exception {
		init();// 初始化
	}

	private void init() throws Exception {
		log.info("SpringDatePool init..");
		long start = System.currentTimeMillis();

		purviewUrlSet = new HashSet<String>();
		initPurviewUrlSet();
		initPlatformConfig();
		initFinanceConfig();
		initEscoreCategory();
		initBigSort();
		initResource();
		initSDKConfig();

		log.info("SpringDatePool end.. cost time:"
				+ (System.currentTimeMillis() - start));
	}

	public void initResource() {
		if (resMap == null) {
			resMap = new HashMap<String, SysResource>();
		}
		try {
			List<SysResource> list = this.sysResourceDao.findAll();
			for (SysResource sysResource : list) {
				resMap.put(sysResource.getUrl(), sysResource);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void initEscoreCategory() {
		ecList = sysSortDao.findECList();
	}

	public void initSDKConfig() throws Exception {
		sdkConfig = configAlarmDao.getConfig();
	}

	public void initBigSort() {
		try {
			bigSortList = sysSortDao.getAdCategory();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 初始化方法
	 */

	/**
	 * 初始化平台设置
	 */
	public void initPlatformConfig() {
		platformConfig = configEScoreDao.getPlatformConfig();
	}

	/**
	 * 初始化财务设置
	 */
	public void initFinanceConfig() {
		financeConfig = configEScoreDao
				.getFinanceConfig(AppConstant.FINANCE_ONLINE_STATUS);
	}

	private void initPurviewUrlSet() {
		try {
			List<SysPurview> list = sysResourceDao.findSub();
			for (SysPurview bean : list) {
				purviewUrlSet.add(bean.getUrl());
			}
		} catch (Exception e) {
			e.printStackTrace();
			log.info("菜单高亮过滤器 - 获取权限菜单异常！");
		}
	}

	/**
	 * 对外接口获取权限菜单
	 * 
	 * @return
	 */
	public Set<String> getPurviewUrlLSet() {
		return purviewUrlSet;
	}

	public boolean urlIsExist(String url) {
		if (!StringUtils.isEmpty(url)) {
			if (purviewUrlSet.contains(url)) {
				return true;
			}
		}
		return false;
	}

	/**
	 * 获取平台配置文件
	 * 
	 * @return
	 */
	public ConfigEScore getPlatformConfig() {
		return platformConfig;
	}

	/**
	 * 获取财务设置文件
	 * 
	 * @return
	 */
	public ConfigFinance getFinanceConfig() {
		return financeConfig;
	}

	public List<Sort> getBigSort() {
		return bigSortList;
	}

	public List<SysCategory> getSysCategory() {
		return ecList;
	}

	public ConfigSDK getSDKConfig() {
		return sdkConfig;
	}

	public SysResource getResource(String url) {
		SysResource r = null;
		if (!StringUtils.isEmpty(url)) {
			r = resMap.get(url);
		}
		return r;
	}

}
