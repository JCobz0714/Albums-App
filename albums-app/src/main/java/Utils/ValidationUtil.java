package Utils;

import java.util.HashMap;
import java.util.Map;

import org.springframework.validation.BindingResult;

public class ValidationUtil {
    //Creating the validation method to validate errors
    public static Map<String, String> validation(BindingResult result){
        Map<String, String> errors = new HashMap<>();

        result.getFieldErrors().forEach(err -> {
            errors.put(err.getField(), String.format("El campo {0} {1}", err.getField(), err.getDefaultMessage()));
        });

        return errors;
    }
}
