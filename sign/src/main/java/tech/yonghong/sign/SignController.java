package tech.yonghong.sign;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

/**
 * @author yonghong
 * @date 2020/3/12
 **/
@Slf4j
@RestController
public class SignController {

    @NeedSign
    @GetMapping("/api/get")
    public String getMethod(HttpServletRequest request, @Valid RecordSearchForm form) {
        return "ok";
    }

    @GetMapping("/api/gethh")
    public String getMethodhh(HttpServletRequest request, @Valid RecordSearchForm form) {
        return "ok";
    }

    @PostMapping("/api/post")
    public String postMethod(HttpServletRequest request) {
        return "ok";
    }
}
