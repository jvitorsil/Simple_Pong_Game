package Pong;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.Random;
import java.util.stream.IntStream;

public class Ball {

	boolean right, left;
	double x, y;
	int W, H;	
	double angle, angle1;
	
	public String winner;
	public String loser;
	
	double dx, dy;
	double speed = 1.0;
	
	public static int Point1 = 0, Point2 = 0;
	
	public void var(int x, int y)
	{
		//this refere-se as variaveis declaradas nessa classe, e as variaveis sem this s�o as do metodo 
		//Que podem estar sendo compartilhado entre outras classes
		//X = posi��o horizontal, y = posi��o vertical, W = tamanho da bola, H = espessura  da bola
		
		this.x = x;
		this.y = y;
	    this.W = 20;
	    this.H = 20;
	    
	    /* A fun��o RANDOM descrita abaixo ir� gerar um n�mero ale�torio que est� sendo armazenado na variavel angle
	     * Est� por sua vez est� sendo convertida em um angulo de seno e cosseno e armazenada em dx e dy
	     * */
	    double angle = new Random().nextInt(90) + 40;
	    dx = Math.cos(Math.toRadians(angle));
	    dy = Math.sin(Math.toRadians(angle));
	    }
	
	public void tick()
	{	
		
		/* Aqui estamos incrementando o valor do angulo criado a posi��o x e y da bola e multiplicando por um valor
		 * Que ir� nos indicar a velocidade que a bolinha se desloca
		 * */
		x += dx * speed;
		y += dy * speed;
		
		//Logica De colis�o da bolinha, um pouco diferente do player e do inimigo
		//Aqui temos que quando a posi��o x + tamanho W for maior que o tamanho da tela teremos uma invers�o
		//Do dx da bola, fazendo com que ela colida com a parede e inverta o sentido de deslocamento na horizontal
		if (x + W >= 180)
		{
			dx = dx * (-1);
		}
		else if (x + W < 0)
		{
			dx = dx * (-1);
		}
		
		
		//L�gica de colis�o da bola com o inimigo de player, trata-se de uma biblioteca "Rectangle" propria do Java
		//Com esse comando iremos identificar com um retangulo a �rea que o objeto ocupa e est� posicionado
		//Tendo assim como base para identificar as colis�es
		Rectangle bounds = new Rectangle((int)(x + dx),(int)(y + dy), W+1, H+1);
		Rectangle boundsEnemy = new Rectangle((int) Pong.enemy.x ,(int)Pong.enemy.y, Pong.enemy.W, Pong.enemy.H);
		Rectangle boundsPlayer = new Rectangle(Pong.player.x +1,Pong.player.y+1, Pong.player.W+1, Pong.player.H+1);
		
		//Caso o espa�o que a bola esta intersecte a �rea que o inimigo est� iremos inverter a posi��o dy da bola
		//Para que ela mude seu sentido de deslocamento e n�o continue naquele sentido de colis�o
		if(bounds.intersects(boundsEnemy))
		{
			dy = dy * -1;
			
			//mudando a dire��o mudando o dy e dx
			double angle = new Random().nextInt(210) + 60;
		    dy = Math.sin(Math.toRadians(angle));
			double angle1 = new Random().nextInt(210) + 60;
		    dx = Math.cos(Math.toRadians(angle));
		}
		//Caso o espa�o que a bola esta intersecte a �rea que o player est� iremos inverter a posi��o dy da bola
		//Para que ela mude seu sentido de deslocamento e n�o continue naquele sentido de colis�o
		else if(bounds.intersects(boundsPlayer))
		{
			dy = dy * -1;
			
			//mudando a dire��o
			double angle = new Random().nextInt(100) + 45;
		    dy = -1 * Math.sin(Math.toRadians(angle));
			double angle1 = new Random().nextInt(100) + 45;
		    dx = Math.cos(Math.toRadians(angle));
		}		
		
		//Sistema de pontua��o do jogo
		/* Caso a posi��o y do seja maior que 120, que se trata do tamanho vertical da tela, teremos ponto do inimigo
		 * Dessa forma iremos incrementar na pontua��o do inimigo e redefinir a posi��o da bola para o centro da tela
		 * Para que se reinicie o jogo
		 * Quando o valor de "Point2" referente a pontua��o do inimigo for maior que 3 entraremos na condi��o em que 
		 * Iremos encerrar o jogo dando um stop(); e dar um return pra sair da condi��o */
		if(y >= 120)
		{
			//ponto do inimigo
			Point2++;
			x = 90;
			y = 60;
			
			//mudando a dire��o da bolinha
			double angle = new Random().nextInt(200) + 40;
		    dy = Math.sin(Math.toRadians(angle));
			double angle1 = new Random().nextInt(200) + 40;
		    dx = Math.cos(Math.toRadians(angle));
		    if(Point2 > 3)
		    {
		    	String loser = new String("PERDEDOR!!");
		    	System.out.println(loser);
		    	Pong.Stop();
		    	return;
		    }
		    
		}
		else if (y < 0)
		{
			//ponto do jogador
		    Point1++;
			x = 90;
			y = 60;
			double angle = new Random().nextInt(200) + 40;
		    dy = Math.sin(Math.toRadians(angle));
			double angle1 = new Random().nextInt(200) + 40;
		    dx = Math.cos(Math.toRadians(angle));
		    if(Point1 > 3)
		    {
		        String winner = new String("GANHOU!!");
		        System.out.println(winner);
		        Pong.Stop();
		        return;
		    }
		    return;
		}
	}

	public void render(Graphics g)
	{           
		//Defini��o da bolinha
		g.setColor(Color.white);
		g.fillArc((int)x,(int)y, W, H, 0, 360);
	
	}
}
