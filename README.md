# lsds2020.lab2.grpP102.team08

The objectives of this lab are to implement a language filter similar to Lab 1 using Apache Spark,to run the application on AWS ElasticMapReduce through the AWS Web console and benchmark it, then to enhance the SimplifiedTweet class to access additional fields and to create more complex applications using Apache Spark

Exercise 1:
In this exercise, we re-implemented theTwitterLanguageFilter using the Spark framework. What it does is to  select  all  tweets  in  a  given  language  and  store  them  in S3.

Exercise 2:
In this exercise, we have run the application in AWS using the ElasticMapReduce service. We ran the code for the languages Spanish, Hungarian and Portuguese and placed the result of the runs in S3. We have also benchmarked the time for the languages in both the cluster and our local machine.

Benchmark:

    -local terminal:
    Tweets in Hungarian: time-> 137 seconds
    Tweets in Spanish: time-> 155 seconds
    Tweets in Portuguese: time-> 156 seconds
    
    -AWS console:
    Tweets in Hungarian: time-> 5 minutes
    Tweets in Spanish: time-> 5 minutes
    Tweets in Portuguese: time-> 5 minutes
    

Exercise 3:


Exercise 4:
