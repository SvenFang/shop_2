package com.cqgk.clerk.http;


import com.cqgk.clerk.config.Key;
import com.cqgk.clerk.helper.PreferencesHelper;

import org.xutils.http.app.ResponseParser;
import org.xutils.http.request.UriRequest;

import java.lang.reflect.Type;


/**
 *
 */
public class JsonResponseParser implements ResponseParser {

    private CommonHttpResponse theResponse;

    @Override
    public void checkResponse(UriRequest request) throws Throwable {


        getBody().setRequestUrl(request.getRequestUri());

        // custom check ?
        // check header ?
        if (request.getResponseHeader("Auth-Token") != null &&
                !request.getResponseHeader("Auth-Token").isEmpty()) {

            String token = request.getResponseHeader("Auth-Token");
            if (token != null) {
                PreferencesHelper.save(Key.TOKEN, token);
            }
        }
    }

    protected CommonHttpResponse getBody() {
        if (theResponse == null)
            theResponse = new CommonHttpResponse();

        return theResponse;
    }

    /**
     * 转换result为resultType类型的对象
     *
     * @param resultType  返回值类型(可能带有泛型信息)
     * @param resultClass 返回值类型
     * @param result      字符串数据
     * @return
     * @throws Throwable
     */
    @Override
    public Object parse(Type resultType, Class<?> resultClass, String result) throws Throwable {
        getBody().setContent(result);
        return theResponse;

    }
}
