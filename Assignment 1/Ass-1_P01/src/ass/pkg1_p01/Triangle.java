/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ass.pkg1_p01;

/**
 *
 * @author kevil-gandhi
 */
public class Triangle extends Shape {

    private int base;
    private int height;

    public Triangle(String color, int base, int height) {
        super(color);

        if (base <= 0 || height <= 0) {
            throw new IllegalArgumentException("Base and Height must be positive");
        }

        this.base = base;
        this.height = height;
    }

    @Override
    public double getArea() {
        return 0.5 * base * height;
    }

    public int getHeight() {
        return height;
    }

    @Override
    public String toString() {
        return "Triangle[" + "color=" + color + ", base=" + base + ", height=" + height + ", area=" + getArea() + ']';
    }

}
