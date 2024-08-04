package br.com.erprms.testBuilders;

import br.com.erprms.dtoPort.personDto.naturalPersonDto.DtoRecord_NaturalPersonOfRegistry;

public class NaturalPersonTestBuilder {
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

    public static NaturalPersonTestBuilder naturalPersonTestBuilder(){
        NaturalPersonTestBuilder builder = new NaturalPersonTestBuilder();
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

    public NaturalPersonTestBuilder fullNameOrEntityName(String param){
        this.fullNameOrEntityName = param;
        return this;
    }

    public NaturalPersonTestBuilder nickname(String param){
            this.nickname = param;
            return this;
        }

    public NaturalPersonTestBuilder cpf(String param){
            this.cpf = param;
            return this;
        }

    public NaturalPersonTestBuilder email(String param){
            this.email = param;
            return this;
        }

    public NaturalPersonTestBuilder site(String param){
            this.site = param;
            return this;
        }

    public NaturalPersonTestBuilder dateBorn(String param){
            this.dateBorn = param;
            return this;
        }

    public NaturalPersonTestBuilder maritalStatus(String param){
            this.maritalStatus = param;
            return this;
        }

    public NaturalPersonTestBuilder cityBorn(String param){
            this.cityBorn = param;
            return this;
        }

    public NaturalPersonTestBuilder countryBorn(String param){
            this.countryBorn = param;
            return this;
        }

    public NaturalPersonTestBuilder sex(String param){
            this.sex = param;
            return this;
        }

    public NaturalPersonTestBuilder street(String param){
            this.street = param;
            return this;
        }

    public NaturalPersonTestBuilder number(String param){
            this.number = param;
            return this;
        }

    public NaturalPersonTestBuilder neighborhood(String param){
            this.neighborhood = param;
            return this;
        }

    public NaturalPersonTestBuilder complement(String param){
            this.complement = param;
            return this;
        }

    public NaturalPersonTestBuilder postalCode(String param){
            this.postalCode = param;
            return this;
        }

    public NaturalPersonTestBuilder cityAndStateOrProvince(String param){
            this.cityAndStateOrProvince = param;
            return this;
        }

    private static void getTestData(NaturalPersonTestBuilder builder) {
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
