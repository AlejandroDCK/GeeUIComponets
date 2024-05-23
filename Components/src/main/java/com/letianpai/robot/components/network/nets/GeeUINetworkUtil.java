package com.letianpai.robot.components.network.nets;

import android.content.Context;
import android.util.Log;

import com.google.gson.Gson;
import com.letianpai.robot.components.network.encryption.EncryptionUtils;
import com.letianpai.robot.components.network.system.SystemUtil;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.HttpUrl;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

/**
 * @author liujunbin
 */
public class GeeUINetworkUtil {

    private static GeeUINetworkUtil instance;
    private Context mContext;
    private Gson gson;
    private static final String AUTHORIZATION = "Authorization";
    public static final String COUNTRY = "country";
    public static final String CN = "cn";
    public static final String GLOBAL = "global";

    private GeeUINetworkUtil(Context context) {
        init(context);
    }

    private void init(Context context) {
        this.mContext = context;
        gson = new Gson();
    }

    public static GeeUINetworkUtil getInstance(Context context) {
        synchronized (GeeUINetworkUtil.class) {
            if (instance == null) {
                instance = new GeeUINetworkUtil(context.getApplicationContext());
            }
            return instance;
        }

    }

    public static void get(String uri, Callback callback) {
        OkHttpClient okHttpClient = new OkHttpClient();
        Request.Builder builder = new Request.Builder();
        Request request = builder.get().url("https://yourservice.com" + uri).build();
        Call call = okHttpClient.newCall(request);
        call.enqueue(callback);
    }

    public static void get1(Context context, String uri, Callback callback) {
        OkHttpClient okHttpClient = new OkHttpClient();
        Request.Builder builder = new Request.Builder();
        HttpUrl.Builder httpBuilder = HttpUrl.parse("https://yourservice.com" + uri).newBuilder();
        String sn = SystemUtil.getLtpSn();
        httpBuilder.addQueryParameter("sn", sn);

        Request request = new Request.Builder()
                .url(httpBuilder.build())
                .build();

        Call call = okHttpClient.newCall(request);
        call.enqueue(callback);
    }

    public static void get11(Context context, String uri, Callback callback) {
        OkHttpClient okHttpClient = new OkHttpClient();
        HttpUrl.Builder httpBuilder = HttpUrl.parse("https://yourservice.com" + uri).newBuilder();
        String mac = EncryptionUtils.getRobotMac();
        String ts = EncryptionUtils.getTs();
        String auth = EncryptionUtils.getHardCodeSign(ts);
        httpBuilder.addQueryParameter("mac", mac);
        httpBuilder.addQueryParameter("ts", ts);

        Request request = new Request.Builder()
                .url(httpBuilder.build())
                .addHeader(AUTHORIZATION, auth)
                .build();

        Call call = okHttpClient.newCall(request);
        call.enqueue(callback);
    }

    public static void get11(Context context, String auth, String ts, String uri, Callback callback) {
        OkHttpClient okHttpClient = new OkHttpClient();
        HttpUrl.Builder httpBuilder = HttpUrl.parse("https://yourservice.com" + uri).newBuilder();
        String mac = EncryptionUtils.getRobotMac();

        httpBuilder.addQueryParameter("mac", mac);
        httpBuilder.addQueryParameter("ts", ts);

        Request request = new Request.Builder()
                .url(httpBuilder.build())
                .addHeader(AUTHORIZATION, auth)
                .build();

        Call call = okHttpClient.newCall(request);
        call.enqueue(callback);
    }

    public static void get11(Context context, boolean isChinese, String auth, String ts, String uri, Callback callback) {
        OkHttpClient okHttpClient = new OkHttpClient();
        HttpUrl.Builder httpBuilder;
        if (isChinese){
            httpBuilder = HttpUrl.parse("https://yourservice.com" + uri).newBuilder();
        }else{
            httpBuilder = HttpUrl.parse("https://yourservice.com" + uri).newBuilder();
        }
        String mac = EncryptionUtils.getRobotMac();
        httpBuilder.addQueryParameter("mac", mac);
        httpBuilder.addQueryParameter("ts", ts);

        Request request = new Request.Builder()
                .url(httpBuilder.build())
                .addHeader(AUTHORIZATION, auth)
                .build();

        Call call = okHttpClient.newCall(request);
        call.enqueue(callback);
    }

