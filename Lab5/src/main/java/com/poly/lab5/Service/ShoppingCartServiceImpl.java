package com.poly.lab5.Service;

import com.poly.lab5.Entity.Item;
import com.poly.lab5.Repository.DB;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;
import org.springframework.web.context.annotation.SessionScope;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

@SessionScope
@Service
public class ShoppingCartServiceImpl implements ShoppingCartService {

    private final Map<Integer, Item> cart = new HashMap<>();

    private Item findItemById(Integer id) {
        Item item = DB.items.get(id);
        if (item == null) return null;
        // Important: create a copy of the item with qty = 1
        return new Item(item.getId(), item.getName(), item.getPrice(), 1);
    }

    @Override
    public Item add(Integer id) {
        Item existing = cart.get(id);
        if (existing != null) {
            existing.setQty(existing.getQty() + 1);
            return existing;
        } else {
            Item item = findItemById(id);
            if (item != null) {
                cart.put(id, item);
                return item;
            }
            return null;
        }
    }

    @Override
    public void remove(Integer id) {
        cart.remove(id);
    }

    @Override
    public Item update(Integer id, int qty) {
        Item existing = cart.get(id);
        if (existing != null) {
            if (qty <= 0) {
                cart.remove(id);
                return null;
            }
            existing.setQty(qty);
            return existing;
        }
        return null;
    }

    @Override
    public void clear() {
        cart.clear();
    }

    @Override
    public Collection<Item> getItems() {
        return cart.values();
    }

    @Override
    public int getCount() {
        return cart.values().stream()
                .mapToInt(Item::getQty)
                .sum();
    }

    @Override
    public double getAmount() {
        return cart.values().stream()
                .mapToDouble(item -> item.getPrice() * item.getQty())
                .sum();
    }
}
