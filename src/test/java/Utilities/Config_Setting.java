package Utilities;

import EndPoints.AllEndPoints;

import org.testng.annotations.BeforeClass;

import java.io.FileInputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Properties;
import java.util.Random;

public class Config_Setting implements AllEndPoints {

    public static Properties config;
    public static Properties baseURL;
    public static Properties Key;
    public static Properties Token;


    public Properties config_reader(String FilePath){

        try {
            config = new Properties();
            config.load(new FileInputStream(FilePath));
        }
        catch (Exception e){
            System.out.println(e.getMessage());
            System.exit(1);

        }
        return config;

    }

    @BeforeClass
    public void SetConfigValue(){

        baseURL = config_reader("resources/testBaseURL.properties");

    }

    public static void waitInSeconds(int secVal) {
        try {
            Thread.sleep(secVal * 1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }






}

