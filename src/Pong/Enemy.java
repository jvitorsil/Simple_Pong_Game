package Pong;

import java.awt.Color;
import java.awt.Graphics;

public class Enemy {

	boolean right, left;
	double x, y;
	int W, H;	
	
	public void var(double x, double y)
	{
		//this refere-se as variaveis declaradas nessa classe, e as variaveis sem this são as do metodo 
		//Que podem estar sendo compartilhado entre outras classes
		
		//X = posição horizontal, y = posição vertical, W = tamanho do inimigo, H = espessura  do inimigo
		this.x = x;
		this.y = y;
	    this.W = 17;
	    this.H = 4;
	    }
	
	public void tick()
	{	
		/*		Logica para o inimigo seguir a bola
		 * Teremos que a posição x do inimigo será indicada pela posição x da bola, porém, se deixarmos só assim
		 * A pontinha do inimigo estará sincronizada com a bola e QUEREMOS QUE O MEIO DO INIMIGO ESTEJA SINCRONIJADO
		 * Dessa forma decrementamos a posição atual (Permitindo que )da bola e 10, para centralizar
		 * 
		 * Multiplicamos por 0.05 para reduzir a velocidade de deslocamento do inimigo			*/
		x += ((Pong.ball.x) - x - 10) * 0.05;
		
		// Lógica utilizada para colisão de objetos ou parede
		/* Temos que, a posição (x) do player somado com seu tamanho (W) for maior que 180, que é o tamanho da 
		 * janela do jogo, retornaremos que a posição do player será 180 que é o tamanho da janela - W que é o seu tamanho
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
		//Criando o playr, que se trata de um retangulo, def. sua cor e posição na layer (x,y)e dimensões (W,H)
		g.setColor(Color.red);
		// Aqui definimos as variaveis x e y como int porque anteriormente elas foram definidas com double
		g.fillRect((int)x,(int)y, W, H);
	
	}

}
