package tetris.block;

import tetris.Board;

public class Bar extends Block{
//長い棒
	public Bar(Board board) {
		super(board);
		// TODO 自動生成されたコンストラクター・スタブ
		
		// 1
		// 1
		// 1
		// 1
		
		
		block[0][1] = 1;
		block[1][1] = 1;
		block[2][1] = 1;
		block[3][1] = 1;
		
		imageNo = BAR;
	}
}
