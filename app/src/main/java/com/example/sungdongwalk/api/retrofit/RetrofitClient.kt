package com.example.sungdongwalk.api.retrofit

import com.example.sungdongwalk.api.utils.User.TOKEN
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.io.IOException
import java.util.concurrent.TimeUnit

object RetrofitClient {
    // 레트로핏 클라이언트 선언
    private var retrofitClient: Retrofit? = null

    // 레트로핏 클라이언트 가져오기
    fun getClient(baseUrl: String): Retrofit? {
        if(retrofitClient == null){
            // 레트로핏 빌더를 통해 인스턴스 생성
            retrofitClient = Retrofit.Builder()
                .baseUrl(baseUrl)
                .client(getOkHttpClient(AppInterceptor()))
                .addConverterFactory(GsonConverterFactory.create())
                .build()
        }
        return retrofitClient
    }

    private fun getOkHttpClient(interceptor: AppInterceptor) : OkHttpClient
            = OkHttpClient.Builder().run{
        addInterceptor(interceptor)
        connectTimeout(300, TimeUnit.SECONDS); // connect timeout
        readTimeout(300, TimeUnit.SECONDS);    // socket timeout
        build()
    }
    class AppInterceptor : Interceptor {
        @Throws(IOException::class)
        override fun intercept(chain: Interceptor.Chain) : Response = with(chain) {
            val newRequest = request().newBuilder()
                .addHeader("Authorization", "Bearer ${TOKEN}")
                .build()
            proceed(newRequest)
        }
    }
}