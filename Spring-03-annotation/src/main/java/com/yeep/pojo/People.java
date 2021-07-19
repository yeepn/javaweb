package com.yeep.pojo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class People {
    @Autowired(required = false)
    private Cat cat;
    @Autowired
    private Dog dog;
    public People() {
    }
    public People(Cat cat, Dog dog) {
        this.cat = cat;
        this.dog = dog;
    }
    public Cat getCat() {
        return cat;
    }

    public void setCat( Cat cat) {
        this.cat = cat;
    }
    public Dog getDog() {
        return dog;
    }
    public void setDog(Dog dog) {
        this.dog = dog;
    }
}
