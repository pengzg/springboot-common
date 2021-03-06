
package xyz.carjoy.question.common.sys.controller;

import xyz.carjoy.question.common.sys.model.SysRoleMenu;
import xyz.carjoy.question.common.sys.service.ISysRoleMenuService;
import xyz.carjoy.question.common.sys.vo.SysRoleMenuVO;
import xyz.carjoy.question.utils.HttpCode;
import xyz.carjoy.question.utils.Pager;
import xyz.carjoy.question.utils.Query;
import xyz.carjoy.question.utils.Result;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/admin/sys/sysRoleMenuControl")
public class SysRoleMenuController{
	private static final Logger log = LoggerFactory.getLogger(SysRoleMenuController.class);
	@Autowired
	private ISysRoleMenuService sysRoleMenuService;

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
		result.setData(HttpCode.OK, sysRoleMenuService.dataGrid(query),null);
		return result;
	}

	

	/**
	 * 查询明细
	 * @param request
	 * @param srm_id
	 * @return
	 */
	@RequestMapping("/getDetail")
	@ResponseBody
	public Result getDetail(HttpServletRequest request, String  srm_id) {
		Result result = new Result();
		try{
			SysRoleMenuVO vo = sysRoleMenuService.getDetail(srm_id);
			result.setData(HttpCode.OK,vo ,null);
		}catch (Exception e) {
			log.error("获取详情异常!【"+e.getMessage()+"】",e);
			result.setError("失败");
		}
		return result;
	}

	/**
	 * 增加一条记录
	 * @param SysRoleMenu vo
	 * @return JSON
	 */
	@RequestMapping("/insert")
	@ResponseBody
	public Result insert(SysRoleMenu vo) {
		Result result = new Result();
		try {
//			SessionInfo sessionInfo = (SessionInfo) request.getSession()
//			.getAttribute(BaseController.RM_LOGIN_USER);
			sysRoleMenuService.insert(vo);
			result.setData(HttpCode.OK, null, "添加成功！");
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
	public Result update(HttpServletRequest request,SysRoleMenu vo) {
		Result result = new Result();
		try {
//			SessionInfo sessionInfo = (SessionInfo) request.getSession()
//			.getAttribute(BaseController.RM_LOGIN_USER);
			if (vo.getSrm_id()!=null){
				sysRoleMenuService.update(vo);
			}else{
				sysRoleMenuService.insert(vo);
			}
			result.setData(HttpCode.OK, null, "编辑成功！");
		} catch (Exception e) {
			e.printStackTrace();
			log.error(e.getMessage(), e);
			result.setData(HttpCode.INTERNAL_SERVER_ERROR, null, "编辑失败！");
		}
		return result;
	}

	
	/**
	 * 从页面的表单获取一条记录id，并删除多条记录
	 * @param srm_id
	 */
	@RequestMapping("/delete")
	@ResponseBody
	public Result delete(HttpServletRequest request,String  srm_id) {
		Result result = new Result();
		try {
//			SessionInfo sessionInfo = (SessionInfo) request.getSession()
//			.getAttribute(BaseController.RM_LOGIN_USER);
			SysRoleMenu vo = sysRoleMenuService.find(srm_id);
			//设置删除标志位
			sysRoleMenuService.update(vo);
			result.setData(HttpCode.OK, null, "删除成功！");
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
		//	
		String srm_id = request.getParameter("srm_id");
		if (StringUtils.isNotBlank(srm_id)) {
			queryParams.put("srm_id", srm_id);
		}
		//	
		String srm_roleid = request.getParameter("srm_roleid");
		if (StringUtils.isNotBlank(srm_roleid)) {
			queryParams.put("srm_roleid", srm_roleid);
		}
		//	
		String srm_menuid = request.getParameter("srm_menuid");
		if (StringUtils.isNotBlank(srm_menuid)) {
			queryParams.put("srm_menuid", srm_menuid);
		}
		//	
		String srm_state = request.getParameter("srm_state");
		if (StringUtils.isNotBlank(srm_state)) {
			queryParams.put("srm_state", srm_state);
		}
		//	
		String srm_dr = request.getParameter("srm_dr");
		if (StringUtils.isNotBlank(srm_dr)) {
			queryParams.put("srm_dr", srm_dr);
		}
		//	
		String srm_addtime = request.getParameter("srm_addtime");
		if (StringUtils.isNotBlank(srm_addtime)) {
			queryParams.put("srm_addtime", srm_addtime);
		}
		//	
		String srm_updatetime = request.getParameter("srm_updatetime");
		if (StringUtils.isNotBlank(srm_updatetime)) {
			queryParams.put("srm_updatetime", srm_updatetime);
		}
		//	
		String srm_ts = request.getParameter("srm_ts");
		if (StringUtils.isNotBlank(srm_ts)) {
			queryParams.put("srm_ts", srm_ts);
		}
		return queryParams;
	}
}