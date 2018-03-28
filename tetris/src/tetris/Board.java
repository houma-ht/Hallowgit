package tetris;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Point;

import javax.swing.ImageIcon;

import tetris.block.Block;

public class Board {
	//フィールドのサイズ
	 public static final int COL = 12;//横
	 public static final int ROW = 26;//縦
	 //マスのサイズ
	 public static final int TILE_SIZE = 16;
	 
	 
	 //ボード
	 private int [][] board;
	 //ボードのイメージ
	 private int [][] imgBoard;
	 //画像
	 private Image image;
	 
	 //コンストラクタ
	 public Board(){
		 board = new int[ROW][COL];
		 imgBoard = new int[ROW][COL];
		 init();
	 }
	 
	 //ボードを初期化
	 public void init (){
		 for(int y=0;y<ROW;y++){
			 for(int x=0;x<COL;x++){
				 //壁を作る
				 if(x == 0 || x == COL -1){
					 board[y][x] = 1;
					 imgBoard[y][x] = Block.WALL;
					 
				 }
				 else if(y == ROW -1){
					 board[y][x] = 1;
					 imgBoard[y][x] = Block.WALL;
				 }
				 else{
					 board[y][x] = 0;
				 }
			 }
		 } 
	 }
	 
	 //フィールドを描画
	 //色設定のタイミングが違うだけでBlockクラスとほぼ一緒ってか同じ
	 public void draw(Graphics g){
		 for(int y=0;y<ROW;y++){
			 for(int x=0;x<COL;x++){
				 if(board[y][x] == 1){
					 if(imgBoard[y][x] == 0){
							loadImage("pic/pink.png");
						}
						else if(imgBoard[y][x] == 1){
							loadImage("pic/red.png");
						}
						else if(imgBoard[y][x] == 2){
							loadImage("pic/yellow.png");
						}
						else if(imgBoard[y][x] == 3){
							loadImage("pic/blue.png");
						}
						else if(imgBoard[y][x] == 4){
							loadImage("pic/orange.png");
						}
						else if(imgBoard[y][x] == 5){
							loadImage("pic/green.png");
						}
						else if(imgBoard[y][x] == 6){
							loadImage("pic/sky.png");
						}
						else if(imgBoard[y][x] == 7){
							loadImage("pic/black.png");
						}
					 g.drawImage(image, x * TILE_SIZE, y * TILE_SIZE,null);
				 }
			 }
		 }
	 }
	 
	 protected void loadImage(String filename) {
	        // ブロックのイメージを読み込む
	        // ImageIconを使うとMediaTrackerを使わなくてすむ
	        ImageIcon icon = new ImageIcon(getClass().getResource(filename));
	        image = icon.getImage();
	    }
	 
	 /**
	  * ブロックの移動ができるかどうかの判別
	  * @param newPos ブロックの移動先座標
	  * @param block ブロック
	  * @return
	  */
	 public boolean isMovable(Point newPos,int[][] block){
		 //ボード全体を検索中...
		 for(int y=0;y<Block.MAX_Y;y++){
			 for(int x=0;x<Block.MAX_X;x++){
				 if(block[y][x] == 1){//ブロックのある場所を調べる
					 if(newPos.y + y < 0){//そのマスが画面の上端外の時
						 //ブロックのあるマスが壁のある0列目(左端)以下もしくは
						 //COL列目(右端)以上に移動しようとしている場合
						 if(newPos.x + x <= 0 ||newPos.x + x >= COL - 1){
							 return false;//移動できない
						 }
					 }
					 //そのマスが画面ないの時
					 else if(board[newPos.y+y][newPos.x+x] == 1){
						 //移動先にすでにブロックがある場合は移動できない
						 return false;
					 }
				 }
			 }
		 }
		 return true;//移動可能
	 }
	 
	 /**
	  * 落ちきったブロックをボードに固定する
	  * @param pos ブロックの位置
	  * @param block ブロック
	  * @param imageNo ブロックの種類
	  */
	 public void fixBlock(Point pos,int[][] block,int imageNo){
		 for(int y=0;y<Block.MAX_Y;y++){
			 for(int x=0;x<Block.MAX_X;x++){
				 if(block[y][x] == 1){
					 if(pos.y + y < 0) continue;
					 //ブロックがいる座標とそのブロックの色ナンバーをボードが記憶する
					 //落ちきったとこでブロックは消えてるよ
					 board[pos.y+y][pos.x+x] = 1;
					 imgBoard[pos.y+y][pos.x+x] = imageNo;
				 } 
			 }
		 }
	 }
	 
	 /**
	  * 揃った行を削除
	  */
	 public void deleteLine(){
		 for(int y=0;y<ROW-1;y++){
			 int count = 0;//y行にあるブロック数を数える
			 for(int x=1;x<COL-1;x++){
				 if(board[y][x] == 1){//ブロックがあれば
					 count ++;//1たす
				 }
			 }
			 if(count == Board.COL-2){//揃った行があれば
				 //その行を消去
				 for(int x=1;x<COL-1;x++){
					 board[y][x] = 0;
				 }
				 //それより上の行を落とす
				 for(int newy=y;newy>0;newy--){
					 for(int newx=1;newx<COL-1;newx++){
						 //位置を1つ下に
						 board[newy][newx] = board[newy-1][newx];
						 //それに伴い色の設定も同じように
						 imgBoard[newy][newx] = imgBoard[newy-1][newx];
					 }
				 }
			 } 
		 }
	 }
	 
	 //ブロックが積み上がっているかの確認
	 public boolean isStrikeed(){
		 //ブロック出てくるとこまで積まったら
		 for(int x=5;x<COL-5;x++){
			 if(board[0][x] == 1){
				 return true;
			 }
		 }
		 return false;
	 }

}
