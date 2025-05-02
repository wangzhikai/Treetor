/*
 * The author Zhikai Wang/www.heteroclinic.net reserves all rights of this file.
 * (c) 2012  Zhikai Wang/www.heteroclinic.net
 *  
 */

package net.heteroclinic.graph;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import net.heteroclinic.graph.Bag;

public class Test {
	static SimpleDateFormat df = new SimpleDateFormat("YYYYMMdd-HHMMssSSS");
	public static String getAResultFilename () {
		return Bag.testresultfilepath + Bag.testunitname + df.format(Calendar.getInstance().getTime())
				+Bag.testresultfiletype;
	}

	public static int getPower( int  base, int p ) {
		int result = 1;
		for (int i = 0; i <p ; i++ ) {
			result *= base;
		}
		return result;
	}
}
