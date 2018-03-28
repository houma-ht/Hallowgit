package tetris.block;

import tetris.Board;

public class Lblock extends Block{
//L字
	public Lblock(Board board) {
		super(board);
		// TODO 自動生成されたコンストラクター・スタブ
		
		// 
		// 11
		//  1
		//  1
		
		block[1][1] = 1;
		block[1][2] = 1;
		block[2][2] = 1;
		block[3][2] = 1;
	
		imageNo = L;
	}

}
