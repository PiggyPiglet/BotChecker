package me.piggypiglet.botchecker.core.tasks;

import com.google.inject.Singleton;
import me.piggypiglet.botchecker.core.objects.GRunnable;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.function.Consumer;

// ------------------------------
// Copyright (c) PiggyPiglet 2018
// https://www.piggypiglet.me
// ------------------------------
@Singleton
public final class Task {
    private static final ExecutorService EXECUTOR = Executors.newFixedThreadPool(10);
    private static final ScheduledExecutorService SCHEDULER = Executors.newScheduledThreadPool(10);

    public static void async(final Consumer<GRunnable> task, String... threadName) {
        EXECUTOR.submit(new GRunnable() {
            @Override
            public void run() {
                if (threadName.length >= 1) {
                    Thread.currentThread().setName(threadName[0]);
                }

                task.accept(this);
            }
        });
    }
}
