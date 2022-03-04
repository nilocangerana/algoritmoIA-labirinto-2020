import java.util.ArrayList;

public class AEstrela {
	private int[] posicaoAtual = new int[2];
	private int[] posicaoProxima = new int[2];
	private int[] posicaoFinal = new int[2];
	private int[] posicaoInicial = new int[2];
	private ArrayList<ArrayList<Integer>> filaOpen = new ArrayList<>();
	private ArrayList<ArrayList<Integer>> filaClosed = new ArrayList<>();
	//x[0],   y[1],  xanterior[2],    yanterior[3]   G->inicio/atual(manhattan)[4],   H->atual/fim(manhattan)[5],    F->G+H[6]
	public void definirPosInicial(char m[][], int[] posInicio)
	{
		posicaoAtual[0]=posInicio[0];
		posicaoAtual[1]=posInicio[1];
		return;
	}
	
	public void definirInicio(char m[][], int[] posInicio)
	{
		posicaoInicial[0]=posInicio[0];
		posicaoInicial[1]=posInicio[1];
		return;
	}
	
	public void definirPosFinal(char m[][], int[] posFim)
	{
		posicaoFinal[0]=posFim[0];
		posicaoFinal[1]=posFim[1];
		return;
	}
	public int calculaF(int g, int h)
	{
		return g+h;
	}
	
	public int calculaManhattanDistance(int xposAtual,int yposAtual,int xposFim,int yposFim)
	{
		return Math.abs(xposFim-xposAtual)+Math.abs(yposFim-yposAtual);
	}
	
	public int getMenorF()
	{
		int minFindex=0;
		int minF=100000000;
		int repeticoesF=0;
		int minH=100000000;
		
		for(int i=0;i<filaOpen.size();i++)
		{
			if(filaOpen.get(i).get(6)<minF)
			{
				minF=filaOpen.get(i).get(6);
				minFindex=i;
			}
			else
			{
				if(filaOpen.get(i).get(6)==minF)
				{
					minF=filaOpen.get(i).get(6);
					minFindex=i;
					repeticoesF++;
				}
			}
		}
		
		if(repeticoesF>0)
		{
			for(int j=0;j<filaOpen.size();j++)
			{
				if(filaOpen.get(j).get(6)==minF && filaOpen.get(j).get(5)<=minH)
				{
					minH=filaOpen.get(j).get(5);
					minFindex=j;
				}
			}
			return minFindex;
		}
		else
		{
			return minFindex;
		}
	}

	
	public int procuraVetor(int x,int y)
	{
		for(int i=0;i<filaClosed.size();i++)
		{
			if(x==filaClosed.get(i).get(0) && y==filaClosed.get(i).get(1))
			{
				return i;
			}
		}
		return 0;
	}
	
	public void imprimirCaminho(char m[][])
	{
		ArrayList<ArrayList<Integer>> saida = new ArrayList<>();
		int retorno=filaClosed.size()-1;
		
		while(retorno>0)
		{
			saida.add(filaClosed.get(retorno));
			retorno = procuraVetor(filaClosed.get(retorno).get(2),filaClosed.get(retorno).get(3));
		}
		saida.add(filaClosed.get(0));
		for(int j=saida.size()-1;j>=0;j--)
		{
			m[saida.get(j).get(0)][saida.get(j).get(1)]='c';
			System.out.print("("+saida.get(j).get(0)+","+saida.get(j).get(1)+") ");
		}
		System.out.println("");
	}
	
