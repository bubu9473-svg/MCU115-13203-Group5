package com.example.hw4;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

public class ResultFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(
                R.layout.fragment_result, container, false);

        TextView result = view.findViewById(R.id.txtResult);

        WorkViewModel model =
                new ViewModelProvider(requireActivity())
                        .get(WorkViewModel.class);

        model.phase.observe(getViewLifecycleOwner(), phase -> {
            if ("idle".equals(phase) || "preparing".equals(phase)) {
                result.setText("準備中");
            } else if ("working".equals(phase)) {
                result.setText("工作中");
            } else if ("finished".equals(phase)) {
                result.setText("背景工作結束");
            } else if ("cancelled".equals(phase)) {
                result.setText("背景工作已中斷");
            }
        });

        return view;
    }
}
