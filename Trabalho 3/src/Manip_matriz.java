
public class Manip_matriz {
	
	public static int getLinhaPosInicial(char [][] m) //retorna linha da posicao inicial
	{
		for(int i=0;i<m.length;i++)
		{
			for(int j=0;j<m[0].length;j++)
			{
				if(m[i][j]=='#')
				{
					return i;
				}
			}
		}
		
		return 0;
	}
	
	public static int getColunaPosInicial(char [][] m) //retorna coluna da posicao inicial
	{
		for(int i=0;i<m.length;i++)
		{
			for(int j=0;j<m[0].length;j++)
			{
				if(m[i][j]=='#')
				{
					return j;
				}
			}
		}
		
		return 0;
	}
	
	public static int getLinhaPosFinal(char [][] m) //retorna linha da posicao final
	{
		for(int i=0;i<m.length;i++)
		{
			for(int j=0;j<m[0].length;j++)
			{
				if(m[i][j]=='$')
				{
					return i;
				}
			}
		}
		
		return 0;
	}
	
	public static int getColunaPosFinal(char [][] m) //retorna coluna da posicao final
	{
		for(int i=0;i<m.length;i++)
		{
			for(int j=0;j<m[0].length;j++)
			{
				if(m[i][j]=='$')
				{
					return j;
				}
			}
		}
		
		return 0;
	}
	
	public static char getValor(char [][]m, int i, int j) //retorna o valor da matriz na posicao i,j
	{
		return m[i][j];
	}
	///////////////////////////////////////////////////////////////////////////////////////////
	///////////////////////////////////////////////////////////////////////////////////////////
	public static char getCima(char [][]m, int linhaAtual,int colunaAtual) //retorna valor da posicao a cima da posicao atual
	{
		if(linhaAtual==0) //posicao atual é o topo
		{
			return 'f'; //fora da matriz
		}
		else
		{
			return m[linhaAtual-1][colunaAtual]; //retorna +,-,#     $,*
		}
	}
	
	public static char getBaixo(char [][]m, int linhaAtual,int colunaAtual) //retorna valor da posicao a baixo da posicao atual
	{
		if(linhaAtual==m.length-1) //posicao atual é o topo
		{
			return 'f'; //fora da matriz
		}
		else
		{
			return m[linhaAtual+1][colunaAtual]; //retorna +,-,#     $,*
		}
	}
	
	public static char getDireita(char [][]m, int linhaAtual,int colunaAtual,int numeroColunas) //retorna valor da posicao a direita da posicao atual
	{
		if(colunaAtual==numeroColunas-1) //posicao atual é ultima coluna
		{
			return 'f'; //fora da matriz
		}
		else
		{
			return m[linhaAtual][colunaAtual+1]; //retorna +,-,#     $,*
		}
	}
	
	public static char getEsquerda(char [][]m, int linhaAtual,int colunaAtual) //retorna valor da posicao a esquerda da posicao atual
	{
		if(colunaAtual==0) //posicao atual é ultima coluna
		{
			return 'f'; //fora da matriz
		}
		else
		{
			return m[linhaAtual][colunaAtual-1]; //retorna +,-,#     $,*
		}
	}
	
	///////////////////////////////////////////////////////////////////////////////////////////
	///////////////////////////////////////////////////////////////////////////////////////////
	
	public static int[] andaCima(char [][]m, int linhaAtual,int colunaAtual) //anda para cima da posicao atual
	{
		int[] novaPosicao = new int[2];
		novaPosicao[0]=linhaAtual-1;
		novaPosicao[1]=colunaAtual;
		return novaPosicao;
	}
	
	public static int[] andaBaixo(char [][]m, int linhaAtual,int colunaAtual) //anda para baixo da posicao atual
	{
		int[] novaPosicao = new int[2];
		novaPosicao[0]=linhaAtual+1;
		novaPosicao[1]=colunaAtual;
		return novaPosicao;
	}
	
	public static int[] andaDireita(char [][]m, int linhaAtual,int colunaAtual) //anda para direita da posicao atual
	{
		int[] novaPosicao = new int[2];
		novaPosicao[0]=linhaAtual;
		novaPosicao[1]=colunaAtual+1;
		return novaPosicao;
	}
	
	public static int[] andaEsquerda(char [][]m, int linhaAtual,int colunaAtual) //anda para esquerda da posicao atual
	{
		int[] novaPosicao = new int[2];
		novaPosicao[0]=linhaAtual;
		novaPosicao[1]=colunaAtual-1;
		return novaPosicao;
	}
	
}
