package xyz.carjoy.question.common.base.cache;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import xyz.carjoy.question.common.base.model.BaseCategory;
import xyz.carjoy.question.common.base.service.IBaseCategoryService;
import xyz.carjoy.question.component.RedisDao;
import xyz.carjoy.question.component.SpringUtils;
import xyz.carjoy.question.utils.BusinessException;

/**
 * @author Administrator
 *
 */
public class BaseCategoryCache  {

	private static final long serialVersionUID = 1934969776833301021L;

	private static final Logger log = LoggerFactory.getLogger(BaseCategoryCache.class);

	// 关键字前缀字符;
	private String keyPrefix = "BaseCategory";


	private static BaseCategoryCache instance = null;

	private static Object lock = new Object();
	private RedisDao redisDao = (RedisDao) SpringUtils.getBean("redisDao");

	public static BaseCategoryCache getInstance() {
		if (instance == null) {
			synchronized (lock) {
				if (instance == null) {
					instance = new BaseCategoryCache();
				}
			}
		}
		return instance;
	}

	public String getValue(String code) {
		try {
			BaseCategory vo = this.get(code);
			if (vo == null)
				return "";
			return vo.getBc_name();

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
		redisDao.delObj(this.keyPrefix + ":" + key);
	}

	public void removeAll() {
		redisDao.del(this.keyPrefix);
	}

	// 获取被缓存的对象;
	public BaseCategory get(String key) throws BusinessException {
		IBaseCategoryService BaseCategoryService = (IBaseCategoryService) SpringUtils
				.getBean("BaseCategoryService");
		BaseCategory vo = null;
		try {
			vo = (BaseCategory) redisDao.getObj(
					this.keyPrefix + ":" + key);
			if (vo == null) {
				throw new BusinessException("SYS_E998", "cache not find");
			}
			return vo;
		} catch (Exception e) {
			vo = BaseCategoryService.find(key);
			if (vo == null) {
				log.error("BaseCategory缓存异常："+this.keyPrefix + ":" + key+"data not find");
			} else {
				redisDao.setObj(this.keyPrefix + ":" + key, vo);
			}
		}
		return vo;
	}
	// 获取被缓存的值;
	public String getKeyValue(String key) throws BusinessException {
		String keyValue="";
		BaseCategory vo = this.get(key);
		if(vo == null)
			keyValue="";
		else
			keyValue = vo.getBc_name();
		return keyValue;
	}
}

