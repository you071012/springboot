package com.ukar.design.factory.abstracts;

import com.ukar.design.factory.abstracts.color.Color;
import com.ukar.design.factory.normal.Shape;

/**
 * Created by jyou on 2018/4/8.
 */
public abstract class AbstractFactory {
    public abstract Shape getShape(String shape);

    public abstract Color getColor(String color);
}
