package com.letianpai.robot.components.network.nets

import android.content.Context
import android.os.Build
import com.google.gson.Gson
import com.letianpai.robot.components.network.encryption.EncryptionUtils
import com.letianpai.robot.components.network.system.SystemUtil
import okhttp3.Callback
import okhttp3.HttpUrl
import okhttp3.HttpUrl.Builder.addQueryParameter
import okhttp3.HttpUrl.Builder.build
import okhttp3.MediaType
import okhttp3.MultipartBody
import okhttp3.MultipartBody.Builder.addFormDataPart
import okhttp3.MultipartBody.Builder.build
import okhttp3.MultipartBody.Builder.setType
import okhttp3.OkHttpClient
import okhttp3.OkHttpClient.Builder.build
import okhttp3.OkHttpClient.Builder.callTimeout
import okhttp3.OkHttpClient.Builder.connectTimeout
import okhttp3.OkHttpClient.Builder.readTimeout
import okhttp3.Request
import okhttp3.Request.Builder.addHeader
import okhttp3.Request.Builder.build
import okhttp3.Request.Builder.get
import okhttp3.Request.Builder.method
import okhttp3.Request.Builder.post
import okhttp3.Request.Builder.url
import okhttp3.RequestBody
import java.io.File
import java.time.Duration

/**
 * @author liujunbin
 */
class GeeUINetworkUtil private constructor(context: Context) {
    private var mContext: Context? = null
    private var gson: Gson? = null
    private fun init(context: Context) {
        this.mContext = context
        gson = Gson()
    }

    // 创建一个 HashMap 对象
    var map: HashMap<String, Int> = HashMap()

    init {
        init(context)
    }

