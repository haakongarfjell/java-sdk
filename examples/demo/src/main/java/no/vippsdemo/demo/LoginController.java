package no.vippsdemo.demo;

import io.github.cdimascio.dotenv.Dotenv;
import no.vipps.infrastructure.VippsConfiguration;
import no.vipps.infrastructure.VippsConfigurationOptions;
import no.vipps.model.login.AuthenticationMethod;
import no.vipps.model.login.StartLoginUriRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import no.vipps.services.LoginService;


@Controller
public class LoginController {

    @RequestMapping(value = "/LoginUri", method = RequestMethod.GET)
    @ResponseBody
    public String GetStartLoginUri()
    {
        StartLoginUriRequest startLoginUriRequest = new StartLoginUriRequest(
                "openid email name phoneNumber",
                "http://localhost:3000",
                AuthenticationMethod.Basic
        );
        return LoginService.GetStartLoginUri(startLoginUriRequest);
    }
}
