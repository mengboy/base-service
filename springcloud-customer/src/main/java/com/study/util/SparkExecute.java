package com.study.util;

import org.apache.spark.deploy.SparkSubmit;

import java.text.SimpleDateFormat;
import java.util.Date;


/*spark-submit --class SparkCount --master local[2]  file:///Users/wang1/jar/untitled.jar*/
public class SparkExecute {

    public  void sparksubmit()
    {

        String[] arg0=new String[]{
                "--master","local[2]",
                "--deploy-mode","client",
                "--name","test java submit job to spark",
                "--class","SparkCount",
              "file:///Users/wang1/jar/untitled.jar"
        };
        SparkSubmit.main(arg0);

    }
    public static void main(String[] args) {

        SparkExecute s=new SparkExecute();
        s.sparksubmit();
    }
}
