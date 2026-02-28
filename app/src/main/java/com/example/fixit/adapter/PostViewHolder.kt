package com.example.fixit.adapter

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.example.fixit.databinding.PostRowLayoutBinding
import com.example.fixit.model.post.Post
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.fixit.R
import com.example.fixit.base.toRelativeTimeString
import com.example.fixit.base.PostUtils

class PostViewHolder(
    private val binding: PostRowLayoutBinding,
    private val onPostClick: (Post) -> Unit
) : RecyclerView.ViewHolder(binding.root) {

    private var currentPost: Post? = null

    init {
        itemView.setOnClickListener {
            currentPost?.let { post ->
                onPostClick(post)
            }
        }
    }

    fun bind(post: Post) {
        this.currentPost = post

        // Handle profile image - always visible, fallback to placeholder
        binding.postUserAvatar.visibility = View.VISIBLE
        Glide.with(binding.root.context)
            .load(post.profileImageUrl)
            .apply(RequestOptions.circleCropTransform())
            .placeholder(R.drawable.profile)
            .error(R.drawable.profile)
            .into(binding.postUserAvatar)


        // Set user name
        binding.postUserName.text = post.userName

        // Set user type badge
        val userTypeDisplay = when (post.userType) {
            "TECHNICIAN" -> "Technician"
            "CUSTOMER" -> "Customer"
            else -> "Customer"
        }
        binding.postUserTypeBadge.text = userTypeDisplay

        // Set timestamp (format as relative time)
        binding.postTimestamp.text = post.timestamp.toRelativeTimeString()

        // Set content
        binding.postContent.text = post.content

        // Set comment count
        val commentText = when (post.commentCount) {
            0 -> "No comments"
            1 -> "1 comment"
            else -> "${post.commentCount} comments"
        }
        binding.postCommentCount.text = commentText

        // Handle image
        if (post.imageUrl.isNullOrEmpty()) {
            binding.postImage.visibility = View.GONE
        } else {
            binding.postImage.visibility = View.VISIBLE
            Glide.with(binding.root.context)
                .load(post.imageUrl)
                .centerCrop()
                .into(binding.postImage)
        }

        // Set status badge
        val (statusLabel, statusColor) = PostUtils.getStatusBadge(post.status)
        binding.postStatusBadge.text = statusLabel
        binding.postStatusBadge.chipBackgroundColor =
            android.content.res.ColorStateList.valueOf(statusColor)
    }
}