import java.util.ArrayList;

public class BuscaProfundidade {
	private int[] posicaoAtual = new int[2];
	private ArrayList<ArrayList<Integer>> caminho = new ArrayList<>();
	
	public void definirPosInicial(char m[][], int[] posInicio)
	{
		posicaoAtual[0]=posInicio[0];
		posicaoAtual[1]=posInicio[1];
		return;
	}
	
	public void imprimirCaminho(char m[][])
	{
		for(int i=0;i<caminho.size();i++)
		{
			m[caminho.get(i).get(0)][caminho.get(i).get(1)]='c';
			System.out.print("("+caminho.get(i).get(0)+","+caminho.get(i).get(1)+") ");
		}
		System.out.println("");
	}
	
	public void buscaProfundidade(char m[][], int[] posInicio)
	{
		m[posicaoAtual[0]][posicaoAtual[1]]='+'; //posicao inicial e marcada com +
		ArrayList<Integer> novoElemento = new ArrayList<>();
		novoElemento.add(posicaoAtual[0]);
		novoElemento.add(posicaoAtual[1]);
		caminho.add(novoElemento); // adiciona o comeco na lista de caminho
		
		while(m[posicaoAtual[0]][posicaoAtual[1]]!='$')
		{
			if(Manip_matriz.getCima(m, posicaoAtual[0], posicaoAtual[1])=='*' || Manip_matriz.getCima(m, posicaoAtual[0], posicaoAtual[1])=='$') // Se cima esta disponivel
			{
				posicaoAtual=Manip_matriz.andaCima(m, posicaoAtual[0], posicaoAtual[1]);
				ArrayList<Integer> n = new ArrayList<>();
				n.add(posicaoAtual[0]);
				n.add(posicaoAtual[1]);
				caminho.add(n);
				if(m[posicaoAtual[0]][posicaoAtual[1]]!='$')
				{
					m[posicaoAtual[0]][posicaoAtual[1]]='+';
				}
				else
				{
					break;
				}
				
			}
			else
			{
				if(Manip_matriz.getDireita(m, posicaoAtual[0], posicaoAtual[1],m[0].length)=='*' || Manip_matriz.getDireita(m, posicaoAtual[0], posicaoAtual[1],m[0].length)=='$') // Se direita esta disponivel
				{
					posicaoAtual=Manip_matriz.andaDireita(m, posicaoAtual[0], posicaoAtual[1]);
					ArrayList<Integer> n = new ArrayList<>();
					n.add(posicaoAtual[0]);
					n.add(posicaoAtual[1]);
					if(!caminho.contains(n))
					{
						caminho.add(n);
					}
					
					if(m[posicaoAtual[0]][posicaoAtual[1]]!='$')
					{
						m[posicaoAtual[0]][posicaoAtual[1]]='+';
					}
					else
					{
						break;
					}
					
				}
				else
				{
					if(Manip_matriz.getBaixo(m, posicaoAtual[0], posicaoAtual[1])=='*' || Manip_matriz.getBaixo(m, posicaoAtual[0], posicaoAtual[1])=='$') // Se baixo esta disponivel
					{
						posicaoAtual=Manip_matriz.andaBaixo(m, posicaoAtual[0], posicaoAtual[1]);
						ArrayList<Integer> n = new ArrayList<>();
						n.add(posicaoAtual[0]);
						n.add(posicaoAtual[1]);
						caminho.add(n);
						
						if(m[posicaoAtual[0]][posicaoAtual[1]]!='$')
						{
							m[posicaoAtual[0]][posicaoAtual[1]]='+';
						}
						else
						{
							break;
						}
						
					}
					else
					{
						if(Manip_matriz.getEsquerda(m, posicaoAtual[0], posicaoAtual[1])=='*' || Manip_matriz.getEsquerda(m, posicaoAtual[0], posicaoAtual[1])=='$') // Se esquerda esta disponivel
						{
							posicaoAtual=Manip_matriz.andaEsquerda(m, posicaoAtual[0], posicaoAtual[1]);
							ArrayList<Integer> n = new ArrayList<>();
							n.add(posicaoAtual[0]);
							n.add(posicaoAtual[1]);
							caminho.add(n);
							
							if(m[posicaoAtual[0]][posicaoAtual[1]]!='$')
							{
								m[posicaoAtual[0]][posicaoAtual[1]]='+';
							}
							else
							{
								break;
							}
						}
						else
						{  //Nao pode andar para nenhum lugar
							posicaoAtual[0]=caminho.get(caminho.size()-2).get(0); //posicao atual vira a anterior da ultima.
							posicaoAtual[1]=caminho.get(caminho.size()-2).get(1); //posicao atual vira a anterior da ultima.
							caminho.remove(caminho.size()-1);
							
						}
					}
				}
			}
		}
	}
	
	
}
