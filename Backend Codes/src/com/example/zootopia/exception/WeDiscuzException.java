package com.example.zootopia.exception;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Date;

/**
 * Exception - exception class to correct error
 *
 * Created by Linpeng Lyu on 3/24/16.
 * Andrew ID: linpengl
 */
public class WeDiscuzException extends Exception {
    private ErrorNo errorno;
    private ErrorMessage errormsg;

    private final static int ERROR1 = 1;
    private final static int ERROR2 = 2;

    private final static String ERRORMSG1 = "Some fields requires a non-negative number!";
    private final static String ERRORMSG2 = "The interest rate must be less than 100%!)";

    private String logfilename = "log.txt";

    public enum ErrorNo {
        ERROR1, ERROR2
    }

    public enum ErrorMessage {
        ERRORMSG1, ERRORMSG2
    }

    // Constructor
    public WeDiscuzException() {
    }

    public WeDiscuzException(int errorno) {
        switch (errorno) {
            case 1:
                this.errorno = ErrorNo.ERROR1;
                this.errormsg = ErrorMessage.ERRORMSG1;
                break;
            case 2:
                this.errorno = ErrorNo.ERROR2;
                this.errormsg = ErrorMessage.ERRORMSG2;
                break;
        }

    }

    // Getters and Setters
    public ErrorNo getErrorno() {
        return errorno;
    }

    public void setErrorno(ErrorNo errorno) {
        this.errorno = errorno;
    }

    public ErrorMessage getErrormsg() {
        return errormsg;
    }

    public void setErrormsg(ErrorMessage errormsg) {
        this.errormsg = errormsg;
    }

    public void fix() {
        Fix1ton f = new Fix1ton();

        switch (errorno) {
            case ERROR1:
                f.fix1(1);
                break;
            case ERROR2:
                f.fix2(2);
                break;
        }
    }

    // print
    public void print() {
        System.out.println("Error Number:" + errorno);
        System.out.println("Error Message:" + errormsg);
        try {
            printLog();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Log exception
    public void printLog() throws IOException {
        BufferedWriter output = null;
        try {
            output = new BufferedWriter(new FileWriter(logfilename, true));
            StringBuilder str = new StringBuilder();
            Date date = new Date();
            str.append(date.toString());
            str.append("\t");
            str.append(errorno);
            str.append("\t");
            str.append(errormsg);
            str.append("\n");
            output.write(str.toString());
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (output != null)
                output.close();
        }
    }
}