    companion object {
        private var instance: GeeUINetworkUtil? = null
        private const val AUTHORIZATION = "Authorization"
        const val COUNTRY: String = "country"
        const val CN: String = "cn"
        const val GLOBAL: String = "global"

        fun getInstance(context: Context): GeeUINetworkUtil? {
            synchronized(GeeUINetworkUtil::class.java) {
                if (instance == null) {
                    instance = GeeUINetworkUtil(context.applicationContext)
                }
                return instance
            }
        }

        fun get(uri: String, callback: Callback?) {
            val okHttpClient = OkHttpClient()
            val builder: Builder = Builder()
            val request: Request = builder.get().url("https://yourservice.com$uri").build()
            val call = okHttpClient.newCall(request)
            call.enqueue(callback!!)
        }

        fun get1(context: Context?, uri: String, callback: Callback?) {
            val okHttpClient = OkHttpClient()
            val builder: Builder = Builder()
            val httpBuilder: Builder = HttpUrl.parse("https://yourservice.com$uri")!!.newBuilder()
            val sn = SystemUtil.getLtpSn()
            httpBuilder.addQueryParameter("sn", sn)

            val request: Request = Builder()
                .url(httpBuilder.build())
                .build()

            val call = okHttpClient.newCall(request)
            call.enqueue(callback!!)
        }

        fun get11(context: Context?, uri: String, callback: Callback?) {
            val okHttpClient = OkHttpClient()
            val httpBuilder: Builder = HttpUrl.parse("https://yourservice.com$uri")!!.newBuilder()
            val mac: String = EncryptionUtils.Companion.getRobotMac()
            val ts: String = EncryptionUtils.Companion.getTs()
            val auth: String = EncryptionUtils.Companion.getHardCodeSign(ts)
            httpBuilder.addQueryParameter("mac", mac)
            httpBuilder.addQueryParameter("ts", ts)

            val request: Request = Builder()
                .url(httpBuilder.build())
                .addHeader(AUTHORIZATION, auth)
                .build()

            val call = okHttpClient.newCall(request)
            call.enqueue(callback!!)
        }

        fun get11(context: Context?, auth: String?, ts: String?, uri: String, callback: Callback?) {
            val okHttpClient = OkHttpClient()
            val httpBuilder: Builder = HttpUrl.parse("https://yourservice.com$uri")!!.newBuilder()
            val mac: String = EncryptionUtils.Companion.getRobotMac()

            httpBuilder.addQueryParameter("mac", mac)
            httpBuilder.addQueryParameter("ts", ts)

            val request: Request = Builder()
                .url(httpBuilder.build())
                .addHeader(AUTHORIZATION, auth)
                .build()

            val call = okHttpClient.newCall(request)
            call.enqueue(callback!!)
        }

        fun get11(
            context: Context?,
            isChinese: Boolean,
            auth: String?,
            ts: String?,
            uri: String,
            callback: Callback?
        ) {
            val okHttpClient = OkHttpClient()
            val httpBuilder: Builder = if (isChinese) {
                HttpUrl.parse("https://yourservice.com$uri")!!.newBuilder()
            } else {
                HttpUrl.parse("https://yourservice.com$uri")!!.newBuilder()
            }
            val mac: String = EncryptionUtils.Companion.getRobotMac()
            httpBuilder.addQueryParameter("mac", mac)
            httpBuilder.addQueryParameter("ts", ts)

            val request: Request = Builder()
                .url(httpBuilder.build())
                .addHeader(AUTHORIZATION, auth)
                .build()

            val call = okHttpClient.newCall(request)
            call.enqueue(callback!!)
        }

        fun get(
            context: Context,
            isChinese: Boolean,
            auth: String?,
            sn: String?,
            ts: String?,
            uri: String,
            callback: Callback?
        ) {
            val okHttpClient = OkHttpClient()

            val httpBuilder: Builder = if (isChinese) {
                HttpUrl.parse("https://yourservice.com$uri")!!.newBuilder()
            } else {
                HttpUrl.parse("https://yourservice.com$uri")!!.newBuilder()
            }

            httpBuilder.addQueryParameter("sn", sn)
            httpBuilder.addQueryParameter("ts", ts)

            if (uri == GeeUINetworkConsts.GET_APP_BG_INFO) {
                httpBuilder.addQueryParameter("package_name", context.packageName)
            }

            var country = CN
            if (!isChinese) {
                country = GLOBAL
            }
            val request: Request = Builder()
                .url(httpBuilder.build())
                .addHeader(AUTHORIZATION, auth)
                .addHeader(COUNTRY, country)
                .build()

            val call = okHttpClient.newCall(request)
            call.enqueue(callback!!)
        }

        fun getWithPage(
            context: Context?,
            isChinese: Boolean,
            auth: String?,
            sn: String?,
            ts: String?,
            uri: String,
            pageSize: Int,
            callback: Callback?
        ) {
            val okHttpClient = OkHttpClient()

            val httpBuilder: Builder = if (isChinese) {
                HttpUrl.parse("https://yourservice.com$uri")!!.newBuilder()
            } else {
                HttpUrl.parse("https://yourservice.com$uri")!!.newBuilder()
            }

            httpBuilder.addQueryParameter("sn", sn)
            httpBuilder.addQueryParameter("ts", ts)
            httpBuilder.addQueryParameter("page_size", pageSize.toString() + "")

            var country = CN
            if (!isChinese) {
                country = GLOBAL
            }
            val request: Request = Builder()
                .url(httpBuilder.build())
                .addHeader(AUTHORIZATION, auth)
                .addHeader(COUNTRY, country)
                .build()

            val call = okHttpClient.newCall(request)
            call.enqueue(callback!!)
        }

        fun get(
            context: Context?,
            isChinese: Boolean,
            auth: String?,
            sn: String?,
            ts: String?,
            modelPath: String?,
            uri: String,
            callback: Callback?
        ) {
            val okHttpClient = OkHttpClient()

            val httpBuilder: Builder = if (isChinese) {
                HttpUrl.parse("https://yourservice.com$uri")!!.newBuilder()
            } else {
                HttpUrl.parse("https://yourservice.com$uri")!!.newBuilder()
            }

            httpBuilder.addQueryParameter("sn", sn)
            httpBuilder.addQueryParameter("ts", ts)

            var country = CN
            if (!isChinese) {
                country = GLOBAL
            }
            val request: Request = Builder()
                .url(httpBuilder.build())
                .addHeader(AUTHORIZATION, auth)
                .addHeader(COUNTRY, country)
                .build()

            val call = okHttpClient.newCall(request)
            call.enqueue(callback!!)
        }

        fun get11(
            context: Context?,
            auth: String?,
            sn: String?,
            ts: String?,
            uri: String,
            callback: Callback?
        ) {
            val okHttpClient = OkHttpClient()
            val httpBuilder: Builder = HttpUrl.parse("https://yourservice.com$uri")!!.newBuilder()

            httpBuilder.addQueryParameter("sn", sn)
            httpBuilder.addQueryParameter("ts", ts)

            val request: Request = Builder()
                .url(httpBuilder.build())
                .addHeader(AUTHORIZATION, auth)
                .build()

            val call = okHttpClient.newCall(request)
            call.enqueue(callback!!)
        }

        fun get11(
            context: Context?,
            isChinese: Boolean,
            auth: String?,
            hashMap: Map<String?, String?>,
            uri: String,
            callback: Callback?
        ) {
            val okHttpClient = OkHttpClient()
            val httpBuilder: Builder = if (isChinese) {
                HttpUrl.parse("https://yourservice.com$uri")!!.newBuilder()
            } else {
                HttpUrl.parse("https://yourservice.com$uri")!!.newBuilder()
            }
            for ((key, value) in hashMap) {
                httpBuilder.addQueryParameter(key, value)
            }

            val request: Request = Builder()
                .url(httpBuilder.build())
                .addHeader(AUTHORIZATION, auth)
                .build()

            val call = okHttpClient.newCall(request)
            call.enqueue(callback!!)
        }

        fun get11(
            context: Context?,
            auth: String?,
            hashMap: Map<String?, String?>,
            uri: String,
            callback: Callback?
        ) {
            val okHttpClient = OkHttpClient()
            val httpBuilder: Builder = HttpUrl.parse("https://yourservice.com$uri")!!.newBuilder()
            for ((key, value) in hashMap) {
                httpBuilder.addQueryParameter(key, value)
            }

            val request: Request = Builder()
                .url(httpBuilder.build())
                .addHeader(AUTHORIZATION, auth)
                .build()

            val call = okHttpClient.newCall(request)
            call.enqueue(callback!!)
        }

        fun get11ForMac(
            context: Context?,
            auth: String?,
            ts: String?,
            uri: String,
            callback: Callback?
        ) {
            val okHttpClient = OkHttpClient()
            val httpBuilder: Builder = HttpUrl.parse("https://yourservice.com$uri")!!.newBuilder()
            val mac: String = EncryptionUtils.Companion.getRobotMac()

            httpBuilder.addQueryParameter("mac", mac)
            httpBuilder.addQueryParameter("ts", ts)

            val request: Request = Builder()
                .url(httpBuilder.build())
                .addHeader(AUTHORIZATION, auth)
                .build()

            val call = okHttpClient.newCall(request)
            call.enqueue(callback!!)
        }

        fun get(
            context: Context?,
            uri: String,
            hashMap: HashMap<String, String>,
            callback: Callback?
        ) {
            val okHttpClient = OkHttpClient()
            val builder: Builder = Builder()
            val httpBuilder: Builder = HttpUrl.parse("https://yourservice.com$uri")!!.newBuilder()


            val iterator: Iterator<Map.Entry<String, String>> = hashMap.entries.iterator()
            while (iterator.hasNext()) {
                val entry = iterator.next()
                val key = entry.key
                val value = entry.value
                println("Key: $key, Value: $value")
                httpBuilder.addQueryParameter(key, value)
            }

            val request: Request = Builder()
                .url(httpBuilder.build())
                .build()

            val call = okHttpClient.newCall(request)
            call.enqueue(callback!!)
        }

        fun get(context: Context?, uri: String, callback: Callback?) {
            val sn = SystemUtil.getLtpSn()
            val authorization = ""
            get1(context, sn, authorization, uri, callback)
        }

        fun get1(context: Context?, sn: String?, key: String?, uri: String, callback: Callback?) {
            val okHttpClient = OkHttpClient()
            val builder: Builder = Builder()
            val httpBuilder: Builder = HttpUrl.parse("https://yourservice.com$uri")!!.newBuilder()

            httpBuilder.addQueryParameter("sn", sn)

            val request: Request = Builder()
                .url(httpBuilder.build())
                .build()

            val call = okHttpClient.newCall(request)
            call.enqueue(callback!!)
        }

        fun get2(context: Context?, sn: String?, key: String?, uri: String, callback: Callback?) {
            val okHttpClient = OkHttpClient()
            val httpBuilder: Builder = HttpUrl.parse("https://yourservice.com$uri")!!.newBuilder()

            httpBuilder.addQueryParameter("sn", sn)

            val request: Request = Builder()
                .url(httpBuilder.build())
                .addHeader(AUTHORIZATION, key)
                .build()

            val call = okHttpClient.newCall(request)
            call.enqueue(callback!!)
        }

        fun post(uri: String, hashMap: HashMap<*, *>, callback: Callback?) {
            val list = ArrayList<Any>()
            var client: OkHttpClient? = null
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                client = Builder().connectTimeout(Duration.ofMinutes(10L))
                    .readTimeout(Duration.ofMinutes(10L)).callTimeout(
                    Duration.ofMinutes(10L)
                ).build()
            }
            val url = "https://yourservice.com$uri"
            val mediaType: MediaType = parse.parse("application/json; charset=utf-8")
            list.add(hashMap)
            val body: RequestBody = RequestBody.create(Gson().toJson(hashMap), mediaType)
            val request: Request = Builder().url(url).method("POST", body)
                .addHeader("Content-Type", "application/json").build()
            client!!.newCall(request).enqueue(callback!!)
        }

