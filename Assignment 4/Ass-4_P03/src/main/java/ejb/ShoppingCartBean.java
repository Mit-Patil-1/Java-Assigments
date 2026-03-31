package ejb;

import entity.ComputerItem;
import entity.SaleOrder;
import jakarta.ejb.Stateful;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Stateful
public class ShoppingCartBean {

    public static class CartLine {

        private final String itemName;
        private final int quantity;
        private final double unitPrice;

        public CartLine(String itemName, int quantity, double unitPrice) {
            this.itemName = itemName;
            this.quantity = quantity;
            this.unitPrice = unitPrice;
        }

        public String getItemName() {
            return itemName;
        }

        public int getQuantity() {
            return quantity;
        }

        public double getUnitPrice() {
            return unitPrice;
        }

        public double getLineTotal() {
            return unitPrice * quantity;
        }
    }

    @PersistenceContext(unitName = "ass4p03PU")
    private EntityManager em;

    private final List<CartLine> lines = new ArrayList<>();

    public void addItem(long itemId, int quantity) {
        ComputerItem item = em.find(ComputerItem.class, itemId);
        if (item == null || quantity <= 0) {
            return;
        }
        lines.add(new CartLine(item.getItemName(), quantity, item.getPrice()));
    }

    public List<CartLine> getLines() {
        return Collections.unmodifiableList(lines);
    }

    public double getGrandTotal() {
        return lines.stream().mapToDouble(CartLine::getLineTotal).sum();
    }

    public Long checkout() {
        if (lines.isEmpty()) {
            return null;
        }
        SaleOrder order = new SaleOrder();
        order.setCreatedAt(LocalDateTime.now());
        order.setTotalAmount(getGrandTotal());
        em.persist(order);
        lines.clear();
        return order.getId();
    }
}

