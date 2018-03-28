package tetris.block;

import tetris.Board;

public class Square extends Block{

	public Square(Board board) {
		super(board);
		// TODO 自動生成されたコンストラクター・スタブ
		
		//
		// 11
		// 11
		//
		
		block[1][1] = 1;
		block[2][1] = 1;
		block[2][2] = 1;
		block[1][2] = 1;
	
		imageNo = SQUARE;
	}

}
