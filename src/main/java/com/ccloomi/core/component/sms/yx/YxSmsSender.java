package com.ccloomi.core.component.sms.yx;

import static com.ccloomi.core.util.MapUtil.objectMap;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.net.URLDecoder;
import java.nio.charset.Charset;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.entity.BasicHttpEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.ccloomi.core.component.sms.SmsSender;
import com.ccloomi.core.component.sms.bean.SenderResponseBean;
import com.ccloomi.core.component.sms.bean.Sms;
import com.ccloomi.core.util.SecretUtil;
import com.ccloomi.core.util.StringUtil;
import com.fasterxml.jackson.databind.ObjectMapper;

import sun.misc.BASE64Encoder;
/**
 * © 2015-2016 CCLooMi.Inc Copyright
 * 类    名：YxSmsSender
 * 类 描 述：
 * 作    者：Chenxj
 * 邮    箱：chenios@foxmail.com
 * 日    期：2016年7月30日-下午6:40:05
 */
public class YxSmsSender implements SmsSender{
	private final Logger log=LoggerFactory.getLogger(getClass());
	private String url;
	private String version;
	private String accountSid;
	private String authToken;
	private String appId;
	public YxSmsSender(String url,String version,String accountSid,String authToken,String appId) {
		this.url=url;
		this.version=version;
		this.accountSid=accountSid;
		this.authToken=authToken;
		this.appId=appId;
	}
	@SuppressWarnings("unchecked")
	@Override
	public SenderResponseBean send(Sms sms) {
		if(sms.getMobile()==null||sms.getMobile().size()==0)return null;
		SenderResponseBean result = null;
		//id#param
		String data[]=sms.getMsg().split("#");
		String tempId=data[0];
		String param=data[1];
		String timestamp=new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());
		String signature=SecretUtil.MD5(accountSid+authToken+timestamp);
		try {
			URIBuilder uriBuilder=new URIBuilder(StringUtil.format("?/?/Accounts/?/Messages/templateSMS", url,version,accountSid))
			.addParameter("sig", signature);
			log.debug("云之讯短信调用地址::{}",uriBuilder.build());
			CloseableHttpClient client=HttpClients.createDefault();
			HttpPost post=new HttpPost(uriBuilder.build());
			post.setHeader("Accept", "application/json");
			post.setHeader("Content-Type", "application/json;charset=utf-8");
			post.setHeader("Authorization", new BASE64Encoder().encode((accountSid+":"+timestamp).getBytes(Charset.forName("UTF-8"))));
			BasicHttpEntity requestBody=new BasicHttpEntity();
			log.debug("post::Authorization:{}#{}",new BASE64Encoder().encode((accountSid+":"+timestamp).getBytes(Charset.forName("UTF-8"))),(accountSid+":"+timestamp));
			Map<String, Object>bodyMap=objectMap(
					"appId",appId,
					"templateId",tempId,
					"to",StringUtil.joinString(",", sms.getMobile().toArray()),
					"param",param);
			String json=new ObjectMapper().writeValueAsString(objectMap("templateSMS",bodyMap));
			log.debug("云之讯电信调用发送数据::{}", json);
			requestBody.setContent(new ByteArrayInputStream(json.getBytes(Charset.forName("UTF-8"))));
			requestBody.setContentLength(json.getBytes("UTF-8").length);
	        post.setEntity(requestBody);
	        CloseableHttpResponse response=client.execute(post);
			log.debug("短信接口调用状态::{}",response.getStatusLine());
			
			HttpEntity entity=response.getEntity();
			//处理返回结果
			InputStream in = entity.getContent();
			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			byte[] buffer = new byte[1024];
			int len = 0;
			while ((len = in.read(buffer)) != -1) {
				baos.write(buffer, 0, len);
			}
			String s=URLDecoder.decode(baos.toString(), "UTF-8");
			
			Map<String, Object>sMap= new ObjectMapper().readValue(s, Map.class);
			Map<String, Object>respMap=(Map<String, Object>) sMap.get("resp");
			if("000000".equals(respMap.get("respCode"))){
				Map<String, Object>m=(Map<String, Object>) respMap.get("templateSMS");
				result=new SenderResponseBean(new Date(), (String)respMap.get("respCode"), (String)m.get("smsId"));
			}else{
				result=new SenderResponseBean(new Date(), (String)respMap.get("respCode"));
			}

			log.debug("短信调用结果::{}", result);
			//释放资源
			EntityUtils.consume(entity);
			client.close();
			response.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

}
