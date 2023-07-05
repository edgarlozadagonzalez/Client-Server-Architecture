package com.mycompany.filewebservice;

import javax.jws.WebService;
import javax.jws.WebMethod;
import javax.jws.WebParam;


@WebService
public interface IFileWebService {

    @WebMethod(operationName = "getListFiles")
    public String[] getListFiles();

  
    @WebMethod(operationName = "getFile")
    public byte[] getFile(@WebParam(name = "filePath") String filePath);
    
}
