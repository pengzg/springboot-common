package xyz.carjoy.question.common.sys.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import xyz.carjoy.question.common.base.service.impl.BaseIdServiceImpl;
import xyz.carjoy.question.common.sys.dao.ISysRoleMenuDao;
import xyz.carjoy.question.common.sys.model.SysRoleMenu;
import xyz.carjoy.question.common.sys.service.ISysRoleMenuService;
import xyz.carjoy.question.common.sys.vo.SysMenuTreeVO;
import xyz.carjoy.question.common.sys.vo.SysRoleMenuVO;
import xyz.carjoy.question.utils.BusinessException;
import xyz.carjoy.question.utils.DataGrid;
import xyz.carjoy.question.utils.Query;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/*
 * @Author pengzg
 * @Version 1.0
 * @date 2019-06-06 09:07
 */

@Transactional
@Service("sysRoleMenuService")
public class SysRoleMenuServiceImpl extends BaseIdServiceImpl implements ISysRoleMenuService {
	private static final Logger log = LoggerFactory.getLogger(SysRoleMenuServiceImpl.class);
	@Autowired
	private ISysRoleMenuDao sysRoleMenuDao;

	
	/**
	 * 查询总记录数，带查询条件
	 * @param queryCondition  查询条件
	 * @return 总记录数
	 */
	public Integer getRecordCount(Map<String, Object> queryCondition){
		return sysRoleMenuDao.getRecordCount(queryCondition);
	}
	
	/**查询列表信息
	 * @return list
	 */
	public List<SysMenuTreeVO> select(Map<String, Object> queryParams) {
		return sysRoleMenuDao.select(queryParams);
	}
	
	
	/**分页查询
	 * @param query
	 * @return
	 */
	public DataGrid dataGrid(Query query) {
		// TODO Auto-generated method stub
		DataGrid<SysRoleMenuVO> dg = new DataGrid<SysRoleMenuVO>();
		List<SysRoleMenu> list = sysRoleMenuDao.queryByCondition(query);
		SysRoleMenuVO vo = null;
		for (SysRoleMenu itemVO : list) {
			vo = new SysRoleMenuVO();
			BeanUtils.copyProperties(itemVO, vo);
			dg.getRows().add(vo);
		}
		dg.setTotal(sysRoleMenuDao.getRecordCount(query.getQueryParams()));
		return dg;
	}
	
	/**
	 * 插入单条记录
	 * @param vo 用于添加的VO对象
	 * @return 若添加成功，返回新生成的id
	 */
	public String insert(SysRoleMenu vo) {
		return sysRoleMenuDao.insert(vo)+"";
	}

	public void insertBatch(String menus, String srm_roleid) {

		sysRoleMenuDao.delete(srm_roleid);
		if (menus != null && menus.length() > 0) {
			String[] item = menus.split(",");
			int lent = item == null ? 0 : item.length;
			List<SysRoleMenu> list = new ArrayList();
			//SequenceGenerator oid = new SequenceGenerator();
			//String[] ids =oid.generate(lent);
			for (int i = 0; i < lent; i++) {
				SysRoleMenu roleMenu = new SysRoleMenu();
				roleMenu.setSrm_menuid(item[i]);
				roleMenu.setSrm_roleid(srm_roleid);
				//roleMenu.setSrm_id(ids[i]);
				list.add(roleMenu);
			}
			sysRoleMenuDao.insertBatch(list.toArray(new SysRoleMenu[list.size()]));
		}
	}

	@Override
	public void insertBatch(List<SysRoleMenu> list,String roleId) {
		// TODO Auto-generated method stub
		sysRoleMenuDao.delete(roleId);
		String[] ids = super.createId("",list.size());
		for(int i = 0; i < list.size(); i++){
			list.get(i).setSrm_id(ids[i]);
		}
		 sysRoleMenuDao.insertBatch(list.toArray(new SysRoleMenu[list.size()]));
	}
	
	/**
	 * 根据Id进行查询
	 * @return 查询到的VO对象
	 */
	public SysRoleMenu find(String srm_id){
		return sysRoleMenuDao.find(srm_id);
	}

	/**
	 * 更新单条记录
	 * @param vo 用于更新的VO对象
	 * @return 成功更新的记录数
	 */
	public int update(SysRoleMenu vo) {
		SysRoleMenu temp = find(vo.getSrm_id());
		if(temp == null) {
			throw new  BusinessException("数据异常");
		}
		BeanUtils.copyProperties(vo, temp,new String[]{""});
		//todo增加版本号 
		return sysRoleMenuDao.update(temp);
	}
	
	
	/**
	 * 更新单条记录
	 * @param vo 用于更新的VO对象
	 * @return 成功更新的记录数
	 */
	public int updateSelect(SysRoleMenu vo) {
		SysRoleMenu temp = find(vo.getSrm_id());
		if(temp == null) {
			throw new  BusinessException("数据异常");
		}
		//todo增加版本号 
		return sysRoleMenuDao.updateSelect(vo);
	}

	/**
	 * 批量更新修改多条记录
	 * @param vos 添加的VO对象数组
	 * @return 成功更新的记录数组
	 */
	public int updateBatch(SysRoleMenu[] vos) {
		return sysRoleMenuDao.updateBatch(vos);
	}

	/**
	 * 根据Id进行查询
	 * @return 查询到的VO对象
	 */
	public SysRoleMenuVO getDetail(String srm_id){
		SysRoleMenu vo = sysRoleMenuDao.find(srm_id);
		SysRoleMenuVO newVO = new SysRoleMenuVO();
		BeanUtils.copyProperties(vo, newVO);
		return newVO;
	}
}
