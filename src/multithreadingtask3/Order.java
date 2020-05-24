package multithreadingtask3;


public class Order
{
    private String clientsName;
    private String dish;
    private int tableNum;
    private Status ordersStatus;


    public Order(String clientsName, String dish, int tableNum, Status ordersStatus) {
        this.clientsName = clientsName;
        this.dish = dish;
        this.tableNum = tableNum;
        this.ordersStatus = ordersStatus;
    }

    public Status getOrdersStatus() {
        return ordersStatus;
    }

    public void setOrdersStatus(Status ordersStatus) {
        this.ordersStatus = ordersStatus;
    }

    public String getClientsName() {
        return clientsName;
    }

    public void setClientsName(String clientsName) {
        this.clientsName = clientsName;
    }

    public String getDish() {
        return dish;
    }

    public void setDish(String dish) {
        this.dish = dish;
    }

    public int getTableNum() {
        return tableNum;
    }

    public void setTableNum(int tableNum) {
        this.tableNum = tableNum;
    }

    @Override
    public String toString() {
        return "Order{" +
                "clientsName='" + clientsName + '\'' +
                ", dish='" + dish + '\'' +
                ", tableNum=" + tableNum +
                ", ordersStatus='" + ordersStatus + '\'' +
                '}';
    }
}
