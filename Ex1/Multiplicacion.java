package threads.Ex1;

//Clase multiplicacion, solo recibe dos enteros en el constructor, y solo tiene un metodo que los 
//multiplica y los devuelve:
public class Multiplicacion{
	private int op1;  
	private int op2;  
	
	public Multiplicacion(int op1, int op2) {  
		this.op1 = op1;  
		this.op2 = op2;  
	}  
	
	//imita al call del original, pero de manera secuencial
	public int multiplica(){
		return this.op1 * this.op2;
	}
	
}
