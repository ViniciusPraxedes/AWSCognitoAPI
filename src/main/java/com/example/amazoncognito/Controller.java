package com.example.amazoncognito;

import com.amazonaws.services.cognitoidp.model.AdminDeleteUserResult;
import com.amazonaws.services.cognitoidp.model.AdminDeleteUserRequest;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import com.amazonaws.services.cognitoidp.AWSCognitoIdentityProvider;
import com.amazonaws.services.cognitoidp.AWSCognitoIdentityProviderClientBuilder;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.regions.Regions;

@org.springframework.stereotype.Controller
@AllArgsConstructor
@NoArgsConstructor
public class Controller {

    String accessKey = "${accessKey}";
    String secretKey = "${secretKey}";
    String regionName = "eu-north-1";

    @RequestMapping(path = "/index", method = RequestMethod.GET)
    public String getIndexPage(){
        return "index";
    }
    @RequestMapping(path = "/", method = RequestMethod.GET)
    public String getLoginPage(){
        return "login";
    }
    @RequestMapping(path = "/logout", method = RequestMethod.GET)
    public String getLogoutPage(){
        return "logout";
    }


    @RequestMapping(path = "/delete", method = RequestMethod.GET)
    public String getDeleteUserPage(){
        return "delete";
    }
    @RequestMapping(path = "submitDelete", method = RequestMethod.GET)
    public String submitDelete(@RequestParam String email){

        String userPoolId = "${userPoolId}";

        AdminDeleteUserRequest deleteUserRequest = new AdminDeleteUserRequest()
                .withUserPoolId(userPoolId)
                .withUsername(email);

        AdminDeleteUserResult deleteUserResult = cognitoClient.adminDeleteUser(deleteUserRequest);

        return "success";
    }

    BasicAWSCredentials awsCredentials = new BasicAWSCredentials(accessKey, secretKey);
    AWSStaticCredentialsProvider credentialsProvider = new AWSStaticCredentialsProvider(awsCredentials);

    AWSCognitoIdentityProvider cognitoClient = AWSCognitoIdentityProviderClientBuilder.standard()
            .withCredentials(credentialsProvider)
            .withRegion(Regions.fromName(regionName))
            .build();


}
