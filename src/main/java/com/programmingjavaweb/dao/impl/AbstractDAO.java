package com.programmingjavaweb.dao.impl;

import com.programmingjavaweb.dao.GenericDAO;
import com.programmingjavaweb.mapper.IRowMapper;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class AbstractDAO<T> implements GenericDAO<T> {

    ResourceBundle resourceBundle = ResourceBundle.getBundle("db");

    public Connection getConnection() {
        try {
//            Class.forName("com.mysql.jdbc.Driver");
//            String url = "jdbc:mysql://localhost:3306/newsservlet";
//            String user = "root";
//            String password = "1234";

            Class.forName(resourceBundle.getString("driverName"));
            String url = resourceBundle.getString("url");
            String user = resourceBundle.getString("user");
            String password = resourceBundle.getString("password");
            return DriverManager.getConnection(url, user, password);
        } catch (ClassNotFoundException | SQLException e) {
            return null;
        }
    }

    @Override
    public <T> List<T> query(String sql, IRowMapper<T> rowMapper, Object... parameters) {
        List<T> results = new ArrayList<>();
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try  {
            connection = getConnection();
            statement = connection.prepareStatement(sql);
            //set parameters
            setParameters(statement, parameters);
            resultSet = statement.executeQuery();
            while(resultSet.next()) {
                results.add(rowMapper.mapRow(resultSet));
            }
            return  results;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        } finally {
            try {
                if(connection != null) {
                    connection.close();
                }
                if(statement != null) {
                    statement.close();
                }
                if(resultSet != null) {
                    resultSet.close();
                }
            } catch(SQLException e) {
                return null;
            }
        }
    }

    @Override
    public void update(String sql, Object... parameters) {
        Connection connection = null;
        PreparedStatement statement = null;
        try {
            connection = getConnection();
            connection.setAutoCommit(false);
            statement = connection.prepareStatement(sql);
            setParameters(statement, parameters);
            statement.executeUpdate();
            connection.commit();
        } catch (SQLException e) {
            try {
                if(connection != null) {
                    connection.rollback();
                }
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
        } finally {
            try {
                if(connection != null) {
                    connection.close();
                }
                if(statement != null) {
                    statement.close();
                }
            } catch(SQLException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public Long insert(String sql, Object... parameters) {
        ResultSet resultSet = null;
        Long id = null;
        Connection connection = null;
        PreparedStatement statement = null;
        try {
            connection = getConnection();
            connection.setAutoCommit(false);
            statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            setParameters(statement, parameters);
            statement.executeUpdate();
            resultSet = statement.getGeneratedKeys();
            while(resultSet.next()) {
                id = resultSet.getLong(1);
            }
            connection.commit();
            return id;
        } catch (SQLException e) {
            try {
                if(connection != null) {
                    connection.rollback();
                }
            } catch (SQLException e1) {
                e1.printStackTrace();
                return null;
            }
            return null;
        } finally {
            try {
                if(connection != null) {
                    connection.close();
                }
                if(statement != null) {
                    statement.close();
                }
                if(resultSet != null) {
                    resultSet.close();
                }
            } catch(SQLException e) {
                return null;
            }
        }
    }

    @Override
    public int count(String sql, Object... parameters) {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try  {
            int count = 0;
            connection = getConnection();
            statement = connection.prepareStatement(sql);
            //set parameters
            setParameters(statement, parameters);
            resultSet = statement.executeQuery();
            while(resultSet.next()) {
                count = resultSet.getInt(1);
            }
            return  count;
        } catch (SQLException e) {
            e.printStackTrace();
            return 0;
        } finally {
            try {
                if(connection != null) {
                    connection.close();
                }
                if(statement != null) {
                    statement.close();
                }
                if(resultSet != null) {
                    resultSet.close();
                }
            } catch(SQLException e) {
                return 0;
            }
        }
    }

    private void setParameters(PreparedStatement statement, Object... parameters) {
        try {
            for(int i = 0; i < parameters.length; i++) {
                statement.setObject(i + 1, parameters[i]);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
