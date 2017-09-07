/*
 * The author Zhikai Wang/www.heteroclinic.net reserves all rights of this file.
 * (c) 2012  Zhikai Wang/www.heteroclinic.net
 *  
 */

package net.heteroclinic.graph;



public class Bag {
	
	public  double tierdistance = 10.0d;
	public  double nodesize = 1.6d;
	public  double nodespace = 1.3d * nodesize;
	//public  final double nearestcousin = 1.5d *nodesize;
	public  double familydistance = 2.0d * nodespace;
	public  double treeinteveralinforest = 2.0d * nodespace;
	public  double boderspace = 3.0d * nodespace;
	public  int OneDoubleequalpixels = 15;
	public  int fontsize = 14;
	public  int fontwidth = 8; // not accurate.
	public  double edgetrim  = 1.5d ; //ratio
	public  String testresultfilepath = "c:\\";
	public  String testunitname = "c:\\";
	public  String testresultfiletype = ".png";
	public  boolean forestministryupdategraphborder = true;
	public  boolean forestministryusepresetgraphborder = false;
}