    public static void get(Context context, boolean isChinese, String auth, String sn, String ts, String uri, Callback callback) {
        OkHttpClient okHttpClient = new OkHttpClient();
        HttpUrl.Builder httpBuilder;

        if (isChinese){
            httpBuilder = HttpUrl.parse("https://yourservice.com" + uri).newBuilder();
        }else{
            httpBuilder = HttpUrl.parse("https://yourservice.com" + uri).newBuilder();
        }

        httpBuilder.addQueryParameter("sn", sn);
        httpBuilder.addQueryParameter("ts", ts);

        if (uri.equals(GeeUINetworkConsts.GET_APP_BG_INFO)){
            httpBuilder.addQueryParameter("package_name", context.getPackageName());
        }

        String country =CN;
        if (!isChinese){
            country = GLOBAL;
        }
        Request request = new Request.Builder()
                .url(httpBuilder.build())
                .addHeader(AUTHORIZATION, auth)
                .addHeader(COUNTRY,country)
                .build();

        Call call = okHttpClient.newCall(request);
        call.enqueue(callback);
    }
    public static void getWithPage(Context context, boolean isChinese, String auth, String sn, String ts, String uri, int pageSize,Callback callback) {
        OkHttpClient okHttpClient = new OkHttpClient();
        HttpUrl.Builder httpBuilder;

        if (isChinese){
            httpBuilder = HttpUrl.parse("https://yourservice.com" + uri).newBuilder();
        }else{
            httpBuilder = HttpUrl.parse("https://yourservice.com" + uri).newBuilder();
        }

        httpBuilder.addQueryParameter("sn", sn);
        httpBuilder.addQueryParameter("ts", ts);
        httpBuilder.addQueryParameter("page_size", pageSize+"");

        String country =CN;
        if (!isChinese){
            country = GLOBAL;
        }
        Request request = new Request.Builder()
                .url(httpBuilder.build())
                .addHeader(AUTHORIZATION, auth)
                .addHeader(COUNTRY,country)
                .build();

        Call call = okHttpClient.newCall(request);
        call.enqueue(callback);
    }

    public static void get(Context context, boolean isChinese, String auth, String sn, String ts, String modelPath, String uri, Callback callback) {
        OkHttpClient okHttpClient = new OkHttpClient();
        HttpUrl.Builder httpBuilder;

        if (isChinese){
            httpBuilder = HttpUrl.parse("https://yourservice.com" + uri).newBuilder();
        }else{
            httpBuilder = HttpUrl.parse("https://yourservice.com" + uri).newBuilder();
        }

        httpBuilder.addQueryParameter("sn", sn);
        httpBuilder.addQueryParameter("ts", ts);

        String country =CN;
        if (!isChinese){
            country = GLOBAL;
        }
        Request request = new Request.Builder()
                .url(httpBuilder.build())
                .addHeader(AUTHORIZATION, auth)
                .addHeader(COUNTRY,country)
                .build();

        Call call = okHttpClient.newCall(request);
        call.enqueue(callback);
    }

    public static void get11(Context context, String auth, String sn, String ts, String uri, Callback callback) {
        OkHttpClient okHttpClient = new OkHttpClient();
        HttpUrl.Builder httpBuilder = HttpUrl.parse("https://yourservice.com" + uri).newBuilder();

        httpBuilder.addQueryParameter("sn", sn);
        httpBuilder.addQueryParameter("ts", ts);

        Request request = new Request.Builder()
                .url(httpBuilder.build())
                .addHeader(AUTHORIZATION, auth)
                .build();

        Call call = okHttpClient.newCall(request);
        call.enqueue(callback);
    }

    public static void get11(Context context, boolean isChinese, String auth, Map<String,String> hashMap, String uri, Callback callback) {
        OkHttpClient okHttpClient = new OkHttpClient();
        HttpUrl.Builder httpBuilder;
        if (isChinese){
            httpBuilder = HttpUrl.parse("https://yourservice.com" + uri).newBuilder();
        }else{
            httpBuilder = HttpUrl.parse("https://yourservice.com" + uri).newBuilder();
        }
        for (Map.Entry<String, String> entry : hashMap.entrySet()) {
            httpBuilder.addQueryParameter(entry.getKey(), entry.getValue());
        }

        Request request = new Request.Builder()
                .url(httpBuilder.build())
                .addHeader(AUTHORIZATION, auth)
                .build();

        Call call = okHttpClient.newCall(request);
        call.enqueue(callback);
    }

