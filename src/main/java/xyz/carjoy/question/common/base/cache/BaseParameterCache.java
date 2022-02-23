package xyz.carjoy.question.common.base.cache;


import xyz.carjoy.question.common.base.model.BaseParameter;
import xyz.carjoy.question.common.base.service.IBaseParameterService;
import xyz.carjoy.question.component.RedisDao;
import xyz.carjoy.question.component.SpringUtils;

import xyz.carjoy.question.utils.BusinessException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class BaseParameterCache  {
	
	private static final long serialVersionUID = 1934969776833301021L;

	private static final Logger logger = LoggerFactory.getLogger(BaseParameterCache.class);

	// 关键字前缀字符;
	private String keyPrefix = "BaseParameterCache";


	private static volatile BaseParameterCache instance = null;

	private static Object lock = new Object();
	private RedisDao redisDao = (RedisDao) SpringUtils.getBean("redisDao");

	public static BaseParameterCache getInstance() {
		if (instance == null) {
			synchronized (lock) {
				if (instance == null) {
					instance = new BaseParameterCache();
				}
			}
		}
		return instance;
	}

	public String getValue(String code) {
		try {
			BaseParameter vo = this.get(code);
			if (vo == null)
				return "";
			return vo.getBp_value();

		} catch (Exception e) {
			e.printStackTrace();
			return "";
		}
	}

	//把缓存里放值
	public void put(String id,Object obj) {
		redisDao.setObj(this.keyPrefix + ":" + id, obj);
	}
	// 删除被缓存的对象;
	public void remove(String key) {
		redisDao.del(this.keyPrefix + ":" + key);
	}



	// 获取被缓存的对象;
	public BaseParameter get(String key) throws BusinessException {
		IBaseParameterService BaseParameterService = (IBaseParameterService) SpringUtils
				.getBean("baseParameterService");
		BaseParameter vo = null;
		try {
			vo = (BaseParameter) redisDao.getObj(
					this.keyPrefix + ":" + key);
			if (vo == null) {
				throw new BusinessException("SYS_E998", "cache not find");
			}
			return vo;
		} catch (Exception e) {
			vo = BaseParameterService.findByKeyCode(key);
			if (vo == null) {
				 logger.error("多渠道参数配置缓存异常："+this.keyPrefix + ":" + key+"data not find");
			} else {
				redisDao.setObj(this.keyPrefix + ":" + key, vo);
			}
		}
		return vo;
	}
	// 获取被缓存的值;
	public String getKeyValue(String key) throws BusinessException {
		IBaseParameterService BaseParameterService = (IBaseParameterService) SpringUtils
				.getBean("baseParameterService");
		BaseParameter vo = null;
		String keyValue="";
		try {
			vo = (BaseParameter) redisDao.getObj(
					this.keyPrefix + ":" + key);

			if (vo == null) {
				throw new BusinessException("SYS_E998", "cache not find");
			}
			return vo.getBp_value();
		} catch (Exception e) {
			vo = BaseParameterService.findByKeyCode(key);
			if (vo == null) {
				logger.error("多渠道参数配置缓存异常："+this.keyPrefix + ":" + key+"data not find");
			} else {
				redisDao.setObj(this.keyPrefix + ":" + key, vo);
			}
		}
		if(vo == null)
			keyValue="";
		else
			keyValue = vo.getBp_value();
		return keyValue;
	}
}
