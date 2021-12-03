package entity;

import java.util.concurrent.Phaser;

public class Buyer implements Runnable {
    private int id;
    private int goodsCount;
    private int numberOfPurchases;
    private Warehouse warehouse;
    private Phaser barrier;

    public Buyer(int id, Warehouse warehouse, Phaser barrier) {
        this.id = id;
        this.warehouse = warehouse;
        this.barrier = barrier;
    }

    @Override
    public void run() {
        barrier.arriveAndAwaitAdvance();
        while (true) {
            int count = (int) (Math.random() * 10 + 1);

            if (!warehouse.isEmpty()) {
                ++numberOfPurchases;
                goodsCount += warehouse.sell(count);
                barrier.arriveAndAwaitAdvance();
            } else {
                System.out.println(this);
                barrier.arriveAndDeregister();
                break;
            }
        }
    }

    @Override
    public String toString() {
        return "Покупатель " + id + "\n" +
                "Количество купленного товара: " + goodsCount + "\n" +
                "Количество покупок: " + numberOfPurchases + ".\n";
    }

}
