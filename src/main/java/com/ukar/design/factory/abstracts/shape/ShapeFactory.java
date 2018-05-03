package com.ukar.design.factory.abstracts.shape;

import com.ukar.design.factory.abstracts.AbstractFactory;
import com.ukar.design.factory.abstracts.color.Color;
import com.ukar.design.factory.normal.*;
import com.ukar.design.factory.normal.Circle;
import com.ukar.design.factory.normal.Rectangle;
import com.ukar.design.factory.normal.Shape;
import com.ukar.design.factory.normal.Square;
import org.apache.commons.lang3.StringUtils;

/**
 * Created by jyou on 2018/4/8.
 *
 * 简单工厂
 */
public class ShapeFactory extends AbstractFactory{


    @Override
    public Shape getShape(String shape) {
        if(StringUtils.isBlank(shape)){
            return null;
        }
        if(shape.equalsIgnoreCase("Circle")){
            return new Circle();
        }else if(shape.equalsIgnoreCase("Rectangle")){
            return new Rectangle();
        }else if(shape.equalsIgnoreCase("Square")){
            return new Square();
        }
        return null;
    }

    @Override
    public Color getColor(String color) {
        return null;
    }
}
