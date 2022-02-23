package xyz.carjoy.question.weixin.controller;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import xyz.carjoy.question.utils.*;
import xyz.carjoy.question.weixin.model.WeixinInformation;
import xyz.carjoy.question.weixin.service.IWeixinInformationService;
import xyz.carjoy.question.weixin.vo.WeixinInformationVO;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/admin/base/weixinInformationControl")
public class WeixinInformationController{
	private static final Logger log = LoggerFactory.getLogger(WeixinInformationController.class);
	@Autowired
	private IWeixinInformationService weixinInformationService;

	/**
	 * 获取数据表格
	 * @param request
	 * @param pager
	 * @return
	 */
	@RequestMapping("/dataGrid")
	@ResponseBody
	public Result dataGrid(HttpServletRequest request, Pager pager) {
		Query query = new Query();
		query.setPager(pager);
		query.setQueryParams((Map<String, Object>) getQueryCondition(request));
		Result result = new Result();
		result.setData(HttpCode.OK, weixinInformationService.dataGrid(query),null);
		return result;
	}

	

	/**
	 * 查询明细
	 * @param request
	 * @param wi_id
	 * @return
	 */
	@RequestMapping("/getDetail")
	@ResponseBody
	public Result getDetail(HttpServletRequest request, String  wi_id) {
		Result result = new Result();
		try{
			WeixinInformationVO vo = weixinInformationService.getDetail(wi_id);
			result.setData(HttpCode.OK,vo ,null);
		} catch (BusinessException e) {
			log.error(e.getMessage());
			result.setExecRes(e.getErrorCode(), e.getMessage());
		} catch (Exception e) {

			log.error("获取详情异常!【"+e.getMessage()+"】",e);
			result.setError("失败");
		}
		return result;
	}

	/**
	 * 增加一条记录
	 * @param vo
	 * @return JSON
	 */
	@RequestMapping("/insert")
	@ResponseBody
	public Result insert(WeixinInformation vo) {
		Result result = new Result();
		try {

			weixinInformationService.insert(vo);
			result.setData(HttpCode.OK, null, "添加成功！");
		} catch (BusinessException e) {
			log.error(e.getMessage());
			result.setExecRes(e.getErrorCode(), e.getMessage());
		} catch (Exception e) {
			e.printStackTrace();
			log.error(e.getMessage(), e);
			result.setData(HttpCode.INTERNAL_SERVER_ERROR, null, "添加失败！");
		}
		return result;
	}

	/**
	 * 修改一条记录
	 * @param vo
	 * @return JSON
	 */
	@RequestMapping("/update")
	@ResponseBody
	public Result update(HttpServletRequest request,WeixinInformation vo) {
		Result result = new Result();
		try {

			weixinInformationService.insertOrUpdate(vo);
			result.setData(HttpCode.OK, null, "编辑成功！");

		} catch (BusinessException e) {
			log.error(e.getMessage());
			result.setExecRes(e.getErrorCode(), e.getMessage());
		} catch (Exception e) {
			e.printStackTrace();
			log.error(e.getMessage(), e);
			result.setData(HttpCode.INTERNAL_SERVER_ERROR, null, "编辑失败！");
		}
		return result;
	}

	
	/**
	 * 从页面的表单获取一条记录id，并删除多条记录
	 * @param wi_id
	 */
	@RequestMapping("/delete")
	@ResponseBody
	public Result delete(HttpServletRequest request,String  wi_id) {
		Result result = new Result();
		try {

			WeixinInformation vo = weixinInformationService.find(wi_id);
			//设置删除标志位
			weixinInformationService.update(vo);
			result.setData(HttpCode.OK, null, "删除成功！");
		} catch (BusinessException e) {
			log.error(e.getMessage());
			result.setExecRes(e.getErrorCode(), e.getMessage());
		} catch (Exception e) {
			e.printStackTrace();
			result.setData(HttpCode.INTERNAL_SERVER_ERROR, null, "删除失败！");
			log.error(e.getMessage(), e);
		}
		return result;
	}


