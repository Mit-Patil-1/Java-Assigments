/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ass.pkg1_p01;

/**
 *
 * @author kevil-gandhi
 */
public abstract class Shape {

    protected String color;

    public Shape(String color) {
        if (color == null || color.isEmpty()) {
            throw new IllegalArgumentException("Color can't be null or empty.");
        }
        this.color = color;
    }

    public abstract double getArea();

    @Override
    public String toString() {
        return "Shape [" + "color=" + color + ']';
    }
}
