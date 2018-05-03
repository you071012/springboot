package com.ukar.design.factory.normal;

import org.apache.commons.lang3.StringUtils;

/**
 * Created by jyou on 2018/4/8.
 *
 * 简单工厂
 */
public class ShapeFactory {

    public Shape getShape(String shapeType){
        if(StringUtils.isBlank(shapeType)){
            return null;
        }
        if(shapeType.equalsIgnoreCase("Circle")){
            return new Circle();
        }else if(shapeType.equalsIgnoreCase("Rectangle")){
            return new Rectangle();
        }else if(shapeType.equalsIgnoreCase("Square")){
            return new Square();
        }
        return null;
    }
}
