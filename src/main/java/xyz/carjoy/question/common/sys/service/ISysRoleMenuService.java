package xyz.carjoy.question.common.sys.service;

import xyz.carjoy.question.common.sys.model.SysRoleMenu;
import xyz.carjoy.question.common.sys.vo.SysMenuTreeVO;
import xyz.carjoy.question.common.sys.vo.SysRoleMenuVO;
import xyz.carjoy.question.utils.DataGrid;
import xyz.carjoy.question.utils.Query;

import java.util.List;
import java.util.Map;

/*
 * @Author pengzg
 * @Version 1.0
 * @date 2019-06-06 09:07
 */

public interface ISysRoleMenuService {
	
	
	/**查询列表信息
	 * @param queryParams
	 * @return list
	 */
	public List<SysMenuTreeVO> select(Map<String, Object> queryParams);
	
	
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
	public String insert(SysRoleMenu vo);

	/**
	 * 批更新插入多条记录，用id作主键，把null全替换为""
	 * @param list 添加的VO对象数组
	 * @return 若添加成功，返回新生成的id数组
	 */
	public void insertBatch(List<SysRoleMenu> list,String roleId);

	

	/**
	 * 根据主键进行查询
	 * @param id 用于查找的id
	 * @return 查询到的VO对象
	 */
	public SysRoleMenu find(String srm_id);

	/**
	 * 更新单条记录
	 * @param vo 用于更新的VO对象
	 * @return 成功更新的记录数
	 */
	public int update(SysRoleMenu vo);
	
	
	/**
	 * 更新多条选中的记录
	 * @param vo 用于更新的VO对象
	 * @return 成功更新的记录数
	 */
	public int updateSelect(SysRoleMenu vo);
	
	

	/**
	 * 批量更新修改多条记录
	 * @param vos 添加的VO对象数组
	 * @return 成功更新的记录数组
	 */
	public int updateBatch(SysRoleMenu[] vos);

	/**
	 * 根据主键进行查询
	 * @param id 用于查找的id
	 * @return 查询到的VO对象
	 */
	public SysRoleMenuVO getDetail(String srm_id);


}
