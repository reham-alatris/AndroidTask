package com.example.androidtask.viewModel;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.example.androidtask.db.AppDataBase;
import com.example.androidtask.model.User;

import java.util.List;

public class UsersViewModel extends AndroidViewModel {
    private LiveData<List<User>> usersList;

    private AppDataBase appDatabase;

    public UsersViewModel(Application application) {
        super(application);

        appDatabase = AppDataBase.getAppDatabase(this.getApplication());
        usersList = appDatabase.usersDao().getUsers();
    }

    public LiveData<List<User>> getUsersList() {
        return usersList;
    }

    public void insertItem(User user) {
        appDatabase.usersDao().insertDetails(user);

    }

}


