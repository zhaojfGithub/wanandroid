package com.zhao.wanandroid.ui

import android.content.Context
import android.util.AttributeSet
import android.view.*
import android.view.inputmethod.EditorInfo
import android.widget.PopupWindow
import android.widget.TextView
import androidx.appcompat.widget.AppCompatEditText
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.zhao.wanandroid.R
import com.zhao.wanandroid.utils.LogUtils


/**
 *创建时间： 2021/12/22
 *编   写：  zjf
 *页面功能:
 */
class SearchTipsEditText : AppCompatEditText {

    private val searchData: MutableList<String> by lazy { ArrayList() }

    private var popupWindow: PopupWindow? = null

    private var successListener: ((Int) -> Unit)? = null
    private var errorListener: ((String) -> Unit)? = null

    private val adapter: SearchTipsAdapter by lazy {
        SearchTipsAdapter {
            successListener?.let { it1 -> it1(it) }
            dismissPopupWindow()
        }
    }

    constructor(context: Context) : super(context, null)

    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs, 0)

    constructor(context: Context, attrs: AttributeSet?, defStyle: Int) : super(context, attrs, defStyle)

    fun setSuccessListener(listener: (Int) -> Unit) {
        this.successListener = listener
    }

    fun setErrorListener(error: (String) -> Unit) {
        this.errorListener = error
    }

    fun setData(data: List<String>) {
        if (searchData.isNotEmpty()) {
            searchData.clear()
        }
        searchData.addAll(data)
    }

    fun showPopupWindow() {
        if (popupWindow == null) {
            val view = LayoutInflater.from(context).inflate(R.layout.view_search, null)
            val recyclerView = view.findViewById<RecyclerView>(R.id.recyclerView)
            recyclerView.layoutManager = LinearLayoutManager(context)
            recyclerView.adapter = adapter
            popupWindow = PopupWindow(context)
            popupWindow?.apply {
                contentView = view
                isOutsideTouchable = true
                isTouchable = true
                inputMethodMode = PopupWindow.INPUT_METHOD_NEEDED
                softInputMode = WindowManager.LayoutParams.SOFT_INPUT_ADJUST_NOTHING
                width = this@SearchTipsEditText.width
                height = ViewGroup.LayoutParams.WRAP_CONTENT
                setBackgroundDrawable(ContextCompat.getDrawable(context, R.color.blue_400))
            }
        }
        if (!popupWindow!!.isShowing) {
            val location = IntArray(2)
            getLocationOnScreen(location)
            popupWindow!!.showAtLocation(this, Gravity.NO_GRAVITY, location[0], location[1] + height)
        }
    }

    fun dismissPopupWindow() {
        popupWindow?.takeIf { it.isShowing }?.also {
            it.dismiss()
        }
    }

    override fun onTextChanged(text: CharSequence?, start: Int, lengthBefore: Int, lengthAfter: Int) {
        super.onTextChanged(text, start, lengthBefore, lengthAfter)
        LogUtils.e(text.toString())
        if (text == null || text.isEmpty() || searchData.isEmpty()){
            //从有内容到无内容，就不用应该显示了
            dismissPopupWindow()
            return
        }
        searchData.filter { it.indexOf(text.toString()) != -1 }.also {
            showPopupWindow()
            adapter.refreshList(it)
        }
    }

    override fun onEditorAction(actionCode: Int) {
        super.onEditorAction(actionCode)
        if (actionCode != EditorInfo.IME_ACTION_SEARCH) return
        if (text.toString().isEmpty()) {
            errorListener?.let { it("输入为空") }
        }
        for (index in searchData.indices) {
            if (searchData[index] == text.toString()) {
                successListener?.let { it(index) }
                return
            }
        }
        errorListener?.let { it("无此文本") }
    }

    private class SearchTipsAdapter(val onClick: (Int) -> Unit) : RecyclerView.Adapter<SearchTipsAdapter.ViewHolder>() {

        private val findList: MutableList<String> by lazy { ArrayList() }

        class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
            val textView: TextView = itemView.findViewById(android.R.id.text1)
        }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
            val view: View = LayoutInflater.from(parent.context).inflate(android.R.layout.test_list_item, parent, false)
            return ViewHolder(view)
        }

        override fun onBindViewHolder(holder: ViewHolder, position: Int) {
            holder.textView.text = findList[position]
            holder.itemView.setOnClickListener {
                onClick(position)
            }
        }

        override fun getItemCount(): Int {
            return findList.size
        }

        fun refreshList(data: List<String>) {
            if (findList.isNotEmpty()) {
                notifyItemRangeRemoved(0,findList.size)
                findList.clear()
            }
            findList.addAll(data)
            notifyItemRangeInserted(0,findList.size)
        }

    }
}