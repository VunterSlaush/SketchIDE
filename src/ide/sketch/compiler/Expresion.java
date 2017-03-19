package ide.sketch.compiler;
class Expresion {

	int direccion;
     String tipo;

	Expresion() {
	}
  Expresion(int d ) {
		direccion = d; 
	}
	Expresion(String tip) {
		tipo=tip;
	}


	int getDireccion() {
		return direccion;
	}

	void setDireccion(int d) {
		direccion = d;
	}

	String getTipo() {
		return tipo;
	}

	void setTipo(String d) {
		tipo = d;
	}
}