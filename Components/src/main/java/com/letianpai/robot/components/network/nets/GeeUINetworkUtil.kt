package com.letianpai.robot.components.network.nets

import android.content.Context
import android.os.Build
//import androidx.privacysandbox.tools.core.generator.build
import com.google.gson.Gson
import com.letianpai.robot.components.network.encryption.EncryptionUtils
import com.letianpai.robot.components.network.system.SystemUtil
import okhttp3.Callback
import okhttp3.HttpUrl.Companion.toHttpUrlOrNull
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.RequestBody.Companion.toRequestBody
import java.io.File
import java.time.Duration
import okhttp3.RequestBody.Companion.asRequestBody

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

        private val okHttpClient: OkHttpClient by lazy { OkHttpClient() }


        fun get(uri: String, callback: Callback?) {
            val request = Request.Builder()
                .url("https://yourservice.com$uri")
                .build()
            okHttpClient.newCall(request).enqueue(callback!!)
        }

        fun get1(context: Context?, uri: String, callback: Callback?) {
            val url = "https://yourservice.com$uri".toHttpUrlOrNull()!!.newBuilder()
                .addQueryParameter("sn", SystemUtil.ltpSn)
                .build()

            val request = Request.Builder()
                .url(url)
                .build()

            okHttpClient.newCall(request).enqueue(callback!!)
        }

        fun get11(context: Context?, uri: String, callback: Callback?) {
            val url = "https://yourservice.com$uri".toHttpUrlOrNull()!!.newBuilder()
                .addQueryParameter("mac", EncryptionUtils.robotMac)
                .addQueryParameter("ts", EncryptionUtils.ts)
                .build()

            val request = Request.Builder()
                .url(url)
                .addHeader(AUTHORIZATION, EncryptionUtils.getHardCodeSign(EncryptionUtils.ts))
                .build()

            okHttpClient.newCall(request).enqueue(callback!!)
        }

        fun get11(context: Context?, auth: String?, ts: String?, uri: String, callback: Callback?) {

            val url = "https://yourservice.com$uri".toHttpUrlOrNull()!!.newBuilder()
                .addQueryParameter("mac", EncryptionUtils.robotMac)
                .addQueryParameter("ts", EncryptionUtils.ts)
                .build()

            val request = auth?.let {
                Request.Builder()
                    .url(url)
                    .addHeader(AUTHORIZATION, it)
                    .build()
            }

            okHttpClient.newCall(request!!).enqueue(callback!!)

        }

        fun get11(
            context: Context?,
            isChinese: Boolean,
            auth: String?,
            ts: String?,
            uri: String,
            callback: Callback?
        ) {
            val url = if (isChinese) {
                "https://yourservice.com$uri".toHttpUrlOrNull()!!.newBuilder()
                    .addQueryParameter("mac", EncryptionUtils.robotMac)
                    .addQueryParameter("ts", ts)
                    .apply {
                        if (uri == GeeUINetworkConsts.GET_APP_BG_INFO) {
                            addQueryParameter("package_name", context!!.packageName)
                        }
                    }
                    .build()
            } else {
                "https://yourservice.com$uri".toHttpUrlOrNull()!!.newBuilder()
                    .addQueryParameter("mac", EncryptionUtils.robotMac)
                    .addQueryParameter("ts", ts)
                    .apply {
                        if (uri == GeeUINetworkConsts.GET_APP_BG_INFO) {
                            addQueryParameter("package_name", context!!.packageName)
                        }
                    }
                    .build()
            }

            val country = if (isChinese) CN else GLOBAL

            val request = auth?.let {
                Request.Builder()
                    .url(url)
                    .addHeader(AUTHORIZATION, it)
                    .addHeader(COUNTRY, country)
                    .build()
            }

            okHttpClient.newCall(request!!).enqueue(callback!!)
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
            val url = if (isChinese) {
                "https://yourservice.com$uri".toHttpUrlOrNull()!!.newBuilder()
                    .addQueryParameter("sn", sn)
                    .addQueryParameter("ts", ts)
                    .apply {
                        if (uri == GeeUINetworkConsts.GET_APP_BG_INFO) {
                            addQueryParameter("package_name", context.packageName)
                        }
                    }
                    .build()
            } else {
                "https://yourservice.com$uri".toHttpUrlOrNull()!!.newBuilder()
                    .addQueryParameter("sn", sn)
                    .addQueryParameter("ts", ts)
                    .apply {
                        if (uri == GeeUINetworkConsts.GET_APP_BG_INFO) {
                            addQueryParameter("package_name", context.packageName)
                        }
                    }
                    .build()
            }

            val country = if (isChinese) CN else GLOBAL

            val request = auth?.let {
                Request.Builder()
                    .url(url)
                    .addHeader(AUTHORIZATION, it)
                    .addHeader(COUNTRY, country)
                    .build()
            }

            okHttpClient.newCall(request!!).enqueue(callback!!)
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

            val url = if (isChinese) {
                "https://yourservice.com$uri".toHttpUrlOrNull()!!.newBuilder()
                    .addQueryParameter("sn", sn)
                    .addQueryParameter("ts", ts)
                    .addQueryParameter("page_size", pageSize.toString() + "")
                    .build()
            } else {
                "https://yourservice.com$uri".toHttpUrlOrNull()!!.newBuilder()
                    .addQueryParameter("sn", sn)
                    .addQueryParameter("ts", ts)
                    .addQueryParameter("page_size", pageSize.toString() + "")
                    .build()
            }

            val country = if (isChinese) CN else GLOBAL

            val request = auth?.let {
                Request.Builder()
                    .url(url)
                    .addHeader(AUTHORIZATION, it)
                    .addHeader(COUNTRY, country)
                    .build()
            }

            okHttpClient.newCall(request!!).enqueue(callback!!)

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

            val url = if (isChinese) {
                "https://yourservice.com$uri".toHttpUrlOrNull()!!.newBuilder()
                    .addQueryParameter("sn", sn)
                    .addQueryParameter("ts", ts)
                    .build()
            } else {
                "https://yourservice.com$uri".toHttpUrlOrNull()!!.newBuilder()
                    .addQueryParameter("sn", sn)
                    .addQueryParameter("ts", ts)
                    .build()
            }

            val country = if (isChinese) CN else GLOBAL

            val request = auth?.let {
                Request.Builder()
                    .url(url)
                    .addHeader(AUTHORIZATION, it)
                    .addHeader(COUNTRY, country)
                    .build()
            }

            okHttpClient.newCall(request!!).enqueue(callback!!)
        }

        fun get11(
            context: Context?,
            auth: String?,
            sn: String?,
            ts: String?,
            uri: String,
            callback: Callback?
        ) {

            val url = "https://yourservice.com$uri".toHttpUrlOrNull()!!.newBuilder()
                    .addQueryParameter("sn", sn)
                    .addQueryParameter("ts", ts)
                    .build()
            
            val request = auth?.let {
                Request.Builder()
                    .url(url)
                    .addHeader(AUTHORIZATION, it)
                    .build()
            }

            okHttpClient.newCall(request!!).enqueue(callback!!)
            
        }

        fun get11(
            context: Context?,
            isChinese: Boolean,
            auth: String?,
            hashMap: Map<String?, String?>,
            uri: String,
            callback: Callback?
        ) {
            val url = if (isChinese) {
                "https://yourservice.com$uri".toHttpUrlOrNull()!!.newBuilder()
                    .apply {
                        hashMap.forEach { (key, value) ->
                            addQueryParameter(key!!, value)
                        }
                    }
                    .build()
            } else {
                "https://yourservice.com$uri".toHttpUrlOrNull()!!.newBuilder()
                    .apply {
                        hashMap.forEach { (key, value) ->
                            addQueryParameter(key!!, value)
                        }
                    }
                    .build()
            }

            val request = auth?.let {
                Request.Builder()
                    .url(url)
                    .addHeader(AUTHORIZATION, it)
                    .build()
            }

            okHttpClient.newCall(request!!).enqueue(callback!!)
        }
        
        fun get11(
            context: Context?,
            auth: String?,
            hashMap: Map<String?, String?>,
            uri: String,
            callback: Callback?
        ) {
            val url = "https://yourservice.com$uri".toHttpUrlOrNull()!!.newBuilder()
                    .apply {
                        hashMap.forEach { (key, value) ->
                            addQueryParameter(key!!, value)
                        }
                    }
                    .build()
          
            val request = auth?.let {
                Request.Builder()
                    .url(url)
                    .addHeader(AUTHORIZATION, it)
                    .build()
            }

            okHttpClient.newCall(request!!).enqueue(callback!!)
        }

        fun get11ForMac(
            context: Context?,
            auth: String?,
            ts: String?,
            uri: String,
            callback: Callback?
        ) {

            val mac: String = EncryptionUtils.robotMac

            val url = "https://yourservice.com$uri".toHttpUrlOrNull()!!.newBuilder()
                .addQueryParameter("mac", mac)
                .addQueryParameter("ts", ts)
                .build()

            val request = auth?.let {
                Request.Builder()
                    .url(url)
                    .addHeader(AUTHORIZATION, it)
                    .build()
            }

            okHttpClient.newCall(request!!).enqueue(callback!!)
            
        }

        fun get(
            context: Context?,
            uri: String,
            hashMap: HashMap<String, String>,
            callback: Callback?
        ) {
            val url = "https://yourservice.com$uri".toHttpUrlOrNull()!!.newBuilder()
                .apply {
                    hashMap.forEach { (key, value) ->
                        addQueryParameter(key, value)
                    }
                }
                .build()

            val request = Request.Builder()
                .url(url)
                .build()

            okHttpClient.newCall(request).enqueue(callback!!)
        }

        fun get(context: Context?, uri: String, callback: Callback?) {
            val sn = SystemUtil.ltpSn
            val authorization = ""
            get1(context, sn, authorization, uri, callback)
        }

        fun get1(context: Context?, sn: String?, key: String?, uri: String, callback: Callback?) {
            val url = "https://yourservice.com$uri".toHttpUrlOrNull()!!.newBuilder()
                .addQueryParameter("sn", sn)
                .build()

            val request = Request.Builder()
                .url(url)
                .build()

            okHttpClient.newCall(request).enqueue(callback!!)
        }

        fun get2(context: Context?, sn: String?, key: String?, uri: String, callback: Callback?) {
            val url = "https://yourservice.com$uri".toHttpUrlOrNull()!!.newBuilder()
                .addQueryParameter("sn", sn)
                .build()

            val request = key?.let {
                Request.Builder()
                    .url(url)
                    .addHeader(AUTHORIZATION, it)
                    .build()
            }

            okHttpClient.newCall(request!!).enqueue(callback!!)
        }

        fun post(uri: String, hashMap: HashMap<*, *>, callback: Callback?) {

            val url = "https://yourservice.com$uri".toHttpUrlOrNull()!!

            val mediaType = "application/json; charset=utf-8".toMediaTypeOrNull()!!
            val requestBody = Gson().toJson(hashMap).toRequestBody(mediaType)
            val request = Request.Builder()
                .url(url)
                .post(requestBody)
                .addHeader("Content-Type", "application/json")
                .build()

            val okHttpClient = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                OkHttpClient.Builder()
                    .connectTimeout(Duration.ofMinutes(10))
                    .readTimeout(Duration.ofMinutes(10))
                    .callTimeout(Duration.ofMinutes(10))
                    .build()
            } else {
                OkHttpClient()
            }

            okHttpClient.newCall(request).enqueue(callback!!)

        }

        fun post1(uri: String, hashMap: HashMap<*, *>, callback: Callback?) {
            val url = "https://yourservice.com$uri".toHttpUrlOrNull()!!.newBuilder()
            .addQueryParameter("sn", SystemUtil.ltpSn)
                .build()

            val mediaType = "application/json; charset=utf-8".toMediaTypeOrNull()!!
            val requestBody = Gson().toJson(hashMap).toRequestBody(mediaType)

            val request = Request.Builder()
                .url(url)
                .post(requestBody)
                .addHeader("Content-Type", "application/json")
                .build()

            val okHttpClient = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                OkHttpClient.Builder()
                    .connectTimeout(Duration.ofMinutes(10))
                    .readTimeout(Duration.ofMinutes(10))
                    .callTimeout(Duration.ofMinutes(10))
                    .build()
            } else {
                OkHttpClient()
            }

            okHttpClient.newCall(request).enqueue(callback!!)
        }

        fun post2(uri: String, hashMap: HashMap<*, *>, callback: Callback?) {
            val url = "https://yourservice.com$uri".toHttpUrlOrNull()!!.newBuilder()
                .addQueryParameter("sn", SystemUtil.ltpSn)
                .addQueryParameter("ts", (System.currentTimeMillis() / 1000).toString() + "")
                .build()

            val mediaType = "application/json; charset=utf-8".toMediaTypeOrNull()!!
            val requestBody = Gson().toJson(hashMap).toRequestBody(mediaType)

            val request = Request.Builder()
                .url(url)
                .post(requestBody)
                .addHeader("Content-Type", "application/json")
                .build()

            val okHttpClient = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                OkHttpClient.Builder()
                    .connectTimeout(Duration.ofMinutes(10))
                    .readTimeout(Duration.ofMinutes(10))
                    .callTimeout(Duration.ofMinutes(10))
                    .build()
            } else {
                OkHttpClient()
            }

            okHttpClient.newCall(request).enqueue(callback!!)
        }

        fun post2(
            hashMap: HashMap<*, *>,
            auth: String?,
            ts: String?,
            uri: String,
            callback: Callback?
        ) {
            val url = "https://yourservice.com$uri".toHttpUrlOrNull()!!.newBuilder()
            .addQueryParameter("sn", SystemUtil.ltpSn)
                .addQueryParameter("ts", ts)
                .build()

            val mediaType = "application/json; charset=utf-8".toMediaTypeOrNull()!!
            val requestBody = Gson().toJson(hashMap).toRequestBody(mediaType)

            val request = auth?.let {
                Request.Builder()
                    .url(url)
                    .post(requestBody)
                    .addHeader(AUTHORIZATION, it)
                    .addHeader("Content-Type", "application/json")
                    .build()
            }

            val okHttpClient = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                OkHttpClient.Builder()
                    .connectTimeout(Duration.ofMinutes(10))
                    .readTimeout(Duration.ofMinutes(10))
                    .callTimeout(Duration.ofMinutes(10))
                    .build()
            } else {
                OkHttpClient()
            }

            okHttpClient.newCall(request!!).enqueue(callback!!)
        }

        fun post3(
            hashMap: HashMap<*, *>,
            auth: String?,
            queryMap: Map<String?, String?>,
            uri: String,
            callback: Callback?
        ) {
            val url = "https://yourservice.com$uri".toHttpUrlOrNull()!!.newBuilder()
                .apply {
                    queryMap.forEach { (key, value) ->
                        addQueryParameter(key!!, value)
                    }
                }
                .build()

            val mediaType = "application/json; charset=utf-8".toMediaTypeOrNull()!!
            val requestBody = Gson().toJson(hashMap).toRequestBody(mediaType)

            val request = auth?.let {
                Request.Builder()
                    .url(url)
                    .post(requestBody)
                    .addHeader(AUTHORIZATION, it)
                    .addHeader("Content-Type", "application/json")
                    .build()
            }

            val okHttpClient = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                OkHttpClient.Builder()
                    .connectTimeout(Duration.ofMinutes(10))
                    .readTimeout(Duration.ofMinutes(10))
                    .callTimeout(Duration.ofMinutes(10))
                    .build()
            } else {
                OkHttpClient()
            }

            okHttpClient.newCall(request!!).enqueue(callback!!)
        }

        fun post3(
            hashMap: HashMap<*, *>,
            isChinese: Boolean,
            auth: String?,
            queryMap: Map<String?, String?>,
            uri: String,
            callback: Callback?
        ) {
            val url = if (isChinese) {
                "https://yourservice.com$uri".toHttpUrlOrNull()!!.newBuilder()
                    .apply {
                        queryMap.forEach { (key, value) ->
                            addQueryParameter(key!!, value)
                        }
                    }
                    .build()
            } else {
                "https://yourservice.com$uri".toHttpUrlOrNull()!!.newBuilder()
                    .apply {
                        queryMap.forEach { (key, value) ->
                            addQueryParameter(key!!, value)
                        }
                    }
                    .build()
            }

            val mediaType = "application/json; charset=utf-8".toMediaTypeOrNull()!!
            val requestBody = Gson().toJson(hashMap).toRequestBody(mediaType)

            val country = if (isChinese) CN else GLOBAL

            val request = auth?.let {
                Request.Builder()
                    .url(url)
                    .post(requestBody)
                    .addHeader(AUTHORIZATION, it)
                    .addHeader(COUNTRY, country)
                    .addHeader("Content-Type", "application/json")
                    .build()
            }

            val okHttpClient = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                OkHttpClient.Builder()
                    .connectTimeout(Duration.ofMinutes(10))
                    .readTimeout(Duration.ofMinutes(10))
                    .callTimeout(Duration.ofMinutes(10))
                    .build()
            } else {
                OkHttpClient()
            }

            okHttpClient.newCall(request!!).enqueue(callback!!)
        }


        fun uploadFile(filePath: String?, callback: Callback?) {
            // Replace these with your actual server URL and file path
            val serverUrl = "http://example.com/upload".toHttpUrlOrNull()!!
            val file = File(filePath)

            val requestBody = MultipartBody.Builder()
                .setType(MultipartBody.FORM)
                .addFormDataPart(
                    "file",
                    file.name,
                    file.asRequestBody("application/octet-stream".toMediaTypeOrNull())
                )
                .build()

            val request = Request.Builder()
                .url(serverUrl)
                .post(requestBody)
                .build()

            okHttpClient.newCall(request).enqueue(callback!!)
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
