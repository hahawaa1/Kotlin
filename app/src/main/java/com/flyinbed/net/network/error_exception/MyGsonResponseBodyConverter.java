package com.flyinbed.net.network.error_exception;

import com.flyinbed.net.network.BaseResponseEntity;
import com.google.gson.Gson;
import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.Charset;

import okhttp3.MediaType;
import okhttp3.ResponseBody;
import retrofit2.Converter;

/**
 * 作者：Administrator on 2017/8/29 17:08
 * 邮箱：zhanghuaiha@gmail.com
 */

public class MyGsonResponseBodyConverter<T> implements Converter<ResponseBody,T> {
    private static final Charset UTF_8 = Charset.forName("UTF-8");
    private final Gson mGson;
    private final TypeAdapter<T> adapter;

    public MyGsonResponseBodyConverter(Gson mGson, TypeAdapter<T> adapter) {
        this.mGson = mGson;
        this.adapter = adapter;
    }

    @Override
    public T convert(ResponseBody value) throws IOException {
        String response = value.string();
        BaseResponseEntity result = mGson.fromJson(response, BaseResponseEntity.class);
        //判断code可自己改动
//        if (result.code != 200) {
//            value.close();
//            throw new ApiException(result.code, result.message);
//        }
        MediaType mediaType = value.contentType();
        Charset charset = mediaType != null ? mediaType.charset(UTF_8) : UTF_8;
        ByteArrayInputStream bis = new ByteArrayInputStream(response.getBytes());
        InputStreamReader reader = new InputStreamReader(bis,charset);
        JsonReader jsonReader = mGson.newJsonReader(reader);
        try {
            return adapter.read(jsonReader);
        } finally {
            value.close();
        }
    }
}
