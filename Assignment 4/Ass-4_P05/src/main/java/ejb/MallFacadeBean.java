package ejb;

import entity.Category;
import entity.MallItem;
import entity.Supplier;
import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import java.util.List;

@Stateless
public class MallFacadeBean {

    @PersistenceContext(unitName = "ass4p05PU")
    private EntityManager em;

    public String addCategory(String name) {
        if (name == null || name.isBlank()) {
            return "Category name is required";
        }
        Category c = new Category();
        c.setCategoryName(name.trim());
        em.persist(c);
        return "Category added";
    }

    public String addSupplier(String name) {
        if (name == null || name.isBlank()) {
            return "Supplier name is required";
        }
        Supplier s = new Supplier();
        s.setSupplierName(name.trim());
        em.persist(s);
        return "Supplier added";
    }

    public String addItem(String name, String priceText, String categoryIdText, String supplierIdText) {
        if (name == null || name.isBlank()) {
            return "Item name is required";
        }
        double price;
        long categoryId;
        long supplierId;
        try {
            price = Double.parseDouble(priceText);
            categoryId = Long.parseLong(categoryIdText);
            supplierId = Long.parseLong(supplierIdText);
        } catch (Exception ex) {
            return "Invalid numeric inputs";
        }
        if (price <= 0) {
            return "Price must be positive";
        }

        Category category = em.find(Category.class, categoryId);
        Supplier supplier = em.find(Supplier.class, supplierId);
        if (category == null || supplier == null) {
            return "Invalid category or supplier id";
        }

        MallItem item = new MallItem();
        item.setItemName(name.trim());
        item.setPrice(price);
        item.setCategory(category);
        item.setSupplier(supplier);
        em.persist(item);
        return "Item added";
    }

    public List<Category> categories() {
        return em.createQuery("SELECT c FROM Category c ORDER BY c.id", Category.class).getResultList();
    }

    public List<Supplier> suppliers() {
        return em.createQuery("SELECT s FROM Supplier s ORDER BY s.id", Supplier.class).getResultList();
    }

    public List<MallItem> items() {
        return em.createQuery("SELECT i FROM MallItem i ORDER BY i.id", MallItem.class).getResultList();
    }
}

