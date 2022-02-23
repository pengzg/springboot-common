package xyz.carjoy.question.common.base.controller;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import xyz.carjoy.question.common.base.model.BaseBrand;
import xyz.carjoy.question.common.base.service.IBaseBrandService;
import xyz.carjoy.question.common.base.vo.BaseBrandVO;
import xyz.carjoy.question.utils.*;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/admin/base/baseBrandControl")
public class BaseBrandController{
	private static final Logger log = LoggerFactory.getLogger(BaseBrandController.class);
	@Autowired
	private IBaseBrandService baseBrandService;

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
		result.setData(HttpCode.OK, baseBrandService.dataGrid(query),null);
		return result;
	}

	

	/**
	 * 查询明细
	 * @param request
	 * @param bb_id
	 * @return
	 */
	@RequestMapping("/getDetail")
	@ResponseBody
	public Result getDetail(HttpServletRequest request, String  bb_id) {
		Result result = new Result();
		try{
			BaseBrandVO vo = baseBrandService.getDetail(bb_id);
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
	public Result insert(BaseBrand vo) {
		Result result = new Result();
		try {

			baseBrandService.insert(vo);
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
	public Result update(HttpServletRequest request,BaseBrand vo) {
		Result result = new Result();
		try {

			baseBrandService.insertOrUpdate(vo);
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
	 * @param bb_id
	 */
	@RequestMapping("/delete")
	@ResponseBody
	public Result delete(HttpServletRequest request,String  bb_id) {
		Result result = new Result();
		try {

			BaseBrand vo = baseBrandService.find(bb_id);
			//设置删除标志位
			baseBrandService.update(vo);
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
		String bb_id = request.getParameter("bb_id");
		if (StringUtils.isNotBlank(bb_id)) {
			queryParams.put("bb_id", bb_id);
		}
		//所属公司	
		String bb_orgid = request.getParameter("bb_orgid");
		if (StringUtils.isNotBlank(bb_orgid)) {
			queryParams.put("bb_orgid", bb_orgid);
		}
		//品牌编码	
		String bb_code = request.getParameter("bb_code");
		if (StringUtils.isNotBlank(bb_code)) {
			queryParams.put("bb_code", bb_code);
		}
		//品牌名称	
		String bb_title = request.getParameter("bb_title");
		if (StringUtils.isNotBlank(bb_title)) {
			queryParams.put("bb_title", bb_title);
		}
		//品牌简介	
		String bb_intro = request.getParameter("bb_intro");
		if (StringUtils.isNotBlank(bb_intro)) {
			queryParams.put("bb_intro", bb_intro);
		}
		//品牌封面	
		String bb_cover = request.getParameter("bb_cover");
		if (StringUtils.isNotBlank(bb_cover)) {
			queryParams.put("bb_cover", bb_cover);
		}
		//国家	
		String bb_countyid = request.getParameter("bb_countyid");
		if (StringUtils.isNotBlank(bb_countyid)) {
			queryParams.put("bb_countyid", bb_countyid);
		}
		//品牌区域1国内2国际	
		String bb_area = request.getParameter("bb_area");
		if (StringUtils.isNotBlank(bb_area)) {
			queryParams.put("bb_area", bb_area);
		}
		//品牌级别1一线品牌,2二线品牌,3三线品牌 	
		String bb_level = request.getParameter("bb_level");
		if (StringUtils.isNotBlank(bb_level)) {
			queryParams.put("bb_level", bb_level);
		}
		//用于无限分类	
		String bb_path = request.getParameter("bb_path");
		if (StringUtils.isNotBlank(bb_path)) {
			queryParams.put("bb_path", bb_path);
		}
		//简介	
		String bb_content = request.getParameter("bb_content");
		if (StringUtils.isNotBlank(bb_content)) {
			queryParams.put("bb_content", bb_content);
		}
		//网络链接	
		String bb_url = request.getParameter("bb_url");
		if (StringUtils.isNotBlank(bb_url)) {
			queryParams.put("bb_url", bb_url);
		}
		//	
		String bb_sort = request.getParameter("bb_sort");
		if (StringUtils.isNotBlank(bb_sort)) {
			queryParams.put("bb_sort", bb_sort);
		}
		//状态 1是启用 0是禁用	
		String bb_state = request.getParameter("bb_state");
		if (StringUtils.isNotBlank(bb_state)) {
			queryParams.put("bb_state", bb_state);
		}
		//添加时间	
		String bb_addtime = request.getParameter("bb_addtime");
		if (StringUtils.isNotBlank(bb_addtime)) {
			queryParams.put("bb_addtime", bb_addtime);
		}
		//更新时间	
		String bb_updatetime = request.getParameter("bb_updatetime");
		if (StringUtils.isNotBlank(bb_updatetime)) {
			queryParams.put("bb_updatetime", bb_updatetime);
		}
		//删除标志位 1未删除2已删除	
		String bb_dr = request.getParameter("bb_dr");
		if (StringUtils.isNotBlank(bb_dr)) {
			queryParams.put("bb_dr", bb_dr);
		}
		//是否前台显示1 是 0 否	
		String bb_is_show = request.getParameter("bb_is_show");
		if (StringUtils.isNotBlank(bb_is_show)) {
			queryParams.put("bb_is_show", bb_is_show);
		}
		// 
		String searchKey = request.getParameter("searchKey");
		if (StringUtils.isNotBlank(searchKey)) {
			queryParams.put("searchKey", searchKey);
		}
		return queryParams;
	}
}