package com.chakray.samples.jaxws;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Hashtable;

import javax.jws.WebResult;
import javax.jws.WebService;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

@WebService(serviceName = "ClientesService")
public class ClientesService {

	@WebMethod(action = "obtenerCliente", operationName = "obtenerCliente")
	@WebResult(name = "Cliente")
	public Cliente obtenerCliente(@WebParam(name = "nombre") String nombre)
			throws CustomException {
		Connection connection = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		Cliente cliente = null;
		try {
			DataSource ds = this.getDataSource();
			connection = ds.getConnection();
			stmt = connection
					.prepareStatement("SELECT id,nombre,direccion,correoelectronico"
						          	+ " FROM cliente WHERE nombre=?");
			stmt.setString(1, nombre);
			rs = stmt.executeQuery();
			cliente = new Cliente();
			while (rs.next()) {
				cliente.setId(rs.getString("id"));
				cliente.setNombre(rs.getString("nombre"));
				cliente.setDireccion(rs.getString("direccion"));
				cliente.setCorreoelectronico(rs.getString("correoelectronico"));

			}

		} catch (Exception e) {
			throw new CustomException(e.getMessage(),
					new CustomExceptionFaultBean(e.getMessage()));
		} finally {
			if (connection != null) {
				try {
					connection.close();
				} catch (Exception e) {
					throw new CustomException(e.getMessage(),
							new CustomExceptionFaultBean(e.getMessage()));
				}
			}
		}
		return cliente;
	}

	private DataSource getDataSource() throws Exception {
		Hashtable<String, Object> environment = new Hashtable<String, Object>();
		environment.put("java.naming.factory.initial",
				"org.wso2.carbon.tomcat.jndi.CarbonJavaURLContextFactory");
		environment.put("java.naming.webapp.bound", true);
		Context initContext = new InitialContext(environment);
		return (DataSource) initContext.lookup("jdbc/MyOracleMBDataSource");
	}
}