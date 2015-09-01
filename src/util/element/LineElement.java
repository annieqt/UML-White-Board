package util.element;

import java.awt.Graphics;

public class LineElement extends Element {
	
	//线型图元的起点和终点
	protected int x1, y1, x2, y2;
	
	public LineElement (int x1, int y1, int x2, int y2) {
		this.x1 = x1;
		this.y1 = y1;
		this.x2 = x2;
		this.y2 = y2;
	}
	
	@Override
	public void display(Graphics g) {
		
	}

	@Override
	public String getContent() {
		return content;
	}

	@Override
	public void inputContent(String content) {
		this.content = content;
	}

	@Override
	public boolean checkFocused(int x, int y) {
		
		return false;
	}

}
