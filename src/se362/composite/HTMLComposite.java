package se362.composite;

import java.util.ArrayList;

/**
 * @author Jenny Zhen, coffee@csh.rit.edu
 */

public class HTMLComposite extends HTMLComponent{
	private String name;
	private boolean isClosed;
	private ArrayList<HTMLComponent> children;
	
	public HTMLComposite(String tag) {
		super(tag);
		this.isClosed = false;
		this.children = new ArrayList<HTMLComponent>();
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
		return super.add(node);
	}

	public boolean addChild(HTMLComponent child) {
		return children.add(child);
	}
}