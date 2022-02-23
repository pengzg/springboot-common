package xyz.carjoy.question.weixin.service;

import xyz.carjoy.question.utils.DataGrid;
import xyz.carjoy.question.utils.Query;
import xyz.carjoy.question.weixin.model.WeixinInformation;
import xyz.carjoy.question.weixin.vo.WechatRepSession;
import xyz.carjoy.question.weixin.vo.WechatReqSession;
import xyz.carjoy.question.weixin.vo.WeixinInformationVO;

import java.util.List;
import java.util.Map;

/*
 * @Author pengzg
 * @Version 1.0
 * @date 2021-7-6
 */

public interface IWeixinInformationService {
	
	
	/**查询列表信息
	 * @param queryParams
	 * @return list
	 */
	public List<WeixinInformation> select(Map<String, Object> queryParams);
	
	
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
	public String insert(WeixinInformation vo);

	/**
	 * 批更新插入多条记录
	 * @param vos 添加的VO对象数组
	 * @return 若添加成功，返回新生成的id数组
	 */
	public void insertBatch(WeixinInformation[] vos);

	

	/**
	 * 根据主键进行查询
	 * @param wi_id 用于查找的id
	 * @return 查询到的VO对象
	 */
	public WeixinInformation find(String wi_id);

	/**
	 * 更新单条记录
	 * @param vo 用于更新的VO对象
	 * @return 成功更新的记录数
	 */
	public int update(WeixinInformation vo);
	
	
	/**
	 * 批量更新修改多条记录
	 * @param vos 添加的VO对象数组
	 * @return 成功更新的记录数组
	 */
	public int updateBatch(WeixinInformation[] vos);

	/**
	 * 根据主键进行查询
	 * @param wi_id 用于查找的id
	 * @return 查询到的VO对象
	 */
	public WeixinInformationVO getDetail(String wi_id);

	
	/**
	 * 更新单条记录
	 * @param vo 用于更新的VO对象
	 * @return 成功更新的记录数
	 */
	public String insertOrUpdate(WeixinInformation vo);


	public WechatRepSession Jscode2session(WechatReqSession args);

	public WeixinInformation getInfoBycode(String code);
}
