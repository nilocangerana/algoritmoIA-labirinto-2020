import java.util.ArrayList;

public class BestFirstSearch {
	private int[] posicaoAtual = new int[2];
	private int[] posicaoProxima = new int[2];
	private int[] posicaoFinal = new int[2];
	private ArrayList<ArrayList<Integer>> nosPercorridos = new ArrayList<>();
	private ArrayList<ArrayList<Integer>> fila = new ArrayList<>();
	
	public void definirPosInicial(char m[][], int[] posInicio)
	{
		posicaoAtual[0]=posInicio[0];
		posicaoAtual[1]=posInicio[1];
		return;
	}
	
	public void definirPosFinal(char m[][], int[] posFim)
	{
		posicaoFinal[0]=posFim[0];
		posicaoFinal[1]=posFim[1];
		return;
	}
	
	public int calculaManhattanDistance(int xposAtual,int yposAtual,int xposFim,int yposFim)
	{
		return Math.abs(xposFim-xposAtual)+Math.abs(yposFim-yposAtual);
	}
	
	public int menorManhattanDistance()
	{
		int minimo=100000000;
		int minimoIndex=0;
		int mDistance;
		
		for(int i=0;i<fila.size();i++)
		{
			mDistance=calculaManhattanDistance(fila.get(i).get(0),fila.get(i).get(1),posicaoFinal[0],posicaoFinal[1]);
			if(mDistance<=minimo)
			{
				minimo=mDistance;
				minimoIndex=i;
			}
		}
		return minimoIndex;
	}
	
	public void trocaPosicaoFila(int minimoIndex)
	{
		if(minimoIndex!=0)
		{
			ArrayList<Integer> troca = new ArrayList<>();
			troca.add(fila.get(minimoIndex).get(0));
			troca.add(fila.get(minimoIndex).get(1));
			troca.add(fila.get(minimoIndex).get(2));
			troca.add(fila.get(minimoIndex).get(3));
			
			fila.get(minimoIndex).remove(3);
			fila.get(minimoIndex).remove(2);
			fila.get(minimoIndex).remove(1);
			fila.get(minimoIndex).remove(0);
			
			fila.get(minimoIndex).add(fila.get(0).get(0));
			fila.get(minimoIndex).add(fila.get(0).get(1));
			fila.get(minimoIndex).add(fila.get(0).get(2));
			fila.get(minimoIndex).add(fila.get(0).get(3));
			
			fila.get(0).remove(3);
			fila.get(0).remove(2);
			fila.get(0).remove(1);
			fila.get(0).remove(0);
			
			fila.get(0).add(troca.get(0));
			fila.get(0).add(troca.get(1));
			fila.get(0).add(troca.get(2));
			fila.get(0).add(troca.get(3));
		}
	}
	
	public int procuraVetor(int x,int y)
	{
		for(int i=0;i<nosPercorridos.size();i++)
		{
			if(x==nosPercorridos.get(i).get(0) && y==nosPercorridos.get(i).get(1))
			{
				return i;
			}
		}
		return 0;
	}
	
	
	public void imprimirCaminho(char m[][])
	{
		ArrayList<ArrayList<Integer>> saida = new ArrayList<>();
		int retorno=nosPercorridos.size()-1;
		
		while(retorno>0)
		{
			saida.add(nosPercorridos.get(retorno));
			retorno = procuraVetor(nosPercorridos.get(retorno).get(2),nosPercorridos.get(retorno).get(3));
		}
		saida.add(nosPercorridos.get(0));
		for(int j=saida.size()-1;j>=0;j--)
		{
			m[saida.get(j).get(0)][saida.get(j).get(1)]='c';
			System.out.print("("+saida.get(j).get(0)+","+saida.get(j).get(1)+") ");
		}
		System.out.println("");
	}
	
