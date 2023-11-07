package com.islamzada.contactsappjetpackcompose.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.islamzada.contactsappjetpackcompose.entity.Contacts
import com.islamzada.contactsappjetpackcompose.repo.ContactsDaoRepository

class MainScreenViewModel: ViewModel() {
    var prepo = ContactsDaoRepository()
    var contactsList = MutableLiveData<List<Contacts>>()

    init {
        loadContacts()
        contactsList = prepo.getContacts()
    }

    fun loadContacts(){
        prepo.getAllContacts()
    }

    fun search(searchName:String){
        prepo.searchPerson(searchName)
    }

    fun delete(person_id:Int){
        prepo.deletePerson(person_id)
    }
}
