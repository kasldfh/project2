import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

public class ThirdReducer extends Reducer<Text, Text, Text, Text> {
	public Text pageKey = new Text();
	public Text pageValue = new Text();
	public List<String> listValue = new ArrayList<String>();
	public Integer intValue;
    
	public void reduce(Text key, Iterable<Text> values, Context context)
			throws IOException, InterruptedException {
        String largest, delim = " ";
        int largestSize = 0;
        while(values.hasNext())
        {
            String clique = values.next();
            String split[] = clique.split(delim);        
            if(split.length > largestSize)
            {
                largestSize = split.length;
                largest = clique; 
            }   
        }
        context.write(largestSize, largest);
        //or...
        String split[] = largest.split(delim);
        for(int i = 0; i < largest.length; i++)
        {
            context.write(one, split[i];
        } 
	}
}
