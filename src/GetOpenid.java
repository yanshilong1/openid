import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.apache.log4j.Logger;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.URL;
import java.net.URLConnection;

/**
 * @Author: yanshilong
 * @Date: 18-10-14 下午10:53
 * @Version 1.0
 */
public class GetOpenid {


    private static Logger log = Logger.getLogger(GetOpenid.class);


    //获取微信的openid'
    public String getOpenId(String wxCode) {
        //微信端登录code值
        log.info("从前端以获取到的code为=======" + wxCode);
//        ResourceBundle resource = ResourceBundle.getBundle("weixin");	//读取属性文件
     //   String requestUrl = "https://api.weixin.qq.com/sns/jscode2session";
//        Map<String, String> requestUrlParam = new HashMap<String, String>();
//        requestUrlParam.put("appid", "wxa24c081314c89a2a");    //开发者设置中的appId
//        requestUrlParam.put("secret", "daolvfacom2Fwebpagesvsvvdbjdbjdf");    //开发者设置中的appSecret
//        requestUrlParam.put("js_code", wxCode);    //小程序调用wx.login返回的code
//        requestUrlParam.put("grant_type", "authorization_code");    //默认参数
//        String url = "https://open.weixin.qq.com/connect/oauth2/authorize?" +
//                "appid=wxa24c081314c89a2a&redirect_" +
//                "uri=http%3A%2F%2Fnba.bluewebgame.com%2Foauth_response.php&response_type=code&scope=snsapi_userinfo&" +
//                "state=STATE#wechat_redirect";


        //wxa24c081314c89a2a----real
//daolvfacom2Fwebpagesvsvvdbjdbjdf
        //wxff92e720a97562f6
        //
        //59ea9d75b64fdc7f214405a9140ebdce

        String url = "https://api.weixin.qq.com/sns/oauth2/access_token?"+
                "appid=88888888888888888888888888888888888888&"+
                "secret=888888888888888888888888888888888888&" +
                "code="+wxCode+"&"+
                "grant_type=authorization_code";

//       String param="appid="+paramMap.get("appid")+"&secret="+paramMap.get("secret")
//                +"&code="+paramMap.get("code")+"&grant_type=authorization_code";

//        String path=url+param;

        // https://api.weixin.qq.com/sns/oauth2/access_token?
        // appid=APPID&secret=SECRET&code=CODE&grant_type=authorization_code


        //发送post请求读取调用微信 https://api.weixin.qq.com/sns/jscode2session 接口获取openid用户唯一标识
        JSONObject jsonObject = JSON.parseObject(sendPost(url));

        String openid = "";
        try {
            openid = (String) jsonObject.get("openid");

        } catch (Exception e) {
            log.error(e.getMessage());
        }
        log.info("已从微信服务器获取到openid------->" + openid);
        log.info("已从微信服务器获取到openid------->" + openid);

        return openid;
    }


    public String sendPost(String url) {
        PrintWriter out = null;
        BufferedReader in = null;
        String result = "";



        https://api.weixin.qq.com/sns/oauth2/access_token?
        // appid=wxa24c081314c89a2a&
        // secret=daolvfacom2Fwebpagesvsvvdbjdbjdf&
        // code=011RZeDZ0SDBn02gjIBZ0eHiDZ0RZeDl&
        // grant_type=authorization_code
        log.info("request -------->" + url);
        try {
            URL realUrl = new URL(url);
            // 打开和URL之间的连接
            URLConnection conn = realUrl.openConnection();
            // 设置通用的请求属性
            conn.setRequestProperty("accept", "*/*");
            conn.setRequestProperty("connection", "Keep-Alive");
            conn.setRequestProperty("Accept-Charset", "utf-8");
            conn.setRequestProperty("user-agent", "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
            // 发送POST请求必须设置如下两行
            conn.setDoOutput(true);
            conn.setDoInput(true);
            // 获取URLConnection对象对应的输出流
            out = new PrintWriter(conn.getOutputStream());
            // 发送请求参数
             out.print(url);
            // flush输出流的缓冲
            out.flush();
            // 定义BufferedReader输入流来读取URL的响应
            in = new BufferedReader(new InputStreamReader(conn.getInputStream(), "UTF-8"));
            String line;
            while ((line = in.readLine()) != null) {
                result += line;
            }
            log.info("输入流来读取URL的响应------>" + result);
        } catch (Exception e) {
            log.error(e.getMessage());
            System.out.println(e.getMessage());
        }
        //使用finally块来关闭输出流、输入流
        finally {
            try {
                if (out != null) {
                    out.close();
                }
                if (in != null) {
                    in.close();
                }
            } catch (IOException ex) {
                log.error(ex.getMessage());
                ex.printStackTrace();
            }
        }
        return result;
    }

    public static void main(String[] args) {
        GetOpenid Openid = new GetOpenid();
        String code = "=061iJJvT0xhcFX1qQVuT0FCLvT0iJJvJ";
        String openid = Openid.getOpenId(code);
    }

}

