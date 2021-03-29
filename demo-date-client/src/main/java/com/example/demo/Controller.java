package com.example.demo;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;
import java.util.concurrent.TimeUnit;

@RestController
@RequestMapping("/testDateClient")
public class Controller {
//    @RequestMapping("/test")
//    public ResponseEntity<?> home(){
//        return  ResponseEntity.ok("ok");
//    }

    @GetMapping(params = "date")
    public String localDate(@RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") final LocalDate date)  {
        return date.toString();
    }
    @GetMapping(params = "date1")
    public String getDate(@RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") final Date date1)  {
        Locale locale = Locale.getDefault();
        System.out.println(locale);
        SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");
        String date =  sf.format(date1);
        return date;
    }

    @PostMapping("/1")
    public void test(){
        String uri = "http://localhost:8080/testDate?date1=";
        RestTemplate restTemplate = new RestTemplate();
        Date d = new Date();

        String result = restTemplate.postForObject( uri, d, String.class);
    }

    @PostMapping("/2")
    public void test1(){
        String uri = "http://localhost:8080/testDate";
        RestTemplate restTemplate = new RestTemplate();
        Date d = new Date();

        String result = restTemplate.postForObject(uri, d,String.class);
    }
    private static String displayTimeZone(TimeZone tz) {

        long hours = TimeUnit.MILLISECONDS.toHours(tz.getRawOffset());
        long minutes = TimeUnit.MILLISECONDS.toMinutes(tz.getRawOffset())
                - TimeUnit.HOURS.toMinutes(hours);
        // avoid -4:-30 issue
        minutes = Math.abs(minutes);

        String result = "";
        if (hours > 0) {
            result = String.format("(GMT+%d:%02d) %s", hours, minutes, tz.getID());
        } else {
            result = String.format("(GMT%d:%02d) %s", hours, minutes, tz.getID());
        }

        return result;

    }

    @RequestMapping(value = "/3",method = RequestMethod.POST )
    public static void createEmployee()   {
        System.out.println("salam");

//        String[] ids = TimeZone.getAvailableIDs();
//        for (String id : ids) {
//            System.out.println(displayTimeZone(TimeZone.getTimeZone(id)));
//        }
//
//        System.out.println("\nTotal TimeZone ID " + ids.length);

        final String uri = "http://localhost:8080/testDate/employees";
        RestTemplate restTemplate = new RestTemplate();

        SimpleDateFormat isoFormat = new SimpleDateFormat("yyyy-MM-dd");
        isoFormat.setTimeZone(TimeZone.getTimeZone("GB"));
        Date date = null;
        try {
            date = isoFormat.parse("2016-05-01");

        } catch (ParseException e) {
//            System.out.println(e.getMessage());
        }
        date = new Date();


        EmployeeDto newEmployee = new EmployeeDto(1, "Adam",date);
        System.out.println(newEmployee.getBirthDate());
        System.out.println(newEmployee.getName());
        System.out.println(newEmployee.getId());
        EmployeeDto result = restTemplate.postForObject( uri, newEmployee, EmployeeDto.class);

        System.out.println(result);
    }
}