package br.com.erprms.controllerAdapter.personController.personQualificationController;

//import org.modelmapper.ModelMapper;
//import org.modelmapper.TypeToken;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.data.domain.Page;
//import org.springframework.data.domain.Pageable;
//import org.springframework.transaction.annotation.Transactional;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.PutMapping;
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
//
//import br.com.erprms.DtoPort.PessoasDto.QualificacoesPessoaisDto.PersonalQualificationsClassDto;
//import br.com.erprms.DtoPort.PessoasDto.QualificacoesPessoaisDto.AccountantDto.AccountantListingDto;
//import br.com.erprms.DtoPort.PessoasDto.QualificacoesPessoaisDto.AccountantDto.AccountantUpdateDto;
//import br.com.erprms.DtoPort.PessoasDto.QualificacoesPessoaisDto.AccountantDto.AccountantRegistryDto.AccountantRegistryClassDto;
//import br.com.erprms.DtoPort.PessoasDto.QualificacoesPessoaisDto.AccountantDto.AccountantRegistryDto.AccountantRegistryRecordDto;
//import br.com.erprms.DtoPort.PessoasDto.QualificacoesPessoaisDto.ManagerDto.ManagerListingDto;
//import br.com.erprms.RepositoryAdapter.PersonRepository.PersonRepository;
//import br.com.erprms.ServiceApplication.Tests.TestAspectAnotation;
//import br.com.erprms.domainModel.personDomain.personComplement.StatusPersonalUseEnum;
//
//@RestController("accountantControllerBean")
//@RequestMapping("accountant")
//public class AccountantController {
////	final private AccountantRepository accountantRepository;
////	final private PersonRepository pessFisicRepository;
////	final private PessJuridicRepository pessJuridicRepository;
//	final private ModelMapper modelMapper;
//	
//	@Autowired
//	public AccountantController(/*AccountantRepository accountantRepository,*/ ModelMapper modelMapper
//			, PersonRepository pessFisicRepository/*, PessJuridicRepository pessJuridicRepository*/) {
////		this.accountantRepository = accountantRepository;
////		this.pessFisicRepository = pessFisicRepository;
////		this.pessJuridicRepository = pessJuridicRepository;
//		this.modelMapper = modelMapper;
//	}
//	
//	@PostMapping
//	@Transactional
//	@TestAspectAnotation
//	public void register(@RequestBody AccountantRegistryRecordDto accountantRegistryDto) {
//		registerServiceTest(accountantRegistryDto);
//	}
//	
//	@GetMapping
//	public Page<AccountantListingDto> acconuntantLinting(Pageable acconuntantListingPageable) {
//		return acconuntantLintingService(acconuntantListingPageable);
//	}
//	
//	@PutMapping
//	@Transactional
//	public void managerUpdate (@RequestBody AccountantUpdateDto acconuntantUpdateDto) {
//		managerUpdateService(acconuntantUpdateDto);
//	}
//	
////	@DeleteMapping("/{id}")
////	@Transactional
////	public void managerDeletion (@PathVariable Long id) {
////		managerDeletionService(id);
////	}
//
//	
//	private void registerServiceTest(AccountantRegistryRecordDto accountantRegistryDto){   // AccountantRegistryClassDto accountantRegistryClassDto
//		var accountantRegistryClassDto = new AccountantRegistryClassDto(accountantRegistryDto);
////		setUpdatePessFisicOrPessJuridic(accountantRegistryClassDto, pessFisicRepository, pessJuridicRepository);
////		accountantRepository.save(modelMapper.map(accountantRegistryClassDto, AccountantEntity.class));
//	}
//	
//	private Page<AccountantListingDto> acconuntantLintingService(Pageable acconuntantListingPageable){
////		Page<AccountantEntity> managerEntityPage = accountantRepository.findAll(acconuntantListingPageable);
////		Type acconuntantListingDtoType  = new TypeToken<Page<ManagerListingDto>>(){}.getType();
//		return null;
////				modelMapper.map(managerEntityPage, acconuntantListingDtoType);
//	}
//
//	private void managerUpdateService(AccountantUpdateDto acconuntantUpdateDto) {
////		modelMapper.map(acconuntantUpdateDto, accountantRepository.getReferenceById(acconuntantUpdateDto.id()));
//	}
//	
////	private void managerDeletionService(Long id) {
////		accountantRepository.getReferenceById(id).setStatusPessEnum(StatusPessEnum.INATIVO);
////	}
//	
//	private void setUpdatePessFisicOrPessJuridic(PersonalQualificationsClassDto personalQualificationsClassDto
//			, PersonRepository pessFisicRepository
////			, PessJuridicRepository pessJuridicRepository
//		) {
//		if (personalQualificationsClassDto.getPessFisicId() != null) {
//			var pessFisicEntity = pessFisicRepository.getReferenceById(personalQualificationsClassDto.getPessFisicId()); //findById(qualifRegistryPeopleDto.getPessFisicId());  
//			pessFisicEntity.setStatusPersonEnum(StatusPersonalUseEnum.USED);
//			pessFisicRepository.save(pessFisicEntity);
//		}
//		
////		if (personalQualificationsClassDto.getPessJuridicId() != null) {
////			var pessJuridicEntity = pessJuridicRepository.getReferenceById(personalQualificationsClassDto.getPessFisicId());  
////			pessJuridicEntity.setStatusPessEnum(StatusPessEnum.ACTIVE);
////			pessJuridicRepository.save(pessJuridicEntity);
////		}
//	}
//}
//
