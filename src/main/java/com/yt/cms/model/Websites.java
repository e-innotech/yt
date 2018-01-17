package com.yt.cms.model;

import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.Min;

import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.URL;

//import com.fasterxml.jackson.annotation.JsonIgnore;

public class Websites extends BaseVo{
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Integer id;
	@NotBlank(message = "网站名不能为空")
    private String siteName;
	@URL(message = "网站首页路径格式错误，请填写正确的网站首页路径")
    private String route;

    private Integer isUse;

    private String createDate;
    /**
     * 配置网站[首页模板路径,首页广告位id;栏目页模板路径,栏目页广告位id;详情页模板路径,详情页广告位id] 数据格式如下：
     * [{'homeTemplateURL':'a/index.html','adpositionsId':[1,3]},{'channelTemplateURL':'b/channel.html','adpositionsId':[1]},{'detailTemplateURL':'b/detail.html','adpositionsId':[1]}]
     */
//    private String templateConfig;
    /**
     * 网站首页权重最大值
     */
    @Min(value=1,message="网站首页权重最小值为{value}")
    private Integer homeWeightMax;
    /**
     * 网站域名
     */
    @URL(message = "网站域名格式错误，请填写正确的网站域名")
    private String domain;
    /**
     * 网站模板
     * 新增网站、编辑网站、列表查询时需要
     */
    @Valid
    private List<WebsiteTemplate> webTemplates;
    /**
     * 网站栏目关系
     * 新增网站填写网站栏目关系需要
     */
    private List<Integer> channelIds;
    /**
     * 列表页显示
     */
    private List<Channel> channels;
    
	public List<Channel> getChannels() {
		return channels;
	}

	public void setChannels(List<Channel> channels) {
		this.channels = channels;
	}

	public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getSiteName() {
		return siteName;
	}

	public void setSiteName(String siteName) {
		this.siteName = siteName == null ? null : siteName.trim();
	}

	public String getRoute() {
        return route;
    }

    public void setRoute(String route) {
        this.route = route == null ? null : route.trim();
    }

    public Integer getIsUse() {
		return isUse;
	}

	public void setIsUse(Integer isUse) {
		this.isUse = isUse;
	}

	public String getCreateDate() {
		return createDate;
	}

	public void setCreateDate(String createDate) {
		this.createDate = createDate;
	}

//	public String getTemplateConfig() {
//		return templateConfig;
//	}
//
//	public void setTemplateConfig(String templateConfig) {
//		this.templateConfig = templateConfig == null ? null : templateConfig.trim();
//	}

	public Integer getHomeWeightMax() {
		return homeWeightMax;
	}

	public void setHomeWeightMax(Integer homeWeightMax) {
		this.homeWeightMax = homeWeightMax;
	}

	public List<Integer> getChannelIds() {
		return channelIds;
	}

	public void setChannelIds(List<Integer> channelIds) {
		this.channelIds = channelIds;
	}

	public String getDomain() {
		return domain;
	}

	public void setDomain(String domain) {
		this.domain = domain;
	}

	public List<WebsiteTemplate> getWebTemplates() {
		return webTemplates;
	}

	public void setWebTemplates(List<WebsiteTemplate> webTemplates) {
		this.webTemplates = webTemplates;
	}

	
}