    public static void get11(Context context, String auth, Map<String,String> hashMap, String uri, Callback callback) {
        OkHttpClient okHttpClient = new OkHttpClient();
        HttpUrl.Builder httpBuilder = HttpUrl.parse("https://yourservice.com" + uri).newBuilder();
        for (Map.Entry<String, String> entry : hashMap.entrySet()) {
            httpBuilder.addQueryParameter(entry.getKey(), entry.getValue());
        }

        Request request = new Request.Builder()
                .url(httpBuilder.build())
                .addHeader(AUTHORIZATION, auth)
                .build();

        Call call = okHttpClient.newCall(request);
        call.enqueue(callback);
    }

    public static void get11ForMac(Context context,String auth,String ts,String uri, Callback callback) {
        OkHttpClient okHttpClient = new OkHttpClient();
        HttpUrl.Builder httpBuilder = HttpUrl.parse("https://yourservice.com"+ uri).newBuilder();
        String mac = EncryptionUtils.getRobotMac();

        httpBuilder.addQueryParameter("mac", mac);
        httpBuilder.addQueryParameter("ts", ts);

        Request request = new Request.Builder()
                .url(httpBuilder.build())
                .addHeader(AUTHORIZATION,auth)
                .build();

        Call call = okHttpClient.newCall(request);
        call.enqueue(callback);
    }

    public static void get(Context context, String uri, HashMap<String, String> hashMap, Callback callback) {
        OkHttpClient okHttpClient = new OkHttpClient();
        Request.Builder builder = new Request.Builder();
        HttpUrl.Builder httpBuilder = HttpUrl.parse("https://yourservice.com" + uri).newBuilder();


        Iterator<Map.Entry<String, String>> iterator = hashMap.entrySet().iterator();
        while (iterator.hasNext()) {
            Map.Entry<String, String> entry = iterator.next();
            String key = entry.getKey();
            String value = entry.getValue();
            System.out.println("Key: " + key + ", Value: " + value);
            httpBuilder.addQueryParameter(key, value);
        }

        Request request = new Request.Builder()
                .url(httpBuilder.build())
                .build();

        Call call = okHttpClient.newCall(request);
        call.enqueue(callback);
    }

    // 创建一个 HashMap 对象
    HashMap<String, Integer> map = new HashMap<>();

    public static void get(Context context, String uri, Callback callback) {
        String sn = SystemUtil.getLtpSn();
        String authorization = "";
        get1(context, sn, authorization, uri, callback);
    }

    public static void get1(Context context, String sn, String key, String uri, Callback callback) {
        OkHttpClient okHttpClient = new OkHttpClient();
        Request.Builder builder = new Request.Builder();
        HttpUrl.Builder httpBuilder = HttpUrl.parse("https://yourservice.com" + uri).newBuilder();

        httpBuilder.addQueryParameter("sn", sn);

        Request request = new Request.Builder()
                .url(httpBuilder.build())
                .build();

        Call call = okHttpClient.newCall(request);
        call.enqueue(callback);
    }

    public static void get2(Context context, String sn, String key, String uri, Callback callback) {
        OkHttpClient okHttpClient = new OkHttpClient();
        HttpUrl.Builder httpBuilder = HttpUrl.parse("https://yourservice.com" + uri).newBuilder();

        httpBuilder.addQueryParameter("sn", sn);

        Request request = new Request.Builder()
                .url(httpBuilder.build())
                .addHeader(AUTHORIZATION, key)
                .build();

        Call call = okHttpClient.newCall(request);
        call.enqueue(callback);
    }

