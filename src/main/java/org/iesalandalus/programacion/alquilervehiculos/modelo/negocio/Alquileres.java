package org.iesalandalus.programacion.alquilervehiculos.modelo.negocio;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.naming.OperationNotSupportedException;

import org.iesalandalus.programacion.alquilervehiculos.modelo.dominio.Alquiler;
import org.iesalandalus.programacion.alquilervehiculos.modelo.dominio.Cliente;
import org.iesalandalus.programacion.alquilervehiculos.modelo.dominio.Turismo;

public class Alquileres {

	private List<Alquiler> coleccionAlquileres;

	public Alquileres() {
		coleccionAlquileres = new ArrayList<>();
	}

	public List<Alquiler> get() {

		return coleccionAlquileres;

	}

	public List<Alquiler> get(Cliente cliente) {
		List<Alquiler> coleccionCliente = new ArrayList<>();
		for (Alquiler alquiler : coleccionAlquileres) {
			if (alquiler.getCliente().equals(cliente)) {
				coleccionCliente.add(alquiler);
			}
			
		}
		return coleccionCliente;
	}

	public List<Alquiler> get(Turismo turismo) {
		List<Alquiler> coleccionTurismo = new ArrayList<>();
		for (Alquiler alquiler : coleccionAlquileres) {
			if (alquiler.getTurismo().equals(turismo)) {
				coleccionTurismo.add(alquiler);
			}
			
		}
		return coleccionTurismo;
	}

	public int getCantidad() {
		return coleccionAlquileres.size();
	}

	public void insertar(Alquiler alquiler) throws OperationNotSupportedException {
		if (alquiler == null) {
			throw new NullPointerException("ERROR: No se puede buscar un alquiler nulo.");
		}
		if (!coleccionAlquileres.contains(alquiler)) {
			comprobarAlquiler(alquiler.getCliente(), alquiler.getTurismo(), alquiler.getFechaAlquiler());
			coleccionAlquileres.add(alquiler);
		} else {
			throw new OperationNotSupportedException("ERROR: El cliente tiene otro alquiler sin devolver.");

		}

	}

	private void comprobarAlquiler(Cliente cliente, Turismo turismo, LocalDate fechaAlquiler)
			throws OperationNotSupportedException {
		for (Alquiler alquiler : coleccionAlquileres) {
			if (alquiler.getFechaDevolucion() == null) {
				// alquileres sin devolver
				if (alquiler.getCliente().equals(cliente)) {
					throw new OperationNotSupportedException("ERROR: El cliente tiene otro alquiler sin devolver.");

				}
				if (alquiler.getTurismo().equals(turismo)) {
					throw new OperationNotSupportedException("ERROR: El turismo está actualmente alquilado.");
				}
			} else {
				// alquileres devueltos

				if (alquiler.getCliente().equals(cliente) && !alquiler.getFechaDevolucion().isBefore(fechaAlquiler))
						 {
					throw new OperationNotSupportedException("ERROR: El cliente tiene un alquiler posterior.");

				}
				if (alquiler.getTurismo().equals(turismo) && !alquiler.getFechaDevolucion().isBefore(fechaAlquiler))
						 {
					throw new OperationNotSupportedException("ERROR: El turismo tiene un alquiler posterior.");
				}
			}
		}
	}

	public void devolver(Alquiler alquiler, LocalDate fechaDevolucion) throws OperationNotSupportedException {
		if (alquiler == null) {
			throw new NullPointerException("ERROR: No se puede devolver un alquiler nulo.");
		}
		if (coleccionAlquileres.contains(alquiler)) {
			alquiler.devolver(fechaDevolucion);
		} else {
			throw new OperationNotSupportedException("ERROR: No existe ningún alquiler igual.");
		}
	}

	public void borrar(Alquiler alquiler) throws OperationNotSupportedException {
		if (alquiler == null) {
			throw new NullPointerException("ERROR: No se puede borrar un alquiler nulo.");
		}
		if (coleccionAlquileres.contains(alquiler)) {
			coleccionAlquileres.remove(alquiler);
		} else {
			throw new OperationNotSupportedException("ERROR: No existe ningún alquiler igual.");
		}

	}

	public Alquiler buscar(Alquiler alquiler) {
		if (alquiler == null) {
			throw new NullPointerException( "ERROR: No se puede buscar un alquiler nulo.");
		}

		if (coleccionAlquileres.contains(alquiler)) {
			return alquiler;
		} else {
			return null;

		}

	}

}
