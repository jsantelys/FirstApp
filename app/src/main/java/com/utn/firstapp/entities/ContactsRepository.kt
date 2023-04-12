package com.utn.firstapp.entities

class ContactsRepository {
    var contacts = mutableListOf<Contact>()

    init {
        contacts.add(Contact("Pedro","Gonzales","1199999999","03/05/2022",305,2562))
        contacts.add(Contact("Enrique","Figueroa","112345678","03/05/2022",305,2562))
        contacts.add(Contact("Lucas","Sanchez","118765432","03/05/2022",305,2562))
        contacts.add(Contact("Francisco","Miranda","1122222222","03/05/2022",305,2562))
        contacts.add(Contact("Lionel","Messi","1133333333","03/05/2022",305,2562))
    }
}