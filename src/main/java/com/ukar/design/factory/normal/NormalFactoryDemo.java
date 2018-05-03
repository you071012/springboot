package com.ukar.design.factory.normal;

/**
 * Created by jyou on 2018/4/8.
 */
public class NormalFactoryDemo {
    public static void main(String[] args) {
        ShapeFactory shapeFactory = new ShapeFactory();
        Shape shape = shapeFactory.getShape("circle");
        if(shape != null){
            shape.draw();
        }
    }

}
