import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.Scanner;

public class Programa {
	
	private static long tempoInicio;
	private static long tempoFim;
	public static DecimalFormat df = new DecimalFormat("0.000000");

	public static void main(String[] args){
		Scanner sc = new Scanner(System.in); 
		Scanner scOp = new Scanner(System.in);
		System.out.println("1-Busca em Profundidade.");
		System.out.println("2-Busca em Largura.");
		System.out.println("3-Busca Best-First Search.");
		System.out.println("4-Busca A*.");
		System.out.println("5-Hill Climbing.");
		System.out.println("Digite uma opção de 1 a 5: ");
		String opEntrada = scOp.nextLine();
		int op = Integer.parseInt(opEntrada);
		
		System.out.println("Digite o nome do arquivo\n(nome_do_arquivo.txt): ");
		String path = sc.nextLine();
		char matriz[][] = null;
		
		try
		{
			 matriz=Manip_arquivos.geraMatriz(path);
		}
		catch (FileNotFoundException e1)
		{
			System.out.println("Arquivo não encontrado. Nome incorreto ou Diretório inválido.");
		}
		catch (IOException e2)
		{
			System.out.println("Erro na abertura do arquivo.");
		}
		
		int[] posicaoInicial = new int[2];
		posicaoInicial[0]=Manip_matriz.getLinhaPosInicial(matriz);
		posicaoInicial[1]=Manip_matriz.getColunaPosInicial(matriz);
		int[] posicaoFinal = new int[2];
		posicaoFinal[0]=Manip_matriz.getLinhaPosFinal(matriz);
		posicaoFinal[1]=Manip_matriz.getColunaPosFinal(matriz);
		
		switch(op)
		{
		case 1:
			tempoInicio=System.nanoTime();
			BuscaProfundidade bp = new BuscaProfundidade();
			bp.definirPosInicial(matriz,posicaoInicial);
			bp.buscaProfundidade(matriz,posicaoInicial);
			tempoFim=System.nanoTime();
			System.out.println("Tempo de execução: "+df.format((tempoFim-tempoInicio)*Math.pow(10,-6))+"ms");
			System.out.println("\nCaminho encontrado:");
			bp.imprimirCaminho(matriz);
			break;
			
		case 2:
			tempoInicio=System.nanoTime();
			BuscaLargura bl = new BuscaLargura();
			bl.definirPosInicial(matriz,posicaoInicial);
			bl.buscaLargura(matriz, posicaoInicial);
			tempoFim=System.nanoTime();
			System.out.println("Tempo de execução: "+df.format((tempoFim-tempoInicio)*Math.pow(10,-6))+"ms");
			System.out.println("\nCaminho encontrado:");
			bl.imprimirCaminho(matriz);
			break;
			
		case 3:
			tempoInicio=System.nanoTime();
			BestFirstSearch bfs = new BestFirstSearch();
			bfs.definirPosInicial(matriz,posicaoInicial);
			bfs.definirPosFinal(matriz,posicaoFinal);
			bfs.buscaBFS(matriz, posicaoInicial);
			tempoFim=System.nanoTime();
			System.out.println("Tempo de execução: "+df.format((tempoFim-tempoInicio)*Math.pow(10,-6))+"ms");
			System.out.println("\nCaminho encontrado:");
			bfs.imprimirCaminho(matriz);
			break;
			
		case 4:
			tempoInicio=System.nanoTime();
			AEstrela bAestrela = new AEstrela();
			bAestrela.definirPosInicial(matriz,posicaoInicial);
			bAestrela.definirInicio(matriz,posicaoInicial);
			bAestrela.definirPosFinal(matriz,posicaoFinal);
			bAestrela.buscaAEstrela(matriz, posicaoInicial);
			tempoFim=System.nanoTime();
			System.out.println("Tempo de execução: "+df.format((tempoFim-tempoInicio)*Math.pow(10,-6))+"ms");
			System.out.println("\nCaminho encontrado:");
			bAestrela.imprimirCaminho(matriz);
			break;
			
		case 5:
			tempoInicio=System.nanoTime();
			HillClimbing hc = new HillClimbing();
			hc.definirPosInicial(matriz,posicaoInicial);
			hc.definirPosFinal(matriz,posicaoFinal);
			hc.buscaHC(matriz, posicaoInicial);
			tempoFim=System.nanoTime();
			System.out.println("Tempo de execução: "+df.format((tempoFim-tempoInicio)*Math.pow(10,-6))+"ms");
			System.out.println("\nCaminho encontrado:");
			hc.imprimirCaminho(matriz);
			break;
			
		}
		
		//Imprime matriz processada
		System.out.println("\nMatriz e celulas exploradas(+): ");
		for(int i=0;i<matriz.length;i++)
		{
			for(int j=0;j<matriz[0].length;j++)
			{
				System.out.print(matriz[i][j]);
			}
			System.out.println("");
		}
		sc.close();
		scOp.close();
	}
	
}