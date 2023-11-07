package com.islamzada.contactsappjetpackcompose.entity

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class ContactsMessage (@SerializedName("kisiler")
                       @Expose
                       var contacts:List<Contacts>,
                       @SerializedName("success")
                       @Expose
                       var success:Int) {
}