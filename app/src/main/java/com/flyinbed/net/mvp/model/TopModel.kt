package com.flyinbed.net.mvp.model

import android.content.Context
import com.cdd.cdb.network.ApiService
import com.cdd.cdb.network.RetrofitClient
import com.flyinbed.net.mvp.contract.TopContract
import com.flyinbed.net.mvp.model.bean.Top
import com.flyinbed.net.network.BaseResponseEntity
import rx.Observable

/**
 * 作者：Administrator on 2017/9/7 16:06
 * 邮箱：zhanghuaiha@gmail.com
 */

class TopModel : TopContract.Model {
    override fun loadTop(context: Context): Observable<BaseResponseEntity<List<Top>>>? {
        return RetrofitClient.getInstance(context,ApiService.BASE_URL).mApi?.getTop()
    }
}
