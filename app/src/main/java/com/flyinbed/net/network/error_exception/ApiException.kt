package com.cdd.cdb.network.error_exception

/**
 * 作者：Administrator on 2017/8/29 16:57
 * 邮箱：zhanghuaiha@gmail.com
 * 异常处理
 */

class ApiException(val code: Int, msg: String) : RuntimeException(msg)
