package cmu.youchun.recommender.common;

import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

public class CommonUtil {
    /**
     * Process errors in forms.
     * @param bindingResult
     * @return All field error messages, separated by ","
     */
    public static String processErrorString(BindingResult bindingResult){
        if(!bindingResult.hasErrors()){
            return "";
        }
        StringBuilder stringBuilder = new StringBuilder();
        // concatenate error messages
        for(FieldError fieldError:bindingResult.getFieldErrors()){
            stringBuilder.append(fieldError.getDefaultMessage()+",");
        }
        // delete last ","
        return stringBuilder.substring(0,stringBuilder.length() - 1);
    }
}