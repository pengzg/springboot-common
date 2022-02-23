package xyz.carjoy.question.weixin.service.impl;

import com.alibaba.fastjson.JSON;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import xyz.carjoy.question.common.base.service.impl.BaseIdServiceImpl;
import xyz.carjoy.question.utils.*;
import xyz.carjoy.question.weixin.dao.IWeixinInformationDao;
import xyz.carjoy.question.weixin.model.WeixinInformation;
import xyz.carjoy.question.weixin.service.IWeixinInformationService;
import xyz.carjoy.question.weixin.vo.WechatRepSession;
import xyz.carjoy.question.weixin.vo.WechatReqSession;
import xyz.carjoy.question.weixin.vo.WeixinInformationVO;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Transactional
@Service("weixinInformationService")
public class WeixinInformationServiceImpl extends BaseIdServiceImpl implements IWeixinInformationService {
	private static final Logger log = LoggerFactory.getLogger(WeixinInformationServiceImpl.class);
	@Autowired
	private IWeixinInformationDao weixinInformationDao;

	
	/**
	 * 查询总记录数，带查询条件
	 * @param queryCondition  查询条件
	 * @return 总记录数
	 */
	public Integer getRecordCount(Map<String, Object> queryCondition){
		return weixinInformationDao.getRecordCount(queryCondition);
	}
	
	/**查询列表信息
	 * @param queryParams
	 * @return list
	 */
	public List<WeixinInformation> select(Map<String, Object> queryParams) {
		return weixinInformationDao.select(queryParams);
	}
	
	
	/**分页查询
	 * @param query
	 * @return DataGrid
	 */
	public DataGrid dataGrid(Query query) {
		// TODO Auto-generated method stub
		DataGrid<WeixinInformationVO> dg = new DataGrid<WeixinInformationVO>();
		List<WeixinInformation> list = weixinInformationDao.queryByCondition(query);
		WeixinInformationVO vo = null;
		for (WeixinInformation itemVO : list) {
			vo = new WeixinInformationVO();
			BeanUtils.copyProperties(itemVO, vo);
			dg.getRows().add(vo);
		}
		dg.setTotal(weixinInformationDao.getRecordCount(query.getQueryParams()));
		return dg;
	}
	
	/**
	 * 插入单条记录
	 * @param vo 用于添加的vo对象
	 * @return 若添加成功，返回新生成主键
	 */
	public String insert(WeixinInformation vo) {
		return weixinInformationDao.insert(vo)+"";
	}

	/**
	 * 批量插入多条记录
	 * @param vos 添加的VO对象数组
	 * @return 
	 */
	public void insertBatch(WeixinInformation[] vos) {
		 weixinInformationDao.insertBatch(vos);
	}
	
	/**
	 * 根据Id进行查询
	 * @param wi_id 用于查找的wi_id
	 * @return 查询到的VO对象
	 */
	public WeixinInformation find(String wi_id){
		return weixinInformationDao.find(wi_id);
	}

	/**
	 * 更新单条记录
	 * @param vo 用于更新的VO对象
	 * @return 成功更新的记录数
	 */
	public int update(WeixinInformation vo) {
		WeixinInformation temp = find(vo.getWi_id());
		if(temp == null) {
			throw new  BusinessException("数据异常");
		}
		BeanUtils.copyProperties(vo, temp,new String[]{""});
		//todo增加版本号 
		return weixinInformationDao.update(temp);
	}
	
	
	

	/**
	 * 批量更新修改多条记录
	 * @param vos 添加的VO对象数组
	 * @return 成功更新的记录数组
	 */
	public int updateBatch(WeixinInformation[] vos) {
		return weixinInformationDao.updateBatch(vos);
	}

	/**
	 * 根据Id进行查询
	 * @param wi_id 用于查找的主键
	 * @return 查询到的VO对象
	 */
	public WeixinInformationVO getDetail(String wi_id){
		WeixinInformation vo = weixinInformationDao.find(wi_id);
		WeixinInformationVO newVO = new WeixinInformationVO();
		BeanUtils.copyProperties(vo, newVO);
		return newVO;
	}
	
	/**
	 * 插入或更新
	 * @param vo 用于更新的VO对象
	 * @return 成功更新的记录数
	 */
	public String insertOrUpdate(WeixinInformation vo) {
		
		if (StringUtils.isNotBlank(vo.getWi_id())){
			this.update(vo);
		}else{
			vo.setWi_id(super.createId(""));
			this.insert(vo);
		}
		
		return null;
	}

	@Override
	public WechatRepSession Jscode2session(WechatReqSession args) {
		HttpsRequest http = new HttpsRequest(WechatUrlConf.JSCODE2SESSION+"?appid="+args.getAppId()+"&secret="+args.getSecret()+"&js_code="+args.getJs_code()+"&grant_type=authorization_code");
		String result = http.postJson("");
		log.info("Jscode2session执行成功："+result);
		WechatRepSession rep  = JSON.parseObject(result, WechatRepSession.class);
		if(StringUtils.isNotBlank(rep.getOpenid())){
			return rep;
		}
		throw new BusinessException(HttpCode.INTERNAL_SERVER_ERROR.toString(),"Jscode2session执行失败："+result);
	}

	@Override
	public WeixinInformation getInfoBycode(String code) {

		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("wi_code",code);


		return this.select(map).get(0);
	}


}
