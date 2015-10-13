package twittertest;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

import twitter4j.*;
import twitter4j.conf.ConfigurationBuilder;

public class testingTwitter {
	
	private static void generateCsvFile(FileWriter writer,String username,String content)
	{
		    try {
				
		    writer.append(username);
		    content = content.replaceAll("\n", " ");
		    content = content.replaceAll(",", "");
		    writer.append(',');
		    writer.append(content);
		    writer.append(',');
		    writer.append("");
		    writer.append('\n');
		    writer.flush();
		    } 
		    catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

	}
	public static void main(String[] args) throws TwitterException, IOException{
		ConfigurationBuilder cb = new ConfigurationBuilder();
		cb.setDebugEnabled(true)
		  .setOAuthConsumerKey("dFiRSJUUVPkB11s1QExT1q6DL")
		  .setOAuthConsumerSecret("VuGhGguFesQ30g2LLeWYdSaA8B3QYhI1kOaRGMHNiC3pmiXj13")
		  .setOAuthAccessToken("91903648-9GUifKt8cyThSO7IuHN9Fo3au3NqW4D5YjkzM6hAO")
		  .setOAuthAccessTokenSecret("PjRxvz7izdtvrm83UJUwwuBi0bIjiTHFpzzB3orEGaNOQ");
		TwitterFactory tf = new TwitterFactory(cb.build());
		Twitter twitter = tf.getInstance();
		String file = "C:\\Users\\A00227178\\POC.csv";
		FileWriter writer = new FileWriter(file);
	    try {
            Query query = new Query("rwc2015");
            QueryResult result;
            do {
                result = twitter.search(query);
                List<Status> tweets = result.getTweets();
                for (Status tweet : tweets) {
                	
                		
                			System.out.println("@" + tweet.getUser().getScreenName() + " - " + tweet.getText());
                			generateCsvFile(writer, tweet.getUser().getScreenName(), tweet.getText());
                			System.out.println(result.getTweets().size());
                		
                }   
            } while ((query = result.nextQuery()) != null);

            writer.close();
		    
            System.exit(0);
        } catch (TwitterException te) {
            te.printStackTrace();
            System.out.println("Failed to search tweets: " + te.getMessage());
            System.exit(-1);
        }
	}
}