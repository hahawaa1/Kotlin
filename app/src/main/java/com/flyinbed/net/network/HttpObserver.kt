package com.cdd.cdb.network


import android.app.Dialog
import com.cdd.cdb.network.error_exception.ApiErrorHelper
import com.cdd.cdb.utils.loadDialog
import com.flyinbed.net.App
import com.flyinbed.net.network.MySubscriber


/**
 * 作者：Administrator on 2017/8/28 16:22
 * 邮箱：zhanghuaiha@gmail.com
 */
abstract class HttpObserver<T> : MySubscriber<T>(), OnRequestListener<T>{
    var loadDialog : Dialog? = null
    init {
        loadDialog = App.mContext?.loadDialog("正在加载..",false)
    }

    override fun onCompleted() {
    }

    override fun onNext(t: T) {
        success(t)
    }
    override fun onError(t: Throwable?) {
        ApiErrorHelper.handleCommonError(t!!)
    }

    override fun onStart() {
        super.onStart()
    }

    override fun unsubscribe() {
        super.unsubscribe()
    }
}