# AUTHOR

# Maaz Syed

# 1102358

# Assignment22

# DESCRIPTION ( Purpose and proble )

## App.java

### Within this file we are asking the user for input for the various objects that we have, with different fields correlating to each of the book class and the electronics class. Once the objects have been populated the user is allowed to search including the different fields which are interpreted within the EStoreSearch class.

## Book.java

### Within this file we are essentially initializing the book object with the values that the user inputted from the previous App.java program. The Book fields include; product ID, description, price, year, author and publisher

## Electronics.java

### Within this file we are initializing the electronics object with the values that the user has inputted from the previous App.java program however this time for electronics rather than books. The electronics fields include ; product ID, description, price, year, and the maker

## Products.java

### Within the most recent addition this is the parent class ( or super class ) in the hierarchy of inheritance, where the book and electronic classes are both subclasses or child classes, where parent contains all of the common information between both of the books and electronics as a whole.

## EStoreSearch

### This file has various functions that allow for the search through different fields. The user is able to search through matchings of; productID, description keywords , and they year ( years including previous, all after, specfic year and a range of years )

# TEST PLAN

## APP.java ( including Book.java & Electronics.java & Products.java)

### For user input where there needs to be mandatory fields; these fields are tested to make sure they aren't empty, and fall within certain ranges. Mandatory fields include the Product ID, year and description of the object. Ranges for product ID include a length of 6 only, price cannot be negative, year must be greater than 1000 and less than 9999. However, any non mandatory fields are able to be left blank. However, keeping in mind when the search command is used any of the searching options including ; productID, keywords and years can be left blank. Each of the cases 2^3 = 8, are accounted for within the eStoreSearch class for searching.

### When dealing with the objects within various functions there are certain checks that we conduct to ensure that the values being passed into the object are sufficient and for example not null

### Within EStoreSearch there are checks to make sure the list is not empty, and when comparing to take into account what lists are larger than the other for matching the key value pairs for an accurate search.

### AS OF VERSION 1.01 -> Additional

### User is able to provide a file which contains formatted records of various book and electronic objects, where this file is checked to make sure it exists in the appropriate path. FilenotFound exceptions is then checked to make sure the system handles the exception appropriatley within a try catch system as specified. While parsing this file, it is carefully examined to match the fields within either a book or electronic object and it is added into the products Array List accordingly ( File format is discussed farther below )

### After the user completes entering each type of object there is branching, where depending on the type of object they create; either book or electronic, there will be a function which writes to the file. This function will throw an IOexception which is dealt with in the main ( tested in JUNIT ) and will append to the current file each time one of these objects is entered. ( FileName check remain similar to that of opening the file in the first place )

### within EStoreSearch the new addition of the hashmap, means that when each of the keywords are mapped to their indexes there needs to be checks to make sure the same index is not added within the hashmap. This is because if the same keyword is found within the same object multiple times we want to map to this object once and this is accounted for.

### When multiple sections of the search are involved such as the year, we need to account for the fact that there needs to be an intersection between the yearindex list and the hasmap for each of the keywords where each of the elements in the intersection cannot be repeated similar to the above. If this is accounted for then multiple objects that are the same will be printed

### When the user is entering each of the book and electronic objects, the duplicate productID's are searched for as a linear scan from one list rather than from 2 individual lists

# USAGE

### The program is a gradle application program meaning it can be compiled through the use of Gradle ( and Java's JRE underlying)

### This can be done by ./gradelew build ( or gradle build ), to build the program then ./gradelew run ( or gradle run), to run the program and then to test against the JUNIT tests that have been provided we are able to do ./gradelew test ( or gradle test )

### FORMAT : The file for the format is as follows

## electronic//productId//Description//price//year//maker

## book//productID//Description//price//year//Author//publisher

## Note that any number of slashes like this may be used as the delimeter allows for it

## ASSUMPTIONS

### Menu can be provided for choosing between the book or electronic option

### While loops are command independent meaning, when wrong input entered we dont need to circulate to the start of the loop but rather the input of the command itself

### Assuming no maximum price where a minimum can just be 0 . ( default is also 0 keep that in mind )

### When inputting the year and productID if spaces in between remove them and parse normally as long as the values are of correct length and range

### Assuming that the productID cannot be the same for objects that are in book vs electronic class. ( Can't be duplicated within each class as well as across both of the classes as a whole )

### Assuming that search function input can be done on separate lines as not specified for readibility and parsing purposes

### Assuming the more fields the search has the more we need to match Ex : "Java keyword" and year is entered. My search will then match the object which matches java at that year/years

### Assuming for search keywords may be split based on punctuation and spaces for comparison ( basic punctuation including )

### Assuming that for the search keywords, a comparison can be done where its not case sensitive

### Assuming Javadoc comments are applied for essential class needs

# ADDITIONAL ASSUMPTIONS -> VERSION 1.01

### Assuming that the file can be formatted as we please, where the recommendation is only provided to give an idea of that

### Assuming that the fields within the file will not contain conflicting ProductID's and we can assume that there will be perfect input from each of the individual fields within the file.

## Assuming that when the check for the hashmaps are done, this can be performed not only when the file is made but updated in a seperate function from the EStoreSearch class. They are updated all at once based on the list of the various Products

## IMPROVEMENTS

### Code reusability, more of the code could have been reused as there are code repititions

### ( Completed for version 1.01)

### Will apply inheritance concepts in the future to possibly update this assignment where it will support more modular code

### ( Completed for version 1.01 )

### In the future I would also account for many more test cases that may arise when a user uses a program unexpectedly

### ( Completed for version 1.01 )

### For future improvements I will add some event-driven programming features where the user is able to freely navigate to change the flow of the program rather than a linear structure to the program. ( GUI )

### Another improvement could be to redo the hashmaping where rather than using a O(N^3) loop i can condense to a binary search to eliminate any results that cannot be mapped and then continue with that rather than a linear scan with a hashmap
