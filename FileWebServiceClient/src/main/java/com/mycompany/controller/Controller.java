
package com.mycompany.controller;

import com.mycompany.entity.Document;
import com.mycompany.filewebservice.IFileWebService;
import java.util.List;

public class Controller {
    
    private final IFileWebService fileWebService;
    
    public Controller(IFileWebService fileWebService) {
        this.fileWebService = fileWebService;
    }
    
    public List<String> getListFiles() {
        try {  
            return fileWebService.getListFiles();
        } catch (Exception ex) {
            System.out.println(ex);
        }
        return null;
    }

    public Document getFile(String filePath) {
        try {
           
            Document documento = new Document(filePath, fileWebService.getFile(filePath));
            return documento;
        } catch (Exception ex) {
            System.out.println(ex);
        }
        return null;
    }
}
