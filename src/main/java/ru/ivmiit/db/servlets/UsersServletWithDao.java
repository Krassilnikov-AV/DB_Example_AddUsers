/*
 * Copyright (c) 2022 Tander, All Rights Reserved.
 */

package ru.ivmiit.db.servlets;

import org.springframework.jdbc.datasource.DriverManagerDataSource;
import ru.ivmiit.db.dao.*;
import ru.ivmiit.db.model.User;

import javax.servlet.ServletException;
import javax.servlet.http.*;
import java.io.*;
import java.util.*;

/**
 * Класс UsersServletWithDao
 */
public class UsersServletWithDao extends HttpServlet {
	private UsersDao usersDao;

	@Override
	public void init() throws ServletException {
		Properties properties = new Properties();
		DriverManagerDataSource dataSource =
			new DriverManagerDataSource();

		try {
			properties.load(new FileInputStream(getServletContext().getRealPath("/WEB-INF/classes/db.properties")));
			String dbUrl = properties.getProperty("db.url");
			String dbUsername = properties.getProperty("db.username");
			String dbPassword = properties.getProperty("db.password");
			String driverClassName = properties.getProperty("db.driverClassName");

			dataSource.setUsername(dbUsername);
			dataSource.setPassword(dbPassword);
			dataSource.setUrl(dbUrl);
			dataSource.setDriverClassName(driverClassName);

			usersDao = new UsersDaoJdbcTemplateImpl(dataSource);
		} catch (IOException e) {
			throw new IllegalArgumentException(e);
		}
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		Optional<User> user = usersDao.find(1);
		int i = 0;

		List<User> users = null;
		if (req.getParameter("firstName") != null) {
			String firstName = req.getParameter("firstName");
			users = usersDao.findAllByFirstName(firstName);
		} else {
			users = usersDao.findAll();
		}
		req.setAttribute("usersFromServer", users);
		req.getServletContext().getRequestDispatcher("/jsp/users.jsp").forward(req, resp);
	}
}