package org.iesalandalus.programacion.alquilervehiculos.modelo;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.naming.OperationNotSupportedException;

import org.iesalandalus.programacion.alquilervehiculos.modelo.dominio.Alquiler;
import org.iesalandalus.programacion.alquilervehiculos.modelo.dominio.Cliente;
import org.iesalandalus.programacion.alquilervehiculos.modelo.dominio.Turismo;
import org.iesalandalus.programacion.alquilervehiculos.modelo.dominio.Vehiculo;
import org.iesalandalus.programacion.alquilervehiculos.modelo.negocio.IFuenteDatos;

public class ModeloCascada extends Modelo {

	public ModeloCascada(IFuenteDatos fuenteDatos) {

	}

	@Override
	public void insertar(Cliente cliente) throws OperationNotSupportedException {
		getClientes().insertar(new Cliente(cliente));

	}

	@Override
	public void insertar(Vehiculo vehiculo) throws OperationNotSupportedException {
		getIVehiculos().insertar(new Vehiculo(vehiculo));
	}

	@Override
	public void insertar(Alquiler alquiler) throws OperationNotSupportedException {
		if (alquiler == null) {
			throw new NullPointerException("ERROR: No se puede realizar un alquiler nulo.");
		}
		Cliente cliente = clientes.buscar(alquiler.getCliente());

		if (cliente == null)

		{
			throw new OperationNotSupportedException("ERROR: No existe el cliente del alquiler.");

		}
		Vehiculo vehiculo = vehiculo.buscar(alquiler.getVehiculo());
		if (vehiculo == null) {
			throw new OperationNotSupportedException("ERROR: No existe el turismo del alquiler.");

		}

		alquileres.insertar(new Alquiler(cliente, vehiculo, alquiler.getFechaAlquiler()));
	}

	@Override
	public Cliente buscar(Cliente cliente) {
		return new Cliente(clientes.buscar(cliente));
	}

	@Override
	public Vehiculo buscar(Turismo turismo) {
		return new Turismo(turismos.buscar(turismo));
	}

	@Override
	public Alquiler buscar(Alquiler alquiler) {
		return new Alquiler(alquileres.buscar(alquiler));
	}

	@Override
	public void modificar(Cliente cliente, String nombre, String telefono) throws OperationNotSupportedException {
		clientes.modificar(cliente, nombre, telefono);
	}

	@Override
	public void devolver(Alquiler alquiler, LocalDate fechaDevolucion) throws OperationNotSupportedException {
		Alquiler alquilerEncontrado = alquileres.buscar(alquiler);
		if (alquilerEncontrado == null) {
			throw new OperationNotSupportedException("ERROR: No existe el alquiler a devolver.");

		}
		alquilerEncontrado.devolver(fechaDevolucion);
	}

	@Override
	public void borrar(Cliente cliente) throws OperationNotSupportedException {
		for (Alquiler alquiler : alquileres.get(cliente)) {
			borrar(alquiler);
		}
		clientes.borrar(cliente);
	}

	@Override
	public void borrar(Vehiculo vehiculo) throws OperationNotSupportedException {
		for (Alquiler alquiler : alquileres.get(vehiculo)) {
			borrar(alquiler);

		}
		vehiculos.borrar(vehiculo);
	}

	@Override
	public void borrar(Alquiler alquiler) throws OperationNotSupportedException {
		alquileres.borrar(alquiler);
	}

	@Override
	public List<Cliente> getClientes() {
		List<Cliente> listaCliente = new ArrayList<>();
		for (Cliente cadaCliente : clientes.get()) {
			Cliente cliente = new Cliente(cadaCliente);
			listaCliente.add(cliente);
		}
		return listaCliente;
	}

	@Override
	public List<Turismo> getTurismos() {
		List<Turismo> listaTurismo = new ArrayList<>();
		for (Turismo cadaTurismo : turismos.get()) {
			Turismo turismo = new Turismo(cadaTurismo);
			listaTurismo.add(turismo);
		}
		return listaTurismo;
	}

	@Override
	public List<Alquiler> getAlquileres() {
		List<Alquiler> listaAlquiler = new ArrayList<>();
		for (Alquiler cadaAlquiler : alquileres.get()) {
			Alquiler alquiler = new Alquiler(cadaAlquiler);
			listaAlquiler.add(alquiler);
		}
		return listaAlquiler;
	}

	@Override
	public List<Alquiler> getAlquileres(Cliente cliente) {
		List<Alquiler> listaAlquiler = new ArrayList<>();
		for (Alquiler cadaAlquiler : alquileres.get(cliente)) {
			Alquiler alquiler = new Alquiler(cadaAlquiler);
			listaAlquiler.add(alquiler);

		}
		return listaAlquiler;
	}

	@Override
	public List<Alquiler> getAlquileres(Vehiculo turismo) {
		List<Alquiler> listaAlquiler = new ArrayList<>();
		for (Alquiler cadaAlquiler : alquileres.get(turismo)) {
			Alquiler alquiler = new Alquiler(cadaAlquiler);
			listaAlquiler.add(alquiler);

		}
		return listaAlquiler;
	}

	@Override
	public void devolver(Cliente cliente, LocalDate fechaDevolucion) throws OperationNotSupportedException {
		// TODO Auto-generated method stub

	}

	@Override
	public Vehiculo buscar(Vehiculo turismo) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void insertar(Vehiculo vehiculo) throws OperationNotSupportedException {
		// TODO Auto-generated method stub

	}

}