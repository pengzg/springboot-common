package com.baidu.ueditor.define;

import java.util.Map;

/**
 * 处理状态接口
 * @author hancong03@baidu.com
 *
 */
public interface State {
	
	public boolean isSuccess ();
	
	public void putInfo( String name, String val );
	
	public void putInfo ( String name, long val );
	
	public String toJSONString ();
	
	public Map<String, String> getInfo ();

}
