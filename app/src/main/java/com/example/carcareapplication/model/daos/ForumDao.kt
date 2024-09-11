package com.example.carcareapplication.model.daos

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.example.carcareapplication.model.models.Cars
import com.example.carcareapplication.model.models.ForumPostComment
import com.example.carcareapplication.model.models.ForumPosts
import com.example.carcareapplication.model.models.User
import kotlinx.coroutines.flow.Flow

@Dao
interface ForumDao {
    @Insert
    suspend fun insert(forumPost: ForumPosts)
    @Update
    suspend fun update(forumPost: ForumPosts)

    @Delete
    suspend fun delete(forumPost: ForumPosts)

    @Query("SELECT * FROM forumPosts")
    suspend fun getAllForumPosts(): List<ForumPosts>

    @Query("SELECT * FROM forumPosts WHERE id = :id")
    fun getForumPostById(id: Int): Flow<ForumPosts>

    @Query("SELECT * FROM forumPosts WHERE userId = :id")
    fun getForumPostsByUserId(id: Int): Flow<List<ForumPosts>>

    @Query("SELECT * FROM forumPostComment WHERE forumPostId = :id")
    suspend fun getForumPostComments(id: Int): List<ForumPostComment>

    @Insert
    suspend fun insertForumPostComment(forumPostComment: ForumPostComment)
}