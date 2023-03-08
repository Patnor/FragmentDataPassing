package com.example.fragmentdatapassing;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.fragmentdatapassing.databinding.FragmentRegistrationBinding;


public class RegistrationFragment extends Fragment {

    FragmentRegistrationBinding binding;

    private String selectedDepartment = null;

    public void setSelectedDepartment(String department){
        this.selectedDepartment = department;
    }

    public RegistrationFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        binding = FragmentRegistrationBinding.inflate(inflater, container, false);
        // Inflate the layout for this fragment
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        getActivity().setTitle("Registration");

        binding.buttonSelectDept.setOnClickListener(v -> mRegistrationFragmentListener.gotoDepartment());

        binding.buttonSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = binding.editTextName.getText().toString();
                String email = binding.editTextEmail.getText().toString();
                String id = binding.editTextID.getText().toString();

                if(name.isEmpty()){
                    Toast.makeText(getActivity(), "Enter Valid name!", Toast.LENGTH_SHORT).show();
                }else if (email.isEmpty()){
                    Toast.makeText(getActivity(), "Enter Valid email!", Toast.LENGTH_SHORT).show();
                }else if (id.isEmpty()){
                    Toast.makeText(getActivity(), "Enter Valid ID!", Toast.LENGTH_SHORT).show();
                }else if(selectedDepartment == null){
                    Toast.makeText(getActivity(), "Select a department!", Toast.LENGTH_SHORT).show();
                }else{

                    Profile profile = new Profile(name, email, id, selectedDepartment);
                    mRegistrationFragmentListener.gotoProfile(profile);
                }

            }
        });

        if(selectedDepartment == null)
            binding.textViewSelectedDept.setText("");
        else
            binding.textViewSelectedDept.setText(selectedDepartment);
    }

    RegistrationFragmentListener mRegistrationFragmentListener;

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        mRegistrationFragmentListener = (RegistrationFragmentListener) context;
    }

    interface RegistrationFragmentListener{
        void gotoDepartment();

        void gotoProfile(Profile profile);
    }
}