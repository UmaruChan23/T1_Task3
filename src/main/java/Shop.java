import entity.Buyer;
import entity.Warehouse;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Phaser;

public class Shop {
    public static void main(String[] args) {
        int buyerCount;

        if (args.length > 0) {
            buyerCount = Integer.parseInt(args[0]);
        } else {
            System.out.println("Введите количество покупателей");
            return;
        }

        Phaser barrier = new Phaser(buyerCount);

        ExecutorService threads = Executors.newFixedThreadPool(buyerCount);
        for (int i = 1; i <= buyerCount; i++) {
            threads.submit(new Buyer(i, Warehouse.getInstance(), barrier));
        }
        threads.shutdown();

    }
}
