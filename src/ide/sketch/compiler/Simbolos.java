package ide.sketch.compiler;

public class Simbolos{

    private int codigo;
    private String nombre;
    private String categoria;
    private int tipo;
	private int valor;
    private int dimension;
    private int minimo;
    private int maximo;
	private int NroPar;
	private int ListPar;
    private int direccion;	
    private int ambito;
    
    public Simbolos(){
        codigo = -1;
        nombre = "";
        categoria ="";
        tipo = -1;
		valor=-1;
        dimension = -1;		
        minimo = -1;
        maximo = -1;
		NroPar=-1;
		ListPar=-1;
		direccion=-1;
        ambito = -1;
    }
     public Simbolos(int codigo,String nombre,String categoria,int tipo,int valor,int dimension,int maximo,int minimo,int NroPar,int ListPar,int direccion,int ambito){
        this.codigo = codigo;
        this.nombre = nombre;
        this.categoria =categoria;
        this.tipo = tipo;
		this.valor=valor;
        this.dimension = dimension;		
        this.minimo = maximo;
        this.maximo = minimo;
		this.NroPar=NroPar;
		this.ListPar=ListPar;
		this.direccion=direccion;
        this.ambito = ambito;
    }
    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public int getTipo() {
        return tipo;
    }

    public void setTipo(int tipo) {
        this.tipo = tipo;
    }

    public int getValor() {
        return valor;
    }

    public void setValor(int valor) {
        this.valor = valor;
    }

    public int getDimension() {
        return dimension;
    }

    public void setDimension(int dimension) {
        this.dimension = dimension;
    }

    public int getMinimo() {
        return minimo;
    }

    public void setMinimo(int minimo) {
        this.minimo = minimo;
    }

    public int getMaximo() {
        return maximo;
    }

    public void setMaximo(int maximo) {
        this.maximo = maximo;
    }

    public int getNroPar() {
        return NroPar;
    }

    public void setNroPar(int NroPar) {
        this.NroPar = NroPar;
    }

    public int getListPar() {
        return ListPar;
    }

    public void setListPar(int ListPar) {
        this.ListPar = ListPar;
    }

    public int getDireccion() {
        return direccion;
    }

    public void setDireccion(int direccion) {
        this.direccion = direccion;
    }

    public int getAmbito() {
        return ambito;
    }

    public void setAmbito(int ambito) {
        this.ambito = ambito;
    }
}