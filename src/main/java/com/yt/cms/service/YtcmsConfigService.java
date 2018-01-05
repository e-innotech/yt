package com.yt.cms.service;

import java.util.List;

import com.yt.cms.common.Page;
import com.yt.cms.model.YtcmsConfig;

/**
 * 系统配置表配置添加，修改，列表查询
 * 系统配置接口定义
 * @author admin
 *
 */
public interface YtcmsConfigService {
	/**
	 * 新增配置
	 * @param config
	 * @return
	 */
	public boolean save(YtcmsConfig config);
	
	/**
	 * 按照id查询配置
	 * @param id
	 * @return
	 */
	public YtcmsConfig findById(Integer id);
	/**
	 * 按照config 参数查询
	 * 列表显示配置信息
	 * @return
	 */
	public List<YtcmsConfig> queryAll(YtcmsConfig config,Page page);
	
	/**
	 * 按照config 参数查询
	 * @return
	 */
	public long queryCount(YtcmsConfig config);
	/**
	 * 更新配置信息
	 * @param config
	 * @return
	 */
	public boolean update(YtcmsConfig config);
	
}
