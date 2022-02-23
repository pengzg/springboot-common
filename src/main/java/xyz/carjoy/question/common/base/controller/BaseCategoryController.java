package xyz.carjoy.question.common.base.controller;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import xyz.carjoy.question.common.base.model.BaseCategory;
import xyz.carjoy.question.common.base.service.IBaseCategoryService;
import xyz.carjoy.question.common.base.vo.BaseCategoryVO;
import xyz.carjoy.question.utils.*;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/admin/base/baseCategoryControl")
public class BaseCategoryController{
	private static final Logger log = LoggerFactory.getLogger(BaseCategoryController.class);
	@Autowired
	private IBaseCategoryService baseCategoryService;

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
		result.setData(HttpCode.OK, baseCategoryService.dataGrid(query),null);
		return result;
	}

	

	/**
	 * 查询明细
	 * @param request
	 * @param bc_id
	 * @return
	 */
	@RequestMapping("/getDetail")
	@ResponseBody
	public Result getDetail(HttpServletRequest request, String  bc_id) {
		Result result = new Result();
		try{
			BaseCategoryVO vo = baseCategoryService.getDetail(bc_id);
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
	public Result insert(BaseCategory vo) {
		Result result = new Result();
		try {

			baseCategoryService.insert(vo);
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
	public Result update(HttpServletRequest request,BaseCategory vo) {
		Result result = new Result();
		try {

			baseCategoryService.insertOrUpdate(vo);
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
	 * @param bc_id
	 */
	@RequestMapping("/delete")
	@ResponseBody
	public Result delete(HttpServletRequest request,String  bc_id) {
		Result result = new Result();
		try {

			BaseCategory vo = baseCategoryService.find(bc_id);
			//设置删除标志位
			baseCategoryService.update(vo);
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
		String bc_id = request.getParameter("bc_id");
		if (StringUtils.isNotBlank(bc_id)) {
			queryParams.put("bc_id", bc_id);
		}
		//所属公司	
		String bc_orgid = request.getParameter("bc_orgid");
		if (StringUtils.isNotBlank(bc_orgid)) {
			queryParams.put("bc_orgid", bc_orgid);
		}
		//上级父类ID	
		String bc_parentid = request.getParameter("bc_parentid");
		if (StringUtils.isNotBlank(bc_parentid)) {
			queryParams.put("bc_parentid", bc_parentid);
		}
		//客户类别编码	
		String bc_code = request.getParameter("bc_code");
		if (StringUtils.isNotBlank(bc_code)) {
			queryParams.put("bc_code", bc_code);
		}
		//客户类别名称	
		String bc_name = request.getParameter("bc_name");
		if (StringUtils.isNotBlank(bc_name)) {
			queryParams.put("bc_name", bc_name);
		}
		//状态	
		String bc_state = request.getParameter("bc_state");
		if (StringUtils.isNotBlank(bc_state)) {
			queryParams.put("bc_state", bc_state);
		}
		//备注	
		String bc_remarks = request.getParameter("bc_remarks");
		if (StringUtils.isNotBlank(bc_remarks)) {
			queryParams.put("bc_remarks", bc_remarks);
		}
		//最后修改人	
		String bc_updateuserid = request.getParameter("bc_updateuserid");
		if (StringUtils.isNotBlank(bc_updateuserid)) {
			queryParams.put("bc_updateuserid", bc_updateuserid);
		}
		//添加时间	
		String bc_addtime = request.getParameter("bc_addtime");
		if (StringUtils.isNotBlank(bc_addtime)) {
			queryParams.put("bc_addtime", bc_addtime);
		}
		//最后修改时间	
		String bc_updatetime = request.getParameter("bc_updatetime");
		if (StringUtils.isNotBlank(bc_updatetime)) {
			queryParams.put("bc_updatetime", bc_updatetime);
		}
		//有效标志位 1 有效 2 删除	
		String bc_dr = request.getParameter("bc_dr");
		if (StringUtils.isNotBlank(bc_dr)) {
			queryParams.put("bc_dr", bc_dr);
		}
		//更新时间	
		String bc_ts = request.getParameter("bc_ts");
		if (StringUtils.isNotBlank(bc_ts)) {
			queryParams.put("bc_ts", bc_ts);
		}
		//版本	
		String bc_version = request.getParameter("bc_version");
		if (StringUtils.isNotBlank(bc_version)) {
			queryParams.put("bc_version", bc_version);
		}
		// 
		String searchKey = request.getParameter("searchKey");
		if (StringUtils.isNotBlank(searchKey)) {
			queryParams.put("searchKey", searchKey);
		}
		return queryParams;
	}
}