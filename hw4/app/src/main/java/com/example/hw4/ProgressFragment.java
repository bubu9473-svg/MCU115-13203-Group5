package com.example.hw4;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

public class ProgressFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(
                R.layout.fragment_progress, container, false);

        WorkViewModel model =
                new ViewModelProvider(requireActivity())
                        .get(WorkViewModel.class);

        TextView status = view.findViewById(R.id.txtProgressStatus);
        TextView percent = view.findViewById(R.id.txtProgressPercent);
        ProgressBar bar = view.findViewById(R.id.progressBar);
        Button cancel = view.findViewById(R.id.btnCancel);

        model.phase.observe(getViewLifecycleOwner(), phase -> {
            if ("idle".equals(phase)) {
                status.setText("準備中");
            } else if ("preparing".equals(phase)) {
                status.setText("準備中");
            } else if ("working".equals(phase)) {
                status.setText("工作進度");
            } else if ("finished".equals(phase)) {
                status.setText("背景工作結束");
            } else if ("cancelled".equals(phase)) {
                status.setText("背景工作已中斷");
            }
        });

        model.progress.observe(getViewLifecycleOwner(), value -> {
            bar.setProgress(value);
            percent.setText(value + "%");
        });

        cancel.setOnClickListener(v -> model.cancelWork());

        return view;
    }
}
