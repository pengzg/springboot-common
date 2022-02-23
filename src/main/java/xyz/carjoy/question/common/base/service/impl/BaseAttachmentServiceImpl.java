package xyz.carjoy.question.common.base.service.impl;

import com.alibaba.fastjson.JSON;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import xyz.carjoy.question.common.base.dao.IBaseAttachmentDao;
import xyz.carjoy.question.common.base.model.BaseAttachment;
import xyz.carjoy.question.common.base.service.IBaseAttachmentService;
import xyz.carjoy.question.common.base.vo.BaseAttachmentVO;
import xyz.carjoy.question.utils.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/*
 * @Author pengzg
 * @Version 1.0
 * @date 2019-06-08
 */

@Transactional
@Service("baseAttachmentService")
public class BaseAttachmentServiceImpl extends BaseIdServiceImpl implements IBaseAttachmentService {
	private static final Logger log = LoggerFactory.getLogger(BaseAttachmentServiceImpl.class);
	@Autowired
	private IBaseAttachmentDao baseAttachmentDao;

	
	/**
	 * 查询总记录数，带查询条件
	 * @param queryCondition  查询条件
	 * @return 总记录数
	 */
	public Integer getRecordCount(Map<String, Object> queryCondition){
		return baseAttachmentDao.getRecordCount(queryCondition);
	}
	
	/**查询列表信息
	 * @return list
	 */
	public List<BaseAttachmentVO> select(Map<String, Object> queryParams) {
		queryParams.put("ba_dr",1);
		List<BaseAttachment>  list = baseAttachmentDao.select(queryParams);
		List<BaseAttachmentVO> listNew = new ArrayList<>();
		BaseAttachmentVO vo = null;
		for (BaseAttachment itemVO : list) {
			vo = new BaseAttachmentVO();
			BeanUtils.copyProperties(itemVO, vo);
			vo.setBa_add_time("");
			listNew.add(vo);
		}

		return listNew;
	}
	
	
	/**分页查询
	 * @param query
	 * @return
	 */
	public DataGrid dataGrid(Query query) {
		// TODO Auto-generated method stub
		DataGrid<BaseAttachmentVO> dg = new DataGrid<BaseAttachmentVO>();
		List<BaseAttachment> list = baseAttachmentDao.queryByCondition(query);
		BaseAttachmentVO vo = null;
		for (BaseAttachment itemVO : list) {
			vo = new BaseAttachmentVO();
			BeanUtils.copyProperties(itemVO, vo);
			dg.getRows().add(vo);
		}
		dg.setTotal(baseAttachmentDao.getRecordCount(query.getQueryParams()));
		return dg;
	}
	
	/**
	 * 插入单条记录
	 * @param vo 用于添加的VO对象
	 * @return 若添加成功，返回新生成的id
	 */
	public String insert(BaseAttachment vo) {
		return baseAttachmentDao.insert(vo)+"";
	}

	/**
	 * 批更新插入多条记录
	 * @param vos 添加的VO对象数组
	 * @return 若添加成功，返回新生成的id数组
	 */
	public void insertBatch(BaseAttachment[] vos) {
		 baseAttachmentDao.insertBatch(vos);
	}
	
	/**
	 * 根据Id进行查询
	 * @return 查询到的VO对象
	 */
	public BaseAttachment find(String ba_id){
		return baseAttachmentDao.find(ba_id);
	}

	/**
	 * 更新单条记录
	 * @param vo 用于更新的VO对象
	 * @return 成功更新的记录数
	 */
	public int update(BaseAttachment vo) {
		BaseAttachment temp = find(vo.getBa_id());
		if(temp == null) {
			throw new  BusinessException("数据异常");
		}
		BeanUtils.copyProperties(vo, temp,new String[]{""});
		//todo增加版本号 
		return baseAttachmentDao.update(temp);
	}
	
	
	/**
	 * 更新单条记录
	 * @param vo 用于更新的VO对象
	 * @return 成功更新的记录数
	 */
	public int updateSelect(BaseAttachment vo) {
		BaseAttachment temp = find(vo.getBa_id());
		if(temp == null) {
			throw new  BusinessException("数据异常");
		}
		//todo增加版本号 
		return baseAttachmentDao.updateSelect(vo);
	}

	/**
	 * 批量更新修改多条记录
	 * @param vos 添加的VO对象数组
	 * @return 成功更新的记录数组
	 */
	public int updateBatch(BaseAttachment[] vos) {
		return baseAttachmentDao.updateBatch(vos);
	}

	/**
	 * 根据Id进行查询
	 * @return 查询到的VO对象
	 */
	public BaseAttachmentVO getDetail(String ba_id){
		BaseAttachment vo = baseAttachmentDao.find(ba_id);
		BaseAttachmentVO newVO = new BaseAttachmentVO();
		BeanUtils.copyProperties(vo, newVO);
		return newVO;
	}

	@Override
	public void removeBySource(Map<String, Object> map) {
		baseAttachmentDao.removeBySource(map);
	}

	@Override
	public void insertBaseAttachmentList(String sourceid, String attListStr) {

		if (StringUtils.isNotBlank(attListStr)) {
			List<BaseAttachment> attList = JSON.parseArray(
					attListStr, BaseAttachment.class);

			for (BaseAttachment item:attList) {
				item.setBa_id(super.createId(""));
				item.setBa_add_time(DateUtil.getCurrentDateToString2());
				item.setBa_dr(GlobalConstants.SAVE);
				item.setBa_sourceid(sourceid);
			}

			this.insertBatch(attList.toArray(new BaseAttachment[attList.size()]));

		}

	}


}
