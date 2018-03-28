package tetris.block;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Point;

import javax.swing.ImageIcon;

import tetris.Board;

public abstract class Block {
	
	//ブロックのサイズ
	public static final int MAX_X = 4;
	public static final int MAX_Y = 4;
	
	// マスのサイズ
	private static final int TILE_SIZE = Board.TILE_SIZE;
	
	// 移動方向
	public static final int LEFT = 0; //左
	public static final int RIGHT = 1; //右
	public static final int DOWN = 2; //下
	
	//ブロックの色コード的なやつ
	public static final int BAR = 0;//長い棒
	public static final int Z = 1;//Z字のやつ
	public static final int SQUARE = 2;//四角
	public static final int L = 3;//L字のやつ
	public static final int REVERSE_Z= 4;//Zの反転
	public static final int T = 5;//T字のやつ
	public static final int REVERSE_L = 6;//Lの反転
	public static final int WALL = 7;//壁
	
	//1を入れたところがブロックになる
	protected int[][] block;
	
	//ブロックの番号(上のやつ)
	protected int imageNo;
	
	//位置
	protected Point pos;
	
	// ボードへの参照
	protected Board board;
	//画像
	protected Image image;
	
	//コンストラクタ
	public Block(Board board){
		block = new int [MAX_Y][MAX_X];
		for(int y=0;y<MAX_Y;y++){
			for(int x=0;x<MAX_X;x++){
				block[y][x] = 0;
			}
		}
		imageNo = 6;
		//落ちてくるとこ
		pos = new Point(4, -4);
		this.board = board;
	}
	
	//描画
	public  void draw(Graphics g){
		//大量のif文でごめんね☆各ブロックの画像を決定してるよ
		if(imageNo == BAR){
			loadImage("../pic/pink.png");
			//このクラスがblockパッケージに入ってるから1つ上へ相対パスで戻ってから画像指定
		}
		else if(imageNo == Z){
			loadImage("../pic/red.png");
		}
		else if(imageNo == SQUARE){
			loadImage("../pic/yellow.png");
		}
		else if(imageNo == L){
			loadImage("../pic/blue.png");
		}
		else if(imageNo == REVERSE_Z){
			loadImage("../pic/orange.png");
		}
		else if(imageNo == T){
			loadImage("../pic/green.png");
		}
		else if(imageNo == REVERSE_L){
			loadImage("../pic/sky.png");
		}
		else if(imageNo == WALL){
			loadImage("../pic/black.png");
		}
		//こっからやっと描画
		for(int y=0;y<MAX_Y;y++){
			for(int x=0;x<MAX_X;x++){
				if(block[y][x] == 1){
					//指定された矩形の内部に収まるようにスケーリング
					g.drawImage(image, (pos.x+x) * TILE_SIZE, (pos.y+y) * TILE_SIZE, null);
				}
			}
		}
	}
	//使ってるdrawImageの引数リスト(画像,描画開始の左上の位置,謎のオブザーバー(nullでいいらしい))
	
	protected void loadImage(String filename) {
        // ブロックのイメージを読み込む
        // ImageIconを使うとMediaTrackerを使わなくてすむ
        ImageIcon icon = new ImageIcon(getClass().getResource(filename));
        image = icon.getImage();
    }
	
	//移動メソッド
	public boolean move(int dir){
		switch(dir){
			//左への移動
			case LEFT:
				Point newPos = new Point(pos.x-1,pos.y);
				if(board.isMovable(newPos, block)){
					pos = newPos;
				}
				break;
			//右への移動	
			case RIGHT:
				newPos =new Point(pos.x+1,pos.y);
				if(board.isMovable(newPos, block)){
					pos = newPos;
				}
				break;
			//下への移動	
			case DOWN:
				newPos =new Point(pos.x,pos.y+1);
				if(board.isMovable(newPos, block)){
					pos = newPos;
				}
				else{
					//ブロックをボードに固定する
					board.fixBlock(pos, block, imageNo);
					//固定したらtrueを返す
					return true;
				}
				break;
		}
		return false;
	}
	
	//ブロックを回転させるよ
	public void turn(){
		int[][] turnBlock = new int[MAX_Y][MAX_X];
		
		//回転したブロック
		for(int y=0;y<MAX_Y;y++){
			for(int x=0;x<MAX_X;x++){
				turnBlock[x][MAX_Y-1-y] = block[y][x];
			}
		}
		
		//回転可能か調べる
		if (board.isMovable(pos, turnBlock)) {
			block = turnBlock;
		}
	}
	
	

}
