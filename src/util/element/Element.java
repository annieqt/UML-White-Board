package util.element;

import java.awt.Graphics;


public abstract class Element {
	
	//图元的文字内容
    protected String content;
    
    //图元的id
    protected int id;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public abstract void display(Graphics g);
	
	public abstract void inputContent(String content);
	
    public abstract String getContent();
    
    public abstract boolean checkFocused(int x, int y);
}
