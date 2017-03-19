package ide.sketch.compiler;
class InformacionCodigo {

	public static int linea;
	public static String token;

	public static void guardarInformacionCodigo(int l,String t) {
		linea = l;
		token = t;
	}
}