package com.example.shopping_core.net;



import java.util.Map;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Part;
import retrofit2.http.QueryMap;
import retrofit2.http.Streaming;
import retrofit2.http.Url;

/**
 * Created by 权 on 2018/8/9.
 */

public interface RestService {

    @GET
    Call<String> get(@Url String url, @QueryMap Map<String,Object> params);

    @FormUrlEncoded
    @POST
    Call<String> post(@Url String url, @FieldMap Map<String,Object> params);

    @POST
    Call<String> postRaw(@Url String url,@Body RequestBody body);

    @FormUrlEncoded
    @PUT
    Call<String> put(@Url String url, @FieldMap Map<String,Object> params);

    @PUT
    Call<String> putRaw(@Url String url,@Body RequestBody body);

    @DELETE
    Call<String> delete(@Url String url,@QueryMap Map<String,Object> params);

    @Streaming  //边下载边写入 避免一次写入文件过大
    @GET
    Call<ResponseBody> download(@Url String url,@QueryMap Map<String,Object> params);

    @Multipart //表示请求体是一个支持文件上传的Form表单
    @POST
    Call<String> upload(@Url String url, @Part MultipartBody.Part file);// @Part用于表单字段适用于有文件上传的情况



}
