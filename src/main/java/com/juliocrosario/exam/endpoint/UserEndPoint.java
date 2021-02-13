package com.juliocrosario.exam.endpoint;


import com.juliocrosario.exam.model.user.Exam;
import com.juliocrosario.exam.model.user.InputRequest;
import com.juliocrosario.exam.model.user.OutputRequest;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

@Endpoint
public class UserEndPoint {

   public final static String NAME_SPACE = "http://juliocrosario.com/exam/model/user.xsd";

    @PayloadRoot(namespace = NAME_SPACE,
    localPart = "InputRequest")
    @ResponsePayload
    public OutputRequest inputRequest(@RequestPayload InputRequest request){

        System.out.println("InputRequest: " + request);
        OutputRequest outputRequest = new OutputRequest();


        Exam exam =  request.getExam();

        double total = exam.getTest1() + exam.getTest2() + exam.getTest3();

        outputRequest.setGrade(total /3 );

        return outputRequest;
    }

}
