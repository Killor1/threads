package threads.Ex3;
/*Crear un proc�s Callable que donat un text (String) i un car�cter cerqui quantes ocurr�ncies d�aquest hi ha dins l�String. Despr�s crear un 
 * programa que generi X cerques de car�cters aleatoris en un String. Aquestes tasques les ha d�executar un Executor.
(el text no cal que sigui diferent a cada cerca, es pot fer servir la mateixa String)*/

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class FindCharList {

	public static void main(String[] args) throws InterruptedException {
		//creamos el executor con tres hilos:
		ExecutorService executor = (ExecutorService) Executors.newFixedThreadPool(3);
		//y el array list de elementos de la clase FindChar
		List<FindChar> taskList= new ArrayList<FindChar>();
		//y un array list donde vamos a meter el abecedario para que busque la concurrencia de alguna letra en el texto:
		List<Character>abc = new ArrayList<Character>();
		//y pasamos un texto copiado de alguna pagina cualquiera:
		String text = "En la margen suroeste de la selva amaz�nica, el primer lunes de la primavera, naci� Tinkus. A diferencia de los otros camaleones "
				+ "beb�s de la maternidad, �l no pod�a cambiar de color. Sin embargo, por muy evidente que eso fuese, sus padres no se dieron cuenta. Quiz� "
				+ "estaban tan felices por traer un hijo al mundo que les imped�a ver cualquier defecto. O quiz� fue otra la raz�n, porque no s�lo ellos lo "
				+ "pasaron por alto, sino tambi�n la matrona, los enfermeros, las pacientes y las visitas. Lo m�s probable es que haya sido la costumbre. "
				+ "Mimetizarse con el entorno estaba tan asumido como respirar. S�lo hac�an ciertas referencias al color cuando necesitaban indicar la ubicaci�n "
				+ "de alg�n amigo o pariente.";
				
		//cargamos la lista del abecedario con las letras del mismo:
		for(char c = 'a';c <= 'z'; c++){
			abc.add(c);
		}
		//random para sacar un valor de letra aleatorio
		Random rand = new Random();
		
		for(int i = 0; i < 30; i++){
			//pillo un n�mero aleatorio del 1 al 26 y guardo la letra a la que pertenece ese n�mero aleatorio
			int randnum = rand.nextInt(26);
			char letra = abc.get(randnum);
			//creo un objeto del tipo CharInString y le paso el texto que quiero tratar y la letra aleatoria
			FindChar valor = new FindChar(text,letra);
			taskList.add(valor);
		}
		
		//creamos una lista de resultados para ver la concurrencia de la letra elegida en el texto:
		List<Future<Integer>> resultList;
		resultList = executor.invokeAll(taskList);
		executor.shutdown();
		
		//y ahora podemos mostrar los datos:
		//recorremos la lista de resultados:
		for (int i = 0; i < resultList.size(); i++) {
			//y vamos sacando los valores y los vamos imprimiendo por pantalla:
			Future<Integer> resultado = resultList.get(i);
			try {        
				System.out.println("Proceso : "+(i+1)+ " Letra " + taskList.get(i).getLetra() + " tiene " + resultado.get()+ " ocurrencias.");  
			} catch (InterruptedException | ExecutionException e) {  
			}   
		}
	}
}
