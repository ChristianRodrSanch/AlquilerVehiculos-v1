package org.iesalandalus.programacion.alquilervehiculos.modelo.negocio;

import java.util.ArrayList;
import java.util.List;

import javax.naming.OperationNotSupportedException;

import org.iesalandalus.programacion.alquilervehiculos.modelo.dominio.Cliente;
import org.iesalandalus.programacion.alquilervehiculos.modelo.dominio.Turismo;

public class Turismos {
	private List<Turismo> coleccionTurismo;

	public Turismos() {
		coleccionTurismo = new ArrayList<>();
	}

	public List<Turismo> get() {
		return coleccionTurismo;
	}

	public int getCantidad() {
		return coleccionTurismo.size();
	}

	public void insertar(Turismo turismo) throws OperationNotSupportedException {
		if (turismo == null) {
			throw new NullPointerException("ERROR: No se puede insertar un turismo nulo.");
		}
		if (!coleccionTurismo.contains(turismo)) {
			coleccionTurismo.add(turismo);
		} else {
			throw new OperationNotSupportedException("ERROR: Ya existe un turismo con esa matrícula.");
		}
	}

	public Turismo buscar(Turismo turismo) {

		if (turismo == null) {
			throw new NullPointerException("ERROR: No se puede buscar un turismo nulo.");
		}
		if (coleccionTurismo.contains(turismo)) {
			return turismo;
		} else {
			return null;
		}
	}

	public void borrar(Turismo turismo) throws OperationNotSupportedException {
		if (turismo == null) {
			throw new NullPointerException("ERROR: No se puede borrar un turismo nulo.");
		}
		if (coleccionTurismo.contains(turismo)) {
			coleccionTurismo.remove(turismo);
		} else {
			throw new OperationNotSupportedException("ERROR: No existe ningún turismo con esa matrícula.");
		}
	}

}
