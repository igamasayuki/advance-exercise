package com.example.ec_201804d;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Not Fount(404)などのエラーを処理するコントローラ.<br>
 * 
 * 例外が起こった際の処理はGrobalExceptionHandlerで行っているので
 * このクラスが呼ばれるのは404の時のみ
 * 
 * @author igamasayuki
 *
 */
@Controller
public class NotFoundController implements ErrorController {

    
    /* (non-Javadoc)
     * @see org.springframework.boot.autoconfigure.web.ErrorController#getErrorPath()
     */
    @Override
    @RequestMapping("/error")
    public String getErrorPath() {
    	System.out.println("404 not found");
        return "error_page/404errorPage";
    }

}