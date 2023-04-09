# Algorithm Package

This package contains a Java program that retrieves data from a specific API endpoint, sorts it based on date created and categorizes it into 3 different categories based on work category. The program is divided into two files - ALGORITHM.java and Job.java.

## ALGORITHM.java

This file contains the main method that executes the program. The program retrieves data from the given API URL, reads the response, and converts it from JSON format into an array of Job objects using the Gson library.

Afterwards, the program sorts the array of Job objects based on the date they were created using a quicksort algorithm. The sorting is done by comparing the dates using the compareTo() method of the Date class.

Finally, the program categorizes the jobs based on their work category and stores the indexes of each category in separate ArrayLists.
 
## Job.java

This file contains the Job class that represents a job object. Each job has a job title, job description, hourly rate, work hours, preferred skills, work category, ID and creation date.

## Requirements

The program requires the Gson library to be installed.
