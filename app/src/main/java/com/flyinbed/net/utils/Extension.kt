package com.tt.lvruheng.eyepetizer.utils

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.widget.Toast
import com.flyinbed.net.App
import com.flyinbed.net.utils.ObtainVersion
import rx.Observable
import rx.android.schedulers.AndroidSchedulers
import rx.schedulers.Schedulers
import java.util.regex.Pattern


/**
 * Created by lvruheng on 2017/7/2.
 */
//强大的Toast
fun Context.showToast(message: String) : Toast {
    var toast : Toast = Toast.makeText(this,message,Toast.LENGTH_SHORT)
//    toast.setGravity(Gravity.CENTER,0,0)
    toast.show()
    return toast
}
//Activity切换
inline fun <reified T: Activity> Activity.newIntent() {
    val intent = Intent(this, T::class.java)
    startActivity(intent)
}

fun <T> Observable<T>.applySchedulers(): Observable<T> {
    return subscribeOn(Schedulers.io()).
            unsubscribeOn(AndroidSchedulers.mainThread())
            .observeOn(AndroidSchedulers.mainThread())
}

//判断手机号格式是否正确
fun Context.isMobileNO(mobiles:String) : Boolean{
    var telRegex = "[1][345678]\\d{9}"
    return when(mobiles.isEmpty()){
        true -> false
        else -> mobiles.matches(telRegex)
    }
}

private fun String.matches(regex: String): Boolean {
    return Pattern.matches(regex, this)
}







