package xyz.carjoy.question.common.base.service.impl;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import xyz.carjoy.question.common.base.cache.BaseCategoryCache;
import xyz.carjoy.question.common.base.dao.IBaseCategoryDao;
import xyz.carjoy.question.common.base.model.BaseCategory;
import xyz.carjoy.question.common.base.service.IBaseCategoryService;
import xyz.carjoy.question.common.base.vo.BaseCategoryVO;
import xyz.carjoy.question.utils.*;

import java.util.List;
import java.util.Map;

@Transactional
@Service("baseCategoryService")
public class BaseCategoryServiceImpl extends BaseIdServiceImpl implements IBaseCategoryService {
	private static final Logger log = LoggerFactory.getLogger(BaseCategoryServiceImpl.class);
	@Autowired
	private IBaseCategoryDao baseCategoryDao;

	
	/**
	 * 查询总记录数，带查询条件
	 * @param queryCondition  查询条件
	 * @return 总记录数
	 */
	public Integer getRecordCount(Map<String, Object> queryCondition){
		return baseCategoryDao.getRecordCount(queryCondition);
	}
	
	/**查询列表信息
	 * @param queryParams
	 * @return list
	 */
	public List<BaseCategory> select(Map<String, Object> queryParams) {
		return baseCategoryDao.select(queryParams);
	}
	
	
	/**分页查询
	 * @return DataGrid
	 */
	public DataGrid dataGrid(Query query) {
		// TODO Auto-generated method stub
		DataGrid<BaseCategoryVO> dg = new DataGrid<BaseCategoryVO>();
		List<BaseCategory> list = baseCategoryDao.queryByCondition(query);
		BaseCategoryVO vo = null;
		for (BaseCategory itemVO : list) {
			vo = new BaseCategoryVO();
			BeanUtils.copyProperties(itemVO, vo);
			dg.getRows().add(vo);
		}
		dg.setTotal(baseCategoryDao.getRecordCount(query.getQueryParams()));
		return dg;
	}
	
	/**
	 * 插入单条记录
	 * @param vo 用于添加的vo对象
	 * @return 若添加成功，返回新生成主键
	 */
	public String insert(BaseCategory vo) {
		return baseCategoryDao.insert(vo)+"";
	}

	/**
	 * 批量插入多条记录
	 * @param vos 添加的VO对象数组
	 * @return 
	 */
	public void insertBatch(BaseCategory[] vos) {
		 baseCategoryDao.insertBatch(vos);
	}
	
	/**
	 * 根据Id进行查询
	 * @param bc_id 用于查找的bc_id
	 * @return 查询到的VO对象
	 */
	public BaseCategory find(String bc_id){
		return baseCategoryDao.find(bc_id);
	}

	/**
	 * 更新单条记录
	 * @param vo 用于更新的VO对象
	 * @return 成功更新的记录数
	 */
	public int update(BaseCategory vo) {
		BaseCategory temp = find(vo.getBc_id());
		if(temp == null) {
			throw new BusinessException("数据异常");
		}
		BeanUtils.copyProperties(vo, temp,new String[]{""});
		BaseCategoryCache.getInstance().put(temp.getBc_id(),temp);
		//todo增加版本号 
		return baseCategoryDao.update(temp);
	}
	
	
	

	/**
	 * 批量更新修改多条记录
	 * @param vos 添加的VO对象数组
	 * @return 成功更新的记录数组
	 */
	public int updateBatch(BaseCategory[] vos) {
		return baseCategoryDao.updateBatch(vos);
	}

	/**
	 * 根据Id进行查询
	 * @return 查询到的VO对象
	 */
	public BaseCategoryVO getDetail(String bc_id){
		BaseCategory vo = baseCategoryDao.find(bc_id);
		BaseCategoryVO newVO = new BaseCategoryVO();
		BeanUtils.copyProperties(vo, newVO);
		return newVO;
	}
	
	/**
	 * 插入或更新
	 * @param vo 用于更新的VO对象
	 * @return 成功更新的记录数
	 */
	public String insertOrUpdate(BaseCategory vo) {
		
		if (StringUtils.isNotBlank(vo.getBc_id())){
			vo.setBc_updatetime(DateUtil.getCurrentDateToString2());
			this.update(vo);
		}else{
			vo.setBc_id(super.createId(""));
			vo.setBc_addtime(DateUtil.getCurrentDateToString2());
			vo.setBc_state(GlobalConstants.STATUS_ON);
			vo.setBc_dr(GlobalConstants.SAVE);
			this.insert(vo);
		}
		
		return null;
	}
}
