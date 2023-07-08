package com.mycompany.main;

import com.mycompany.controller.Controller;
import com.mycompany.filewebservice.FileWeb;
import com.mycompany.filewebservice.IFileWebService;
import com.mycompany.view.ClientConsole;

public class Main {

    public static void main(String[] args) {

        FileWeb service = new FileWeb();
        IFileWebService webService = service.getFileWebServiceImplPort();
        
        Controller controller = new Controller(webService);
        
        ClientConsole view = new ClientConsole(controller);
        
        view.menu();
    }
}
