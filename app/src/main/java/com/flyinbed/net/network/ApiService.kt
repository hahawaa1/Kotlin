package com.cdd.cdb.network

import com.flyinbed.net.mvp.model.bean.Top
import com.flyinbed.net.network.BaseResponseEntity
import com.flyinbed.net.network.NetWorkCodeInfo
import retrofit2.http.FieldMap
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.POST
import rx.Observable


/**
 * 作者：Administrator on 2017/8/28 14:18
 * 邮箱：zhanghuaiha@gmail.com
 */
interface ApiService {
    //http://api.douban.com/v2/movie/top250

    companion object{
        val BASE_URL : String
            get() = "http://api.douban.com"
    }

    @GET("/v2/movie/top250")
    fun getTop(): Observable<BaseResponseEntity<List<Top>>>
}