package com.yt.cms.service;

import java.util.List;

import com.yt.cms.model.Module;

/**
 * 模块接口定义
 * @author admin
 *
 */
public interface ModuleService {
	/**
	 * 新增模块
	 * @param module
	 * @return
	 */
	public boolean save(Module module);
	
	/**
	 * 按照id查询模块
	 * @param id
	 * @return
	 */
	public Module findById(Integer id);
	/**
	 * 列表显示模块信息
	 * @return
	 */
	public List<Module> queryAll();
	/**
	 * 更新模块信息
	 * @param module
	 * @return
	 */
	public boolean update(Module module);
	/**
	 * 按照module 参数查询
	 * @param module
	 * @return
	 */
	public List<Module> find(Module module);
}
