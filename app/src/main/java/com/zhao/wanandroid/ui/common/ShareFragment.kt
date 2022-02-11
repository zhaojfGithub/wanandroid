package com.zhao.wanandroid.ui.common

import android.os.Bundle
import android.view.*
import com.zhao.wanandroid.R
import com.zhao.wanandroid.base.BaseLoadingInterface
import com.zhao.wanandroid.base.BaseVmActivity
import com.zhao.wanandroid.base.BaseVmFragment
import com.zhao.wanandroid.databinding.FragmentShareBinding
import com.zhao.wanandroid.ui.main.fragment.home.HomeFragment
import com.zhao.wanandroid.utils.LogUtils

/**
 *创建时间： 2022/2/10
 *编   写：  zjf
 *页面功能:
 */
class ShareFragment : BaseVmFragment<CommonViewModel, FragmentShareBinding>() {

    companion object {
        @JvmStatic
        fun newInstance() = ShareFragment()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        setHasOptionsMenu(true)
        return super.onCreateView(inflater, container, savedInstanceState)
    }

    override fun getLayoutId(): Int {
        return R.layout.fragment_share
    }

    override fun initView() {
        (activity as CommonActivity).supportActionBar?.title = getString(R.string.share_article)
    }


    override fun initData() {

    }

    override fun observer() {

    }

    override fun getModelClass(): Class<CommonViewModel> {
        return CommonViewModel::class.java
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.menu_share, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }


    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.action_share -> {
                if (binding.tvArticleTitle.text?.isNotEmpty() == true && binding.tvArticleUrl.text?.isNotEmpty() == true) {
                    viewModel.articleShare(binding.tvArticleTitle.text.toString(), binding.tvArticleUrl.text.toString())
                }else{
                    (requireActivity() as BaseLoadingInterface).isShowMsgDialog(getString(R.string.please_examine_hint))
                }
            }
        }
        return true
    }


}