package com.flyinbed.net.mvp.presenter

import android.content.Context
import com.cdd.cdb.network.HttpObserver
import com.flyinbed.net.mvp.contract.TopContract
import com.flyinbed.net.mvp.model.TopModel
import com.flyinbed.net.mvp.model.bean.Top
import com.flyinbed.net.network.BaseResponseEntity
import com.tt.lvruheng.eyepetizer.utils.applySchedulers

/**
 * 作者：Administrator on 2017/9/7 16:06
 * 邮箱：zhanghuaiha@gmail.com
 */

class TopPresenter : TopContract.Presenter {

    var mContext : Context? = null
    var mView : TopContract.View? = null
    var mModel : TopModel? = null

    //构造方法
    init {
        mModel = TopModel()
    }

    fun initView(context: Context,view: TopContract.View){
        mView = view
        mContext = context
    }

    override fun start() {
        requestData()
    }

    override fun requestData() {
        mModel?.loadTop(mContext!!)?.applySchedulers()?.subscribe(object :HttpObserver<BaseResponseEntity<List<Top>>>(){
            override fun success(t: BaseResponseEntity<List<Top>>) {
                mView?.setTop(t)
            }
        })
    }
}
