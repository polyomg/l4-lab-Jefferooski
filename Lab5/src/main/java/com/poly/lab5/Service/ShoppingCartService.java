package com.poly.lab5.Service;

import com.poly.lab5.Entity.Item;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
@Primary
public interface ShoppingCartService {
    Item add(Integer id);

    void remove(Integer id);

    Item update(Integer id, int qty);

    void clear();

    Collection<Item> getItems();

    int getCount();

    double getAmount();
}
