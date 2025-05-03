# Treetor
Treetor is an acyclic graph (tree/forest) rendering tool. 

QuickStart
----
Locate file Test20121111_II.java, change Bag.testresultfilepath.
```java
		Bag.testresultfilepath = "./";
```
mvn clean package


ls /pathto/Treetor/target/classes/net/heteroclinic/graphtest/Test20121111_II.class
java -cp /pathto/Treetor/target/classes net.heteroclinic.graphtest.Test20121111_II
OR
java -cp /path/Treetor/target/classes net.heteroclinic.graphtest.Test20121111_II "../"
"../" or choose a different path

Change Picture orientation
java -cp /path/Treetor/target/treetordev-0.0.1-SNAPSHOT.jar net.heteroclinic.graphtest.Test20120927_I  "./"

Draw Tree From Node JS data array
java -cp /path/Treetor/target/treetordev-0.0.1-SNAPSHOT.jar net.heteroclinic.graphtest.Test20250502_DrawTreeFromJSArray  "./"
The array has the information of a full binary tree information. 'null' for empty node. The last layer is truncated. If the array/tree is malformed, the program will complain and stop.
For rendering limitation, if a right node's parent does not have a left node, we put an dummy node as the left node.
A sample picture at the "./" folder like JSArrayTree20250502-180510209.png.








Introduction
----
Treetor is an acyclic graph (tree/forest) rendering tool. It was once aimed to visualize thread/resource dependency graph. This project started in 2012 with Java (TM) implementation. This project is an experimental project for non-commercial purpose. 

License
----
All third party licenses and rights are automatically cascaded. Direct mock/hack of third party sources is referenced and documented to the best. The responsibility of the author(s), Zhikai Wang/www.heteroclinic.net, to the maximum is to remove or modify matters in dispute. You can utilize this project at good-will. The inverse of good-will includes illegal activities that are subject to jurisdiction applicable. Zhikai Wang/www.heteroclinic.net (c) 2012,2015 based on the preceding statements.

