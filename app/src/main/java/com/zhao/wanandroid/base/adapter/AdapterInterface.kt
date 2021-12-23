package com.zhao.wanandroid.base.adapter

/**
 *创建时间： 2021/12/17
 *编   写：  zjf
 *页面功能:
 */
interface AdapterInterface<T> {
    /**
     * 点击事件
     */
    fun onItemClick(onClick: (Int, T) -> Unit)

    /**
     * 长按事件
     */
    fun onItemLongClick(onLongClick: (Int, T) -> Unit)

    /**
     * 添加item到顶部
     */
    fun addHeaderItemData(data: T)

    /**
     * 添加item到底部
     */
    fun addFooterItemData(data: T)

    /**
     * 添加多个item到顶部
     */
    fun addHeaderItemAllData(data: List<T>)

    /**
     * 添加多个item到底部
     */
    fun addFooterItemAllData(data: List<T>)

    /**
     * 刷新[data]所处位置
     */
    fun refreshItem(data: T)

    /**
     * 刷新[data],要求在总列表中必须相连
     */
    fun refreshAllItem(data: List<T>)

    /**
     * 删除某个item
     */
    fun removeItemData(data: T)

    /**
     * 删除多个item，要求在总列表中必须相连
     */
    fun removePartItemData(data: List<T>)

}