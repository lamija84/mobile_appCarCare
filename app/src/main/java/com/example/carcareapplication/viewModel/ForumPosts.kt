package com.example.carcareapplication.viewModel

import com.example.carcareapplication.model.models.ForumPostComment
import com.example.carcareapplication.model.models.ForumPosts

data class ForumPostDetails(
    val id: Int = 0,
    val userId: Int = 1,
    val title: String = "",
    val description: String = "",
    val createdAt: String = ""

    )

data class ForumPostUiState(
    val forumPostDetails: ForumPostDetails = ForumPostDetails(),
    val isEntryValid: Boolean = false
)

fun ForumPostDetails.toForumPost(): ForumPosts = ForumPosts(
    id = id,
    userId = userId,
    title = title,
    description = description,
    createdAt = createdAt
)


fun ForumPosts.toForumPostDetails() = ForumPostDetails(
    id = id,
    userId = userId ?: 1,
    title = title ?: "Why no work?",
    description = description ?: "Why no work description",
    createdAt = createdAt ?: "01.01.2001 00:01",
)


fun ForumPosts.toForumPostUiState(isEntryValid: Boolean = false): ForumPostUiState = ForumPostUiState(
    forumPostDetails = this.toForumPostDetails(),
    isEntryValid = isEntryValid
)

data class ForumPostCommentDetails(
    val id: Int = 0,
    val forumPostId: Int = 1,
    val userId: Int = 1,
    val title: String = "",
    val description: String = "",
)

data class ForumPostCommentUiState(
    val forumPostCommentDetails: ForumPostCommentDetails = ForumPostCommentDetails(),
    val isEntryValid: Boolean = false
)

fun ForumPostCommentDetails.toForumPostComment(): ForumPostComment = ForumPostComment(
    id = id,
    forumPostId = forumPostId,
    userId = userId,
    title = title,
    description = description,
)


fun ForumPostComment.toForumPostCommentDetails() = ForumPostCommentDetails(
    id = id,
    forumPostId = forumPostId ?: 1,
    userId = userId ?: 1,
    title = title ?: "Comment title",
    description = description ?: "Comment description declared in the viewmodel",
)


fun ForumPostComment.toForumPostCommentUiState(isEntryValid: Boolean = false): ForumPostCommentUiState = ForumPostCommentUiState(
    forumPostCommentDetails = this.toForumPostCommentDetails(),
    isEntryValid = isEntryValid
)