    public static void post(String uri, HashMap hashMap, Callback callback) {
        ArrayList<Object> list = new ArrayList<>();
        OkHttpClient client = null;
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            client = new OkHttpClient.Builder().connectTimeout(Duration.ofMinutes(10l)).readTimeout(Duration.ofMinutes(10l)).callTimeout(Duration.ofMinutes(10l)).build();
        }
        String url = "https://yourservice.com" + uri;
        MediaType mediaType = MediaType.parse("application/json; charset=utf-8");
        list.add(hashMap);
        RequestBody body = RequestBody.create(new Gson().toJson(hashMap), mediaType);
        Request request = new Request.Builder().url(url).method("POST", body).addHeader("Content-Type", "application/json").build();
        client.newCall(request).enqueue(callback);

    }

    public static void post1(String uri, HashMap hashMap, Callback callback) {
        ArrayList<Object> list = new ArrayList<>();
        OkHttpClient client = null;
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            client = new OkHttpClient.Builder().connectTimeout(Duration.ofMinutes(10l)).readTimeout(Duration.ofMinutes(10l)).callTimeout(Duration.ofMinutes(10l)).build();
        }
        HttpUrl.Builder httpBuilder = HttpUrl.parse("https://yourservice.com" + uri).newBuilder();
        String sn = SystemUtil.getLtpSn();
        httpBuilder.addQueryParameter("sn", sn);

        MediaType mediaType = MediaType.parse("application/json; charset=utf-8");
        list.add(hashMap);
        RequestBody body = RequestBody.create(new Gson().toJson(hashMap), mediaType);

        Request request = new Request.Builder().url(httpBuilder.build()).method("POST", body).addHeader("Content-Type", "application/json").build();
        client.newCall(request).enqueue(callback);

    }

    public static void post2(String uri, HashMap hashMap, Callback callback) {
        ArrayList<Object> list = new ArrayList<>();
        OkHttpClient client = null;
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            client = new OkHttpClient.Builder().connectTimeout(Duration.ofMinutes(10l)).readTimeout(Duration.ofMinutes(10l)).callTimeout(Duration.ofMinutes(10l)).build();
        }
        HttpUrl.Builder httpBuilder = HttpUrl.parse("https://yourservice.com" + uri).newBuilder();
        String sn = SystemUtil.getLtpSn();
        String ts = ((long) (System.currentTimeMillis() / 1000)) + "";
        httpBuilder.addQueryParameter("sn", sn);
        httpBuilder.addQueryParameter("ts", ts);

        MediaType mediaType = MediaType.parse("application/json; charset=utf-8");
        list.add(hashMap);
        RequestBody body = RequestBody.create(new Gson().toJson(hashMap), mediaType);

        Request request = new Request.Builder().url(httpBuilder.build()).method("POST", body).addHeader("Content-Type", "application/json").build();
        client.newCall(request).enqueue(callback);

    }
    public static void post2(HashMap hashMap,String auth,String ts,String uri, Callback callback) {
        ArrayList<Object> list = new ArrayList<>();
        OkHttpClient client = null;
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            client = new OkHttpClient.Builder().connectTimeout(Duration.ofMinutes(10l)).readTimeout(Duration.ofMinutes(10l)).callTimeout(Duration.ofMinutes(10l)).build();
        }
        HttpUrl.Builder httpBuilder = HttpUrl.parse("https://yourservice.com"+ uri).newBuilder();
        String sn = SystemUtil.getLtpSn();
        httpBuilder.addQueryParameter("sn", sn);
        httpBuilder.addQueryParameter("ts", ts);

        MediaType mediaType = MediaType.parse("application/json; charset=utf-8");
        list.add(hashMap);
        RequestBody body = RequestBody.create(new Gson().toJson(hashMap), mediaType);

        Request request = new Request.Builder().url(httpBuilder.build()).addHeader(AUTHORIZATION,auth).method("POST", body).addHeader("Content-Type", "application/json").build();
        client.newCall(request).enqueue(callback);

    }

    public static void post3(HashMap hashMap,String auth,Map<String,String> queryMap,String uri, Callback callback) {
        ArrayList<Object> list = new ArrayList<>();
        OkHttpClient client = null;
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            client = new OkHttpClient.Builder().connectTimeout(Duration.ofMinutes(10l)).readTimeout(Duration.ofMinutes(10l)).callTimeout(Duration.ofMinutes(10l)).build();
        }
        HttpUrl.Builder httpBuilder = HttpUrl.parse("https://yourservice.com"+ uri).newBuilder();

        for (Map.Entry<String, String> entry : queryMap.entrySet()) {
            httpBuilder.addQueryParameter(entry.getKey(), entry.getValue());
        }

        MediaType mediaType = MediaType.parse("application/json; charset=utf-8");
        list.add(hashMap);
        RequestBody body = RequestBody.create(new Gson().toJson(hashMap), mediaType);

        Request request = new Request.Builder().url(httpBuilder.build()).addHeader(AUTHORIZATION,auth).method("POST", body).addHeader("Content-Type", "application/json").build();
        client.newCall(request).enqueue(callback);

    }

    public static void post3(HashMap hashMap,boolean isChinese,String auth,Map<String,String> queryMap,String uri, Callback callback) {
        ArrayList<Object> list = new ArrayList<>();
        OkHttpClient client = null;
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            client = new OkHttpClient.Builder().connectTimeout(Duration.ofMinutes(10l)).readTimeout(Duration.ofMinutes(10l)).callTimeout(Duration.ofMinutes(10l)).build();
        }
        HttpUrl.Builder httpBuilder;
        if (isChinese){
            httpBuilder = HttpUrl.parse("https://yourservice.com"+ uri).newBuilder();
        }else{
            httpBuilder = HttpUrl.parse("https://yourservice.com"+ uri).newBuilder();
        }

        for (Map.Entry<String, String> entry : queryMap.entrySet()) {
            httpBuilder.addQueryParameter(entry.getKey(), entry.getValue());
        }

        MediaType mediaType = MediaType.parse("application/json; charset=utf-8");
        list.add(hashMap);
        RequestBody body = RequestBody.create(new Gson().toJson(hashMap), mediaType);
