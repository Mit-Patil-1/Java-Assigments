/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ass.pkg1_p01;

/**
 *
 * @author kevil-gandhi
 */
public class Rectangle extends Shape {

    private int length;
    private int width;

    public Rectangle(String color, int length, int width) {
        super(color);

        if (length <= 0 || width <= 0) {
            throw new IllegalArgumentException("Length and width must be positive.");
        }
        this.length = length;
        this.width = width;
    }

    @Override
    public double getArea() {
        return length * width;
    }

    public int getLength() {
        return length;
    }

    @Override
    public String toString() {
        return "Rectangle [" + "color=" + color + ", length=" + length + ", width=" + width + ", area=" + getArea() + "]";
    }
}
