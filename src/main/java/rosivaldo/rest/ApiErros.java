package rosivaldo.rest;

import lombok.Data;

import java.util.Arrays;
import java.util.List;

@Data
public class ApiErros {
    private List<String> erros;

    public ApiErros(String msgsErro) {
        this.erros = Arrays.asList(msgsErro);
    }
}
