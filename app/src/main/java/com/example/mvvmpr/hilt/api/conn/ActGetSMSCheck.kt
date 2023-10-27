package com.example.mvvmpr.hilt.api.conn

import com.google.gson.annotations.SerializedName

data class ActGetSMSCheck(
    @SerializedName("code") val code: String = "",
    @SerializedName("message") val message: String = ""
)
