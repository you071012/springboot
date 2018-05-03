/**
 * 
 */
package com.ukar.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.SocketTimeoutException;
import java.net.URL;
import java.net.URLEncoder;
import java.security.SecureRandom;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

import com.alibaba.fastjson.JSON;
import org.apache.log4j.Logger;



/**
 * 
 * <p>
 * Post请求
 * </p>
 * 
 * <p>
 * Post请求
 * </p>
 * 
 ******************************************************** 
 * Date Author Changes 2011-11-23 Eric Cao 创建
 ******************************************************** 
 */
public class PostRequest {

	private static Logger log = Logger.getLogger(PostRequest.class);

	/**
	 * Post请求(默认使用UTF-8)
	 * 
	 * @param uri
	 *            请求的URL
	 * @param data
	 *            请求参数,待请求的参数采用name=value&name=value模式集成.
	 * @return 应答报文 
	 * @throws IOException
	 *             如果请求失败，抛出该异常
	 */
	public static String postRequest(String uri, String data) throws IOException {

		log.info("POST:"+uri+", msg:"+data);
		trustAllHosts();// 信任所有HTTPS主机.
		URL url = new URL(uri);
		HttpURLConnection conn = null;
		StringBuffer resp = new StringBuffer();
		try {
			conn = (HttpURLConnection) url.openConnection();
			if ( url.getProtocol().equalsIgnoreCase("HTTPS") ) {// 为HTTPS地址，添加域名验证策略
				((HttpsURLConnection) conn).setHostnameVerifier(verifier);
			}
			if(url.getHost().contains("mo9.com") && uri.contains("proxydeduct/pay.mhtml")){
				log.info("post-代收-url	："+uri);
                //代收链接超时时间要延长
				conn.setConnectTimeout(5000);// 连接超时
                conn.setReadTimeout(30000);// 超时时限
			}else if(url.getHost().contains("mo9.com"))
			{/**mo9类部地址,仅仅允许2秒超时.*/
			    conn.setConnectTimeout(500);// 连接超时
                conn.setReadTimeout(4500);// 超时时限
			}
			else if((uri != null && uri.contains("pay_to_email=migugame%40139.com"))
			        || (data!= null && data.contains("pay_to_email=migugame%40139.com")))
			{
			    /**
                 * 江苏移动，需要把回调通知超时时长改到30s,
                 */
                conn.setConnectTimeout(5000);// 连接超时
                conn.setReadTimeout(30000);// 超时时限
			}
			else
			{
    			conn.setConnectTimeout(5000);// 5s超时
    			conn.setReadTimeout(8000);// 超时时限
			}
			conn.setRequestMethod("POST");
			// 这里是关键，表示我们要向链接里输出内容
			conn.setDoOutput(true);
			// 获得连接输出流
			OutputStreamWriter out = new OutputStreamWriter(conn.getOutputStream());
			// 把数据写入
			StringBuffer param = new StringBuffer(data);
			if ( param.length() > 0 && param.charAt(0) == '&' ) {// Post请求，删除第一个'&符号'
				out.write(param.substring(1));
			} else {
				out.write(param.toString());
			}
			out.flush();
			out.close();
			// 到这里已经完成了，不过我们还是看看返回信息吧，他的注册返回信息也在此页面
			BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));

