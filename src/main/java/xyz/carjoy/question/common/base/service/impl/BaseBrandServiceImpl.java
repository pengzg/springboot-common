package xyz.carjoy.question.common.base.service.impl;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import xyz.carjoy.question.common.base.cache.BaseBrandCache;
import xyz.carjoy.question.common.base.dao.IBaseBrandDao;
import xyz.carjoy.question.common.base.model.BaseBrand;
import xyz.carjoy.question.common.base.service.IBaseBrandService;
import xyz.carjoy.question.common.base.vo.BaseBrandVO;
import xyz.carjoy.question.utils.*;

import java.util.List;
import java.util.Map;

@Transactional
@Service("baseBrandService")
public class BaseBrandServiceImpl extends BaseIdServiceImpl implements IBaseBrandService {
	private static final Logger log = LoggerFactory.getLogger(BaseBrandServiceImpl.class);
	@Autowired
	private IBaseBrandDao baseBrandDao;

	
	/**
	 * 查询总记录数，带查询条件
	 * @param queryCondition  查询条件
	 * @return 总记录数
	 */
	public Integer getRecordCount(Map<String, Object> queryCondition){
		return baseBrandDao.getRecordCount(queryCondition);
	}
	
	/**查询列表信息
	 * @param queryParams
	 * @return list
	 */
	public List<BaseBrand> select(Map<String, Object> queryParams) {
		return baseBrandDao.select(queryParams);
	}
	
	
	/**分页查询
	 * @param Query
	 * @return DataGrid
	 */
	public DataGrid dataGrid(Query query) {
		// TODO Auto-generated method stub
		DataGrid<BaseBrandVO> dg = new DataGrid<BaseBrandVO>();
		List<BaseBrand> list = baseBrandDao.queryByCondition(query);
		BaseBrandVO vo = null;
		for (BaseBrand itemVO : list) {
			vo = new BaseBrandVO();
			BeanUtils.copyProperties(itemVO, vo);
			dg.getRows().add(vo);
		}
		dg.setTotal(baseBrandDao.getRecordCount(query.getQueryParams()));
		return dg;
	}
	
	/**
	 * 插入单条记录
	 * @param vo 用于添加的vo对象
	 * @return 若添加成功，返回新生成主键
	 */
	public String insert(BaseBrand vo) {
		return baseBrandDao.insert(vo)+"";
	}

	/**
	 * 批量插入多条记录
	 * @param vos 添加的VO对象数组
	 * @return 
	 */
	public void insertBatch(BaseBrand[] vos) {
		 baseBrandDao.insertBatch(vos);
	}
	
	/**
	 * 根据Id进行查询
	 * @param bb_id 用于查找的bb_id
	 * @return 查询到的VO对象
	 */
	public BaseBrand find(String bb_id){
		return baseBrandDao.find(bb_id);
	}

	/**
	 * 更新单条记录
	 * @param vo 用于更新的VO对象
	 * @return 成功更新的记录数
	 */
	public int update(BaseBrand vo) {
		BaseBrand temp = find(vo.getBb_id());
		if(temp == null) {
			throw new BusinessException("数据异常");
		}
		BeanUtils.copyProperties(vo, temp,new String[]{""});
		BaseBrandCache.getInstance().put(vo.getBb_id(),temp);
		//todo增加版本号 
		return baseBrandDao.update(temp);
	}
	
	
	

	/**
	 * 批量更新修改多条记录
	 * @param vos 添加的VO对象数组
	 * @return 成功更新的记录数组
	 */
	public int updateBatch(BaseBrand[] vos) {
		return baseBrandDao.updateBatch(vos);
	}

	/**
	 * 根据Id进行查询
	 * @param  用于查找的主键
	 * @return 查询到的VO对象
	 */
	public BaseBrandVO getDetail(String bb_id){
		BaseBrand vo = baseBrandDao.find(bb_id);
		BaseBrandVO newVO = new BaseBrandVO();
		BeanUtils.copyProperties(vo, newVO);
		return newVO;
	}
	
	/**
	 * 插入或更新
	 * @param vo 用于更新的VO对象
	 * @return 成功更新的记录数
	 */
	public String insertOrUpdate(BaseBrand vo) {
		
		if (StringUtils.isNotBlank(vo.getBb_id())){
			vo.setBb_updatetime(DateUtil.getCurrentDateToString2());
			this.update(vo);
		}else{
			vo.setBb_id(super.createId(""));
			vo.setBb_addtime(DateUtil.getCurrentDateToString2());
			vo.setBb_state(GlobalConstants.STATUS_ON);
			vo.setBb_dr(GlobalConstants.SAVE);
			this.insert(vo);
		}
		
		return null;
	}
}
