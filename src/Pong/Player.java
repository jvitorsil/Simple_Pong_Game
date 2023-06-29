package Pong;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

public class Player{
	private static final long serialVersionUID = 1L;
	
	boolean right, left;
	int x, y;
	int W, H;	
	
	public void var(int x, int y)
	{
		//this refere-se as variaveis declaradas nessa classe, e as variaveis sem this são as do metodo 
		//Que podem estar sendo compartilhado entre outras classes
		
		//X = posição horizontal, y = posição vertical, W = tamanho do player, H = espessura  do player
		this.x = x;
		this.y = y;
	    this.W = 17;
	    this.H = 3;
	    }
	
	public void tick()
	{
		// Movimentação do player, no qual, quando detectado que right == true teremos uma incrementação 
		// na posição (x) do player resultando em seu deslocamento
		// Essa detecção está sendo realizada na classe pong pela biblioteca KeyListener
		// O mesmo ocorre para left
		if(right){
			x++;
		}else if(left){
			x--;
			}

		// Lógica utilizada para colisão de objetos ou parede
		/* Temos que, a posição (x) do player somado com seu tamanho (W) for maior que 180, que é o tamanho da 
		 * janela do jogo, retornaremos que a posição do player será 180 que é o tamanho da janela - W que é o seu tamanho
		 * Evitando que ele atravesse a parede */
		if((x + W) >= 180)
		{
			x = 180 - W;
		}
		/* Já quando a posição for menor que 0 retornaremos que ela é 0, evitando que atravesse a parede esquerda */
		else if(x < 0 )
		{
			x = 0;
		}
	}

	// Metodo utilizado para renderizar as imagens na tela, nesse caso o player
	public void render(Graphics g)
	{     
		//Criando o playr, que se trata de um retangulo, def. sua cor e posição na layer (x,y)e dimensões (W,H)
		g.setColor(Color.white);
		g.fillRect(x, y, W, H);
	
	}
}
