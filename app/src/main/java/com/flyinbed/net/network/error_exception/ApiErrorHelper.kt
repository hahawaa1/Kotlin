package com.cdd.cdb.network.error_exception

import com.flyinbed.net.App
import com.tt.lvruheng.eyepetizer.utils.showToast
import retrofit2.adapter.rxjava.HttpException
import java.io.IOException
import java.net.ConnectException


/**
 * 作者：Administrator on 2017/8/29 17:32
 * 邮箱：zhanghuaiha@gmail.com
 */

object ApiErrorHelper {
    fun handleCommonError(e: Throwable) {
        print("网络异常："+e :: javaClass)
//        if (e is HttpException){
//            App.mContext?.showToast("网络异常,请重试")
//        }else {
//            App.mContext?.showToast(judge(e.message!!))
//        }
        when(e){
            is ConnectException -> App.mContext?.showToast("没有网络,请检查你的网络")
            is HttpException -> App.mContext?.showToast("网络异常,请重试......")
            is IOException ->  App.mContext?.showToast("数据加载错误,请重试")
            //后台返回的message
            is ApiException ->  App.mContext?.showToast(e.message!!)
            else ->   App.mContext?.showToast(judge(e.message!!))
            } //App.mContext?.showToast(e.message!!)
        }

    private fun judge(msg : String) : String {
        return when {
            msg.indexOf("50") != -1 -> "网络异常，请重试"
            msg.indexOf("40") != -1 -> "网络请求失败，请重试"
            msg.indexOf("41") != -1 -> "网络请求失败，请重试"
            msg.indexOf("30") != -1 -> "网络加载失败，请重试"
            else -> msg
        }
    }
}
