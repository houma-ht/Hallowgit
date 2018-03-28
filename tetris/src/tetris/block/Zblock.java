package tetris.block;

import tetris.Board;

public class Zblock extends Block{
//Z字のやつ
	public Zblock(Board board) {
		super(board);
		// TODO 自動生成されたコンストラクター・スタブ
		
		//
		//  1
		// 11
		// 1
		
		block[1][2] = 1;
		block[2][1] = 1;
		block[2][2] = 1;
		block[3][1] = 1;
	
		imageNo = Z;
	}
}
