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
In this exercise we implement the class ExtendedSimplifiedTweet, which is similar to the SimplifiedTweet class from the previous practice, but with additional fileds: the number of followers of a user, a boolean for whether a tweet is a retweet and the user id and tweet id of thew retweeted tweet.
Then in the main we use Spark to calculate bigrams for a given language.
After implementing everything, we answer the following question:

    What are the top-10 most popular bi-grams for a given language from original Tweets used during the 2018 Eurovision Festival?
  
  Bi-grams in Hungarian:
    
    (2,@masonanonymous:-shalom)
    (2,🇮🇱-🇮🇱🇮🇱🇮🇱🇮🇱🇮🇱🇮🇱🇮🇱🇮🇱✡️✡️✡️✡️✡️✡️✡️✡️✡️✡️✡️✡️🕊🕊🕊✅)
    (2,israel-jai!!!!!!)
    (2,am-israel)
    (2,rt-@masonanonymous:)
    (2,#israel-#eurovision)
    (2,jai!!!!!!-#israel)
    (2,israel-🇮🇱)
    (2,shalom-israel)
    (2,🇮🇱🇮🇱🇮🇱🇮🇱🇮🇱🇮🇱🇮🇱🇮🇱✡️✡️✡️✡️✡️✡️✡️✡️✡️✡️✡️✡️🕊🕊🕊✅-https://t.co/9bf2nb6csi)
    
  Bi-grams in Spanish:
  
    (1828,el-año)
    (1180,rt-@manelnmusic:)
    (1123,este-año)
    (1074,que-el)
    (1008,de-la)
    (1008,que-no)
    (993,con-una)
    (984,año-pasado)
    (941,con-un)
    (917,gana-una)

  Bi-grams in Portuguese:
  
    (310,o-salvador)
    (269,para-o)
    (261,o-ano)
    (185,vai-ser)
    (178,o-melhor)
    (175,rt-@mocedumcabreste:)
    (168,foi-o)
    (165,salvador-a)
    (160,não-é)
    (157,salvador-sobral)

Exercise 4:
