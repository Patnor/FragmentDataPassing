package com.example.fragmentdatapassing;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.fragmentdatapassing.databinding.FragmentDepartmentBinding;

public class DepartmentFragment extends Fragment {


    FragmentDepartmentBinding binding;
    public DepartmentFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        binding = FragmentDepartmentBinding.inflate(inflater, container, false);

        // Inflate the layout for this fragment
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        getActivity().setTitle("Department");

        binding.buttonCancel.setOnClickListener( v -> mDepartmentFragmentListener.cancelDepartment());

        binding.buttonSelect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int selectedId = binding.radioGroup.getCheckedRadioButtonId();
                String department = getString(R.string.cs_label);

                if(selectedId == R.id.radioButtonSIS)
                    department = getString(R.string.sis_label);
                else if(selectedId == R.id.radioButtonBIO)
                    department = getString(R.string.bio_label);
                else if(selectedId == R.id.radioButtonDS)
                    department = getString(R.string.ds_label);

                mDepartmentFragmentListener.sendDepartment(department);

            }
        });
    }

    DepartmentFragmentListener mDepartmentFragmentListener;

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        mDepartmentFragmentListener = (DepartmentFragmentListener) context;
    }

    interface DepartmentFragmentListener{
        void sendDepartment(String department);
        void cancelDepartment();
    }
}