        fun post1(uri: String, hashMap: HashMap<*, *>, callback: Callback?) {
            val list = ArrayList<Any>()
            var client: OkHttpClient? = null
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                client = Builder().connectTimeout(Duration.ofMinutes(10L))
                    .readTimeout(Duration.ofMinutes(10L)).callTimeout(
                    Duration.ofMinutes(10L)
                ).build()
            }
            val httpBuilder: Builder = HttpUrl.parse("https://yourservice.com$uri")!!.newBuilder()
            val sn = SystemUtil.getLtpSn()
            httpBuilder.addQueryParameter("sn", sn)

            val mediaType: MediaType = parse.parse("application/json; charset=utf-8")
            list.add(hashMap)
            val body: RequestBody = RequestBody.create(Gson().toJson(hashMap), mediaType)

            val request: Request = Builder().url(httpBuilder.build()).method("POST", body)
                .addHeader("Content-Type", "application/json").build()
            client!!.newCall(request).enqueue(callback!!)
        }

        fun post2(uri: String, hashMap: HashMap<*, *>, callback: Callback?) {
            val list = ArrayList<Any>()
            var client: OkHttpClient? = null
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                client = Builder().connectTimeout(Duration.ofMinutes(10L))
                    .readTimeout(Duration.ofMinutes(10L)).callTimeout(
                    Duration.ofMinutes(10L)
                ).build()
            }
            val httpBuilder: Builder = HttpUrl.parse("https://yourservice.com$uri")!!.newBuilder()
            val sn = SystemUtil.getLtpSn()
            val ts = (System.currentTimeMillis() / 1000).toString() + ""
            httpBuilder.addQueryParameter("sn", sn)
            httpBuilder.addQueryParameter("ts", ts)

            val mediaType: MediaType = parse.parse("application/json; charset=utf-8")
            list.add(hashMap)
            val body: RequestBody = RequestBody.create(Gson().toJson(hashMap), mediaType)

            val request: Request = Builder().url(httpBuilder.build()).method("POST", body)
                .addHeader("Content-Type", "application/json").build()
            client!!.newCall(request).enqueue(callback!!)
        }

        fun post2(
            hashMap: HashMap<*, *>,
            auth: String?,
            ts: String?,
            uri: String,
            callback: Callback?
        ) {
            val list = ArrayList<Any>()
            var client: OkHttpClient? = null
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                client = Builder().connectTimeout(Duration.ofMinutes(10L))
                    .readTimeout(Duration.ofMinutes(10L)).callTimeout(
                    Duration.ofMinutes(10L)
                ).build()
            }
            val httpBuilder: Builder = HttpUrl.parse("https://yourservice.com$uri")!!.newBuilder()
            val sn = SystemUtil.getLtpSn()
            httpBuilder.addQueryParameter("sn", sn)
            httpBuilder.addQueryParameter("ts", ts)

            val mediaType: MediaType = parse.parse("application/json; charset=utf-8")
            list.add(hashMap)
            val body: RequestBody = RequestBody.create(Gson().toJson(hashMap), mediaType)

            val request: Request = Builder().url(httpBuilder.build()).addHeader(AUTHORIZATION, auth)
                .method("POST", body).addHeader("Content-Type", "application/json").build()
            client!!.newCall(request).enqueue(callback!!)
        }

        fun post3(
            hashMap: HashMap<*, *>,
            auth: String?,
            queryMap: Map<String?, String?>,
            uri: String,
            callback: Callback?
        ) {
            val list = ArrayList<Any>()
            var client: OkHttpClient? = null
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                client = Builder().connectTimeout(Duration.ofMinutes(10L))
                    .readTimeout(Duration.ofMinutes(10L)).callTimeout(
                    Duration.ofMinutes(10L)
                ).build()
            }
            val httpBuilder: Builder = HttpUrl.parse("https://yourservice.com$uri")!!.newBuilder()

            for ((key, value) in queryMap) {
                httpBuilder.addQueryParameter(key, value)
            }

            val mediaType: MediaType = parse.parse("application/json; charset=utf-8")
            list.add(hashMap)
            val body: RequestBody = RequestBody.create(Gson().toJson(hashMap), mediaType)

            val request: Request = Builder().url(httpBuilder.build()).addHeader(AUTHORIZATION, auth)
                .method("POST", body).addHeader("Content-Type", "application/json").build()
            client!!.newCall(request).enqueue(callback!!)
        }

        fun post3(
            hashMap: HashMap<*, *>,
            isChinese: Boolean,
            auth: String?,
            queryMap: Map<String?, String?>,
            uri: String,
            callback: Callback?
        ) {
            val list = ArrayList<Any>()
            var client: OkHttpClient? = null
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                client = Builder().connectTimeout(Duration.ofMinutes(10L))
                    .readTimeout(Duration.ofMinutes(10L)).callTimeout(
                    Duration.ofMinutes(10L)
                ).build()
            }
            val httpBuilder: Builder = if (isChinese) {
                HttpUrl.parse("https://yourservice.com$uri")!!.newBuilder()
            } else {
                HttpUrl.parse("https://yourservice.com$uri")!!.newBuilder()
            }

            for ((key, value) in queryMap) {
                httpBuilder.addQueryParameter(key, value)
            }

            val mediaType: MediaType = parse.parse("application/json; charset=utf-8")
            list.add(hashMap)
            val body: RequestBody = RequestBody.create(Gson().toJson(hashMap), mediaType)

            //        File file = new File("");
