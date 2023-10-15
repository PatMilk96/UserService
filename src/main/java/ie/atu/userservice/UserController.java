package ie.atu.userservice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
public class UserController {
    private RegistrationServiceClient registrationServiceClient;
    private final AcknowledgeService acknowledgeService;

    @Autowired
    public UserController(AcknowledgeService acknowledgeService, RegistrationServiceClient registrationServiceClient){
        this.registrationServiceClient = registrationServiceClient;
        this.acknowledgeService = acknowledgeService;
    }

    @PostMapping("/confirm-and-register")
    public String confirmAndRegister(@RequestBody UserDetails userDetails){
        String confirm = registrationServiceClient.details(userDetails);
        return (confirm + ",\n" + acknowledgeService.ackMessage(userDetails));
    }
}
