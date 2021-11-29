package com.example.ex_rv.data_generator

import com.example.ex_rv.model.Contact

fun generateContact(size: Int) : List<Contact>{

    val list = mutableListOf<Contact>()
    for (i in 0..size){
        list.add(Contact(name = "Name $i", number = i.toString()))
    }
    return list
}