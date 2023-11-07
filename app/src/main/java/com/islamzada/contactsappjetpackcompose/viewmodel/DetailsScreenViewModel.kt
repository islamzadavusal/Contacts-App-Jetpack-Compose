package com.islamzada.contactsappjetpackcompose.viewmodel

import androidx.lifecycle.ViewModel
import com.islamzada.contactsappjetpackcompose.repo.ContactsDaoRepository

class DetailsScreenViewModel: ViewModel() {
    var prepo = ContactsDaoRepository()

    fun update (person_id:Int,person_name:String,person_number:String){
        prepo.updatePerson(person_id,person_name,person_number)
    }
}