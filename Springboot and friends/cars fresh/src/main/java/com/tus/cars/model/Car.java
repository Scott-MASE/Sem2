package com.tus.cars.model;
import jakarta.persistence.*;

@Entity
@Table(name = "cars")
public class Car {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    private String make;
    private String model;
    @Column(name = "manufactureryear")
    private int manufacturerYear;
    private String color;
    private double litre;
    private int mileage;
    private int price;
    private String image;
    
    @Column(name = "cond")
    private String condition;
    
    private String seller;
    
    // Constructors
    public Car() {}

    public Car(Long id, String make, String model, int manufacturerYear, String color, double litre, int mileage, int price, String image, String condition, String seller) {
        this.id = id;
        this.make = make;
        this.model = model;
        this.manufacturerYear = manufacturerYear;
        this.color = color;
        this.litre = litre;
        this.mileage = mileage;
        this.price = price;
        this.image = image;
        this.condition = condition;
        this.seller = seller;
    }

    // Getters and Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getMake() { return make; }
    public void setMake(String make) { this.make = make; }

    public String getModel() { return model; }
    public void setModel(String model) { this.model = model; }

    public int getManufacturerYear() { return manufacturerYear; }
    public void setManufacturerYear(int manufacturerYear) { this.manufacturerYear = manufacturerYear; }

    public String getColor() { return color; }
    public void setColor(String color) { this.color = color; }

    public double getLitre() { return litre; }
    public void setLitre(double litre) { this.litre = litre; }

    public int getMileage() { return mileage; }
    public void setMileage(int mileage) { this.mileage = mileage; }

    public int getPrice() { return price; }
    public void setPrice(int price) { this.price = price; }

    public String getImage() { return image; }
    public void setImage(String image) { this.image = image; }

    public String getCondition() { return condition; }
    public void setCondition(String condition) { this.condition = condition; }

    public String getSeller() { return seller; }
    public void setSeller(String seller) { this.seller = seller; }
}
