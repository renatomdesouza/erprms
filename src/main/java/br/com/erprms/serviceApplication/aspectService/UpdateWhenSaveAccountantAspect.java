package br.com.erprms.serviceApplication.aspectService;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;


@Aspect
@Component
public class UpdateWhenSaveAccountantAspect {
//	@Autowired
//	PessFisicRepository pessFisicRepository;
//	
//	@Autowired
//	PessJuridicRepository pessJuridicRepository;
	
//	@Around(value = "@annotation(TestAspectAnotation) && args(qualifRegistryPeopleDto)") // && args(qualifRegistryPeopleDto)
//	@Around("@annotation(TestAspectAnotation)")
	public Object updateWhenSave(ProceedingJoinPoint joinPoint) throws Throwable {
//		if (qualifRegistryPeopleDto.getPessFisicId() != null) {
//			PessFisicEntity pessFisic 
//					= pessFisicRepository.getReferenceById(qualifRegistryPeopleDto.getPessFisicId());
//			qualifRegistryPeopleDto.setPessFisic(pessFisic);
//			pessFisic.setStatusPessEnum(StatusPessEnum.ACTIVE);
//			pessFisicRepository.save(pessFisic);
//		}
//
//		if (qualifRegistryPeopleDto.getPessJuridicId() != null) {
//			PessJuridicEntity pessJuridic 
//					= pessJuridicRepository.getReferenceById(qualifRegistryPeopleDto.getPessJuridicId());
//			qualifRegistryPeopleDto.setPessJuridic(pessJuridic);
//			pessJuridic.setStatusPessEnum(StatusPessEnum.ACTIVE);
//			pessJuridicRepository.save(pessJuridic);
//		}
		return joinPoint.proceed();

	}
	
}
