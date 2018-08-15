package com.example.shopping_core.net.interceptors;

import android.support.annotation.RawRes;
import com.example.shopping_core.util.file.FileUtil;
import java.io.IOException;
import okhttp3.MediaType;
import okhttp3.Protocol;
import okhttp3.Response;
import okhttp3.ResponseBody;

/**
 * 作者：Created by 权.
 * 时间：2018/8/14.
 */

public class DebugInterceptor extends BaseInterceptor{

    private final String DUBUG_URL;
    private final int DEBUG_RAW_ID;

    public DebugInterceptor(String DUBUG_URL, int DEBUG_RAW_ID) {
        this.DUBUG_URL = DUBUG_URL;
        this.DEBUG_RAW_ID = DEBUG_RAW_ID;
    }


    private Response getResponse(Chain chain,String json){
        return new Response.Builder()
                .code(200)
                .addHeader("Content-type","application/json")
                .body(ResponseBody.create(MediaType.parse("application/json"),json))
                .message("OK")
                .request(chain.request())
                .protocol(Protocol.HTTP_1_1)
                .build();

    }

    private Response debugResponse(Chain chain, @RawRes int rawId){
        final String json = FileUtil.getRawFile(rawId);
        return getResponse(chain,json);
    }


    @Override
    public Response intercept(Chain chain) throws IOException {
        final String url = chain.request().url().toString();
        if(url.contains(DUBUG_URL)){
            return debugResponse(chain,DEBUG_RAW_ID);
        }
        return chain.proceed(chain.request());
    }
}
