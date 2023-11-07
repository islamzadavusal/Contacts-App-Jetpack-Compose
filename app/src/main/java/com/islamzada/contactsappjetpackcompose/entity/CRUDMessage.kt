package com.islamzada.contactsappjetpackcompose.entity

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class CRUDMessage (@SerializedName("success")
                 @Expose
                 var success:Int,
                        @SerializedName("message")
                 @Expose
                 var message:String) {
}