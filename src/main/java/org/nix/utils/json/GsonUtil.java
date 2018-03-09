package org.nix.utils.json;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.TreeMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.nix.utils.StringUtil ;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

/**
 *  json工具类
 *
 */ 
public class GsonUtil {
	
	private static Map<String, Object> jsonap = new HashMap<String, Object>();  
	 
	/** 
     * 获取JsonObject 
     * @param json 
     * @return 
     */  
    public static JsonObject parseJson(String json){  
        JsonParser parser = new JsonParser();  
        JsonObject jsonObj = parser.parse(json).getAsJsonObject();  
        return jsonObj;  
    }
    
    /**
     * 获取json节点数据
     * @param json
     * @param element
     * @return
     */
	public static String getString(String json,String element){
    	JsonParser parser = new JsonParser();
    	return parser.parse(json).getAsJsonObject().get(element).getAsString();
    }
      
    /** 
     * 根据json字符串返回Map对象 
     * @param json 
     * @return 
     */  
    public static Map<String,Object> toMap(String json){  
        return GsonUtil.toMap(GsonUtil.parseJson(json));  
    }  
    
    /**
     * 按照key值的ASCII大小升序排列
     * @param json
     * @return
     */
    public static Map<String,Object> JsonOrderByAscii(String json){
		return GsonUtil.JsonOrderByAscii(GsonUtil.parseJson(json));
    }

	/** 
	 * 解析json字符串，并按照key值的ASCII码大小排序
     * @param json 
     * @return 
     */  
    public static Map<String, Object> JsonOrderByAscii(JsonObject json){  
        Map<String, Object> map = new TreeMap<String, Object>();  
        Set<Entry<String, JsonElement>> entrySet = json.entrySet();  
        for (Iterator<Entry<String, JsonElement>> iter = entrySet.iterator(); iter.hasNext(); ){  
            Entry<String, JsonElement> entry = iter.next();  
            String key = entry.getKey();  
            JsonElement value = entry.getValue();  
            if(value instanceof JsonArray) {
                map.put((String) key, toList((JsonArray) value));  
        	} else if(value instanceof JsonObject) {
                map.put((String) key, JsonOrderByAscii((JsonObject) value));  
        	} else {
	           	 if(value.toString().indexOf("\"") > -1) {
	           		map.put((String) key, value.getAsString());
	        	 } else {
	        		map.put((String) key, value);  
	        	 }
            }   
        }  
        return map;  
    }  
    
    /**
     * 将JSONObjec对象转换成Map-List集合 
     * @param json
     * @return
     */
	public static Map<String,Object> toMap(JsonObject json){
         Set<Entry<String, JsonElement>> entrySet = json.entrySet();  
         for (Iterator<Entry<String, JsonElement>> iter = entrySet.iterator(); iter.hasNext(); ){  
             Entry<String, JsonElement> entry = iter.next();  
             String key = entry.getKey();
             JsonElement value = entry.getValue();  
             if(value instanceof JsonArray) {
            	 tojsonList((JsonArray) value);  
             } else if(value instanceof JsonObject)  {
            	 toMap((JsonObject) value);  
             } else  {
            	 if(value.toString().indexOf("\"") > -1) {
            		 jsonap.put((String) key, value.getAsString());
            	 } else {
            		 jsonap.put((String) key, value); 
            	 }
             }
         }  
         String data="{'page_no':'1','page_size':'100','code':'1234','status':'1','begin_date':'2014-11-11 10:00:00','end_date':'2014-11-28 16:31:00'}";
         return jsonap;  
    }
    
    
    /** 
     * 将JSONArray对象转换成List集合 
     * @param json 
     * @return 
     */  
    public static List<Object> toList(JsonArray json){  
        List<Object> list = new ArrayList<Object>();  
        for (int i=0; i<json.size(); i++){  
            Object value = json.get(i);  
            if(value instanceof JsonArray){  
                list.add(toList((JsonArray) value));  
            }  
            else if(value instanceof JsonObject){  
                list.add(JsonOrderByAscii((JsonObject) value));  
            }  
            else{  
                list.add(value);  
            }  
        }  
        return list;  
    }  
    
    /** 
     * 将JSONArray对象转换成List集合 
     * @param json 
     * @return 
     */  
    @SuppressWarnings("unused")
	public static void tojsonList(JsonArray json){  
    	List<Object> list = new ArrayList<Object>();  
    	for (int i=0; i<json.size(); i++){  
    		Object value = json.get(i);  
    		if(value instanceof JsonArray){  
    			tojsonList((JsonArray) value);  
    		}  
    		else if(value instanceof JsonObject){  
    			toMap((JsonObject) value);  
    		}  
    	}  
    }
    
