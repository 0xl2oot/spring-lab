package cc.capslock.spring.gsrestservice;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.atomic.AtomicLong;

/**
 * RestController 是 Restful 接口必须要有的注解，这样 Spring 才能扫描到
 *
 * @author yonghong.wang
 * @date 2019-02-15 13:38
 */
@RestController
public class GreetingController {

    // 字符串模板
    private static final String template = "Hello, %s!";
    // concurrent 包中的一个类，能保证并发竞争不太激烈的情况的读写
    private final AtomicLong counter = new AtomicLong();

    // RequestMapping 绑定了 /greeting 这个 URL 与 greeting() 方法。但并没有声明是什么 HTTP 方法
    // 如需明确是什么方法，应该这样写 `@RequestMapping(method = RequestMethod.GET, value = "/greeting")` 或者 `@GetMapping("/greeting")`
    @RequestMapping("/greeting")
    public Greeting greeting(@RequestParam(value="name", defaultValue="World") String name) {
        // 返回 JSON
        return new Greeting(counter.incrementAndGet(),
                String.format(template, name));
    }
}
