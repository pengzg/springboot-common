package xyz.carjoy.question.weixin.cache;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import xyz.carjoy.question.component.RedisDao;
import xyz.carjoy.question.component.SpringUtils;
import xyz.carjoy.question.utils.BusinessException;
import xyz.carjoy.question.weixin.model.WeixinInformation;
import xyz.carjoy.question.weixin.service.IWeixinInformationService;

/**
 * @author Administrator
 *
 */
public class WeixinInformationCache  {

	private static final long serialVersionUID = 1934969776833301021L;

	private static final Logger log = LoggerFactory.getLogger(WeixinInformationCache.class);

	// 关键字前缀字符;
	private String keyPrefix = "WeixinInformation";


	private static WeixinInformationCache instance = null;

	private static Object lock = new Object();
	private RedisDao redisDao = (RedisDao) SpringUtils.getBean("redisDao");

	public static WeixinInformationCache getInstance() {
		if (instance == null) {
			synchronized (lock) {
				if (instance == null) {
					instance = new WeixinInformationCache();
				}
			}
		}
		return instance;
	}

	public String getValue(String code) {
		try {
			WeixinInformation vo = this.get(code);
			if (vo == null)
				return "";
			return vo.getWi_appid();

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
	public WeixinInformation get(String key) throws BusinessException {
		IWeixinInformationService weixinInformationService = (IWeixinInformationService) SpringUtils
				.getBean("weixinInformationService");
		WeixinInformation vo = null;
		try {
			vo = (WeixinInformation) redisDao.getObj(
					this.keyPrefix + ":" + key);
			if (vo == null) {
				throw new BusinessException("SYS_E998", "cache not find");
			}
			return vo;
		} catch (Exception e) {
			vo = weixinInformationService.getInfoBycode(key);
			if (vo == null) {
				log.error("WeixinInformation缓存异常："+this.keyPrefix + ":" + key+"data not find");
			} else {
				redisDao.setObj(this.keyPrefix + ":" + key, vo);
			}
		}
		return vo;
	}
	// 获取被缓存的值;
	public String getKeyValue(String key) throws BusinessException {
		String keyValue="";
		WeixinInformation vo = this.get(key);
		if(vo == null)
			keyValue="";
		else
			keyValue = vo.getWi_key();
		return keyValue;
	}
}

