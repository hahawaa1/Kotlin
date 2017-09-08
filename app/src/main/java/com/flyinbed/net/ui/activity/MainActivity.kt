package com.flyinbed.net.ui.activity


import android.app.Dialog
import android.content.Context
import android.content.Intent
import android.support.v7.widget.LinearLayoutManager
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.cdd.cdb.ui.base.BaseActivity
import com.cdd.cdb.utils.loadDialog
import com.flyinbed.net.R
import com.flyinbed.net.mvp.contract.TopContract
import com.flyinbed.net.mvp.model.bean.Top
import com.flyinbed.net.mvp.presenter.TopPresenter
import com.flyinbed.net.network.BaseResponseEntity
import com.tt.lvruheng.eyepetizer.utils.newIntent
import kotlinx.android.synthetic.main.activity_main.*
import com.zhy.adapter.recyclerview.CommonAdapter
import com.zhy.adapter.recyclerview.base.ViewHolder


class MainActivity : BaseActivity(),TopContract.View {
    var mPresenter : TopPresenter? = null
    var mDialog : Dialog? = null
    var mContext : Context? = null

    //TOP250接口回调
    override fun setTop(responseEntity: BaseResponseEntity<List<Top>>) {
        mDialog?.dismiss()
        mRecycler.adapter = (object : CommonAdapter<Top>(this, R.layout.item_list,responseEntity.subjects) {
            override fun convert(holder: ViewHolder?, t: Top?, position: Int) {
                holder?.setText(R.id.name,t?.title)
                holder?.setText(R.id.year,t?.year)
                val icon = holder?.getView<ImageView>(R.id.icon)
                Glide.with(mContext).load(t?.images?.medium).into(icon)

                holder?.itemView?.setOnClickListener {
                    var intent = Intent(mContext, WebActivity::class.java)
                    intent.putExtra("url",t?.alt)
                    startActivity(intent)
                }
            }
        })
    }

    override fun provideContentViewId(): Int {
        return R.layout.activity_main
    }

    override fun initView() {
        mContext = this
        mDialog = this.loadDialog("正在加载..",false)
        mRecycler.layoutManager = LinearLayoutManager(this)
        mPresenter = TopPresenter()
        mPresenter?.initView(this,this)

    }

    override fun initData() {
        this.multiPermissions()
        mPresenter?.start()
        mDialog?.show()
    }
}
