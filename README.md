
# GPS -Ex2

This program is an educational program.

It is broken down into several stages:

The 3D points, which will then be defined as GPS or as Cartesian.

The program is to make the conversion between cartesian is polar, as well as to make any kind of calculations between vectors and points GPS, to find the vector between two points GPS, to find the new point GPS after this displacer of a GPS points with a vectors ...

The program is also read a csv file or a folder containing csv files and convert it to kml file.

Let's go to Package Gis

To make it short:

- Gis-element define a GPS point on the map

- Gis-Layer define a Csv file (several Gis-element)

- Gis-Project defined a set of Gis-layers

We also have a Meta-data that is common to the three classes is defined the data.

The tests of the function were carried out by Junit, all the functions have their verifications which are validated. The tests are located in the Tests Package.

# Authors

Shimon Mimoun&Omer Paz 

# Built With

Eclipse - is an integrated development environment (IDE) used in computer programming, and is the most widely used Java IDE

The project includes class diagrams

# Getting Started

There is a test object that contains demonstrations of all functions to illustrate how things work. In addition, it is recommended to go over the javadoc before and read on all functions.

# Acknowledgments

Wikipedia to understand more how to CSV File :  https://fr.wikipedia.org/wiki/Comma-separated_values

Wikipedia to understand more how to Kml File :  https://fr.wikipedia.org/wiki/Keyhole_Markup_Language

Wikipedia to understand more how to Polar Coordinate :  https://en.wikipedia.org/wiki/Polar_coordinate_system

# Description

### Package:

***Coords:***

_Include 1 interface such as_:  coords_converter

My Calsse: [MyCoords](https://github.com/omerpaz1/GPS-Ex2/blob/master/OOP_EX2-EX4-master/OOP_EX2-Ex4/src/Coords/MyCoords.java "MyCoords.java")

***File_format :***

My Calsses : _[CsvFileHelper](https://github.com/omerpaz1/GPS-Ex2/blob/master/OOP_EX2-EX4-master/OOP_EX2-Ex4/src/File_format/CsvFileHelper.java "CsvFileHelper.java") , [CsvFileHelper](https://github.com/omerpaz1/GPS-Ex2/blob/master/OOP_EX2-EX4-master/OOP_EX2-Ex4/src/File_format/CsvFileHelper.java "CsvFileHelper.java") , [Json_101](https://github.com/omerpaz1/GPS-Ex2/blob/master/OOP_EX2-EX4-master/OOP_EX2-Ex4/src/File_format/Json_101.java "Json_101.java") , [Lunch](https://github.com/omerpaz1/GPS-Ex2/blob/master/OOP_EX2-EX4-master/OOP_EX2-Ex4/src/File_format/Lunch.java "Lunch.java") , [MultiCSV](https://github.com/omerpaz1/GPS-Ex2/blob/master/OOP_EX2-EX4-master/OOP_EX2-Ex4/src/File_format/MultiCSV.java "MultiCSV.java") , [csv2kml](https://github.com/omerpaz1/GPS-Ex2/blob/master/OOP_EX2-EX4-master/OOP_EX2-Ex4/src/File_format/csv2kml.java "csv2kml.java")_  


***GIS :***

Include 4 interface such as: [GIS_element](https://github.com/omerpaz1/GPS-Ex2/blob/master/OOP_EX2-EX4-master/OOP_EX2-Ex4/src/GIS/GIS_element.java "GIS_element.java") , [GIS_layer](https://github.com/omerpaz1/GPS-Ex2/blob/master/OOP_EX2-EX4-master/OOP_EX2-Ex4/src/GIS/GIS_layer.java "GIS_layer.java") , [GIS_project](https://github.com/omerpaz1/GPS-Ex2/blob/master/OOP_EX2-EX4-master/OOP_EX2-Ex4/src/GIS/GIS_project.java "GIS_project.java") , [Meta_data](https://github.com/omerpaz1/GPS-Ex2/blob/master/OOP_EX2-EX4-master/OOP_EX2-Ex4/src/GIS/Meta_data.java "Meta_data.java")

My Calsses: [GIS_Layer_using](https://github.com/omerpaz1/GPS-Ex2/blob/master/OOP_EX2-EX4-master/OOP_EX2-Ex4/src/GIS/GIS_Layer_using.java "GIS_Layer_using.java") , [GIS_projet_using](https://github.com/omerpaz1/GPS-Ex2/blob/master/OOP_EX2-EX4-master/OOP_EX2-Ex4/src/GIS/GIS_projet_using.java "GIS_projet_using.java") , [Gis_element_using](https://github.com/omerpaz1/GPS-Ex2/blob/master/OOP_EX2-EX4-master/OOP_EX2-Ex4/src/GIS/Gis_element_using.java "Gis_element_using.java") , [Meta_Data_using](https://github.com/omerpaz1/GPS-Ex2/blob/master/OOP_EX2-EX4-master/OOP_EX2-Ex4/src/GIS/Meta_Data_using.java "Meta_Data_using.java")

***GEOM :***

Include 1 interface such as: Geom_element

My Calsse: Point3D
