import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.input.TextInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.apache.hadoop.mapreduce.lib.output.TextOutputFormat;

public class MaxCliques {

	public static void main(String[] args) throws Exception {
		final String OUTPUT_PATH = "intermediate_output";
		final String OUTPUT_PATH1 = "intermediate_output1";
        
        //set up and run the first MapReduce cycle
		Configuration conf = new Configuration();
		Job job = new Job(conf, "Job");//job1 configuration that controls the first job
		job.setJarByClass(MaxCliques.class);
		/* 
         Set the Mapper and Reducer output types for job configuration
        */
        job.setMapOutputKeyClass(Text.class);
		job.setMapOutputValueClass(Text.class);
		job.setOutputKeyClass(Text.class);
		job.setOutputValueClass(IntWritable.class);
		job.setMapperClass(FirstMapper.class);//Job uses first mapper class
		job.setReducerClass(FirstReducer.class);//Job uses first reducer class
		job.setInputFormatClass(TextInputFormat.class);
		job.setOutputFormatClass(TextOutputFormat.class);
		//job.setNumReduceTasks(128);//Distributes the mapper output to specified reduce tasks
		FileInputFormat.addInputPath(job, new Path(args[0]));
		FileOutputFormat.setOutputPath(job, new Path(OUTPUT_PATH));
		job.waitForCompletion(true);
        
        //End of Job. chain job to job2
        //set up and run the second MapReduce cycle
		Configuration conf2 = new Configuration();
		Job job2 = new Job(conf2, "Job2");//job2 configuration that controls the first job
		job2.setJarByClass(MaxCliques.class);// Set Hadoop to look out for WikiStats.class in Jar file
        /* 
         Set the Mapper and Reducer output types for job2 configuration
         */
        job2.setMapOutputKeyClass(Text.class);
		job2.setMapOutputValueClass(Text.class);
		job2.setOutputKeyClass(Text.class);
		job2.setOutputValueClass(IntWritable.class);
		job2.setMapperClass(SecondMapper.class);//Job2 uses first mapper class
		job2.setReducerClass(SecondReducer.class);//Job2 uses first mapper class
		job2.setInputFormatClass(TextInputFormat.class);
		job2.setOutputFormatClass(TextOutputFormat.class);
		job2.setNumReduceTasks(64);//Distributes the mapper output to specified reduce tasks
		FileInputFormat.addInputPath(job2, new Path(OUTPUT_PATH));
		FileOutputFormat.setOutputPath(job2, new Path(OUTPUT_PATH1));
		job2.waitForCompletion(true);
        
        //End of Job. chain job2 to job3
        //set up and run the third MapReduce cycle
		Configuration conf3 = new Configuration();
		Job job3 = new Job(conf3, "Job3");//job3 configuration that controls the first job in Jar file
		job3.setJarByClass(MaxCliques.class);//Set Map output key as text
        /* 
         Set the Mapper and Reducer output types for job3 configuration
         */
		job3.setMapOutputKeyClass(Text.class);
		job3.setMapOutputValueClass(Text.class);
		job3.setOutputKeyClass(Text.class);
		job3.setOutputValueClass(IntWritable.class);
		job3.setMapperClass(ThirdMapper.class);//Job3 uses first mapper class
		job3.setReducerClass(ThirdReducer.class);//Job3 uses first mapper class
		job3.setInputFormatClass(TextInputFormat.class);
		job3.setOutputFormatClass(TextOutputFormat.class);
		FileInputFormat.addInputPath(job3, new Path(OUTPUT_PATH1));
		FileOutputFormat.setOutputPath(job3, new Path("output"));
		job3.waitForCompletion(true);
        //End of job3
        
        
        //End of Job. chain job3 job4
        //set up and run the fourth MapReduce cycle

		Configuration conf3 = new Configuration();
		Job job3 = new Job(conf3, "Job3");//job3 configuration that controls the first job in Jar file

        /* 
         Set the Mapper and Reducer output types for job3 configuration
         */
        job4.setMapOutputKeyClass(Text.class);
        job4.setMapOutputValueClass(Text.class);
        job4.setOutputKeyClass(Text.class);
        job4.setOutputValueClass(IntWritable.class);
        job4.setMapperClass(FourthMapper.class);//Job3 uses first mapper class
        job4.setReducerClass(FourthReducer.class);//Job3 uses first mapper class
        job4.setInputFormatClass(TextInputFormat.class);
        job4.setOutputFormatClass(TextOutputFormat.class);
        FileInputFormat.addInputPath(job3, new Path(OUTPUT_PATH1));
        FileOutputFormat.setOutputPath(job3, new Path("output"));
        job4.waitForCompletion(true); //End of job3 } }
