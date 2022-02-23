package xyz.carjoy.question.member.controller;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import xyz.carjoy.question.member.model.MemberBase;
import xyz.carjoy.question.member.service.IMemberBaseService;
import xyz.carjoy.question.member.vo.MemberBaseVO;
import xyz.carjoy.question.utils.*;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/admin/phone/memberBaseControl")
public class MemberBaseController{
	private static final Logger log = LoggerFactory.getLogger(MemberBaseController.class);
	@Autowired
	private IMemberBaseService memberBaseService;

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
		result.setData(HttpCode.OK, memberBaseService.dataGrid(query),null);
		return result;
	}

	

	/**
	 * 查询明细
	 * @param request
	 * @param mb_id
	 * @return
	 */
	@RequestMapping("/getDetail")
	@ResponseBody
	public Result getDetail(HttpServletRequest request, String  mb_id) {
		Result result = new Result();
		try{
			MemberBaseVO vo = memberBaseService.getDetail(mb_id);
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
	public Result insert(MemberBase vo) {
		Result result = new Result();
		try {

			memberBaseService.insert(vo);
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
	public Result update(HttpServletRequest request,MemberBase vo) {
		Result result = new Result();
		try {

			memberBaseService.insertOrUpdate(vo);
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
	 * @param mb_id
	 */
	@RequestMapping("/delete")
	@ResponseBody
	public Result delete(HttpServletRequest request,String  mb_id) {
		Result result = new Result();
		try {

			MemberBase vo = memberBaseService.find(mb_id);
			//设置删除标志位
			memberBaseService.update(vo);
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
		String mb_id = request.getParameter("mb_id");
		if (StringUtils.isNotBlank(mb_id)) {
			queryParams.put("mb_id", mb_id);
		}
		//应用号	
		String mb_applicationid = request.getParameter("mb_applicationid");
		if (StringUtils.isNotBlank(mb_applicationid)) {
			queryParams.put("mb_applicationid", mb_applicationid);
		}
		//所属公司	
		String mb_orgid = request.getParameter("mb_orgid");
		if (StringUtils.isNotBlank(mb_orgid)) {
			queryParams.put("mb_orgid", mb_orgid);
		}
		//会员昵称	
		String mb_nickname = request.getParameter("mb_nickname");
		if (StringUtils.isNotBlank(mb_nickname)) {
			queryParams.put("mb_nickname", mb_nickname);
		}
		//会员姓名	
		String mb_name = request.getParameter("mb_name");
		if (StringUtils.isNotBlank(mb_name)) {
			queryParams.put("mb_name", mb_name);
		}
		//联系电话	
		String mb_mobile = request.getParameter("mb_mobile");
		if (StringUtils.isNotBlank(mb_mobile)) {
			queryParams.put("mb_mobile", mb_mobile);
		}
		//微信id	
		String mb_uuid = request.getParameter("mb_uuid");
		if (StringUtils.isNotBlank(mb_uuid)) {
			queryParams.put("mb_uuid", mb_uuid);
		}
		//	
		String mb_openid = request.getParameter("mb_openid");
		if (StringUtils.isNotBlank(mb_openid)) {
			queryParams.put("mb_openid", mb_openid);
		}
		//用户头像	
		String mb_img = request.getParameter("mb_img");
		if (StringUtils.isNotBlank(mb_img)) {
			queryParams.put("mb_img", mb_img);
		}
		//所属城市	
		String mb_city = request.getParameter("mb_city");
		if (StringUtils.isNotBlank(mb_city)) {
			queryParams.put("mb_city", mb_city);
		}
		//性别	
		String mb_sex = request.getParameter("mb_sex");
		if (StringUtils.isNotBlank(mb_sex)) {
			queryParams.put("mb_sex", mb_sex);
		}
		//关注时间	
		String mb_followtime = request.getParameter("mb_followtime");
		if (StringUtils.isNotBlank(mb_followtime)) {
			queryParams.put("mb_followtime", mb_followtime);
		}
		//用户类型 1 普通用户 2 商家（管理员） 3代理商 4 运营商 5核销员用户	
		String mb_usertype = request.getParameter("mb_usertype");
		if (StringUtils.isNotBlank(mb_usertype)) {
			queryParams.put("mb_usertype", mb_usertype);
		}
		//生日	
		String mb_birthday = request.getParameter("mb_birthday");
		if (StringUtils.isNotBlank(mb_birthday)) {
			queryParams.put("mb_birthday", mb_birthday);
		}
		//取消关注时间	
		String mb_unfollowtime = request.getParameter("mb_unfollowtime");
		if (StringUtils.isNotBlank(mb_unfollowtime)) {
			queryParams.put("mb_unfollowtime", mb_unfollowtime);
		}
		//最后一次登陆时间	
		String mb_lastlogintime = request.getParameter("mb_lastlogintime");
		if (StringUtils.isNotBlank(mb_lastlogintime)) {
			queryParams.put("mb_lastlogintime", mb_lastlogintime);
		}
		//用户状态 0禁用  1启用	
		String mb_state = request.getParameter("mb_state");
		if (StringUtils.isNotBlank(mb_state)) {
			queryParams.put("mb_state", mb_state);
		}
		//有效开始时间	
		String mb_statdate = request.getParameter("mb_statdate");
		if (StringUtils.isNotBlank(mb_statdate)) {
			queryParams.put("mb_statdate", mb_statdate);
		}
		//有效结束时间	
		String mb_enddate = request.getParameter("mb_enddate");
		if (StringUtils.isNotBlank(mb_enddate)) {
			queryParams.put("mb_enddate", mb_enddate);
		}
		//ts	
		String mb_ts = request.getParameter("mb_ts");
		if (StringUtils.isNotBlank(mb_ts)) {
			queryParams.put("mb_ts", mb_ts);
		}
		//添加时间	
		String mb_addtime = request.getParameter("mb_addtime");
		if (StringUtils.isNotBlank(mb_addtime)) {
			queryParams.put("mb_addtime", mb_addtime);
		}
		//更新时间 	
		String mb_updatetime = request.getParameter("mb_updatetime");
		if (StringUtils.isNotBlank(mb_updatetime)) {
			queryParams.put("mb_updatetime", mb_updatetime);
		}
		//dr	
		String mb_dr = request.getParameter("mb_dr");
		if (StringUtils.isNotBlank(mb_dr)) {
			queryParams.put("mb_dr", mb_dr);
		}
		// 
		String searchKey = request.getParameter("searchKey");
		if (StringUtils.isNotBlank(searchKey)) {
			queryParams.put("searchKey", searchKey);
		}
		return queryParams;
	}
}