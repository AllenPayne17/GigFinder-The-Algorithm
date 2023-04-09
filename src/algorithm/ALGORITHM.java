package algorithm;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import com.google.gson.Gson;
import java.util.ArrayList;

import java.util.Date;

public class ALGORITHM {

    public static void main(String[] args) {

        try {
            // Send GET request to API URL
            URL url = new URL("https://gigfinder.onrender.com/api/job-posts");
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod("GET");

            // Read response from API
            BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
            String inputLine;
            StringBuffer data = new StringBuffer();
            while ((inputLine = in.readLine()) != null) {
                data.append(inputLine);
            }
            in.close();
            //----------------------------------------------------------------------------------------------||
//             System.out.println(data);
            Gson gson = new Gson();
            
            Job[] job = gson.fromJson(data.toString(), Job[].class);
            
     
            int[] date_index = new int[job.length];
            Date[] date_created = new Date[job.length]; 
            ArrayList<Integer> on_campus_index = new ArrayList<>();
            ArrayList<Integer> food_service_index = new ArrayList<>();
            ArrayList<Integer> tutor_index = new ArrayList<>();
           
            for(int i = 0; i < job.length ; i++){
               date_index[i] = i;
               date_created[i] =  job[i].get_createdAt();
               switch (job[i].get_work_category()[0]) {
                    case "on-campus part-time job":
                        on_campus_index.add(i);
                        break;
                    case "food-service part-time job":
                        food_service_index.add(i);
                        break;
                    case "one-on-one tutor part-time job":
                        tutor_index.add(i);
                        break;
                    default:
                        break;
                }
            }

            System.out.print(tutor_index.toString());
            System.out.print(on_campus_index.toString());
            System.out.print(food_service_index.toString());
           
            quickSort(date_created, 0, job.length -1, date_index);
            System.out.println();
            for(int j = 0; j < job.length ; j++){
                System.out.println(date_created[j]);
                
            }
            
             for(int j = 0; j < job.length ; j++){
                System.out.print(date_index[j] + " ");
                
            }

            //System.out.println(job[0].get_work_category()[0]);
           //System.out.println(job[0].get_createdAt());
           /*
           for loop         retrieve work category
           
           arrays: 4
           all - stored indexes of data (based on created date, compare dates, most recent to least recent)
           on campus - part time
           one-on-one mentor
           food services
           
           requirements:
           Stack
           ArrayList
           
           store index of data
           */
            //----------------------------------------------------------------------------------------------||
        } catch (Exception e){ 
            e.printStackTrace();
        }
    }
    
    
    static int partition(Date[] date_created, int low, int high, int[] date_index) {
    Date pivot = date_created[high];
    int i = (low - 1);

    for (int j = low; j < high; j++) {
        if(date_created[j].compareTo(pivot) > 0){
        i++;

        Date temp = date_created[i];
        date_created[i] = date_created[j];
        date_created[j] = temp;
        
        int temp_index = date_index[i];
        date_index[i] = date_index[j];
        date_index[j] = temp_index;
        }
       }
    Date temp = date_created[i + 1];
    date_created[i + 1] = date_created[high];
    date_created[high] = temp;
    
    int temp_index = date_index[i + 1];
    date_index[i+1] = date_index[high];
    date_index[high] = temp_index;
    return (i + 1);
    }
    
    private static void quickSort(Date[] date_created, int low, int high, int[] date_index){
        if(low < high){
        int pi = partition(date_created, low, high, date_index);
         quickSort(date_created, low, pi-1, date_index); 
         quickSort(date_created, pi + 1, high, date_index);
        }
    }  
}