//package example;
//
//import javax.faces.application.FacesMessage;
//import javax.faces.context.FacesContext;
//
///**
// * @author Copyright (c) 2015, 2017, Oracle and/or its affiliates. All rights reserved.
// */
//public class MyRunnableTask implements Runnable {
//
//  FacesContext facesContext;
//
//  public MyRunnableTask(FacesContext facesContext) {
//    this.facesContext = facesContext;
//  }
//
//  @Override
//  public void run() {
//    facesContext.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "MyRunnableTask.run() method is called" +
//        ".", null));
//  }
//}
