import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Manip_arquivos {
	
	public static char[][] geraMatriz(String path) throws IOException { //Lê o arquivo de entrada
        BufferedReader buffRead = new BufferedReader(new FileReader(path));
        String linha = "";
        String[] dimensao = null;
        int i=0;
        int j=0;
        char matriz[][];
        
        linha = buffRead.readLine(); //le a primeira linha do arquivo para instanciacao da matriz
        if (linha != null) {
        	dimensao=linha.split(" ");
        }
        matriz= new char[Integer.parseInt(dimensao[0])][Integer.parseInt(dimensao[1])]; //cria a matriz de dimensao passada
        
        while (true) {
        	linha = buffRead.readLine();
            if (linha != null) {
            	while(j<Integer.parseInt(dimensao[1]))
            	{
            		matriz[i][j]=linha.charAt(j);
            		j++;
            	}
            	i++;
            	j=0;
            } else
                break;
        }
        buffRead.close();
        return matriz;
    }
	
}
