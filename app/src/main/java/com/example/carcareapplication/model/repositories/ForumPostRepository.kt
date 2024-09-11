package com.example.carcareapplication.model.repositories

import com.example.carcareapplication.model.daos.ForumDao
import com.example.carcareapplication.model.models.ForumPostComment
import com.example.carcareapplication.model.models.ForumPosts
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext

class ForumPostRepository(private val forumDao: ForumDao): BaseRepository<ForumPosts> {
    override suspend fun insert(t: ForumPosts) = forumDao.insert(t)

    override suspend fun update(t: ForumPosts) = forumDao.update(t)

    override suspend fun delete(t: ForumPosts) = forumDao.delete(t)

    override fun getOneStream(id: Int): Flow<ForumPosts?> = forumDao.getForumPostById(id)

    fun getForumPostByUserId(id: Int): Flow<List<ForumPosts>> = forumDao.getForumPostsByUserId(id)

    suspend fun getAllForumPosts(): List<ForumPosts> = withContext(Dispatchers.IO) {
        forumDao.getAllForumPosts()
    }

    suspend fun getForumPostComments(id: Int): List<ForumPostComment> = withContext(Dispatchers.IO) {
        forumDao.getForumPostComments(id)
    }

    suspend fun insertForumPostComment(t: ForumPostComment) = forumDao.insertForumPostComment(t)
}