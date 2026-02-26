package com.example.fixit.model.post

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Post(
    @PrimaryKey
    var id: String,
    var userId: String,
    var userName: String,
    var content: String,
    var imageUrl: String? = null,
    var timestamp: Long,
    var commentCount: Int = 0,
    var userType: String = "CUSTOMER",
    val lastUpdated: Long?,
    var profileImageUrl: String? = null,
    var status: String = "WAITING"
)
