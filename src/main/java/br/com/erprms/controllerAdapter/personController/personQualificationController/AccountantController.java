package br.com.erprms.controllerAdapter.personController.personQualificationController;

import static br.com.erprms.serviceApplication.personService.SpecifiedQualificationConstants.ACCOUNTANT;

import br.com.erprms.dtoPort.personDto.personQualificationDto.PersonQualificationOutputDtoInterface;
import br.com.erprms.dtoPort.personDto.personQualificationDto.accountantDto.InputDtoClass_Accountant;
import br.com.erprms.dtoPort.personDto.personQualificationDto.accountantDto.InputDtoRecord_Accountant;
import br.com.erprms.serviceApplication.personService.personQualificationHttpVerbService.*;
import jakarta.transaction.Transactional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.NonNull;
import org.springframework.web.bind.annotation.*;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping(ACCOUNTANT)
@SecurityRequirement(name = "bearer-key")
public class AccountantController {
    private final PersonQualificationService_HttpPost personQualificationService_HttpPost;
    private final PersonQualificationService_HttpGet personQualificationService_HttpGet;
    private final PersonQualificationService_HttpPut personQualificationService_HttpPut;
    private final PersonQualificationService_HttpDelete personQualificationService_HttpDelete;

    public AccountantController (
            PersonQualificationService_HttpPost personQualificationService_HttpPost,
            PersonQualificationService_HttpGet personQualificationService_HttpGet,
            PersonQualificationService_HttpPut personQualificationService_HttpPut,
            PersonQualificationService_HttpDelete personQualificationService_HttpDelete) {
        this.personQualificationService_HttpPost = personQualificationService_HttpPost;
        this.personQualificationService_HttpGet = personQualificationService_HttpGet;
        this.personQualificationService_HttpPut = personQualificationService_HttpPut;
        this.personQualificationService_HttpDelete = personQualificationService_HttpDelete;
    }

    @PostMapping
    @Transactional
    public ResponseEntity<? extends PersonQualificationOutputDtoInterface> register(
            @RequestBody InputDtoRecord_Accountant accountantRecordDto,
            UriComponentsBuilder uriComponentsBuilder)
            throws ResponseStatusException {
        return personQualificationService_HttpPost.registerService(
                new InputDtoClass_Accountant(accountantRecordDto),
                uriComponentsBuilder,
                ACCOUNTANT);
    }

    @GetMapping
    @Transactional
    public ResponseEntity<Page<? extends PersonQualificationOutputDtoInterface>> listing(
            @PageableDefault(size = 10) Pageable qualificationPageable,
            UriComponentsBuilder uriComponentsBuilder) {
        return personQualificationService_HttpGet.listingService(
                qualificationPageable,
                uriComponentsBuilder,
                ACCOUNTANT);
    }

    @PutMapping
    @Transactional
    public ResponseEntity<? extends PersonQualificationOutputDtoInterface> update(
            @RequestBody InputDtoRecord_Accountant accountantRecordDto,
            UriComponentsBuilder uriComponentsBuilder)
            throws ResponseStatusException {
        return	personQualificationService_HttpPut.update(
                new InputDtoClass_Accountant(accountantRecordDto),
                uriComponentsBuilder,
                ACCOUNTANT);
    }

    @DeleteMapping("/{person_Id}")
    @Transactional
    public ResponseEntity<? extends PersonQualificationOutputDtoInterface> exclude(
            @NonNull @PathVariable Long person_Id,
            UriComponentsBuilder uriComponentsBuilder)
            throws ResponseStatusException {
        return personQualificationService_HttpDelete.exclude(	person_Id,
                uriComponentsBuilder,
                ACCOUNTANT);
    }

}