			String inputLine = reader.readLine();
			while (inputLine != null) {
				resp.append(inputLine);
				inputLine = reader.readLine();
			}
			reader.close();
		} finally {
			if ( conn != null ) {
				conn.disconnect();
			}
		}
		log.info("POST RESULT:"+resp.toString());
		return resp.toString();
	}
	
	public static String postRequest(String uri, String data,Integer timeout ) throws IOException {

		log.info("POST:"+uri+", msg:"+data);
		trustAllHosts();// 信任所有HTTPS主机.
		URL url = new URL(uri);
		HttpURLConnection conn = null;
		StringBuffer resp = new StringBuffer();
		try {
			conn = (HttpURLConnection) url.openConnection();
			if ( url.getProtocol().equalsIgnoreCase("HTTPS") ) {// 为HTTPS地址，添加域名验证策略
				((HttpsURLConnection) conn).setHostnameVerifier(verifier);
			}
			
			if(url.getHost().contains("mo9.com"))
			{/**mo9类部地址,仅仅允许2秒超时.*/
			    conn.setConnectTimeout(500);// 连接超时
                conn.setReadTimeout(4500);// 超时时限
			}
			else if((uri != null && uri.contains("pay_to_email=migugame%40139.com"))
                    || (data!= null && data.contains("pay_to_email=migugame%40139.com")))
            {
                /**
                 * 江苏移动，需要把回调通知超时时长改到30s,
                 */
                conn.setConnectTimeout(5000);// 连接超时
                conn.setReadTimeout(30000);// 超时时限
            }
			else
			{
				if(timeout == null || timeout == 0){
					timeout = 5000;// 5s超时
				}
    			conn.setConnectTimeout(timeout);
    			conn.setReadTimeout(8000);// 超时时限
			}
			conn.setRequestMethod("POST");
			// 这里是关键，表示我们要向链接里输出内容
			conn.setDoOutput(true);
			// 获得连接输出流
			OutputStreamWriter out = new OutputStreamWriter(conn.getOutputStream());
			// 把数据写入
			StringBuffer param = new StringBuffer(data);
			if ( param.length() > 0 && param.charAt(0) == '&' ) {// Post请求，删除第一个'&符号'
				out.write(param.substring(1));
			} else {
				out.write(param.toString());
			}
			out.flush();
			out.close();
			// 到这里已经完成了，不过我们还是看看返回信息吧，他的注册返回信息也在此页面
			BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));

			String inputLine = reader.readLine();
			while (inputLine != null) {
				resp.append(inputLine);
				inputLine = reader.readLine();
			}
			reader.close();
		} finally {
			if ( conn != null ) {
				conn.disconnect();
			}
		}
		log.info("POST RESULT:"+resp.toString());
		return resp.toString();
	}

	/**
	 * Post请求(默认使用UTF-8)
	 * 
	 * @param uri
	 *            请求的URL
	 * @param params
	 *            请求参数
	 * @return 应答报文
	 * @throws IOException
	 *             如果请求失败，抛出该异常
	 */
	public static String postRequest(String uri, Map<String, String> params) throws IOException {

		return postRequest(uri, params, "UTF-8");
	}

	/**
	 * 按照指定的编码发起Post请求.
	 * 
	 * @param uri
	 *            请求的URL
	 * @param params
	 *            请求参数
	 * @return 应答报文
	 * @throws IOException
	 *             如果请求失败，抛出该异常
	 */
	public static String postRequest(String uri, Map<String, String> params, String charSet) throws IOException {

		StringBuffer param = new StringBuffer();
		Set<String> keys = params.keySet();
		for ( String key : keys ) {
			// 将请求参数进行URL编码
			String value = URLEncoder.encode(params.get(key), charSet);
			param.append("&" + key + "=" + value);
		}
		return postRequest(uri, param.toString());
	}

	/**
	 * HTTS域名验证
	 */
	private static final HostnameVerifier verifier = new HostnameVerifier() {

		public boolean verify(String hostname, SSLSession session) {

			return true;
		}
	};

	/**
	 * HTTPS信任所有主机 trustAllHosts:
	 * 
	 */
	private static final void trustAllHosts() {

		TrustManager[] trustEverythingTrustManager = new TrustManager[] { new X509TrustManager() {

			public void checkClientTrusted(X509Certificate[] chain, String authType) throws CertificateException {

				// TODO Auto-generated method stub
			}

			public void checkServerTrusted(X509Certificate[] chain, String authType) throws CertificateException {

				// TODO Auto-generated method stub

			}

			public X509Certificate[] getAcceptedIssuers() {

				// TODO Auto-generated method stub
				return null;
			}

		} };

		SSLContext sc;
		try {
			sc = SSLContext.getInstance("TLS");
			sc.init(null, trustEverythingTrustManager, new SecureRandom());
			HttpsURLConnection.setDefaultSSLSocketFactory(sc.getSocketFactory());
		} catch (Exception e) {
		}
	}
	
	 public static String httpRequest(String uri, String content, Map<String, String> headerProperty, RequestEnum requestMethod) throws IOException {
	        return httpRequest(uri, content, headerProperty, requestMethod, 0);
	    }

    public static String httpRequest(String uri, String content, Map<String, String> headerProperty, RequestEnum requestMethod, int repeatNum) throws IOException {
        try {
            return request(uri, content, headerProperty, requestMethod);
        } catch (SocketTimeoutException te) {
            repeatNum++;
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            log.debug("连接超时重试"+repeatNum+"次");
            if (repeatNum > 3) {
                log.warn("调用api"+uri+"重试超过三次失败");
                return null;
            }
            httpRequest(uri, content, headerProperty, requestMethod, repeatNum);
        }
        return null;
    }

    public static String request(String uri, String content, Map<String, String> headerProperty, RequestEnum requestMethod) throws IOException {
        log.debug(requestMethod.getRequestMethod() + ":" + uri + ", content:" + content + ",headerProperty");
        trustAllHosts();// 信任所有HTTPS主机.
        URL url = new URL(uri);
        HttpURLConnection conn = null;
        StringBuffer resp = new StringBuffer();
        try {
            conn = (HttpURLConnection) url.openConnection();
            if (url.getProtocol().equalsIgnoreCase("HTTPS")) {// 为HTTPS地址，添加域名验证策略
                ((HttpsURLConnection) conn).setHostnameVerifier(verifier);
            }
            if (url.getHost().contains("mo9.com")) {/**mo9类部地址,仅仅允许2秒超时.*/
                conn.setConnectTimeout(500);// 连接超时
                conn.setReadTimeout(4500);// 超时时限
            }
            //设置请求头参数
            for (Map.Entry<String, String> entry : headerProperty.entrySet()) {
                conn.setRequestProperty(entry.getKey(), entry.getValue());
            }
            conn.setRequestMethod(requestMethod.getRequestMethod());
            // 这里是关键，表示我们要向链接里输出内容
            conn.setDoOutput(true);
            // 获得连接输出流
            if (requestMethod == RequestEnum.GET) { //get请求
                conn.connect();
            }
            if (null != content) {
                OutputStreamWriter out = new OutputStreamWriter(conn.getOutputStream());
                // 把数据写入
                out.write(content);
                out.flush();
                out.close();
            }
            // 到这里已经完成了，不过我们还是看看返回信息吧，他的注册返回信息也在此页面
            InputStream inputStream = conn.getInputStream();
            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
            String inputLine = reader.readLine();
            while (inputLine != null) {
                resp.append(inputLine);
                inputLine = reader.readLine();
            }
            reader.close();
        } finally {
            if (conn != null) {
                conn.disconnect();
            }
        }
        log.debug(requestMethod.getRequestMethod() + " RESULT:" + resp.toString());
        return resp.toString();
    }
	    
    public static enum RequestEnum {
        POST("POST"), GET("GET");
        private String requestMethod;

        RequestEnum(String requestMethod) {
            this.requestMethod = requestMethod;
        }

        public String getRequestMethod() {
            return requestMethod;
        }
    }


	public static void main(String[] arg) throws IOException {
		
//	    Map<String,String> params = new HashMap<String,String>();
//	    params.put("client_id", "S_admin");
//	    params.put("valid_type", "secret");
//	    params.put("valid_token", "11111111");
//	    params.put("mobile", "15618550929");
//	    params.put("template_name", "PINCODE");
//	    params.put("template_data", "captcha:123456");
//	    params.put("template_tags", "CN");

		Map<String, Object> tempParams = new HashMap<String, Object>();
		tempParams.put("pinCode", "123456");//模板占位符内容
		Map<String, String> params = new HashMap<String, String>();
		params.put("mobile", "13213173517");//手机号
		params.put("template_name", "EXPinCode");// 模板名称
		params.put("template_data", JSON.toJSONString(tempParams));
		params.put("template_tags", "CN");// 国家简称

		params.put("snc_version", "2.0");// 版本，目前固定2.0
		params.put("biz_sys", "TEST");// 发送者服务名，可自定义，一般是项目英文缩写，如江湖救急=JHJJ
		params.put("product_name", "TEST");// 发送者服务名，一般是项目英文缩写，如江湖救急=JHJJ
		params.put("biz_type", "deal_captcha");// 短信类型,请谨慎填写，最好填写前咨询一下，详情见附录1

		/**
		 * 固定参数信息
		 */
		params.put("client_id", "S_soaDefClient");
		params.put("valid_type", "secret");
		params.put("valid_token", "kdjdi95@d7");
		String str = PostRequest.postRequest("https://www.mo9.com/snc/sms/sendSms", params);
		System.out.println(str);
	}
}
