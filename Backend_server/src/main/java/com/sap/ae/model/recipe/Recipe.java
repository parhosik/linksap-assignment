package com.sap.ae.model.recipe;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Transient;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Entity
@Data
@Getter
@Setter
public class Recipe {

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "recipeTitle")
    private String recipeTitle;

    @Column(name = "isVegeterian" ) 
    private String isVegeterian;
    
    @CreationTimestamp
    @Column(name="created_date", updatable = false)
    private Date createdDate;
    
    @UpdateTimestamp
    @Column(name="updated_date")
    private Date updatedDate;
    
    @Column( name = "suitableForPeople" )
    private int suitableForPeople;
    
    @Column(name="ingredients")
    private String ingredients;
    
    @Column(name="cookingInstructions")
    private String cookingInstructions;
    
    @Transient
    private List<String> ingredientsList;

}
