package com.example.PowerToolStore.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "categories")
public class Category {
    @Setter(AccessLevel.NONE)
    @NotNull
    @Id
    @Min(1)
    private Long categoryId;

    @NotNull
    @Column(nullable = false, unique = true)
    private String category;

    private String description;

    protected Category(){}
}
