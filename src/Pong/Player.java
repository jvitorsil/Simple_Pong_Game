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
		//this refere-se as variaveis declaradas nessa classe, e as variaveis sem this s�o as do metodo 
		//Que podem estar sendo compartilhado entre outras classes
		
		//X = posi��o horizontal, y = posi��o vertical, W = tamanho do player, H = espessura  do player
		this.x = x;
		this.y = y;
	    this.W = 17;
	    this.H = 3;
	    }
	
	public void tick()
	{
		// Movimenta��o do player, no qual, quando detectado que right == true teremos uma incrementa��o 
		// na posi��o (x) do player resultando em seu deslocamento
		// Essa detec��o est� sendo realizada na classe pong pela biblioteca KeyListener
		// O mesmo ocorre para left
		if(right){
			x++;
		}else if(left){
			x--;
			}

		// L�gica utilizada para colis�o de objetos ou parede
		/* Temos que, a posi��o (x) do player somado com seu tamanho (W) for maior que 180, que � o tamanho da 
		 * janela do jogo, retornaremos que a posi��o do player ser� 180 que � o tamanho da janela - W que � o seu tamanho
		 * Evitando que ele atravesse a parede */
		if((x + W) >= 180)
		{
			x = 180 - W;
		}
		/* J� quando a posi��o for menor que 0 retornaremos que ela � 0, evitando que atravesse a parede esquerda */
		else if(x < 0 )
		{
			x = 0;
		}
	}

	// Metodo utilizado para renderizar as imagens na tela, nesse caso o player
	public void render(Graphics g)
	{     
		//Criando o playr, que se trata de um retangulo, def. sua cor e posi��o na layer (x,y)e dimens�es (W,H)
		g.setColor(Color.white);
		g.fillRect(x, y, W, H);
	
	}
}
