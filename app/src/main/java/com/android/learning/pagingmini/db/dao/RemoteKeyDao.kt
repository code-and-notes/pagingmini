package com.android.learning.pagingmini.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.android.learning.pagingmini.db.entities.RemoteKeyDTO

@Dao
interface RemoteKeyDao {

    @Insert
    suspend fun insertALL(remoteKeys: List<RemoteKeyDTO>)

    @Query("SELECT * FROM remote_keys WHERE productId = :productId")
    suspend fun remoteKeyByProductId(productId: Int): RemoteKeyDTO?

    @Query("DELETE FROM remote_keys")
    suspend fun clearRemoteKeys()
}