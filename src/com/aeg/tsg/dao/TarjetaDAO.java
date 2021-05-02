package com.aeg.tsg.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import com.aeg.tsg.model.Tarjeta;

/**
 * 
 * @author aeg
 *
 */
public class TarjetaDAO {
	private String jdbcURL = "jdbc:oracle:thin:@//localhost:1521/xe";
	private String jdbcUsername = "tsg";
	private String jdbcPassword = "tsg";

	public TarjetaDAO() {
	}

	protected Connection getConnection() {
		Connection connection = null;
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			
			connection = DriverManager.getConnection(jdbcURL, jdbcUsername, jdbcPassword);
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		return connection;
	}

	public Tarjeta selectOne(Long id) {
		Tarjeta tarjeta = null;
		// Step 1: Establishing a Connection
		try (Connection connection = getConnection();) 
		{
			String sntc ="select * from tarjetas where id_tarjeta = "+id; 
			PreparedStatement preparedStatement = connection.prepareStatement(sntc);
			//System.out.println(preparedStatement);
			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
				tarjeta = new Tarjeta();
				tarjeta.setBanco(rs.getString("banco"));
				tarjeta.setNumero(Long.valueOf(rs.getString("numero")));
				tarjeta.setIdCliente(Long.valueOf(rs.getString("id_cliente")));
				tarjeta.setIdTarjeta(Long.valueOf(rs.getString("id_tarjeta")));
				tarjeta.setFecVence(rs.getDate("fec_vence"));
				//System.out.println("rs.getDate(fecVence) : "+rs.getDate("fec_vence"));
			}
		} catch (SQLException e) {
			printSQLException(e);
		}
		return tarjeta;
	}

	public List<Tarjeta> selectAll() {
		List<Tarjeta> tarjetas = new ArrayList<>();
		try (Connection connection = getConnection();
			PreparedStatement preparedStatement = connection.prepareStatement("select * from tarjetas");) {
			System.out.println("");
			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
				Tarjeta tarjeta = new Tarjeta();
				tarjeta.setIdTarjeta(rs.getLong("id_tarjeta"));
				tarjeta.setBanco(rs.getString("banco"));
				tarjeta.setNumero(rs.getLong("numero"));
				tarjeta.setFecVence(rs.getDate("fec_vence"));
				tarjeta.setPredeterminada(rs.getString("predeterminada"));
				tarjetas.add(tarjeta);
				//System.out.println("qqq  tarjeta.getNombres()"+tarjeta.getBanco());
			}
		} catch (SQLException e) {
			printSQLException(e);
		}
		return tarjetas;
	}

	public List<Tarjeta> selectCliente(Long long1) {
		List<Tarjeta> tarjetas = new ArrayList<>();
		try (Connection connection = getConnection();
			PreparedStatement preparedStatement = connection.prepareStatement("select * from tarjetas where id_cliente ="+long1);) {
			System.out.println("");
			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
				Tarjeta tarjeta = new Tarjeta();
				tarjeta.setIdTarjeta(rs.getLong("id_tarjeta"));
				tarjeta.setIdCliente(rs.getLong("id_cliente"));
				tarjeta.setBanco(rs.getString("banco"));
				tarjeta.setNumero(rs.getLong("numero"));
				tarjeta.setFecVence(rs.getDate("fec_vence"));
				tarjeta.setPredeterminada(rs.getString("predeterminada"));
				tarjetas.add(tarjeta);
				//System.out.println("qqq  tarjeta.getNombres()"+tarjeta.getBanco());
			}
		} catch (SQLException e) {
			printSQLException(e);
		}
		return tarjetas;
	}

	public void insert(Tarjeta tarjeta) throws SQLException {
		try {
			Connection connection = getConnection();
//			System.out.println("qqq  dao.insert tarjeta.getFecVence() : "+tarjeta.getFecVence());
			Format formatter = new SimpleDateFormat("yyyy/MM/dd");
			String fec1 = formatter.format(tarjeta.getFecVence());			
			String sntc = "INSERT INTO tarjetas(banco,numero,fec_vence,id_cliente) VALUES ('"+
							tarjeta.getBanco()+"','"+
							tarjeta.getNumero()+
							"',to_date('"+fec1+"','yyyy/mm/dd'),'"+
							tarjeta.getIdCliente()+"')";
//			System.out.println("qqq dao.insert sntc : "+sntc);
			PreparedStatement preparedStatement = connection.prepareStatement(sntc);
			preparedStatement.executeUpdate();
		} catch (SQLException e) {
			printSQLException(e);
		}
	}

	public boolean update(Tarjeta tarjeta) throws SQLException {
		boolean rowUpdated;
		try (Connection connection = getConnection();) 
		{
			Format formatter = new SimpleDateFormat("yyyy/MM/dd");
			String fec1 = formatter.format(tarjeta.getFecVence());			
			String sntc = "update tarjetas "+
						"set banco = '"+tarjeta.getBanco()+
						"',numero= '"+tarjeta.getNumero()+
						"',predeterminada= '"+tarjeta.getPredeterminada()+
						"',fec_vence= to_date('"+fec1+"','yyyy/mm/dd')"+
						",id_cliente= "+tarjeta.getIdCliente()+
						" where id_tarjeta = "+tarjeta.getIdTarjeta();
			System.out.println("sntc : "+sntc);
			PreparedStatement statement = connection.prepareStatement(sntc);
			rowUpdated = statement.executeUpdate() > 0;
		}
		return rowUpdated;
	}

	public boolean delete(Long id) throws SQLException {
		boolean rowDeleted;
		try (Connection connection = getConnection();) 
		{
			String sntc = "delete from tarjetas where id_tarjeta ="+id;
			System.out.println("tmp : "+sntc);
			PreparedStatement statement = connection.prepareStatement(sntc);			
			rowDeleted = statement.executeUpdate() > 0;
		}
		return rowDeleted;
	}

	public void marcar(Long id) throws SQLException {
		System.out.println("marcar 1 ");
		try (Connection connection = getConnection();) 
		{
			// se trae el id_cliente antes
			String sntc ="select * from tarjetas where id_tarjeta = "+id; 
			PreparedStatement preparedStatement = connection.prepareStatement(sntc);
			//System.out.println(preparedStatement);
			ResultSet rs = preparedStatement.executeQuery();
			Long id_cliente=0L;
			while (rs.next()) {
				id_cliente=rs.getLong("id_cliente");
			}
			// con el id_cliente se traen las tarjetas del cliente y se marca solo una
			List<Tarjeta> lst = new ArrayList<Tarjeta>();
			lst = selectCliente(id_cliente);
			for(Tarjeta tarjeta:lst){
				System.out.println("marcar 2 ");
				if(tarjeta.getIdTarjeta().equals(id)){
					tarjeta.setPredeterminada("S");
				} else{
					tarjeta.setPredeterminada("N");
				}
				System.out.println("marcar 3 ");
				this.update(tarjeta);
			}
		}
		System.out.println("marcar 4 ");
		return;
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
