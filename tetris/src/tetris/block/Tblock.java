package tetris.block;

import tetris.Board;

public class Tblock extends Block{

	public Tblock(Board board) {
		super(board);
		// TODO 自動生成されたコンストラクター・スタブ
		
		// 1
		// 11
		// 1
		
		
		block[1][1] = 1;
		block[2][1] = 1;
		block[2][2] = 1;
		block[3][1] = 1;
	
		imageNo = T;
	}

}
