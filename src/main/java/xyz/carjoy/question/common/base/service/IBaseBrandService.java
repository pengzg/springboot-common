package xyz.carjoy.question.common.base.service;

import xyz.carjoy.question.common.base.model.BaseBrand;
import xyz.carjoy.question.common.base.vo.BaseBrandVO;
import xyz.carjoy.question.utils.DataGrid;
import xyz.carjoy.question.utils.Query;

import java.util.List;
import java.util.Map;

public interface IBaseBrandService {
	
	
	/**查询列表信息
	 * @param queryParams
	 * @return list
	 */
	public List<BaseBrand> select(Map<String, Object> queryParams);
	
	
	/**分页查询
	 * @param query
	 * @return
	 */
	public DataGrid dataGrid(Query query);

	/**
	 * 插入单条记录
	 * @param vo 用于添加的VO对象
	 * @return 若添加成功，返回新生成的id
	 */
	public String insert(BaseBrand vo);

	/**
	 * 批更新插入多条记录
	 * @param vos 添加的VO对象数组
	 * @return 若添加成功，返回新生成的id数组
	 */
	public void insertBatch(BaseBrand[] vos);

	

	/**
	 * 根据主键进行查询
	 * @param bb_id 用于查找的id
	 * @return 查询到的VO对象
	 */
	public BaseBrand find(String bb_id);

	/**
	 * 更新单条记录
	 * @param vo 用于更新的VO对象
	 * @return 成功更新的记录数
	 */
	public int update(BaseBrand vo);
	
	
	/**
	 * 批量更新修改多条记录
	 * @param vos 添加的VO对象数组
	 * @return 成功更新的记录数组
	 */
	public int updateBatch(BaseBrand[] vos);

	/**
	 * 根据主键进行查询
	 * @param bb_id 用于查找的id
	 * @return 查询到的VO对象
	 */
	public BaseBrandVO getDetail(String bb_id);

	
	/**
	 * 更新单条记录
	 * @param vo 用于更新的VO对象
	 * @return 成功更新的记录数
	 */
	public String insertOrUpdate(BaseBrand vo);

}
