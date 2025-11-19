package com.BokKey.BokKey.api.store.domain;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Builder
@AllArgsConstructor
public class Store {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String area;

    private String address;

    private String phone;

    private String openHours;

    private boolean isLive;

    private boolean isDelivery;

    @ManyToMany
    @JoinTable(name = "Target")
    private List<Target> Target = new ArrayList<>();

    @ManyToMany
    @JoinTable(name = "store_meal_type")
    private List<MealType> mealTypes = new ArrayList<>();

    @ManyToMany
    @JoinTable(name = "category")
    private List<Category> categories = new ArrayList<>();
}
