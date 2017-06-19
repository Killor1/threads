package threads.Ex4;

import java.util.concurrent.Callable;

public class FindCharSec{

	private String texto;
	private char letra;
	//Constructor
	public FindCharSec(String texto, char letra) {
		this.texto = texto;
		this.letra = letra;
	}
	//Getter
	public char getLetra() {
		return letra;
	}
	//setter
	public void setLetra(char letra) {
		this.letra = letra;
	}
	//hacemos el mismo proceso del call, pero de forma secuencial:
	public Integer vecesLetra(){
		
		char frase[] = this.texto.toCharArray();
		int cont = 0;
		
		for(char c : frase){
			if(this.letra == c){
				cont = cont+1;;
			}
		}		
		return cont;
	}

}