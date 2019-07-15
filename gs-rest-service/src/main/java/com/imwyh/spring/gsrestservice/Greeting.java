package com.imwyh.spring.gsrestservice;

/**
 * 实体类，当通过 HTTP 返回时，以 JSON 形式返回
 *
 * @author yonghong.wang
 * @date 2019-02-15 13:36
 */
public class Greeting {

    private final long id;
    private final String content;

    public Greeting(long id, String content) {
        this.id = id;
        this.content = content;
    }

    public long getId() {
        return id;
    }

    public String getContent() {
        return content;
    }
}
