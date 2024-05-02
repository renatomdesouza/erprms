package br.com.erprms.controllerAdapter.personController.personQualificationController;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static br.com.erprms.serviceApplication.personService.SpecifiedQualificationConstants.PROVIDER;

@RestController
@RequestMapping(PROVIDER)
@SecurityRequirement(name = "bearer-key")
public class ProviderController {

}
