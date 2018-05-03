package com.ukar.design.factory.abstracts;

import com.ukar.design.factory.abstracts.color.Color;
import com.ukar.design.factory.normal.Shape;

/**
 * Created by jyou on 2018/4/8.
 */
public class AbstractsFactoryDemo {
    public static void main(String[] args) {
        FactoryProducer factoryProducer = new FactoryProducer();
        AbstractFactory shapeFactory = factoryProducer.getFactory("shapeFactory");
        AbstractFactory colorFactory = factoryProducer.getFactory("colorFactory");
        Shape shape = shapeFactory.getShape("circle");
        Color color = colorFactory.getColor("red");
        shape.draw();
        color.getColor();
    }

}