	public void trocaPosicaoFila(int minimoIndex)
	{
		if(minimoIndex!=0)
		{
			ArrayList<Integer> troca = new ArrayList<>();
			troca.add(filaOpen.get(minimoIndex).get(0));
			troca.add(filaOpen.get(minimoIndex).get(1));
			troca.add(filaOpen.get(minimoIndex).get(2));
			troca.add(filaOpen.get(minimoIndex).get(3));
			troca.add(filaOpen.get(minimoIndex).get(4));
			troca.add(filaOpen.get(minimoIndex).get(5));
			troca.add(filaOpen.get(minimoIndex).get(6));
			
			filaOpen.get(minimoIndex).remove(6);
			filaOpen.get(minimoIndex).remove(5);
			filaOpen.get(minimoIndex).remove(4);
			filaOpen.get(minimoIndex).remove(3);
			filaOpen.get(minimoIndex).remove(2);
			filaOpen.get(minimoIndex).remove(1);
			filaOpen.get(minimoIndex).remove(0);
			
			filaOpen.get(minimoIndex).add(filaOpen.get(0).get(0));
			filaOpen.get(minimoIndex).add(filaOpen.get(0).get(1));
			filaOpen.get(minimoIndex).add(filaOpen.get(0).get(2));
			filaOpen.get(minimoIndex).add(filaOpen.get(0).get(3));
			filaOpen.get(minimoIndex).add(filaOpen.get(0).get(4));
			filaOpen.get(minimoIndex).add(filaOpen.get(0).get(5));
			filaOpen.get(minimoIndex).add(filaOpen.get(0).get(6));
			
			filaOpen.get(0).remove(6);
			filaOpen.get(0).remove(5);
			filaOpen.get(0).remove(4);
			filaOpen.get(0).remove(3);
			filaOpen.get(0).remove(2);
			filaOpen.get(0).remove(1);
			filaOpen.get(0).remove(0);
			
			filaOpen.get(0).add(troca.get(0));
			filaOpen.get(0).add(troca.get(1));
			filaOpen.get(0).add(troca.get(2));
			filaOpen.get(0).add(troca.get(3));
			filaOpen.get(0).add(troca.get(4));
			filaOpen.get(0).add(troca.get(5));
			filaOpen.get(0).add(troca.get(6));
		}
	}
	
