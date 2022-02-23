package xyz.carjoy.question.common.base.vo;

import org.apache.commons.lang.StringUtils;
import xyz.carjoy.question.common.base.cache.BaseParameterCache;
import xyz.carjoy.question.common.base.model.BaseAttachment;
import xyz.carjoy.question.utils.BaseParamConstant;
import xyz.carjoy.question.utils.GlobalConstants;

public class BaseAttachmentVO extends BaseAttachment implements java.io.Serializable{
	private static final long serialVersionUID = 5454155825314635342L;
	
	//columns START111
	private String ba_path_show;



	public String getBa_path_show() {
		if (StringUtils.isNotBlank(getBa_path())) {
			ba_path_show = BaseParameterCache.getInstance().getKeyValue(BaseParamConstant.IMAGE_DOMAIN_URL)+ GlobalConstants.IMGPATH_ROUTE+getBa_path();
		}
		return ba_path_show;
	}

	public void setBa_path_show(String ba_path_show) {
		this.ba_path_show = ba_path_show;
	}

}

