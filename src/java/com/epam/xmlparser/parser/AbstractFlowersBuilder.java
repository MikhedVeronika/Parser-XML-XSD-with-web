package com.epam.xmlparser.parser;

import com.epam.xmlparser.entity.Flower;

import java.util.HashSet;
import java.util.Set;

public abstract class AbstractFlowersBuilder {

    protected Set<Flower> flowers;

    public AbstractFlowersBuilder() {
        flowers = new HashSet<Flower>();
    }

    public AbstractFlowersBuilder(Set<Flower> flowers) {
        this.flowers = flowers;
    }

    public Set<Flower> getFlowers() {
        return flowers;
    }

    public abstract void buildSetFlowers(String fileName);
}
