package org.example;

import org.example.saltfish.annotations.Autowired;
import org.example.saltfish.annotations.Component;
import org.example.saltfish.annotations.Scope;

@Component
//@Scope("prototype")
public class userService {

    @Autowired
    private testService testService;
    public void xxx(){
        System.out.println(testService);
    }

}
