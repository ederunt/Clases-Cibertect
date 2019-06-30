package pe.cibertec.agendaroommvp.ui.main;

import java.util.List;

import pe.cibertec.agendaroommvp.data.db.model.Contact;

public interface MainContrac {

    interface MainView{
        void showContacts(List<Contact> contacts);
    }

    interface MainPresenter{

        void addContac(Contact contact);
        void getAllContacts();
    }
}
