package threads.Ex4;
/*Crear la versió seqüencial del punt 3. (No cal calcular si el 3 és més ràpid)
*/
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class FindCharListSec {

	public static void main(String[] args) throws InterruptedException {
		//creamos la lista de elementos de nuestra clase FIndCharSec, un arrayList de chars que sera contendra el abecedario, y el texto sobre el cual vamos
		//a iterar:
		List<FindCharSec> taskList= new ArrayList<FindCharSec>();
		List<Character> abc = new ArrayList<Character>();
		String text = "En la margen suroeste de la selva amazónica, el primer lunes de la primavera, nació Tinkus. A diferencia de los otros camaleones "
				+ "bebés de la maternidad, él no podía cambiar de color. Sin embargo, por muy evidente que eso fuese, sus padres no se dieron cuenta. Quizá "
				+ "estaban tan felices por traer un hijo al mundo que les impedía ver cualquier defecto. O quizá fue otra la razón, porque no sólo ellos lo "
				+ "pasaron por alto, sino también la matrona, los enfermeros, las pacientes y las visitas. Lo más probable es que haya sido la costumbre. "
				+ "Mimetizarse con el entorno estaba tan asumido como respirar. Sólo hacían ciertas referencias al color cuando necesitaban indicar la ubicación "
				+ "de algún amigo o pariente.";
				
		//cargamos el abecedario en su array
		for(char c = 'a';c <= 'z'; c++){
			abc.add(c);
		}
		
		Random rand = new Random();
		//sacamos objetos FindCharSec aleatorios con letras random sobre el array del abecedario y lo cargamos en su array correspondiente:
		for(int i = 0; i < 30; i++){
			int randnum = rand.nextInt(26);
			char letra = abc.get(randnum);
			FindCharSec valor = new FindCharSec(text,letra);
			taskList.add(valor);
		}
		
		//creamos un array para contar los resultados, y recorremos en bucle la lista de objetos FindCharSec para ver cuantas veces esta cada letra
		//aplicando el metodo que le creemos para contarlas:
		List<Integer> resultList = new ArrayList<Integer>();
		for(FindCharSec cis : taskList){
			resultList.add(cis.vecesLetra());
		}
		
		//y sacamos los datos por pantalla:
		for (int i = 0; i < resultList.size(); i++) {    
			int resultado = resultList.get(i);     
			System.out.println("Proceso : "+(i+1)+ " Letra " + taskList.get(i).getLetra() + " tiene " + resultado + " ocurrencias.");
		}
	}
}