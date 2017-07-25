package DAO;

import Entity.Category;

import java.util.List;

/**
 * Copyright (c) 2017 Peter Mao. All rights reserved.
 * Created by mao on 17-7-22.
 */
public interface CategoryDAO {

    List<Category> getAllCategory();

    void addCategory(Category category);

    Category getCategoryById(int categoryId);

}