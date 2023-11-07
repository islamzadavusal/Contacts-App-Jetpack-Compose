package com.islamzada.contactsappjetpackcompose.entity

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName


data class Contacts(@SerializedName("kisi_id")
                    @Expose
                    var person_id:Int,

                    @SerializedName("kisi_ad")
                    @Expose
                    var person_name:String,

                    @SerializedName("kisi_tel")
                    @Expose
                    var person_number:String) {
}