	public void buscaBFS(char m[][], int[] posInicio)
	{
		int flag=1;
		ArrayList<Integer> novoElemento = new ArrayList<>();
		novoElemento.add(posicaoAtual[0]);
		novoElemento.add(posicaoAtual[1]);
		novoElemento.add(-1);
		novoElemento.add(-1);
		fila.add(novoElemento);
		
		while(flag==1)
		{
			trocaPosicaoFila(menorManhattanDistance());
			
			posicaoAtual[0]=fila.get(0).get(0);	//poe a posicao atual com o valor do inicio da fila
			posicaoAtual[1]=fila.get(0).get(1);
			
			if(Manip_matriz.getCima(m, fila.get(0).get(0), fila.get(0).get(1))=='*' || Manip_matriz.getCima(m, fila.get(0).get(0), fila.get(0).get(1))=='$') // Compara o quadrado de cima ao valor da cabeca da fila
			{
				posicaoProxima=Manip_matriz.andaCima(m, posicaoAtual[0], posicaoAtual[1]);
				ArrayList<Integer> n = new ArrayList<>();
				n.add(posicaoProxima[0]);
				n.add(posicaoProxima[1]);
				n.add(posicaoAtual[0]);
				n.add(posicaoAtual[1]);
				if(!fila.contains(n))
				{
					fila.add(n);
				}
				if(Manip_matriz.getValor(m, posicaoProxima[0], posicaoProxima[1])=='$')
				{
					nosPercorridos.add(fila.get(0));
					m[fila.get(0).get(0)][fila.get(0).get(1)]='+';
					//Se o simbolo da fila é o final
					ArrayList<Integer> fim = new ArrayList<>();
					fim.add(posicaoProxima[0]);
					fim.add(posicaoProxima[1]);
					fim.add(posicaoAtual[0]);
					fim.add(posicaoAtual[1]);
					nosPercorridos.add(fim);
					break;
				}
				
			}
			
			if(Manip_matriz.getDireita(m, fila.get(0).get(0), fila.get(0).get(1),m[0].length)=='*' || Manip_matriz.getDireita(m, fila.get(0).get(0), fila.get(0).get(1),m[0].length)=='$') // Compara o quadrado da direita ao valor da cabeca da fila
			{
				posicaoProxima=Manip_matriz.andaDireita(m, posicaoAtual[0], posicaoAtual[1]);
				ArrayList<Integer> n = new ArrayList<>();
				n.add(posicaoProxima[0]);
				n.add(posicaoProxima[1]);
				n.add(posicaoAtual[0]);
				n.add(posicaoAtual[1]);
				if(!fila.contains(n))
				{
					fila.add(n);
				}
				if(Manip_matriz.getValor(m, posicaoProxima[0], posicaoProxima[1])=='$')
				{
					nosPercorridos.add(fila.get(0));
					m[fila.get(0).get(0)][fila.get(0).get(1)]='+';
					//Se o simbolo da fila é o final
					ArrayList<Integer> fim = new ArrayList<>();
					fim.add(posicaoProxima[0]);
					fim.add(posicaoProxima[1]);
					fim.add(posicaoAtual[0]);
					fim.add(posicaoAtual[1]);
					nosPercorridos.add(fim);
					break;
				}
				
			}
			
			if(Manip_matriz.getBaixo(m, fila.get(0).get(0), fila.get(0).get(1))=='*' || Manip_matriz.getBaixo(m, fila.get(0).get(0), fila.get(0).get(1))=='$') // Compara o quadrado de baixo ao valor da cabeca da fila
			{
				posicaoProxima=Manip_matriz.andaBaixo(m, posicaoAtual[0], posicaoAtual[1]);
				ArrayList<Integer> n = new ArrayList<>();
				n.add(posicaoProxima[0]);
				n.add(posicaoProxima[1]);
				n.add(posicaoAtual[0]);
				n.add(posicaoAtual[1]);
				if(!fila.contains(n))
				{
					fila.add(n);
				}
				if(Manip_matriz.getValor(m, posicaoProxima[0], posicaoProxima[1])=='$')
				{
					nosPercorridos.add(fila.get(0));
					m[fila.get(0).get(0)][fila.get(0).get(1)]='+';
					//Se o simbolo da fila é o final
					ArrayList<Integer> fim = new ArrayList<>();
					fim.add(posicaoProxima[0]);
					fim.add(posicaoProxima[1]);
					fim.add(posicaoAtual[0]);
					fim.add(posicaoAtual[1]);
					nosPercorridos.add(fim);
					break;
				}
			}
			
			if(Manip_matriz.getEsquerda(m, fila.get(0).get(0), fila.get(0).get(1))=='*' || Manip_matriz.getEsquerda(m, fila.get(0).get(0), fila.get(0).get(1))=='$') // Compara o quadrado da esquerda ao valor da cabeca da fila
			{
				posicaoProxima=Manip_matriz.andaEsquerda(m, posicaoAtual[0], posicaoAtual[1]);
				ArrayList<Integer> n = new ArrayList<>();
				n.add(posicaoProxima[0]);
				n.add(posicaoProxima[1]);
				n.add(posicaoAtual[0]);
				n.add(posicaoAtual[1]);
				if(!fila.contains(n))
				{
					fila.add(n);
				}
				if(Manip_matriz.getValor(m, posicaoProxima[0], posicaoProxima[1])=='$')
				{
					nosPercorridos.add(fila.get(0));
					m[fila.get(0).get(0)][fila.get(0).get(1)]='+';
					//Se o simbolo da fila é o final
					ArrayList<Integer> fim = new ArrayList<>();
					fim.add(posicaoProxima[0]);
					fim.add(posicaoProxima[1]);
					fim.add(posicaoAtual[0]);
					fim.add(posicaoAtual[1]);
					nosPercorridos.add(fim);
					break;
				}
			}
			
			if(m[fila.get(0).get(0)][fila.get(0).get(1)]!='$')
			{
				m[fila.get(0).get(0)][fila.get(0).get(1)]='+'; //marca o simbolo
			}

			nosPercorridos.add(fila.get(0));
			fila.remove(0);
		}
	}
	
}
