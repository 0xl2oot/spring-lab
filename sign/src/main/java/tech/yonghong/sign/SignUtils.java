package tech.yonghong.sign;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.net.URLEncoder;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.Map;
import java.util.TreeMap;

/**
 * @author yonghong
 * @date 2020/3/13
 **/
@Slf4j
public class SignUtils {

    private static final Charset UTF_8 = StandardCharsets.UTF_8;

    public static String buildQueryString(Map<String, String> map) {
        String retStr = "";
        for (String key : map.keySet()) {
            String value = map.get(key);
            if (retStr.length() == 0) {
                retStr += '?';
            } else {
                retStr += '&';
            }
            retStr += key + '=' + URLEncoder.encode(value, UTF_8);
        }
        return retStr;
    }

    @SneakyThrows
    public static String sign(String secretKey, String signPlainText) {
        Mac mac = Mac.getInstance("HmacSHA256");
        byte[] hash;
        SecretKeySpec secretKeySpec = new SecretKeySpec(secretKey.getBytes(UTF_8), mac.getAlgorithm());
        mac.init(secretKeySpec);
        hash = mac.doFinal(signPlainText.getBytes(UTF_8));
        return new String(Base64.getEncoder().encode(hash));
    }

    public static String makeSignPlainText(TreeMap<String, String> map,
                                           String method, String host, String path) {
        String retStr = "";
        retStr += method;
        retStr += host;
        retStr += path;
        retStr += buildParamStr(map);
        return retStr;
    }

    private static String buildParamStr(TreeMap<String, String> map) {
        String retStr = "";
        for (String key : map.keySet()) {
            String value = map.get(key);
            if (retStr.length() == 0) {
                retStr += '?';
            } else {
                retStr += '&';
            }
            retStr += key + '=' + value;
        }
        return retStr;
    }
}
