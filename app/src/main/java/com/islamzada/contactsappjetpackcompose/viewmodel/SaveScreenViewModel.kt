package com.islamzada.contactsappjetpackcompose.viewmodel

import androidx.lifecycle.ViewModel
import com.islamzada.contactsappjetpackcompose.repo.ContactsDaoRepository

class SaveScreenViewModel : ViewModel() {
    var prepo = ContactsDaoRepository()

    fun save (person_name:String,person_number:String){
        prepo.savePerson(person_name,person_number)
    }
}