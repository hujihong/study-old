package com.github.im.spring4;

import org.springframework.http.ResponseEntity;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.util.concurrent.ListenableFutureCallback;
import org.springframework.web.client.AsyncRestTemplate;

import com.github.im.common.test.User;

public class AsyncRestTemplateTest {

    public static void main(String[] args) {
        AsyncRestTemplate template = new AsyncRestTemplate();
        //调用完后立即返回（没有阻塞）
        ListenableFuture<ResponseEntity<User>> future = template.getForEntity("http://localhost:9080/spring4/api", User.class);
        //设置异步回调
        future.addCallback(new ListenableFutureCallback<ResponseEntity<User>>() {
            @Override
            public void onSuccess(ResponseEntity<User> result) {
                System.out.println("======client get result : " + result.getBody());
            }

            @Override
            public void onFailure(Throwable t) {
                System.out.println("======client failure : " + t);
            }
        });
        System.out.println("==no wait");
    }
    
}
