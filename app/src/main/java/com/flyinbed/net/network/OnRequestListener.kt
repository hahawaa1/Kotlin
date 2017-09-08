package com.cdd.cdb.network

/**
 * 作者：Administrator on 2017/8/28 16:25
 * 邮箱：zhanghuaiha@gmail.com
 */
interface OnRequestListener<T> {
    fun success(t : T)
}