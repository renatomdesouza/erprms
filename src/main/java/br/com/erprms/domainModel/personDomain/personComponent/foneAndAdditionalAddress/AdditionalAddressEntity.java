package br.com.erprms.domainModel.personDomain.personComponent.foneAndAdditionalAddress;

import br.com.erprms.domainModel.personDomain.PersonEntity;
import br.com.erprms.domainModel.personDomain.personComponent.AddressInterface;
import br.com.erprms.domainModel.personDomain.personQualification.personQualificationSuperclassEntity.personQualificationEnum.AdditionalAddressTypeEnum;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "additional_address_entity" )
@Getter
@Setter
public class AdditionalAddressEntity implements AddressInterface{
	@Column(name = "id")
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
		
	@Column(name = "additional_address_type")
	@Enumerated(EnumType.STRING)
	private AdditionalAddressTypeEnum additionalAddressType;

	@JoinColumn(name = "person_id")
	@OneToOne(mappedBy = "additionalAddressEntity")
	private PersonEntity person;

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
}