	public void buscaAEstrela(char m[][], int[] posInicio)
	{
		int flag=1;
		ArrayList<Integer> novoElemento = new ArrayList<>();
		novoElemento.add(posicaoAtual[0]);
		novoElemento.add(posicaoAtual[1]);
		novoElemento.add(-1);
		novoElemento.add(-1);
		novoElemento.add(calculaManhattanDistance(posicaoAtual[0],posicaoAtual[1],posicaoInicial[0],posicaoInicial[1]));
		novoElemento.add(calculaManhattanDistance(posicaoAtual[0],posicaoAtual[1],posicaoFinal[0],posicaoFinal[1]));
		novoElemento.add(calculaF(novoElemento.get(4),novoElemento.get(5)));
		filaOpen.add(novoElemento);
		
		while(flag==1)
		{
			trocaPosicaoFila(getMenorF());
			
			posicaoAtual[0]=filaOpen.get(0).get(0);	//poe a posicao atual com o valor do inicio da fila
			posicaoAtual[1]=filaOpen.get(0).get(1);
			
			if(Manip_matriz.getCima(m, filaOpen.get(0).get(0), filaOpen.get(0).get(1))=='*' || Manip_matriz.getCima(m, filaOpen.get(0).get(0), filaOpen.get(0).get(1))=='$') // Compara o quadrado de cima ao valor da cabeca da fila
			{
				posicaoProxima=Manip_matriz.andaCima(m, posicaoAtual[0], posicaoAtual[1]);
				ArrayList<Integer> n = new ArrayList<>();
				n.add(posicaoProxima[0]);
				n.add(posicaoProxima[1]);
				n.add(posicaoAtual[0]);
				n.add(posicaoAtual[1]);
				n.add(calculaManhattanDistance(posicaoProxima[0],posicaoProxima[1],posicaoInicial[0],posicaoInicial[1]));
				n.add(calculaManhattanDistance(posicaoProxima[0],posicaoProxima[1],posicaoFinal[0],posicaoFinal[1]));
				n.add(calculaF(n.get(4),n.get(5)));
				
				if(!filaOpen.contains(n))
				{
					filaOpen.add(n);
				}
				if(Manip_matriz.getValor(m, posicaoProxima[0], posicaoProxima[1])=='$')
				{
					filaClosed.add(filaOpen.get(0));
					m[filaOpen.get(0).get(0)][filaOpen.get(0).get(1)]='+';
					//Se o simbolo da fila é o final
					ArrayList<Integer> fim = new ArrayList<>();
					fim.add(posicaoProxima[0]);
					fim.add(posicaoProxima[1]);
					fim.add(posicaoAtual[0]);
					fim.add(posicaoAtual[1]);
					fim.add(calculaManhattanDistance(posicaoProxima[0],posicaoProxima[1],posicaoInicial[0],posicaoInicial[1]));
					fim.add(calculaManhattanDistance(posicaoProxima[0],posicaoProxima[1],posicaoFinal[0],posicaoFinal[1]));
					fim.add(calculaF(fim.get(4),fim.get(5)));
					filaClosed.add(fim);
					break;
				}
				
			}
			
			if(Manip_matriz.getDireita(m, filaOpen.get(0).get(0), filaOpen.get(0).get(1),m[0].length)=='*' || Manip_matriz.getDireita(m, filaOpen.get(0).get(0), filaOpen.get(0).get(1),m[0].length)=='$') // Compara o quadrado da direita ao valor da cabeca da fila
			{
				posicaoProxima=Manip_matriz.andaDireita(m, posicaoAtual[0], posicaoAtual[1]);
				ArrayList<Integer> n = new ArrayList<>();
				n.add(posicaoProxima[0]);
				n.add(posicaoProxima[1]);
				n.add(posicaoAtual[0]);
				n.add(posicaoAtual[1]);
				n.add(calculaManhattanDistance(posicaoProxima[0],posicaoProxima[1],posicaoInicial[0],posicaoInicial[1]));
				n.add(calculaManhattanDistance(posicaoProxima[0],posicaoProxima[1],posicaoFinal[0],posicaoFinal[1]));
				n.add(calculaF(n.get(4),n.get(5)));
				if(!filaOpen.contains(n))
				{
					filaOpen.add(n);
				}
				if(Manip_matriz.getValor(m, posicaoProxima[0], posicaoProxima[1])=='$')
				{
					filaClosed.add(filaOpen.get(0));
					m[filaOpen.get(0).get(0)][filaOpen.get(0).get(1)]='+';
					//Se o simbolo da fila é o final
					ArrayList<Integer> fim = new ArrayList<>();
					fim.add(posicaoProxima[0]);
					fim.add(posicaoProxima[1]);
					fim.add(posicaoAtual[0]);
					fim.add(posicaoAtual[1]);
					fim.add(calculaManhattanDistance(posicaoProxima[0],posicaoProxima[1],posicaoInicial[0],posicaoInicial[1]));
					fim.add(calculaManhattanDistance(posicaoProxima[0],posicaoProxima[1],posicaoFinal[0],posicaoFinal[1]));
					fim.add(calculaF(fim.get(4),fim.get(5)));
					filaClosed.add(fim);
					break;
				}
				
			}
			
			if(Manip_matriz.getBaixo(m, filaOpen.get(0).get(0), filaOpen.get(0).get(1))=='*' || Manip_matriz.getBaixo(m, filaOpen.get(0).get(0), filaOpen.get(0).get(1))=='$') // Compara o quadrado de baixo ao valor da cabeca da fila
			{
				posicaoProxima=Manip_matriz.andaBaixo(m, posicaoAtual[0], posicaoAtual[1]);
				ArrayList<Integer> n = new ArrayList<>();
				n.add(posicaoProxima[0]);
				n.add(posicaoProxima[1]);
				n.add(posicaoAtual[0]);
				n.add(posicaoAtual[1]);
				n.add(calculaManhattanDistance(posicaoProxima[0],posicaoProxima[1],posicaoInicial[0],posicaoInicial[1]));
				n.add(calculaManhattanDistance(posicaoProxima[0],posicaoProxima[1],posicaoFinal[0],posicaoFinal[1]));
				n.add(calculaF(n.get(4),n.get(5)));
				if(!filaOpen.contains(n))
				{
					filaOpen.add(n);
				}
				if(Manip_matriz.getValor(m, posicaoProxima[0], posicaoProxima[1])=='$')
				{
					filaClosed.add(filaOpen.get(0));
					m[filaOpen.get(0).get(0)][filaOpen.get(0).get(1)]='+';
					//Se o simbolo da fila é o final
					ArrayList<Integer> fim = new ArrayList<>();
					fim.add(posicaoProxima[0]);
					fim.add(posicaoProxima[1]);
					fim.add(posicaoAtual[0]);
					fim.add(posicaoAtual[1]);
					fim.add(calculaManhattanDistance(posicaoProxima[0],posicaoProxima[1],posicaoInicial[0],posicaoInicial[1]));
					fim.add(calculaManhattanDistance(posicaoProxima[0],posicaoProxima[1],posicaoFinal[0],posicaoFinal[1]));
					fim.add(calculaF(fim.get(4),fim.get(5)));
					filaClosed.add(fim);
					break;
				}
			}
			
			if(Manip_matriz.getEsquerda(m, filaOpen.get(0).get(0), filaOpen.get(0).get(1))=='*' || Manip_matriz.getEsquerda(m, filaOpen.get(0).get(0), filaOpen.get(0).get(1))=='$') // Compara o quadrado da esquerda ao valor da cabeca da fila
			{
				posicaoProxima=Manip_matriz.andaEsquerda(m, posicaoAtual[0], posicaoAtual[1]);
				ArrayList<Integer> n = new ArrayList<>();
				n.add(posicaoProxima[0]);
				n.add(posicaoProxima[1]);
				n.add(posicaoAtual[0]);
				n.add(posicaoAtual[1]);
				n.add(calculaManhattanDistance(posicaoProxima[0],posicaoProxima[1],posicaoInicial[0],posicaoInicial[1]));
				n.add(calculaManhattanDistance(posicaoProxima[0],posicaoProxima[1],posicaoFinal[0],posicaoFinal[1]));
				n.add(calculaF(n.get(4),n.get(5)));
				if(!filaOpen.contains(n))
				{
					filaOpen.add(n);
				}
				if(Manip_matriz.getValor(m, posicaoProxima[0], posicaoProxima[1])=='$')
				{
					filaClosed.add(filaOpen.get(0));
					m[filaOpen.get(0).get(0)][filaOpen.get(0).get(1)]='+';
					//Se o simbolo da fila é o final
					ArrayList<Integer> fim = new ArrayList<>();
					fim.add(posicaoProxima[0]);
					fim.add(posicaoProxima[1]);
					fim.add(posicaoAtual[0]);
					fim.add(posicaoAtual[1]);
					fim.add(calculaManhattanDistance(posicaoProxima[0],posicaoProxima[1],posicaoInicial[0],posicaoInicial[1]));
					fim.add(calculaManhattanDistance(posicaoProxima[0],posicaoProxima[1],posicaoFinal[0],posicaoFinal[1]));
					fim.add(calculaF(fim.get(4),fim.get(5)));
					filaClosed.add(fim);
					break;
				}
			}
			
			if(m[filaOpen.get(0).get(0)][filaOpen.get(0).get(1)]!='$')
			{
				m[filaOpen.get(0).get(0)][filaOpen.get(0).get(1)]='+'; //marca o simbolo
			}

			filaClosed.add(filaOpen.get(0));
			filaOpen.remove(0);
		}
	}
}
