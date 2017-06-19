package threads.Ex4;
/*Crear la versi� seq�encial del punt 3. (No cal calcular si el 3 �s m�s r�pid)
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
		String text = "En la margen suroeste de la selva amaz�nica, el primer lunes de la primavera, naci� Tinkus. A diferencia de los otros camaleones "
				+ "beb�s de la maternidad, �l no pod�a cambiar de color. Sin embargo, por muy evidente que eso fuese, sus padres no se dieron cuenta. Quiz� "
				+ "estaban tan felices por traer un hijo al mundo que les imped�a ver cualquier defecto. O quiz� fue otra la raz�n, porque no s�lo ellos lo "
				+ "pasaron por alto, sino tambi�n la matrona, los enfermeros, las pacientes y las visitas. Lo m�s probable es que haya sido la costumbre. "
				+ "Mimetizarse con el entorno estaba tan asumido como respirar. S�lo hac�an ciertas referencias al color cuando necesitaban indicar la ubicaci�n "
				+ "de alg�n amigo o pariente.";
				
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