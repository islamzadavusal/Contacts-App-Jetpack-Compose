package com.islamzada.contactsappjetpackcompose.repo

import androidx.lifecycle.MutableLiveData
import com.islamzada.contactsappjetpackcompose.entity.CRUDMessage
import com.islamzada.contactsappjetpackcompose.entity.Contacts
import com.islamzada.contactsappjetpackcompose.entity.ContactsMessage
import com.islamzada.contactsappjetpackcompose.retrofit.ApiUtils
import com.islamzada.contactsappjetpackcompose.retrofit.ContactsDao
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ContactsDaoRepository {
    var contactsList = MutableLiveData<List<Contacts>>()
    var contactsDao: ContactsDao

   init {
       contactsDao = ApiUtils.getContactsDao()
       contactsList = MutableLiveData()
}

fun getContacts():MutableLiveData<List<Contacts>>{
    return contactsList
}

fun getAllContacts(){
    contactsDao.allContacts().enqueue(object : Callback<ContactsMessage> {
        override fun onResponse(call: Call<ContactsMessage>, response: Response<ContactsMessage>) {
            val list = response.body()?.contacts
            contactsList.value = list
        }
        override fun onFailure(call: Call<ContactsMessage>?, t: Throwable?) {}
    })
}

fun searchPerson(searchName:String){
    contactsDao.searchPerson(searchName).enqueue(object : Callback<ContactsMessage> {
        override fun onResponse(call: Call<ContactsMessage>, response: Response<ContactsMessage>) {
            val list = response.body()?.contacts
            contactsList.value = list
        }
        override fun onFailure(call: Call<ContactsMessage>?, t: Throwable?) {}
    })
}

fun savePerson(person_name:String,person_number:String){
    contactsDao.savePerson(person_name,person_number).enqueue(object : Callback<CRUDMessage> {
        override fun onResponse(call: Call<CRUDMessage>, response: Response<CRUDMessage>) {}
        override fun onFailure(call: Call<CRUDMessage>?, t: Throwable?) {}
    })
}

fun updatePerson(person_id:Int,person_name:String,person_number:String){
    contactsDao.updatePerson(person_id,person_name,person_number).enqueue(object :
        Callback<CRUDMessage> {
        override fun onResponse(call: Call<CRUDMessage>, response: Response<CRUDMessage>) {}
        override fun onFailure(call: Call<CRUDMessage>?, t: Throwable?) {}
    })
}

fun deletePerson(person_id:Int){
    contactsDao.deletePerson(person_id).enqueue(object : Callback<CRUDMessage> {
        override fun onResponse(call: Call<CRUDMessage>, response: Response<CRUDMessage>) {
            getAllContacts()
        }
        override fun onFailure(call: Call<CRUDMessage>?, t: Throwable?) {}
    })
}
}
