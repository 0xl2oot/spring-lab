package tech.yonghong.sign;

import lombok.extern.slf4j.Slf4j;
import org.apache.http.NameValuePair;
import org.apache.http.client.utils.URLEncodedUtils;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.stream.Collectors;

/**
 * @author yonghong
 * @date 2020/3/13
 **/
@Slf4j
@Component
public class NeedSignInterceptor extends HandlerInterceptorAdapter {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        if (handler instanceof HandlerMethod) {
            NeedSign annotation = ((HandlerMethod) handler).getMethodAnnotation(NeedSign.class);
            if (annotation != null) {
                String method = request.getMethod();
                String host = request.getServerName();
                String path = request.getRequestURI();
                String queryString = request.getQueryString();
                log.info(method);
                log.info(host);
                log.info(path);
                log.info(queryString);
                List<NameValuePair> nameValuePairs = URLEncodedUtils.parse(queryString, StandardCharsets.UTF_8);
                Map<String, String> queryMap = nameValuePairs.stream()
                        .filter(nameValuePair -> !nameValuePair.getName().equals("sign"))
                        .collect(Collectors.toMap(NameValuePair::getName, NameValuePair::getValue));

                String signPlainText = SignUtils.makeSignPlainText(new TreeMap<>(queryMap), method, host, path);
                String sig = SignUtils.sign("xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx", signPlainText);
                log.info(signPlainText);
                log.info(sig);
            }
        }
        return true;
    }
}
