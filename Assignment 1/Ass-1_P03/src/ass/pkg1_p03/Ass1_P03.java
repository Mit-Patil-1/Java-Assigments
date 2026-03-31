/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package ass.pkg1_p03;

/**
 *
 * @author kevil-gandhi
 */
import java.util.Hashtable;
import java.util.Scanner;

public class Ass1_P03 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        DataTable table;

        try {
            System.out.print("Enter rows: ");
            int r = sc.nextInt();
            System.out.print("Enter columns: ");
            int c = sc.nextInt();

            table = new DataTable(r, c);

        } catch (TableException ex) {
            System.out.println(ex.getMessage());
            return;
        }

        while (true) {
            System.out.println("\n1.Insert Cell  \n2.Display  \n3.Insert Row  \n4.Delete Row");
            System.out.println("5.Insert Col  \n6.Delete Col  \n7.Features  \n8.Populate  \n9.Exit");

            System.out.print("\n Enter Your Choice : ");
            
            int choice = sc.nextInt();

            switch (choice) {
                case 1:
                    System.out.print("Row Col Value: ");
                    table.setCell(sc.nextInt(), sc.nextInt(), sc.next());
                    break;

                case 2:
                    table.display();
                    break;

                case 3:
                    table.insertRow();
                    break;

                case 4:
                    System.out.print("Row index: ");
                    table.deleteRow(sc.nextInt());
                    break;

                case 5:
                    table.insertColumn();
                    break;

                case 6:
                    System.out.print("Column index: ");
                    table.deleteColumn(sc.nextInt());
                    break;

                case 7:
                    table.setForeground("Blue");
                    table.setBackground("Yellow");
                    table.setFont("Times New Roman");
                    System.out.println("Features Applied");
                    break;

                case 8:
                    Hashtable<String, String> ht = new Hashtable<>();
                    ht.put("A", "100");
                    ht.put("B", "200");
                    table.populateFromHashtable(ht);
                    break;

                case 9:
                    System.exit(0);
            }
        }
    }
}
