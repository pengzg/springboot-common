package xyz.carjoy.question.common.base.service.impl;

import xyz.carjoy.question.common.base.cache.BaseDataDetailListCache;
import xyz.carjoy.question.common.base.cache.BaseDataDetailValueCache;
import xyz.carjoy.question.common.base.dao.IBaseDataDao;
import xyz.carjoy.question.common.base.model.BaseData;
import xyz.carjoy.question.common.base.model.BaseDataType;
import xyz.carjoy.question.common.base.service.IBaseDataService;
import xyz.carjoy.question.common.base.service.IBaseDataTypeService;
import xyz.carjoy.question.common.base.vo.BaseDataTreeVO;
import xyz.carjoy.question.common.base.vo.BaseDataVO;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import xyz.carjoy.question.utils.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/*
 * @Author pengzg
 * @Version 1.0
 * @date Wed May  8 10:32:14 CST 2019
 */

@Transactional
@Service("baseDataService")
public class BaseDataServiceImpl extends BaseIdServiceImpl implements IBaseDataService {
	private static final Logger log = LoggerFactory.getLogger(BaseDataServiceImpl.class);
	@Autowired
	private IBaseDataDao baseDataDao;

	@Autowired
	private IBaseDataTypeService baseDataTypeService;
	
	/**
	 * 查询总记录数，带查询条件
	 * @param queryCondition  查询条件
	 * @return 总记录数
	 */
	public Integer getRecordCount(Map<String, Object> queryCondition){
		return baseDataDao.getRecordCount(queryCondition);
	}
	
	/**查询列表信息
	 * @return list
	 */
	public List<BaseData> select(Map<String, Object> queryParams) {
		return baseDataDao.select(queryParams);
	}
	
	
	/**分页查询
	 * @param query
	 * @return
	 */
	public DataGrid dataGrid(Query query) {
		// TODO Auto-generated method stub
		DataGrid<BaseDataVO> dg = new DataGrid<BaseDataVO>();
		List<BaseData> list = baseDataDao.queryByCondition(query);
		BaseDataVO vo = null;
		for (BaseData itemVO : list) {
			vo = new BaseDataVO();
			BeanUtils.copyProperties(itemVO, vo);
			dg.getRows().add(vo);
		}
		dg.setTotal(baseDataDao.getRecordCount(query.getQueryParams()));
		return dg;
	}
	
	/**
	 * 插入单条记录
	 * @param vo 用于添加的VO对象
	 * @return 若添加成功，返回新生成的id
	 */
	public String insert(BaseData vo) {
		this.setCachValue(vo);
		return baseDataDao.insert(vo)+"";
	}

	/**
	 * 批更新插入多条记录
	 * @param vos 添加的VO对象数组
	 * @return 若添加成功，返回新生成的id数组
	 */
	public void insertBatch(BaseData[] vos) {
		 baseDataDao.insertBatch(vos);
	}
	
	/**
	 * 根据Id进行查询

	 * @return 查询到的VO对象
	 */
	public BaseData find(String bd_id){
		return baseDataDao.find(bd_id);
	}

	/**
	 * 更新单条记录
	 * @param vo 用于更新的VO对象
	 * @return 成功更新的记录数
	 */
	public int update(BaseData vo) {
		BaseData temp = find(vo.getBd_id());
		if(temp == null) {
			throw new BusinessException("数据异常");
		}
		BeanUtils.copyProperties(vo, temp,new String[]{""});
		this.setCachValue(temp);
		//todo增加版本号 
		return baseDataDao.update(temp);
	}
	
	
	/**
	 * 更新单条记录
	 * @param vo 用于更新的VO对象
	 * @return 成功更新的记录数
	 */
	public int updateSelect(BaseData vo) {
		BaseData temp = find(vo.getBd_id());
		if(temp == null) {
			throw new  BusinessException("数据异常");
		}
		//todo增加版本号 
		return baseDataDao.updateSelect(vo);
	}

	/**
	 * 批量更新修改多条记录
	 * @param vos 添加的VO对象数组
	 * @return 成功更新的记录数组
	 */
	public int updateBatch(BaseData[] vos) {
		return baseDataDao.updateBatch(vos);
	}


	/**
	 * 更新单条记录
	 * @param vo 用于更新的VO对象
	 * @return 成功更新的记录数
	 */
	public String insertOrUpdate(BaseData vo) {
		if (StringUtils.isNotBlank(vo.getBd_id())) {
			this.update(vo);
		} else {
			vo.setBd_id(super.createId(vo.getBd_org()));
			vo.setBd_adddate(DateUtil.getCurrentDateToString2());
			vo.setBd_dr(GlobalConstants.SAVE);
			vo.setBd_state(GlobalConstants.STATUS_ON);
			this.insert(vo);
		}
		this.setCachValue(vo);
		return null;
	}

	@Override
	public List<BaseDataTreeVO> queryBaseDataDetailTree(Map<String, Object> map) {
		List<BaseData> list = this.select(map);
		List<BaseDataTreeVO> treeList = new ArrayList<>();
		for (BaseData baseData : list) {
			BaseDataVO baseDataVO = new BaseDataVO();
			BeanUtils.copyProperties(baseData, baseDataVO);
			BaseDataTreeVO tree = new BaseDataTreeVO();
			tree.setId(baseDataVO.getBd_id()+"");
			tree.setPId(baseDataVO.getBd_pid()+"");
			tree.setName(baseDataVO.getBd_name());

			tree.setBd_id(baseDataVO.getBd_id());
			tree.setBd_name(baseDataVO.getBd_name());
			tree.setBd_code(baseDataVO.getBd_code());
			tree.setBd_order(baseDataVO.getBd_order());
			tree.setBd_status(baseDataVO.getBd_state_nameref());

			treeList.add(tree);
		}
		return treeList;
	}

	private void setCachValue(BaseData temp) {
		BaseDataType baseDataType = baseDataTypeService.find(temp.getBd_datatypeid());
		BaseDataDetailValueCache.getInstance().put(baseDataType.getBdt_code(), temp.getBd_code(), temp);
		Map<String, Object> queryParams = new HashMap<String, Object>();
		queryParams.put("bd_datatypeid", temp.getBd_datatypeid());
		List<BaseData> dataList = this.select(queryParams);
		BaseDataDetailListCache.getInstance().put(baseDataType.getBdt_code(), dataList);
	}
}
