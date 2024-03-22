package gr.tses.service;


import java.util.List;
import java.util.Map;

import java.util.stream.Collectors;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.kie.kogito.rules.RuleUnit;
import org.kie.kogito.rules.RuleUnitInstance;




@Path("/hello")
public class GreetingResourceMLib {

    @Inject
    RuleUnit<gr.tses.lib1.Hello> ruleUnit1;

     @Inject
     RuleUnit<gr.tses.lib2.Hello> ruleUnit2;



    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String hello() {
       
        gr.tses.lib1.Hello hello1 = new gr.tses.lib1.Hello();
        hello1.getStrings().add("hello");
        RuleUnitInstance<gr.tses.lib1.Hello> instance1 = ruleUnit1.createInstance(hello1);

        List<Map<String, Object>> a1 = 
        instance1.executeQuery("hello");
   
        String lib1Values = a1.stream()
        .flatMap(map -> map.values().stream()) 
        .map(Object::toString)
        .collect(Collectors.joining(" "));


        String lib2Values = "";

        gr.tses.lib2.Hello hello2 = new gr.tses.lib2.Hello();
        hello2.getStrings().add("hello");
        RuleUnitInstance<gr.tses.lib2.Hello> instance2 = ruleUnit2.createInstance(hello2);

        List<Map<String, Object>> a2 = 
        instance2.executeQuery("hello");
        
        lib2Values = a2.stream()
        .flatMap(map -> map.values().stream()) 
        .map(Object::toString)
        .collect(Collectors.joining(" "));
 
        
       return lib1Values + " " +lib2Values;
    }
}