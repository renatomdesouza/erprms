package br.com.erprms.infrastructure.springSecurity;

public class PasswordCheckerSimulator  /*implements CompromisedPasswordChecker*/ {
    public static final String FAILURE_KEYWORD = "compromised";

//    @Override
//    public CompromisedPasswordDecision check(String password) {
//        boolean isPasswordCompromised = false;
//        if (password.contains(FAILURE_KEYWORD)) {
//            isPasswordCompromised = true;
//        }
//        return new CompromisedPasswordDecision(isPasswordCompromised);
//    }
}
