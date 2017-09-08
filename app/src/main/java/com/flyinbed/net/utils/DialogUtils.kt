package com.cdd.cdb.utils

import android.app.Dialog
import android.content.Context
import android.widget.TextView
import com.flyinbed.net.R


/**
 * 作者：Administrator on 2017/8/31 14:49
 * 邮箱：zhanghuaiha@gmail.com
 * dialog工具类
 */

//提示dialog
fun Context.TsDialog(msg:String,flag:Boolean){
    val progressDialog = Dialog(this, R.style.progress_dialog)
    progressDialog.setContentView(R.layout.dialog_ts)
    progressDialog.setCancelable(true)
    progressDialog.setCanceledOnTouchOutside(flag)
    progressDialog.window!!.setBackgroundDrawableResource(android.R.color.transparent)
    val tv_msg = progressDialog.findViewById(R.id.id_tv_loadingmsg) as TextView
    val save = progressDialog.findViewById(R.id.dialog_save) as TextView
    save.setOnClickListener { progressDialog.dismiss() }
    tv_msg.text = msg
    progressDialog.show()
}

//加载等待dialog
fun Context.loadDialog(msg:String,flag:Boolean) : Dialog{
    val progressDialog = Dialog(this, R.style.progress_dialog)
    progressDialog.setContentView(R.layout.dialog_loading)
    progressDialog.setCancelable(true)
    progressDialog.setCanceledOnTouchOutside(flag)
    progressDialog.window!!.setBackgroundDrawableResource(android.R.color.transparent)
    val tv_msg = progressDialog.findViewById(R.id.id_tv_loadingmsg) as TextView
    tv_msg.text = msg
    return progressDialog
}

//有取消和确定的dialog
fun Context.selectDialog(msg:String,flag:Boolean) : Dialog{
    val progressDialog = Dialog(this, R.style.progress_dialog)
    progressDialog.setContentView(R.layout.dialog_select)
    progressDialog.setCancelable(true)
    progressDialog.setCanceledOnTouchOutside(flag)
    progressDialog.window!!.setBackgroundDrawableResource(android.R.color.transparent)
    val tv_msg = progressDialog.findViewById(R.id.id_tv_loadingmsg) as TextView
    val save = progressDialog.findViewById(R.id.dialog_save) as TextView
    save.setOnClickListener { progressDialog.dismiss() }
    tv_msg.text = msg
    progressDialog.show()
    return progressDialog
}