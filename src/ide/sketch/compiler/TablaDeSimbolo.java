package ide.sketch.compiler;
import java.util.Vector;
import java.io.*;

public class TablaDeSimbolo{
    private Vector tabla;
    
    public TablaDeSimbolo(){
        tabla = new Vector();
		Simbolos simbolo = new Simbolos();
        simbolo.setCodigo(-1);
        simbolo.setNombre(" ");
        simbolo.setCategoria(" ");
        simbolo.setTipo(-1);
		simbolo.setValor(-1);
        simbolo.setDimension(-1);		
        simbolo.setMinimo(-1);
        simbolo.setMaximo(-1);
		simbolo.setNroPar(-1);
		simbolo.setListPar(-1);
		simbolo.setDireccion(-1);
        simbolo.setAmbito(-1);
		agregaSimbolo2(simbolo);
    }
    
	//SOLO PRUEBA PREGUNTAR SI HAY QUE PASARLE TODOS LOS PARAMETROS.
    public void agregaSimbolo(int codigo,String id,String categoria, int tipo,int valor,int dimension,int min,int max,int nroPar,int lisPar,int dir,int ambito){
		Simbolos simbolo = new Simbolos();
		simbolo.setCodigo(codigo);
        simbolo.setNombre(id);
        simbolo.setCategoria(categoria);
        simbolo.setTipo(tipo);
		simbolo.setValor(valor);
        simbolo.setDimension(dimension);		
        simbolo.setMinimo(min);
        simbolo.setMaximo(max);
		simbolo.setNroPar(nroPar);
		simbolo.setListPar(lisPar);
		simbolo.setDireccion(dir);
        simbolo.setAmbito(ambito);		
        tabla.addElement(simbolo);
    }
	public void modificarVariableVector(int codigo,String nomb,String catego,String tam){
        Simbolos simbolo;
        for (int i = tabla.size() - 1; i >= 0; i--){
			simbolo = getSimbolo(i);
                if (simbolo.getNombre().equals(nomb) && simbolo.getCodigo()==codigo){
					
                    simbolo.setCategoria(catego);
					simbolo.setDimension(Integer.parseInt(tam));
					simbolo.setMinimo(0);
					simbolo.setMaximo(Integer.parseInt(tam)-1);
					//System.out.printf("\nOOOOKK");
                    break;
                }
        }        
    }
	 public void agregaSimbolo2(Simbolos simbolo){
        tabla.addElement(simbolo);
    }
      public boolean existeSimboloEnAmbito(String nombre, int ambito){
        Simbolos simbolo;
        boolean existe = false;
        for (int i = tabla.size() - 1; i >= 0; i--){
            //tipo = getFinal();
            //if (tipo.getAmbito() > ambito) break;
			simbolo = getSimbolo(i);
                if (simbolo.getNombre().equals(nombre) && ambito==simbolo.getAmbito()){
                    existe = true;
                    break;
                }
        }        
        return existe;
    }
	
	 public Simbolos getTipo(String nombre){
        Simbolos retorno = null;
        Simbolos simbolo;
        for (int i = tabla.size() - 1; i >= 0; i--){
            simbolo = getSimbolo(i);
            if (simbolo.getNombre().equals(nombre)){
                retorno = simbolo;
                break;
            }            
        }
        return retorno;
    }
	 public int getDireccionn(String id,int ambito){
        
        Simbolos simbolo;
        for (int i = tabla.size() - 1; i >= 0; i--){
            simbolo = getSimbolo(i);
            if (simbolo.getNombre().equals(id) && simbolo.getAmbito()==ambito){
                return simbolo.getDireccion();
            }            
        }
        return 0;
    }
	
	public int obtenerCodigoTipo(String nombre){
        Simbolos simbolo= getTipo(nombre);
        return simbolo.getTipo();
		
		   }
	
	 public boolean existeSimboloEstruct(String nombre,String catego ,int ambito){
        Simbolos simbolo;
        boolean existe = false;
        for (int i = tabla.size() - 1; i >= 0; i--){
            //tipo = getFinal();
            //if (tipo.getAmbito() > ambito) break;
			simbolo = getSimbolo(i);
                if (simbolo.getNombre().equals(nombre) && simbolo.getCategoria().equals(catego) && ambito==simbolo.getAmbito()){
                    existe = true;
                    break;
                }
        }        
        return existe;
    }
 
	
	
	public boolean existeSimboloFuncion(String nombre,String categoria, int parametros){
        Simbolos simbolo;
        boolean existe = false;
        for (int i = tabla.size() - 1; i >= 0; i--){
            //tipo = getFinal();
            //if (tipo.getAmbito() > ambito) break;
			simbolo = getSimbolo(i);
                if (simbolo.getNombre().equals(nombre) && simbolo.getCategoria().equals(categoria) && parametros==simbolo.getNroPar()){
                    existe = true;
                    break;
                }
        }        
        return existe;
		
    }
	public boolean existeSimboloVect(String nombre,int ambito, String categoria){
        Simbolos simbolo;
        boolean existe = false;
        for (int i = tabla.size() - 1; i >= 0; i--){
            //tipo = getFinal();
            //if (tipo.getAmbito() > ambito) break;
			simbolo = getSimbolo(i);
                if (simbolo.getNombre().equals(nombre)&& ambito==simbolo.getAmbito() && simbolo.getCategoria().equals(categoria)){
					//System.out.printf("\nSI AGARROOO");
                    existe = true;
                    break;
                }
        }        
        return existe;
    }
     public Simbolos getSimbolo(int i){
        return (Simbolos)tabla.elementAt(i);
    }

    
    
}