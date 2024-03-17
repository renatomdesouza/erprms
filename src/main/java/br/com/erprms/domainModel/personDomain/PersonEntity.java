package br.com.erprms.domainModel.personDomain;

import java.util.Set;

import br.com.erprms.domainModel.personDomain.personComponent.AddressInterface;
import br.com.erprms.domainModel.personDomain.personComponent.LegalPersonInterface;
import br.com.erprms.domainModel.personDomain.personComponent.PepleCommonDataInterface;
import br.com.erprms.domainModel.personDomain.personComponent.NaturalPersonInterface;
import br.com.erprms.domainModel.personDomain.personComponent.foneAndAdditionalAddress.AdditionalAddressEntity;
import br.com.erprms.domainModel.personDomain.personComponent.foneAndAdditionalAddress.FoneEntity;
import br.com.erprms.domainModel.personDomain.personComponent.personEnum.SexEnum;
import br.com.erprms.domainModel.personDomain.personComponent.personEnum.StatusPersonalUseEnum;
import br.com.erprms.domainModel.personDomain.personQualification.PersonQualificationSuperclassEntity;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "person")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public  class PersonEntity implements 
									PepleCommonDataInterface, 
									NaturalPersonInterface, 
									LegalPersonInterface, 
									AddressInterface {  
	@Column(name = "id")
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@OneToMany(mappedBy = "person", cascade = CascadeType.ALL)
	private Set<PersonQualificationSuperclassEntity> personQualification;
	
	@Column(name = "status_person_enum")
	@Enumerated(EnumType.STRING)
	public StatusPersonalUseEnum statusPersonEnum;

	@Column(name = "is_natural_person")		
	public Boolean isNaturalPerson;
 
	@Column(name = "full_name_or_entity_name")
 	private String fullNameOrEntityName;
 
	@Column(name = "nickname")
	private String nickname;
	 
	@Column(name = "cpf_or_Cnpj")
 	private String cpfOrCnpj;
	 
	@Column(name = "email")
 	private String email;
	 
	@Column(name = "site")
 	private String site;

	@Column(name = "date_born")
	private String dateBorn;
	
	@Column(name = "marital_status")
	private String maritalStatus;
	
	@Column(name = "city_born")
	private String cityBorn;
	
	@Column(name = "country_born")
	private String countryBorn;
	
	@Column(name = "sex")
	@Enumerated(EnumType.STRING)
	private SexEnum sex;
	
	@Column(name = "inscric_estad")
	private String inscricEstad;
	
	@Column(name = "inscric_municip")
	private String inscricMunicip;

	@Column(name = "street")
	private String street;
	
	@Column(name = "number")
	private String number;
	
	@Column(name = "neighborhood")
	private String neighborhood;
	
	@Column(name = "complement")
	private String complement;
	
	@Column(name = "postal_code")
	private String postalCode;
	
	@Column(name = "city_and_state_or_province")
	private String cityAndStateOrProvince;

	@OneToMany(mappedBy = "person", cascade = CascadeType.ALL)
	private Set<FoneEntity> fones;

	@OneToOne(cascade = CascadeType.ALL)
	@JoinTable(name = "PersonEntity_x_AdditionalAddressEntity", 
	joinColumns = 
		{ @JoinColumn(name = "personEntityId", referencedColumnName = "id") },
		inverseJoinColumns = 
		{ @JoinColumn(name = "additionalAddressEntityId", referencedColumnName = "id") })
	private AdditionalAddressEntity additionalAddressEntity;
}

