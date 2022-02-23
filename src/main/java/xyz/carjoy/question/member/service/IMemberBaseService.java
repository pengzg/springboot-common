package xyz.carjoy.question.member.service;

import java.util.*;

import java.util.Map;

import xyz.carjoy.question.utils.*;
/*
 * @Author pengzg
 * @Version 1.0
 * @date 2021-7-6
 */


import xyz.carjoy.question.member.vo.MemberBaseVO;
import xyz.carjoy.question.member.model.MemberBase;

public interface IMemberBaseService {
	
	
	/**查询列表信息
	 * @param queryParams
	 * @return list
	 */
	public List<MemberBase> select(Map<String, Object> queryParams);
	
	
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
	public String insert(MemberBase vo);

	/**
	 * 批更新插入多条记录
	 * @param vos 添加的VO对象数组
	 * @return 若添加成功，返回新生成的id数组
	 */
	public void insertBatch(MemberBase[] vos);

	

	/**
	 * 根据主键进行查询
	 * @param mb_id 用于查找的id
	 * @return 查询到的VO对象
	 */
	public MemberBase find(String mb_id);

	/**
	 * 更新单条记录
	 * @param vo 用于更新的VO对象
	 * @return 成功更新的记录数
	 */
	public int update(MemberBase vo);
	
	
	/**
	 * 批量更新修改多条记录
	 * @param vos 添加的VO对象数组
	 * @return 成功更新的记录数组
	 */
	public int updateBatch(MemberBase[] vos);

	/**
	 * 根据主键进行查询
	 * @param mb_id 用于查找的id
	 * @return 查询到的VO对象
	 */
	public MemberBaseVO getDetail(String mb_id);

	
	/**
	 * 更新单条记录
	 * @param vo 用于更新的VO对象
	 * @return 成功更新的记录数
	 */
	public String insertOrUpdate(MemberBase vo);

}
