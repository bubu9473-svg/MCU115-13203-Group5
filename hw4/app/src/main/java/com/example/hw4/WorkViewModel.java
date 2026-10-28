
package com.example.hw4;

import android.os.Handler;
import android.os.Looper;
import android.os.SystemClock;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class WorkViewModel extends ViewModel {

    public final MutableLiveData<String> phase =
            new MutableLiveData<>("idle");

    public final MutableLiveData<Integer> progress =
            new MutableLiveData<>(0);

    private Thread worker;
    private volatile boolean cancelled = false;

    private final Handler mainHandler =
            new Handler(Looper.getMainLooper());

    public synchronized void startWork() {

        // 避免重複啟動背景工作
        if (worker != null && worker.isAlive()) {
            return;
        }

        cancelled = false;
        progress.setValue(0);
        phase.setValue("preparing");

        worker = new Thread(() -> {
            try {
                // 第一階段：準備中，約 2 秒
                for (int i = 0; i < 10; i++) {
                    if (Thread.currentThread().isInterrupted()
                            || cancelled) {
                        return;
                    }

                    SystemClock.sleep(200);
                }

                if (cancelled) {
                    return;
                }

                mainHandler.post(() ->
                        phase.setValue("working"));

                // 第二階段：背景工作，更新 1% 到 100%
                for (int i = 1; i <= 100; i++) {

                    if (Thread.currentThread().isInterrupted()
                            || cancelled) {
                        return;
                    }

                    final int value = i;

                    mainHandler.post(() -> {
                        if (!cancelled) {
                            progress.setValue(value);
                        }
                    });

                    SystemClock.sleep(80);
                }

                if (!cancelled) {
                    mainHandler.post(() -> {
                        progress.setValue(100);
                        phase.setValue("finished");
                    });
                }

            } finally {
                synchronized (WorkViewModel.this) {
                    worker = null;
                }
            }
        });

        worker.start();
    }

    public synchronized void cancelWork() {
        cancelled = true;

        if (worker != null) {
            worker.interrupt();
            worker = null;
        }

        phase.setValue("cancelled");
    }

    @Override
    protected void onCleared() {
        cancelWork();
        super.onCleared();
    }
}
