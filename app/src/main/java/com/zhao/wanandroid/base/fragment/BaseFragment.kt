package com.zhao.wanandroid.base.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.ConcatAdapter
import com.zhao.wanandroid.base.adapter.footer.BaseSimplenessFooterAdapter
import com.zhao.wanandroid.utils.LogUtils

/**
 *创建时间： 2021/9/1
 *编   写：  zjf
 *页面功能:
 */
abstract class BaseFragment : Fragment() {

    protected val footerAdapter: BaseSimplenessFooterAdapter by lazy { BaseSimplenessFooterAdapter() }
    protected val config by lazy { ConcatAdapter.Config.Builder().setIsolateViewTypes(true).build() }

    private var isLazyInit = false

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(getLayoutId(), container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initCreated()
    }

    open fun initCreated() {
        initView()
        initData()
    }

    abstract fun initData()

    open fun lazyData() {

    }

    abstract fun initView()

    @LayoutRes
    abstract fun getLayoutId(): Int

    override fun onResume() {
        super.onResume()
        if (!isLazyInit) {
            isLazyInit = true
            lazyData()
        }
    }
}