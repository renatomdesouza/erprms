package br.com.erprms.serviceApplication.personService.personQualificationHttpVerbService.ArgumentsProviders_Statics;

import java.util.stream.Stream;

import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.ArgumentsProvider;
import org.springframework.test.context.ActiveProfiles;

@ActiveProfiles("test")
public class HttpPost_Test01 implements ArgumentsProvider{
	
	 @Override
     public Stream<? extends Arguments> provideArguments(ExtensionContext context) throws Exception {
         return Stream.of(
                 Arguments.of((Object) new int[] {1, 2, 3, 4, 5}),
                 Arguments.of((Object) new int[] {6, 7, 8, 9, 0}),
                 Arguments.of((Object) new int[] {10, 11, 12, 13}),
                 Arguments.of((Object) new int[] {15, 16, 17, 18})
         );
     }

	
}
