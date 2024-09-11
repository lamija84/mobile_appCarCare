package com.example.carcareapplication.model.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import org.jetbrains.annotations.NotNull

@Entity(tableName = "forumPostComment",
    foreignKeys = [ForeignKey(
        entity = User::class,
        parentColumns = ["id"],
        childColumns = ["userId"],
        onDelete = ForeignKey.CASCADE // Optional: specify what happens on delete
    ),
        ForeignKey(
            entity = ForumPosts::class,
            parentColumns = ["id"],
            childColumns = ["forumPostId"],
            onDelete = ForeignKey.CASCADE // Optional: specify what happens on delete
        )]
)
data class ForumPostComment(
    @PrimaryKey(autoGenerate = true)
    @NotNull
    @ColumnInfo(name = "id")
    val id: Int = 0,

    @ColumnInfo(name = "forumPostId")
    val forumPostId: Int? = 1,

    @ColumnInfo(name = "userId")
    val userId: Int? = 1,

    @ColumnInfo(name = "title")
    val title: String? = null,

    @ColumnInfo(name = "description")
    val description: String? = null,

)
