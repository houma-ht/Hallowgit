package tetris;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Random;

import javax.swing.JPanel;

import tetris.block.*;
//メインパネル(実際の流れが書いてあるよ)
public class MainPanel extends JPanel implements Runnable, KeyListener{
	
	// パネルサイズ
    public static final int WIDTH = 192;//12*16 横
    public static final int HEIGHT = 416;//26*16 縦
    
    //ボード
    private Board board;

    // 現在のブロック
    private Block block;
    // 次のブロック
    private Block nextBlock;

    // ゲームループ用スレッド
    private Thread gameLoop;
    //乱数
    private Random rand;
    
    public MainPanel() {
        // パネルの推奨サイズを設定、pack()するときに必要
        setPreferredSize(new Dimension(WIDTH, HEIGHT));
        //JPanelでキー入力を受け付けるようにする(必須)
        setFocusable(true);
        //キーリスナー登録(上と同様に必須?)
        addKeyListener(this);
        
        rand = new Random();
        board = new Board();
        block = createBlock(board);
        
        //ゲームループ開始
        gameLoop = new Thread(this);
        //Threadクラスのrunメソッドよりこのクラスのrunメソッドが優先で実行されるらしい?
        gameLoop.start();
        
    }
    
    @Override
	public void run() {
		// TODO 自動生成されたメソッド・スタブ
    	long lastTime = 0;
    	while(true){
    		//ブロックを移動する
    		//ブロックが固定されたらtrueが返される
    		boolean isFixed = block.move(Block.DOWN);
    		if(isFixed){//ブロックが固定されたら
    			//次のブロックを作成(ランダム)
    			nextBlock = createBlock(board);
    			block = nextBlock;
    		}
    		
    		//ブロックが揃った行を消す
    		board.deleteLine();
    		
    		if(board.isStrikeed()){//ゲームオーバーなら
    			board = new Board();//ボードを初期化(ゲームスタートの状態)
    			block = createBlock(board);
    		}
    		
    		//再描画
    		repaint();
    		//休止(CPUの負荷減らすよ)
    		//try-catch文...例外が発生する可能性がある処理に使うよ
    		try{
    			Thread.sleep(200/*=0.2秒*/);//例外が発生するおそれのある処理
    		} catch(InterruptedException e){//例外が発生した後の処理
    			//プログラムの割り込みが発生した時
    			e.printStackTrace();//例外の「クラス名」,「例外の説明」,
    			                    //「呼び出しもとメソッドとファイル及び行番号」を標準エラーに出力
    		}
    	}
	}
    
    public void paint(Graphics g){
    	//super.paintComponent(g);
    	//ボードを描画
    	board.draw(g);
    	//落ちてくるブロックを描画
    	block.draw(g);
    }
    
    //ブロック作るよ
    public Block createBlock(Board board){
    	//乱数使ってランダムに決定
    	int blockNo = rand.nextInt(7);
    	switch(blockNo){
    		case Block.BAR:
    			return new Bar(board);//長い棒
    		case Block.Z:
    			return new Zblock(board);//Z字
    		case Block.SQUARE:
    			return new Square(board);//四角
    		case Block.L:
    			return new Lblock(board);//L字
    		case Block.REVERSE_Z:
    			return new ReverseZblock(board);//逆Z字
    		case Block.T:
    			return new Tblock(board);//T字
    		case Block.REVERSE_L:
    			return new ReverseLblock(board);//逆L字
    	}
    	return null;
    }

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO 自動生成されたメソッド・スタブ
		//多分キーを入力している時のやつだから必要なし無視
	}

	@Override
	public void keyPressed(KeyEvent e) {
		// TODO 自動生成されたメソッド・スタブ
		int key = e.getKeyCode();//文字コードが無いような「F1」ボタンなどを検出する
        if (key == KeyEvent.VK_LEFT) { // 左キーを押した時ブロックを左へ移動
            block.move(Block.LEFT);
        } else if (key == KeyEvent.VK_RIGHT) { // 右キーを押した時ブロックを右へ移動
            block.move(Block.RIGHT);
        } else if (key == KeyEvent.VK_DOWN) { // 下キーを押した時ブロックを下へ移動(速度UP)
            block.move(Block.DOWN);
        } else if (key == KeyEvent.VK_SPACE || key == KeyEvent.VK_UP) { 
        	//上もしくはスベースキーを押した時ブロックを回転
            block.turn();
        }
        repaint();
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO 自動生成されたメソッド・スタブ
		//多分キーを離した時のやつだから必要なし無視
	}

}
