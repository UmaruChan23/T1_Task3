package entity;

public class Warehouse {
    private int goods;
    private static Warehouse warehouse;

    public static synchronized Warehouse getInstance() {
        if (warehouse == null) {
            warehouse = new Warehouse(1000);
            return warehouse;
        }
        return warehouse;
    }

    private Warehouse(int goods) {
        this.goods = goods;
    }

    public synchronized int sell(int count) {
        if (count <= goods) {
            goods -= count;
        } else {
            count = goods;
            goods = 0;
        }
        return count;
    }

    public boolean isEmpty() {
        return goods == 0;
    }
}