    /*
     * 将字符串转换成JsonArray
     */
    public static JsonArray toJsonArray(String str) {
		//创建一个JsonParser
		JsonParser parser = new JsonParser();

		//通过JsonParser对象可以把json格式的字符串解析成一个JsonElement对象
		JsonElement el = parser.parse(str);

		//把JsonElement对象转换成JsonArray
		JsonArray jsonArray = null;
		if(el.isJsonArray()){
		jsonArray = el.getAsJsonArray();
		}
		return jsonArray;
	}
    
    private static final Gson gson = new Gson();
	
	/**
	 * 序列化对象
	 * 
	 * @param obj
	 * @return
	 */
	public static String toJson(Object obj){  
		return gson.toJson(obj);
    }

	/**
	 * 反序列化对象
	 * 
	 * @param json
	 * @param clazz
	 * @return
	 */
    public static <T> T  fromJson(String json,Class<T> clazz){
    	return gson.fromJson(json, clazz);
    }
    
    /**
     * 反序列化对象
     * 
     * @param json
     * @return
     */
    public static Object fromJson(String json,Type type){
    	return gson.fromJson(json, type);
    }
    
    /**
	 * 验证一个字符串是否是合法的JSON串
	 *
	 * @return true-合法 ，false-非法
	 */
	public static boolean validate(String json) {
		return (new JsonValidator()).validate(json);
	}
	
	/**
	 * 校验是否为get方式提交的参数
	 * 
	 * @param paras
	 * @return
	 */
	public static boolean validateModel(String paras){
		return Pattern.compile("\\w*[^&=]*=\\w*[^&=]*&?").matcher(paras).find();
	}
	
	/**
	 * 格式化为json格式
	 * 请使用@see{JsonXmlUtils}的 xml2JSON方法
	 * 
	 * @param result
	 * @return
	 */
	@Deprecated
	public static String fmt2Json(String result){
		if(validate(result)){
			return result;
		}
		result = result.replaceAll(">\\s*<", "><").replaceAll("<\\?([^>|^\\?]*)\\?>", "");
		String json = result;
		Matcher matcher = Pattern.compile("<([^>|^/]*)>").matcher(result);
		while(matcher.find()){
			for (int i = 0; i < matcher.groupCount(); i++) {
				String s = matcher.group(i+1);
				json = json.replaceAll("<"+s+">([^<|^\"]*)</"+s+">", "\""+s+"\":\"$1\",");
			}
		}
		json = "{"+json.replaceAll(",?</([^<]*)>", "},").replaceAll("<([^<]*)>", "\"$1\":{")+"}";
		json =json.replaceAll(",}","}").replaceAll("(\\s*\\w*)=\"(\\w*)\"\\s*\"?", "\"$1\":\"$2\",")
				.replaceAll("\\s+([^{]*),:" ,  ":{\"@attributes\":{\"$1},").replace("},{", "},")
				.replaceAll("},([^}|^\"]*)}", "},\"@value\":\"$1\"}");
		return json;
	}
	
	/**
	 * 格式化为xml格式
	 * 请使用@see{JsonXmlUtils}的 json2XML方法
	 * 
	 * @param json
	 * @return
	 */
	@Deprecated
	public static String fmt2Xml(String json){
		return fmt2Xml(json, "root");
	}
	
	/**
	 * 格式化为xml格式
	 * 请使用@see{JsonXmlUtils}的 json2XML方法
	 * 
	 * @param json
	 * @param rootEle
	 * @return
	 */
	@Deprecated
	public static String fmt2Xml(String json, String rootEle){
		if(!validate(json)){
			return fmt2Xml(fmt2Json(json),rootEle);
		}
		rootEle = rootEle.replaceAll("\\W", "");
		rootEle = StringUtil.isEmpty(rootEle)? "root": rootEle;
//		return json.replaceAll("\"(\\w*)\":\"?([^\",}]*)\"?,?","<$1>$2</$1>").replaceAll("\\{([^\\}]*)\\}", "<?xml version=\"1.0\" encoding=\"utf-8\" ?><"+rootEle+">$1"+"</"+rootEle+">");
		
		//去掉@attributes和@value
		Pattern pattern = Pattern.compile("\"@attributes\":\\{([^}]*)}");
		Matcher matcher = pattern.matcher(json);
		while(matcher.find()){
			String s = "";
			for (int i = 0; i < matcher.groupCount(); i++) {
				s = matcher.group(i+1);
				s = s.replaceAll("\"(\\w*)\":\"([^\"]*)\",?", " $1=$2");
			}
			json = json.replaceAll("[^,]\"(\\w*)\":\\{\"@attributes\":\\{[^}]*},?","{\"$1"+s+"\":{");
			//matcher = pattern.matcher(json);
		}
		json = json.replaceAll("\\{\"@value\":\"([^\"]*)\"}", "\"$1\"");
		
		//处理嵌套
		json = json.replaceAll("\"([\\w|\\s|=]*)\":\"([^\",{}]+)\",?", "<$1>$2</$1>");
		pattern = Pattern.compile("\"(\\w*)\":\\{([^{}]*)},?");
		while(pattern.matcher(json).find()){
			json = pattern.matcher(json).replaceAll("<$1>$2</$1>");
		}
		
		pattern = Pattern.compile("\"([\\w|\\s|=]*)\":([^}\"]*)},?");
		while(pattern.matcher(json).find()){
			json = pattern.matcher(json).replaceAll("<$1>$2</$1>");
		}
		
		json = json.replaceAll("(\\w*)=(\\w*)", "$1=\"$2\"").replaceAll("/(\\w*)\\s[\\w*)=\"\\w*\"\\s?]*", "/$1").replaceAll("[{|}]", "");
		json = "<?xml version=\"1.0\" ?><"+rootEle+">"+json+"</"+rootEle+">";
		return json;
	}

