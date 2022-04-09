package com.example.dogapp.model;

import androidx.room.Embedded;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.google.gson.annotations.SerializedName;
import java.io.Serializable;

@Entity
public class DogBreed implements Serializable {
    @SerializedName("id")
    @PrimaryKey
    private int id;
    @SerializedName("name")
    private String name;
    @SerializedName("bred_for")
    private String bredFor;
    @SerializedName("breed_group")
    private String breedGroup;
    @SerializedName("life_span")
    private String lifeSpan;
    @SerializedName("origin")
    private String origin;
    @SerializedName("temperament")
    private String temperament;
    @SerializedName("height")
    @Embedded(prefix = "height_")
    private DogData height;
    @Embedded(prefix = "weight_")
    @SerializedName("weight")
    private DogData weight;
    @SerializedName("url")
    private String url;

    public DogBreed(int id, String name, String bredFor, String breedGroup, String lifeSpan, String origin, String temperament, DogData height, DogData weight, String url) {
        this.id = id;
        this.name = name;
        this.bredFor = bredFor;
        this.breedGroup = breedGroup;
        this.lifeSpan = lifeSpan;
        this.origin = origin;
        this.temperament = temperament;
        this.height = height;
        this.weight = weight;
        this.url = url;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBredFor() {
        return bredFor;
    }

    public void setBredFor(String bredFor) {
        this.bredFor = bredFor;
    }

    public String getBreedGroup() {
        return breedGroup;
    }

    public void setBreedGroup(String breedGroup) {
        this.breedGroup = breedGroup;
    }

    public String getLifeSpan() {
        return lifeSpan;
    }

    public void setLifeSpan(String lifeSpan) {
        this.lifeSpan = lifeSpan;
    }

    public String getOrigin() {
        return origin;
    }

    public void setOrigin(String origin) {
        this.origin = origin;
    }

    public String getTemperament() {
        return temperament;
    }

    public void setTemperament(String temperament) {
        this.temperament = temperament;
    }

    public DogData getHeight() {
        return height;
    }

    public void setHeight(DogData height) {
        this.height = height;
    }

    public DogData getWeight() {
        return weight;
    }

    public void setWeight(DogData weight) {
        this.weight = weight;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public static class DogData {
        @SerializedName("imperial")
        private String imperial;
        @SerializedName("metric")
        private String metric;

        public DogData(String imperial, String metric) {
            this.imperial = imperial;
            this.metric = metric;
        }

        public String getImperial() {
            return imperial;
        }

        public void setImperial(String imperial) {
            this.imperial = imperial;
        }

        public String getMetric() {
            return metric;
        }

        public void setMetric(String metric) {
            this.metric = metric;
        }
    }
}