//        RequestBody body1 = new MultipartBody.Builder().setType(MultipartBody.FORM)
//                .addFormDataPart("file", file.getName(), RequestBody.create(MediaType.parse("application/octet-stream"), file))
//                .addPart(body).build();
            var country = CN
            if (!isChinese) {
                country = GLOBAL
            }

            val request: Request =
                Builder().url(httpBuilder.build()).addHeader(AUTHORIZATION, auth).addHeader(
                    COUNTRY, country
                ).method("POST", body).addHeader("Content-Type", "application/json").build()
            client!!.newCall(request).enqueue(callback!!)

            //        Request request = new Request.Builder().url(httpBuilder.build()).addHeader(AUTHORIZATION,auth).addHeader(COUNTRY,country).post(body).addHeader("Content-Type", "application/json").build();
//        client.newCall(request).enqueue(callback);
        }


        fun uploadFile(filePath: String?, callback: Callback?) {
            // Replace these with your actual server URL and file path
            val serverUrl = "http://example.com/upload"

            //            String filePath = "path/to/your/file.jpg";
            val client = OkHttpClient()
            val file = File(filePath)

            val requestBody: RequestBody = Builder()
                .setType(MultipartBody.FORM)
                .addFormDataPart(
                    "file",
                    file.name,
                    RequestBody.create(parse.parse("application/octet-stream"), file)
                )
                .build()

            val request: Request = Builder()
                .url(serverUrl)
                .post(requestBody)
                .build()

            val call = client.newCall(request)
            call.enqueue(callback!!)
        } //
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
}
