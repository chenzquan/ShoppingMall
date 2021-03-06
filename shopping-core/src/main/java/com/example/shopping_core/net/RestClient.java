package com.example.shopping_core.net;

import android.content.Context;

import com.example.shopping_core.download.DownLoadHandler;
import com.example.shopping_core.net.callback.IError;
import com.example.shopping_core.net.callback.IFailure;
import com.example.shopping_core.net.callback.IRequest;
import com.example.shopping_core.net.callback.ISuccess;
import com.example.shopping_core.net.callback.RequestCallBacks;
import com.example.shopping_core.ui.loader.LatteLoader;
import com.example.shopping_core.ui.loader.LoaderStyle;
import java.io.File;
import java.util.Map;
import java.util.WeakHashMap;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;


/**
 * Created by 权 on 2018/8/9.
 */

//传入什么用什么  就用建造者模式
public class RestClient {

    private final String URL;
    private static final WeakHashMap<String,Object> PARAMS = RestCreator.getParams();
    private final IRequest REQUEST;
    private final ISuccess SUCCESS;
    private final IFailure FAILURE;
    private final IError ERROR;

    private final RequestBody BODY;
    private final File FILE;

    private final LoaderStyle LOADER_STYLE;

    private final Context CONTEXT;
    private final String DOWNLOAD_DIR;
    private final String EXTENSION;
    private final String NAME;


    public RestClient(String url,
                      Map<String, Object> params,
                      IRequest request,
                      ISuccess success,
                      IFailure failure,
                      IError error,
                      RequestBody body,
                      File file,
                      String downLoadDir,
                      String extension,
                      String name,
                      LoaderStyle loader_style,
                      Context context) {
        this.URL = url;
        this.PARAMS.putAll(params);
        this.REQUEST = request;
        this.SUCCESS = success;
        this.FAILURE = failure;
        this.ERROR = error;
        this.BODY = body;
        this.FILE = file;
        this.LOADER_STYLE = loader_style;
        this.CONTEXT = context;
        this.DOWNLOAD_DIR = downLoadDir;
        this.EXTENSION = extension;
        this.NAME = name;
    }

    public static RestClientBuilder builder(){
        return new RestClientBuilder();
    }

    /*
    网络请求 根据方法判断
     */
    public void request(HttpMethod method){
        final RestService restService = RestCreator.getRestService(); //取全局变量中的service
        Call<String> call = null;

        if(REQUEST != null){
            REQUEST.onRequestStart();
        }

        if(LOADER_STYLE != null){   //设置loading图标的类型
            LatteLoader.showLoading(CONTEXT,LOADER_STYLE);
        }


        switch (method){
            case GET:
                call = restService.get(URL,PARAMS);
                break;
            case POST:
                call = restService.post(URL,PARAMS);
                break;
            case POST_RAW:
                call = restService.postRaw(URL,BODY);
                break;
            case PUT:
                call = restService.put(URL,PARAMS);
                break;
            case PUT_RAW:
                call = restService.putRaw(URL,BODY);
                break;
            case DELETE:
                call = restService.delete(URL,PARAMS);
                break;
            case UPLOAD:
                final RequestBody requestBody =
                        RequestBody.create(MediaType.parse("multipart/form-data"),FILE);//第一个参数是文件类型
                final MultipartBody.Part body = MultipartBody.Part.createFormData("file",FILE.getName(),requestBody);
                call = restService.upload(URL,body);
                break;
            default:
                break;
        }

        if(call != null){
            call.enqueue(getRequestCallback()); //异步
        }


    }

    /*
    创建一个RequestCallbacks
     */
    private Callback<String> getRequestCallback(){
        return new RequestCallBacks(REQUEST,SUCCESS,FAILURE,ERROR,LOADER_STYLE);
    }


    public final void get(){
        request(HttpMethod.GET);
    }

    public final void post(){
        if(BODY == null){
            request(HttpMethod.POST);
        }else{
            if(!PARAMS.isEmpty()){
                throw new RuntimeException("params must be null");
            }
            request(HttpMethod.POST_RAW);
        }
    }

    public final void put(){
        if(BODY == null){
            request(HttpMethod.PUT);
        }else{
            if(!PARAMS.isEmpty()){
                throw new RuntimeException("params must be null");
            }
            request(HttpMethod.PUT_RAW);
        }

    }

    public final void delete(){
        request(HttpMethod.DELETE);
    }

    public final void download(){
        new DownLoadHandler(URL,
                REQUEST,
                DOWNLOAD_DIR,
                EXTENSION,
                NAME,
                SUCCESS,
                FAILURE,
                ERROR).handleDownload();
    }



}
