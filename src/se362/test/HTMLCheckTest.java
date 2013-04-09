package se362.test;

import JUnit.framework.Assert;
import junit.framework.TestCase;
import se362.composite.HTMLCheck;
import se362.gui.GUI;

/**
 * @author: Jenny Zhen; jenz.rit@gmail.com
 * date: 04 09, 2013
 * language: Java
 * project: htmleditor
 */

public class HTMLCheckTest extends TestCase {
	private GUI editor;
	private HTMLCheck htmlCheck;

	public void testCheckValid() {
		editor = new GUI();
		htmlCheck = new HTMLCheck(editor);

		htmlCheck.checkValid("html"));
	}
}
