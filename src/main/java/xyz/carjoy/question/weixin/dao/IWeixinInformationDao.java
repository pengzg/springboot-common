package xyz.carjoy.question.weixin.dao;

import org.apache.ibatis.annotations.Mapper;
import xyz.carjoy.question.utils.Query;
import xyz.carjoy.question.weixin.model.WeixinInformation;

import java.util.List;
import java.util.Map;
/*
 * @Author pengzg
 * @Version 1.0
 * @date 2021-7-6
 */

@Mapper
public interface IWeixinInformationDao{
	/**
	 * 插入单条记录，用id作主键，把null全替换为""
	 * @param vo 用于添加的VO对象
	 * @return 若添加成功，返回新生成的id
	 */
	public int insert(WeixinInformation vo);

	/**
	 * 批更新插入多条记录，用id作主键，把null全替换为""
	 * @param vos  添加的VO对象数组
	 * @return 若添加成功，返回新生成的id数组
	 */
	public void insertBatch(WeixinInformation[] vos);



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
	 * 根据Id进行查询
	 * @param wi_id 用于查找的id
	 * @return 查询到的VO对象
	 */
	public WeixinInformation find(String wi_id);

	/**
	 * 查询总记录数，带查询条件
	 * @param queryCondition  查询条件
	 * @return 总记录数
	 */
	public Integer getRecordCount(Map<String, Object> queryCondition);

	/**
	 * 功能: 通过查询条件获得所有的VO对象列表，带翻页，带排序字符
	 * @param param包含queryCondition
	 *            查询条件, queryCondition等于null或""时查询全部，orderStr 排序字符
	 * @return 查询到的VO列表
	 */
	public List<WeixinInformation> queryByCondition(Query query);
	
	/**
	 * 功能: 通过查询条件获得所有的VO对象列表 
	 * @param param 包含 queryParams
	 * @return 查询到的VO列表
	 */
	public List<WeixinInformation> select(Map<String, Object> queryParams);
}
