package com.ukar.design.factory.abstracts.color;

import com.ukar.design.factory.abstracts.AbstractFactory;
import com.ukar.design.factory.normal.Shape;

/**
 * Created by jyou on 2018/4/8.
 */
public class ColorFactory extends AbstractFactory{
    @Override
    public Shape getShape(String shape) {
        return null;
    }

    @Override
    public Color getColor(String color){
        if(color == null){
            return null;
        }
        if(color.equalsIgnoreCase("red")){
            return new RedColor();
        }

        if(color.equalsIgnoreCase("blue")){
            return new BlueColor();
        }

        if(color.equalsIgnoreCase("green")){
            return new GreenColor();
        }
        return null;
    }
}
