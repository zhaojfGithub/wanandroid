package com.zhao.wanandroid.bean

import androidx.room.*
import java.util.*

/**
 *创建时间： 2022/2/25
 *编   写：  zjf
 *页面功能:
 */
@Entity(tableName = "todo")
data class TodoBean(
    @PrimaryKey
    val title:String,
    val content:String,
    val priority:String,
    val date: Date
)

@Dao
interface TodoDao{
    @Query("select * from todo")
    suspend fun getTodoList():List<TodoBean>

    @Insert
    suspend fun insertAll(vararg bean:TodoBean)

    @Delete
    suspend fun delete(bean: TodoBean)

    @Update
    suspend fun updateData(vararg bean: TodoBean)

}

