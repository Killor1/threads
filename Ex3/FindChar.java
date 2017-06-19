package threads.Ex3;

import java.util.concurrent.Callable;
//la clase que buscara los caracteres, implementara Callable<integer> por que lo que devuelve es un contador de veces que 
//encuentra la letra, y sus variables seran el texto, y la letra a buscar:
public class FindChar implements Callable<Integer>{

	private String texto;
	private char letra;	
	//Constructor:
	public FindChar(String texto, char letra) {
		super();
		this.texto = texto;
		this.letra = letra;
	}
	//Getter
	public char getLetra() {
		return letra;
	}
	//Setter
	public void setLetra(char letra) {
		this.letra = letra;
	}
	//y el override del runnable
	@Override
	public Integer call() throws Exception {
		
		//pasamos el texto que recibe la clase a un array de chars:
		char frase[] = this.texto.toCharArray();
		//e iniciamos el contador a cero:
		int cont = 0;
		
		//y ahora con un for recorremos el array de chars para ver que coincida con la letra en cuestion, en ese caso aumentamos contador.
		for(char c : frase){
			if(this.letra == c){
				cont =cont+1;;
			}
		}
		//y lo devolvemos el contador:
		return cont;
	}
}