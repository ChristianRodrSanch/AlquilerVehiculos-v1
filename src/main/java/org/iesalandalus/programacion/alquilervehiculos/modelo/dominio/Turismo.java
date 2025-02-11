package org.iesalandalus.programacion.alquilervehiculos.modelo.dominio;



public class Turismo extends Vehiculo { //cuando es su hijo el extends indica de donde viene es decir turismo viene de vehiculo
	private static final int FACTOR_CILINDRADA = 10;
	private int cilindrada;
	public Turismo(String marca, String modelo, int cilindrada, String matricula) {
		super(marca, modelo, matricula);
		setCilindrada(cilindrada);
		
	}

	public Turismo(Turismo turismo) {
		super(turismo);
		cilindrada = turismo.getCilindrada();

	}

	public int getCilindrada() {
		return cilindrada;
	}

	private void setCilindrada(int cilindrada) {
		if (cilindrada <= 0 || cilindrada > 5000) {
			throw new IllegalArgumentException("ERROR: La cilindrada no es correcta.");
		}
		this.cilindrada = cilindrada;
	}

	@Override
	public String toString() {
		return String.format( "%s %s (%s cc) - %s", getMarca(), getModelo(), cilindrada,getMatricula());
	}

	@Override
	public int getFactorPrecio() {
	 return cilindrada / FACTOR_CILINDRADA;
	}
	

	
}
