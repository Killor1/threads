package threads.Ex1;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

//Aqui probamos lo que tardaria haciendolo normal, sin que sea con callable:
public class MultiLlist {
	
	public static void main(String[] args) throws InterruptedException, ExecutionException {
 
		//instancio un arrayList para guardar los elementos Multiplicacion (las operaciones)
		List<Multiplicacion> taskList= new ArrayList<Multiplicacion>();
		//Tiro en bucle una serie de procesos, para que me construya un array grande que me permita 
		//ver bien los tiempos, solo he de ir instanciando objetos de clase Multiplicacion con valores random y guardandolas
		//en el array::
		for (int i = 0; i < 10000; i++) {  
			Multiplicacion calc = new Multiplicacion((int)(Math.random()*10), (int)(Math.random()*10));  
			taskList.add(calc);  
		}  
		
		//Ahora en un nuevo array, guardamos las operaciones y lo que han tardado en hacerse:
		List <Integer> resultList = new ArrayList<Integer>();
		//primero cojo el tiempo en el que empieza:
		long temp1 = System.currentTimeMillis();
		//hago que mis objetos Multiplicacion realicen las operaciones mediante el metodo "multiplica"
		//y guardo los resultados en el array:
		for(Multiplicacion m : taskList){
			resultList.add(m.multiplica());
		}
		//y cuando acabe cojo el tiempo final:
		long temp2 = System.currentTimeMillis();
		
		// ahora muestro los resultados de las multiplicaciones
		for (int i = 0; i < resultList.size(); i++) {    
			int resultado = resultList.get(i);     
				System.out.println("Resultado operación " +(i+1)+ ": " + resultado);  
		}

		//y calculo el tiempo que ha tardado en ahcerlas:
		System.out.println("Tiempo de ejecución : " + (temp2-temp1) + " milisegundos");
		
	}
}