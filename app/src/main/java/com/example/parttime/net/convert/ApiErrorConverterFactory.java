package com.example.parttime.net.convert;


import com.example.parttime.base.HttpResponse;
import com.example.parttime.net.exception.FalseException;
import com.google.gson.Gson;
import java.io.IOException;
import java.lang.annotation.Annotation;
import java.lang.reflect.Type;


import androidx.annotation.Nullable;
import okhttp3.MediaType;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Converter;
import retrofit2.Retrofit;

/**
 * 实现自定义转换器，
 * 统一 处理了 code 和 Error
 */
public class ApiErrorConverterFactory extends Converter.Factory {

    private final Converter.Factory mDelegateFactory;
    private final Gson gson;

    public ApiErrorConverterFactory(Converter.Factory mDelegateFactory) {
        this.mDelegateFactory = mDelegateFactory;
        gson = new Gson();
    }

    @Nullable
    @Override
    public Converter<?, RequestBody> requestBodyConverter(Type type, Annotation[] parameterAnnotations,
                                                          Annotation[] methodAnnotations, Retrofit retrofit) {
        return mDelegateFactory.requestBodyConverter(type, parameterAnnotations, methodAnnotations, retrofit);
    }

    @Nullable
    @Override
    public Converter<ResponseBody, ?> responseBodyConverter(Type type, Annotation[] annotations, Retrofit retrofit) {
       //^^ 这个type是 Observable中传递的类型参数
        final Converter<ResponseBody, ?> apiErrorConverter = mDelegateFactory.responseBodyConverter(HttpResponse.class, annotations, retrofit);
        final Converter<ResponseBody, ?> delegateConverter = mDelegateFactory.responseBodyConverter(type, annotations, retrofit);
        return new Converter<ResponseBody, Object>() {
            @Override
            public Object convert(ResponseBody value) throws IOException {
                MediaType mediaType = value.contentType();
//                Log.e("api1","convert(ApiErrorConverterFactory.java:52)"+mediaType.toString());
                String stringBody = value.string(); // 3
                HttpResponse httpResponse = (HttpResponse) apiErrorConverter
                        .convert(ResponseBody.create(mediaType, stringBody));//stringBody convert to HttpResponse.class
                if (httpResponse.isSuccessful()) {
                    if (httpResponse.getResult() == null) {
                        throw new NullPointerException();
                    }
                        //解析为对象了
                    return delegateConverter.convert(ResponseBody.create(mediaType, gson.toJson(httpResponse.getResult()))); //to jsonString
                } else {
                    throw new FalseException(httpResponse.getMsg(), Integer.parseInt(httpResponse.getCode()));
                }
            }
        };
    }
}
