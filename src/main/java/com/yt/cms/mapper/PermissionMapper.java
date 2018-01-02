package com.yt.cms.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.yt.cms.model.Button;
import com.yt.cms.model.Menu;
import com.yt.cms.model.User;

public interface PermissionMapper {
	List<Menu> queryMenu();

	List<Integer> queryMenuByUserGroupId(Integer userGroupId);

	List<Button> queryMenuButtonByUserGroupId(@Param("userGroupId") Integer userGroupId,
			@Param("menuId") Integer menuId);

	List<Button> queryMenuButton(@Param("menuId") Integer menuId);
	
	  /**
     * 查询用户组的角色名
     * @param userGroupId
     * @return
     */
    String queryRolesName(Integer userGroupId);
    
    User getUserPermissionInfo(Integer userId);
}