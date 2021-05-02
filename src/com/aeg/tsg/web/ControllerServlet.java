package com.aeg.tsg.web;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.aeg.tsg.dao.ClienteDAO;
import com.aeg.tsg.dao.TarjetaDAO;
import com.aeg.tsg.model.Cliente;
import com.aeg.tsg.model.Tarjeta;

/**
 * @autho aeg
 */

@WebServlet("/")
public class ControllerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private Cliente clienteActual= new Cliente();;
	private ClienteDAO clienteDAO;
	private TarjetaDAO tarjetaDAO;
	
	public void init() {
		clienteDAO = new ClienteDAO();
		tarjetaDAO = new TarjetaDAO();
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String action = request.getServletPath();
		//System.out.println("qqq  ClienteServlet.doGet action : "+action);
		try {
			switch (action) {
			case "/new":
				showNewForm(request, response);
				break;
			case "/insert":
				insertCliente(request, response);
				break;
			case "/delete":
				deleteCliente(request, response);
				break;
			case "/edit":
				showEditForm(request, response);
				break;
			case "/update":
				updateCliente(request, response);
				break;
			case "/tarjetas":
				tarjetas(request, response);
				break;
			case "/nuevaTarjeta":
				showNewFormTarjeta(request, response);
				break;
			case "/insertTarjeta":
				insertTarjeta(request, response);
				break;
			case "/editTarjeta":
				showEditTarjeta(request, response);
			break;
			case "/marcarTarjeta":
				marcarTarjeta(request, response);
			break;
			case "/deleteTarjeta":
				deleteTarjeta(request, response);
				break;
			case "/updateTarjeta":
				updateTarjeta(request, response);
				break;
			case "/listTarjeta":
				listTarjeta(request, response);
				break;
			default:
				listCliente(request, response);
				break;
			}
		} catch (SQLException ex) {
			throw new ServletException(ex);
		}
	}

	private void listCliente(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, IOException, ServletException {
		List<Cliente> listCliente = clienteDAO.selectAll();
		request.setAttribute("listCliente", listCliente);
		RequestDispatcher dispatcher = request.getRequestDispatcher("cliente-list.jsp");
		dispatcher.forward(request, response);
	}

	private void showNewForm(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		RequestDispatcher dispatcher = request.getRequestDispatcher("cliente-form.jsp");
		dispatcher.forward(request, response);
	}

	private void showEditForm(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, ServletException, IOException {
		int id = Integer.parseInt(request.getParameter("id"));
		Cliente existingCliente = clienteDAO.selectOne(id);
		RequestDispatcher dispatcher = request.getRequestDispatcher("cliente-form.jsp");
		request.setAttribute("cliente", existingCliente);
		dispatcher.forward(request, response);

	}

	private void showEditTarjeta(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, ServletException, IOException {
		Long idTarjeta = Long.valueOf(request.getParameter("id"));
		//System.out.println("idTarjeta : "+idTarjeta);
		Tarjeta tarjeta = tarjetaDAO.selectOne(idTarjeta);
		//System.out.println("qqq getIdTarjeta() : "+tarjeta.getIdTarjeta());
		//System.out.println("qqq tarjeta.getFecVence() : "+tarjeta.getFecVence());
		RequestDispatcher dispatcher = request.getRequestDispatcher("tarjeta-form.jsp");
		request.setAttribute("tarjeta", tarjeta);
		dispatcher.forward(request, response);

	}

	private void insertCliente(HttpServletRequest request, HttpServletResponse response) 
			throws SQLException, IOException {
		Cliente cliente = new Cliente();
//		cliente.setIdCliente(Long.valueOf(request.getParameter("idCliente")));
		cliente.setCedula(Long.valueOf(request.getParameter("cedula")));
		cliente.setNombres(request.getParameter("nombres"));
		cliente.setApellidos(request.getParameter("apellidos"));
		cliente.setDireccion(request.getParameter("direccion"));
		cliente.setTelefono(Long.valueOf(request.getParameter("telefono")));
		clienteDAO.insert(cliente);
		response.sendRedirect("list");
	}

	private void updateCliente(HttpServletRequest request, HttpServletResponse response) 
			throws SQLException, IOException  {
		Long id = Long.valueOf(request.getParameter("idCliente"));
		//System.out.println("qqq  ClienteServlet.updateCliente id : "+id);
		Cliente cliente = new Cliente();
		//System.out.println("qqq  ClienteServlet.updateCliente request.getParameter(idCliente) : "+request.getParameter("idCliente"));
		cliente.setIdCliente(Long.valueOf(request.getParameter("idCliente")));
		//System.out.println("qqq  ClienteServlet.updateCliente request.getParameter(cedula) : "+request.getParameter("cedula"));
		cliente.setCedula(Long.valueOf(request.getParameter("cedula")));
		cliente.setNombres(request.getParameter("nombres"));
		cliente.setApellidos(request.getParameter("apellidos"));
		cliente.setDireccion(request.getParameter("direccion"));
		cliente.setTelefono(Long.valueOf(request.getParameter("telefono")));
		clienteDAO.update(cliente);
		response.sendRedirect("list");
	}

	private void deleteCliente(HttpServletRequest request, HttpServletResponse response) 
			throws SQLException, IOException {
		//System.out.println("qqq  ClienteServlet.deleteCliente request.getParameter('idCliente') : "+request.getParameter("idCliente"));
		Long id = Long.valueOf(request.getParameter("idCliente"));
		clienteDAO.delete(id);
		response.sendRedirect("list");
	}

	private void tarjetas(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, ServletException, IOException {
		int id = Integer.parseInt(request.getParameter("id"));
		Cliente existingClient = clienteDAO.selectOne(id);
		clienteActual = existingClient;
		//System.out.println("qqq  ClienteServlet id 2: "+id);
		request.setAttribute("existingClient", existingClient);
		request.setAttribute("cliente", existingClient);
		List<Tarjeta> listTarjeta = tarjetaDAO.selectCliente(existingClient.getIdCliente());
		request.setAttribute("listTarjeta", listTarjeta);
		RequestDispatcher dispatcher = request.getRequestDispatcher("tarjeta-list.jsp");
		dispatcher.forward(request, response);

	}

	private void showNewFormTarjeta(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		RequestDispatcher dispatcher = request.getRequestDispatcher("tarjeta-form.jsp");
		dispatcher.forward(request, response);
	}

	private void insertTarjeta(HttpServletRequest request, HttpServletResponse response) 
			throws SQLException, IOException {
		//System.out.println("qqq  TarjetaServlet.insertTarjeta ");
		Tarjeta tarjeta = new Tarjeta();
		tarjeta.setBanco(request.getParameter("banco"));
		tarjeta.setNumero(Long.valueOf(request.getParameter("numero")));
		tarjeta.setFecVence(java.sql.Date.valueOf(request.getParameter("vencimiento")));
		tarjeta.setIdCliente(clienteActual.getIdCliente());
		tarjetaDAO.insert(tarjeta);
		response.sendRedirect("listTarjeta");
	}

	private void updateTarjeta(HttpServletRequest request, HttpServletResponse response) 
			throws SQLException, IOException  {
//		System.out.println("qqq  TarjetaServlet.updateTarjeta request.getParameter(idTarjeta) : ");
//		System.out.println("qqq  TarjetaServlet.updateTarjeta request.getParameter(idTarjeta) : "+request.getParameter("idTarjeta"));
		Long id = Long.valueOf(request.getParameter("idTarjeta"));
//		System.out.println("qqq  TarjetaServlet.updateTarjeta id : "+id);
		Tarjeta tarjeta = new Tarjeta();
		//System.out.println("qqq  TarjetaServlet.updateTarjeta request.getParameter(idTarjeta) : "+request.getParameter("idTarjeta"));
		tarjeta.setIdTarjeta(id);
		//System.out.println("qqq  TarjetaServlet.updateTarjeta request.getParameter(cedula) : "+request.getParameter("cedula"));
		tarjeta.setBanco(request.getParameter("banco"));
		tarjeta.setNumero(Long.valueOf(request.getParameter("numero")));
		tarjeta.setFecVence(java.sql.Date.valueOf(request.getParameter("vencimiento")));
		tarjeta.setIdCliente(clienteActual.getIdCliente());
		tarjetaDAO.update(tarjeta);
		response.sendRedirect("listTarjeta");
	}

	private void deleteTarjeta(HttpServletRequest request, HttpServletResponse response) 
			throws SQLException, IOException {
//		System.out.println("qqq  ClienteServlet.deleteTarjeta request.getParameter('idTarjeta') : "+request.getParameter("idTarjeta"));
		Long id = Long.valueOf(request.getParameter("idTarjeta"));
		tarjetaDAO.delete(id);
		response.sendRedirect("listTarjeta");

	}

	private void marcarTarjeta(HttpServletRequest request, HttpServletResponse response) 
			throws SQLException, IOException {
//		System.out.println("qqq  ClienteServlet.marcarTarjeta request.getParameter('idTarjeta') : "+request.getParameter("idTarjeta"));
		Long id = Long.valueOf(request.getParameter("idTarjeta"));
		tarjetaDAO.marcar(id);
		response.sendRedirect("listTarjeta");

	}

	private void listTarjeta(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, IOException, ServletException {
		List<Tarjeta> listTarjeta = tarjetaDAO.selectCliente(clienteActual.getIdCliente());
		request.setAttribute("listTarjeta", listTarjeta);
		RequestDispatcher dispatcher = request.getRequestDispatcher("tarjeta-list.jsp");
		dispatcher.forward(request, response);
	}

}
