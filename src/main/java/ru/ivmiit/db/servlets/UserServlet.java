/*
 * Copyright (c) 2022 Tander, All Rights Reserved.
 */

package ru.ivmiit.db.servlets;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.*;
import java.sql.*;
import java.util.Properties;

/**
 * Класс UserServlet
 */

@WebServlet("/users")
public class UserServlet extends HttpServlet {

	private Connection connection;

	@Override
	public void init() throws ServletException {

		Properties properties = new Properties();

		try {
			properties.load(new FileInputStream(getServletContext().getRealPath("/WEB-INF/classes/db.properties")));
			String dbUrl = properties.getProperty("db.url");
			String dbUsername = properties.getProperty("db.username");
			String dbPassword = properties.getProperty("db.password");
			String driverClassName = properties.getProperty("db.driverClassName");

			Class.forName(driverClassName);
			connection = DriverManager.getConnection(dbUrl, dbUsername, dbPassword);
		} catch (IOException | SQLException | ClassNotFoundException e) {
			throw new IllegalStateException(e);
		}
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.getServletContext().getRequestDispatcher("/jsp/addUser.jsp").forward(req, resp);
	}


	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String firstName = req.getParameter("first-name");
		String lastName = req.getParameter("last-name");

		try {
//            Statement statement = connection.createStatement();
//            String sqlInsert = "INSERT INTO fix_user(first_name, last_name)" +
//                    "VALUES('" + firstName + "','" + lastName + "');";
//            System.out.println(sqlInsert);
//            statement.execute(sqlInsert);
			PreparedStatement preparedStatement =
				connection.prepareStatement("INSERT INTO " +
					"fix_user(first_name, last_name) VALUES (?, ?)");
			preparedStatement.setString(1, firstName);
			preparedStatement.setString(2, lastName);
			preparedStatement.execute();
		} catch (SQLException e) {
			throw new IllegalStateException(e);
		}
	}
}