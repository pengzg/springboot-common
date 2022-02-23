package xyz.carjoy.question.common.base.cache;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import xyz.carjoy.question.common.base.model.BaseData;
import xyz.carjoy.question.common.base.service.IBaseDataService;
import xyz.carjoy.question.component.RedisDao;
import xyz.carjoy.question.component.SpringUtils;
import xyz.carjoy.question.utils.BusinessException;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


//根据字典类型编码取列表信息
public class BaseDataDetailValueCache  {


	private static final Logger logger = LoggerFactory.getLogger(BaseDataDetailValueCache.class);

	// 过期时间(单位为秒);
	private int refreshPeriod = 100000;

	// 关键字前缀字符;
	private String keyPrefix = "BaseDataDetailValueCache";

	private static final long serialVersionUID = -4397192926052141162L;

	private static BaseDataDetailValueCache instance = null;
	private RedisDao redisDao = (RedisDao) SpringUtils.getBean("redisDao");

	private static Object lock = new Object();

	public static BaseDataDetailValueCache getInstance() {
		if (instance == null) {
			synchronized (lock) {
				if (instance == null) {
					instance = new BaseDataDetailValueCache();
				}
			}
		}
		return instance;
	}

	private String key;


	public String setKeyVal(String dataType,String code) {
		return this.key+":"+dataType+":"+code;
	}
	
	// 添加被缓存的对象;
	public void put(String dataType,String code, Object value) {
		redisDao.setObj(this.setKeyVal(dataType,code), value);
	}
	


	
	public void remove(String dataType,String code ) {
		redisDao.del(this.setKeyVal(dataType,code));
	}

	
	public String getName(String datatype, String code) {
		try {
			BaseData vo = this.get(datatype, code);
			if (vo == null)
				return "";
			return vo.getBd_name();

		} catch (Exception e) {
			return "";
		}
	}


	// 获取被缓存的对象;
	public BaseData get(String datatype, String code) throws BusinessException {
		IBaseDataService baseDataService = (IBaseDataService) SpringUtils.getBean("baseDataService");
		BaseData vo = null;
		try {
			vo = (BaseData)redisDao.getObj(this.setKeyVal(datatype,code));
			if (vo == null) {
				throw new BusinessException("SYS_E998", "cache not find");
			}
			return vo;
		} catch (Exception nre) {

			List<BaseData> list = null;
			Map<String, Object> map = new HashMap<String, Object>();

			map.put("bdt_code", datatype);
			map.put("bd_code", code);
			list = baseDataService.select(map);
			if (list == null || list.size() == 0) {
				throw new BusinessException("SYS_E999", "取系统类型定义缓存出错");
			}
			vo = list.get(0);
			put(datatype, code, vo);
			return vo;
		}

	}
}
