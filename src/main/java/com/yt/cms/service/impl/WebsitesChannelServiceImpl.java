//package com.yt.cms.service.impl;
//
//import java.util.ArrayList;
//import java.util.List;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//import com.yt.cms.common.CollectionUtils;
//import com.yt.cms.mapper.WebsitesChannelMapper;
//import com.yt.cms.model.WebsitesChannel;
//import com.yt.cms.service.WebsitesChannelService;
//@Service
//public class WebsitesChannelServiceImpl implements WebsitesChannelService {
//	@Autowired
//	private WebsitesChannelMapper websitesChannelDAO;
//	
//	@Override
//	public boolean save(List<WebsitesChannel> moduleList) {
//		try {
//			websitesChannelDAO.insertBatch(moduleList);
//			return true;
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//		return false;
//	}
//
//	@Override
//	public boolean update(Integer websitesId, Integer[] old_channelIds, Integer[] new_channelIds) {
//		// 在old中找出不在new 中的元素
//		try {
//			List<Integer> delList = CollectionUtils.find(old_channelIds, new_channelIds);
//			// 在new中找出不在old 中的元素
//			List<Integer> addList = CollectionUtils.find(new_channelIds, old_channelIds);
//					
//			// 删除解除关系的数据
//			if(CollectionUtils.isNotEmpty(delList)) {
//				websitesChannelDAO.deleteByWebsitesId(websitesId, delList);
//			}
//			// 新增新建立关系的数据
//			List<WebsitesChannel> moduleList = new ArrayList<WebsitesChannel>();
//			for(Integer id : addList) {
//				WebsitesChannel bar = new WebsitesChannel();
//				bar.setChannelId(id);
//				bar.setWebsitesId(websitesId);
//				moduleList.add(bar);
//			}
//			if(CollectionUtils.isNotEmpty(moduleList)) {
//				websitesChannelDAO.insertBatch(moduleList);
//			}
//			return true;
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//		return false;
//	}
//
//	
//
//
//}
