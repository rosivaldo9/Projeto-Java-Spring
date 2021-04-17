package rosivaldo.rest.Controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import rosivaldo.exception.regraNegocioExceptiom;
import rosivaldo.rest.ApiErros;

@RestControllerAdvice
public class AplicatioControllerAdvice {

    @ExceptionHandler(regraNegocioExceptiom.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
public ApiErros handleNegocioException(regraNegocioExceptiom ex){
String mensagemErro = ex.getMessage();
return  new ApiErros(mensagemErro);
}
}
