package br.com.erprms.controllerAdapter.personController.personQualificationController;
//package br.com.erprms.controllerAdapter.personController.personQualificationController;
//
//import java.lang.reflect.Type;
//
//import org.modelmapper.ModelMapper;
//import org.modelmapper.TypeToken;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.data.domain.Page;
//import org.springframework.data.domain.Pageable;
//import org.springframework.transaction.annotation.Transactional;
//import org.springframework.web.bind.annotation.DeleteMapping;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PathVariable;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.PutMapping;
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
//
//import br.com.erprms.DtoPort.PessoasDto.QualificacoesPessoaisDto.ManagerDto.ManagerListingDto;
//import br.com.erprms.DtoPort.PessoasDto.QualificacoesPessoaisDto.ManagerDto.ManagerUpdateDto;
//import br.com.erprms.DtoPort.PessoasDto.QualificacoesPessoaisDto.ManagerDto.ManagerRegistryDto.ManagerRegistryClassDto;
//import br.com.erprms.DtoPort.PessoasDto.QualificacoesPessoaisDto.ManagerDto.ManagerRegistryDto.ManagerRegistryRecordDto;
//import br.com.erprms.RepositoryAdapter.PersonRepository.PersonRepository;
//import br.com.erprms.domainModel.personDomain.PersonEntity;
//import br.com.erprms.domainModel.personDomain.personComplement.StatusPersonalQualificationEnum;
//import br.com.erprms.domainModel.personDomain.personComplement.StatusPersonalUseEnum;
////import br.com.erprms.RepositoryAdapter.PessoasRepository.PessJuridicRepository;
//import lombok.NoArgsConstructor;
//
//@RestController
//@RequestMapping("manager")
//@NoArgsConstructor
//public class ManagerController {
////	private ManagerRepository managerRepository;
//	private ModelMapper modelMapper;
//	
//	@Autowired
//	private ManagerController(/*ManagerRepository managerRepository,*/ ModelMapper modelMapper) {
////		this.managerRepository = managerRepository;
////		this.managerRepository = managerRepository;
//	}
//	
////	@PostMapping
////	@Transactional
////	public void managerRegistion(@RequestBody ManagerRegistryRecordDto managerRegisterDto) {
////		managerRepository.save(modelMapper.map(managerRegisterDto, ManagerEntity.class));
////	}
//	
//	@PostMapping
//	@Transactional
//	public void register(@RequestBody ManagerRegistryRecordDto managerRegisterDto) {
//		saveManager(managerRegisterDto);
////		var managerRegistryClassDto = new ManagerRegistryClassDto(managerRegisterDto);
////		managerRepository.save(modelMapper.map(
////				new ManagerRegistryClassDto(managerRegisterDto)
////				, ManagerEntity.class));
//	}
//
//// Refatorar *****************************
//	@Autowired
//	private PersonRepository pessFisicRepository;
////	@Autowired
////	private PessJuridicRepository pessJuridicRepository;
//	private void saveManager(ManagerRegistryRecordDto managerRegisterDto) {
//		var managerRegistryClassDto = new ManagerRegistryClassDto(managerRegisterDto);
//
////		if (managerRegistryClassDto.getPessFisicId() != null) {
////			PersonEntity pessFisic 
////					= pessFisicRepository.getReferenceById(managerRegistryClassDto.getPessFisicId());
////			managerRegistryClassDto.setPessFisic(pessFisic);
////			pessFisic.setStatusPessEnum(StatusPersonalUseEnum.USED);
////			pessFisicRepository.save(pessFisic);
//		}
//
////		if (managerRegistryClassDto.getPessJuridicId() != null) {
////			PessJuridicEntity pessJuridic 
////					= pessJuridicRepository.getReferenceById(managerRegistryClassDto.getPessJuridicId());
////			managerRegistryClassDto.setPessJuridic(pessJuridic);
////			pessJuridic.setStatusPessEnum(StatusPessEnum.ACTIVE);
////			pessJuridicRepository.save(pessJuridic);
////		}
//
////		managerRepository.save(modelMapper.map(
////				managerRegistryClassDto
////				, ManagerEntity.class));
////	}
//// *****************************	
//
//	
//	@GetMapping
//	public Page<ManagerListingDto> managerLinting(Pageable managerListingPageable) {
////		Page<ManagerEntity> managerEntityPage	= managerRepository.findAll(managerListingPageable);
////		Type managerListingDtoType  = new TypeToken<Page<ManagerListingDto>>(){}.getType();
//		return null;
////				modelMapper.map(managerEntityPage, managerListingDtoType);
//	}
//
//	@PutMapping
//	@Transactional
//	public void managerUpdate (@RequestBody ManagerUpdateDto managerUpdateDto) {
////		modelMapper.map(managerUpdateDto,
////			managerRepository.getReferenceById(managerUpdateDto.id()));
//	}
//	
//	@DeleteMapping("/{id}")
//	@Transactional
//	public void managerDeletion (@PathVariable Long id) {
////		managerRepository
////			.getReferenceById(id)
////			.setStatusPersonalUse(StatusPersonalUseEnum.NOT_USED);
//	}
//	
//}
