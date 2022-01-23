package ru.ivmiit.db.dao;

import ru.ivmiit.db.model.User;

import java.util.List;

/**
 * Класс UsersDao
 */

public interface UsersDao extends CrudDao<User> {
	List<User> findAllByFirstName(String firstName);
}