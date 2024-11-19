package br.com.erprms.constantsAndBuilder;

import br.com.erprms.dtoPort.personDto.naturalPersonDto.DtoRecord_NaturalPersonOfRegistry;

public class Builder_NaturalPersonTest {
    private String fullNameOrEntityName;
    private String nickname;
    private String cpf;
    private String email;
    private String site;
    private String dateBorn;
    private String maritalStatus;
    private String cityBorn;
    private String countryBorn;
    private String sex;
    private String street;
    private String number;
    private String neighborhood;
    private String complement;
    private String postalCode;
    private String cityAndStateOrProvince;

    public static Builder_NaturalPersonTest naturalPersonTestBuilder(){
        Builder_NaturalPersonTest builder = new Builder_NaturalPersonTest();
        getTestData(builder);
        return builder;
    }

    public DtoRecord_NaturalPersonOfRegistry build(){
        return new DtoRecord_NaturalPersonOfRegistry(   fullNameOrEntityName,
                                                        nickname,
                                                        cpf,
                                                        email,
                                                        site,
                                                        dateBorn,
                                                        maritalStatus,
                                                        cityBorn,
                                                        countryBorn,
                                                        sex,
                                                        street,
                                                        number,
                                                        neighborhood,
                                                        complement,
                                                        postalCode,
                                                        cityAndStateOrProvince);
    }

    public Builder_NaturalPersonTest fullNameOrEntityName(String param){
        this.fullNameOrEntityName = param;
        return this;
    }

    public Builder_NaturalPersonTest nickname(String param){
            this.nickname = param;
            return this;
        }

    public Builder_NaturalPersonTest cpf(String param){
            this.cpf = param;
            return this;
        }

    public Builder_NaturalPersonTest email(String param){
            this.email = param;
            return this;
        }

    public Builder_NaturalPersonTest site(String param){
            this.site = param;
            return this;
        }

    public Builder_NaturalPersonTest dateBorn(String param){
            this.dateBorn = param;
            return this;
        }

    public Builder_NaturalPersonTest maritalStatus(String param){
            this.maritalStatus = param;
            return this;
        }

    public Builder_NaturalPersonTest cityBorn(String param){
            this.cityBorn = param;
            return this;
        }

    public Builder_NaturalPersonTest countryBorn(String param){
            this.countryBorn = param;
            return this;
        }

    public Builder_NaturalPersonTest sex(String param){
            this.sex = param;
            return this;
        }

    public Builder_NaturalPersonTest street(String param){
            this.street = param;
            return this;
        }

    public Builder_NaturalPersonTest number(String param){
            this.number = param;
            return this;
        }

    public Builder_NaturalPersonTest neighborhood(String param){
            this.neighborhood = param;
            return this;
        }

    public Builder_NaturalPersonTest complement(String param){
            this.complement = param;
            return this;
        }

    public Builder_NaturalPersonTest postalCode(String param){
            this.postalCode = param;
            return this;
        }

    public Builder_NaturalPersonTest cityAndStateOrProvince(String param){
            this.cityAndStateOrProvince = param;
            return this;
        }

    private static void getTestData(Builder_NaturalPersonTest builder) {
        builder.fullNameOrEntityName = "José da Silva";
        builder.nickname = "Silva";
        builder.cpf = "12345678912";
        builder.email = "josesilva@email.com.br";
        builder.site = "www.silva.com.br";
        builder.dateBorn = "20/01/1980";
        builder.maritalStatus = "casado";
        builder.cityBorn = "São Paulo";
        builder.countryBorn = "São Paulo";
        builder.sex = "MASCULINE";
        builder.street = "Rua Das Alamedas";
        builder.number = "0001";
        builder.neighborhood = "Bairro das Alamedas";
        builder.complement = "não há";
        builder.postalCode = "01153-000";
        builder.cityAndStateOrProvince = "São Paulo/SP";
    }
}
