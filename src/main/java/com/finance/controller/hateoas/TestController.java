package com.finance.controller.hateoas;

import com.finance.controller.UserController;
import com.finance.domain.dto.PasswordChangerDto;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

@RestController
public class TestController {

    private static final String TEMPLATE = "Hello, %s!";

    @RequestMapping("/testhateoas")
    public HttpEntity<TestObj> testObj(@RequestParam(value = "name", defaultValue = "World") String name) {
        TestObj testObj = new TestObj(String.format(TEMPLATE, name));

        testObj.add(linkTo(methodOn(TestController.class).testObj(name)).withSelfRel());

        return new ResponseEntity<>(testObj, HttpStatus.OK);
    }
}
