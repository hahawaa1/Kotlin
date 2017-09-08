package com.cdd.cdb.ui.base

import android.Manifest
import android.content.Context
import android.content.DialogInterface
import android.content.Intent
import android.content.pm.ActivityInfo
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.support.v7.app.AlertDialog
import android.support.v7.app.AppCompatActivity
import com.tbruyelle.rxpermissions2.RxPermissions
import com.tt.lvruheng.eyepetizer.utils.showToast
import rx.subscriptions.CompositeSubscription


/**
 * 作者：Administrator on 2017/8/30 10:49
 * 邮箱：zhanghuaiha@gmail.com
 */
abstract class BaseActivity : AppCompatActivity(){

    var open = 0x00001
    var rxPermissions : RxPermissions? = null
    var context : Context? = null
    var mCompositeSubscription : CompositeSubscription? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //设置竖屏锁死
        requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT
        init()
        setContentView(provideContentViewId())
        //初始化权限管理
        rxPermissions = RxPermissions(this)
        context = this
        mCompositeSubscription = CompositeSubscription()
        initView()
        initData()
        initListener()
    }

    //在加载布局前的方法，需要使用可以重写
    open fun init(){}

    //布局文件
    abstract fun provideContentViewId(): Int

    //初始化控件
    abstract fun initView()

    //初始化数据
    abstract fun initData()

    //点击事件处理
    open fun initListener(){}

    //单个权限申请
    open fun singlePermissions(permission: String) {
        rxPermissions?.requestEach(permission)
                ?.subscribe{permission ->
                    when (permission.granted) {
                    // 用户已经同意该权限
                        true ->  consent()
                    // 用户拒绝了该权限，并且选中『不再询问』
                        permission.shouldShowRequestPermissionRationale ->   Dialog()
                    // 用户拒绝了该权限，没有选中『不再询问』（Never ask again）,那么下次再次启动时，还会提示请求权限的对话框
                        else ->  context?.showToast(permission.name)
                    }
                }
    }
    //当用户权限被调用，且同意，所要走的方法 子类需要自己去实现
    open fun consent(){}

    //一次获取所有权限,
    open fun multiPermissions(){
        open = 0x00001
        //常用权限,定位，相机，SD卡读写，日历
        rxPermissions?.requestEach(Manifest.permission.ACCESS_FINE_LOCATION,
                Manifest.permission.ACCESS_COARSE_LOCATION,
                Manifest.permission.CAMERA,
                Manifest.permission.READ_EXTERNAL_STORAGE,
                Manifest.permission.WRITE_EXTERNAL_STORAGE,
                Manifest.permission.WRITE_CALENDAR,
                Manifest.permission.READ_CALENDAR)
                ?.subscribe{permission ->
                    when (permission.granted) {
                    // 用户已经同意该权限
                        true ->{
                            println("同意了"+permission.name)
                        }

                                // 用户拒绝了该权限，并且选中『不再询问』
                                permission.shouldShowRequestPermissionRationale ->   judge()
                    // 用户拒绝了该权限，没有选中『不再询问』（Never ask again）,那么下次再次启动时，还会提示请求权限的对话框
                        else ->    multiPermissions()
                    }
        }
    }

    private fun judge(){
        when(open){
            0x00001 -> Dialog()
        }
    }

    private fun Dialog(){
        open = 0x0002
        val builder = AlertDialog.Builder(this)
        builder.setMessage("权限未开启，暂无法使用此功能")
                .setCancelable(false)
                .setPositiveButton("取消", DialogInterface.OnClickListener { dialog, id -> dialog.cancel() })
                .setNegativeButton("前去设置", DialogInterface.OnClickListener { dialog, id -> getAppDetailSettingIntent()}).show()
        val alert = builder.create()
    }

    //当权限被拒绝前往设置界面
    private fun getAppDetailSettingIntent() {
        val localIntent = Intent()
        localIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
        if (Build.VERSION.SDK_INT > 16) {
            localIntent.action = "android.settings.APPLICATION_DETAILS_SETTINGS"
            localIntent.data = Uri.fromParts("package", packageName, null)
        } else if (Build.VERSION.SDK_INT <= 16) {
            localIntent.action = Intent.ACTION_VIEW
            localIntent.setClassName("com.android.settings", "com.android.settings.InstalledAppDetails")
            localIntent.putExtra("com.android.settings.ApplicationPkgName", packageName)
        }
        startActivity(localIntent)
    }

    override fun onDestroy() {
        super.onDestroy()
        when {
            mCompositeSubscription?.hasSubscriptions()!! -> mCompositeSubscription?.unsubscribe()
        }

    }
}


