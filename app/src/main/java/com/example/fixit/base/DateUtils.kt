package com.example.fixit.base

import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

fun Long.toRelativeTimeString(): String {
    val diff = System.currentTimeMillis() - this
    return when {
        diff < 60_000       -> "Just now"
        diff < 3_600_000    -> "${diff / 60_000}m ago"
        diff < 86_400_000   -> "${diff / 3_600_000}h ago"
        diff < 604_800_000  -> "${diff / 86_400_000}d ago"
        else -> SimpleDateFormat("MMM dd, yyyy", Locale.getDefault()).format(Date(this))
    }
}