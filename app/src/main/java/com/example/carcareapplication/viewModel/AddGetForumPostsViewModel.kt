package com.example.carcareapplication.viewModel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.carcareapplication.model.ForumPost
import com.example.carcareapplication.model.models.ForumPostComment
import com.example.carcareapplication.model.models.ForumPosts
import com.example.carcareapplication.model.repositories.ForumPostRepository
import kotlinx.coroutines.launch

class AddGetForumPostsViewModel(private val forumPostRepository: ForumPostRepository): ViewModel() {
    var forumPostUiState by mutableStateOf(ForumPostUiState())
        private set

    var forumPostList = mutableStateListOf<ForumPosts>()
        private set

    var forumPostCommentUiState by mutableStateOf(ForumPostCommentUiState())

    var forumPostCommentList = mutableStateListOf<ForumPostComment>()
        private set

    init {
        fetchForumPosts()
    }

    private fun fetchForumPosts() {
        viewModelScope.launch {
            val posts = forumPostRepository.getAllForumPosts()
            forumPostList.addAll(posts)

            val comments = forumPostRepository.getForumPostComments(1)
            forumPostCommentList.addAll(comments)
        }
    }

    suspend fun addForumPost(): Boolean {
        forumPostRepository.insert(forumPostUiState.forumPostDetails.toForumPost())
        forumPostList.add(forumPostUiState.forumPostDetails.toForumPost())
        return true
    }

    suspend fun addForumPostComment(): Boolean {
        forumPostRepository.insertForumPostComment(forumPostCommentUiState.forumPostCommentDetails.toForumPostComment())
        forumPostCommentList.add(forumPostCommentUiState.forumPostCommentDetails.toForumPostComment())
        return true
    }

    fun updateUiState(forumPostDetails: ForumPostDetails) {
        forumPostUiState = ForumPostUiState(forumPostDetails = forumPostDetails, isEntryValid = false)
    }

    fun updateUiState(forumPostCommentDetails: ForumPostCommentDetails) {
        forumPostCommentUiState = ForumPostCommentUiState(forumPostCommentDetails = forumPostCommentDetails, isEntryValid = false)
    }
}
