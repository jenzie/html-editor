package se362.test;

import junit.framework.Assert;
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

	public void testCheckValid() throws Exception{
		Assert.assertTrue(HTMLCheck.checkValid("<html>"));
		Assert.assertTrue(HTMLCheck.checkValid("<table>"));
		Assert.assertFalse(HTMLCheck.checkValid("<foo>"));
	}
}
