package com.flyinbed.net.mvp.contract

import android.content.Context
import com.flyinbed.net.mvp.model.bean.Top
import com.flyinbed.net.network.BaseResponseEntity
import com.tt.lvruheng.eyepetizer.base.BasePresenter
import com.tt.lvruheng.eyepetizer.base.BaseView
import rx.Observable

/**
 * 作者：Administrator on 2017/9/7 16:06
 * 邮箱：zhanghuaiha@gmail.com
 */

interface TopContract {
    interface Model{
        fun loadTop(context: Context) : Observable<BaseResponseEntity<List<Top>>>?
    }

    interface View : BaseView<Presenter>{
        fun setTop(responseEntity: BaseResponseEntity<List<Top>>)
    }

    interface Presenter : BasePresenter{
        fun requestData()
    }
}
