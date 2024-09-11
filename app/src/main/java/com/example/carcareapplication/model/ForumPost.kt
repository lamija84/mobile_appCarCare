package com.example.carcareapplication.model

import java.util.Date

data class ForumPost(
    val title: String,
    val description: String,
    val user: String,
    val createdAt: String // valjalo bi da je Date
)
