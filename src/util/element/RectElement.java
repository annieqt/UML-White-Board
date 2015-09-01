package util.element;

import java.awt.Graphics;

public class RectElement extends Element {
	
	//矩形图元的起始位置，长，宽
	protected int x, y, width, height;

	public RectElement(int x, int y, int width, int height) {
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
	}

	@Override
	public void display(Graphics g) {
		
	}

	@Override
	public void inputContent(String content) {
		this.content = content;
	}

	@Override
	public String getContent() {
		return content;
	}

	@Override
	public boolean checkFocused(int x, int y) {
		return false;
	}

}
