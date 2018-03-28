package tetris.block;

import tetris.Board;

public class ReverseLblock extends Block{

	public ReverseLblock(Board board) {
		super(board);
		// TODO 自動生成されたコンストラクター・スタブ
		
		//
		// 11
		// 1
		// 1
		block[1][1] = 1;
		block[1][2] = 1;
		block[2][1] = 1;
		block[3][1] = 1;
	
		imageNo = REVERSE_L;
	}

}
