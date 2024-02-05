package br.com.ecommerce.domain;

import jakarta.validation.constraints.NotNull;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

@Entity
public class Product implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotNull
    @Column(name = "name" , nullable = false)
    private String name;

    @NotNull
    @Column(name = "value" , nullable = false)
    private Double value;

    @NotNull
    @Column(name = "description")
    private String description;

    @NotNull
    @Column(name = "date_register_cadastre")
    private Date date_register_cadastre;

    public Product(){

    }


    public Product(String name, Double value, String description, Date date_register_cadastre) {
        this.name = name;
        this.value = value;
        this.description = description;
        this.date_register_cadastre = date_register_cadastre;
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

    public Double getValue() {
        return value;
    }

    public void setValue(Double value) {
        this.value = value;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", value=" + value +
                ", description='" + description + '\'' +
                ", date_register_cadastre=" + date_register_cadastre +
                '}';
    }
}
