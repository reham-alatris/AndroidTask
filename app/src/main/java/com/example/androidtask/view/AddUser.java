package com.example.androidtask.view;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.example.androidtask.R;
import com.example.androidtask.databinding.FragmentAddUserBinding;
import com.example.androidtask.databinding.FragmentUsersListBinding;
import com.example.androidtask.model.User;
import com.example.androidtask.viewModel.UsersViewModel;

public class AddUser extends Fragment {
    String name, age, jobTitle = "";
    String gender = "";
    UsersViewModel usersViewModel;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final FragmentAddUserBinding binding = DataBindingUtil.inflate(
                inflater, R.layout.fragment_add_user, container, false);
        View view = binding.getRoot();
        ((AppCompatActivity) getActivity()).getSupportActionBar().setTitle(getString(R.string.add_user));

        usersViewModel = new ViewModelProvider(this).get(UsersViewModel.class);

        binding.submitUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                name = binding.nameEdit.getText().toString().trim();
                age = binding.ageEdit.getText().toString().trim();
                jobTitle = binding.jobTitleEdit.getText().toString().trim();
                if (name.isEmpty() || age.isEmpty() || jobTitle.isEmpty() || gender.isEmpty()) {
                    Toast.makeText(getContext(), getString(R.string.fill_fields), Toast.LENGTH_LONG).show();
                } else {
                    User user = new User();
                    user.setAge(age);
                    user.setGender(gender);
                    user.setJobTitle(jobTitle);
                    user.setName(name);
                    usersViewModel.insertItem(user);
                    Navigation.findNavController(getView()).navigate(R.id.show_users);


                }

            }
        });
        binding.genderRadioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if (checkedId == R.id.female) {
                    gender = getString(R.string.female);
                } else {
                    gender = getString(R.string.male);
                }

            }
        });
        return view;
    }
}
