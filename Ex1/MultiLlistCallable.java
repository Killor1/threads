package threads.Ex1;

import java.util.*; 
import java.util.concurrent.*;

public class MultiLlistCallable {
	
	public static void main(String[] args) throws InterruptedException, ExecutionException {
		//creamos los tres hilos para que vaya que estos ejecuten las operaciones
		ThreadPoolExecutor executor = (ThreadPoolExecutor) Executors.newFixedThreadPool(3); 
		//y unm array de nuestra clase MultiCallable para ir guardando los elementos:
		List<MultiCallable> taskList= new ArrayList<MultiCallable>(); 
		//lo llenamos igual que lo hicimos en la clase sin el callable, para tener la misma cantidad de elementos
		//y que esta parte de la ejecucion tarde lo mismo que la anterior, para asi despues poder comparar bien:
		for (int i = 0; i < 10000; i++) {  
			MultiCallable calc = new MultiCallable((int)(Math.random()*10), (int)(Math.random()*10));  
			taskList.add(calc);  
		}  
		//Ahora nos creamos un list<future<integer>> que nos guardara los resultados de las operaciones:
		List <Future<Integer>> resultList;
		//cogemos el tiempo actual:
		long temp1 = System.currentTimeMillis();
		//le decimos al executor que haga las operaciones
		resultList = executor.invokeAll(taskList);
		//cogemos el segundo tiempo para poder calcular lo que tardó:
		long temp2 = System.currentTimeMillis();
		//y cerramos el executor:
		executor.shutdown();  
		//ahora procedemos a mostrar los resultados:
		for (int i = 0; i < resultList.size(); i++) {  
			Future<Integer> result = resultList.get(i);  
			try {        
				System.out.println("Resultado operación " +(i+1)+ ": "  + result.get());  
			} catch (InterruptedException | ExecutionException e) {
				e.printStackTrace();
			}   
		}
		//y el tiempo que ha tardado en ejecutarlas:
		System.out.println("Temps: " + (temp2-temp1) + " milisegons");
		
		//Despues de ejecutar, podemos comprobar que haciendolo asi, en este caso, ha tardado bastante mas que en el caso anterior, ya que tiene
		//que crear los hilos correspondientes para las ejecuciones
		
	}
}
