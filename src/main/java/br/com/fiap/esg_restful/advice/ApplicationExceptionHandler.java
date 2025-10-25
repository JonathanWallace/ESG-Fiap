package br.com.fiap.esg_restful.advice;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestControllerAdvice
public class ApplicationExceptionHandler {

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Map<String, String> manusearArgumentosInvalidos(MethodArgumentNotValidException exception) {
        Map<String, String> mapaDeErro = new HashMap<>();
        List<FieldError> campos = exception.getBindingResult().getFieldErrors();

        for(FieldError campo : campos){
            mapaDeErro.put(campo.getField(), campo.getDefaultMessage());
        }
        return mapaDeErro;
    }
    
    @ResponseStatus(HttpStatus.CONFLICT)
    @ExceptionHandler(DataIntegrityViolationException.class)
    public Map<String, String> handleDataIntegrityViolationException(DataIntegrityViolationException exception) {
        Map<String, String> mapaDeErros = new HashMap<>();
        String mensagem = extrairMensagem(exception);
        if (mensagem != null) {
            if (mensagem.contains("ORA-02292")){
                mapaDeErros.put("mensagem", "Entidade possui dependentes no banco de dados");
            }
            if (mensagem.contains("ORA-01400")){
                mapaDeErros.put("mensagem", "Entidade não pode possuir dados nulos");
            }
        }
        return mapaDeErros;
    }

    private String extrairMensagem(Throwable throwable) {
        if (throwable.getCause() != null) {
            return extrairMensagem(throwable.getCause());
        }else {
            return throwable.getMessage();
        }
    }
}
