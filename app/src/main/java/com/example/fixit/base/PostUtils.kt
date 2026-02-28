package com.example.fixit.base

import android.content.Context
import androidx.core.graphics.toColorInt

object PostUtils {

    fun getCachedLong(key: String): Long =
        MyApplication.appContext
            ?.getSharedPreferences("TAG", Context.MODE_PRIVATE)
            ?.getLong(key, 0) ?: 0

    fun setCachedLong(key: String, value: Long) {
        MyApplication.appContext
            ?.getSharedPreferences("TAG", Context.MODE_PRIVATE)
            ?.edit()
            ?.putLong(key, value)
            ?.apply()
    }

    val STATUS_LABELS = listOf(
        "Waiting for Response",
        "In Progress",
        "Resolved",
        "Closed"
    )

    val STATUS_VALUES = listOf(
        "WAITING",
        "IN_PROGRESS",
        "RESOLVED",
        "CLOSED"
    )

    fun getStatusValue(selectedLabel: String?): String {
        val index = STATUS_LABELS.indexOf(selectedLabel ?: STATUS_LABELS[0])
        return if (index >= 0) STATUS_VALUES[index] else "WAITING"
    }

    fun getLabelForValue(statusValue: String?): String {
        val index = STATUS_VALUES.indexOf(statusValue)
        return if (index >= 0) STATUS_LABELS[index] else STATUS_LABELS[0]
    }

    fun getUserTypeDisplay(userType: String?): String = when (userType) {
        "TECHNICIAN" -> "Technician"
        else -> "Customer"
    }

    fun getStatusBadge(status: String?): Pair<String, Int> = when (status) {
        "IN_PROGRESS" -> "In Progress" to "#2196F3".toColorInt()
        "RESOLVED" -> "Resolved" to "#4CAF50".toColorInt()
        "CLOSED" -> "Closed" to "#9E9E9E".toColorInt()
        else -> "Waiting" to "#FF9800".toColorInt()
    }
}