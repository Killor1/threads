package threads.Ex2;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/*L’objectiu d’aquesta activitat és practicar la programació paral·lela amb el framework executor de Java.
Haurem de trobar tots els números divisibles per 2 i per 3 més petits de 100. La idea és crear tasques per tal que verifiquin si un número és múltiple de 2 i 
també crear tasques per veure si són múltiples de 3. Per últim s’han de mostrar els que són múltiples dels dos. La classe fils hauran d’implementar Callable.*/
public class MathsExecutor {
	
	//creamos una pequeña subclase divisor dentro de la principal, como clase auxiliar (desaconsejado)
	//que implemente callable de Bool:
	static class Divisor implements Callable<Boolean> {
		private int numero;
		private int divisor;

		public Divisor(int numero,int divisor ) {
			this.numero = numero;
			this.divisor = divisor;
		}
		//ahora en su Call, lo unico que hacemos es que diga si la division devuelve cero el modulo(resto), en ese caso true, sino false:
		@Override
		public Boolean call() throws Exception {
			if((numero%divisor)==0){
				return true;
			}else{
				return false;
			}		
		}
	}
	//ahora tiramos el main para ver el funcionamoento:
	public static void main(String[] args) throws InterruptedException, ExecutionException {
		//creamos un array que acoja 100 enteros y lo llenamos con un bucle:
		//este array es inutil, ya que no se usa despues
		int[] data = new int[100];
		for (int i = 0; i < data.length; i++) {
			data[i]= i;
		}
		
		//instanciamos el executor con tres hilos, y dos arrays de Divisor para coger los resultados de /2 y de /3;
		ExecutorService executor = (ExecutorService) Executors.newFixedThreadPool(3);
		List<Divisor> llistaTasques2= new ArrayList<Divisor>();
		List<Divisor> llistaTasques3= new ArrayList<Divisor>();

		//en cada uno de los dos arrays, metemos un objeto de la clase divisor, cada uno pasandole como primer argumento un valor de 1 a 100 en bucle
		//y como segundo parametro los divisores, que seran o 2 o3:
		for (int i = 0; i < 100; i++) {
			Divisor calculaDivisor2 = new Divisor(i,2);
			llistaTasques2.add(calculaDivisor2);
			Divisor calculaDivisor3 = new Divisor(i,3);
			llistaTasques3.add(calculaDivisor3);
		}
		
		//hacemos el call para los dos arrays de Divisor, para que ejecute los procesos y comprobemos cuales son divisibles entre 2 y/o 3:
		List <Future<Boolean>> llistaResultats2;
		llistaResultats2 = executor.invokeAll(llistaTasques2);
		List <Future<Boolean>> llistaResultats3;
		llistaResultats3 = executor.invokeAll(llistaTasques3);
		//y cerramos el executor
		executor.shutdown();
		
		//ahora cargamos los resultados de nuestros arrays de Divisores, en dos arrays de Future para poder comparar despues:
		for (int i = 0; i < llistaResultats2.size(); i++) {
			Future<Boolean> resultat2 = llistaResultats2.get(i);
			Future<Boolean> resultat3 = llistaResultats3.get(i);
			try {
				//y aqui vemos por indices equivalentes, si ambos son true, que sera la condicion para ver que los numeros seran divisibles por 2 y por 3:
				if (resultat2.get() && resultat3.get())
					System.out.println("Número "+i+ " és divisible per 2 i per 3");
				//System.out.println("Número "+i+ " és divisible per 2 :" + resultat2.get() + " i divisible per 3: "+ resultat3.get());
			} catch (InterruptedException | ExecutionException e) {
			}
		}
	}
}