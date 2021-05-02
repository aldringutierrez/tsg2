package com.aeg.tsg.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.aeg.tsg.model.Cliente;

/**
 * 
 * @author aeg
 *
 */
public class ClienteDAO {

	private String jdbcURL = "jdbc:oracle:thin:@//localhost:1521/xe";
	private String jdbcClientename = "tsg";
	private String jdbcPassword = "tsg";

	public ClienteDAO() {
	}

	protected Connection getConnection() {
		Connection connection = null;
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			connection = DriverManager.getConnection(jdbcURL, jdbcClientename, jdbcPassword);
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		return connection;
	}

	public Cliente selectOne(int id) {
		//System.out.println("qqq ClienteDao.selectOne ");
		Cliente cliente = new Cliente();
		try (Connection connection = getConnection();) 
		{
			PreparedStatement preparedStatement = connection.prepareStatement("select * from clientes where id_cliente =?");
			preparedStatement.setInt(1, id);
			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
				cliente = new Cliente();
				cliente.setIdCliente(rs.getLong("id_cliente"));
				cliente.setCedula(rs.getLong("cedula"));
				cliente.setNombres(rs.getString("nombres"));
				cliente.setApellidos(rs.getString("apellidos"));
				cliente.setDireccion(rs.getString("direccion"));
				cliente.setTelefono(rs.getLong("telefono"));
			}
		} catch (SQLException e) {
			printSQLException(e);
		}
		return cliente;
	}

	public List<Cliente> selectAll() {
		List<Cliente> clientes = new ArrayList<>();
		try (Connection connection = getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement("select * from clientes");) {
			//System.out.println(preparedStatement);
			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
				Cliente cliente = new Cliente();
				cliente.setIdCliente(rs.getLong("id_cliente"));
				cliente.setCedula(rs.getLong("cedula"));
				cliente.setNombres(rs.getString("nombres"));
				cliente.setApellidos(rs.getString("apellidos"));
				cliente.setDireccion(rs.getString("direccion"));
				cliente.setTelefono(rs.getLong("telefono"));
				clientes.add(cliente);
				//System.out.println("qqq  cliente.getNombres()"+cliente.getNombres());
				
			}
		} catch (SQLException e) {
			printSQLException(e);
		}
		return clientes;
	}

	public void insert(Cliente cliente) throws SQLException {
		try (Connection connection = getConnection();) {
			String sntc = "INSERT INTO clientes(cedula,nombres,apellidos,direccion,telefono) "
					+ "VALUES ('" + cliente.getCedula() +"','" + 
					 cliente.getNombres() +"','"+
					cliente.getApellidos() +"','"+
					cliente.getDireccion() +"','"+
					cliente.getTelefono() +
					"')";
			//System.out.println("qqq  sntc : " + sntc);
			PreparedStatement preparedStatement = connection.prepareStatement(sntc);
			preparedStatement.executeUpdate();
		} catch (SQLException e) {
			printSQLException(e);
		}
	}

	public boolean update(Cliente cliente) throws SQLException {
		boolean rowUpdated;
		try (Connection connection = getConnection();) {

			String sntc = "update clientes set " + 
							"cedula = '" + cliente.getCedula() + 
							"',nombres= '" + cliente.getNombres()+
							"',apellidos ='" + cliente.getApellidos() + 
							"',direccion ='" + cliente.getDireccion() + 
							"',telefono ='" + cliente.getTelefono() + 
							"' where id_cliente = " + cliente.getIdCliente();
			System.out.println("sntc : " + sntc);
			PreparedStatement statement = connection.prepareStatement(sntc);
			rowUpdated = statement.executeUpdate() > 0;
		}
		return rowUpdated;
	}

	public boolean delete(Long id) throws SQLException {
		boolean rowDeleted;
		try (Connection connection = getConnection();) {
			String sntc = "delete from clientes where id_cliente ="+id;
			System.out.println("sntc : " + sntc);
			PreparedStatement statement = connection.prepareStatement(sntc);
			rowDeleted = statement.executeUpdate() > 0;
		}
		return rowDeleted;
	}

	private void printSQLException(SQLException ex) {
		for (Throwable e : ex) {
			if (e instanceof SQLException) {
				e.printStackTrace(System.err);
				System.err.println("SQLState: " + ((SQLException) e).getSQLState());
				System.err.println("Error Code: " + ((SQLException) e).getErrorCode());
				System.err.println("Message: " + e.getMessage());
				Throwable t = ex.getCause();
				while (t != null) {
					System.out.println("Cause: " + t);
					t = t.getCause();
				}
			}
		}
	}

}
