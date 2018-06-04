# max2_challenge
Max2 Inc code challenge


Functional requirements
---------------------------------------------

#Part 1
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


#Part 2
 1. Implement an API to consume data from FourSquare and returns list of names of venues in the following JSON format
      {
			"places": [
				"Just Salad",
				"Mcdonalds",
				"Chipotle" ]
	  }