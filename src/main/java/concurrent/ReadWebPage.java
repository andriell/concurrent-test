package concurrent;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;

import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import java.util.ArrayList;
import java.util.List;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executors;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;
import java.util.concurrent.TimeoutException;
import java.util.concurrent.TimeUnit;

public class ReadWebPage {
    public static void main(final String[] args) {
        new ReadWebPage().start();
    }

    public void start() {
        ExecutorService executor = Executors.newSingleThreadExecutor();
        Callable<String> callable = new Callable<String>() {
            @Override
            public String call() throws Exception {
                int i;
                for (i = 0; i < 10; i++) {
                    System.out.println(i);
                    try {
                        Thread.sleep(200);
                    } catch (InterruptedException e) {}
                }
                return "r" + i;
            }
        };
        Future<String> future = executor.submit(callable);
        try {
            String lines = future.get(1, TimeUnit.SECONDS);
            System.out.println(lines);
        } catch (ExecutionException ee) {
            System.err.println("Callable through exception: " + ee.getMessage());
        } catch (TimeoutException eite) {
            System.err.println("URL not responding");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        executor.shutdown();
    }
}
