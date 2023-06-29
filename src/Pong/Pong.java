package Pong;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;

import javax.swing.JFrame;

public class Pong extends Canvas implements Runnable, KeyListener{
	private static final long serialVersionUID = 1L;
	
	private final int Width = 180; 
	private final int Hight = 120;
	private final int Scale = 3;
	
	private static boolean isRunning;
	
	private JFrame frame;
	private static Thread thread;
	private BufferedImage image;
	
	public static Player player;
	public static Enemy enemy;
	//static possibilita acessa-la por outra classe por exemplo: Enemy
	public static Ball ball;	
	
	//metodo construtor
	public Pong()
	{
		
		//Criando Janela
        frame = new JFrame("Pong");
		setPreferredSize(new Dimension(Width*Scale, Hight*Scale));
		inFrame();
		
		//Otimiza Renderiza��o, layer em que o grafico � renderizado -- FUNDO
		image = new BufferedImage(Width, Hight, BufferedImage.TYPE_INT_RGB);
		
		//Todas as classes s�o instanciadas no metodo construtor da classe principal
        player = new Player();
        player.var(60, 110);
        
        enemy = new Enemy();
        enemy.var(60, 6);
        
        ball = new Ball();
        ball.var(60, 52);
        
        //movimenta��o do jogador
        this.addKeyListener(this);
	}
	
	public void inFrame() {
		
        // vai adicionar o canvas ao JFrame
        frame.add(this);
        // m�todo para redimencionar a tela, est� como falsa para n�o deixar
        frame.setResizable(false);
        // m�todo do frame para calcular certa as dimens�es e mostrar
        frame.pack();
        // m�todo para fazer a localiza��o da tela, dizendo que � null, ela estar� iniciando no centro
        frame.setLocationRelativeTo(null);
        // m�todo que ir� dizer que se clicar no bot�o de fechar, ele ir� fechar, e n�o deixar em segundo plano
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        // m�todo para deixar a janela vis�vel
        frame.setVisible(true);	
	}
 
	public static void main(String[] args)
	{
		
		Pong pong = new Pong();
		pong.Start();
	}

	public synchronized void Start() {
		isRunning = true;
		thread = new Thread(this);
		thread.start();
	}
	
	public synchronized static void Stop() {
		isRunning = false;
		try {
			thread.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	public void tick()
	{
		player.tick();
		enemy.tick();
		ball.tick();
	}
	public void render()
	{

		//Otimizar a renderiza��o - dar melhor performance
						//instanciando Buffered 
		BufferStrategy bs = this.getBufferStrategy();
		if(bs == null)
		{
		    this.createBufferStrategy(3);
			return;}
		
		                //Definindo a imagem de fundo preta que ir� ficar atualizando e n�o deixando sobrepor imagens
        Graphics g = image.getGraphics();
        g.setColor(Color.BLACK);
        g.fillRect(0, 0, Width*Scale, Hight*Scale);  
        
        				//Desenhando a linha no meio da tela
        g.setColor(Color.white);
        g.drawLine(0, 60, 180, 60);
        
        				//Definindo o placar do jogo
      g.setColor(Color.red);
      g.setFont(new Font("Arial", Font.HANGING_BASELINE, 10)); 
      
      g.drawString(Integer.toString(ball.Point2), 160, 10);
      g.setColor(Color.white);

      					//Printando a mensagem de ganhador ou perdedor na tela quando se faz 3 pontos
      g.drawString(Integer.toString(ball.Point1), 2, 110);
      if(ball.Point1 >= 3 ) {
          g.setFont(new Font("Arial", Font.HANGING_BASELINE, 10));
          g.setColor(Color.white);
 		  g.drawString("Voc� Ganhouuu!!!", 45, 40);
      }
      else if(ball.Point2 >= 3 ) {
          g.setFont(new Font("Arial", Font.HANGING_BASELINE, 10));
          g.setColor(Color.white);
 		  g.drawString("VOC� PERDEU!!! LOSER ", 40, 40);
      }
      
	    //chamando o metodo render das classes, para que elas rodem
        player.render(g);
        enemy.render(g);
        ball.render(g);
        
        //Renderizando o jogo para mostrar a imagem na tela
        g = bs.getDrawGraphics(); 
        g.drawImage(image, 0, 0, Width * Scale, Hight * Scale, null);
        //Comandos que possibilitam a renderiza��o das imagens
        g.dispose();
        bs.show();
	
	
	}
	
	
	public void run() {
		/* L�gica do looping que ira renderizar e atualizar as informa��es do jogo*/
		
		// Pega o ultimo tempo do computados em nano segundos
		long lastTimer = System.nanoTime();
		double QtFrame = 60.0;
		//Calculo que mostra o momento certo que deve ser realizado o update do jogo
		double ns = 1000000000 / QtFrame;
		double Delta = 0;
		
		//Variaveis usadas para mostrar a taxa de FPS na tela
		long Timer = System.currentTimeMillis();
		int Frame = 0;
		
		//Looping, enquando isRunning == true o jogo ir� rodar
		while(isRunning)
		{
			//Pega o ultimo tempo do computador em nano segundos, j� se passou um tempo des de a ultima variavel
			long nowTimer = System.nanoTime();
			/* Faz a subtra��o dos tempos pegados e dividem pela variavel ns
			 * Quando essa opera��o ou o delta for maior ou igual a 1 est� na hora de atualizar o jogo 
			 * entrando no if */
			Delta += (nowTimer - lastTimer) / ns;
			lastTimer = nowTimer;
			if(Delta >= 1)
			{
				/* Aqui temos a renderiza��o dos graficos e atualiza��o do jogo, em uma taxa constante
				 * permitindo que o jogo rode limpo.
				 * Decrementamos Delta para que volte a ser 0 e que entre na condi��o novamente posteriormente*/
				tick();
				render();
				Delta --;
				//Cada vez que entra na condi��o incrementamos 1 no Frame, que ira indicar a taxa de FPS
				Frame++;
			}
			
			/* Quando a diferen�a do tempo atual, com o tempo coletado em timer for maior que 1000 quer dizer 
			 * que j� se passou 1 segundo, com isso iremos calcular a taxa de atualiza��o por segundo que o jogo
			 * est� apresentando
			 */
			if(System.currentTimeMillis() - Timer >= 1000)
			{
				//System.out.println("FPS: "+Frame);
				Frame = 0;
				Timer += 1000;
			}
		}	
	}

	//Logica que ira detectar quando pressionamos uma tecla do teclado
	public void keyPressed(KeyEvent e) 
	{
		
		if(e.getKeyCode() == KeyEvent.VK_RIGHT)
		{
			//Puxando a variavel right declarada em player e atribuindo o valor de true a ela
			player.right = true;
		}
		else if(e.getKeyCode() == KeyEvent.VK_LEFT)
		{
		    player.left = true;
		}
	}

	//Logica que ira detectar quando soltamos uma tecla do teclado
	public void keyReleased(KeyEvent e) {
		if(e.getKeyCode() == KeyEvent.VK_RIGHT)
		{
			player.right = false;
		}
		else if(e.getKeyCode() == KeyEvent.VK_LEFT)
		{
		    player.left = false;
		}
	}

	public void keyTyped(KeyEvent e) {		
	}
}
