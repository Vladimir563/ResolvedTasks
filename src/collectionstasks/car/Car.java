package collectionstasks.car;

import java.util.Objects;

public class Car {
    private Color color;
    private String brand;
    private int price;

    public Car(Color color, String brand, int price) {
        this.color = color;
        this.brand = brand;
        this.price = price;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Car car = (Car) o;
        return getPrice() == car.getPrice() &&
                getColor() == car.getColor() &&
                Objects.equals(getBrand(), car.getBrand());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getColor(), getBrand(), getPrice());
    }

    @Override
    public String toString() {
        return "Car{" +
                "color=" + color +
                ", brand='" + brand + '\'' +
                ", price=" + price +
                '}';
    }
}
