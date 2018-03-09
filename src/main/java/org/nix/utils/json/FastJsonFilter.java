package org.nix.utils.json;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import com.alibaba.fastjson.serializer.PropertyFilter;

/**
 * 
 * JSON字段过滤器
 * @author ly
 *
 */
public class FastJsonFilter implements PropertyFilter
{
	private Map<Class<?>, Set<String>> includeMap = new HashMap<Class<?>, Set<String>>();
	
	public FastJsonFilter(){}
	
	//@Override  
	public boolean apply(Object source, String name, Object value) 
	{  
	    for(Entry<Class<?>, Set<String>> entry : includeMap.entrySet())
	    {  
	        Class<?> class1 = entry.getKey();  
	        
	        if(source.getClass() == class1)
	        {  
	            Set<String> fields = entry.getValue(); 
	            
	            for(String field : fields)
	            {  
	                if(field.equals(name))
	                {  
	                    return false;  
	                }  
	            }  
	        }  
	    }  
	    return true;  
	} 
	      
	/**
	 * 添加需要过滤的类的字段。
	 *
	 * @param cls 对应的类
	 * @param filedNames 类里面的字段名,不能为空
	 */
	public void addFiled(Class<?> cls, String... filedNames)
	{
		if(filedNames != null)
		{
			Set<String> filedSet = includeMap.get(cls);
			
			if(filedSet != null)
			{
				for(String s : filedNames)
				{
					filedSet.add(s);
				}
			}
			else
			{
				filedSet = new HashSet<String>();
				
				for(String s : filedNames)
				{
					filedSet.add(s);
				}
				
				includeMap.put(cls, filedSet);
			}
		}
	}
}