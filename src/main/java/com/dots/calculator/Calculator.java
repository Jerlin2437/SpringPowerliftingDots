package com.dots.calculator;

import jakarta.persistence.*;

import java.util.Objects;

@Entity
public class Calculator {
    @Id
    @SequenceGenerator(
            name = "Person_ID", allocationSize = 1, sequenceName = "Person_ID")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "PERSON_ID")
    
    private Integer id;
    private String name;
    private Double weight;
    private Double total;
    private Double dots;

    public Calculator(String name, Double weight, Double total, Double dots) {
        this.name = name;
        this.weight = weight;
        this.total = total;
        this.dots = dots;
    }

    public Calculator() {

    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getWeight() {
        return weight;
    }

    public void setWeight(Double weight) {
        this.weight = weight;
    }

    public Double getTotal() {
        return total;
    }

    public void setTotal(Double total) {
        this.total = total;
    }

    public Double getDots() {
        return dots;
    }

    public void setDots(Double dots) {
        this.dots = dots;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Calculator that = (Calculator) o;
        return Objects.equals(id, that.id) && Objects.equals(name, that.name) && Objects.equals(weight, that.weight) && Objects.equals(total, that.total) && Objects.equals(dots, that.dots);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, weight, total, dots);
    }

    @Override
    public String toString() {
        return "Calculator{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", weight=" + weight +
                ", total=" + total +
                ", dots=" + dots +
                '}';
    }
}