//        File file = new File("");
//        RequestBody body1 = new MultipartBody.Builder().setType(MultipartBody.FORM)
//                .addFormDataPart("file", file.getName(), RequestBody.create(MediaType.parse("application/octet-stream"), file))
//                .addPart(body).build();

        String country =CN;
        if (!isChinese){
            country = GLOBAL;
        }

        Request request = new Request.Builder().url(httpBuilder.build()).addHeader(AUTHORIZATION,auth).addHeader(COUNTRY,country).method("POST", body).addHeader("Content-Type", "application/json").build();
        client.newCall(request).enqueue(callback);

//        Request request = new Request.Builder().url(httpBuilder.build()).addHeader(AUTHORIZATION,auth).addHeader(COUNTRY,country).post(body).addHeader("Content-Type", "application/json").build();
//        client.newCall(request).enqueue(callback);

    }


        public static void uploadFile(String filePath ,Callback callback) {
            // Replace these with your actual server URL and file path
            String serverUrl = "http://example.com/upload";
//            String filePath = "path/to/your/file.jpg";

            OkHttpClient client = new OkHttpClient();
            File file = new File(filePath);

            RequestBody requestBody = new MultipartBody.Builder()
                    .setType(MultipartBody.FORM)
                    .addFormDataPart("file", file.getName(), RequestBody.create(MediaType.parse("application/octet-stream"), file))
                    .build();

            Request request = new Request.Builder()
                    .url(serverUrl)
                    .post(requestBody)
                    .build();

            Call call = client.newCall(request);
            call.enqueue(callback);
        }
//
//        public static void uploadFile(String filePath ,Callback callback) {
//            // Replace these with your actual server URL and file path
//            String serverUrl = "http://example.com/upload";
////            String filePath = "path/to/your/file.jpg";
//
//            OkHttpClient client = new OkHttpClient();
//
//            File file = new File(filePath);
//
//            RequestBody requestBody = new MultipartBody.Builder()
//                    .setType(MultipartBody.FORM)
//                    .addFormDataPart("file", file.getName(), RequestBody.create(MediaType.parse("application/octet-stream"), file))
//                    .build();
//
//            Request request = new Request.Builder()
//                    .url(serverUrl)
//                    .post(requestBody)
//                    .build();
//
//            Call call = client.newCall(request);
//
//            call.enqueue(new Callback() {
//                @Override
//                public void onFailure(Call call, IOException e) {
//                    e.printStackTrace();
//                }
//
//                @Override
//                public void onResponse(Call call, Response response) throws IOException {
//                    if (response.isSuccessful()) {
//                        // Handle the successful response here
//                        String responseBody = response.body().string();
//                        System.out.println("Response: " + responseBody);
//                    } else {
//                        // Handle the error here
//                        System.out.println("Request failed with code: " + response.code());
//                    }
//                }
//            });
//        }




}
