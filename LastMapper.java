import java.io.IOException;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

public class FourthMapper extends Mapper<LongWritable, Text, Text, Text> {

	public void map(LongWritable key, Text value, Context context) throws IOException,
			InterruptedException {
		Text constantKey = new Text();
		constantKey.set("const"); //set constant key to send all records to same reducer
		context.write(constantKey, value);
	}

}

