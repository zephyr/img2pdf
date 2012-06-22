
# img2pdf

## Purpose

This is a simple Java program to embed a image without any changes or recompression in a PDF (of version 1.4), particular useful for using JPEG 2000 images in LaTeX.

## For end users

### How to use the program
At first, you’ll have to install a Java 6 JRE or later (if you doesn’t already have one).

At second, download the file `img2pdf.jar` – a normal double click on this file will start a self-instructional graphical use interface.

If you you prefer the (more powerfull!) command-line interface, just type
    javac -jar img2pdf.jar
instead, with will give you a list of valid usage examples.

### Shortcut for the command line
There are also tiny but useful bash shortcuts (for Windows and Linux), so it’s even sufficient to type
    img2pdf
It it recomended to put the `jar` together with the `bat/sh` file into a directory included in your system path, so you can use them everywhere.

## For developers

### Requirements
At first, you’ll need to install a Java 6 JDK or later.

At second, you have to download the [latest jar distribution of iText (version 5.2 or later)](http://sourceforge.net/projects/itext/files/) and put it into the same directory as the `build.xml`.

### How to compile and execute
If you have [Apache Ant](http://ant.apache.org/) installed, just type
    ant dist
to compile and to create the `img2pdf.jar` file for distribution.

## Licence

Copyright 2010 by [Dennis Heidsiek](http://www.google.com/profiles/Dennis.Heidsiek)

This program is free software: you can redistribute it and/or modify it under the terms of the [GNU General Public License](http://www.gnu.org/copyleft/gpl.html) as published by the Free Software Foundation, either version 3 of the License, or (at your option) any later version.

This program is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY; without even the implied warranty of   MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU General Public License for more details.

You should have received a copy of the GNU General Public License along with this program.  If not, see [here](http://www.gnu.org/licenses/).
