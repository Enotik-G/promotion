package org.example.cement.model;

import java.time.LocalDateTime;
import java.util.Objects;

public class Order{
    private LocalDateTime date;
    private  String company;
    private  int weightKg;

    public Order(LocalDateTime date, String company, int weight) {
        this.date = date;
        this.company = company;
        this.weightKg = weight;
    }

    public LocalDateTime getDate(){
        return this.date;
    }

    public String getCompany() {
        return this.company;
    }

    public int getWeightKg() {
        return this.weightKg;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public void setWeightKg(int weightKg) {
        this.weightKg = weightKg;
    }

    @Override
    public boolean equals(Object o) {
        if(this == o) return true;
        if(o == null ||  getClass() != o.getClass()) return false;

        Order other =  (Order) o;
        return other.getDate().equals(this.date) &&
                other.getCompany().equals(this.company) &&
                other.getWeightKg() == this.weightKg;
    }

    @Override
    public int hashCode() {
        return Objects.hash(date, company, weightKg);
    }
}
