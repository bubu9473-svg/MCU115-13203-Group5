
package com.example.hw4;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

public class StartFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(
                R.layout.fragment_start, container, false);

        WorkViewModel model =
                new ViewModelProvider(requireActivity())
                        .get(WorkViewModel.class);

        Button startButton = view.findViewById(R.id.btnStart);

        startButton.setOnClickListener(v -> model.startWork());

        return view;
    }
}
