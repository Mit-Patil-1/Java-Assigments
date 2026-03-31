package com.mycompany.assignment2.model;

public class Category {
    private int categoryId;
    private String categoryName;
    private int parentCategoryId;

    public Category() {}

    public Category(int categoryId, String categoryName, int parentCategoryId) {
        this.categoryId = categoryId;
        this.categoryName = categoryName;
        this.parentCategoryId = parentCategoryId;
    }

    public int getCategoryId() { return categoryId; }
    public void setCategoryId(int categoryId) { this.categoryId = categoryId; }

    public String getCategoryName() { return categoryName; }
    public void setCategoryName(String categoryName) { this.categoryName = categoryName; }

    public int getParentCategoryId() { return parentCategoryId; }
    public void setParentCategoryId(int parentCategoryId) { this.parentCategoryId = parentCategoryId; }
}
