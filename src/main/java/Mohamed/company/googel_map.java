package Mohamed.company;
import com.google.maps.ElevationApi;
import com.google.maps.GeoApiContext;
import com.google.maps.GeocodingApi;
import com.google.maps.model.ElevationResult;
import com.google.maps.model.GeocodingResult;
import com.google.maps.model.LatLng;

import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.FileReader;
import java.util.Scanner;

/**
 * Created by mash4 on 3/7/2017.
 */
public class googel_map {
    //Reads user inputs
    static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) throws  Exception {
        String key = null; // Initailzing key
        try{ // Reader that reads a key value
            BufferedReader reader = new BufferedReader(new FileReader("key.txt"));
            key = reader.readLine(); //next line reading

        }
        catch (Exception ioe){ //General exception
            System.out.println("No key found, or could n't read key. Please verify key.txt present ");
            System.exit(-1);
        }

        System.out.println("Please enter an address : "); //User inputs
        GeoApiContext context = new GeoApiContext().setApiKey(key);
        String myAdrress = sc.nextLine();
        GeocodingResult result [] = GeocodingApi.geocode(context, myAdrress).await();
        LatLng r = (result[0].geometry.location);
        ElevationResult [] t = ElevationApi.getByPoints(context, r).await();
        if(result.length >= 1){
            ElevationResult addElevation = t[0];
            System.out.println("The elevation of  " + myAdrress + " above sea level is " +
                    addElevation.elevation + " meters.");
            System.out.println(String.format("The elevation of "+ myAdrress + " above sea level is %.2f meters ",addElevation.elevation));
        }

    }
}




