/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package upf.edu.parser;

import java.io.Serializable;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ExtendedSimplifiedTweet implements Serializable{
    
    private static JsonParser jp = new JsonParser();

    private final long tweetId;              // the id of the tweet ('id')
    private final String text;                // the content of the tweet ('text')
    private final long userId;              // the user id ('user->id')
    private final String userName;          // the user name ('user'->'name')
    private final long followersCount;   //the number of followers (’user’->’followers_count’)
    private final String language;          // the language of a tweet ('lang')
    private final boolean isRetweeted;         // is it a retweet? (the object ’retweeted_status’ exists?)
    private final long retweetedUserId;        // [if retweeted] (’retweeted_status’->’user’->’id’)
    private final long retweetedTweetId;    // [if retweeted] (’retweeted_status’->’id’)
    private final long timestampMs;          // seconds from epoch ('timestamp_ms')

    //retweeted_status -> id 
    //                 -> user -> id
    
    public ExtendedSimplifiedTweet(long tweetId, String text, long userId, String userName,
                                long followersCount, String language, boolean isRetweeted,
                                long retweetedUserId, long retweetedTweetId, long timestampMs) {

    // PLACE YOUR CODE HERE!

        this.tweetId = tweetId;
        this.text = text;
        this.userId = userId;
        this.userName = userName;
        this.followersCount=followersCount;
        this.language = language;
        this.isRetweeted = isRetweeted;
        this.retweetedUserId = retweetedUserId;
        this.retweetedTweetId = retweetedTweetId;
        this.timestampMs = timestampMs;

    }
    
    public Optional<List<String>> generateBiGrams() {
		if (this.text.length() < 0) {
			return Optional.empty();
		}
		String[] splited_txt = this.text.split(" ");
		List<String> bigrams = new ArrayList<String>();
		
		splited_txt[0] = splited_txt[0].toLowerCase();
		for (int i = 1; i < splited_txt.length; i++) {
			splited_txt[i] = splited_txt[i].toLowerCase();
			bigrams.add(splited_txt[i-1] + "-" + splited_txt[i]);
		}
		return Optional.of(bigrams);
    }
    
    public static String getLanguage(Optional<ExtendedSimplifiedTweet> tweet){
        String lang = tweet.get().language;
        lang = lang.substring(1, lang.length()-1);
        return lang;
    }
    
    public static String changeFormat(Optional<ExtendedSimplifiedTweet> tweet){
        ExtendedSimplifiedTweet something = tweet.get();
        String line =
            "id: "+ something.tweetId +
                    "  " + "text: " + something.text +
                    "  " + "userId:  " + something.userId +
                    "  " + "userName: " +something.userName +
                    "  " + "followersCount: " + something.followersCount +
                    "  " + "language: " + something.language +
                    "  " + "isRetweeted: " + something.isRetweeted +
                    "  " + "retweetedUserId: " + something.retweetedUserId +
                    "  " + "retweetedTweetId: " + something.retweetedTweetId +
                    "  " + "timestampMs: " + something.timestampMs;
        return line;

    }
    
    public static Boolean checkfields(JsonObject o) {

        if (o.has("id") && o.has("text") && o.has("user") && o.has("followers_count") && o.has("lang") && o.has("retweeted") && o.has("timestamp_ms") && o.has("retweeted_status")) {
            JsonObject o2 = o.getAsJsonObject("user");
            JsonObject o3 = o.getAsJsonObject("retweeted_status");
            JsonObject o4 = o3.getAsJsonObject("user");
            return (o2.has("id") && o2.has("name") && o3.has("id") && o4.has("id"));
        } else {
            return false;
        }
    }
    
    public static Boolean isJson(String str) {
        try {
            jp.parse(str);
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    //IDENTIFY ORIGINAL TWEETS --> NOT RETWEETED
    public static Optional<ExtendedSimplifiedTweet> fromJson(String jsonStr) {
        // IMPLEMENT ME
        Optional<ExtendedSimplifiedTweet> opt = Optional.empty();
        if (isJson(jsonStr)) {
            JsonElement je = jp.parse(jsonStr);
            JsonObject jsonObject = je.getAsJsonObject();

            if (checkfields(jsonObject)) {

                JsonObject jObjects = jsonObject.getAsJsonObject("user");
                JsonObject rt_tweet = jsonObject.getAsJsonObject("retweeted_status");
                JsonObject rt_user = rt_tweet.getAsJsonObject("user");

                long a = jsonObject.get("id").getAsLong();
                String b = jsonObject.get("text").toString();
                long c = jObjects.get("id").getAsLong();   
                String d = jObjects.get("name").toString();
                long e = jObjects.get("followers_count").getAsLong();
                String f = jsonObject.get("lang").toString();
                boolean g = jsonObject.get("retweeted").getAsBoolean();
                long h = rt_user.get("id").getAsLong();
                long i = rt_tweet.get("id").getAsLong();
                long j = jsonObject.get("timestamp_ms").getAsLong();

                
            opt = Optional
                .ofNullable(a)
                .flatMap(text -> Optional.ofNullable(b))
                .flatMap(userId -> Optional.ofNullable(c))
                .flatMap(userName -> Optional.ofNullable(d))
                .flatMap(followers_count-> Optional.ofNullable(e))
                .flatMap(lang -> Optional.ofNullable(f))
                .flatMap(isRet -> Optional.ofNullable(g))
                .flatMap(retUserId -> Optional.ofNullable(h))
                .flatMap(retTweetId -> Optional.ofNullable(i))
                .flatMap(time -> Optional.ofNullable(j))
                .map(z -> new ExtendedSimplifiedTweet(a, b, c, d, e, f, g, h, i, j));   
            
            }
        } else System.out.println("The string is an invalid json.");
        
        return opt;
        
    }
}



