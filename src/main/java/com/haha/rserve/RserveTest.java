//package com.haha.rserve;
//
//import org.rosuda.REngine.REXP;
//import org.rosuda.REngine.REXPMismatchException;
//import org.rosuda.REngine.Rserve.RConnection;
//import org.rosuda.REngine.Rserve.RserveException;
//
//public class RserveTest {
//    public static void main(String[] args) throws RserveException, REXPMismatchException {
//        RConnection c = new RConnection();
//        REXP x = c.eval("source(\"/"+args[0]+"\")");
//        System.out.println(x.asString());
//    }
//}
