package Pong;

import java.awt.Color;
import java.awt.Graphics;

public class Enemy {

	boolean right, left;
	double x, y;
	int W, H;	
	
	public void var(double x, double y)
	{
		//this refere-se as variaveis declaradas nessa classe, e as variaveis sem this s�o as do metodo 
		//Que podem estar sendo compartilhado entre outras classes
		
		//X = posi��o horizontal, y = posi��o vertical, W = tamanho do inimigo, H = espessura  do inimigo
		this.x = x;
		this.y = y;
	    this.W = 17;
	    this.H = 4;
	    }
	
	public void tick()
	{	
		/*		Logica para o inimigo seguir a bola
		 * Teremos que a posi��o x do inimigo ser� indicada pela posi��o x da bola, por�m, se deixarmos s� assim
		 * A pontinha do inimigo estar� sincronizada com a bola e QUEREMOS QUE O MEIO DO INIMIGO ESTEJA SINCRONIJADO
		 * Dessa forma decrementamos a posi��o atual (Permitindo que )da bola e 10, para centralizar
		 * 
		 * Multiplicamos por 0.05 para reduzir a velocidade de deslocamento do inimigo			*/
		x += ((Pong.ball.x) - x - 10) * 0.05;
		
		// L�gica utilizada para colis�o de objetos ou parede
		/* Temos que, a posi��o (x) do player somado com seu tamanho (W) for maior que 180, que � o tamanho da 
		 * janela do jogo, retornaremos que a posi��o do player ser� 180 que � o tamanho da janela - W que � o seu tamanho
		 * Evitando que ele atravesse a parede */
		if((x + W) >= 180)
		{
			x = 180 - W;
		}
		else if(x < 0 )
		{
			x = 0;
		}	
	}

	public void render(Graphics g)
	{           
		//Criando o playr, que se trata de um retangulo, def. sua cor e posi��o na layer (x,y)e dimens�es (W,H)
		g.setColor(Color.red);
		// Aqui definimos as variaveis x e y como int porque anteriormente elas foram definidas com double
		g.fillRect((int)x,(int)y, W, H);
	
	}

}
