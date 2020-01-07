package com.ukar.design.factory.abstracts;

import com.ukar.design.factory.abstracts.color.ColorFactory;
import com.ukar.design.factory.abstracts.shape.ShapeFactory;

/**
 * Created by jyou on 2018/4/8.
 */
public class FactoryProducer {
    public AbstractFactory getFactory(String choice) {
        if (choice == null) {
            return null;
        }
        if (choice.equalsIgnoreCase("shapeFactory")) {
            return new ShapeFactory();
        }

        if (choice.equalsIgnoreCase("colorFactory")) {
            return new ColorFactory();
        }
        return null;
    }
}
