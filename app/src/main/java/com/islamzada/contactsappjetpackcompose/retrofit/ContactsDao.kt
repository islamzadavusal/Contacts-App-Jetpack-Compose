package com.islamzada.contactsappjetpackcompose.retrofit

import com.islamzada.contactsappjetpackcompose.entity.CRUDMessage
import com.islamzada.contactsappjetpackcompose.entity.ContactsMessage
import retrofit2.Call
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.POST

interface ContactsDao {
    @GET("kisiler/tum_kisiler.php")
    fun allContacts(): Call<ContactsMessage>

    @POST("kisiler/tum_kisiler_arama.php")
    @FormUrlEncoded
    fun searchPerson(@Field("kisi_ad") person_name:String): Call<ContactsMessage>

    @POST("kisiler/delete_kisiler.php")
    @FormUrlEncoded
    fun deletePerson(@Field("kisi_id") person_id:Int): Call<CRUDMessage>

    @POST("kisiler/insert_kisiler.php")
    @FormUrlEncoded
    fun savePerson(@Field("kisi_ad") person_name:String, @Field("kisi_tel") person_number:String): Call<CRUDMessage>

    @POST("kisiler/update_kisiler.php")
    @FormUrlEncoded
    fun updatePerson(@Field("kisi_id") person_id:Int,
                     @Field("kisi_ad") person_name:String,
                     @Field("kisi_tel") person_number:String): Call<CRUDMessage>
}