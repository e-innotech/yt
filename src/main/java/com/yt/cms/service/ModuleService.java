package com.yt.cms.service;

import java.util.List;

import com.yt.cms.model.Module;
import com.yt.cms.model.Resource;

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
	 * 按照module 参数查询
	 * 列表显示模块信息
	 * @return
	 */
	public List<Module> queryAll(Module module);
	/**
	 * 更新模块信息
	 * @param module
	 * @return
	 */
	public boolean update(Module module);
	/**
	 * 按照模块id删除模块
	 * @param id
	 * @return
	 */
	public boolean delete(Integer id);
	
	/**
	 * 按照parentId查询所有子模块
	 * @param parentId
	 * @return
	 */
	public List<Module> queryByParentId(Integer parentId);
	/**
	 * 查询模块id下所有的资源数据
	 * @param moduleId
	 * @return
	 */
	public List<Resource> queryResourcesByModuleId(Integer moduleId);
	
}
