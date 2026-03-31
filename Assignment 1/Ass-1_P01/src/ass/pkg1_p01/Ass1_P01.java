/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package ass.pkg1_p01;

/**
 *
 * @author kevil-gandhi
 */
public class Ass1_P01 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here

        try {
            // 🔹 Upcasting (Parent reference, Child object)
            Shape s1 = new Rectangle("Blue", 10, 5);
            Shape s2 = new Triangle("Red", 8, 6);

            // 🔹 Polymorphism (method call decided at runtime)
            System.out.println(s1);
            System.out.println(s2);

            System.out.println("Area using Shape reference:");
            System.out.println("Rectangle Area: " + s1.getArea());
            System.out.println("Triangle Area: " + s2.getArea());

            // 🔹 Downcasting (with safety check)
            if (s1 instanceof Rectangle) {
                Rectangle r = (Rectangle) s1;
                System.out.println("Downcasting successful. Rectangle length = " + r.getLength());
            }

            if (s2 instanceof Triangle) {
                Triangle t = (Triangle) s2;
                System.out.println("Downcasting successful. Triangle height = " + t.getHeight());
            }

        } catch (IllegalArgumentException ex) {
            System.err.println("Input Error: " + ex.getMessage());
        } catch (Exception ex) {
            System.err.println("Unexpected Error: " + ex.getMessage());
        }
    }

}
