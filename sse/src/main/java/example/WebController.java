//package example;
//
//import javax.annotation.PostConstruct;
//import javax.annotation.PreDestroy;
//import javax.annotation.Resource;
//import javax.enterprise.inject.Model;
//import javax.faces.application.FacesMessage;
//import javax.faces.context.FacesContext;
//import javax.enterprise.concurrent.ManagedExecutorService;
//import javax.naming.InitialContext;
//import javax.naming.NamingException;
//import java.util.ArrayList;
//import java.util.Collection;
//import java.util.List;
//import java.util.concurrent.Callable;
//import java.util.concurrent.ExecutionException;
//import java.util.concurrent.Future;
//import java.util.concurrent.TimeUnit;
//import java.util.concurrent.TimeoutException;
//import java.util.logging.Level;
//import java.util.logging.Logger;
//
///**
// * @author Copyright (c) 2015, 2017, Oracle and/or its affiliates. All rights reserved.
// */
//@Model
//public class WebController {
//
//  @Resource
//  ManagedExecutorService executor;
//
//  private final static String RESPONSE = "response";
//
//  private FacesContext context;
//
//  @PostConstruct
//  public void initContext() {
//    context = FacesContext.getCurrentInstance();
//  }
//
//  /**
//   * Get ManagedExecutorService by injection
//   *
//   * @return
//   */
//  public String executeInject() {
//    executor.submit(getTask());
//    return RESPONSE;
//  }
//
//  /**
//   * Get ManagedExecutorService by JNDI
//   *
//   * @return
//   */
//  public String executeJNDI() {
//    InitialContext ctx = null;
//    try {
//      ctx = new InitialContext();
//      executor = (ManagedExecutorService) ctx.lookup("java:comp/DefaultManagedExecutorService");
//    } catch (NamingException ex) {
//      Logger.getLogger(WebController.class.getName()).log(Level.SEVERE, null, ex);
//      throw new RuntimeException(ex);
//    }
//    executor.submit(getTask());
//    return RESPONSE;
//  }
//
//  /**
//   * Submit tasks by invokeAll
//   *
//   * @return
//   */
//  public String executeInvokeAll() {
//    Collection<Callable<String>> tasks = new ArrayList();
//    tasks.add(getCallableTask());
//    tasks.add(getCallableTask());
//    try {
//      List<Future<String>> results = executor.invokeAll(tasks);
//      for (Future<String> f : results) {
//        print("[InvokeAll] one has completed successfully and got '" + f.get() + "'.");
//      }
//    } catch (InterruptedException | ExecutionException ex) {
//      Logger.getLogger(WebController.class.getName()).log(Level.SEVERE, null, ex);
//      throw new RuntimeException(ex);
//    }
//    return RESPONSE;
//  }
//
//  /**
//   * Submit tasks by invokeAny
//   *
//   * @return
//   */
//  public String executeInvokeAny() {
//    Collection<Callable<String>> tasks = new ArrayList();
//    tasks.add(getCallableTask());
//    tasks.add(getCallableTask());
//    try {
//      String result = executor.invokeAny(tasks, 2, TimeUnit.SECONDS);
//      print("[InvokeAny] one has completed successfully and got '" + result + "'.");
//    } catch (InterruptedException | TimeoutException | ExecutionException ex) {
//      Logger.getLogger(WebController.class.getName()).log(Level.SEVERE, null, ex);
//      throw new RuntimeException(ex);
//    }
//    return RESPONSE;
//  }
//
//  /**
//   * ManagedTaskListener
//   *
//   * @return
//   */
//  public String executeListener() {
//    executor.submit(new MyTaskWithListener(context));
//    return RESPONSE;
//  }
//
//  private Runnable getTask() {
//    return new MyRunnableTask(context);
//  }
//
//  private Callable<String> getCallableTask() {
//    return new MyCallableTask();
//  }
//
//  private void print(String msg) {
//    context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, msg, null));
//  }
//
//  @PreDestroy
//  public void destroy() {
//    executor = null;
//  }
//
//}