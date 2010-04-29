
# img2pdf

## Purpose

This is a simple Java program to embed a image without any changes or recompression in a PDF (of version 1.4), particular useful for using JPEG 2000 images in LaTeX.

Currently, it uses a command-line interface.

## Compilation (and requirements)
At first, you’ll need to install Java 6 or later.

At second, you have to download the <a href="http://sourceforge.net/projects/itext/files/">latest jar distribution of iText (version 5 or later)</a>.

After that, you can compile with
    javac -cp .;* *.java

## How to execute
I already created some tiny bash shortcuts (for Windows and Linux), so just type
    img2pdf
with will give you a list of valid usage examples.

## Licence

Copyright 2010 by [Dennis Heidsiek](http://www.google.com/profiles/Dennis.Heidsiek)

This program is free software: you can redistribute it and/or modify it under the terms of the [GNU General Public License](http://www.gnu.org/copyleft/gpl.html) as published by the Free Software Foundation, either version 3 of the License, or (at your option) any later version.

This program is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY; without even the implied warranty of   MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU General Public License for more details.

You should have received a copy of the GNU General Public License along with this program.  If not, see [here](http://www.gnu.org/licenses/).
