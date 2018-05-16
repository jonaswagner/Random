//package example;
//
//import java.util.Map;
//import java.util.concurrent.Future;
//import javax.faces.application.FacesMessage;
//import javax.faces.context.FacesContext;
//import javax.enterprise.concurrent.ManagedExecutorService;
//import javax.enterprise.concurrent.ManagedTask;
//import javax.enterprise.concurrent.ManagedTaskListener;
//
///**
// * @author Copyright (c) 2015, 2017, Oracle and/or its affiliates. All rights reserved.
// */
//public class MyTaskWithListener implements Runnable, ManagedTask, ManagedTaskListener {
//
//  FacesContext facesContext;
//
//  public MyTaskWithListener(FacesContext facesContext) {
//    this.facesContext = facesContext;
//  }
//
//  @Override
//  public void run() {
//    print("MyTaskWithListener.run() method is called.");
//  }
//
//  @Override
//  public void taskSubmitted(Future<?> future, ManagedExecutorService mes, Object task) {
//    print("MyTaskWithListener is submitted.");
//  }
//
//  @Override
//  public void taskAborted(Future<?> future, ManagedExecutorService mes, Object task, Throwable thrwbl) {
//    print("MyTaskWithListener is taskAborted.");
//  }
//
//  @Override
//  public void taskDone(Future<?> future, ManagedExecutorService mes, Object task, Throwable thrwbl) {
//    print("MyTaskWithListener is done.");
//  }
//
//  @Override
//  public void taskStarting(Future<?> future, ManagedExecutorService mes, Object task) {
//    print("MyTaskWithListener is stating.");
//  }
//
//  @Override
//  public ManagedTaskListener getManagedTaskListener() {
//    return this;
//  }
//
//  @Override
//  public Map<String, String> getExecutionProperties() {
//    return null;
//  }
//
//  private void print(String msg) {
//    facesContext.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, msg, null));
//  }
//}
