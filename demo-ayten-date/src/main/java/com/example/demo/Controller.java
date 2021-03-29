package com.example.demo;

import org.apache.tomcat.jni.Local;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Locale;

@RestController
@RequestMapping("/testDate")
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
    public String getDate(@RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") final Date date1) {
        Locale locale = Locale.getDefault();
        System.out.println("before "+date1);
        System.out.println(locale);
        SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");
        String date = sf.format(date1);
        System.out.println("after " + date);
        return date;
        }

    @PostMapping(value="/employees")
    public ResponseEntity<EmployeeDto> createEmployee(@RequestBody  EmployeeDto dto) {
        System.out.println(dto);
        Locale locale = Locale.getDefault();
        System.out.println(locale);
//        LocalDate date1 =
        SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");
        String date = sf.format(dto.getBirthDate());
        System.out.println(date);
        System.out.println("create employee");
        System.out.println(dto.getBirthDate());
        System.out.println(dto.getId());
        System.out.println(dto.getName());
        return ResponseEntity.ok(dto);
    }


//    @PostMapping(params = "datePost")
    @PostMapping
    public ResponseEntity<?> sendDate(@RequestParam @DateTimeFormat(pattern = "yyyy.MM.dd") final Date datePost) {
        Locale locale = Locale.getDefault();
        System.out.println(locale);
        SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");
        String date = sf.format(datePost);
        return ResponseEntity.ok(date);
    }

    @PostMapping("/v1")
    public ResponseEntity<?> sendDate1(@RequestParam(required = false)  final Date datePost) {
        Locale locale = Locale.getDefault();
        System.out.println(locale);
        SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");
        String date = sf.format(datePost);
        return ResponseEntity.ok(date);
    }

    @GetMapping("/v2")
    public ResponseEntity<?> getDate1(@RequestParam(required = false ,name = "dpv2")
                                           final LocalDate datePost1) {
//        @DateTimeFormat(pattern = "MM/dd/yyyy")
                System.out.println("v2 datePost1= "+datePost1);
        String date = null;
//        Locale locale = Locale.getDefault();
//        System.out.println(locale);
//        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd");
//
////        SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");
//        if(datePost1 !=null){
//            System.out.println("nulldan ferqlidir");
//            date ="ok";
//             date = LocalDate.now().format(formatter);
//        } else {
//            System.out.println("LocalDate.now() "+LocalDate.now());
////            date  = sf.format(LocalDate.now());
//            date="noOK";
//        }
        return ResponseEntity.ok("date");
    }

    @GetMapping("/v3")
    public ResponseEntity<?> getDate2(@RequestParam(required = false ,name = "dpv3" )
                                            final Date datePost1) {
        System.out.println("v3 datePost1= "+datePost1);
        String date = null;
//        Locale locale = Locale.getDefault();
//        System.out.println(locale);
//        SimpleDateFormat sf = new SimpleDateFormat("dd.mm.yyyy");
//        if(datePost1!=null){
//            date ="ok";
////            date= sf.format(datePost1);
//        } else {
//            date ="noOk";
////            date = sf.format(new Date());
//        }
        return ResponseEntity.ok("date");
    }

     @GetMapping("/1")
    public ResponseEntity<String> test(){
             return ResponseEntity.ok("salam");
        }

//    @InitBinder
//    public void initBinder(WebDataBinder binder) {
//        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy.MM.dd"); //yyyy-MM-dd'T'HH:mm:ssZ example
//        dateFormat.setLenient(false);
//        binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, false));
//    }


}

