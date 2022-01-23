/*
 * Copyright (c) 2022 Tander, All Rights Reserved.
 */

package ru.ivmiit.db.dao;

import java.util.*;

/**
 * Класс CrudDAO
 */
public interface CrudDao<T> {
	Optional<T> find(Integer id);

	void save(T model);

	void update(T model);

	void delete(Integer id);

	List<T> findAll();
}
