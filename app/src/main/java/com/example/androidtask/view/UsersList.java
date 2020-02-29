package com.example.androidtask.view;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.androidtask.R;
import com.example.androidtask.databinding.FragmentUsersListBinding;
import com.example.androidtask.model.User;
import com.example.androidtask.viewModel.UsersViewModel;

import java.util.List;


public class UsersList extends Fragment {
    RecyclerView usersRecyclerView;
    UsersAdapter usersAdapter;
    UsersViewModel usersViewModel;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final FragmentUsersListBinding binding = DataBindingUtil.inflate(
                inflater, R.layout.fragment_users_list, container, false);
        View view = binding.getRoot();
        ((AppCompatActivity) getActivity()).getSupportActionBar().setTitle(getString(R.string.user_list));

        usersRecyclerView = binding.usersList;
        usersRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        binding.addUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Navigation.findNavController(getView()).navigate(R.id.submit_user);

            }
        });
        usersViewModel = new ViewModelProvider(this).get(UsersViewModel.class);
        usersViewModel.getUsersList().observe(getActivity(), new Observer<List<User>>() {
            @Override
            public void onChanged(List<User> usersModels) {
                Log.v("users", String.valueOf(usersModels.size()));
                if (usersModels.isEmpty()) {
                    binding.noUsersTxt.setVisibility(View.VISIBLE);
                } else
                    binding.noUsersTxt.setVisibility(View.GONE);

                usersAdapter = new UsersAdapter(usersModels, getContext());
                usersRecyclerView.setAdapter(usersAdapter);

            }
        });


        return view;
    }
}