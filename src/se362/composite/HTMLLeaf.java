package se362.composite;

/**
 * @author Jenny Zhen, coffee@csh.rit.edu
 */

public class HTMLLeaf extends HTMLComponent{
	private String name;
	private boolean isClosed;

	public HTMLLeaf(String tag) {
		super(tag);
		this.isClosed = true;
	}

	public String getName() {
		return super.getName();
	}

	public boolean isClosed() {
		return super.isClosed();
	}

	public boolean checkValidTag(){
		return super.checkValidTag();
	}

	public boolean add(HTMLComponent node) {
		return false;
	}
}