	public Map<String, Object> getQueryCondition(HttpServletRequest request) {
		// TODO Auto-generated method stub
		Map<String, Object> queryParams = new HashMap<String, Object>();
		//主键	
		String wi_id = request.getParameter("wi_id");
		if (StringUtils.isNotBlank(wi_id)) {
			queryParams.put("wi_id", wi_id);
		}
		//	
		String wi_code = request.getParameter("wi_code");
		if (StringUtils.isNotBlank(wi_code)) {
			queryParams.put("wi_code", wi_code);
		}
		//	
		String wi_name = request.getParameter("wi_name");
		if (StringUtils.isNotBlank(wi_name)) {
			queryParams.put("wi_name", wi_name);
		}
		//appid	
		String wi_appid = request.getParameter("wi_appid");
		if (StringUtils.isNotBlank(wi_appid)) {
			queryParams.put("wi_appid", wi_appid);
		}
		//	
		String wi_appsecret = request.getParameter("wi_appsecret");
		if (StringUtils.isNotBlank(wi_appsecret)) {
			queryParams.put("wi_appsecret", wi_appsecret);
		}
		//	
		String wi_apptoken = request.getParameter("wi_apptoken");
		if (StringUtils.isNotBlank(wi_apptoken)) {
			queryParams.put("wi_apptoken", wi_apptoken);
		}
		//	
		String wi_token = request.getParameter("wi_token");
		if (StringUtils.isNotBlank(wi_token)) {
			queryParams.put("wi_token", wi_token);
		}
		//token到期时间	
		String wi_tokentime = request.getParameter("wi_tokentime");
		if (StringUtils.isNotBlank(wi_tokentime)) {
			queryParams.put("wi_tokentime", wi_tokentime);
		}
		//	
		String wi_ticket = request.getParameter("wi_ticket");
		if (StringUtils.isNotBlank(wi_ticket)) {
			queryParams.put("wi_ticket", wi_ticket);
		}
		//	
		String wi_ticketexpire_time = request.getParameter("wi_ticketexpire_time");
		if (StringUtils.isNotBlank(wi_ticketexpire_time)) {
			queryParams.put("wi_ticketexpire_time", wi_ticketexpire_time);
		}
		//	
		String wi_dr = request.getParameter("wi_dr");
		if (StringUtils.isNotBlank(wi_dr)) {
			queryParams.put("wi_dr", wi_dr);
		}
		//wi_ts	
		String wi_ts = request.getParameter("wi_ts");
		if (StringUtils.isNotBlank(wi_ts)) {
			queryParams.put("wi_ts", wi_ts);
		}
		//微信绑定授权域名	
		String wi_authorize_url = request.getParameter("wi_authorize_url");
		if (StringUtils.isNotBlank(wi_authorize_url)) {
			queryParams.put("wi_authorize_url", wi_authorize_url);
		}
		//私有Key	
		String wi_key = request.getParameter("wi_key");
		if (StringUtils.isNotBlank(wi_key)) {
			queryParams.put("wi_key", wi_key);
		}
		//商户号ID	
		String wi_mchid = request.getParameter("wi_mchid");
		if (StringUtils.isNotBlank(wi_mchid)) {
			queryParams.put("wi_mchid", wi_mchid);
		}
		//子商户号	
		String wi_submchid = request.getParameter("wi_submchid");
		if (StringUtils.isNotBlank(wi_submchid)) {
			queryParams.put("wi_submchid", wi_submchid);
		}
		//证书的本地路径	
		String wi_certlocalpath = request.getParameter("wi_certlocalpath");
		if (StringUtils.isNotBlank(wi_certlocalpath)) {
			queryParams.put("wi_certlocalpath", wi_certlocalpath);
		}
		//证书密码	
		String wi_certpassword = request.getParameter("wi_certpassword");
		if (StringUtils.isNotBlank(wi_certpassword)) {
			queryParams.put("wi_certpassword", wi_certpassword);
		}
		//	
		String wi_partnerid = request.getParameter("wi_partnerid");
		if (StringUtils.isNotBlank(wi_partnerid)) {
			queryParams.put("wi_partnerid", wi_partnerid);
		}
		//微信小程序原始gid	
		String wi_gid = request.getParameter("wi_gid");
		if (StringUtils.isNotBlank(wi_gid)) {
			queryParams.put("wi_gid", wi_gid);
		}
		// 
		String searchKey = request.getParameter("searchKey");
		if (StringUtils.isNotBlank(searchKey)) {
			queryParams.put("searchKey", searchKey);
		}
		return queryParams;
	}
}