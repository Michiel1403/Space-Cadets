import java.io.*;
import java.net.URL;
public class LecturerFind {
        public static void main(String[] args) throws IOException {
                BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	        System.out.println("Please enter the username of the lecturer.");
	        String out = br.readLine();
	        String url = "https://www.ecs.soton.ac.uk/people/" + out;
                URL myUrl = new URL(url);
                myUrl.openConnection();
                String longStr = "";
                BufferedReader br2 = new BufferedReader(new InputStreamReader(myUrl.openStream(), "UTF-8"));
                InputStream is = myUrl.openStream();
                byte[] b = new byte[8];
                while(br2.ready()){
        	       longStr = longStr + br2.readLine();
                }
                System.out.println(longStr.substring((longStr.indexOf("property=\"name\"")+ 16), (longStr.indexOf("property=\"honorificSuffix\"") - 4)));
	}
}
