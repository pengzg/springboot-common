package xyz.carjoy.question.common.base.service;


import xyz.carjoy.question.common.base.model.BaseCategory;
import xyz.carjoy.question.common.base.vo.BaseCategoryVO;
import xyz.carjoy.question.utils.DataGrid;
import xyz.carjoy.question.utils.Query;

import java.util.List;
import java.util.Map;

public interface IBaseCategoryService {
	
	
	/**查询列表信息
	 * @param queryParams
	 * @return list
	 */
	public List<BaseCategory> select(Map<String, Object> queryParams);
	
	
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
	public String insert(BaseCategory vo);

	/**
	 * 批更新插入多条记录
	 * @param vos 添加的VO对象数组
	 * @return 若添加成功，返回新生成的id数组
	 */
	public void insertBatch(BaseCategory[] vos);

	

	/**
	 * 根据主键进行查询
	 * @param bc_id 用于查找的id
	 * @return 查询到的VO对象
	 */
	public BaseCategory find(String bc_id);

	/**
	 * 更新单条记录
	 * @param vo 用于更新的VO对象
	 * @return 成功更新的记录数
	 */
	public int update(BaseCategory vo);
	
	
	/**
	 * 批量更新修改多条记录
	 * @param vos 添加的VO对象数组
	 * @return 成功更新的记录数组
	 */
	public int updateBatch(BaseCategory[] vos);

	/**
	 * 根据主键进行查询
	 * @param bc_id 用于查找的id
	 * @return 查询到的VO对象
	 */
	public BaseCategoryVO getDetail(String bc_id);

	
	/**
	 * 更新单条记录
	 * @param vo 用于更新的VO对象
	 * @return 成功更新的记录数
	 */
	public String insertOrUpdate(BaseCategory vo);

}
