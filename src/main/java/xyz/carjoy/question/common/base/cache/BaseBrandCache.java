package xyz.carjoy.question.common.base.cache;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import xyz.carjoy.question.common.base.model.BaseBrand;
import xyz.carjoy.question.common.base.service.IBaseBrandService;
import xyz.carjoy.question.component.RedisDao;
import xyz.carjoy.question.component.SpringUtils;
import xyz.carjoy.question.utils.BusinessException;

/**
 * @author Administrator
 *
 */
public class BaseBrandCache  {

	private static final long serialVersionUID = 1934969776833301021L;

	private static final Logger log = LoggerFactory.getLogger(BaseBrandCache.class);

	// 关键字前缀字符;
	private String keyPrefix = "BaseBrand";


	private static BaseBrandCache instance = null;

	private static Object lock = new Object();
	private RedisDao redisDao = (RedisDao) SpringUtils.getBean("redisDao");

	public static BaseBrandCache getInstance() {
		if (instance == null) {
			synchronized (lock) {
				if (instance == null) {
					instance = new BaseBrandCache();
				}
			}
		}
		return instance;
	}

	public String getValue(String code) {
		try {
			BaseBrand vo = this.get(code);
			if (vo == null)
				return "";
			return vo.getBb_title();

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
	public BaseBrand get(String key) throws BusinessException {
		IBaseBrandService BaseBrandService = (IBaseBrandService) SpringUtils
				.getBean("baseBrandService");
		BaseBrand vo = null;
		try {
			vo = (BaseBrand) redisDao.getObj(
					this.keyPrefix + ":" + key);
			if (vo == null) {
				throw new BusinessException("SYS_E998", "cache not find");
			}
			return vo;
		} catch (Exception e) {
			vo = BaseBrandService.find(key);
			if (vo == null) {
				log.error("BaseBrand缓存异常："+this.keyPrefix + ":" + key+"data not find");
			} else {
				redisDao.setObj(this.keyPrefix + ":" + key, vo);
			}
		}
		return vo;
	}
	// 获取被缓存的值;
	public String getKeyValue(String key) throws BusinessException {
		String keyValue="";
		BaseBrand vo = this.get(key);
		if(vo == null)
			keyValue="";
		else
			keyValue = vo.getBb_title();
		return keyValue;
	}
}

