package ide.sketch.compiler;
public class Tipo{

    private int codigo;
    private String nombre;
    private int tipoBase;
    private int padre;
    private int dimension;
    private int minimo;
    private int maximo;
    private int ambito;
    
    public Tipo(){
        codigo = -1;
        nombre = "";
        tipoBase = -1;
        padre = -1;
        dimension = -1;
        minimo = -1;
        maximo = -1;
        ambito = -1;
    }
    
    public void setCodigo(int c){
        this.codigo = c;
    }

    public void setNombre(String n){
        this.nombre = n;
    }

    public void setTipoBase(int t){
        this.tipoBase = t;
    }

    public void setPadre(int p){
        this.padre = p;
    }

    public void setDimension(int d){
        this.dimension = d;
    }

    public void setMinimo(int m){
        this.minimo = m;
    }

    public void setMaximo(int m){
        this.maximo = m;
    }

    public void setAmbito(int a){
        this.ambito = a;
    }

    public int getCodigo(){
        return this.codigo;
    }

    public String getNombre(){
        return this.nombre;
    }

    public int getTipoBase(){
        return this.tipoBase;
    }

    public int getPadre(){
        return this.padre;
    }

    public int getDimension(){
        return this.dimension;
    }

    public int getMinimo(){
        return this.minimo;
    }

    public int getMaximo(){
        return this.maximo;
    }

    public int getAmbito(){
        return this.ambito;
    }    
}