package com.vunh.login_hilt_clean.domain.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "tb_Account")
data class AccountEntity(
    @SerializedName("username")
    var username: String,
    @SerializedName("password")
    var password: String
){
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0
}

