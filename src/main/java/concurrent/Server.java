package concurrent;

/**
 * Created by Rybalko on 24.06.2016.
 */

import java.io.IOException;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

class Server {
    static Executor pool = Executors.newFixedThreadPool(2);

    public static void main(String[] args) throws IOException
    {
        new Server().start();
    }

    public void start() {
        for (int i = 0; i < 10; i++) {
            pool.execute(new MyThread(i));
        }
    }

    class MyThread implements Runnable {
        private int i;

        public MyThread(int i) {
            this.i = i;
        }

        @Override
        public void run() {
            for (int i = 0; i < 10; i++) {
                System.out.println(this.i + " " + i);
                try {
                    Thread.sleep(200);
                } catch (InterruptedException e) {}
            }
        }
    }

}
