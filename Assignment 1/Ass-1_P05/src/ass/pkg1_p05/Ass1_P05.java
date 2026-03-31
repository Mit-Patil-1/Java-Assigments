/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package ass.pkg1_p05;

/**
 *
 * @author kevil-gandhi
 */
import java.sql.*;
import java.util.*;

public class Ass1_P05 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {

        List<Category> categories = new ArrayList<>();

        try {
            Connection con = DBConnection.getConnection();

            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(
                    "SELECT category_id, category_name, parent_category_id FROM Category_Master"
            );

            // Read data from DB
            while (rs.next()) {
                categories.add(
                        new Category(
                                rs.getInt("category_id"),
                                rs.getString("category_name"),
                                (Integer) rs.getObject("parent_category_id")
                        )
                );
            }

            con.close();

        } catch (Exception e) {
            e.printStackTrace();
        }

        // Build parent → children map
        Map<Integer, List<Category>> treeMap = new HashMap<>();

        for (Category c : categories) {
            treeMap
                    .computeIfAbsent(c.parentId, k -> new ArrayList<>())
                    .add(c);
        }

        // Print tree starting from root (parentId = NULL)
        printTree(null, treeMap, 0);
    }

    // Recursive method to print hierarchy
    private static void printTree(
            Integer parentId,
            Map<Integer, List<Category>> treeMap,
            int level) {

        List<Category> children = treeMap.get(parentId);
        if (children == null) {
            return;
        }

        for (Category c : children) {
            System.out.println("  ".repeat(level) + "- " + c.name);
            printTree(c.id, treeMap, level + 1);
        }

    }

}
