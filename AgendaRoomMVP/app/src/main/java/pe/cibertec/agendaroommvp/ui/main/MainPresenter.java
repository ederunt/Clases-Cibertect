package pe.cibertec.agendaroommvp.ui.main;

import android.content.Context;
import android.os.AsyncTask;
import android.support.v7.widget.LinearLayoutManager;

import java.util.List;

import pe.cibertec.agendaroommvp.data.db.AppDatabase;
import pe.cibertec.agendaroommvp.data.db.model.Contact;

public class MainPresenter implements MainContrac.MainPresenter {

    MainContrac.MainView mainView;

    //Inyeccion de dependencia de construtor
    public MainPresenter(MainContrac.MainView mainView) {
        this.mainView = mainView;
    }

    @Override
    public void addContac(Contact contact) {

        new TaskAddContac().execute(contact);
    }

    @Override
    public void getAllContacts() {

        new TaskGetContacts().execute();

    }

    private class TaskAddContac extends AsyncTask<Contact,Void,Void> {
        @Override
        protected Void doInBackground(Contact... contacts) {
            AppDatabase.nuevaInstancia((Context) mainView).getContactDao().insertContacts(contacts);

            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            //adapter.notifyDataSetChanged();
        }
    }

    private class TaskGetContacts extends AsyncTask<Void, Void, List<Contact>>{
        @Override
        protected List<Contact> doInBackground(Void... voids) {
            return AppDatabase.nuevaInstancia((Context) mainView).getContactDao().getAll();
        }

        @Override
        protected void onPostExecute(List<Contact> contacts) {
            super.onPostExecute(contacts);

            mainView.showContacts(contacts);
//            items = contacts;
//            adapter = new ContactAdapter(items);
//            rvContact.setAdapter(adapter);
//
//            rvContact.setLayoutManager(new LinearLayoutManager(MainActivity.this));
        }
    }
}
