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
		  .setOAuthConsumerKey("zg4Ipy0BXMucvp8Ct5R7igYds")
		  .setOAuthConsumerSecret("U9wpwN3ZHfYm5repbC2Rfz6dY31WI0LhW4qUcdPAW01ItVPSqC")
		  .setOAuthAccessToken("3874319477-LvOFbMnHwBEd2MPNBj8MWXtXaU5hwf0ctJtgpE2")
		  .setOAuthAccessTokenSecret("GpYwLKyRtG5AE5BzgOdcUdZph2fxuyTuYldtqY0cVOb2T");
		TwitterFactory tf = new TwitterFactory(cb.build());
		Twitter twitter = tf.getInstance();
		String file = "C:\\Users\\A00227178\\IREvsARG.csv";
		FileWriter writer = new FileWriter(file);
	    try {
            Query query = new Query("leeds");
            QueryResult result;
            query.setCount(-1);
            do {
                result = twitter.search(query);
                List<Status> tweets = result.getTweets();
                for (Status tweet : tweets) {
                	if(!tweet.getText().startsWith("RT")){
                		if(!tweet.getText().contains("http")){
                			System.out.println("@" + tweet.getUser().getScreenName() + " - " + tweet.getText());
                			generateCsvFile(writer, tweet.getUser().getScreenName(), tweet.getText());
                		}
                	}
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