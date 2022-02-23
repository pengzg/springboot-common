package xyz.carjoy.question.member.service.impl;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import xyz.carjoy.question.common.base.service.impl.BaseIdServiceImpl;
import xyz.carjoy.question.member.cache.MemberBaseCache;
import xyz.carjoy.question.member.dao.IMemberBaseDao;
import xyz.carjoy.question.member.model.MemberBase;
import xyz.carjoy.question.member.service.IMemberBaseService;
import xyz.carjoy.question.member.vo.MemberBaseVO;
import xyz.carjoy.question.utils.*;

import java.util.List;
import java.util.Map;

@Transactional
@Service("memberBaseService")
public class MemberBaseServiceImpl extends BaseIdServiceImpl implements IMemberBaseService {
	private static final Logger log = LoggerFactory.getLogger(MemberBaseServiceImpl.class);
	@Autowired
	private IMemberBaseDao memberBaseDao;

	
	/**
	 * 查询总记录数，带查询条件
	 * @param queryCondition  查询条件
	 * @return 总记录数
	 */
	public Integer getRecordCount(Map<String, Object> queryCondition){
		return memberBaseDao.getRecordCount(queryCondition);
	}
	
	/**查询列表信息
	 * @param queryParams
	 * @return list
	 */
	public List<MemberBase> select(Map<String, Object> queryParams) {
		return memberBaseDao.select(queryParams);
	}
	
	
	/**分页查询
	 * @param query 条件
	 * @return DataGrid
	 */
	public DataGrid dataGrid(Query query) {
		// TODO Auto-generated method stub
		DataGrid<MemberBaseVO> dg = new DataGrid<MemberBaseVO>();
		List<MemberBase> list = memberBaseDao.queryByCondition(query);
		MemberBaseVO vo = null;
		for (MemberBase itemVO : list) {
			vo = new MemberBaseVO();
			BeanUtils.copyProperties(itemVO, vo);
			dg.getRows().add(vo);
		}
		dg.setTotal(memberBaseDao.getRecordCount(query.getQueryParams()));
		return dg;
	}
	
	/**
	 * 插入单条记录
	 * @param vo 用于添加的vo对象
	 * @return 若添加成功，返回新生成主键
	 */
	public String insert(MemberBase vo) {
		if (StringUtils.isBlank(vo.getMb_id())) {
			vo.setMb_id(super.createId(""));
			vo.setMb_code(StringUtil.createBillNumber());
			vo.setMb_addtime(DateUtil.getCurrentDateToString2());
			vo.setMb_dr(GlobalConstants.SAVE);
			vo.setMb_state(GlobalConstants.STATUS_ON);
			vo.setMb_followtime(DateUtil.getCurrentDateToString2());
		}

		memberBaseDao.insert(vo);

		MemberBaseCache.getInstance().put(vo.getMb_id(),vo);
		return vo.getMb_id();
	}

	/**
	 * 批量插入多条记录
	 * @param vos 添加的VO对象数组
	 * @return 
	 */
	public void insertBatch(MemberBase[] vos) {
		 memberBaseDao.insertBatch(vos);
	}
	
	/**
	 * 根据Id进行查询
	 * @param mb_id 用于查找的mb_id
	 * @return 查询到的VO对象
	 */
	public MemberBase find(String mb_id){
		return memberBaseDao.find(mb_id);
	}

	/**
	 * 更新单条记录
	 * @param vo 用于更新的VO对象
	 * @return 成功更新的记录数
	 */
	public int update(MemberBase vo) {
		MemberBase temp = find(vo.getMb_id());
		if(temp == null) {
			throw new  BusinessException("数据异常");
		}
		BeanUtils.copyProperties(vo, temp,new String[]{""});

		temp.setMb_updatetime(DateUtil.getCurrentDateToString2());
		temp.setMb_ts(DateUtil.getCurrentDateToString2());
		MemberBaseCache.getInstance().put(vo.getMb_id(),temp);

		//todo增加版本号 
		return memberBaseDao.update(temp);
	}
	
	
	

	/**
	 * 批量更新修改多条记录
	 * @param vos 添加的VO对象数组
	 * @return 成功更新的记录数组
	 */
	public int updateBatch(MemberBase[] vos) {
		return memberBaseDao.updateBatch(vos);
	}

	/**
	 * 根据Id进行查询
	 * @param mb_id 用于查找的主键
	 * @return 查询到的VO对象
	 */
	public MemberBaseVO getDetail(String mb_id){
		MemberBase vo = memberBaseDao.find(mb_id);
		MemberBaseVO newVO = new MemberBaseVO();
		BeanUtils.copyProperties(vo, newVO);
		return newVO;
	}
	
	/**
	 * 插入或更新
	 * @param vo 用于更新的VO对象
	 * @return 成功更新的记录数
	 */
	public String insertOrUpdate(MemberBase vo) {

		String currTime = DateUtil.getCurrentDateToString2();
		if (StringUtils.isNotBlank(vo.getMb_id())){
			vo.setMb_addtime(currTime);
			vo.setMb_state(GlobalConstants.STATUS_ON);
			vo.setMb_dr(GlobalConstants.SAVE);
			this.update(vo);
		}else{
			vo.setMb_id(super.createId(""));
			vo.setMb_updatetime(currTime);
			vo.setMb_ts(currTime);
			this.insert(vo);
		}
		
		return null;
	}
}
