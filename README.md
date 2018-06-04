# Max2 challenge
Max2 Inc code challenge


### Functional requirements
---------------------------------------------

Part 1

Given input string of the format
firstname lastname, address, zipcode, phone number, color

1. Create a POJO class of the above input string

2. Your program will be fed an input file of n lines. You are required to read csv values, one line at a time, and convert them to the above POJO

3. The code should be able to handle various erroneous scenarios like wrongly formatted input string

4. Make sure your code is robust enough to accept the following 4 input formats-
     (firstname, lastname, number, color, zipcode)
     (firstname lastname, color, zipcode, phone number)
     (firstname, lastname, zipcode, phone number, color)
     (firstname lastname, address, zipcode, phone number, color)

5. Some lines may be invalid (does not conform to any of the above formats) and should not hinder the processing of subsequent valid lines. 
   Invalid lines should generate a warning log.

6. Generate the count and the names of people who belong to that color and implement an API that returns a JSON corresponding
     Example: JSON response
           [{
			"color": "Green",
			"count": 5
			}]
7. Generate color, the count and the names of people who belong to that color and implement an API that returns a JSON corresponding
     Example: JSON response
             [{
               "color": "Golden",
			   "count": 2,
			   "names": ["Donald Duck", "Mickey Mouse"]
			 }]
8. Use any DB as persistence

---------------------------------------------

Part 2

 1. Implement an API to consume data from FourSquare and returns list of names of venues in the following JSON format
      {
			"places": [
				"Just Salad",
				"Mcdonalds",
				"Chipotle" ]
	  }
	  
------------------------------------------
### Design

High Level design

![Alt text](max2_challenge_HDL.png?raw=true "Max2 Challenge HLD")

See the UML class diagram

-------------------------------------------------
Decision Part 1

I. core structure
   1. Decouple and modularize the uses cases into class separate components
   2. The core application is layered into controllers, services, repositories/dao, parsers, and helper/support packages
   3. Map the raw structure {firstname lastname, address, zipcode, phone number, color} into  PersonColor POJO class 

II. Data parsing and formatting  operation    
   1. Dynamic parser formatters to linked in  a chain (Chain of Responsibility - design pattern)
       a. For now the dynamic format patterns are hard-coded and mapped in the "com.max2.support.FormatPattern" class in an array list 
       b. The field format uses regular expressions for the raw structures  and are defined as DataPattern which contains the field names 
          and corresponding regular expression. It should be possible to store the DataPattern in the database for users to dynamically add or 
          remove regex and field structures- that is beyond the current score of the max2 challenge
          
   2. The parser formatters do not write directly to the database -- separation of concern.The formatters parse the valid line into Map
      and  delegate it to the Converter component that transforms the map into POJO object
   
   3. The ParserEventHandler extends the Java observable( observer design pattern) to propagate a ParserEvent object of both valid or invalid lines 
      with status code, original line, and POJO object(invalid line will be null) to the observers that have registered for notification
      
   4. Write operation observer registers to the ParserEventHandler  and gets ParserEvent updates whenever a line from the input data is parsed.
      Valid ParserEvent object(not null) are delegated to the PersonColorService(WriteOperation interface) to persist the data
      Invalid data is logged with a warning.
 
 III. Person color count query operation
   1.  Aggregate count queries are performed on the SQL database to better performance and flexibility.
  
   2.  Aggregate results are projected into interface class objects defined "com.max2.support.QueryProjection" class
 
 
 IV. Data I/O
   1. Data input can be given a raw text file separated by lines or uploaded  as file
   
   2. Restful API using the PersonColor controller that provides HTTP end points to perform POST and GET requests
  

----------------------------------------

Decision Part 2

  I. Implemented a backend API service operation that uses Foursquare API library provided to query the venues
      ( no need to re-engineer the wheel by using another HTTP rest client library)
     
  II. VenueController provides the HTTP end points to search for venues

 ---------------------------------------


### Setup, build and deployment  
  1. Framework used: Java Spring Framework (spring-boot)
  
     The following are required dependencies and must be installed on your computer before starting the build process
       1. Database: MySQL
       2. Apache Maven 3 
    
  2. Create a two databases and user account of your choice
           
        (Default) Quick setup on the database use the following MySQL commands to create default user "max2" with password "max2123" for two databases.
      
        CREATE USER 'max2'@'localhost' IDENTIFIED BY 'max2123';
            
		GRANT ALL PRIVILEGES ON max2_db. * TO 'max2'@'localhost';
		
		GRANT ALL PRIVILEGES ON max2_db_test. * TO 'max2'@'localhost';
		
		FLUSH PRIVILEGES;

   3.  Move directory into the project directory and build the code. Run the following maven commands below:
   
       *(option 1)  Run maven build with the default test database setup to run the test cases*
    
       mvn clean install -Ddbname="max2_db_test"
       
       *(option 2) Run maven build with the modified custom database parameters to run the test cases*
       
       mvn clean install -Ddbname="database name" -Ddbuser="database user" -Ddbpassword="database password" -Ddbhost="localhost" -Ddbport="3306"
        
   4. To run the jar application( default port is 18080)
      
      *(option 1)  Run and luanch using maven spring-boot plugin *
      
       mvn spring-boot:run
  
      *(option 2)  Run and lunch using java *
      
       java -jar target/max2_challenge-0.0.1-SNAPSHOT.jar
       
    5.  To access the end points locally
    
	    ###### Get color counts
		curl -X GET http://localhost:18080/max2/api/v1/persons/colors
		
		###### Get color counts and names of persons
		curl -X GET http://localhost:18080/max2/api/v1/persons/colors?names=true
		
		##### Post data
         curl -X POST \
		 http://localhost:18080/max2/api/v1/persons/colors \
		 -H 'content-type: text/plain' \
		 -d 'Duck, Donald, (703)-742-0996, Blue, 23234'
  		
		##### Upload file
		curl -X POST -F file=@sample_csv.txt http://localhost:18080/max2/api/v1/persons/colors/upload 
		
		##### Get venues
		curl -X GET http://localhost:18080/max2/api/v1/venues?near=MN
	
	 6. To access the end points over the cloud deployed in AWS
    
	    ###### Get color counts
		curl -X GET http://dev.etunkara.info:18080/max2/api/v1/persons/colors
		
		###### Get color counts and names of persons
		curl -X GET http://dev.etunkara.info:18080/max2/api/v1/persons/colors?names=true
		
		##### Post data
	    curl -X POST  http://dev.etunkara.info:18080/max2/api/v1/persons/colors -H 'content-type: text/plain' 
        -d 'Duck, Donald, (703)-742-0996, Blue, 23234'
		
		##### Upload file
		curl -X POST -F file=@sample_csv.txt http://dev.etunkara.info:18080/max2/api/v1/persons/colors/upload 
		
		##### Get venues  (default is near=NY, with "near" params is not given)
		curl -X GET http://dev.etunkara.info:18080/max2/api/v1/venues?near=MN
		
		
		(Optional) - You can either use Chrome Postman or firefox RestClient add-on plugins to test the endpoints
		
		##### Note: the code is deployed to my AWS server using Jenkins(http://dev.etunkara.info:8080) that is hooked to this repository