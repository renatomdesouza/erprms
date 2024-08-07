package br.com.erprms.controllerAdapter.personController.personQualificationController;
import static br.com.erprms.serviceApplication.personService.SpecifiedQualificationConstants.RESPONSIBLE_FOR_LEGAL_PERSON;

import br.com.erprms.serviceApplication.personService.personQualificationHttpVerbService.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.NonNull;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.erprms.dtoPort.personDto.personQualificationDto.PersonQualificationOutputDtoInterface;
import br.com.erprms.dtoPort.personDto.personQualificationDto.responsibleForLegalPersonDto.internalDto_ResponsibleForLegalPerson.InputDtoClass_ResponsibleForLegalPerson;
import br.com.erprms.dtoPort.personDto.personQualificationDto.responsibleForLegalPersonDto.InputDtoRecord_ResponsibleForLegalPerson;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.transaction.Transactional;

@RestController
@RequestMapping(RESPONSIBLE_FOR_LEGAL_PERSON)
@SecurityRequirement(name = "bearer-key")
public class ResponsibleForLegalPersonController {
    private final PersonQualificationService_HttpPost personQualificationService_HttpPost;
    private final PersonQualificationService_HttpGet personQualificationService_HttpGet;
    private final PersonQualificationService_HttpPut personQualificationService_HttpPut;
    private final PersonQualificationService_HttpDelete personQualificationService_HttpDelete;

    public ResponsibleForLegalPersonController (
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
            @RequestBody InputDtoRecord_ResponsibleForLegalPerson responsibleRecordDto,
            UriComponentsBuilder uriComponentsBuilder)
            throws ResponseStatusException {
        return personQualificationService_HttpPost.registerService(
                new InputDtoClass_ResponsibleForLegalPerson(responsibleRecordDto),
                uriComponentsBuilder,
                RESPONSIBLE_FOR_LEGAL_PERSON);
    }

    @GetMapping
    @Transactional
    public ResponseEntity<Page<? extends PersonQualificationOutputDtoInterface>> listing(
            @PageableDefault(size = 10) Pageable qualificationPageable,
            UriComponentsBuilder uriComponentsBuilder) {
        return personQualificationService_HttpGet.listingService(
                qualificationPageable,
                uriComponentsBuilder,
                RESPONSIBLE_FOR_LEGAL_PERSON);
    }

    @PutMapping
    @Transactional
    public ResponseEntity<? extends PersonQualificationOutputDtoInterface> update(
            @RequestBody InputDtoRecord_ResponsibleForLegalPerson resposibleRecordDto,
            UriComponentsBuilder uriComponentsBuilder)
            throws ResponseStatusException {
        return	personQualificationService_HttpPut.update(
                new InputDtoClass_ResponsibleForLegalPerson(resposibleRecordDto),
                uriComponentsBuilder,
                RESPONSIBLE_FOR_LEGAL_PERSON);
    }

    @DeleteMapping("/{person_Id}")
    @Transactional
    public ResponseEntity<? extends PersonQualificationOutputDtoInterface> exclude(
            @NonNull @PathVariable Long person_Id,
            UriComponentsBuilder uriComponentsBuilder)
            throws ResponseStatusException {
        return personQualificationService_HttpDelete.exclude(	person_Id,
                uriComponentsBuilder,
                RESPONSIBLE_FOR_LEGAL_PERSON);
    }
}
