package ide.sketch.compiler;
import java.util.Vector;
import java.io.*;

public class TablaDeTipos{
    private Vector tabla;
    
    public TablaDeTipos(){
        tabla = new Vector();
        
        Tipo tipo = new Tipo();
        tipo.setCodigo(0);
        tipo.setNombre("int");
        tipo.setDimension(1);
        tipo.setAmbito(0);
        agregaTipo2(tipo);
        
        Tipo tipo2 = new Tipo();
        tipo2.setCodigo(0);
        tipo2.setNombre("void");
        tipo2.setDimension(1);
        tipo2.setAmbito(0);
        agregaTipo2(tipo2);
        
    }
    
	//SOLO PRUEBA
    public void agregaTipo(String id, int ambito){
		Tipo tipo = new Tipo();
		tipo.setNombre(id);
		tipo.setAmbito(ambito);
        tabla.addElement(tipo);
    }
	 public void agregaTipo2(Tipo tipo){
        tabla.addElement(tipo);
    }
    
	
	public void addNuevoTipo(int cod,String id,int tipoBas,int pad,int dimen,int min,int max, int ambito){
		Tipo tipo = new Tipo();
		tipo.setCodigo(cod);
		tipo.setNombre(id);
		tipo.setTipoBase(tipoBas);
		tipo.setPadre(pad);
		tipo.setDimension(dimen);
		tipo.setMinimo(min);
		tipo.setMaximo(max);
		tipo.setAmbito(ambito);
        tabla.addElement(tipo);
    }
	
	
    public boolean existeTipoEnAmbito(String nombreTipo, int ambito){
        Tipo tipo;
        boolean existe = false;
        for (int i = tabla.size() - 1; i >= 0; i--){
            //tipo = getFinal();
            //if (tipo.getAmbito() > ambito) break;
			tipo = getTipo(i);
                if (tipo.getNombre().equals(nombreTipo) && ambito==tipo.getAmbito()){
                    existe = true;
                    break;
                }
        }        
        return existe;
    }
    
    public Tipo getFinal(){
        return (Tipo)tabla.lastElement();
    }
    
    public boolean existeTipo(String nombreTipo){
        boolean existe = false;
        Tipo tipo;
        for (int i = tabla.size() - 1; i >= 0; i--){
            tipo = getTipo(i);
            if (tipo.getNombre().equals(nombreTipo) && tipo.getPadre()==-1){
                existe = true;
                break;
            }            
        }
        return existe;
    }
	
	 public boolean existeTipoStruc(String nombreTipo,int ambito){
        boolean existe = false;
        Tipo tipo;
        for (int i = tabla.size() - 1; i >= 0; i--){
            tipo = getTipo(i);
            if (tipo.getNombre().equals(nombreTipo) && tipo.getAmbito()==ambito){
                existe = true;
                break;
            }            
        }
        return existe;
    }
	
     public boolean existeVariableCuerpoEstruct(int codigo,String id){
        Tipo tipo;
		int dimension,cont=0;
        boolean existe = false;
        for (int i = tabla.size() - 1; i >= 0; i--){
			tipo = getTipo(i);
                if (tipo.getCodigo()==codigo){
					  cont=i;
					 dimension=tipo.getDimension();
					 for (int j =0; j < dimension; j++){
					     cont++;
						 tipo = getTipo(cont);
						 if(tipo.getNombre().equals(id)){
							existe = true;
                             break; 
						 }
					 }
                    
                }
        }        
        return existe;
    }
	
    public Tipo getTipo(int i){
        return (Tipo)tabla.elementAt(i);
    }
    
    public void borrarFinal(){
        tabla.removeElementAt(tabla.size() - 1);
    }
    
    public void setTipo(Tipo tipo, int posi){
        tabla.setElementAt(tipo, posi);
    }
    
    public Tipo getTipo(String nombre){
        Tipo retorno = null;
        Tipo tipo;
        for (int i = tabla.size() - 1; i >= 0; i--){
            tipo = getTipo(i);
            if (tipo.getNombre().equals(nombre)){
                retorno = tipo;
                break;
            }            
        }
        return retorno;
    }
	
	public int getDimensionStruct(String nombre,int ambito){
        int retorno = -1;
        Tipo tipo;
        for (int i = tabla.size() - 1; i >= 0; i--){
            tipo = getTipo(i);
            if (tipo.getNombre().equals(nombre) && tipo.getAmbito() == ambito){
                retorno = tipo.getDimension();
                break;
            }            
        }
        return retorno;
    }
	
		public int obtenerAmbitoDelTipo(String nombre){
        int retorno = -1;
        Tipo tipo;
        for (int i = tabla.size() - 1; i >= 0; i--){
            tipo = getTipo(i);
            if (tipo.getNombre().equals(nombre)){
                retorno = tipo.getAmbito();
                break;
            }            
        }
        return retorno;
    }
	
    public int getCodigoTipo(String nombre){
        Tipo tipo = getTipo(nombre);
        return tipo.getCodigo();
    }
	
   public int obtenerCodigoDelTipo(String id){
	    int retorno = -1;
        Tipo tipo;
        for (int i = tabla.size() - 1; i >= 0; i--){
            tipo = getTipo(i);
            if (tipo.getNombre().equals(id)){
                return tipo.getCodigo();
               
            }            
        }
        return -1;
	   
	   
   }
	
    public void borrarUltimoAmbito(){
        int tam = tabla.size();
        Tipo tipo = getFinal();
        int ambitoActual = tipo.getAmbito();
        for (int i = 0; i < tam; i++){
            tipo = getFinal();
            if (tipo.getAmbito() >= ambitoActual)
                borrarFinal();
            else
                break;
        }                
    }
	
}