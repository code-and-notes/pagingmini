package com.android.learning.pagingmini.ui.sealed

sealed class FeedType(val name: String) {
    object OfflineFirst: FeedType("offline first")
    object RemoteOnly : FeedType("remote only")
}