	public static void main(String[] args) {
		String str = "<Response a=\"123\" b=\"000\">"
								+ "<status  c=\"123\" d=\"000\">201</status>"
								+ "<A><status1>201</status1><message1>APP被用户自己禁用</message1></A>"
								+ "<A2><status1>201</status1><message1>APP被用户自己禁用</message1></A2>"
								+ "<B>"
								+ "	<BB><status1>201</status1><message1>APP被用户自己禁用</message1></BB>"
								+ "</B>"
								+ "<message>APP被用户自己禁用，请在控制台解禁</message>"
								+ "<C><status1>201</status1><message1>APP被用户自己禁用</message1></C>"
							+ "</Response>";
		
		String json = fmt2Json(str);
		String xml = fmt2Xml(json);
		System.out.println("xml转化为json：" + json);
		System.out.println("json转化为xml：" + xml);

		
		
//    	String json = "{'status':200,'message':'success','data':{'createTime':1476096569,'updateTime':1476164641,'orderNo':'10110844780184','memberId':'15000000000990184','orderAmt':'1','realPayAmt':'1','orderStatus':'TRADE_FINISHED','usePoint':'0','usePointDiscount':'0','payOrderNo':'20161010184948001719782','returnPoint':'0','remark':{'FFClientType':'1','FFClientVersion':'49000000','adId':'GP1476092639622000000','verificationOperatorId':'1234','userIsWhite':false,'useRemainingBudget':'0','flashSale':'1','ddId':'6857d6537d1da4cbf115390e04a84cddd83e06fb','wdId':'7a47ff976a2df861559c1cac5f4c5c75'},'storeId':'10021090','storeName':'摩提工房（口龙之梦店）','orderMark':{'refundFlag':0,'auditRefundFailFlag':0,'useCouponFlag':0,'payFlag':1,'usePointFlag':0,'orderNo':'10110844780184','financeProjectFailFlag':0,'returnPointFlag':0,'payTimeoutFlag':0,'auditRefundSuccessFlag':0,'createTicketFlag':0,'applyRefundFlag':0},'productList':[{'productId':'50375','productCount':1,'productPrice':'1','storeId':'10021090','merchantId':'10025580','productInfo':{'consumedRefundDays':7,'priceBeforePromotion':6,'settleSubjectId':'10646','consumedRefundFlag':1,'spacId':'50375','attribute':'','pic':'T1KZ_TByE_1RCvBVdK','useValidType':0,'brandId':'10001250','tradeCode':7013,'parentId':'1000025665','title':'超级大面包','useValidBegin':'','price':'6','useValidDays':3,'operateCutAmount':'2','settleSubject':1,'useValidEnd':'','merchantCutAmount':'3','redundancy03':'0'},'title':'超级大面包','picture':'T1KZ_TByE_1RCvBVdK','applyRefundFlag':0,'refundFinishedFlag':0}],'useCouponList':[],'useCouponDiscount':'0','offlinePayments':[],'payInfoPos':[{'amount':'100','amountYuan':'1','createTime':1476096590,'orderNo':'10110844780184','payOrderNo':'20161010184948001719782','payType':1001,'tradeCode':7013,'payTypeDetail':0}],'payTime':1476096590,'pickUpInfo':{'merchantId':'10025580','orderNo':'10110844780184','orderStatus':0,'phoneNum':'18101805638','sign':'0732395162','signStatus':2,'checkTime':0,'validateTime':1476355790000,'operator':'1234'},'orderCode':0}}";
//    	Map<String, Object> map = toMap(json);
//    	System.out.println(map.get("signStatus"));
    
    }
      
}
