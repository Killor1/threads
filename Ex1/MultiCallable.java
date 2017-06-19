package threads.Ex1;

import java.util.concurrent.Callable;

//esta es como la clase multiplicacion, solo que en lugar de ser una clase normal, implementa callable
//y la operacion la hace en el metodo Call que se ha de sobreescribir al implementar callable
public class MultiCallable implements Callable<Integer>{
	private int op1;  
	private int op2;  
	
	public MultiCallable(int op1, int op2) {  
		this.op1 = op1;  
		this.op2 = op2;  
	}  
	@Override
	public Integer call() throws Exception {  
		return op1 * op2;
	}  

}